import java.util.*;

public class LadderAdvancement {

	//read text file and get seq of teams & no of teams
	
	private int CurrNoOfRounds;
	
	private Round[] CurrRounds = new Round[CurrNoOfRounds];
	
	private Round[] NewRounds = new Round[CurrNoOfRounds/2];

	public void createBracket() {
	
		while(CurrNoOfRounds>1) {
		
		int count1 = 0, count2 = 0;
		while(count1 < CurrNoOfRounds) {
			
			NewRounds[count2].addteam(CurrRounds[count1].getWinner());
			count1++;
			NewRounds[count2].addteam(CurrRounds[count1].getWinner());
			
			count2++;
			
			//at every stage can store the data in both array in to a text file 
		}	
				
		CurrRounds = NewRounds;
		CurrNoOfRounds = CurrRounds.length;
		NewRounds = new Round[CurrNoOfRounds/2];
	}
	}
	
	Team winner = CurrRounds[0].getWinner();
	
	public static void main( String[]args ) {
		LadderAdvancement ladder = new LadderAdvancement();
		
	}
}