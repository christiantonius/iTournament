
//This class tests all the features found in Ladder.java
//It requires Event.java and Team.java to work
public class testLadder {

	public static void main(String[]args) {
		
		//create new event for simulation
		//newEvent();
		
		//initialize a new ladder
		Ladder ldr = new Ladder("TestData - 16 Teams.txt");
		
		ldr.setFirstTime(12, 12, 30, 8, 0);
		ldr.setSchedule();
		ldr.printSchedule();
		
		//save 8 winners out of the 16 teams for simulation
		ldr.saveWinner("Barcelona", 3, 1);
		ldr.saveWinner("Real Madrid", 2, 1);
		ldr.saveWinner("Arsenal", 5, 0);
		ldr.saveWinner("AC Milan", 7, 2);
		ldr.saveWinner("Fulham", 2, 1);
		ldr.saveWinner("Everton", 1, 0);
		ldr.saveWinner("Birmingham", 3, 2);
		ldr.saveWinner("Newcastle United", 1, 0);
		
		//create the next pairings which will have 4 matches(rounds)
		ldr.createFixtures(); 		
		ldr.setSchedule();
		ldr.printSchedule();
		
		//save 4 winners out of the 8 teams for simulation
		ldr.saveWinner("Barcelona", 4, 2);
		ldr.saveWinner("AC Milan", 3, 1);
		ldr.saveWinner("Fulham", 1, 0);
		ldr.saveWinner("Birmingham", 2, 1);
		
		//create the next pairings which will have 2 matches(rounds)
		ldr.createFixtures();
		ldr.setSchedule();
		ldr.printSchedule();
		
		//save 2 winner out of the 4 matches for simulation
		ldr.saveWinner("Barcelona", 2, 0);
		ldr.saveWinner("Fulham", 1, 0);
		
		//create the next pairings which will have 2 matches(rounds)
		ldr.createFixtures();
		ldr.setSchedule();
		ldr.printSchedule();
		
		//save 1 winner out of the 2 matches for simulation
		ldr.saveWinner("Barcelona", 3, 1);
		
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