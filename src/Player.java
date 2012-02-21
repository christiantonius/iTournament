
public class Player {
	String playerName;
	boolean isCaptain;
	int jerseyNumber;	
	
	public Player() {
		playerName = "dummy";
		jerseyNumber = 0;
		isCaptain = false;
	}
	
	public void setName (String name) {
		playerName = name;
	}
	
	public void setJerseyNumber (int number) {
		jerseyNumber = number;
	}
	
	public void setCaptain() {
		isCaptain = true;
	}
	
	int checkNumber() {
		return jerseyNumber;
	}
	
	String checkName() {
		return playerName;
	}
	
	boolean checkCaptain() {
		return isCaptain;
	}
}
