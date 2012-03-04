import java.util.*;

public class LadderAdvancement {
	
	private int CurrNoOfRounds;
	private Round[] CurrRounds; 
	private Round[] NewRounds; 
	
	//initialize new LadderPairings system 
	public LadderAdvancement() {
		CurrRounds = new Round[4]; 		//The current fixtures
		NewRounds = new Round[4/2];		//The new fixtures. Divided by 2 as the number of rounds will be halved
	}
	
	//Advance teams till the final round(when current No of Rounds = 1)	
	public void createFixtures() {
	
		while(CurrNoOfRounds>1) { //If CurrNoOfRounds = 1, then it is already the final match
		
			int currCount = 0, newCount = 0;
			Round newRound = new Round();		//create a temp round to store the winners from the current
			
			while(currCount < CurrNoOfRounds) {
		
				newRound.addTeam(CurrRounds[currCount].getWinner());	//Add 1st match winner of 1st round to 1st match of 2nd round
				currCount++;
				newRound.addTeam(CurrRounds[currCount].getWinner());	//Add 2nd match winner of 1st round to 1st match of 2nd round
				newRound.setWinner(newRound.getFirstTeam());			//set winner for simulation
				NewRounds[newCount] = newRound;							
				newCount++;												//Iterate through the array
				currCount++;
			}	//Now the new fixtures for one round have been created
		
		CurrRounds = NewRounds;						//the new fixtures we just computed becomes the current fixtures 
		CurrNoOfRounds = CurrRounds.length;			//Update the number of rounds
		NewRounds = new Round[CurrNoOfRounds/2];	
		}
	}
	
	//main function to test the createFixtures function
	public static void main( String[]args ) {
		
		LadderAdvancement ladder = new LadderAdvancement();
		Scanner sc = new Scanner(System.in);
		
		Round rnd;
		Team t1, t2;
		int NoOfTeamsFirstRound = sc.nextInt();		//read in amount of teams
		sc.nextLine();
		ladder.CurrNoOfRounds = NoOfTeamsFirstRound/2;
		
		for(int i=0; i<ladder.CurrNoOfRounds; i++) {
			
			rnd = new Round();
			t1 = new Team();
			t2 = new Team();
			
			t1.setTeamName(sc.nextLine());
			t2.setTeamName(sc.nextLine());
			
			rnd.addTeam(t1);
			rnd.addTeam(t2);
			rnd.setWinner(t1);
			ladder.CurrRounds[i] = rnd;				//Fill up the array for use in the createFixtures Function
		}
		
		ladder.createFixtures();
		System.out.println(ladder.CurrRounds[0].getWinner().checkTeamName());		//Print the winner of the final match
	}
}