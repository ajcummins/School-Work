package edu.ycp.cs320.Stratego;

public enum Pieces {

	NO_PIECE(""),
	FLAG("F"),	//lowest so anything can take it
	SPY("S"),	//special piece can only take flag and ten
	TWO("2"),
	THREE("3"),
	FOUR("4"),
	FIVE("5"),
	SIX("6"),
	SEVEN("7"),
	EIGHT("8"),
	NINE("9"),
	TEN("10"),
	BOMB("B");	//destroys everything but 3

	private String str;
	
	private Pieces(String str) {
		this.str = str;
	}
	
	@Override
	public String toString() {
		return str;
	}
}
