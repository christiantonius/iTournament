import java.io.*;
import java.util.Vector;

public class Team {
	String teamName;
	Team opponent;
	int goalsFor, goalsAgainst;
	private Vector<Player> playerList;
	
	public Team () {
		playerList = new Vector<Player>(); 
	}
	
	public void setTeamName (String name) {
		teamName = name;
	}
	
	public String checkTeamName() {
		return teamName;
	}
	
	public Vector<Player> checkTeamList() {
		return playerList;
	}
	
	public void setOpponent (Team otherTeam) {
		opponent = otherTeam;
	}
	
	public int teamSize() {
		return playerList.size();
	}
	
	public void setScore (int first, int second) {
		goalsFor = first;
		goalsAgainst = second;
	}
	
	public void addPlayer (Player _player) {
		 playerList.add(_player);
	}
	
	public void setTeamCaptain (Player captain) {
		captain.setCaptain();
	}
	
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
