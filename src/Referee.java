
public class Referee { // class for a referee
	String refereeName;
	double refereeCost;
	
	public Referee() { // create dummy referee with 0 cost
		refereeName = "dummy";
		refereeCost = 0.0;
	}
	
	public void setName (String name) { // set the name of the referee
		refereeName = name;
	}
	
	public void setCost (double cost) { // set the cost of the referee
		refereeCost = cost;
	}
	
	String getName() { // return the name of a player
		return refereeName;
	}
	
	double getCost() { // return the cost of the referee 
		return refereeCost;
	}
}
