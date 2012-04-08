import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Vector;

public class Round {
	
	//Attributes as listed in Domain Model
	private int[] startTime, endTime;
	private int duration, fieldNumber, winnerGoals=0, loserGoals=0;
	private Team winner=null, loser=null;
	private Vector<Team> teams = new Vector <Team>();
	
	public Round() {
	}
	
	//for RoundRobin roundof means round Iteration
	public void saveRound(String eventName, int roundOf, int num, boolean isRR) {
		try{
			
			//The file created is a .round file
			FileWriter outFile;
			
			if(isRR) {
				outFile = new FileWriter("data/" + eventName + " - Round Iter " + roundOf + " - Num " + num + ".round");
			}
			else {
				outFile = new FileWriter("data/" + eventName + " - Round of " + roundOf + " - Num " + num + ".round");
			}
			
			PrintWriter out = new PrintWriter(outFile);
			out.println(getFirstTeam().getTeamName());
			out.println(getSecondTeam().getTeamName());
			out.println();
			
			if(winner!=null) {
				out.println(winner.getTeamName());
				out.println("won");
				out.println(loser.getTeamName());
				out.println(winnerGoals);
				out.println(loserGoals);
				out.println();
			}
			
			else {
				out.println("null");
				out.println("won");
				out.println("null");
				out.println(0);
				out.println(0);
				out.println();
			}
			
		
			
			out.println(fieldNumber);
			out.println();
			
			if(startTime!=null) {
				for(int i=0; i<5; i++) {
					out.print(startTime[i] + " ");
				}
			}
			
			else
				System.out.println("null");
			
			out.println();
			
			out.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	//for RoundRobin roundof means round Iteration
	public void loadRound(String eventName, int roundOf, int num, boolean isRR) {
		
		FileInputStream fStream = null;		
		
		// Begin a block of code that handles exceptions
		try{
			// Open the file as a stream
			if(isRR) {
				fStream = new FileInputStream("data/" + eventName + " - Round Iter " + roundOf + " - Num " + num + ".round");
			}	
			else {
				fStream = new FileInputStream("data/" + eventName + " - Round of " + roundOf + " - Num " + num + ".round");
			}
			
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fStream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			Team team = new Team();
			
			strLine = br.readLine();
			team.loadTeam(strLine + ".team");
			teams.add(team);
			
			strLine = br.readLine();
			team = new Team();
			team.loadTeam(strLine + ".team");
			teams.add(team);
			
			br.readLine();
			strLine = br.readLine();
			if(!strLine.matches("null")) {
				team = new Team();
				team.loadTeam(strLine + ".team");
				winner = team;
			}
			
			br.readLine();
			strLine = br.readLine();
			if(!strLine.matches("null")) {
				team = new Team();
				team.loadTeam(strLine + ".team");
				loser = team;
			}
			
			strLine = br.readLine();
			winnerGoals = Integer.parseInt(strLine);
			
			strLine = br.readLine();
			loserGoals = Integer.parseInt(strLine);
			
			br.readLine();
			strLine = br.readLine();
			fieldNumber = Integer.parseInt(strLine);
			
			br.readLine();
			strLine = br.readLine();
			String[] str = strLine.split(" ");
			
			if(!strLine.matches("null")) {
				startTime = new int[5];
				for(int i=0; i<5; i++) {
					startTime[i] = Integer.parseInt(str[i]);
				}
			}
		}
		
		catch (IOException e){
			e.printStackTrace();
		}
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
		winner.incGamesWon();
		
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
	
	public String getFormattedDate() {
		String date = String.format("%02d", startTime[2]) + "/" + String.format("%02d", startTime[1]) + "/" + String.format("%02d",startTime[0]);	
		return date;
	}
	
	public String getFormattedTime() {
		String time = String.format("%02d",startTime[3]) + ":" + String.format("%02d",startTime[4]);
		return time;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public int getFieldnumber() {
		return fieldNumber; 
	}
}