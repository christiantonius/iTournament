
public class Referee { // class for a referee
	String refereeName;
	double refereeCost;
	String contactNumber;
	
	public Referee() { // create dummy referee with 0 cost
		refereeName = "dummy";
		refereeCost = 0.0;
		contactNumber = null;
	}
	
	public void setName (String name) { // set the name of the referee
		refereeName = name;
	}
	
	public void setCost (double cost) { // set the cost of the referee
		refereeCost = cost;
	}
	
	public void setContact (String number) { // set the contact number of the referee
		contactNumber = number;
	}
	
	String checkName() { // return the name of a player
		return refereeName;
	}
	
	double checkCost() { // return the cost of the referee 
		return refereeCost;
	}
	
	String checkContactNumber() { // return the contact number of the referee
		return contactNumber;
	}
}
