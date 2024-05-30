package blackjack;

public class PlayerStats {

	//結果回数管理
	int wins = 0;
	int loses = 0;
	int ties = 0;

	public void addWins() {
		wins++;
	}

	public void addloses() {
		loses++;
	}

	public void addTies() {
		ties++;
	}

	public int getWins() {
		return wins;
	}

	public int getLoses() {
		return loses;
	}

	public int getTies() {
		return ties;
	}


}
