package edu.ycp.cs320.Stratego;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {
	private Location location;

	@Before
	public void setUp(){
		location = new Location(1,3);
	}
	
	@Test
	public void testSet_X(){
		assertEquals(location.get_X(),1);
		location.set_X(2);
		assertEquals(location.get_X(),2);
	}
	
	@Test
	public void testSet_Y(){
		assertEquals(location.get_Y(),3);
		location.set_Y(4);
		assertEquals(location.get_Y(),4);
	}
	//Test: toString
}