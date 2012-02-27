import java.util.Scanner;

public class iTournament { // for testing purpose
	public static void main(String[] args) {
		Team tempTeam = new Team(); // create a new team
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter team name:");
		String name = sc.nextLine();
		tempTeam.setTeamName(name); // set the name entered as the team name

		System.out.println("Enter team size:");
		int size = sc.nextInt();
		int i = size;
		
		while (i > 0) {
			Player tempPlayer = new Player(); // create a new player
			
			System.out.println("Enter player name:");
			sc.nextLine();
			String tempName = sc.nextLine();
			tempPlayer.setName(tempName); // store the player's name
			
			System.out.println("Enter player number:");
			int tempNumber = sc.nextInt();
			tempPlayer.setJerseyNumber(tempNumber); // store the player's jersey number
			
			tempTeam.addPlayer(tempPlayer); // add the player into the team
			i--;
		}
		tempTeam.saveTeam(name, tempTeam); // save into a text file
	}
}
