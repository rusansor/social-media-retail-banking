package org.banking.twitter.fetcher;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;

import org.banking.twitter.commands.Commands;
import org.banking.twitter.configuration.Configuration;
import org.banking.twitter.dao.TweetRepository;
import org.banking.twitter.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TweetsFetcher {

	private final Logger logger = Logger.getLogger(Commands.class.getName());

	@Autowired
	private TweetRepository tweetRepository;

	private static Configuration configuration = new Configuration();

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

	public void fetch() {
		logger.info("start fetching data");

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
			System.out.println(String.format("Searching... for bank[%s]\n", bank));
			for (Tweet tweet : TweetManager.getTweets(criteria)) {

				tweetRepository.save(tweet);
			}
		}
	}

}