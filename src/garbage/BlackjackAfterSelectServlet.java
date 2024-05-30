package garbage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blackjack.Deck;
import blackjack.Hand;
import blackjack.players.Dealer;
import blackjack.players.Player;
import dao.UserDao;
import exception.MyException;
import model.User;


@WebServlet("/BlackjackAfterSelectServlet")
public class BlackjackAfterSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public BlackjackAfterSelectServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	//opt = hit or standで画面遷移
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("USER");
		Deck deck = (Deck) session.getAttribute("DECK");
		Player player =(Player) session.getAttribute("PLAYER");
		Dealer dealer = (Dealer) session.getAttribute("DEALER");
		Integer betPoint = (Integer) session.getAttribute("BETPOINT");

		//選択の取得
		String opt = request.getParameter("opt");

		//画面遷移先の設定
		String nextPage = "/view/game/game_playing.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);

		//手札の取得
		Hand playerHand = player.getHand();
		Hand dealerHand = dealer.getHand();

		Boolean dic = true;//true:ゲーム中 false:ゲーム終了
		request.setAttribute("dic", dic);

		//プレイヤーの選択による分岐
		//汚いので後で修正
		if(opt.equals("hit") && !playerHand.isBurst()) {
			player.drawCard(deck);
			if(playerHand.isBurst()) {
				String e = "プレイヤーの負けです";
				updateStatus(user,request);
				addHistory(user,-betPoint,request);
				dic = false;
				request.setAttribute("msg", e);
				request.setAttribute("dic", dic);
				requestDispatcher.forward(request, response);
				return ;
			}else if(playerHand.totalValue() == 21){
				dic = false ;
			}else{//再選択
				requestDispatcher.forward(request, response);
				return ;
			}
		}else if(opt.equals("stand")) {
			 dic = false;
		}

		//dealerのターン
		dealer.drawCard(deck);

		//勝敗
		String e;

		 if(playerHand.totalValue() > dealerHand.totalValue() || dealerHand.totalValue() > 21){
				 e = "プレイヤーの勝利";
				 user.setNumberOfTips(user.getNumberOfTips() + 2*betPoint);
				 addHistory(user,+betPoint,request);
				 updateStatus(user,request);
			}else if(playerHand.totalValue() == dealerHand.totalValue()){
				 e = "引き分け";
				 user.setNumberOfTips(user.getNumberOfTips() + betPoint);
				 addHistory(user,0,request);
			}else {
				 e = "ディーラーの勝利";
				 updateStatus(user,request);
				 addHistory(user,-betPoint,request);
			}
			request.setAttribute("msg", e);
			request.setAttribute("dic", false);
		requestDispatcher.forward(request, response);

	}

	//DBのuserへ現在のチップ数を反映させるメソッド
	public void updateStatus(User user , HttpServletRequest request) {

		try {
		UserDao userDao = new UserDao();
		userDao.updateNumberOfTips(user);
		}catch(MyException e) {
			String message = e.getMessage();
			request.setAttribute("message", message);
		}finally {

		}
	}

	//DBで勝敗を記録する
	public void addHistory(User user, int amountOfChange ,HttpServletRequest request) {

		try {
		UserDao userDao = new UserDao();
		userDao.addToHistory(user,amountOfChange);
		}catch(MyException e) {
			String message = e.getMessage();
			request.setAttribute("message", message);
		}finally {

		}
	}



}
