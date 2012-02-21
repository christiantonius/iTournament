import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;


public class iTournament {
	public static void main(String[] args) {
		Team tempTeam = new Team();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter team name:");
		String name = sc.nextLine();
		tempTeam.setTeamName(name);

		System.out.println("Enter team size:");
		int size = sc.nextInt();
		int i = size;
		
		while (i > 0) {
			Player tempPlayer = new Player();
			
			System.out.println("Enter player name:");
			sc.nextLine();
			String tempName = sc.nextLine();
			tempPlayer.setName(tempName);
			
			System.out.println("Enter player number:");
			int tempNumber = sc.nextInt();
			tempPlayer.setJerseyNumber(tempNumber);
			
			tempTeam.addPlayer(tempPlayer);
			i--;
		}
		tempTeam.saveTeam(name, tempTeam);
	}
}
