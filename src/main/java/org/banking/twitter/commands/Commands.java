package org.banking.twitter.commands;

import java.io.IOException;
import java.util.logging.Logger;

import org.banking.twitter.dao.MongoDBDao;
import org.banking.twitter.dao.TweetRepository;
import org.banking.twitter.fetcher.TweetsFetcher;
import org.banking.twitter.model.CategoryDefinition;
import org.banking.twitter.model.Metadata;
import org.banking.twitter.model.Tweet;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import jline.console.ConsoleReader;

@Component
public class Commands implements CommandMarker {

	private final Logger logger = Logger.getLogger(Commands.class.getName());

	@Autowired
	private TweetRepository tweetRepository;

	@Autowired
	private TweetsFetcher tweetsFetcher;

	@Autowired
	private MongoDBDao mongoDBDao;

	@CliCommand(value = "fetch-and-store-tweets", help = "Fetch and store tweets")
	public String fetchAandStoreTweets() {
		tweetsFetcher.fetch();
		return "done";
	}

	@CliCommand(value = "reset-all-tweets", help = "Reset *ALL* Tweets")
	public String resetAllTweets() {
		tweetRepository.deleteAll();
		return "Deleted all tweets";
	}

	@CliCommand(value = "fetch-tweets-without-metadata", help = "Fetch and store tweets")
	public String fetchTweetTithoutMetadata() throws JsonGenerationException, JsonMappingException, IOException {
		Tweet tweetWithoutMetadata = mongoDBDao.getTweetWithoutMetadata("santander_resp");
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(tweetWithoutMetadata);
	}

	@CliCommand(value = "read-tweet-by-id", help = "Read a Tweet stored in the DB with lookup by Id")
	public String readTweetById(
			@CliOption(key = { "tweetId" }, mandatory = true, help = "Tweet Id") final String tweetId)
			throws JsonGenerationException, JsonMappingException, IOException {
		Tweet tweet = tweetRepository.findOne(tweetId);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(tweet);
	}

	@CliCommand(value = "add-metadata", help = "Fetch and store tweets")
	public String addMetadata(
			@CliOption(key = { "tweetId" }, mandatory = false, help = "Tweet Id") final String tweetId)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		if (tweetId == null) {
			Tweet tweetWithoutMetadata = mongoDBDao.getTweetWithoutMetadata("santander_resp");
			String tweet = mapper.writeValueAsString(tweetWithoutMetadata);
			System.out.println(tweet);
			ConsoleReader reader = new ConsoleReader();
			String readLine = reader.readLine("Enter Category Definition[(A)cquisition, (E)ngagement, (R)etention]: ");
			System.out.println(readLine);
			Metadata metadata = new Metadata();
			if ("a".equals(readLine.toLowerCase())) {
				metadata.setCategoryDefinition(CategoryDefinition.ACQUISITION);
			} else if ("e".equals(readLine.toLowerCase())) {
				metadata.setCategoryDefinition(CategoryDefinition.ENGAGEMENT);
			} else if ("r".equals(readLine.toLowerCase())) {
				metadata.setCategoryDefinition(CategoryDefinition.RETENTION);
			} else {
				System.out.println("not matching!!!!!!!!!!!!!!");
			}
			mongoDBDao.addMetadata(tweetWithoutMetadata, metadata);
			return "saved tweet with metadata";
		} else {
			Tweet tweet = tweetRepository.findOne(tweetId);
			System.out.println(tweet);
			Metadata metadata = new Metadata();
			metadata.setCategoryDefinition(CategoryDefinition.ENGAGEMENT);
			tweet.setMetadata(metadata);
			tweetRepository.save(tweet);
			// mongoDBDao.saveTweet(tweet);

			String tweetJson = mapper.writeValueAsString(tweet);
			return "saved Tweet with content: " + tweetJson;
		}

	}

}
