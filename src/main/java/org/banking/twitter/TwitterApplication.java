package org.banking.twitter;

import java.util.logging.Logger;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Twitter application using Twitter4J
 */
@Deprecated
public class TwitterApplication {
	private final Logger logger = Logger.getLogger(TwitterApplication.class.getName());

	public static void main(String[] args) {
		new TwitterApplication().retrieve();
	}

	public void retrieve() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setJSONStoreEnabled(true);
		cb.setDebugEnabled(true).setOAuthConsumerKey("5D8mIJy81yJD3ho7JJEMMemzz")
				.setOAuthConsumerSecret("rUtq9LeDWNtwJpm0cssNYkILPyp8d0yb6IflNOv3X9eXRoWsn2")
				.setOAuthAccessToken("1349915486-6vGnsSn9jAkED2J7eM6yoyUQpXzuvisHeIEiLmU")
				.setOAuthAccessTokenSecret("Xv0SYntAnqNW699fDtlQx1GKpcJfuEBFF9BLX97dvTr8g");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		logger.info("Retrieving tweets...");
		// List<String> banks = getBanks();
		// for (String bank : banks) {
		// Query query = new Query("from:" + bank);
		// query.setCount(100);
		// query.setSince("2016-01-01");
		// query.setUntil("2016-05-01");
		// // query.setSinceId( 713079310446436352l);
		// try {
		// QueryResult result = twitter.search(query);
		// List<Status> tweets = result.getTweets();
		// if(tweets== null || tweets.size() == 0){
		// System.out.println("not tweets found for query => " +query );
		// }else{
		// System.out.println("tweets were successfully found for query => "
		// +query );
		// for (Status tweet : tweets) {
		// String json = TwitterObjectFactory.getRawJSON(tweet);
		//// MongoDBDao.saveTweet(tweet.getId(), json);
		// }
		// }
		//
		// } catch (TwitterException e) {
		// e.printStackTrace();
		// }
		// logger.info("done! ");
		// }

	}

}