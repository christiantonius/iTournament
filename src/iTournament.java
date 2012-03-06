import java.io.IOException;

public class iTournament { // for testing purpose
	public static void main(String[] args) {
		 /* Event tempEvent = new Event(); // create a new event
		
		tempEvent.setEventName();
		tempEvent.setNumberOfTeams();
		tempEvent.registerTeam();
		tempEvent.saveEvent(); */
		
		Event tempEvent = new Event();
		try {
			tempEvent.loadEvent("BPL");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tempEvent.printEvent();
	}
}