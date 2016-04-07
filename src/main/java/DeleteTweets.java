import java.util.ArrayList;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class DeleteTweets
{
    public static void main(String args[]) throws Exception
    {
    	ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setJSONStoreEnabled(true);
		cb.setDebugEnabled(true).setOAuthConsumerKey("5D8mIJy81yJD3ho7JJEMMemzz")
				.setOAuthConsumerSecret("rUtq9LeDWNtwJpm0cssNYkILPyp8d0yb6IflNOv3X9eXRoWsn2")
				.setOAuthAccessToken("1349915486-6vGnsSn9jAkED2J7eM6yoyUQpXzuvisHeIEiLmU")
				.setOAuthAccessTokenSecret("Xv0SYntAnqNW699fDtlQx1GKpcJfuEBFF9BLX97dvTr8g");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        String [] people = { "santander_resp" };
        for(String s : people){
            do{
                ReadSearch rs = new ReadSearch();
                tweets = rs.getTweets(twitter.getScreenName(), s);
                for(Tweet tw : tweets){
                	System.out.println(tw.toString());
                    
                }
            } while(tweets.size()!=0);
        }
    }
}