package edu.ycp.cs320.Stratego;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class GameInstanceTest {

	private Board board;
	private Location loc1;
	private Location loc2;
	private Location loc3;
	private Location loc4;
	private Location loc5;
	private Location loc6;
	private GameInstance game;
	private Piece[] PieceBank;
	
	@Before
	public void setUp(){
		game = new GameInstance();
		board = game.getBoard();
		loc1 = new Location(1,1);
		loc2 = new Location(1,2);
		loc3 = new Location(2,2);
		loc4 = new Location(2,3);
		loc5 = new Location(1,6);
		loc6 = new Location(1,4);
		PieceBank = new Piece[0];
		
		board.getSquare(loc1).set_Piece(true, Pieces.FIVE);
		board.getSquare(loc2).set_Piece(false, Pieces.SIX);
		board.getSquare(loc3).set_Piece(true, Pieces.SEVEN);
		
	}
	
	@Test
	public void testBattle(){
		assertEquals(board.getPiece(new Location(5,5)).get_Value(),Pieces.NO_PIECE);
		assertEquals(board.getPiece(loc1).get_Value(),Pieces.FIVE);
		assertEquals(board.getPiece(loc2).get_Value(),Pieces.SIX);
		game.Battle(board.getSquare(loc2),board.getSquare(loc1));
		
		assertEquals(board.getPiece(loc1).get_Value(),Pieces.NO_PIECE);
		assertEquals(board.getPiece(loc2).get_Value(),Pieces.SIX);
		
		game.Battle(board.getSquare(loc2), board.getSquare(loc3));
		assertEquals(board.getPiece(loc2).get_Value(),Pieces.SEVEN);
		assertEquals(board.getPiece(loc3).get_Value(),Pieces.NO_PIECE);
		assertEquals(board.getPiece(loc2).get_isHuman(),true);
	}
	
	@Test
	public void testMove(){
		assertEquals(board.getPiece(loc3).get_Value(),Pieces.SEVEN);
		game.Move(loc3,loc4);
		assertEquals(game.getPieceToBeAttacked().get_Value(),Pieces.NO_PIECE);
		assertEquals(game.getPieceToMove().get_Value(),Pieces.SEVEN);
		assertEquals(board.getPiece(loc3).get_Value(),Pieces.NO_PIECE);
		assertEquals(board.getPiece(loc4).get_Value(),Pieces.SEVEN);
		
		board.getSquare(loc3).set_Unpassable(true);
		assertEquals(game.Move(loc4,loc3),false);
	}
	
	@Test
	public void testRemovePiece(){
		assertEquals(board.getPiece(loc3).get_Value(),Pieces.SEVEN);
		game.removePiece(loc3);
		assertEquals(board.getPiece(loc3).get_Value(),Pieces.NO_PIECE);
		game.removePiece(loc3);
	}
	
	@Test
	public void testCanPlacePiece(){
		assertEquals(game.canPlacePiece(loc5),true);
		assertEquals(game.canPlacePiece(loc1),false);
	}
		
	@Test
	public void testCheckPieceBank(){
		assertEquals(game.checkPieceBank(),false);
		assertEquals(game.getPieceBank()[9].get_Value(),Pieces.EIGHT);
		//test, if piece bank has pieces that aren't 
	}
	
	@Test
	public void testSetUp(){
		assertEquals(game.setUp(),false);
		//When placePiece works check to see if true value can be tested correctly
	}
	@Test
	public void testsetPieceBankLocation(){
		
	}
	//need to test: setUp, setPieceBankLocation, getGraveYard, getPieceBank 
}
