import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
/*
 * Purpose: Data Structure and Algorithms project
 * Status: Complete
 * Last update: 12/06/15
 * Submitted:  12/07/15
 * @author: Tom Miller
 * @version: 2015.11.04
 */
public class Application {
	/**
	 * The main part of the program. It runs a loop that allows the user
	 * to enter a number, and depending on the number, it will call a method. 
	 * It starts off with an initial question for how many runways the program 
	 * should start with, and initializes the Lists: planes and runways. These 
	 * are the lists for all the planes, and every runway. It also creates currentLauncher, 
	 * which is the Runway that the next plane will take off from. id is the unique number 
	 * that gets added into every plane. takenOff if the amount of planes that have taken off.
	 */
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static List planes = new List();	//This is a list to hold all of the planes.
	private static List runways = new List();	//This is a list to hold all of the runways.
	private static Runway currentLauncher;		//This is the runway that will be launching the next plane.
	private static int id = 1;		//ID for the runways.
	private static int takenOff = 0;	//number of planes that have taken off.
	
	public static void main(String args[]) throws IOException   
	{			
		System.out.println("Welcome to the Airport program!");
		System.out.print("Enter number of runways: ");
		String numOfRunwaysTMP = bf.readLine();
		int numOfRunways = Integer.parseInt(numOfRunwaysTMP);
		System.out.println();
		
		//creates initial runways 
		while(id <= numOfRunways)
		{
			String runwayName = "";
			boolean alreadyUsed = true;
			while (alreadyUsed == true)
			{
				alreadyUsed = false;
				System.out.print("Enter the name of runway number " + id + ": ");
				runwayName = bf.readLine();
				for (int x = 0; x < runways.size(); x++)
				{		
					if (((Runway) runways.get(x)).getName().equals(runwayName))
					{
						System.out.println("That name is already in use.");
						alreadyUsed = true;
					}
				}
			}
			
			Queue q1 = new Queue();
			Queue q2 = new Queue();
			Runway newRunway = new Runway(q1, q2, id, runwayName, null);
			runways.add(0,newRunway);
			id++;
			System.out.println();
		}
		currentLauncher = (Runway)runways.get(runways.size()-1);	//selects first runway for first launcher
		System.out.println();
		
		boolean finished = false;
		while (finished == false)
		{
			System.out.println("Select from the following menu: ");
			System.out.println("1. Plane enters the system.\n2. Plane takes off.\n3. Plane is allowed to re-enter a runway.\n4. Runway opens.\n5. Runway closes.\n6. Display info about planes waiting to take off.\n7. Display info about planes waiting to be allowed to re-enter a runway.\n8. Display number of planes who have taken off.\n9. End the program.");
			System.out.print("Make your menu selection now: ");
			String numTMP = bf.readLine();
			int num = Integer.parseInt(numTMP);
			
			switch(num)
			{
			case 1:  enterPlane(); break;
			case 2:  planeTakesOff(); break;
			case 3:  planeAllowedToReenter(); break;
			case 4:  runwayOpens(); break;
			case 5:  runwayCloses(); break;
			case 6:  displayPlanesWaitingForTakeOff(); break;
			case 7:  displayPlanesWaitingToBeReentered(); break;
			case 8:  displayNumberOfPlanesTakenOff(); break;
			case 9: System.out.println("The Airport is closing :Bye Bye..."); finished = true; break;
				default: System.out.println("Invalid Number");
			}
			System.out.println();
		}
	}
	

	
	/**
	 * Method for the user to add a plane to a runway.
	 * It will require input for the plane's unique flight number, destination, and runway.
	 */
	private static void enterPlane() throws IOException
	{
		System.out.println();
		//checks to see if there are runways or not.
		if (runways.isEmpty() == true)
		{
			System.out.println("There are no runways to add planes to!");
		}
		else
		{
			boolean done = false;		//used for while loop below.
			String flightNum ="";
			boolean alreadyUsed = true;
			while (alreadyUsed == true)
			{
				alreadyUsed = false;
				System.out.println("Enter flight number: ");
				flightNum = bf.readLine();
				//checks to see if a planes flight number is unique or not.
				for (int x = 0; x < planes.size(); x++)
				{
					if (((Plane) planes.get(x)).getNum().equals(flightNum))
					{
						System.out.println("That flight number is already in use.");
						alreadyUsed = true;
					}
				}
			}
			
			System.out.println("Enter destination: ");
			String destination = bf.readLine();
			
			//adds the plane to a runway.
			while (done == false)
			{
				System.out.println("Enter runway: ");
				String runway = bf.readLine();
				for (int x = 0; x < runways.size(); x++)
				{
					if (((Runway)runways.get(x)).getName().equals(runway))
					{
						Plane p = new Plane((Runway)runways.get(x),flightNum,destination,false);
						planes.add(0,p);
						((Runway) runways.get(x)).addPlaneToRunway(p);
						System.out.println("Flight " + flightNum + " is now waiting for takeoff on runway " + runway + ".");
						done = true;
					}
				}
				if (done == false)
				{
					System.out.println("No such runway!");
				}
			}
		}
	}
	
	
	/**
	 * Uses the currentLauncher and checks if there is a plane that can be 
	 * sent away from that runway.It will then ask the user whether or not
	 * the plane has permission to take off. If it does, then the plane will 
	 * take off, if not then it will be sent to the runways waiting area.
	 */
	private static void planeTakesOff() throws IOException
	{
		System.out.println();
		if (planes.isEmpty() == true)
		{
			System.out.println("No planes on any runways!");
		}
		else
		{
			boolean empty = true;
			
			//checks if we have runways
			if (runways.isEmpty() == true)										
			{
				System.out.println("There are no runways!");
			}
			else
			{
				//checks if they are all empty
				for (int x = 0; x < runways.size(); x++)							
				{
					if (((Runway) runways.get(x)).seeTopOnRunwayPlane() != null)				
					{
						empty = false;		
					}
				}	
				
				//finds the next runway with an available plane.
				while (currentLauncher.seeTopOnRunwayPlane() == null)
				{
					switchLauncher();
				}
				
				
				//goes through and moves planes to another runway
				if (empty == false)
				{
					for (int x = 0; x < runways.size(); x++)			
					{	
						if (runways.get(x) == currentLauncher)
						{
								//checks if any runway has a plane on it.
								boolean allEmpty = true;
								for (int i = 0; i < runways.size(); i++)			
								{
									if (((Runway) runways.get(i)).seeTopOnRunwayPlane() != null)
									{
										allEmpty = false;
									}
								}
								
								
							    //finds the next runway with an available plane.
								while (currentLauncher.seeTopOnRunwayPlane() == null && allEmpty == false)
								{
									switchLauncher();
								}
								 
								
								
								//prints out the plane and asks user to take off or not.
								System.out.println("Is flight " + currentLauncher.seeTopOnRunwayPlane().getNum() + " cleard for takeoff(Y/N):");
								String yesNo = bf.readLine().toUpperCase();
								if (yesNo.equals("N")) //sends plane to that runways waiting list
								{
									Plane tmp = currentLauncher.takeTopUnclearedPlane();
									System.out.println("Flight " + tmp.getNum() + " is now waiting to re-enter a runway.");
									currentLauncher.addToWaiting(tmp);
									tmp.switchStatus();
								}
								else if (yesNo.equalsIgnoreCase("Y"))  //lets plane take off
								{
									Plane tmp = currentLauncher.takeTopUnclearedPlane();
									System.out.println("Flight " + tmp.getNum() + " has now taken off from runway " + currentLauncher.getName());
									takenOff++;
									for (int j = 0; j < planes.size(); j++)
									{		
										if (planes.get(j) == tmp)
										{
											planes.remove(j);
										}
									}
								}
						}
					}
					//This switches the launcher to the next runway.
					switchLauncher();
				}
				else
				{
					System.out.println("No plane on any runway!");
				}
			}
		}
	}
	
	
	/**
	 * This is used to switch the runway to the next one in the list after
	 *  a plane takes off. It just finds the next runway in the list and selects it.
	 */
	private static void switchLauncher()
	{
		int index = 0;
		for (int i = runways.size()-1; i >= 0; i--)
		{
			if (currentLauncher == runways.get(i))
			{
				index = i;
			}
		}
		if (index == 0)
		{
			index = runways.size()-1;
			currentLauncher = (Runway) runways.get(index);
		}
		else
		{
			index--;
			currentLauncher = (Runway) runways.get(index);
		}
	}
	
	
	/**
	 * This method asks a user to input a flight number and it will search for 
	 * that flight number to see if it is a plane that is in a waiting area.
	 * If it is, then it will allow the plane back onto it's runway.
	 */
	private static void planeAllowedToReenter() throws IOException
	{
		System.out.println();
		boolean noPlanes = true;
		//checks to make sure there are planes.
		for (int x = 0; x < runways.size(); x++)
		{
			if (((Runway) runways.get(x)).getWaitingPlanes() != "")
			{
				noPlanes = false;
			}
		}
		if (noPlanes == true)
		{
			System.out.println("There are no planes waiting to be re-entered.");
		}
		else
		{
			//check if there are any planes waiting for clearance.
			boolean empty = true;
			for (int x = 0; x < runways.size(); x++)
			{
				if (((Runway) runways.get(x)).isEmpty() == false)
				{
					empty = false;
				}
			}
			
			
			//if there are planes, it will ask for a specified flight number
			if (empty == true)
			{
				System.out.println("There are no planes waiting for clearance!");
			}
			else
			{
				System.out.println("Enter flight number: ");
				String flightNum = bf.readLine();
				
				
				//this will find the plane.
				Plane p = null;
				boolean correctPlane = false;
				
				for (int x = planes.size()-1; x >= 0; x--)
				{
					if (flightNum.equals(((Plane) planes.get(x)).getNum()))
					{
						p = (Plane)planes.get(x);
						correctPlane = true;
					}
				}
				
				//adds the plane back onto the runway.
				if (correctPlane == true)
				{
					for (int x = 0; x < runways.size(); x++)
					{
						if (runways.get(x) == p.getRunway())
						{
							if (p.getStatus() == true)
							{
								System.out.println(p.getNum() + " is now waiting to takeoff on runway " + p.getRunway().getName());
								p.switchStatus();
								((Runway)runways.get(x)).addPlaneToRunway(p);
								((Runway) runways.get(x)).removePlaneFromWaiting(p);
							}
						}
					}
				}
				else
				{
					System.out.println("There is no plane with that flight number");
				}
			}
		}
	}
	
	/**
	 * This will open a new runway at the airport and will allow planes to
	 * be added to it.
	 */
	private static void runwayOpens() throws IOException
	{
		System.out.println();
		boolean finished = false;
		String runway = "";
		
		//user inputs runways name and it checks to see if that name is available.
		while (finished == false)
		{
			System.out.println("Enter the name of the new runway: ");
			runway = bf.readLine();
			boolean alreadyExists = false;
			for (int x = 0; x < runways.size(); x++)
			{
				if (((Runway) runways.get(x)).getName().equals(runway))
				{
					alreadyExists = true;
					System.out.println("Runway " + runway + " already exists, please choose another name.");
				}
			}
			if (alreadyExists == false)
			{
				finished = true;
			}
		}
		//creates runway and notifies user.
		System.out.println("Runway " + runway + " has opened");
		Queue q1 = new Queue();	
		Queue q2 = new Queue(); 
		Runway r = new Runway(q1, q2, id, runway, null);
		runways.add(0, r);
		id++;
	}
	
	
	
	
	
	
	/**
	 * This will ask a user for a runway name and then it will shut that
	 * runway down. If there are planes on it, it will go through each
	 * one by one and ask the user which runway to move the plane to.
	 * It will not allow the user to shut down the runway if it is the only
	 * one since it will have no place to move the planes to.
	 */
	private static void runwayCloses() throws IOException
	{
		System.out.println();
		//checks to see if there is only one runway, and then checks for zero runways.
		if (runways.size() == 1)
		{
			System.out.println("There will be no runways to add planes to, so you cannot remove this runway, until a new runway becomes available.");
		}
		else
		{
			if (runways.isEmpty() == true)
			{
				System.out.println("There are no current runways available.");
			}
			else
			{
				boolean done = false;		//used to close the while loop for finding the correct runway.
				Runway r = null;			//place holder for the runway we are closing.
				
				while (done == false)
				{
					System.out.println("Enter runway: ");
					String runway = bf.readLine();
					boolean realRunway = false;				//Checks if there is a runway with the name entered.
					
					for(int x = 0; x < runways.size(); x++)
					{
						if (((Runway) runways.get(x)).getName().equals(runway))
						{
							if (((Runway) runways.get(x)).getNextUnclearedPlane() != null && ((Runway) runways.get(x)).getNextWaitingPlane() != null)
							{
								r = (Runway)runways.get(x);
								runways.remove(x);
								if (currentLauncher == r)
								{
									switchLauncher();
								}
								done = true;
								realRunway = true;
							}
							else 
							{
								realRunway = true;
								System.out.println("That runway has no planes on it!");
							}
						}
					}
					
					if (realRunway == false)
					{
						System.out.println("No such runway!");
					}
				}
				//this is for closing the runway   /\
				
				
				
				//this it for sorting the planes on runways planes into selected runways  \/
				while (r.getOnRunwayPlanes() != "")
				{
					boolean fini = false;
					Plane pl = r.takeTopUnclearedPlane();
					System.out.println("Enter new runway for plane " + pl.getNum());
					//checks if runway is valid.
					while (fini == false)
					{
						boolean notFound = false;
						String runway = "";
						
						//checks if the runway closing is the selected one to move the plane to.
						while (notFound == false)
							{
								notFound = true;
								System.out.println("Enter runway: ");
								runway = bf.readLine();
								
								if (runway.equals(r.getName()))
								{
									System.out.println("This is the runway that is closing!");
									notFound = false;
								}
							}
						//finds the correct runway.
						for (int x = 0; x < runways.size(); x++)
						{
							if (((Runway) runways.get(x)).getName().equals(runway))
							{
								fini = true;
								notFound = false;
								((Runway) runways.get(x)).addPlaneToRunway(pl);
								//makes sure the correct plane matches and then adds is to the runway.
								for (int y = 0; y < planes.size(); y++)
								{
									if (planes.get(y) == pl)
									{
										System.out.println(pl.getNum() + " is now waiting for take off on " + ((Runway) runways.get(x)).getName());
										((Plane) planes.get(y)).changeRunway((Runway) runways.get(x));
									}
								}
							}
						}
						if (notFound == true)
						{
							System.out.println("No such runway!");
							System.out.println();
						}
					}
				}	
				
				
				//this it for sorting the waiting planes into selected runways  \/
				while (r.getWaitingPlanes() != "")
				{
					boolean fini = false;
					Plane pl = r.takeTopWaitingPlane();
					System.out.println("Enter new runway for plane " + pl.getNum());
					//checks if runway is valid.
					while (fini == false)
					{
						boolean notFound = false;
						String runway = "";
						
						//checks if the runway closing is the selected one to move the plane to.
						while (notFound == false)
							{
								notFound = true;
								System.out.println("Enter runway: ");
								runway = bf.readLine();
								
								if (runway.equals(r.getName()))
								{
									System.out.println("This is the runway that is closing!");
									notFound = false;
								}
							}
						
						//finds the correct runway.
						for (int x = 0; x < runways.size(); x++)
						{
							if (runway.equals(r.getName()))
							{
								System.out.println("This is the runway that is closing!");
								notFound = false;
							}
							else if (((Runway) runways.get(x)).getName().equals(runway))
							{
								fini = true;
								notFound = false;
								((Runway) runways.get(x)).addPlaneToRunway(pl);
								//makes sure the correct plane matches and then adds is to the runway.
								for (int y = 0; y < planes.size(); y++)
								{
									if (planes.get(y) == pl)
									{
										System.out.println(pl.getNum() + " is now waiting for take off on " + ((Runway) runways.get(x)).getName());
										((Plane) planes.get(y)).changeRunway((Runway) runways.get(x));
									}
								}
							}
						}
						if (notFound == true)
						{
							System.out.println("No such runway!");
							System.out.println();
						}
					}
				}	
				System.out.println("Runway " + r.getName() + " has been closed.");
			}
		}
	}
	
	
	
	
	
	
	
	/**
	 * This goes through and displays all of the planes in every waiting area.
	 */
	private static void displayPlanesWaitingForTakeOff() throws IOException
	{
		System.out.println();
		if (runways.isEmpty() == true)
		{
			System.out.println("There are no current runways.");
		}
		else
		{
			for (int x = runways.size()-1; x >= 0; x--)
			{
				if (((Runway) runways.get(x)).getOnRunwayPlanes().equals(""))
				{
					System.out.println("No planes are waiting for takeoff on runway " + ((Runway) runways.get(x)).getName());
				}	
				else if (((Runway) runways.get(x)).getOnRunwayPlanes() != "")
				{
					System.out.println("These planes are waiting for takeoff from runway " + ((Runway)runways.get(x)).getName());
					System.out.println(((Runway) runways.get(x)).getOnRunwayPlanes());
					System.out.println();
				}
			}
		}
	}
	
	
	
	
	
	
	/**
	 * This goes through and displays every plane that is not on the runways.
	 */
	private static void displayPlanesWaitingToBeReentered() throws IOException
	{
		System.out.println();
		if (runways.isEmpty() == true)
		{
			System.out.println("There are no current runways.");
		}
		else
		{
			for (int x = runways.size()-1; x >= 0; x--)
			{
				if (((Runway) runways.get(x)).getWaitingPlanes().equals(""))
				{
					System.out.println("No planes are waiting for re-entry on runway " + ((Runway) runways.get(x)).getName());
				}	
				else if (((Runway) runways.get(x)).getWaitingPlanes() != "")
				{
					System.out.println("These planes are waiting to re-enter runway: " + ((Runway)runways.get(x)).getName());
					System.out.println(((Runway) runways.get(x)).getWaitingPlanes());
					System.out.println();
				}
			}
		}
	}
	
	
	/**
	 *  displays the number of flights that have taken off.
	 */
	private static void displayNumberOfPlanesTakenOff() throws IOException
	{
		System.out.println();
		String output = " ";
		if (takenOff == 1)
		{
			output += " plane has taken off from the airport.";
		}
		else
		{
			output += " planes have taken off from the airport.";
		}
		System.out.println(takenOff + output);
	}	
}