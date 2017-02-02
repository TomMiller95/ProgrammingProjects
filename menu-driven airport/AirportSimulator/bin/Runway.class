public class Runway {
	
	private Queue <Plane> waitingPlanes = new Queue<Plane>();	//planes on the runway who are waiting to be cleared for takeoff.
	private Queue <Plane> onRunwayPlanes = new Queue<Plane>();	//planes on that have not gotten clearance to enter the runway yet.
	private Plane nextUncleared;		//This is the next plane ready to take off from the runway
	private int runwayID;	//The unique ID number to identify which runway this is
	private String name;		//The unique name given for this runway
	
	/**
	 * Runway will handle things like adding and removing planes from itself.
	 * It has two queues, one for on runway planes, and one for waiting planes. 
	 */
	public Runway(Queue waitingPlanes, Queue onRunwayPlanes, int runwayID, String name, Plane nextUncleared)
	{
		this.waitingPlanes = waitingPlanes;
		this.onRunwayPlanes = onRunwayPlanes;
		this.runwayID = runwayID;
		this.name = name;
		this.nextUncleared = nextUncleared;
	}

	/**
	 *  Quick way to know if both the runway has no planes at all.
	 *  @return TRUE if it has no planes. FALSE if it has even one plane.
	 */
	public boolean isEmpty()
	{
		if (waitingPlanes.isEmpty() == true && onRunwayPlanes.isEmpty() == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * @return the runways name.
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * This will receive a plane and add it to the runway.
	 * @param p the plane to be added to the runway.
	 */
	public void addPlaneToRunway(Plane p)
	{
		if (nextUncleared == null)
		{
			this.nextUncleared = p;
		}
		this.onRunwayPlanes.enqueue(p);		
	}
	
	/**
	 * @param p This is the plane to be set for the next spot in the uncleared planes
	 */
	public void setNextUnclearedPlane(Plane p)
	{
		this.nextUncleared = p;
	}
	/**
	 * @return the plane that is next in line on the uncleared list
	 */
	public Plane getNextUnclearedPlane()
	{
		return this.nextUncleared;
	}
	
	
	/**
	 * @param p this plane is added to the waiting planes queue
	 */
	public void addToWaiting(Plane p)
	{
		waitingPlanes.enqueue(p);
	}
	/**
	 * @return the waiting planes queue top plane.
	 */
	public Plane getNextWaitingPlane()
	{
		return this.nextUncleared;
	}
	
	
	
	/**
	 * @return pops the waitingPLanes queue plane
	 */
	public Plane takeTopWaitingPlane()
	{
		return waitingPlanes.dequeue();
	}
	/**
	 * @return pops the unclearedPlanes queue plane
	 */
	public Plane takeTopUnclearedPlane()
	{
		return onRunwayPlanes.dequeue();
	}
	
	/**
	 * @return peeks at the waitingPLanes queue plane
	 */
	public Plane seeTopWaitingPlane()
	{
		return waitingPlanes.peek();
	}
	/**
	 * @return peeks at the unClearedPLanes queue plane
	 */
	public Plane seeTopOnRunwayPlane()
	{
		return onRunwayPlanes.peek();
	}
	
	/**
	 * @return all the planes for this runways waiting list.
	 */
	public String getWaitingPlanes()
	{
		String output = this.waitingPlanes.toString();
		return output;
	}
	/**
	 * @return all the planes for this runways uncleared list.
	 */
	public String getOnRunwayPlanes()
	{
		String output = this.onRunwayPlanes.toString();
		return output;
	}
	
	/**Since its a queue, I have to go in and take every item and then remove the desired one.
	 * Then I have to put all of the others ones back in the queue.
	 * @param p this is the plane that will be removed.
	 */
	public void removePlaneFromWaiting(Plane p)
	{
		List planes = new List();
		while (waitingPlanes.isEmpty() == false)
		{
			Plane pl = waitingPlanes.dequeue();
			if (pl == p)
			{
				//this is the removed plane.
			}
			else
			{
				planes.add(0,pl);
			}
		}
		
		for (int x = 0; x < planes.size(); x++)
		{
			waitingPlanes.enqueue((Plane)planes.get(x));
		}
	}
}