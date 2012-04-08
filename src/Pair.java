import java.util.*;
import java.io.*;
		
public class Pair {// the class is to shuffle the teams and 
		   // output a text file "pairing" with the number and names of
		   // the teams shuffled. 
		     	
	String eventName;
	
	public Pair(String fn) {
		eventName = fn;
	}
	
	public void saveTeam (String name, String[] a) { 
		//to shuffle
		try{
			File file = new File("data/" + name + ".pairing");
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
	public void createPairing() {
		int num=0,i=0;
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		//File file = new File("data/" + eventName + ".event");
		String fileName = "data/" + eventName + ".event";
		
		try {
			FileInputStream fStream = null;		
	
			fStream = new FileInputStream(fileName);
			
			DataInputStream in = new DataInputStream(fStream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			
			//skip the following
			br.readLine(); //eventName
			br.readLine(); //venue
			br.readLine(); //year
			br.readLine(); //month
			br.readLine(); //day
			br.readLine(); //hour
			br.readLine(); //min
	
			//read No. of Teams
			strLine = br.readLine();
			num = Integer.parseInt(strLine); // load the number of participating teams
			
			br.readLine();	//skip eventFund
			br.readLine();	//skip regFee		
			
			int[] pair =new int[num];   	 
			for(int j = 0; j<num; j++)
				numbers.add(j+1);
				 
			Collections.shuffle(numbers);
					     
			for(int k =0 ;k<num; k++ )	     
				pair[k]=numbers.get(k)-1;
				   	   
			String[] team = new String[num];
			while (i < num) {
				team[pair[i]] = br.readLine();
				i++;
			}
			
			saveTeam(eventName, team);       
			
			  in.close();
		} catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
}