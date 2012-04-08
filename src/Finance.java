import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Vector;

public class Finance { // output the financial information for all events
	Double income, expense, totalProfit, currentEventProfit;
	Vector<Event> eventList;
	String result = "";
	
	public Finance() {
		income = 0.0;
		expense = 0.0;
		totalProfit = 0.0;
		currentEventProfit = 0.0;
	}
	
	public void printProfit(Event _event) {
		//System.out.println(_event.getEventName());
		result += _event.getEventName() + "\n\n";
		printEventFund(_event.getEventFund());
		printRegFee(_event.getRegFee(), _event.getEventSize(), _event.getTotalRegFee());
		
		try {
			_event.loadReferee(_event.getEventName() + ".ref");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		printRefFee(_event.getRefereeList(), _event.getRefSize());
		printVenueCharges(_event);
		
		String s2;
		if (income >= expense) {
			currentEventProfit = income - expense;
			totalProfit += currentEventProfit;

			s2 = " $" + currentEventProfit + "\n";
		}
			
		else {
			currentEventProfit = expense - income;
			totalProfit -= currentEventProfit;

			s2 = "\u002D$" + currentEventProfit + "\n";
		}

		String s = String.format("\t%-45s", "Profit");					
		
		result += "    -------------------------------------------------------\n";
		result += s + s2;
		result += "    -------------------------------------------------------\n";
	}
	
	public String printAllProfit() {
		eventList = loadEventList();
		result += "-----------------------------------------------------------\n";
		result += "Finance Calculator\n";
		result += "-----------------------------------------------------------\n";
		
		for (int i = 0; i < eventList.size(); i++) {
			result += (i+1) + ". ";
			printProfit(eventList.get(i));
			income =0.0;
			expense = 0.0;
			currentEventProfit = 0.0;
		}
		
		String s2;
		if (totalProfit > 0) 
			s2 = " $" + totalProfit + "\n";
			
		else 
			s2 = "\u002D$" + totalProfit + "\n";

		String s = String.format("%-49s", "Total Profit");	
		
		result += "-----------------------------------------------------------\n";
		result += s + s2;
		result += "-----------------------------------------------------------\n";
		
		return result;
	}
	
	public void printEventFund (double money) {
		String s = String.format("\t%-46s", "Event Fund");
		String s2 = "$" + money + "\n";
		result += s + s2;
		
		income += money;
	}
	
	public void printRegFee(double fee, int n, double total) {
		String s = String.format("\t%-30s", "Registration Fee");
		String s2 = "$" + fee + " \u0078 " + n;
		String s3 = String.format("%-15s", s2);
		String s4 = " $" + total + "\n";
		result += s + s3 + s4;
		income += total;
	}
	
	public void printRefFee(Vector<Referee> refList, int n) {
		double totalCost = 0;
		for (int i = 0; i < n; i++) {
			Referee tempRef = refList.get(i);
			String line = "Referee Pay (" + tempRef.getName() + ")";
			String s = String.format("\t%-30s", line);
			String s2 = "$" + tempRef.getCost() + "\n";
			result += s + s2;
			totalCost += tempRef.getCost();
		}
		String s = String.format("\t%-45s", "Total Referee Pay");
		String s2 = "\u002D$" + totalCost + "\n";
		result += s + s2;
		expense += totalCost;
	}
	
	public void printVenueCharges (Event _event) {
		//String s = String.format("\t%-30s", "Venue Charges");
	}
	
	public Vector<Event> loadEventList() {
		String[] eventList = getEventFiles();
		Vector<Event> tempList = new Vector<Event>();
		
		if(eventList == null)	
			return null;
		
		int noOfEvents = eventList.length;
		
		for(int i=0; i < noOfEvents; i++) {
			//String _event = eventList[i].substring(0, eventList[i].length()-6);
			Event tempEvent = new Event();
			
			try {
				tempEvent.loadEvent(/*_event + ".event"*/eventList[i]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			tempList.add(tempEvent);
		}
		
		return tempList;
	}
	
	private String[] getEventFiles() {
		
		ExtensionFilter filter = new ExtensionFilter(".event");
		File dir = new File("data/");

		String[] list = dir.list(filter);

		return list;
	}
	
	class ExtensionFilter implements FilenameFilter {
		
		private String extension;
	
		public ExtensionFilter( String extension ) {
			this.extension = extension;             
		}
		public boolean accept(File dir, String name) {
			return (name.endsWith(extension));
		}
	}
}
