import java.io.*;
import java.util.Vector;

public class Team { // class for a team
	String teamName;
	Team opponent;
	int goalsFor, goalsAgainst; 
	private Vector<Player> playerList; // has list of players, in a form of vector
	
	public Team () { // create a team with empty player list
		playerList = new Vector<Player>(); 
	}
	
	public void setTeamName (String name) { // set the team name
		teamName = name;
	}
	
	public String checkTeamName() { // return the team name
		return teamName;
	}
	
	public Vector<Player> checkTeamList() { // return the vector of player list
		return playerList;
	}
	
	public void setOpponent (Team otherTeam) { // set the opponent for this team
		opponent = otherTeam;
	}
	
	public int teamSize() { // return the number of players in the team
		return playerList.size();
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
	
	// save the team information into a text file
	// format: 
	// first line: team_name, team_size
	// next line and onwards: player_name, player_number 
	public void saveTeam (String name, Team _team) { 
		try{
			FileWriter outFile = new FileWriter(name);
			PrintWriter out = new PrintWriter(outFile);
		
			out.printf("%s %d\n", _team.checkTeamName(), _team.teamSize());
			for (int i = 0; i < _team.teamSize(); i++) {
				Player tempPlayer = _team.playerList.get(i);
				out.printf("%s %d", tempPlayer.checkName(), tempPlayer.checkNumber());
				if (tempPlayer.checkCaptain())
					out.print(" (c)");
				out.println();
			}
			out.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
}
