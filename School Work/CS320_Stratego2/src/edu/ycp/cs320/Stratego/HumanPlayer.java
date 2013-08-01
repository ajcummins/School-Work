package edu.ycp.cs320.Stratego;

import java.util.ArrayList;

public class HumanPlayer extends Player
{
	//private GameInstance gameInt;
	
	public HumanPlayer(GameInstance game)
	{
		super(game);
	}
	
	@Override 
	public boolean makeMove(Location from, Location to)
	{
		return gameInt.Move(from, to); // we need getter's for the Loc to 												   // move from and Loc to move to
	}
	
	@Override 
	public boolean hasMoveablePieces()
	{
		ArrayList<Piece> graveYard = gameInt.getGraveYard();
		int numOfRemovedMoveable = 0;
		for(int l = 0; l < graveYard.size(); l++)
		{
			if(graveYard.get(l) != null)
			{
				if(graveYard.get(l).get_isHuman() && (graveYard.get(l).get_MoveType() > 0)) // if the piece is a human piece
				{																			// and if its a moveable piece
					numOfRemovedMoveable ++;
				}
			}
			
		}
		
		if(numOfRemovedMoveable == 33) // if the number of removed moveable pieces equals the number of moveable pieces
		{								// each player gets then the player doesn't have any more moveable pieces they lose
			gameInt.setWinner(2);		// the player loses let the GameInstance know the player lost
			return false;
		}
		else
		{
			return true;
		}
		
	}
}

