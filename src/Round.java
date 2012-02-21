import java.util.*;

public class Round {
	
	private int startTime, duration, fieldNumber;
	private Team winner;
	private Vector<Team> teams = new Vector <Team>();
	
	public Round() {
		
	}
	
	public void addteam( Team t ) {
		if(teams.size() < 3) 
			teams.add(t);
	}

	public Team getWinner() {
		return winner;
	}
	
	public int getStartTime() {
		return startTime;
	}
	
	public int getduration() {
		return duration;
	}
	
	public int getfieldnumber() {
		return fieldNumber;
	}
}
