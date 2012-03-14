import java.util.*;
import java.io.*;
		
public class Pair {// the class is to shuffle the teams and 
		   // output a text file "pairing" with the number and names of
		   // the teams shuffled. 
		     	
	String eventName;
	
	public Pair(String fn) throws FileNotFoundException {
		eventName = fn;
		pairing();
	}
	
	public void saveTeam (String name, String[] a) { 
		//to shuffle
		try{
			File file = new File("data/" + name + ".prg");
			FileWriter outFile = new FileWriter(file);
			PrintWriter out = new PrintWriter(outFile);
			
			out.printf("%d",a.length);
			out.println();
			
			for (int i = 0; i < a.length; i++) {
				out.printf("%s",a[i] );
				out.println();
			}
			
			out.close();
		}catch (IOException e){
				e.printStackTrace();}
	}
	
	//main function is to read from a text file containing 
	//number of teams and the names of team.
	public void pairing() throws FileNotFoundException{
		int num=0,i=0;
		String tempName;
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		File file = new File("data/" + eventName + ".ivt");
		Scanner scanner = new Scanner(file);
		
		if(scanner.hasNext())
			tempName=scanner.nextLine();//read the name of event
			
		if(scanner.hasNext())
			num=Integer.parseInt(scanner.next());//read the number of teams
		
		int[] pair =new int[num];   	 
		for(int j = 0; j<num; j++)
			numbers.add(j+1);
			 
		Collections.shuffle(numbers);
				     
		for(int k =0 ;k<num; k++ )	     
		pair[k]=numbers.get(k)-1;
			   	   
		String[] team = new String[num];
		while (scanner.hasNext()&&i<num) {
			team[pair[i]]=scanner.next();
			i++;
			}
		
		scanner.close();
		saveTeam(eventName, team);       
 }	
}