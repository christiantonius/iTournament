
public class Player { // class for a player
	String playerName;
	boolean isCaptain; // to check if the player is the captain of the team
	int jerseyNumber;	
	
	public Player() { // create "dummy" player with 0 as his jersey number
		playerName = "dummy";
		jerseyNumber = 0;
		isCaptain = false;
	}
	
	public void setName (String name) { // set the name of the player
		playerName = name;
	}
	
	public void setJerseyNumber (int number) { // set the jersey number of the player
		jerseyNumber = number;
	}
	
	public void setCaptain() { // set the player as the captain of the team
		isCaptain = true;
	}
	
	int checkNumber() { // return the jersey number of a player
		return jerseyNumber;
	}
	
	String checkName() { // return the name of a player
		return playerName;
	}
	
	boolean checkCaptain() { // return true if the player is the captain of the team
		return isCaptain;
	}
}
