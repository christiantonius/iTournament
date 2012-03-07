import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Ladder {
	
	private int CurrNoOfRounds;
	private Round[] CurrRounds, NewRounds;
		
	//Initialise new Ladder system
	//The String pairings is the name of the text file which contains the Pairings which have been randomly created by the class pair
	//The data is pairings.txt is used to create the array of Rounds, CurrRounds
	public Ladder(String pairings) {
		readPairings(pairings);	
		//The new fixtures. Divided by 2 as the number of rounds will be halved
		NewRounds = new Round[CurrNoOfRounds/2];
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
	
	//This function must be called every time the winner has been declared and needs to be input into iTournament
	public void saveWinner(String teamName) {
		for(int i=0; i<CurrRounds.length; i++) {
			if(CurrRounds[i].contains(teamName))
				CurrRounds[i].setWinner(teamName);
		}
	}
	
	//This function saves the results of the CurrentRounds before the data is overwritten by NewRounds
	//It saves the total number of teams in this round and the winner and loser for each round
	public void saveCurrRoundResults () {
		
		//Create a text file and write data
		try{
			FileWriter outFile = new FileWriter("Round of " + CurrRounds.length);
			PrintWriter out = new PrintWriter(outFile);
			
			out.printf("%d\n", CurrNoOfRounds);
			for (int i = 0; i < CurrNoOfRounds; i++) {
				out.printf("%s\nwon\n%s\n", CurrRounds[i].getWinner().getTeamName(), CurrRounds[i].getLoser().getTeamName());
				out.println();
			}
			out.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	//This function searches for "textfile".txt and reads the data inside to initialize the variables
	//CurrNoOfRounds and CurrRounds
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
}