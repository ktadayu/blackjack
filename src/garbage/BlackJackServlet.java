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
import blackjack.players.Dealer;
import blackjack.players.Player;
import model.User;


@WebServlet("/BlackJackServlet")
public class BlackJackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public BlackJackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    //restart
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	IOException {

		String nextPage = "/view/game/game_top.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);

	}

	//変更→post送信で受け取ることに
	//betting画面からの遷移
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッションからユーザーを取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("USER");

		//選択されたベット額を取得しuserとsessionに登録
		int betPoint = Integer.parseInt(request.getParameter("betPoint"));
		user.setNumberOfTips(user.getNumberOfTips() - betPoint);
		session.setAttribute("BETPOINT", betPoint);

		//ゲームの初期化(デッキ生成, プレイヤとディーラーの生成)
		startGame(session);

		String nextPage = "/PlayingServlet";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}


	public void startGame(HttpSession session) {
		//ゲーム初期化
		Deck deck = new Deck(); //デッキ生成
		Player player = new Player();//プレイヤー生成
		Dealer dealer = new Dealer();//ディーラー生成

		session.setAttribute("DECK", deck );
		session.setAttribute("PLAYER", player );
		session.setAttribute("DEALER", dealer );

	}

}
