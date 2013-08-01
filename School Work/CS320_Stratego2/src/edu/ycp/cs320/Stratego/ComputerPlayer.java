package edu.ycp.cs320.Stratego;
import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player 
{
	private Location compLocToMoveFrom;
	private Location compLocToMoveTo;
	
	
	
	private Piece compPieceToMove;
	
	
	
	ComputerPlayer(GameInstance game)
	{
		super(game); // sets the super gameInt = game
	}
	
	@Override 
	public boolean makeMove()
	{
		boolean moved = false;
		Location select;
		
		gameInt.setHumanTurn(false);
		
		while(!moved)
		{
			System.out.println("Computer Attempts Move");
			Random r = new Random();
			
			int temp = r.nextInt(gameInt.getComputerPieces().size());
			
			select = gameInt.getComputerPieces().get(temp);
			
			compLocToMoveFrom = select;
			
			
			
//			int t = r.nextInt(4);
			
			for(int t=0;t<4;t++)
			{
				if(!moved && t==0 && select.get_X()<8) 
				{
					moved = gameInt.compMove(select,new Location(select.get_X()+1,select.get_Y())); 
					if(moved)
					{
						gameInt.getComputerPieces().remove(temp);
						gameInt.getComputerPieces().add(new Location(select.get_X()+1,select.get_Y()));
					}
				}
				if(!moved && t==1 && select.get_Y()<8)
				{
					moved = gameInt.compMove(select,new Location(select.get_X(),select.get_Y()+1));
					if(moved)
					{
						gameInt.getComputerPieces().remove(temp);
						gameInt.getComputerPieces().add(new Location(select.get_X(),select.get_Y()+1));
					}
				}
				if(!moved && t==2 && select.get_X()>0)
				{
					moved = gameInt.compMove(select,new Location(select.get_X()-1,select.get_Y()));
					if(moved)
					{
						gameInt.getComputerPieces().remove(temp);
						gameInt.getComputerPieces().add(new Location(select.get_X()-1,select.get_Y()));
					}
				}
				if(!moved && t==3 && select.get_Y()>0)
				{
					moved = gameInt.compMove(select,new Location(select.get_X(),select.get_Y()-1));
					if(moved)
					{
						gameInt.getComputerPieces().remove(temp);
						gameInt.getComputerPieces().add(new Location(select.get_X(),select.get_Y()-1));
					}
				}
			}
			
		}
		
		gameInt.setHumanTurn(true);
		
		/*
		// first we need to get a piece to move....
		
		// and we need to check if the selected piece is valid
		boolean invalid = true;
		
		while (invalid)
		{
			Random rand = new Random(10);	// this should restrict the numbers that this should return between 0-9
			compLocToMoveFrom = new Location(rand.nextInt(),rand.nextInt());
			compPieceToMove = gameInt.getBoard().getPiece(compLocToMoveFrom);
			
			if((!compPieceToMove.get_isHuman()) && (compPieceToMove.get_MoveType()>0) && this.isMoveAvail())
			{
				invalid = false;
			}
			else
			{
				invalid = true;
			}
			
		}

		
		// now we need to pick a location for it to move to...
		
		invalid = true;
		
		while(invalid)
		{
			Location[] availLocs = this.movesAvail();
			for(int i = 0; i < 4; i++)
			{
				if(availLocs[i] != null)
				{
					invalid = false;	// if there is at least one move set invalid to false
				}
			}
			// this is the chain of if elses that will make it more probable that the comps pieces will move downward
			
			if(availLocs[0] != null)// if below the piece is available
			{
				compLocToMoveTo = availLocs[0]; 

				if(compLocToMoveTo.get_Y()+1 < 10) 	// this should take care of if the piece is a scout
				{
					Location tempLoc = new Location(compLocToMoveTo.get_X(),(compLocToMoveTo.get_Y()+ 1));
					if((!gameInt.getBoard().Has_Piece(tempLoc)) || (gameInt.getBoard().getPiece(tempLoc).get_isHuman())) 
					{
						
						compLocToMoveTo = tempLoc;
					}
				}	
				
				
			}
			else if(availLocs[2] != null)
			{
				compLocToMoveTo = availLocs[2]; // move to right
				
				if(compLocToMoveTo.get_X()+1 < 10) 
				{
					Location tempLoc = new Location((compLocToMoveTo.get_X()+ 1),compLocToMoveTo.get_Y());
					if((!gameInt.getBoard().Has_Piece(tempLoc)) || (gameInt.getBoard().getPiece(tempLoc).get_isHuman())) 
					{
						
						compLocToMoveTo = tempLoc;
					}
					
				}
				
			}
			else if(availLocs[3] != null)
			{
				compLocToMoveTo = availLocs[3]; // move to the left
				
				if(compLocToMoveTo.get_X()-1 > 0) 
				{
					Location tempLoc = new Location((compLocToMoveTo.get_X()-1),compLocToMoveTo.get_Y());
					if((!gameInt.getBoard().Has_Piece(tempLoc)) || (gameInt.getBoard().getPiece(tempLoc).get_isHuman())) 
					{
						
						compLocToMoveTo = tempLoc;
					}
					
				}
				
			}
			else if(availLocs[1] != null)	
			{
				compLocToMoveTo = availLocs[1]; // move up
				
				if(compLocToMoveTo.get_Y()-1 > 0) 
				{
					Location tempLoc = new Location(compLocToMoveTo.get_X(),(compLocToMoveTo.get_Y()- 1));
					if((!gameInt.getBoard().Has_Piece(tempLoc)) || (gameInt.getBoard().getPiece(tempLoc).get_isHuman())) 
					{
						
						compLocToMoveTo = tempLoc;
					}
					
				}
				
			}
			
		}
		
		// now we need to decide how far to move is it a scout or a normal piece
		
		return gameInt.Move(compLocToMoveFrom, compLocToMoveTo); // the only problem with the current set up is if a scout
														  // can move two spaces it will
			
		
		
		
		// NOTE: are we able to use move with the computer? will the built in checks make it fail?
		
		*/
		
		
		
		return true;
	}
	
	public Location[] movesAvail()// we need to check to see if there are any places around the piece that can be moved to
	{							// it might also save us some trouble to store the available moves
		Location[] availMoves = new Location[4];
		Location tempLoc;
		Piece tempPiece;
		for(int i = 0; i < 4; i++)
		{
			if( i == 0) // check the piece at loc y + 1
			{
				
				if(compLocToMoveFrom.get_Y()+1 < 10) // if the location we're checking isn't out of bounds
				{
					tempLoc = new Location(compLocToMoveFrom.get_X(),(compLocToMoveFrom.get_Y()+1));
					if((!gameInt.getBoard().Has_Piece(tempLoc)) || (gameInt.getBoard().getPiece(tempLoc).get_isHuman())) 
					{ // (above) if there is a piece in the location is it Human? if not, can't move there...
						availMoves[i] = tempLoc;
					}
				}
			}
			if( i == 1) // check the piece at loc y - 1
			{
				if(compLocToMoveFrom.get_Y()-1 >= 0)
				{
					tempLoc = new Location(compLocToMoveFrom.get_X(),(compLocToMoveFrom.get_Y()-1));
					if((!gameInt.getBoard().Has_Piece(tempLoc)) || (gameInt.getBoard().getPiece(tempLoc).get_isHuman())) 
					{
						availMoves[i] = tempLoc;
					}
				}
			}
			if( i == 2) //check the piece at loc x + 1
			{
				if(compLocToMoveFrom.get_X()+1   < 10)
				{
					tempLoc = new Location((compLocToMoveFrom.get_X()+1),compLocToMoveFrom.get_Y());
					if((!gameInt.getBoard().Has_Piece(tempLoc)) || (gameInt.getBoard().getPiece(tempLoc).get_isHuman()))
					{
						availMoves[i] = tempLoc;
					}
				}
			}
			if( i == 3) // check the piece at loc x - 1
			{
				if(compLocToMoveFrom.get_X()-1 >= 0)
				{
					tempLoc = new Location((compLocToMoveFrom.get_X()-1),compLocToMoveFrom.get_Y());
					if((!gameInt.getBoard().Has_Piece(tempLoc)) || (gameInt.getBoard().getPiece(tempLoc).get_isHuman())) 
					{
						availMoves[i] = tempLoc;
					}
				}
			}
		}
		return availMoves;
	}
	
	public boolean isMoveAvail()
	{
		Location[] availableMoves = this.movesAvail();
		for(int i = 0; i < availableMoves.length; i++)
		{
			if(availableMoves[i] != null) // if at least one space in available moves isn't null
			{
				return true;
			}
		}
		return false;
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
				if((!graveYard.get(l).get_isHuman()) && (graveYard.get(l).get_MoveType() > 0)) // if the piece is not a human 
				{																			// piece and if its a moveable piece
					numOfRemovedMoveable ++;
				}
			}
			
		}
		
		if(numOfRemovedMoveable == 33) // if the number of removed moveable pieces equals the number of moveable pieces
		{								// each player gets then the player doesn't have any more moveable pieces they lose
			gameInt.setWinner(1);		// the computer loses let the GameInstance know the player lost
			return false;
		}
		else
		{
			return true;
		}
	}
	
}

