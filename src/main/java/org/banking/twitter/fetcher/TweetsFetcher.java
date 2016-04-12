package org.banking.twitter.fetcher;

import java.text.SimpleDateFormat;
import java.util.List;

import org.banking.twitter.configuration.Configuration;
import org.banking.twitter.dao.MongoDBDao;
import org.banking.twitter.model.Tweet;

public class TweetsFetcher {

	
	private static Configuration configuration = new Configuration();
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	

	public static void fetch() {

		// else if (parameterSplit[0].equals("querysearch")) {
		// criteria.setQuerySearch(parameterSplit[1]);
		// } else if (parameterSplit[0].equals("maxtweets")) {
		// criteria.setMaxTweets(Integer.valueOf(parameterSplit[1]));
		// }
		List<String> banks = configuration.getBanks();
		for (String bank : banks) {
			String since = "2015-10-01";
			String until = "2015-11-01";
			TwitterCriteria criteria = TwitterCriteria.create();

			criteria.setUsername(bank);
			criteria.setSince(since);
			criteria.setUntil(until);
			System.out.println(String.format("Searching... for bank[%s]\n",bank));
			for (Tweet tweet : TweetManager.getTweets(criteria)) {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("{");
				stringBuilder.append(String.format("\"id\": \"%s\"", tweet.getId()));
				stringBuilder.append(",");
				stringBuilder.append(String.format("\"userName\": \"%s\"", tweet.getUsername()));
				stringBuilder.append(",");
				stringBuilder.append(String.format("\"text\": \"%s\"", tweet.getText()));
				stringBuilder.append(",");
				stringBuilder.append(String.format("\"date\": \"%s\"", sdf.format(tweet.getDate())));
				stringBuilder.append(",");
				stringBuilder.append(String.format("\"retweets\": \"%d\"", tweet.getRetweets()));
				stringBuilder.append(",");
				stringBuilder.append(String.format("\"favorites\": \"%d\"", tweet.getFavorites()));
				stringBuilder.append(",");
				stringBuilder.append(String.format("\"mentions\": \"%s\"", tweet.getMentions()));
				stringBuilder.append(",");
				stringBuilder.append(String.format("\"hastags\": \"%s\"", tweet.getHashtags()));
				stringBuilder.append(",");
				stringBuilder.append(String.format("\"geo\": \"%s\"", tweet.getGeo()));
				stringBuilder.append(",");
				stringBuilder.append(String.format("\"permlink\": \"%s\"", tweet.getPermalink()));
				stringBuilder.append("}");

				MongoDBDao.saveTweet(tweet.getId(), stringBuilder.toString());
			}
		}
	}

}