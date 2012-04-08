

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.swt.browser.Browser;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class OAuthTwitterClient {
	//this consumer key  and consumer secret is to get from twitter once you register a client app with your own account 	
	private final static String consumerKey = "6pV7veelyNrrcHOLwqDSg";
	private final static String consumerSecret = "6kLyBp7RUf2j2qG9r1eApSoy1ETMzIvoXqN4RI4k";

	private final static String fileToken = "token.txt"; //text file to sore the user id and access token once obtained by request,currently
	//twitter won't expire them. they are associate with that registered accout. 

	
	public void updateStatus(String status) throws TwitterException,
			IOException {
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		RequestToken requestToken = twitter.getOAuthRequestToken();
		AccessToken accessToken = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (accessToken == null) {
			System.out.println("Open the following URL and grant access to your account:");
			System.out.println(requestToken.getAuthorizationURL());
			//browser.setUrl(requestToken.getAuthorizationURL());
			// Copy the authorisation URL in your browser			
			// Log in Twitter, grant access
			System.out.println("Copy the PIN displayed in your browser, then hit ENTER: ");
			String pin = br.readLine();
			accessToken = twitter.getOAuthAccessToken(requestToken, pin);
		}

		// store the access token for future reference
		int userId = (int) twitter.verifyCredentials().getId();
		storeAccessToken(userId, accessToken);
		Status twitterStatus = twitter.updateStatus(status);
		System.out.println("Successfully updated the status for user with ID "
				+ userId + " to [" + twitterStatus.getText() + "].");
	}

	public void updateStatus(int userId, String status)
			throws TwitterException, IOException {
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		AccessToken accessToken = loadAccessToken(userId);
		twitter.setOAuthAccessToken(accessToken);
		Status twitterStatus = twitter.updateStatus(status);
		System.out.println("Successfully updated the status for user with ID "
				+ userId + " to [" + twitterStatus.getText() + "].");
	}

	private void storeAccessToken(int userId, AccessToken accessToken)
			throws IOException {
		// Store user ID, token and token secret
		System.out.print("Storing access token for " + userId + "...");
		BufferedWriter out = new BufferedWriter(new FileWriter(fileToken));
		out.append("" + userId);
		out.append(" ");
		out.append(accessToken.getToken());
		out.append(" ");
		out.append(accessToken.getTokenSecret());
		out.newLine();
		out.close();
		System.out.println("Done");
	}

	private AccessToken loadAccessToken(int userId) throws IOException {
		AccessToken accessToken = null;
		String id = "" + userId;
		BufferedReader in = new BufferedReader(new FileReader(fileToken));

		String line;
		String[] fields;
		while ((line = in.readLine()) != null) {
			fields = line.split(" ");
			if (id.equals(fields[0])) {
				accessToken = new AccessToken(fields[1], fields[2]);
				System.out.println("Access token found for " + userId);
				break;
			}
		}

		in.close();

		return accessToken;
	}

/*
	public static void main(String[] args) throws TwitterException, IOException {
		OAuthTwitterClient tt = new OAuthTwitterClient();
		//tt.updateStatus("Studying OAuth");
		
	tt.updateStatus("the user id", "the massage u r to tweet");
	}*/

}
