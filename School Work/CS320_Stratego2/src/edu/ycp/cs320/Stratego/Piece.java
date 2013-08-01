package edu.ycp.cs320.Stratego;

import java.util.Observable;
import java.util.Observer;

public class Piece extends Observable{

	private boolean humanPiece;
	private Pieces value;
	private int moveType;
	private boolean hasBeenSeen;
	private boolean hasBeenRemoved;
	private boolean isOnBoard;
	
	public Piece()	//creates an empty/null piece
	{
		return;
	}
	
	public Piece(boolean isHuman, Pieces value)	//constructor for a new piece
	{
		humanPiece = isHuman;
		this.value = value;
		hasBeenSeen = false;
		hasBeenRemoved = false;
		isOnBoard = false;
		
		if(value.equals(Pieces.BOMB) || value.equals(Pieces.FLAG))
			moveType = 0;
		else if(value.equals(Pieces.TWO))
			moveType = 2;
		else
			moveType = 1;
		
		return;
	}
	
	public void set_human(boolean isHuman)	//sets if the piece is controlled by a human or a computer
	{
		humanPiece = isHuman;
		return;
	}
	
	public void set_Value(Pieces value)		//sets the piece's value from a list of enumerations
	{
		this.value = value;
		
		if(value.equals(Pieces.BOMB) || value.equals(Pieces.FLAG))
			moveType = 0;
		else if(value.equals(Pieces.TWO))
			moveType = 2;
		else
			moveType = 1;
		
		return;
	}
	
	public void set_MoveType(int moveType)	//gives the piece an int defining the move type
	{
		this.moveType = moveType;
		return;
	}
	
	public void set_hasBeenSeen(boolean seen)	//sets if the piece has been revealed to an opponent
	{
		hasBeenSeen = seen;
		return;
	}
	
	public void set_hasBeenRemoved(boolean removed)	//sets if the piece has been removed from the board
	{
		hasBeenRemoved = removed;
		return;
	}
	
	public void set_isOnBoard(boolean onBoard)	//sets if the piece has been placed on the board yet
	{
		isOnBoard = onBoard;
		return;
	}
	
	public boolean get_isHuman()	//returns if the piece is controlled by the human player
	{
		return humanPiece;
	}
	
	public Pieces get_Value()		//returns the enumeration(value) of the piece
	{
		return value;
	}
	
	public int get_MoveType()		//returns the move type of the piece
	{
		return moveType;
	}
	
	public boolean get_hasBeenSeen()	//returns if the piece has been revealed to an opponent
	{
		return hasBeenSeen;
	}
	
	public boolean get_hasBeenRemoved()		//returns if the piece has been removed from the board
	{
		return hasBeenRemoved;
	}
	
	public boolean get_isOnBoard()		//return if the piece has been placed on the board
	{
		return isOnBoard;
	}
}
