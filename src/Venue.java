import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Vector;


public class Venue {
	String venueName;
	private Vector<Event> eventList;
	HashMap<String, Integer> venueHash;

	public Venue(String name) { // create a venue with empty event list
		venueName = name;
		eventList = new Vector<Event>();
		venueHash = new HashMap<String, Integer>();
	}

	public void addEvent(Event _event) { // book the venue for that event 
		eventList.add(_event);
	}
	
	// checks if there is a clash with the events in the venue
	public boolean checkForClashes(Event _event) { 
		// if the hash of the event date is already in venueHash
		// then the venue is already booked on that day
		if (venueHash.containsValue(_event.getFormattedDate().hashCode())) {
			System.out.println("Venue is already booked for that day");
			return false;
		} 
		// else, proceed to book the venue
		return true;
	}
	
	// first check if the venue is free for booking
	// then book the venue, put the hash of the event date into venueHash
	public void registerEvent(Event _event) {
		if (checkForClashes(_event)) {
			addEvent(_event);
			venueHash.put(_event.getEventName(), _event.getFormattedDate().hashCode());
		}			
	}

	public void saveVenue() {
		try{
			File file = new File("data/" + venueName + ".venue");
			FileWriter outFile = new FileWriter(file);
			PrintWriter out = new PrintWriter(outFile);

			out.println(venueName);
			int j = eventList.size();
			out.println(j);
			
			for (int i = 0; i < j; i++) {
				out.println(eventList.get(i).getEventName());
			}

			out.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

	public void loadVenue(String fileName) throws IOException {
		fileName = "data/" + fileName;
		// Declare and initialize local variables 
		FileInputStream fStream = null;	

		// Begin a block of code that handles exceptions
		try{
			// Open the file as a stream
			fStream = new FileInputStream(fileName);

			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fStream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			
			// read the first two lines
			strLine = br.readLine();
			venueName = strLine; // load event name
			
			strLine = br.readLine();
			int noOfEvents = Integer.parseInt(strLine);
			
			// read file line by line
			int i = 0;
			while (i < noOfEvents) {
				Event tempEvent = new Event(); // create a dummy event
				strLine = br.readLine();
				tempEvent.loadEvent(strLine + ".event"); // load the event
				venueHash.put(strLine, tempEvent.getFormattedDate().hashCode());
				addEvent(tempEvent); // add the event to the venue's event list
				i++;
			}
		}

		finally // handle any exceptions
		{			
			// If the file is open, then close it.
			if (fStream != null){								
				fStream.close();					
			}
		}	
	}
}
