import java.util.Scanner;
import java.io.*;
public class schedule{
		Scanner sc = new Scanner(System.in);
		String name;
		int num;
		public void readEvent() throws FileNotFoundException{
			System.out.println("please type in the event name");
			name =sc.nextLine();
			
			File file = new File(name+".ivt");
			Scanner scanner = new Scanner(file);
			if(scanner.hasNext())
				name=(scanner.nextLine());
			if(scanner.hasNext())
				num=Integer.parseInt(scanner.nextLine());
			sc.close();
		}
		
		public void setSchedule(){
			int i=1;
			while(num>1){
				System.out.println("the round"+i+"will be on May "+(i*2+5)+"th");
				i++;
				num/=2;
			}
		}
}
