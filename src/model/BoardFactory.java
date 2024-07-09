package model;

public class BoardFactory {
	public Board create9x9Board() {
		return new Board9x9Model();
	}

	public Board create13x13Board() {
		return new Board9x9Model();
	}
}
