package edu.ycp.cs320.Stratego;

import java.util.ArrayList;
import java.util.Random;

public class GameInstance 
{
	private Piece[] PieceBank; 		// holds the pieces the player
    private Piece[] compBank;		//holds the pieces of the computer

	private Location selected;		// will place on their side --selected is physical location of the piece in the bank
	
	private ArrayList<Piece> graveyard = new ArrayList<Piece>();	//holds the pieces removed from the game
	private ArrayList<Location> compPieces = new ArrayList<Location>();
	
	private Piece one;
	private Piece two;
	
	private Board gameBoard;
	private int winner;
	// need to figure out how the PieceBank is going to work, currently it is an array
	// however we need it to work more like a board in order to move pieces around.
	// this would also mean changing the PieceBank check method in order to check the new
	// pieceBank/Graveyard board rather then the PieceBank Array
	
	private boolean ValidMove;
    boolean humanPlayerTurn;
	
	private Piece pieceToBeAttacked;
	private Location locToMoveTo;
	
	private Piece pieceToMove;
	private Location locToMoveFrom;
	
	private Player human;
	private Player computer;
	
	private int placedPieces;		//counts how many pieces you have placed on the board
									//flags end of set up
	public GameInstance()
	{
		winner = 0;
		gameBoard = new Board();
		human = new HumanPlayer(this);
		computer = new ComputerPlayer(this);
		placedPieces = 0;
		
		
		PieceBank = new Piece[40];	//sets up all the
                compBank = new Piece[40];
		for(int i = 0; i < 40; i++)	//pieces for the player
		{							//to place
			Piece temp = new Piece();
			temp.set_human(true);
			
			if(i < 6)							// indexes are inclusive
			{									// 0-5 (indexes in array)
				temp.set_Value(Pieces.BOMB); 	// BOMB
				PieceBank[i] = temp;			// 6 (of them)
			}
			else if(i == 6)
			{									// 6
				temp.set_Value(Pieces.TEN);		// Marshall
				PieceBank[i] = temp;			// 1
			}
			else if(i ==7)
			{									// 7 
				temp.set_Value(Pieces.NINE);	// General
				PieceBank[i] = temp;			// 1
			}
			else if(i > 7 && i <= 9)
			{									// 8 - 9
				temp.set_Value(Pieces.EIGHT);	// Colonel
				PieceBank[i] = temp;			// 2
			}
			else if(i > 9 && i <= 12)
			{									// 10-12
				temp.set_Value(Pieces.SEVEN);	// Major
				PieceBank[i] = temp;			// 3
			}
			else if(i > 12 && i <= 16)
			{									// 13-16
				temp.set_Value(Pieces.SIX);		// Captain
				PieceBank[i] = temp;			// 4
			}
			else if(i > 16 && i <= 20)
			{									// 17-20
				temp.set_Value(Pieces.FIVE);	// Lieutenant
				PieceBank[i] = temp;			// 4
			}
			else if(i >= 21 && i <= 24)
			{									// 21-24
				temp.set_Value(Pieces.FOUR);	// Sergeant
				PieceBank[i] = temp;			// 4
			}
			else if(i >= 25 && i <= 29)
			{									// 25-29
				temp.set_Value(Pieces.THREE);	// Miner
				PieceBank[i] = temp;			// 5
			}
			else if(i >= 30 && i <= 37)
			{									// 30-37
				temp.set_Value(Pieces.TWO);		// Scout
				PieceBank[i] = temp;			// 8
			}
			else if(i == 38)
			{									// 38
				temp.set_Value(Pieces.SPY);		// Spy
				PieceBank[i] = temp;			// 1
			}
			else if(i == 39)
			{									// 39
				temp.set_Value(Pieces.FLAG);	// Flag
				PieceBank[i] = temp;			// 1
			}
			// it ended at 39, perfect because the array
			// starts at 0 - 0-39 = 40 
				
				
		}

        for(int i = 0; i < 40; i++)	//pieces for the computer
		{							//to place
			Piece temp = new Piece();
			temp.set_human(false);
			

			if(i < 6)							// indexes are inclusive
			{									// 0-5 (indexes in array)
				temp.set_Value(Pieces.BOMB); 	// BOMB
				compBank[i] = temp;			// 6 (of them)
			}
			else if(i == 6)
			{									// 6
				temp.set_Value(Pieces.FLAG);		// FLAG
				compBank[i] = temp;			// 1
                        }
			else if(i ==7)
			{									// 7
				temp.set_Value(Pieces.SPY);	// Spy
				compBank[i] = temp;			// 1
			}
			else if(i == 8)
			{									// 8 - 9
				temp.set_Value(Pieces.TEN);	// Marshal
				compBank[i] = temp;			// 1
			}
			else if(i == 9)
			{									// 10-12
				temp.set_Value(Pieces.NINE);	// General
				compBank[i] = temp;			// 1
			}
			else if(i >= 10 && i <= 11)
			{									// 13-16
				temp.set_Value(Pieces.EIGHT);		// Colonel
				compBank[i] = temp;			// 2
			}
			else if(i >= 12 && i <= 14)
			{									// 17-20
				temp.set_Value(Pieces.SEVEN);	// Major
				compBank[i] = temp;			// 3
			}
			else if(i >= 15 && i <= 18)
			{									// 21-24
				temp.set_Value(Pieces.SIX);	// Captain
				compBank[i] = temp;			// 4
			}
			else if(i >= 19 && i <= 22)
			{									// 25-29
				temp.set_Value(Pieces.FIVE);	// Lieutenant
				compBank[i] = temp;			// 4
			}
			else if(i >= 23 && i <= 26)
			{									// 30-37
				temp.set_Value(Pieces.FOUR);		// Sergeant
				compBank[i] = temp;			// 4
			}
			else if(i >= 27 && i <= 31)
			{									// 38
				temp.set_Value(Pieces.THREE);		// Miner
				compBank[i] = temp;			// 5
			}
			else if(i >= 32 && i <= 39)
			{									// 39
				temp.set_Value(Pieces.TWO);	// Scout
				compBank[i] = temp;			// 8
			}
			// it ended at 39, perfect because the array
			// starts at 0 - 0-39 = 40
		}

	}		// This is the end of the GameInstance Constructor

	public boolean setUp()		//return true when setup can be complete
	{
		if(placedPieces==40)
                {
                    Random r = new Random();
                    humanPlayerTurn = true;
                    return true;
                }
		else
                {
                    return false;
                }
	}
	
	public void setPieceBankLocation(Location in)	//sets location of piece to be placed from the piece selection grid
	{
		selected = in;
	}

        public Location getPieceBankLocation()
        {
            return selected;
        }
	
	public void placePiece(Location in)		//places selected piece on the board
	{
		if(canPlacePiece(in))
		{
			PieceBank[selected.get_X()+(selected.get_Y()*4)].set_isOnBoard(true);
			gameBoard.getSquare(in).set_Piece(PieceBank[selected.get_X()+(selected.get_Y()*4)]);
			placedPieces++;
                        selected = null;
		}

                return;
	}

    public boolean canPlacePiece(Location in)		//places selected piece on the board
	{
		if(!(in.get_Y()>5) || gameBoard.Has_Piece(in))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public void removePiece(Location in)		//returns a piece to the selection grid
	{
                int gy = 0;
                if(canRemovePiece(in))
		{
			for(int i=0;i<40;i++)
			{
				if(gameBoard.getPiece(in).equals(PieceBank[i]))
				{
					PieceBank[i].set_isOnBoard(false);
                                        gy = i;
				}
			}
			gameBoard.getSquare(in).set_Piece(false, Pieces.NO_PIECE);
			placedPieces--;
			selected = (new Location(gy/4,gy%4));
		}
                return;
	}
	
	public boolean canRemovePiece(Location in)
        {
            if(!gameBoard.getSquare(in).hasPiece() || !gameBoard.getSquare(in).get_Piece().get_isHuman())
            {
		System.out.println("FALSE");
                return false;
            }
            else
            {
                return true;
            }
        }
	
	public void computerSetup()
        {
            Random r = new Random();
            int spot;
            boolean placed;
            
            for(int j=0;j<10;j++)
            {
                for(int i=0;i<4;i++)
                {
                    //gameBoard.getSquare(new Location(j,i)).set_Piece(compBank[i+(j*4)]);

                    placed = false;

                    if(compBank[i+(j*4)].get_Value().equals(Pieces.FLAG) || compBank[i+(j*4)].get_Value().equals(Pieces.BOMB) || compBank[i+(j*4)].get_Value().equals(Pieces.SPY))
                    {
                        do{
                            spot = r.nextInt(4);
                            if(spot==0)
                            {
                                Location loc = new Location (r.nextInt(2)+2,3);
                                
                                if(!gameBoard.Has_Piece(loc))
                                {
                                	compPieces.add(loc);	// when a piece is placed for the comp, record the loc
                                    gameBoard.getSquare(loc).set_Piece(compBank[i+(j*4)]);
                                    placed = true;
                                }
                            }
                            else if(spot==1)
                            {
                                Location loc = new Location (r.nextInt(2)+6,3);
                                if(!gameBoard.Has_Piece(loc))
                                {
                                	compPieces.add(loc);
                                    gameBoard.getSquare(loc).set_Piece(compBank[i+(j*4)]);
                                    placed = true;
                                }
                            }
                            else if(spot==2)
                            {
                                Location loc = new Location (r.nextInt(3)+7,r.nextInt(2));
                                if(!gameBoard.Has_Piece(loc))
                                {
                                	compPieces.add(loc);
                                    gameBoard.getSquare(loc).set_Piece(compBank[i+(j*4)]);
                                    placed = true;
                                }
                            }
                            else
                            {
                                Location loc = new Location (r.nextInt(4),r.nextInt(2));
                                if(!gameBoard.Has_Piece(loc))
                                {
                                	compPieces.add(loc);
                                    gameBoard.getSquare(loc).set_Piece(compBank[i+(j*4)]);
                                    placed = true;
                                }
                            }

                            if(placed == true)
                            {
                                System.out.println("FLAG, BOMB, SPY");
                            }

                        }while(!placed);
                    }
                    if(compBank[i+(j*4)].get_Value().equals(Pieces.TEN) || compBank[i+(j*4)].get_Value().equals(Pieces.NINE) || compBank[i+(j*4)].get_Value().equals(Pieces.EIGHT))
                    {
                        do{
                            spot = r.nextInt(2);
                            if(spot==0)
                            {
                                Location loc = new Location (r.nextInt(10),1);
                                if(!gameBoard.Has_Piece(loc))
                                {
                                	compPieces.add(loc);
                                    gameBoard.getSquare(loc).set_Piece(compBank[i+(j*4)]);
                                    placed = true;
                                }
                            }
                            else
                            {
                                Location loc = new Location (r.nextInt(10),2);
                                if(!gameBoard.Has_Piece(loc))
                                {
                                	compPieces.add(loc);
                                    gameBoard.getSquare(loc).set_Piece(compBank[i+(j*4)]);
                                    placed = true;
                                }
                            }

                            if(placed == true)
                            {
                                System.out.println("TEN, NINE, EIGHT");
                            }

                        }while(!placed);
                    }
                    if(compBank[i+(j*4)].get_Value().equals(Pieces.SEVEN) || compBank[i+(j*4)].get_Value().equals(Pieces.SIX) || compBank[i+(j*4)].get_Value().equals(Pieces.FIVE))
                    {
                        do{
                            spot = r.nextInt(2);
                            if(spot==0)
                            {
                                Location loc = new Location (r.nextInt(10),r.nextInt(2)+2);
                                if(!gameBoard.Has_Piece(loc))
                                {
                                	compPieces.add(loc);
                                    gameBoard.getSquare(loc).set_Piece(compBank[i+(j*4)]);
                                    placed = true;
                                }
                            }
                            else
                            {
                                Location loc = new Location (r.nextInt(4)+3,r.nextInt(3));
                                if(!gameBoard.Has_Piece(loc))
                                {
                                	compPieces.add(loc);
                                    gameBoard.getSquare(loc).set_Piece(compBank[i+(j*4)]);
                                    placed = true;
                                }
                            }

                            if(placed == true)
                            {
                                System.out.println("SEVEN, SIX, FIVE");
                            }

                        }while(!placed);
                    }
                    if(compBank[i+(j*4)].get_Value().equals(Pieces.FOUR) || compBank[i+(j*4)].get_Value().equals(Pieces.TWO))
                    {
                        do{
                            Location loc = new Location (r.nextInt(10),r.nextInt(4));
                            if(!gameBoard.Has_Piece(loc))
                            {
                            	compPieces.add(loc);
                                gameBoard.getSquare(loc).set_Piece(compBank[i+(j*4)]);
                                placed = true;
                            }

                            if(placed == true)
                            {
                                System.out.println("FOUR, TWO");
                            }

                        }while(!placed);
                    }
                    if(compBank[i+(j*4)].get_Value().equals(Pieces.THREE))
                    {
                        do{
                            Location loc = new Location (r.nextInt(10),r.nextInt(3));
                            if(!gameBoard.Has_Piece(loc))
                            {
                            	compPieces.add(loc);
                                gameBoard.getSquare(loc).set_Piece(compBank[i+(j*4)]);
                                placed = true;
                            }

                            if(placed == true)
                            {
                                System.out.println("THREE");
                            }

                        }while(!placed);
                    }
                }
            }

            System.out.println("DONE");
        }
	
	
	/**
	 * This method is called to choose a piece to move.
	 * 
	 * @param pieceToMove the piece to move
	 */
	
	public void setLocToMoveFrom(Location in)					// Both of the sets must be called
	{															// Before any comparisons can be done
		this.locToMoveFrom = in;						
		pieceToMove =  gameBoard.getPiece(in);// this.locToMoveFrom.get_Piece();			// note this method will also get the piece in this location
	}															// automatically 
	
	public void setLocToMoveTo(Location in)
	{
		this.locToMoveTo = in;
		pieceToBeAttacked = gameBoard.getPiece(in);// this.locToMoveTo.get_Piece();

	}
	
	public boolean pickedValidMove() 
	{
		
		if(pieceToMove.get_isHuman() && humanPlayerTurn)			// is the piece yours and it's your turn
		{
			if(!pieceToMove.get_Value().equals(Pieces.NO_PIECE))
			{
				if(pieceToMove.get_MoveType() != 0)			// is the piece moveable
				{
					Square sqToMoveTo = gameBoard.getSquare(locToMoveTo);
					if(!sqToMoveTo.isUnpassable())			// are you trying to run face first into a cliff
					{
						if(Math.abs(locToMoveFrom.get_X() - locToMoveTo.get_X()) <= pieceToMove.get_MoveType())   //Is the piece your moving able to move that far? 
						{
							if(Math.abs(locToMoveFrom.get_Y() - locToMoveTo.get_Y()) <= pieceToMove.get_MoveType())	
							{
								if(sqToMoveTo.hasPiece() && !sqToMoveTo.get_Piece().get_isHuman()) // the square your moving to has a piece and its not your own
								{
									return true;
								}
								else if(!sqToMoveTo.hasPiece() || sqToMoveTo.get_Piece() == null) // what if it doesn't have a piece there
								{
									return true;
								}
								
							}
						}
					}
				}
			}
		}
		return false;

	}
	
	public static void wait(int n)
	{
        
        long t0, t1;

        t0 =  System.currentTimeMillis();

        do
        {
            t1 = System.currentTimeMillis();
        }
        while ((t1 - t0) < (n * 1000));
    }
	
	public boolean pickedValidCompMove() 
	{
		
		if(!pieceToMove.get_isHuman() && !humanPlayerTurn)			// is the piece yours and it's your turn
		{
			if(!pieceToMove.get_Value().equals(Pieces.NO_PIECE))
			{
				if(pieceToMove.get_MoveType() != 0)			// is the piece moveable
				{
					Square sqToMoveTo = gameBoard.getSquare(locToMoveTo);
					if(!sqToMoveTo.isUnpassable())			// are you trying to run face first into a cliff
					{
						if(Math.abs(locToMoveFrom.get_X() - locToMoveTo.get_X()) <= pieceToMove.get_MoveType())   //Is the piece your moving able to move that far? 
						{
							if(Math.abs(locToMoveFrom.get_Y() - locToMoveTo.get_Y()) <= pieceToMove.get_MoveType())	
							{
								
								if(sqToMoveTo.hasPiece() && sqToMoveTo.get_Piece().get_isHuman())
								{
									return true;
								}
								else if(!sqToMoveTo.hasPiece() || (sqToMoveTo.hasPiece() && gameBoard.getSquare(locToMoveTo).get_Piece().get_Value() == Pieces.NO_PIECE))
								{
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;

	}
	
	public boolean checkPieceBank() // checks pieceBank to see if there are any pieces left in PieceBank
	{
		for(int i = 0; i < PieceBank.length; i++)
		{
			if(!(PieceBank[i].get_Value().equals(Pieces.NO_PIECE)))
			{
				return false;
			}
		}
		return true;
	}
		
	public void Battle(Square toSquare, Square fromSquare)
	{		
		
		
		one = fromSquare.get_Piece();
		two = toSquare.get_Piece();
		
		
		one.set_hasBeenSeen(true);
		two.set_hasBeenSeen(true);
							
		
			
		
		
		if(one.get_Value()==Pieces.THREE && two.get_Value()==Pieces.BOMB)
		{
			two.set_hasBeenRemoved(true);
		}
		else if(two.get_Value() == Pieces.FLAG)
		{
			if(one.get_isHuman())		// the player got the computers flag
			{
				winner = 1;				// player wins
			}
			else  						// the computer got the players flag
			{
				winner = 2;				// computer wins
			}
		}
		else if(one.get_Value()==Pieces.SPY && two.get_Value()==Pieces.TEN)
		{
			two.set_hasBeenRemoved(true);
		}
		else if(one.get_Value().compareTo(two.get_Value())==0)
		{
			one.set_hasBeenRemoved(true);
			two.set_hasBeenRemoved(true);
		}
		else if(one.get_Value().compareTo(two.get_Value())>0)
		{
			two.set_hasBeenRemoved(true);
		}
		else
		{
			one.set_hasBeenRemoved(true);
		}
		
		if(two.get_Value().equals(Pieces.BOMB))
		{
			two.set_hasBeenRemoved(true);
		}
		
		if(one.get_hasBeenRemoved())	//removes piece one from the board
		{
			graveyard.add(one);
			fromSquare.set_Piece(new Piece(false, Pieces.NO_PIECE));
			
			
		}
		if(two.get_hasBeenRemoved())	//removes piece two from the board
		{
			graveyard.add(two);
			toSquare.set_Piece(new Piece(false, Pieces.NO_PIECE));
		}
			
			
		if(!one.get_hasBeenRemoved())	//if piece one has not been removed it was victorious
		{								//move piece one to conquered square
			toSquare.set_Piece(one);
			fromSquare.set_Piece(new Piece(false, Pieces.NO_PIECE));
			
			
		}
		
		
		// we need to do a check to see if one of the pieces was the flag
		// we also need to do a check to see if each player still has pieces that are moveable **
		// 33 of each players 40 pieces are movable the rest are either bombs or the flag
		// adding a check movable pieces to each Player class...
		
		
		
		
		return;
	}
	
	public boolean Move(Location from, Location to) //returns true if moved, false if not
	{		
		Square fromSquare = gameBoard.getSquare(from);
		Square toSquare = gameBoard.getSquare(to);
		
		setLocToMoveTo(to);
		setLocToMoveFrom(from);
		
		if(!pickedValidMove())
		{	
			return false;
		}
		
		else
		{
			Battle(toSquare,fromSquare);
			return true;
		}
	}
	
	public boolean compMove(Location from, Location to) //returns true if moved, false if not
	{		
		Square fromSquare = gameBoard.getSquare(from);
		Square toSquare = gameBoard.getSquare(to);
		
		setLocToMoveTo(to);
		setLocToMoveFrom(from);
		
		if(!pickedValidCompMove())
		{	
			return false;
		}
		
		else
		{
			Battle(toSquare,fromSquare);
			return true;
		}
	}
	
	public Board getBoard()
	{
		return gameBoard;
	}
	
	public Piece[] getPieceBank()
	{
		return PieceBank;
	}
	
	public ArrayList<Piece> getGraveYard()
	{
		return graveyard;
	}
	
	public Piece getPieceToMove()
	{
		return pieceToMove;
	}
	
	public Piece getPieceToBeAttacked()
	{
		return pieceToBeAttacked;
	}
	
	public Location getLocToMoveFrom()
	{
		return locToMoveFrom;
	}
	
	public Location getLocToMoveTo()
	{
		return locToMoveTo;
	}
	
	public void setWinner(int oneIsPlayer) // 1 = Player won   2 = Computer won
	{
		winner = oneIsPlayer;
	}
	
	public boolean isGameDone()
	{
		return ((!computer.hasMoveablePieces()) || (!human.hasMoveablePieces()) || winner != 0 );
	}
	public Player getHuman()
	{
		return human;
	}
	
	public Player getComputer()
	{
		return computer;
	}
	
	public ArrayList<Location> getComputerPieces()
	{
		return compPieces;
	}
	
	public boolean getHumanTurn()
	{
		return humanPlayerTurn;
	}
	
	public void setHumanTurn(boolean turn)
	{
		humanPlayerTurn = turn;
	}
	
	public int getWinner()
	{
		return winner;
	}
	
	public Piece getOne()
	{
		return one;
	}
	
	public Piece getTwo()
	{
		return two;
	}

}
