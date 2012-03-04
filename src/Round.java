import java.util.*;

public class Round {
	
	//Attributes as listed in Domain Model
	private int startTime, duration, fieldNumber;
	private Team winner;
	private Vector<Team> teams = new Vector <Team>();
	
	public Round() {
		
	}
	
	public void addTeam( Team t ) {
		if(teams.size() < 2) 	//For soccer, 1 round should have at most 2 teams 
			teams.add(t);
	}
	
	public void setWinner( Team t ) {
		winner = t;
	}
	
	public Team getWinner() {
		return winner;
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