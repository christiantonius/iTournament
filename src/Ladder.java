import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Ladder {
	
	private int firstNoOfRounds, CurrNoOfRounds, roundItr;
	private Round[] CurrRounds, NewRounds;
	private int[] firstTime; //the timing for the first ever match in iTournament
	private String eventName;
	
	//Initialise new Ladder system
	//The String pairings is the name of the text file which contains the Pairings which have been randomly created by the class pair
	//The data is pairings.txt is used to create the array of Rounds, CurrRounds
	public Ladder(String pairings) {
		eventName = pairings;
		readPairings(pairings);	
		//The new fixtures. Divided by 2 as the number of rounds will be halved
		NewRounds = new Round[CurrNoOfRounds/2];
		firstNoOfRounds = CurrNoOfRounds;
	}
	
	public Ladder() {
	}
	
	public int getCurrNoOfRounds() {
		return CurrNoOfRounds;
	}
	
	public int getFirstNoOfRounds() {
		return firstNoOfRounds;
	}
	
	public int getRoundItr() {
		return roundItr;
	}
	
	public String getFirstTeamOfTheMatch(int i) {
		return CurrRounds[i].getFirstTeam().getTeamName();
	}
	
	public String getSecondTeamOfTheMatch(int i) {
		return CurrRounds[i].getSecondTeam().getTeamName();
	}
	
	public String getMatchDate(int i) {
		return CurrRounds[i].getFormattedDate();
	}
	
	public String getMatchTime(int i) {
		return CurrRounds[i].getFormattedTime();
	}
	
	public String getWinner(int index) {
		Team winner = CurrRounds[index].getWinner();
		
		if(winner != null)
			return winner.getTeamName();
		
		else return null;
	}
	
	public int[] getScores(int index) {
		int[] scores = new int[2];
		scores[0] = CurrRounds[index].getWinScore();
		scores[1] = CurrRounds[index].getLosScore();
		
		return scores;
	}
	
	public String getEventName() {
		return eventName;
	}
	
	//Advance the winners from the current rounds to the next
	public void createFixtures() {
		
		//If CurrNoOfRounds = 1, then it is already the final match
		if(CurrNoOfRounds==1)
			//System.out.println(CurrRounds[0].getWinner().getTeamName() + " has won the final round");
			saveCurrRoundResults();
		
		else { 
			int currCount = 0, newCount = 0;
			//create a temp round to store the winners from the current
			Round newRound;
			
			while(currCount < CurrNoOfRounds) {
				newRound = new Round();
				//Add 1st match winner of 1st round to 1st match of 2nd round
				newRound.addTeam(CurrRounds[currCount].getWinner());
				currCount++;
				//Add 2nd match winner of 1st round to 1st match of 2nd round
				newRound.addTeam(CurrRounds[currCount].getWinner());					
				NewRounds[newCount] = newRound;
				//Iterate through the array
				newCount++;												
				currCount++;
			}	//Now the new fixtures for one round have been created
			
			
			saveCurrRoundResults();
			//the new fixtures we just computed becomes the current fixtures
			CurrRounds = NewRounds;
			//Update the number of rounds
			CurrNoOfRounds /= 2;			
			NewRounds = new Round[CurrNoOfRounds/2];	
		}
	}
	
	//Use this funciton to save the timing for the first ever game in iTournament
	//All other games' timing will be incremented from this first game's timing
	public void setFirstTime(int year, int month, int date, int hour, int min) {
		firstTime = new int[5];
		firstTime[0] = year;
		firstTime[1] = month;
		firstTime[2] = date;
		firstTime[3] = hour;
		firstTime[4] = min;
	}
	
	//Based on the timing from the most recent match, set the timing for the all the next matches, in the present currRounds[] array
	//There will be 15 min break between each match, and the match itself will be 90 min
	public void setSchedule() {
		
		int year, month, date, hour, min;
	
		year = firstTime[0];
		month = firstTime[1];
		date = firstTime[2];
		hour = firstTime[3];
		min = firstTime[4];
		
		
		for(int i=0; i<CurrNoOfRounds; i++) {
			CurrRounds[i].setStartTime(year, month, date, hour, min);
			// 90 min + 15 min = length of one match + length of 1 break
			min += 105;			
			
			//Update the clock, as the next hour arrives
			while(min>59) {		
				min -=60;
				hour++;
			}
			
			//After 10:00pm, call it a day
			//Next match will be on 08:00am, the next day
			if(hour>21) {
				hour = 8;
				min = 0;
				date++;
			}
			
			//Update the month assuming a month has 30 days
			//can be further refined to take into account months with 31 days
			//Or.....we can just set the event date to be somewhere in the middle of the month, when creating event
			if(date>30) {
				date = 1;
				month++;
			}
			
			//Update the year, if you want to organize a tournament on New Year's Eve...
			if(month>12){
				month = 1;
				year++;
			}
		}
		
		//Update the time counter 
		firstTime[0] = year;
		firstTime[1] = month;
		firstTime[2] = date;
		firstTime[3] = hour;
		firstTime[4] = min;
	}
	
	//
	public void printSchedule() {
		Round rnd;
		
		//////////////////////////////
		///////print to console///////
		/////////////////////////////
		
		/*
		System.out.printf("%d Rounds\n\n", CurrNoOfRounds);
		for(int i=0; i<CurrRounds.length; i++) {
			rnd = CurrRounds[i];
			System.out.println("Round " + (i+1) + " at:");
			System.out.println(rnd.getStartTime());
			System.out.println();
		}
		System.out.println();
		*/
		
		///////////////////////////////
		/////print to textfile/////////
		///////////////////////////////
		
		try{
			//the file created is a .timings file
			FileWriter outFile = new FileWriter("data/" + eventName + " - Round of " + CurrRounds.length + ".timings");
			PrintWriter out = new PrintWriter(outFile);
			
			out.printf("%d\n\n", CurrNoOfRounds);
			
			for (int i = 0; i < CurrNoOfRounds; i++) {
				rnd = CurrRounds[i];
				out.println(rnd.getFormattedDate());
				out.println(rnd.getFormattedTime());
				out.println();
			}
			out.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	//This function must be called every time the winner has been declared and needs to be input into iTournament
	public void saveWinner(String teamName, int winnerScore, int loserScore) {
		 
		for(int i=0; i<CurrRounds.length; i++) {
			if(CurrRounds[i].contains(teamName))
				//if(CurrRounds[i].getWinner()!=null)
					CurrRounds[i].setWinner(teamName, winnerScore, loserScore);
				//else
					//System.out.println("Existing winner for this round already exists");
		}
	}
	
	//This function saves the results of the CurrentRounds before the data is overwritten by NewRounds
	//It saves the total number of teams in this round and the winner and loser for each round
	public void saveCurrRoundResults () {
		
		//Create a text file and write data
		try{
			//The file created is a .results file
			FileWriter outFile = new FileWriter("data/" + eventName + " - Round of " + CurrRounds.length + ".results");
			PrintWriter out = new PrintWriter(outFile);
			
			out.printf("%d\n", CurrNoOfRounds);
			Round rnd;
			for (int i = 0; i < CurrNoOfRounds; i++) {
				rnd = CurrRounds[i];
				out.printf("%s\nwon\n%s\n%d\n%d\n", rnd.getWinner().getTeamName(), rnd.getLoser().getTeamName(), rnd.getWinScore(), rnd.getLosScore());
				out.println();
			}
			out.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	//saves the data in current ladder object
	//to save the round array
	//		-Round.java has has saveRound.
	//		-Go through CurrRounds[], and invoke save Round for each round in this array
	public void saveLadder() {
		try{
			//The file created is a .ladder file
			//FileWriter outFile = new FileWriter("data/" + eventName + " - Round of " + CurrRounds.length + ".ladder");
			
			FileWriter outFile = new FileWriter("data/" + eventName + ".ladder");
			PrintWriter out = new PrintWriter(outFile);
			
			out.println(eventName);
			out.println(CurrNoOfRounds);
			out.println();
			
			for(int i=0; i<5; i++) {
				out.print(firstTime[i] + " ");
			}
			out.println();
			
			for (int i = 0; i < CurrNoOfRounds; i++) {
				CurrRounds[i].saveRound(eventName, CurrNoOfRounds, 1+i, false);
			}
			
			out.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	//search for eventname.ladder and create a ladder object based from data inside
	//to create CurrRounds[], search for each .ROUND file generated during saveLadder() and recreate the rounds
	public void loadLadder(String name) {
		
		FileInputStream fStream = null;		
		
		// Begin a block of code that handles exceptions
		try{
			// Open the file as a stream
			//fStream = new FileInputStream("data/" + name + " - Round of " + num + ".ladder");
			
			fStream = new FileInputStream("data/" + name + ".ladder");
			
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fStream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			
			strLine = br.readLine();
			eventName = strLine;
			
			strLine = br.readLine();
			CurrNoOfRounds = Integer.parseInt(strLine);
			
			br.readLine();
			strLine = br.readLine();
			String[] str = strLine.split(" ");
			
			firstTime = new int[5];
			for(int i=0; i<5; i++) {
				firstTime[i] = Integer.parseInt(str[i]);
			}
			
			Round rnd;
			CurrRounds = new Round[CurrNoOfRounds];
			NewRounds = new Round[CurrNoOfRounds/2];
			for(int i=0; i<CurrNoOfRounds; i++) {
				rnd = new Round();
				rnd.loadRound(eventName, CurrNoOfRounds, 1+i, false);
				CurrRounds[i] = rnd;
			}
		}
		
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
public void loadLadder(String name, int c) {
		
		FileInputStream fStream = null;		
		
		// Begin a block of code that handles exceptions
		try{
			// Open the file as a stream
			//fStream = new FileInputStream("data/" + name + " - Round of " + num + ".ladder");
			
			fStream = new FileInputStream("data/" + name + ".ladder");
			
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fStream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			
			strLine = br.readLine();
			eventName = strLine;
			
			strLine = br.readLine();
			CurrNoOfRounds = c;
			
			br.readLine();
			strLine = br.readLine();
			String[] str = strLine.split(" ");
			
			firstTime = new int[5];
			for(int i=0; i<5; i++) {
				firstTime[i] = Integer.parseInt(str[i]);
			}
			
			Round rnd;
			CurrRounds = new Round[CurrNoOfRounds];
			NewRounds = new Round[CurrNoOfRounds/2];
			for(int i=0; i<CurrNoOfRounds; i++) {
				rnd = new Round();
				rnd.loadRound(eventName, CurrNoOfRounds, 1+i, false);
				CurrRounds[i] = rnd;
			}
		}
		
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	//This function searches for "pairing".pairing and reads the data inside to initialize the variables
	//CurrNoOfRounds and CurrRounds
	public void readPairings(String pairing) {
		try{
			  // Open the file that is the first 
			  // command line parameter
			
			  String paringFile = "data/" + pairing + ".pairing";
			  FileInputStream fstream = new FileInputStream(paringFile);
			  // Get the object of DataInputStream
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  CurrNoOfRounds = Integer.parseInt(br.readLine())/2;
			  CurrRounds = new Round[CurrNoOfRounds];
			  Round rnd = new Round();
			  Team t;
			  int count=0;
			  //Read File Line By Line
			  while ((strLine = br.readLine()) != null)   {
				  t = new Team();
				  t.loadTeam(strLine + ".team");
				  rnd.addTeam(t);
				  if(rnd.getSize()==2) {
					  CurrRounds[count] = rnd;
					  count++;
					  rnd = new Round();
				  }
			  }
			  //Close the input stream
			  in.close();
			    }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
		}
	}
}