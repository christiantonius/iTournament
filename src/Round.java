import java.util.Vector;

public class Round {
	
	//Attributes as listed in Domain Model
	private int[] startTime, endTime;
	private int duration, fieldNumber, winnerGoals, loserGoals;
	private Team winner, loser;
	private Vector<Team> teams = new Vector <Team>();
	
	public Round() {
	}
	
	//set the starting time for a round
	public void setStartTime(int y, int m, int d, int h, int min) {
		startTime = new int[5];
		startTime[0] = y;
		startTime[1] = m;
		startTime[2] = d;
		startTime[3] = h;
		startTime[4] = min;
		duration = 90;
		//set the endTime
		this.setEndTime(y, m, d, h, min);
	}
	
	//set the ending time for a round
	public void setEndTime(int y, int m, int d, int h, int min) {
		
		min += 90;
		if(min>59) {
			min -=60;
			h++;
		}
		
		if(h>23) {
			h -=24;
			d++;
		}
		
		endTime = new int[5];
		endTime[0] = y;
		endTime[1] = m;
		endTime[2] = d;
		endTime[3] = h;
		endTime[4] = min;
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
	
	public void setWinner( String str, int w, int l ) {
		
		Team t = getTeam(str);
		winner = t;
		int winnerIndex = teams.indexOf(t);
		if(winnerIndex ==1)
			loser = teams.elementAt(0);
		else
			loser = teams.elementAt(1);
		
		winnerGoals = w;
		winner.setScore(w, l);
		
		loserGoals = l;
		loser.setScore(w, l);
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
	
	public int getWinScore() {
		return winnerGoals;
	}
	
	public int getLosScore() {
		return loserGoals;
	}
	
	public Team getFirstTeam() {
		return teams.firstElement();
	}
	
	public Team getSecondTeam() {
		return teams.elementAt(1);
	}
	
	//return a string that states the starting time and date of a match in a nice format
	public String getStartTime() {
		String timing = "Date: " + String.format("%02d", startTime[2]) + "/" + String.format("%02d", startTime[1]) + "/" + String.format("%02d",startTime[0]) + ", Time: " + String.format("%02d",startTime[3]) + ":" + String.format("%02d",startTime[4]);
		return timing;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public int getFieldnumber() {
		return fieldNumber; 
	}
}