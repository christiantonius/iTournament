
//This class tests all the features found in Ladder.java
//It requires Event.java and Team.java to work
public class testLadder {

	public static void main(String[]args) {
		
		//create new event for simulation
		newEvent();
		
		//initialize a new ladder
		Ladder ldr = new Ladder("TestData - 8 Teams.txt");
		
		//save 4 winners out of the 8 matches for simulation
		ldr.saveWinner("Barcelona");
		ldr.saveWinner("Real Madrid");
		ldr.saveWinner("Arsenal");
		ldr.saveWinner("AC Milan");
		
		//create the next pairings which will have 4 matches(rounds)
		ldr.createFixtures(); 
		
		//save 2 winners out of the 4 matches for simulation
		ldr.saveWinner("Barcelona");
		ldr.saveWinner("AC Milan");
		
		//create the next pairings which will have 2 matches(rounds)
		ldr.createFixtures();
		
		//save 1 winner out of the 2 matches for simulation
		ldr.saveWinner("Barcelona");
		
		//create the next pairings which will have 0 matches(rounds)
		//But this is not necessary for 0 rounds
		//The winner of the final round will simply be printed
		ldr.createFixtures();
		
	}
	
	//Create a new event for simultation purpose.
	//Enter 8 teams, in the order given in "TestData - 8 teams"
	//This will create a new Event with 8 Team object for testing the ladder
	public static void newEvent() {
		
		Event tempEvent = new Event(); // create a new event
		tempEvent.setEventName();
		tempEvent.setNumberOfTeams();
		tempEvent.registerTeam();
		tempEvent.saveEvent(); 
	}
}