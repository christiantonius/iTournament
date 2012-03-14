import java.io.*;
import java.util.*;

public class Ladder {
	
	private int CurrNoOfRounds;
	private Round[] CurrRounds, NewRounds;
		
	//Initialise new Ladder system
	public Ladder(String data) {
		readPairings(data);	
		//The new fixtures. Divided by 2 as the number of rounds will be halved
		NewRounds = new Round[CurrNoOfRounds/2];	
	}
	
	//Advance teams till the final round(when current No of Rounds = 1)	
	public void createFixtures() {
		
		//If CurrNoOfRounds = 1, then it is already the final match
		if(CurrNoOfRounds==1)
			System.out.println("It is already the final round");
		
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
		
			saveNewRoundInfo(NewRounds);
			//the new fixtures we just computed becomes the current fixtures
			CurrRounds = NewRounds;
			//Update the number of rounds
			CurrNoOfRounds /= 2;			
			NewRounds = new Round[CurrNoOfRounds/2];	
		}
	}
	
	public void saveWinner(Team t) {
		for(int i=0; i<CurrRounds.length; i++) {
			if(CurrRounds[i].contains(t))
				CurrRounds[i].setWinner(t);
		}
	}
	
	public void saveNewRoundInfo (Round[] newR) {
		
		try{
			FileWriter outFile = new FileWriter("Round of " + 2*newR.length);
			PrintWriter out = new PrintWriter(outFile);
		
			out.printf("%d\n", CurrNoOfRounds);
			for (int i = 0; i < CurrNoOfRounds; i++) {
				//Player tempPlayer = _team.playerList.get(i);l
				out.printf("%s won %s", CurrRounds[i].getWinner().getTeamName(), CurrRounds[i].getLoser().getTeamName());
				out.println();
			}
			out.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

	public void readPairings(String textfile) {
		try{
			  // Open the file that is the first 
			  // command line parameter
			  FileInputStream fstream = new FileInputStream(textfile);
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
			  // Print the content on the console
				  t = new Team();
				  t.loadTeam(strLine);
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
/*
	//main function to test the createFixtures function
	public static void main( String[]args ) {
		
		Round[] InitialRounds = null;
		readTextFile(InitialRounds);		
		LadderAdvancement ladder = new LadderAdvancement(InitialNoOfRounds/2, InitialRounds);
		ladder.createFixtures();
		System.out.println(ladder.CurrRounds[0].getWinner().checkTeamName());		//Print the winner of the final match	
	}*/
}