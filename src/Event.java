import java.io.BufferedReader;
import java.io.DataInputStream;
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
	
	public Event() { // create an event with an empty team list
		teamList = new Vector<Team>();
	}
	
	public void setEventName() { // set the event name
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter event name:");
		eventName = sc.nextLine();
	}
	
	public void setNumberOfTeams() { // set the number of teams in the event
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of teams:");
		numberOfTeams = sc.nextInt();
	}
	
	public void setNumberOfReferees() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of referees:");
		numberOfReferees = sc.nextInt();
	}
	
	public String getEventName() { // return the event name
		return eventName;
	} 
	
	public Vector<Team> getTeamList() { // return the vector of team list
		return teamList;
	}
	
	public int getEventSize() { // return the number of teams in an event
		return numberOfTeams;
	}
	
	public void addTeam (Team _team) { // add a team into the event
		teamList.add(_team);		
	}
	
	public void addReferee (Referee _referee) { // add a referee into the event
		refereeList.add(_referee);
	}
	
	public void createTeam() { // create a team, save it, and add it into team list
		Team tempTeam = new Team(); // create a new team
		
		tempTeam.setTeamName(); // set the name entered as the team name
		tempTeam.setTeamSize(); // set the number of players in a team
		
		// input the player name and his jersey number, and add it into the player list
		tempTeam.registerPlayer(); 
		tempTeam.saveTeam(tempTeam); // save into a text file
		addTeam(tempTeam);
	}
	
	// register each team into the team list
	public void registerTeam() {
		int i = numberOfTeams;
		
		while (i > 0) {
			createTeam();
			i--;
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
			FileWriter outFile = new FileWriter(eventName);
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
			eventName = strLine;
			strLine = br.readLine();
			numberOfTeams = Integer.parseInt(strLine);
			
			// read file line by line
			int i = 0;
			while (i < getEventSize()) {
				Team tempTeam = new Team();
				strLine = br.readLine();
				tempTeam.loadTeam(strLine);
				addTeam(tempTeam);
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
