import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;


public class Event { // class for an event
	String eventName, venue;
	int startTime;
	double registrationFee, eventFund;
	private Vector<Team> teamList;
	private Vector<Referee> refereeList;
	boolean isCreateEventFinish = false;
	int year, month, day, hour, min;	
	
	public Event() { // create an event with an empty team list
		teamList = new Vector<Team>();
		refereeList = new Vector<Referee>();
		eventFund = 0.0;
	}
	
	public void setIsCreateEventFinish() {
		isCreateEventFinish = true;
	}
	
	public void setEventName(String eName) { // set the event name
		eventName = eName;
	}
	
	public void setVenue(String v) { // set the event name
		venue = v;
	}
	
	public String getVenue() { // set the event name
		return venue;
	}
	
	public boolean getIsCreateEventFinish() {
		return isCreateEventFinish;
	}
	
	public void setTimeStart(int y, int mo, int d, int h, int mi) {
		year = y;
		month = mo;
		day = d;
		hour = h;
		min = mi;
	}
	
	public int getYear() { return year;}
	public int getMonth() { return month; }
	public int getDay() { return day;}
	public int getHour() { return hour; }
	public int getMin() { return min; }
	
	public String getFormattedDate() {
		String date = String.format("%02d", day) + "/" + String.format("%02d", month) + "/" + String.format("%02d", year);	
		return date;
	}
	
	public String getFormattedTime() {
		String time = String.format("%02d", hour) + ":" + String.format("%02d", min);
		return time;
	}
	
	public String getEventName() { // return the event name
		return eventName;
	} 
	
	public void setEventFund(double money) { // set the event fund
		eventFund = money;
	}
	
	public double getEventFund() { // return the event fund
		return eventFund;
	}
	
	public void setRegFee(double fee) { // set the event fund
		registrationFee = fee;
	}
	
	public double getRegFee() { // return the registration fee
		return registrationFee;
	}
	
	public double getTotalRegFee() {
		double total = registrationFee * getEventSize();
		return total;
	}
	
	public Vector<Team> getTeamList() { // return the vector of team list
		return teamList;
	}
	
	public Team getTeam(String teamName) {
		Vector<Team> teamList = getTeamList();
		
		for(int i=0; i<teamList.size(); i++) {
			if(teamList.get(i).getTeamName().equals(teamName))
				return teamList.get(i);
		}
		
		return null;
	}
	
	public void removeTeam(String teamName) {
		Team tempTeam = getTeam(teamName);
		
		if(tempTeam != null)
			tempTeam.removeAllPlayer();
		
		else System.out.println("Event.java, public void removeTeam : Team not found.");
		
		teamList.remove(tempTeam);
	}

	public Vector<Referee> getRefereeList() { // return the vector of referee list
		return refereeList;
	}
	
	public int getEventSize() { // return the number of teams in an event
		return teamList.size();
	}
	
	public int getRefSize() { // return the number of referees in an event
		return refereeList.size();
	}
	
	public void addTeam (Team _team) { // add a team into the event
		teamList.add(_team);		
	}
	
	public Referee getReferee(String name, double cost) {
		int noOfReferees = refereeList.size();
		
		for(int i=0; i<noOfReferees; i++) {
			if(refereeList.get(i).getName().equals(name) &&
					refereeList.get(i).getCost() == cost)
				return refereeList.get(i);
		}
		
		return null;
	}
	
	public void addReferee (Referee _referee) { // add a referee into the event
		refereeList.add(_referee);
	}
	
	public void createReferee(String name, double cost) {		
		Referee tempReferee = new Referee();
		tempReferee.setName(name);
		tempReferee.setCost(cost);
		addReferee(tempReferee);
	}
	
	public void removeReferee(String name, double cost) {
		Referee tempReferee = new Referee();
		
		tempReferee = getReferee(name, cost);
		
		if(tempReferee != null)
			refereeList.removeElement(tempReferee);
		
		else
			System.out.println("Event.java, public void removeReferee : Referee not found.");
	}
	
	public void createTeam(String teamname){
		Team tempTeam = new Team(); // create a new team
		
		tempTeam.setTeamName(teamname); // set the name entered as the team name
		addTeam(tempTeam);
	}
	
	/*public void setTeamSize(String teamName, int teamSize) {
		Team tempTeam = getTeam(teamName);
		
		if(tempTeam != null)
			tempTeam.setTeamSize(teamSize);
		
		else System.out.println("Event.java, public void setTeamSize : Team not found.");
	}*/
	
	public void addPlayer(String teamName, String playerName, int jerseyNumber) {
		Team tempTeam = getTeam(teamName);
		
		if(tempTeam != null)
			tempTeam.registerPlayer(playerName, jerseyNumber); 
		
		else System.out.println("Event.java, public void addPlayer : Team not found.");
	}

	public void saveReferee() {
		try{
			File file = new File("data/" + eventName + ".ref");
			FileWriter outFile = new FileWriter(file);
			PrintWriter out = new PrintWriter(outFile);
			
			out.println(refereeList.size());
			
			for (int i = 0; i < refereeList.size(); i++) {
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
			int tempNoOfReferees = Integer.parseInt(strLine); // load the number of referees
			
			// read file line by line
			int i = 0;
			while (i < tempNoOfReferees) {
				Referee tempReferee = new Referee(); // create a dummy referee
				strLine = br.readLine();
				tempReferee.setName(strLine); // set referee name
				strLine = br.readLine();
				tempReferee.setCost(Double.parseDouble(strLine)); //set referee cost
				addReferee(tempReferee);
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
	 * year
	 * month
	 * day
	 * hour
	 * min
	 * number_of_teams
	 * event_fund
	 * registration_fee
	 * next line and onwards:
	 * team_name
	 *********************************************
	 */
	public void saveEvent() {
		try{
			File file = new File("data/" + eventName + ".event");
			FileWriter outFile = new FileWriter(file);
			PrintWriter out = new PrintWriter(outFile);
			
			out.println(eventName);
			out.println(venue);
			out.printf("%d\n%d\n%d\n%d\n%d\n", year, month, day, hour, min);
			out.println(teamList.size());
			out.println(eventFund);
			out.println(registrationFee);
			
			for (int i = 0; i < teamList.size(); i++) {
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
			venue = strLine; // load venue name
			strLine = br.readLine();
			year = Integer.parseInt(strLine);
			strLine = br.readLine();
			month = Integer.parseInt(strLine);
			strLine = br.readLine();
			day = Integer.parseInt(strLine);
			strLine = br.readLine();
			hour = Integer.parseInt(strLine);
			strLine = br.readLine();
			min = Integer.parseInt(strLine);
			strLine = br.readLine();
			int tempNoOfTeams = Integer.parseInt(strLine); // load the number of participating teams
			strLine = br.readLine();
			eventFund = Double.parseDouble(strLine);
			strLine = br.readLine();
			registrationFee = Double.parseDouble(strLine);
			
			// read file line by line
			int i = 0;
			while (i < tempNoOfTeams) {
				Team tempTeam = new Team(); // create a dummy team
				strLine = br.readLine();
				tempTeam.loadTeam(strLine + ".team"); // load the team
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
	
	public void saveAllTeams() {
			for(Team t : teamList) 
				t.saveTeam();	
	}

	
	public void deleteEventFile() {
		String fileName = eventName + ".event";
		deleteFile(fileName);
	}
	
	public void deleteTeamFiles() throws IOException {
		String fileName = "data/" + eventName + ".event";
		FileInputStream fStream = null;		
		
		try{
			fStream = new FileInputStream(fileName);
			
			DataInputStream in = new DataInputStream(fStream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			
			strLine = br.readLine();
			eventName = strLine; // load event name
			
			//skip the following
			br.readLine(); //venue
			br.readLine(); //year
			br.readLine(); //month
			br.readLine(); //day
			br.readLine(); //hour
			br.readLine(); //min

			//read No. of Teams
			strLine = br.readLine();
			int tempNoOfTeams = Integer.parseInt(strLine); // load the number of participating teams
			
			br.readLine();	//skip eventFund
			br.readLine();	//skip regFee
			
			int i = 0;
			while (i < tempNoOfTeams) {
				strLine = br.readLine();
				deleteFile(strLine + ".team");
				i++;
			}
		}
		
		finally 
		{			
			if (fStream != null)							
				fStream.close();					
		}		
	}

	public void deleteRefereeFile() {
		String fileName = eventName + ".ref";
		deleteFile(fileName);		
	}
	
	public void deleteTimingFile() {
		String[] timingList = getFiles(".timings");

		if(timingList.length != 0) {
			for(int i=0; i<timingList.length; i++) {
				if(timingList[i].startsWith(eventName))
					deleteFile(timingList[i]);
			}
		}
	}
	
	public void deleteResultFile() {
		String[] resultList = getFiles(".results");

		if(resultList.length != 0) {
			for(int i=0; i<resultList.length; i++) {
				if(resultList[i].startsWith(eventName))
					deleteFile(resultList[i]);
			}
		}
		
	}
	
	public void deleteRoundFile() {
		String[] roundList = getFiles(".round");

		if(roundList.length != 0) {
			for(int i=0; i<roundList.length; i++) {
				if(roundList[i].startsWith(eventName))
					deleteFile(roundList[i]);
			}
		}
	}
	
	public void deleteLadderFile() {
		String fileName = eventName + ".ladder";
		deleteFile(fileName);		
	}
	
	public void deletePairingFile() {
		String fileName = eventName + ".pairing";
		deleteFile(fileName);		
	}
	
	public void deleteAllEventRelatedFile() throws IOException {
		deleteRefereeFile();
		deleteTeamFiles();
		deleteEventFile();
		deletePairingFile();
		deleteLadderFile();
		deleteRoundFile();
		deleteResultFile();
		deleteTimingFile();
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
	
	private String[] getFiles(String ext) {
		
		ExtensionFilter filter = new ExtensionFilter(ext);
		File dir = new File("data/");

		String[] list = dir.list(filter);
		
		return list;
	}
	

	class ExtensionFilter implements FilenameFilter {
	
		private String extension;
	
		public ExtensionFilter( String extension ) {
			this.extension = extension;             
		}
		public boolean accept(File dir, String name) {
			return (name.endsWith(extension));
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
