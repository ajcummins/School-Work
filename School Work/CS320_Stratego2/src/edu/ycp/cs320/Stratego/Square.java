package edu.ycp.cs320.Stratego;

import java.util.Observable;
import java.util.Observer;

public class Square extends Observable {

	private boolean unpassable;
	private Piece piece;
	
	public Square()	//creates a location with x and y coordinates
	{
		unpassable = false;
		return;
	}
	
	public Square(Piece piece)	//creates a location with x and y coordinates and a piece
	{
		this.piece = piece;
		unpassable = false;
	}
	
	public void set_Unpassable(boolean unpassable)		//Sets the location as unpassable
	{
		this.unpassable = unpassable;
		setChanged();
		notifyObservers();
	}
	
	public void set_Piece(boolean isHuman, Pieces value)	//Creates a piece in current location
	{
		this.piece = new Piece(isHuman,value);
		setChanged();
		notifyObservers();
		return;
	}
	
	public void set_Piece(Piece p)			//Sets a piece object in current location
	{
		this.piece = p;
		setChanged();
		notifyObservers();
		return;
	}
	
	public Piece get_Piece()		//returns the piece in the current location
	{
		return piece;
	}
	
	public boolean hasPiece()	//returns if the location has a piece
	{
		if(piece.get_Value()==Pieces.NO_PIECE)
			return false;
		else
			return true;
	}
	
	public boolean isUnpassable()		//returns if the location is unpassable
	{
		return unpassable;
	}
}
