import java.io.IOException;

public class iTournament { // for testing purpose
	public static void main(String[] args) {
		Event tempEvent = new Event(); // create a new event
		/*
		tempEvent.setEventName();
		tempEvent.setNumberOfTeams();
		tempEvent.registerTeam();
		tempEvent.setNumberOfReferees();
		tempEvent.registerReferee(); */
		tempEvent.saveEvent();
		
		try {
			tempEvent.loadEvent("BPL.ivt"); // load an event (choose BPL or La Liga)
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tempEvent.printEvent();
		
		
	}
}