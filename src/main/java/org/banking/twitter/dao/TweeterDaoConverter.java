package org.banking.twitter.dao;

import org.banking.twitter.model.Tweet;

import com.mongodb.DBObject;

public class TweeterDaoConverter {

	public Tweet convert(DBObject dbObject) {
		Tweet tweet = new Tweet();
		
		tweet.setId((String) dbObject.get("_id"));
		tweet.setFavorites( new Integer( (String)dbObject.get("favorites")));
		tweet.setText((String) dbObject.get("text"));
		return tweet;
	}

	public DBObject convert(Tweet tweet) {
		// TODO
		return null;
	}

}
