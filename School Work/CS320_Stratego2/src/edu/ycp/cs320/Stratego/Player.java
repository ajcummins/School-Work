package edu.ycp.cs320.Stratego;

public class Player {
	
	protected GameInstance gameInt;
	
	public Player(GameInstance game)
	{
		gameInt = game;
	}
	
	public boolean makeMove(Location from, Location to)
	{
		return false;
	}
	public boolean makeMove()
	{
		return false;
	}
	
	public boolean hasMoveablePieces()
	{
		return false;
	}
	
	
}