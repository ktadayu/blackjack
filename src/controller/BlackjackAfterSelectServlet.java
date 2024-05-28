package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blackjack.cal.Deck;
import blackjack.cal.Hand;
import blackjack.players.Dealer;
import blackjack.players.Player;
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		Deck deck = (Deck) session.getAttribute("DECK");
		Player player =(Player) session.getAttribute("PLAYER");
		Dealer dealer = (Dealer) session.getAttribute("DEALER");
		User user = (User) session.getAttribute("USER");

		String opt = request.getParameter("opt");

		String nextPage = "/view/game/game_playing.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);

		Hand playerHand = player.getHand();
		Hand dealerHand = dealer.getHand();

		Boolean dic = true;//true:ゲーム中 false:ゲーム終了
		request.setAttribute("dic", dic);

			if(opt.equals("hit") && playerHand.totalValue() < 21) {
				player.drawCard(deck);
				if(playerHand.totalValue() >21) {
					String e = "プレイヤーの負けです";
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

		System.out.println("現在のチップ数=" + user.getNumberOfTips());

		//勝敗
		//あとで書き直す
		String e;

		 if(playerHand.totalValue() > dealerHand.totalValue() || dealerHand.totalValue() > 21){
				 e = "プレイヤーの勝利";
			}else if(playerHand.totalValue() == dealerHand.totalValue()){
				 e = "引き分け";
			}else {
				 e = "ディーラーの勝利";
			}
			request.setAttribute("msg", e);
			request.setAttribute("dic", false);
		requestDispatcher.forward(request, response);

	}

}
