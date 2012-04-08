import java.io.*;
import java.util.Vector;

public class Team { // class for a team
	String teamName;
	Team opponent;
	int teamSize, goalsFor, goalsAgainst, gamesWon; 
	private Vector<Player> playerList; // has list of players, in a form of vector
	
	public Team () { // create a team with empty player list
		playerList = new Vector<Player>(); 
	}
	
	public int compareTo(Team x) {
		if( this.getGamesWon() < x.getGamesWon() )
			return -1;
		
		if( this.getGamesWon() == x.getGamesWon() )
			return 0;
		
		else
			return 1;
	}	
	
	public int getGamesWon() {
		return gamesWon;
	}
	
	public void incGamesWon() {
		gamesWon++;
	}
	
	public void setTeamName (String name){ // set the team name
		teamName = name;
	}
	
	public String getTeamName() { // return the team name
		return teamName;
	}
	
	public int getTeamSize() {
		return playerList.size();
	}
	
	public Vector<Player> getTeamList() { // return the vector of player list
		return playerList;
	}
	
	public String[] getAllPlayerNames() {
		String[] playerNames = new String[playerList.size()];

		for(int i=0; i<playerList.size(); i++) 
			playerNames[i] = playerList.get(i).getName();
	
		return playerNames;
	}
	
	public int[] getAllJerseyNumbers() {
		int[] jerseyNumber = new int[playerList.size()];
		
		for(int i=0; i<playerList.size(); i++) 
			jerseyNumber[i] = playerList.get(i).getNumber();
		
		return jerseyNumber;
	}
	
	public void setOpponent (Team otherTeam) { // set the opponent for this team
		opponent = otherTeam;
	}
	
	public void setScore (int first, int second) { // set the goals scored and conceded
		goalsFor = first;
		goalsAgainst = second;
	}
	
	public void addPlayer (Player _player) { // add a player to this team
		 playerList.add(_player);
	}
	
	public void setTeamCaptain (Player captain) { // set a player as the captain for this team
		captain.setCaptain();
	}
	
	public void registerPlayer(String name, int number) {
		Player tempPlayer = new Player(); // create a new player
		
		tempPlayer.setName(name); // store the player's name
		tempPlayer.setJerseyNumber(number); // store the player's jersey number
		
		addPlayer(tempPlayer); // add the player into the team
	}
	
	public void removePlayer(int jerseyNumber) {
		for(int i=0; i<playerList.size(); i++) {
			if(playerList.get(i).getNumber() == jerseyNumber)
				playerList.remove(i);		
		}
	}
	
	public void removeAllPlayer() {
		playerList.clear();
	}
	
	/************************************************
	 * save the team information into a text file
	 * format: 
	 * team_name 
	 * team_size
	 * next line and onwards: 
	 * player_name
	 * player_number
	 ************************************************  
	 */
	public void saveTeam () { 
		try{
			File file = new File("data/" + teamName + ".team");
			FileWriter outFile = new FileWriter(file);
			PrintWriter out = new PrintWriter(outFile);
		
			out.printf("%s\n", teamName);
			out.printf("%d\n", playerList.size());
			for (int i = 0; i < playerList.size(); i++) {
				Player tempPlayer = playerList.get(i);
				out.printf("%s\n", tempPlayer.getName());
				out.printf("%d", tempPlayer.getNumber());
				if (tempPlayer.getCaptain())
					out.print(" (c)");
				out.println();
			}
			out.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	// load a team from a text file
	public void loadTeam (String fileName) throws IOException {
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
			teamName = strLine; // load team name
			strLine = br.readLine();
			int tempTeamSize = Integer.parseInt(strLine); // load team size
			
			// read file line by line
			int i = 0;
			while (i < tempTeamSize) {
				Player tempPlayer = new Player(); // create a dummy player
				strLine = br.readLine();
				tempPlayer.setName(strLine); // set the player name
				strLine = br.readLine();
				tempPlayer.setJerseyNumber(Integer.parseInt(strLine)); // set the player number
				addPlayer(tempPlayer); // add the player to the team's player list
				i++; // next player
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
}
