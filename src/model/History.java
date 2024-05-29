package model;

public class History {

	private int history_id;
	private int amountOfChanges;
	private String timeStamp;


	public History(int history_id, int amountOfChanges, String timeStamp){
		this.amountOfChanges = amountOfChanges;
		this.timeStamp = timeStamp;
	}

	public int getAmountOfChanges() {
		return amountOfChanges;
	}
	public int getHistoryId() {
		return history_id;
	}
	public String getTimeStamp() {
		return timeStamp;
	}

}
