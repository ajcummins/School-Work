package edu.ycp.cs320.Stratego;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SquareTest {
private Square loc;
private Piece p;
	
	@Before
	public void setUp() {
		loc = new Square();
		p = new Piece();
	}
	
	@Test
	public void testUnpassable() throws Exception {
		loc = new Square(/*2,3*/);
		assertFalse(loc.isUnpassable());
		loc.set_Unpassable(true);
		assertTrue(loc.isUnpassable());
	}
	
	@Test
	public void testSetPiece() throws Exception {
		p = new Piece(true,Pieces.BOMB);
		loc.set_Piece(p);
		assertEquals(loc.get_Piece().get_Value(),Pieces.BOMB);
		loc.set_Piece(true,Pieces.THREE);
		assertEquals(loc.get_Piece().get_Value(),Pieces.THREE);
	}
	
//	@Test
//	public void testXandY() throws Exception {
////		assertEquals(loc.get_X(),null);
////		assertEquals(loc.get_Y(),null);
//		loc = new Square(4,5);
//		assertEquals(loc.get_X(),4);
//		assertEquals(loc.get_Y(),5);
//		loc.set_X(3);
//		loc.set_Y(7);
//		assertEquals(loc.get_X(),3);
//		assertEquals(loc.get_Y(),7);
//	}
	
	@Test
	public void testHasPiece() throws Exception {
		p = new Piece(true,Pieces.NO_PIECE);
		loc = new Square(/*3,4,*/p);
		assertFalse(loc.hasPiece());
		p = new Piece(true,Pieces.BOMB);
		loc.set_Piece(p);
		assertTrue(loc.hasPiece());
	}
}
