package edu.ycp.cs320.Stratego;

import java.util.Observable;
import java.util.Observer;


public class Board extends Observable
{
	private Square[][] board;
	private int rows;
	private int cols;
	
	public Board()
	{
		rows = 10;
		cols = 10;
		board = new Square[10][10];
		for(int row = 0; row < 10; row ++)
		{
			for(int col = 0; col < 10; col ++)
			{
				board[row][col] = new Square(new Piece(false, Pieces.NO_PIECE));
                if((col==4 && row==2) || (col==5 && row==2) || (col==4 && row==3) || (col==5 && row==3) || (col==4 && row==6) || (col==5 && row==6) || (col==4 && row==7) || (col==5 && row==7))
				{ // set the Obstacles up
					board[row][col].set_Unpassable(true);
				}
                else if(col == 4 || col == 5 )
                { // set the empty spaces to NoPieces
                	
            		if(row == 0 || row ==1 || row == 4 || row == 5 || row == 8 || row == 9)
            		{
            			board[row][col].set_Piece(new Piece(false, Pieces.NO_PIECE));
            		}
                	
                }
            }
		}
	}
	

	
	public Board(int colsX, int rowsY)  // in order to get a graveyard that works like a board for setup we have to have a way
	{					 				// to set the size of the board
		board = new Square[colsX][rowsY];
		for(int col = 0; col < colsX; col ++)
		{
			for(int row = 0; row < rowsY; row ++)
			{
				board[col][row] = new Square(new Piece(false, Pieces.NO_PIECE));
			}
		}
		rows = rowsY;
		cols = colsX;
	}
	
	@Override
	public synchronized void addObserver(Observer o) {
		super.addObserver(o);
		
		// the Square objects will need to notify the Observer of changes 
		for(int col = 0; col < cols; col ++)
		{
			for(int row = 0; row < rows; row ++)
			{
				board[col][row].addObserver(o);
			}
		}
	}
	
	@Override
	public synchronized void deleteObserver(Observer o) {
		// unregister the observer from the Square objects 
		for(int col = 0; col < cols; col ++)
		{
			for(int row = 0; row < rows; row ++)
			{
				board[col][row].deleteObserver(o);
			}
		}
		rows = 10;
		cols = 10;
		
	}
	
	
	
	public boolean Has_Piece(Location loc)
	{
		return board[loc.get_X()][loc.get_Y()].hasPiece();
	}
	
	public boolean put_Piece(Location loc, Piece p)		//if piece is placed on board, return true. If not, return false
	{
		int x = loc.get_X();
		int y = loc.get_Y();
		
		if(board[x][y].get_Piece()!=null && !board[x][y].isUnpassable() && inValidStartZone(y,p))
		{
			board[x][y].set_Piece(p);
			return true;
		}
		else
			return false;								//CHECK IF PIECE IS PLACED IN VALID STARTING ZONE
	}
	
	public void Remove_piece(int x, int y)
	{
		
		board[x][y].get_Piece().set_hasBeenRemoved(true);
		
		// need to send the removed piece to the graveyard 
		// so to the array holding all the removed pieces.
	}
	
	public boolean inValidStartZone(int y, Piece p)
	{
		if(p.get_isHuman() && y<4)
			return true;
		else if(!p.get_isHuman() && y>5)
			return true;
		else
			return false;
	}

	public Piece getPiece(Location in)
	{
		return getSquare(in).get_Piece();
	}

	public Square getSquare(Location in)
	{
		return board[in.get_X()][in.get_Y()];
	}
}
