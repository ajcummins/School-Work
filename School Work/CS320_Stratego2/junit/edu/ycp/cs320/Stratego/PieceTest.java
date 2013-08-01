package edu.ycp.cs320.Stratego;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class PieceTest {
	private Piece piece;
	
	@Before
	public void setUp() {
		piece = new Piece(true, Pieces.BOMB);
	}
	
	@Test
	public void testIsHuman() throws Exception {
		assertTrue(piece.get_isHuman());
		piece.set_human(false);
		assertFalse(piece.get_isHuman());
	}
	
	@Test
	public void testValue() throws Exception {
		Piece p2 = new Piece(false,Pieces.FIVE);
		assertTrue((piece.get_Value()).compareTo(p2.get_Value())>0);
		piece.set_Value(Pieces.FOUR);
		assertTrue((piece.get_Value()).compareTo(p2.get_Value())<0);
		p2.set_Value(Pieces.FOUR);
		assertTrue((piece.get_Value()).compareTo(p2.get_Value())==0);
		
	}
	
	@Test
	public void testRemoved() throws Exception {
		assertFalse(piece.get_hasBeenRemoved());
		piece.set_hasBeenRemoved(true);
		assertTrue(piece.get_hasBeenRemoved());
	}
	
	@Test
	public void testMoveType() throws Exception {
		assertEquals(piece.get_MoveType(),0);
		piece = new Piece(true,Pieces.TWO);
		assertEquals(piece.get_MoveType(),2);
		piece = new Piece(true,Pieces.THREE);
		assertEquals(piece.get_MoveType(),1);
	}
	
	@Test
	public void testSet_GetSeen() throws Exception {
		assertFalse(piece.get_hasBeenSeen());
		piece.set_hasBeenSeen(true);
		assertTrue(piece.get_hasBeenSeen());
	}
	
	@Test
	public void testSet_MoveType() throws Exception {
		assertEquals(piece.get_MoveType(),0);
		piece.set_MoveType(1);
		assertEquals(piece.get_MoveType(),1);
	}
}
