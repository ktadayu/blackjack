package model;

public class FlagOwner {

	//endFlag0 : 通常手札
	//endFlag1 : split後手札1
	//endFlag2 : split後手札2
	static Boolean endFlag0 = false;
	static Boolean endFlag1 = false;
	static Boolean endFlag2 = false;
	static Boolean endFlag = false;

	public static Boolean checkEndGame() {
		return endFlag0 ;
//		return endFlag0 && endFlag1 && endFlag2;
	}

	public static void validate0() {
		endFlag0 = true;
	}
	public static void validate1() {
		endFlag1 = true;
	}
	public static void validate2() {
		endFlag2 = true;
	}
	public static void validate() {
		endFlag = true;
	}


	static void resetFlag() {
		 endFlag0 = false;
		 endFlag1 = false;
		 endFlag2 = false;
		 endFlag = false;
	}




}
