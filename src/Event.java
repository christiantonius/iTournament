import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;


public class Event { // class for an event
	String eventName;
	int startTime, numberOfTeams, numberOfReferees;
	double registrationFee;
	private Vector<Team> teamList;
	private Vector<Referee> refereeList;
	boolean isCreateEventFinish = false;
	
	public Event() { // create an event with an empty team list
		teamList = new Vector<Team>();
		refereeList = new Vector<Referee>();
	}
	
	public void setIsCreateEventFinish() {
		isCreateEventFinish = true;
	}
	
	public void setEventName(String eName) { // set the event name
		eventName = eName;
	}
	
	public void setNumberOfTeams(int noOfTeams) { // set the number of teams in the event
		numberOfTeams = noOfTeams;
	}
	
	public void setNumberOfReferees(int noOfReferees) {
		numberOfReferees = noOfReferees;
	}
	
	public boolean getIsCreateEventFinish() {
		return isCreateEventFinish;
	}
	
	public String getEventName() { // return the event name
		return eventName;
	} 
	
	public Vector<Team> getTeamList() { // return the vector of team list
		return teamList;
	}

	public Vector<Referee> getRefereeList() { // return the vector of referee list
		return refereeList;
	}
	
	public int getEventSize() { // return the number of teams in an event
		return numberOfTeams;
	}
	
	public int getRefSize() { // return the number of referees in an event
		return numberOfReferees;
	}
	
	public void addTeam (Team _team) { // add a team into the event
		teamList.add(_team);		
	}
	
	public void addReferee (Referee _referee) { // add a referee into the event
		refereeList.add(_referee);
	}
	
	public void createReferee(String []name, double []cost) {		
		for(int i=0; i<numberOfReferees; i++) {
			Referee tempReferee = new Referee();
			tempReferee.setName(name[i]);
			tempReferee.setCost(cost[i]);
			addReferee(tempReferee);
		}
	}
	
	public void createTeam(String teamname, int teamSize, String[] players, int[]numbers) { // create a team, save it, and add it into team list
		Team tempTeam = new Team(); // create a new team
		
		tempTeam.setTeamName(teamname); // set the name entered as the team name
		tempTeam.setTeamSize(teamSize); // set the number of players in a team
		
		// input the player name and his jersey number, and add it into the player list
		int size = teamSize;
		for(int i=0; i<size; i++) 
			tempTeam.registerPlayer(players[i], numbers[i]); 
		
		tempTeam.saveTeam(tempTeam); // save into a text file
		addTeam(tempTeam);
	}

	public void saveReferee() {
		try{
			File file = new File("data/" + eventName + ".irf");
			FileWriter outFile = new FileWriter(file);
			PrintWriter out = new PrintWriter(outFile);
			
			out.println(numberOfReferees);
			
			for (int i = 0; i < numberOfReferees; i++) {
				Referee tempReferee = refereeList.get(i);
				out.printf("%s\n", tempReferee.getName());
				out.printf("%.2f\n", tempReferee.getCost());					
			}
			
			out.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	// load the referee list from a text file
	public void loadReferee(String fileName) throws IOException {
		fileName = "data/" + fileName;
		// Declare and initialize local variables 
		FileInputStream fStream = null;		
		
		// Begin a block of code that handles exceptions
		try{
			// Open the file as a stream
			fStream = new FileInputStream(fileName);
			
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fStream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			
			strLine = br.readLine();
			numberOfReferees = Integer.parseInt(strLine); // load the number of referees
			
			// read file line by line
			int i = 0;
			while (i < getRefSize()) {
				Referee tempReferee = new Referee(); // create a dummy referee
				strLine = br.readLine();
				tempReferee.setName(strLine); // set referee name
				strLine = br.readLine();
				tempReferee.setCost(Double.parseDouble(strLine)); //set referee cost
				i++; // next referee
			}
		}
		finally // handle any exceptions
		{			
			// If the file is open, then close it.
			if (fStream != null){								
				fStream.close();					
			}
		}	
	}
	
	/*********************************************
	 * save the event information into a text file
	 * format:
	 * event_name
	 * number_of_teams
	 * next line and onwards:
	 * team_name
	 *********************************************
	 */
	public void saveEvent() {
		try{
			File file = new File("data/" + eventName + ".ivt");
			FileWriter outFile = new FileWriter(file);
			PrintWriter out = new PrintWriter(outFile);
			
			out.println(eventName);
			out.println(numberOfTeams);
			
			for (int i = 0; i < numberOfTeams; i++) {
				Team tempTeam = teamList.get(i);
				out.printf("%s\n", tempTeam.getTeamName());
			}
			
			out.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
	// load an event from a text file
	public void loadEvent(String fileName) throws IOException {
		fileName = "data/" + fileName;
		// Declare and initialize local variables 
		FileInputStream fStream = null;		
		
		// Begin a block of code that handles exceptions
		try{
			// Open the file as a stream
			fStream = new FileInputStream(fileName);
			
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fStream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			
			// read the first two lines
			strLine = br.readLine();
			eventName = strLine; // load event name
			strLine = br.readLine();
			numberOfTeams = Integer.parseInt(strLine); // load the number of participating teams
			
			// read file line by line
			int i = 0;
			while (i < getEventSize()) {
				Team tempTeam = new Team(); // create a dummy team
				strLine = br.readLine();
				tempTeam.loadTeam(strLine + ".itm"); // load the team
				addTeam(tempTeam); // add the team to the event's team list
				i++;
			}
		}
		
		finally // handle any exceptions
		{			
			// If the file is open, then close it.
			if (fStream != null){								
				fStream.close();					
			}
		}	
	}
	
	public void deleteFile(String fileName) {
	    File f = new File("data/" + fileName);
	
	    // Make sure the file or directory exists and isn't write protected
	    if (!f.exists())
	      throw new IllegalArgumentException(
	          "Delete: no such file or directory: " + fileName);
	
	    if (!f.canWrite())
	      throw new IllegalArgumentException("Delete: write protected: "
	          + fileName);
	
	    // Attempt to delete it
	    boolean success = f.delete();
	
	    if (!success)
	      throw new IllegalArgumentException("Delete: deletion failed");
  }
	
	// for testing purpose
	public void printEvent() {
		System.out.println("Event name: " + getEventName());
		System.out.printf("Number of teams: %d\n", getEventSize());
		for (int i = 1; i <= getEventSize(); i++) {
			System.out.printf("Team %d:\n", i);
			Team tempTeam = getTeamList().get(i-1);
			System.out.println(tempTeam.getTeamName());
			int j;
			for (j = 0; j < tempTeam.getTeamSize(); j++) {
				Player tempPlayer = tempTeam.getTeamList().get(j);
				System.out.printf("%d %s\n", tempPlayer.getNumber(), tempPlayer.getName());
			}
		}
	}
}
