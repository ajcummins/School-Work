/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SetupView. java
 *
 * Created on Apr 11, 2011, 9:17:07 AM
 */

package edu.ycp.cs320.Stratego;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

/**
 *
 * @author acummins
 */
public class SetupView extends javax.swing.JFrame implements Observer {

    private JButton[][] boardButtons;                   // these are all the buttons for the board
    private ActionListener boardButtonActionListener;   // and this is the action handler that
                                                        // calls the code when one of the board buttons
                                                        // are clicked
    private JButton[][] gyButtons;
    private ActionListener gyButtonActionListener;

    private Location lastHitLocation;
    private Location lastGyHitLocation;
    private Location lastBoardHitLocation;
    private boolean setupCompleted;
    private boolean pieceIsSelected;
    
    
    
    private String[] arguements;
    
    private boolean isMoveFromLoc; // this variable is used to tell if the button you clicked is reffering to
    							   // the Location your moving from or the location your moving to.

    private GameInstance gameInt;


    /** Creates new form SetupView */
    public SetupView(GameInstance inGameInt) {
        boardButtonActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBoardButtonPressed(e);
            }
        };

        gameInt = inGameInt;
        setupCompleted = false;
        pieceIsSelected = false;
        isMoveFromLoc = true;
        gameInt.getBoard().addObserver(this);
//        for(int i=0;i<10;i++)
//        {
//        	for(int j=0;j<10;j++)
//        	{
//        		gameInt.getBoard().getSquare(new Location(i,j)).addObserver(this);
//        	}
//        }
        
        gyButtonActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGYButtonPressed(e);
            }
        };

        initComponents();


        boardButtons = new JButton[][] {
            { Board_0_0, Board_0_1, Board_0_2, Board_0_3, Board_0_4, Board_0_5, Board_0_6, Board_0_7, Board_0_8, Board_0_9 },
            { Board_1_0, Board_1_1, Board_1_2, Board_1_3, Board_1_4, Board_1_5, Board_1_6, Board_1_7, Board_1_8, Board_1_9 },
            { Board_2_0, Board_2_1, Board_2_2, Board_2_3, Board_2_4, Board_2_5, Board_2_6, Board_2_7, Board_2_8, Board_2_9 },
            { Board_3_0, Board_3_1, Board_3_2, Board_3_3, Board_3_4, Board_3_5, Board_3_6, Board_3_7, Board_3_8, Board_3_9 },
            { Board_4_0, Board_4_1, Board_4_2, Board_4_3, Board_4_4, Board_4_5, Board_4_6, Board_4_7, Board_4_8, Board_4_9 },
            { Board_5_0, Board_5_1, Board_5_2, Board_5_3, Board_5_4, Board_5_5, Board_5_6, Board_5_7, Board_5_8, Board_5_9 },
            { Board_6_0, Board_6_1, Board_6_2, Board_6_3, Board_6_4, Board_6_5, Board_6_6, Board_6_7, Board_6_8, Board_6_9 },
            { Board_7_0, Board_7_1, Board_7_2, Board_7_3, Board_7_4, Board_7_5, Board_7_6, Board_7_7, Board_7_8, Board_7_9 },
            { Board_8_0, Board_8_1, Board_8_2, Board_8_3, Board_8_4, Board_8_5, Board_8_6, Board_8_7, Board_8_8, Board_8_9 },
            { Board_9_0, Board_9_1, Board_9_2, Board_9_3, Board_9_4, Board_9_5, Board_9_6, Board_9_7, Board_9_8, Board_9_9 },
        };

        gyButtons = new JButton[][] {
            { GY_0_0, GY_0_1, GY_0_2, GY_0_3 },
            { GY_1_0, GY_1_1, GY_1_2, GY_1_3 },
            { GY_2_0, GY_2_1, GY_2_2, GY_2_3 },
            { GY_3_0, GY_3_1, GY_3_2, GY_3_3 },
            { GY_4_0, GY_4_1, GY_4_2, GY_4_3 },
            { GY_5_0, GY_5_1, GY_5_2, GY_5_3 },
            { GY_6_0, GY_6_1, GY_6_2, GY_6_3 },
            { GY_7_0, GY_7_1, GY_7_2, GY_7_3 },
            { GY_8_0, GY_8_1, GY_8_2, GY_8_3 },
            { GY_9_0, GY_9_1, GY_9_2, GY_9_3 },
        };
        
        // force initial update of UI
        update(gameInt.getBoard(), null);
    }
    
    // This method is called by the Board and its Square object
    // whenever the state of the board changes
    @Override
    public void update(Observable o, Object arg) {
    	// Update state of the GUI
    	
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                 if(gameInt.getBoard().getPiece(new Location(i,j)).get_isHuman())
                 {
                 	boardButtons[j][i].setBackground(Color.cyan);
                 }
                 else if(!gameInt.getBoard().getPiece(new Location(i,j)).get_Value().equals(Pieces.NO_PIECE))
                 {
                 	boardButtons[j][i].setBackground(Color.orange);
                 }
                 else
                 {
                	 boardButtons[j][i].setBackground(null);
                 }
            	
            	if(gameInt.getBoard().getPiece(new Location(i,j)).get_Value().equals(Pieces.TWO))
                {
                    boardButtons[j][i].setText("2");
                }
                else if(gameInt.getBoard().getPiece(new Location(i,j)).get_Value().equals(Pieces.THREE))
                {
                    boardButtons[j][i].setText("3");
                }
                else if(gameInt.getBoard().getPiece(new Location(i,j)).get_Value().equals(Pieces.FOUR))
                {
                    boardButtons[j][i].setText("4");
                }
                else if(gameInt.getBoard().getPiece(new Location(i,j)).get_Value().equals(Pieces.FIVE))
                {
                    boardButtons[j][i].setText("5");
                }
                else if(gameInt.getBoard().getPiece(new Location(i,j)).get_Value().equals(Pieces.SIX))
                {
                    boardButtons[j][i].setText("6");
                }
                else if(gameInt.getBoard().getPiece(new Location(i,j)).get_Value().equals(Pieces.SEVEN))
                {
                    boardButtons[j][i].setText("7");
                }
                else if(gameInt.getBoard().getPiece(new Location(i,j)).get_Value().equals(Pieces.EIGHT))
                {
                    boardButtons[j][i].setText("8");
                }
                else if(gameInt.getBoard().getPiece(new Location(i,j)).get_Value().equals(Pieces.NINE))
                {
                    boardButtons[j][i].setText("9");
                }
                else if(gameInt.getBoard().getPiece(new Location(i,j)).get_Value().equals(Pieces.TEN))
                {
                    boardButtons[j][i].setText("10");
                }
                else if(gameInt.getBoard().getPiece(new Location(i,j)).get_Value().equals(Pieces.BOMB))
                {
                    boardButtons[j][i].setText("B");
                }
                else if(gameInt.getBoard().getPiece(new Location(i,j)).get_Value().equals(Pieces.SPY))
                {
                    boardButtons[j][i].setText("S");
                }
                else if(gameInt.getBoard().getPiece(new Location(i,j)).get_Value().equals(Pieces.FLAG))
                {
                    boardButtons[j][i].setText("F");
                }
                else if(gameInt.getBoard().getSquare(new Location(i,j)).isUnpassable())
                {
                    boardButtons[j][i].setText("X");
                    boardButtons[j][i].setBackground(Color.red);
                }
                else
                {
                    boardButtons[j][i].setText("-");
                }

                if(!gameInt.getBoard().getPiece(new Location(i,j)).get_isHuman() && !gameInt.getBoard().getSquare(new Location(i,j)).isUnpassable() && !gameInt.getBoard().getPiece(new Location(i,j)).get_Value().equals(Pieces.NO_PIECE))
                {
                	if(gameInt.getBoard().getPiece(new Location(i,j)).get_hasBeenSeen())
                	{
                		boardButtons[j][i].setText(gameInt.getBoard().getPiece(new Location(i,j)).get_Value().toString());
                	}
                	else
                	{
                		boardButtons[j][i].setText("O");
                	}
                    
                }
            }
        }

        for(int i=0;i<10;i++)
        {
            for(int j=0;j<4;j++)
            {
                if(gameInt.getPieceBank()[j+i*4].get_Value().equals(Pieces.TWO))
                {
                    gyButtons[i][j].setText("2");
                }
                else if(gameInt.getPieceBank()[j+i*4].get_Value().equals(Pieces.THREE))
                {
                    gyButtons[i][j].setText("3");
                }
                else if(gameInt.getPieceBank()[j+i*4].get_Value().equals(Pieces.FOUR))
                {
                    gyButtons[i][j].setText("4");
                }
                else if(gameInt.getPieceBank()[j+i*4].get_Value().equals(Pieces.FIVE))
                {
                    gyButtons[i][j].setText("5");
                }
                else if(gameInt.getPieceBank()[j+i*4].get_Value().equals(Pieces.SIX))
                {
                    gyButtons[i][j].setText("6");
                }
                else if(gameInt.getPieceBank()[j+i*4].get_Value().equals(Pieces.SEVEN))
                {
                    gyButtons[i][j].setText("7");
                }
                else if(gameInt.getPieceBank()[j+i*4].get_Value().equals(Pieces.EIGHT))
                {
                    gyButtons[i][j].setText("8");
                }
                else if(gameInt.getPieceBank()[j+i*4].get_Value().equals(Pieces.NINE))
                {
                    gyButtons[i][j].setText("9");
                }
                else if(gameInt.getPieceBank()[j+i*4].get_Value().equals(Pieces.TEN))
                {
                    gyButtons[i][j].setText("10");
                }
                else if(gameInt.getPieceBank()[j+i*4].get_Value().equals(Pieces.BOMB))
                {
                    gyButtons[i][j].setText("B");
                }
                else if(gameInt.getPieceBank()[j+i*4].get_Value().equals(Pieces.SPY))
                {
                    gyButtons[i][j].setText("S");
                }
                else if(gameInt.getPieceBank()[j+i*4].get_Value().equals(Pieces.FLAG))
                {
                    gyButtons[i][j].setText("F");
                }
                else
                {
                    gyButtons[i][j].setText("M");
                }
                if(gameInt.getPieceBank()[j+(i*4)].get_isOnBoard())
                {
                    gyButtons[i][j].setText("x");
                }
            }
        }
        
//        // updating to move removed pieces to the Grave yard
//        if(setupCompleted)
//        {	               
//	        int row = 0;
//	        int col = 0;
//	        
//	        for(int i = gameInt.getGraveYard().size()-1; i > 0; i--)		// working here....................
//	        {
//	        	if(row > 10)
//	        	{
//	        		row = 0;	// iterate down to the next column to update the buttons
//	        		col++;
//	        	}
//	        	Piece pieceHere = gameInt.getGraveYard().get(i);
//        		gyButtons[row][col].setText(pieceHere.get_Value().toString());
//        		row++;
//        		if(pieceHere.get_isHuman())
//        		{
//        			gyButtons[row][col].setBackground(Color.CYAN);
//        		}
//        		else if(!pieceHere.get_isHuman())
//        		{
//        			gyButtons[row][col].setBackground(Color.ORANGE);
//        		}
//	        }
//	        
//        }
       
    }
    
   

    private Location findLocationForBoardButton(JButton b) {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                if (boardButtons[y][x] == b) {
                    return new Location(x, y);
                }
            }
        }
        throw new IllegalArgumentException("unknown board button!");
    }

    private Location findLocationForGYButton(JButton b) {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 4; x++) {
                if (gyButtons[y][x] == b) {
                    return new Location(x, y);
                }
            }
        }
        throw new IllegalArgumentException("unknown graveyard button!");
    }

    public Location getLastLocationClicked()
    {
        return lastHitLocation;
    }

    public boolean getIsSetupDone()
    {
        return setupCompleted;
    }
    
    public void outputToUser(String in)
    {
    	userDisplayTextArea.append("" + in + "\n");
    }
    
    public GameInstance getGameInt()
    {
    	return gameInt;
    }
    
//    public void gameOver()
//    {
//    	GameOverView gameOver = new GameOverView(gameInt.getWinner(), this);
//    }

    

    protected void handleBoardButtonPressed(ActionEvent e) {
        Location loc = findLocationForBoardButton((JButton) e.getSource());
        
        // do something with location
        System.out.println("Board Location Returned is: " + loc);
        System.out.println("The piece located here is: " + gameInt.getBoard().getPiece(loc).get_Value());
        if(gameInt.getBoard().getSquare(loc).isUnpassable())
        {
            System.out.println("unpassable");
        }

        //Removes a piece from the board if double clicked during setup
        if(lastBoardHitLocation!=null && loc.compareLocation(lastBoardHitLocation) && gameInt.canRemovePiece(loc) &&!setupCompleted && !pieceIsSelected)
        {
            gameInt.removePiece(loc);
            gyButtons[gameInt.getPieceBankLocation().get_X()][gameInt.getPieceBankLocation().get_Y()].setBackground(null);
            System.out.println("REMOVE");
            gameInt.setPieceBankLocation(null);
            loc = null;
        }

        //Adds a piece to the board from the piece bank
        if(gameInt.getPieceBankLocation()!=null && !setupCompleted && gameInt.canPlacePiece(loc))
        {
            gyButtons[lastGyHitLocation.get_Y()][lastGyHitLocation.get_X()].setBackground(Color.LIGHT_GRAY);
            gameInt.placePiece(loc);
            pieceIsSelected = false;
        }
        
        //
        if(setupCompleted && lastBoardHitLocation!=null) 
        {
        	gameInt.setLocToMoveFrom(lastBoardHitLocation);
        	gameInt.setLocToMoveTo(loc);
        	if(gameInt.getHuman().makeMove(lastBoardHitLocation, loc))
        	{
//        		if(!gameInt.getTwo().get_Value().equals(Pieces.NO_PIECE))
//        		{
//        			GameOverView battleView = new GameOverView(0,this);
//        			
//        		}
        		
        		boardButtons[lastBoardHitLocation.get_Y()][lastBoardHitLocation.get_X()].setBackground(null);
        		if(!gameInt.isGameDone())
        		{
        			
        			gameInt.getComputer().makeMove();
        		}
        		if(gameInt.isGameDone())
    			{
        			
//        			GameOverView gameOver = new GameOverView(gameInt.getWinner(), this);
        			if(gameInt.getWinner() == 1)
        			{
        				userDisplayTextArea.append("YOU WON!!!!");
        			}
        			else if(gameInt.getWinner() == 2)
        			{
        				userDisplayTextArea.append("You Lost  T-T  ");
        			}
        			else
        			{
        				userDisplayTextArea.append("Something");
        			}
        			
        			gameInt.wait(5);
        			System.exit(1);
        					
        			
    			}
        	}
        	else
        	{
        		
        		boardButtons[lastBoardHitLocation.get_Y()][lastBoardHitLocation.get_X()].setBackground(Color.cyan);
        	}
        	
        	loc = null;
        }
        
        if(setupCompleted && lastBoardHitLocation==null && !gameInt.getBoard().getPiece(loc).get_isHuman())
        {
        	loc = null;
        }
        if(setupCompleted && lastBoardHitLocation==null && gameInt.getBoard().getPiece(loc).get_isHuman())
        {
        	boardButtons[loc.get_Y()][loc.get_X()].setBackground(Color.yellow);
        }
        
        lastHitLocation = loc;
        lastBoardHitLocation = loc;
        //userDisplayTextArea.append("Board button was pressed!\n");
        
       
        
        
        
        
    }

    protected void handleGYButtonPressed(ActionEvent e) {
        Location loc = findLocationForGYButton((JButton) e.getSource());

        // do something with location
        System.out.println("GY Location Returned is: " + loc);
        System.out.println("The piece located here is: " + gameInt.getPieceBank()[loc.get_X()+loc.get_Y()*4].get_Value());

        //Unhighlights a piece in the piece bank if another one is selected
        if(lastGyHitLocation!=null && !gameInt.getPieceBank()[lastGyHitLocation.get_X()+(lastGyHitLocation.get_Y()*4)].get_isOnBoard())
        {
            gyButtons[lastGyHitLocation.get_Y()][lastGyHitLocation.get_X()].setBackground(null);
            pieceIsSelected = false;
        }
        
        //Selects and highlights a piece in the piece bank
        if(!gameInt.getPieceBank()[loc.get_X()+(loc.get_Y()*4)].get_isOnBoard() && !setupCompleted)
        {
            gameInt.setPieceBankLocation(loc);
            gyButtons[loc.get_Y()][loc.get_X()].setBackground(Color.yellow);
            pieceIsSelected = true;
        }
        else
        {
            gameInt.setPieceBankLocation(null);
        }

        lastHitLocation = loc;
        lastGyHitLocation = loc;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Board_9_1 = new javax.swing.JButton();
        Board_9_1.addActionListener(boardButtonActionListener);
        Board_9_2 = new javax.swing.JButton();
        Board_9_2.addActionListener(boardButtonActionListener);
        Board_9_3 = new javax.swing.JButton();
        Board_9_3.addActionListener(boardButtonActionListener);
        Board_9_0 = new javax.swing.JButton();
        Board_9_0.addActionListener(boardButtonActionListener);
        Board_9_4 = new javax.swing.JButton();
        Board_9_4.addActionListener(boardButtonActionListener);
        Board_9_5 = new javax.swing.JButton();
        Board_9_5.addActionListener(boardButtonActionListener);
        Board_9_6 = new javax.swing.JButton();
        Board_9_6.addActionListener(boardButtonActionListener);
        Board_9_7 = new javax.swing.JButton();
        Board_9_7.addActionListener(boardButtonActionListener);
        Board_9_8 = new javax.swing.JButton();
        Board_9_8.addActionListener(boardButtonActionListener);
        Board_9_9 = new javax.swing.JButton();
        Board_9_9.addActionListener(boardButtonActionListener);
        Board_8_0 = new javax.swing.JButton();
        Board_8_0.addActionListener(boardButtonActionListener);
        Board_8_1 = new javax.swing.JButton();
        Board_8_1.addActionListener(boardButtonActionListener);
        Board_8_2 = new javax.swing.JButton();
        Board_8_2.addActionListener(boardButtonActionListener);
        Board_8_3 = new javax.swing.JButton();
        Board_8_3.addActionListener(boardButtonActionListener);
        Board_8_4 = new javax.swing.JButton();
        Board_8_4.addActionListener(boardButtonActionListener);
        Board_8_5 = new javax.swing.JButton();
        Board_8_5.addActionListener(boardButtonActionListener);
        Board_8_6 = new javax.swing.JButton();
        Board_8_6.addActionListener(boardButtonActionListener);
        Board_8_7 = new javax.swing.JButton();
        Board_8_7.addActionListener(boardButtonActionListener);
        Board_8_8 = new javax.swing.JButton();
        Board_8_8.addActionListener(boardButtonActionListener);
        Board_8_9 = new javax.swing.JButton();
        Board_8_9.addActionListener(boardButtonActionListener);
        Board_7_0 = new javax.swing.JButton();
        Board_7_0.addActionListener(boardButtonActionListener);
        Board_7_1 = new javax.swing.JButton();
        Board_7_1.addActionListener(boardButtonActionListener);
        Board_7_2 = new javax.swing.JButton();
        Board_7_2.addActionListener(boardButtonActionListener);
        Board_7_3 = new javax.swing.JButton();
        Board_7_3.addActionListener(boardButtonActionListener);
        Board_7_4 = new javax.swing.JButton();
        Board_7_4.addActionListener(boardButtonActionListener);
        Board_7_5 = new javax.swing.JButton();
        Board_7_5.addActionListener(boardButtonActionListener);
        Board_7_6 = new javax.swing.JButton();
        Board_7_6.addActionListener(boardButtonActionListener);
        Board_7_7 = new javax.swing.JButton();
        Board_7_7.addActionListener(boardButtonActionListener);
        Board_7_8 = new javax.swing.JButton();
        Board_7_8.addActionListener(boardButtonActionListener);
        Board_7_9 = new javax.swing.JButton();
        Board_7_9.addActionListener(boardButtonActionListener);
        Board_6_0 = new javax.swing.JButton();
        Board_6_0.addActionListener(boardButtonActionListener);
        Board_6_1 = new javax.swing.JButton();
        Board_6_1.addActionListener(boardButtonActionListener);
        Board_6_2 = new javax.swing.JButton();
        Board_6_2.addActionListener(boardButtonActionListener);
        Board_6_3 = new javax.swing.JButton();
        Board_6_3.addActionListener(boardButtonActionListener);
        Board_6_4 = new javax.swing.JButton();
        Board_6_4.addActionListener(boardButtonActionListener);
        Board_6_5 = new javax.swing.JButton();
        Board_6_5.addActionListener(boardButtonActionListener);
        Board_6_6 = new javax.swing.JButton();
        Board_6_6.addActionListener(boardButtonActionListener);
        Board_6_7 = new javax.swing.JButton();
        Board_6_7.addActionListener(boardButtonActionListener);
        Board_6_8 = new javax.swing.JButton();
        Board_6_8.addActionListener(boardButtonActionListener);
        Board_6_9 = new javax.swing.JButton();
        Board_6_9.addActionListener(boardButtonActionListener);
        Board_5_0 = new javax.swing.JButton();
        Board_5_0.addActionListener(boardButtonActionListener);
        Board_5_1 = new javax.swing.JButton();
        Board_5_1.addActionListener(boardButtonActionListener);
        Board_5_2 = new javax.swing.JButton();
        Board_5_2.addActionListener(boardButtonActionListener);
        Board_5_3 = new javax.swing.JButton();
        Board_5_3.addActionListener(boardButtonActionListener);
        Board_5_4 = new javax.swing.JButton();
        Board_5_4.addActionListener(boardButtonActionListener);
        Board_5_5 = new javax.swing.JButton();
        Board_5_5.addActionListener(boardButtonActionListener);
        Board_5_6 = new javax.swing.JButton();
        Board_5_6.addActionListener(boardButtonActionListener);
        Board_5_7 = new javax.swing.JButton();
        Board_5_7.addActionListener(boardButtonActionListener);
        Board_5_8 = new javax.swing.JButton();
        Board_5_8.addActionListener(boardButtonActionListener);
        Board_5_9 = new javax.swing.JButton();
        Board_5_9.addActionListener(boardButtonActionListener);
        Board_4_0 = new javax.swing.JButton();
        Board_4_0.addActionListener(boardButtonActionListener);
        Board_4_1 = new javax.swing.JButton();
        Board_4_1.addActionListener(boardButtonActionListener);
        Board_4_2 = new javax.swing.JButton();
        Board_4_2.addActionListener(boardButtonActionListener);
        Board_4_3 = new javax.swing.JButton();
        Board_4_3.addActionListener(boardButtonActionListener);
        Board_4_4 = new javax.swing.JButton();
        Board_4_4.addActionListener(boardButtonActionListener);
        Board_4_5 = new javax.swing.JButton();
        Board_4_5.addActionListener(boardButtonActionListener);
        Board_4_6 = new javax.swing.JButton();
        Board_4_6.addActionListener(boardButtonActionListener);
        Board_4_7 = new javax.swing.JButton();
        Board_4_7.addActionListener(boardButtonActionListener);
        Board_4_8 = new javax.swing.JButton();
        Board_4_8.addActionListener(boardButtonActionListener);
        Board_4_9 = new javax.swing.JButton();
        Board_4_9.addActionListener(boardButtonActionListener);
        Board_3_0 = new javax.swing.JButton();
        Board_3_0.addActionListener(boardButtonActionListener);
        Board_3_1 = new javax.swing.JButton();
        Board_3_1.addActionListener(boardButtonActionListener);
        Board_3_2 = new javax.swing.JButton();
        Board_3_2.addActionListener(boardButtonActionListener);
        Board_3_3 = new javax.swing.JButton();
        Board_3_3.addActionListener(boardButtonActionListener);
        Board_3_4 = new javax.swing.JButton();
        Board_3_4.addActionListener(boardButtonActionListener);
        Board_3_5 = new javax.swing.JButton();
        Board_3_5.addActionListener(boardButtonActionListener);
        Board_3_6 = new javax.swing.JButton();
        Board_3_6.addActionListener(boardButtonActionListener);
        Board_3_7 = new javax.swing.JButton();
        Board_3_7.addActionListener(boardButtonActionListener);
        Board_3_8 = new javax.swing.JButton();
        Board_3_8.addActionListener(boardButtonActionListener);
        Board_3_9 = new javax.swing.JButton();
        Board_3_9.addActionListener(boardButtonActionListener);
        Board_2_0 = new javax.swing.JButton();
        Board_2_0.addActionListener(boardButtonActionListener);
        Board_2_1 = new javax.swing.JButton();
        Board_2_1.addActionListener(boardButtonActionListener);
        Board_2_2 = new javax.swing.JButton();
        Board_2_2.addActionListener(boardButtonActionListener);
        Board_2_3 = new javax.swing.JButton();
        Board_2_3.addActionListener(boardButtonActionListener);
        Board_2_4 = new javax.swing.JButton();
        Board_2_4.addActionListener(boardButtonActionListener);
        Board_2_5 = new javax.swing.JButton();
        Board_2_5.addActionListener(boardButtonActionListener);
        Board_2_6 = new javax.swing.JButton();
        Board_2_6.addActionListener(boardButtonActionListener);
        Board_2_7 = new javax.swing.JButton();
        Board_2_7.addActionListener(boardButtonActionListener);
        Board_2_8 = new javax.swing.JButton();
        Board_2_8.addActionListener(boardButtonActionListener);
        Board_2_9 = new javax.swing.JButton();
        Board_2_9.addActionListener(boardButtonActionListener);
        Board_1_0 = new javax.swing.JButton();
        Board_1_0.addActionListener(boardButtonActionListener);
        Board_1_1 = new javax.swing.JButton();
        Board_1_1.addActionListener(boardButtonActionListener);
        Board_1_2 = new javax.swing.JButton();
        Board_1_2.addActionListener(boardButtonActionListener);
        Board_1_3 = new javax.swing.JButton();
        Board_1_3.addActionListener(boardButtonActionListener);
        Board_1_4 = new javax.swing.JButton();
        Board_1_4.addActionListener(boardButtonActionListener);
        Board_1_5 = new javax.swing.JButton();
        Board_1_5.addActionListener(boardButtonActionListener);
        Board_1_6 = new javax.swing.JButton();
        Board_1_6.addActionListener(boardButtonActionListener);
        Board_1_7 = new javax.swing.JButton();
        Board_1_7.addActionListener(boardButtonActionListener);
        Board_1_8 = new javax.swing.JButton();
        Board_1_8.addActionListener(boardButtonActionListener);
        Board_1_9 = new javax.swing.JButton();
        Board_1_9.addActionListener(boardButtonActionListener);
        Board_0_0 = new javax.swing.JButton();
        Board_0_0.addActionListener(boardButtonActionListener);
        Board_0_1 = new javax.swing.JButton();
        Board_0_1.addActionListener(boardButtonActionListener);
        Board_0_2 = new javax.swing.JButton();
        Board_0_2.addActionListener(boardButtonActionListener);
        Board_0_3 = new javax.swing.JButton();
        Board_0_3.addActionListener(boardButtonActionListener);
        Board_0_4 = new javax.swing.JButton();
        Board_0_4.addActionListener(boardButtonActionListener);
        Board_0_5 = new javax.swing.JButton();
        Board_0_5.addActionListener(boardButtonActionListener);
        Board_0_6 = new javax.swing.JButton();
        Board_0_6.addActionListener(boardButtonActionListener);
        Board_0_7 = new javax.swing.JButton();
        Board_0_7.addActionListener(boardButtonActionListener);
        Board_0_8 = new javax.swing.JButton();
        Board_0_8.addActionListener(boardButtonActionListener);
        Board_0_9 = new javax.swing.JButton();
        Board_0_9.addActionListener(boardButtonActionListener);
        GY_7_2 = new javax.swing.JButton();
        GY_7_2.addActionListener(gyButtonActionListener);
        GY_7_3 = new javax.swing.JButton();
        GY_7_3.addActionListener(gyButtonActionListener);
        GY_7_0 = new javax.swing.JButton();
        GY_7_0.addActionListener(gyButtonActionListener);
        GY_7_1 = new javax.swing.JButton();
        GY_7_1.addActionListener(gyButtonActionListener);
        GY_6_0 = new javax.swing.JButton();
        GY_6_0.addActionListener(gyButtonActionListener);
        GY_8_0 = new javax.swing.JButton();
        GY_8_0.addActionListener(gyButtonActionListener);
        GY_8_1 = new javax.swing.JButton();
        GY_8_1.addActionListener(gyButtonActionListener);
        GY_8_2 = new javax.swing.JButton();
        GY_8_2.addActionListener(gyButtonActionListener);
        GY_8_3 = new javax.swing.JButton();
        GY_8_3.addActionListener(gyButtonActionListener);
        GY_9_1 = new javax.swing.JButton();
        GY_9_1.addActionListener(gyButtonActionListener);
        GY_9_0 = new javax.swing.JButton();
        GY_9_0.addActionListener(gyButtonActionListener);
        GY_9_3 = new javax.swing.JButton();
        GY_9_3.addActionListener(gyButtonActionListener);
        GY_9_2 = new javax.swing.JButton();
        GY_9_2.addActionListener(gyButtonActionListener);
        GY_0_3 = new javax.swing.JButton();
        GY_0_3.addActionListener(gyButtonActionListener);
        GY_0_2 = new javax.swing.JButton();
        GY_0_2.addActionListener(gyButtonActionListener);
        GY_0_1 = new javax.swing.JButton();
        GY_0_1.addActionListener(gyButtonActionListener);
        GY_0_0 = new javax.swing.JButton();
        GY_0_0.addActionListener(gyButtonActionListener);
        GY_1_3 = new javax.swing.JButton();
        GY_1_3.addActionListener(gyButtonActionListener);
        GY_1_2 = new javax.swing.JButton();
        GY_1_2.addActionListener(gyButtonActionListener);
        GY_1_1 = new javax.swing.JButton();
        GY_1_1.addActionListener(gyButtonActionListener);
        GY_1_0 = new javax.swing.JButton();
        GY_1_0.addActionListener(gyButtonActionListener);
        GY_2_0 = new javax.swing.JButton();
        GY_2_0.addActionListener(gyButtonActionListener);
        GY_2_3 = new javax.swing.JButton();
        GY_2_3.addActionListener(gyButtonActionListener);
        GY_2_1 = new javax.swing.JButton();
        GY_2_1.addActionListener(gyButtonActionListener);
        GY_2_2 = new javax.swing.JButton();
        GY_2_2.addActionListener(gyButtonActionListener);
        GY_4_3 = new javax.swing.JButton();
        GY_4_3.addActionListener(gyButtonActionListener);
        GY_3_0 = new javax.swing.JButton();
        GY_3_0.addActionListener(gyButtonActionListener);
        GY_3_1 = new javax.swing.JButton();
        GY_3_1.addActionListener(gyButtonActionListener);
        GY_3_2 = new javax.swing.JButton();
        GY_3_2.addActionListener(gyButtonActionListener);
        GY_3_3 = new javax.swing.JButton();
        GY_3_3.addActionListener(gyButtonActionListener);
        GY_5_3 = new javax.swing.JButton();
        GY_5_3.addActionListener(gyButtonActionListener);
        GY_5_2 = new javax.swing.JButton();
        GY_5_2.addActionListener(gyButtonActionListener);
        GY_4_1 = new javax.swing.JButton();
        GY_4_1.addActionListener(gyButtonActionListener);
        GY_4_2 = new javax.swing.JButton();
        GY_4_2.addActionListener(gyButtonActionListener);
        GY_4_0 = new javax.swing.JButton();
        GY_4_0.addActionListener(gyButtonActionListener);
        GY_6_2 = new javax.swing.JButton();
        GY_6_2.addActionListener(gyButtonActionListener);
        GY_6_1 = new javax.swing.JButton();
        GY_6_1.addActionListener(gyButtonActionListener);
        GY_6_3 = new javax.swing.JButton();
        GY_6_3.addActionListener(gyButtonActionListener);
        GY_5_0 = new javax.swing.JButton();
        GY_5_0.addActionListener(gyButtonActionListener);
        GY_5_1 = new javax.swing.JButton();
        GY_5_1.addActionListener(gyButtonActionListener);
        jSeparator2 = new javax.swing.JSeparator();
        SetupCompleteButton = new javax.swing.JButton();
        userDisplaySP = new javax.swing.JScrollPane();
        userDisplayTextArea = new javax.swing.JTextArea();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSeparator2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        SetupCompleteButton.setText("Setup Complete");
        SetupCompleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SetupCompleteButtonActionPerformed(evt);
            }
        });

        userDisplayTextArea.setColumns(20);
        userDisplayTextArea.setRows(5);
        userDisplaySP.setViewportView(userDisplayTextArea);

        jSeparator3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(userDisplaySP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(GY_8_0, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_8_1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_8_2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_8_3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(GY_9_0, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_9_1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_9_2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_9_3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(GY_7_0, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_7_1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_7_2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_7_3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(GY_6_0, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_6_1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_6_2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_6_3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(GY_5_0, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_5_1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_5_2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_5_3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(GY_4_0, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_4_1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_4_2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_4_3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(GY_3_0, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_3_1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_3_2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_3_3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(GY_2_0, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_2_1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_2_2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_2_3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(GY_1_0, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_1_1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_1_2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_1_3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(GY_0_0, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_0_1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_0_2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GY_0_3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SetupCompleteButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Board_8_0, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_8_1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_8_2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_8_3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_8_4, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_8_5, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_8_6, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_8_7, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_8_8, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_8_9, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Board_9_0, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_9_1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_9_2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_9_3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_9_4, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_9_5, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_9_6, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_9_7, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_9_8, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_9_9, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Board_7_0, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_7_1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_7_2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_7_3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_7_4, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_7_5, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_7_6, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_7_7, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_7_8, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_7_9, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Board_6_0, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_6_1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_6_2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_6_3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_6_4, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_6_5, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_6_6, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_6_7, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_6_8, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_6_9, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Board_5_0, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_5_1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_5_2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_5_3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_5_4, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_5_5, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_5_6, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_5_7, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_5_8, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_5_9, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Board_4_0, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_4_1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_4_2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_4_3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_4_4, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_4_5, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_4_6, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_4_7, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_4_8, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_4_9, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Board_3_0, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_3_1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_3_2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_3_3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_3_4, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_3_5, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_3_6, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_3_7, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_3_8, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_3_9, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Board_2_0, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_2_1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_2_2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_2_3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_2_4, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_2_5, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_2_6, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_2_7, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_2_8, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_2_9, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Board_1_0, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_1_1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_1_2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_1_3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_1_4, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_1_5, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_1_6, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_1_7, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_1_8, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_1_9, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Board_0_0, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_0_1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_0_2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_0_3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_0_4, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_0_5, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_0_6, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_0_7, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_0_8, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Board_0_9, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Board_0_9, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_0_8, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_0_7, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_0_6, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_0_5, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_0_4, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_0_3, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_0_2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_0_1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_0_0, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Board_1_9, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_1_8, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_1_7, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_1_6, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_1_5, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_1_4, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_1_3, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_1_2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_1_1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_1_0, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Board_2_9, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_2_8, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_2_7, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_2_6, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_2_5, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_2_4, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_2_3, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_2_2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_2_1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_2_0, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Board_3_9, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_3_8, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_3_7, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_3_6, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_3_5, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_3_4, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_3_3, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_3_2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_3_1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_3_0, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Board_4_9, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_4_8, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_4_7, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_4_6, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_4_5, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_4_4, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_4_3, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_4_2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_4_1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_4_0, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Board_5_9, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_5_8, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_5_7, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_5_6, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_5_5, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_5_4, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_5_3, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_5_2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_5_1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_5_0, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Board_6_9, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_6_8, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_6_7, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_6_6, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_6_5, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_6_4, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_6_3, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_6_2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_6_1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_6_0, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Board_7_9, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_7_8, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_7_7, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_7_6, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_7_5, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_7_4, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_7_3, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_7_2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_7_1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_7_0, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Board_8_9, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_8_8, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_8_7, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_8_6, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_8_5, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_8_4, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_8_3, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_8_2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_8_1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(Board_8_0, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Board_9_9, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(Board_9_8, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(Board_9_7, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(Board_9_6, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(Board_9_5, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(Board_9_4, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(Board_9_3, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(Board_9_2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(Board_9_1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(Board_9_0, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(GY_0_3, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_0_2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_0_1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_0_0, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(GY_1_3, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_1_2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_1_1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_1_0, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(GY_2_3, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_2_2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_2_1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_2_0, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(GY_3_3, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_3_2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_3_1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_3_0, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(GY_4_3, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_4_2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_4_1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_4_0, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(GY_5_3, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_5_2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_5_1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_5_0, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(GY_6_3, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                                    .addComponent(GY_6_2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                                    .addComponent(GY_6_1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                                    .addComponent(GY_6_0, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)))
                            .addComponent(SetupCompleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(GY_7_3, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_7_2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_7_1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_7_0, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(GY_8_3, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_8_2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_8_1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_8_0, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(GY_9_3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_9_2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_9_1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(GY_9_0, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userDisplaySP, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Board_9_1.getAccessibleContext().setAccessibleName("Board0,0");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SetupCompleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SetupCompleteButtonActionPerformed
       
        if(gameInt.setUp())
        {
            setupCompleted = true;
            gameInt.computerSetup();
            userDisplayTextArea.append("The game is set up\n");
            
            lastBoardHitLocation = null;
            lastHitLocation = null;

            SetupCompleteButton.setEnabled(false);

        }
        else
        {
            userDisplayTextArea.append("There are still pieces that have not been placed\n");
        }
    }//GEN-LAST:event_SetupCompleteButtonActionPerformed

    public void update(GameInstance in)
    {
        gameInt = in;
    }
    

    /**
    * @param args the command line arguments
    */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            GameInstance x = new GameInstance();
            public void run() {
                new SetupView(x).setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Board_0_0;
    private javax.swing.JButton Board_0_1;
    private javax.swing.JButton Board_0_2;
    private javax.swing.JButton Board_0_3;
    private javax.swing.JButton Board_0_4;
    private javax.swing.JButton Board_0_5;
    private javax.swing.JButton Board_0_6;
    private javax.swing.JButton Board_0_7;
    private javax.swing.JButton Board_0_8;
    private javax.swing.JButton Board_0_9;
    private javax.swing.JButton Board_1_0;
    private javax.swing.JButton Board_1_1;
    private javax.swing.JButton Board_1_2;
    private javax.swing.JButton Board_1_3;
    private javax.swing.JButton Board_1_4;
    private javax.swing.JButton Board_1_5;
    private javax.swing.JButton Board_1_6;
    private javax.swing.JButton Board_1_7;
    private javax.swing.JButton Board_1_8;
    private javax.swing.JButton Board_1_9;
    private javax.swing.JButton Board_2_0;
    private javax.swing.JButton Board_2_1;
    private javax.swing.JButton Board_2_2;
    private javax.swing.JButton Board_2_3;
    private javax.swing.JButton Board_2_4;
    private javax.swing.JButton Board_2_5;
    private javax.swing.JButton Board_2_6;
    private javax.swing.JButton Board_2_7;
    private javax.swing.JButton Board_2_8;
    private javax.swing.JButton Board_2_9;
    private javax.swing.JButton Board_3_0;
    private javax.swing.JButton Board_3_1;
    private javax.swing.JButton Board_3_2;
    private javax.swing.JButton Board_3_3;
    private javax.swing.JButton Board_3_4;
    private javax.swing.JButton Board_3_5;
    private javax.swing.JButton Board_3_6;
    private javax.swing.JButton Board_3_7;
    private javax.swing.JButton Board_3_8;
    private javax.swing.JButton Board_3_9;
    private javax.swing.JButton Board_4_0;
    private javax.swing.JButton Board_4_1;
    private javax.swing.JButton Board_4_2;
    private javax.swing.JButton Board_4_3;
    private javax.swing.JButton Board_4_4;
    private javax.swing.JButton Board_4_5;
    private javax.swing.JButton Board_4_6;
    private javax.swing.JButton Board_4_7;
    private javax.swing.JButton Board_4_8;
    private javax.swing.JButton Board_4_9;
    private javax.swing.JButton Board_5_0;
    private javax.swing.JButton Board_5_1;
    private javax.swing.JButton Board_5_2;
    private javax.swing.JButton Board_5_3;
    private javax.swing.JButton Board_5_4;
    private javax.swing.JButton Board_5_5;
    private javax.swing.JButton Board_5_6;
    private javax.swing.JButton Board_5_7;
    private javax.swing.JButton Board_5_8;
    private javax.swing.JButton Board_5_9;
    private javax.swing.JButton Board_6_0;
    private javax.swing.JButton Board_6_1;
    private javax.swing.JButton Board_6_2;
    private javax.swing.JButton Board_6_3;
    private javax.swing.JButton Board_6_4;
    private javax.swing.JButton Board_6_5;
    private javax.swing.JButton Board_6_6;
    private javax.swing.JButton Board_6_7;
    private javax.swing.JButton Board_6_8;
    private javax.swing.JButton Board_6_9;
    private javax.swing.JButton Board_7_0;
    private javax.swing.JButton Board_7_1;
    private javax.swing.JButton Board_7_2;
    private javax.swing.JButton Board_7_3;
    private javax.swing.JButton Board_7_4;
    private javax.swing.JButton Board_7_5;
    private javax.swing.JButton Board_7_6;
    private javax.swing.JButton Board_7_7;
    private javax.swing.JButton Board_7_8;
    private javax.swing.JButton Board_7_9;
    private javax.swing.JButton Board_8_0;
    private javax.swing.JButton Board_8_1;
    private javax.swing.JButton Board_8_2;
    private javax.swing.JButton Board_8_3;
    private javax.swing.JButton Board_8_4;
    private javax.swing.JButton Board_8_5;
    private javax.swing.JButton Board_8_6;
    private javax.swing.JButton Board_8_7;
    private javax.swing.JButton Board_8_8;
    private javax.swing.JButton Board_8_9;
    private javax.swing.JButton Board_9_0;
    private javax.swing.JButton Board_9_1;
    private javax.swing.JButton Board_9_2;
    private javax.swing.JButton Board_9_3;
    private javax.swing.JButton Board_9_4;
    private javax.swing.JButton Board_9_5;
    private javax.swing.JButton Board_9_6;
    private javax.swing.JButton Board_9_7;
    private javax.swing.JButton Board_9_8;
    private javax.swing.JButton Board_9_9;
    private javax.swing.JButton GY_0_0;
    private javax.swing.JButton GY_0_1;
    private javax.swing.JButton GY_0_2;
    private javax.swing.JButton GY_0_3;
    private javax.swing.JButton GY_1_0;
    private javax.swing.JButton GY_1_1;
    private javax.swing.JButton GY_1_2;
    private javax.swing.JButton GY_1_3;
    private javax.swing.JButton GY_2_0;
    private javax.swing.JButton GY_2_1;
    private javax.swing.JButton GY_2_2;
    private javax.swing.JButton GY_2_3;
    private javax.swing.JButton GY_3_0;
    private javax.swing.JButton GY_3_1;
    private javax.swing.JButton GY_3_2;
    private javax.swing.JButton GY_3_3;
    private javax.swing.JButton GY_4_0;
    private javax.swing.JButton GY_4_1;
    private javax.swing.JButton GY_4_2;
    private javax.swing.JButton GY_4_3;
    private javax.swing.JButton GY_5_0;
    private javax.swing.JButton GY_5_1;
    private javax.swing.JButton GY_5_2;
    private javax.swing.JButton GY_5_3;
    private javax.swing.JButton GY_6_0;
    private javax.swing.JButton GY_6_1;
    private javax.swing.JButton GY_6_2;
    private javax.swing.JButton GY_6_3;
    private javax.swing.JButton GY_7_0;
    private javax.swing.JButton GY_7_1;
    private javax.swing.JButton GY_7_2;
    private javax.swing.JButton GY_7_3;
    private javax.swing.JButton GY_8_0;
    private javax.swing.JButton GY_8_1;
    private javax.swing.JButton GY_8_2;
    private javax.swing.JButton GY_8_3;
    private javax.swing.JButton GY_9_0;
    private javax.swing.JButton GY_9_1;
    private javax.swing.JButton GY_9_2;
    private javax.swing.JButton GY_9_3;
    private javax.swing.JButton SetupCompleteButton;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JScrollPane userDisplaySP;
    private javax.swing.JTextArea userDisplayTextArea;
    // End of variables declaration//GEN-END:variables
}