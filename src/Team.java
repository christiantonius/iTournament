import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class Team { // class for a team
	String teamName;
	Team opponent;
	int teamSize, goalsFor, goalsAgainst; 
	private Vector<Player> playerList; // has list of players, in a form of vector
	
	public Team () { // create a team with empty player list
		playerList = new Vector<Player>(); 
	}
	
	public void setTeamName (){ // set the team name
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter team name:");
		teamName = sc.nextLine();
	}
	
	public void setTeamSize() { // set the team size
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter team size:");
		teamSize = sc.nextInt();
	}
	
	public String getTeamName() { // return the team name
		return teamName;
	}
	
	public int getTeamSize() { // return the team size
		return teamSize;
	}
	
	public Vector<Player> getTeamList() { // return the vector of player list
		return playerList;
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
	
	public void registerPlayer() {
		int i = teamSize;
		while (i > 0) {
			Player tempPlayer = new Player(); // create a new player
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter player name:");
			String tempName = sc.nextLine();
			tempPlayer.setName(tempName); // store the player's name
			
			System.out.println("Enter player number:");
			int tempNumber = sc.nextInt();
			tempPlayer.setJerseyNumber(tempNumber); // store the player's jersey number
			
			addPlayer(tempPlayer); // add the player into the team
			i--;
		}
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
	public void saveTeam (Team _team) { 
		try{
			FileWriter outFile = new FileWriter(teamName);
			PrintWriter out = new PrintWriter(outFile);
		
			out.printf("%s\n", _team.getTeamName());
			out.printf("%d\n", _team.getTeamSize());
			for (int i = 0; i < _team.getTeamSize(); i++) {
				Player tempPlayer = _team.playerList.get(i);
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
			teamName = strLine;
			strLine = br.readLine();
			teamSize = Integer.parseInt(strLine);
			
			// read file line by line
			int i = 0;
			while (i < getTeamSize()) {
				Player tempPlayer = new Player();
				strLine = br.readLine();
				tempPlayer.setName(strLine);
				strLine = br.readLine();
				tempPlayer.setJerseyNumber(Integer.parseInt(strLine));
				addPlayer(tempPlayer);
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
}
