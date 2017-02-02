public class Plane {
	
	private Runway runway;
	private String flightNum, destination;
	private boolean waiting;
	
	/**
	 * Plane is used to return information about each plane such as if its on the runway,
	 * its destination, and its flightnumber.
	 * @param runway the runway this plane is on.
	 * @param flightNum unique flightnumber that this plane was given.
	 * @param destination this planes targeted destination
	 * @param waiting determines whether this plane is on the runway or not.
	 */
	public Plane(Runway runway, String flightNum, String destination, boolean waiting)
	{
		this.runway = runway;
		this.destination = destination;
		this.flightNum = flightNum;
		this.waiting = waiting;
	}
	
	/**
	 * Switches the planes status from being on the runway, to being in the waiting area,
	 * or vice versa.
	 */
	public void switchStatus()
	{
		if (waiting == true)
		{
			waiting = false;
		}
		else
		{
			waiting = true;
		}
	}
	
	/**
	 * @return gets the runway this plane is associated with.
	 */
	public Runway getRunway()
	{
		return this.runway;
	}
	
	/**
	 * @param r the runway that this plane will now be on.
	 */
	public void changeRunway(Runway r)
	{
		this.runway = r;
	}
	
	
	/**
	 * if its waiting, it returns true.
	 * if its on the runway, it returns false
	 * @return boolean for if it is on the runway or not.
	 */
	public boolean getStatus()
	{
		return this.waiting;
	}
	
	/**
	 * @return the planes flight number.
	 */
	public String getNum()
	{
		return this.flightNum;
	}
	
	/**
	 * @return the planes destination.
	 */
	public String getDestination()
	{
		return this.destination;
	}
}