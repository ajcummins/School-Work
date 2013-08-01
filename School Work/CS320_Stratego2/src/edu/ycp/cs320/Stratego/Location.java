package edu.ycp.cs320.Stratego;

public class Location {
	private int x;
	private int y;

	public Location(int inX, int inY)
	{
		this.x = inX;
		this.y = inY;
	}
	
	public void set_X(int x)		//Sets the x coordinate of the location
	{
		this.x = x;
		return;
	}
	
	public void set_Y(int y)		//Sets the y coordinate of the location
	{
		this.y = y;
		return;
	}
	public int get_X()		//returns the x coordinate
	{
		return x;
	}
	
	public int get_Y()		//returns the y coordinate
	{
		return y;
	}

        public boolean compareLocation(Location in)
        {
            if(x == in.get_X() && y == in.get_Y())
            {
                return true;
            }
            else
            {
                return false;
            }
        }

    @Override
    public String toString() {
        return x + "," + y;
    }


}
