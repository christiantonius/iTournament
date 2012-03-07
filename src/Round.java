import java.util.Vector;

public class Round {
	
	//Attributes as listed in Domain Model
	private int startTime, duration, fieldNumber;
	private Team winner, loser;
	private Vector<Team> teams = new Vector <Team>();
	
	public Round() {
		
	}
	
	public void addTeam( Team t ) {
		if(teams.size() < 2) 	//For soccer, 1 round should have at most 2 teams 
			teams.add(t);
	}
	
	public boolean contains(String str) {
	
		if(getTeam(str)!=null)
			return true;
		
		else return false;
	}
	
	public Team getTeam(String str) {
		
		for(int i=0; i<teams.size(); i++) 
			if(teams.elementAt(i).getTeamName().equals(str))
				return teams.elementAt(i);
		
		//assume input is correct
		return null;
	}
	
	public void setWinner( String str ) {
		
		Team t = getTeam(str);
		winner = t;
		int winnerIndex = teams.indexOf(t);
		if(winnerIndex ==1)
			loser = teams.elementAt(0);
		else
			loser = teams.elementAt(1);
	}
	
	public int getSize() {
		return teams.size();
	}
	
	public Team getWinner() {
		return winner;
	}
	
	public Team getLoser() {
		return loser;
	}
	
	public Team getFirstTeam() {
		return teams.firstElement();
	}
	
	public Team getSecondTeam() {
		return teams.elementAt(1);
	}
	
	public int getStartTime() {
		return startTime;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public int getFieldnumber() {
		return fieldNumber;
	}
}