import java.awt.Desktop;
import java.net.URI;
public class twitter{
	
	
	
	Desktop desktop=null;
	public void LaunchURL(String url){
		try{
			URI uri= new URI(url);
			if(Desktop.isDesktopSupported())
				desktop =Desktop.getDesktop();	
			if(desktop!=null){
				desktop.browse(uri);
			}
		
			}catch(Exception ex){
				ex.printStackTrace();
			}
	}
	
	/*public static void main(String[] args){
		
		twitter twitter = new twitter();
		twitter.LaunchURL();
	}*/

}
