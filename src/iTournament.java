import java.io.IOException;


public class iTournament { // for testing purpose
	public static void main(String[] args) throws IOException {
		// Event tempEvent = new Event(); // create a new event
		/*
		tempEvent.setEventName();
		tempEvent.setNumberOfTeams();
		tempEvent.registerTeam();
		tempEvent.setNumberOfReferees();
		tempEvent.registerReferee(); */
		// tempEvent.setTimeStart();
		// tempEvent.saveEvent();
				
		Venue Old_Trafford = new Venue("Old Trafford");
		Event A = new Event();
		A.loadEvent("Premier League.event" );
		Old_Trafford.registerEvent(A);
		Event B = new Event();
		B.loadEvent("IPL.event");
		Old_Trafford.registerEvent(B);
		Old_Trafford.saveVenue();
			
	}
}