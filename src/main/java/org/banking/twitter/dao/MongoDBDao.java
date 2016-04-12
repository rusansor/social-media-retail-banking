package org.banking.twitter.dao;

import java.net.UnknownHostException;

import org.banking.twitter.model.Tweet;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

public class MongoDBDao {
	TweeterDaoConverter tweeterDaoConverter = new TweeterDaoConverter();

	public Tweet getTweetById(String tweetId) {
		Mongo mongo;
		try {
			mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("tweeter");
			DBCollection table = db.getCollection("tweets");
			DBObject dbObject = new BasicDBObject();
			dbObject.put("_id", tweetId);
			System.out.println("looking up tweet for id ["+tweetId+"]") ;
			DBObject result = table.findOne(dbObject);
			mongo.close();
			return tweeterDaoConverter.convert(result);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void saveTweet(Tweet tweet) {
		Mongo mongo;
		try {
			mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("tweeter");
			DBCollection table = db.getCollection("tweets");
		
			DBObject convert = tweeterDaoConverter.convert(tweet);
			
			DBObject key = new BasicDBObject();
			key.put("_id", tweet.getId());
			System.out.println("removing tweet from DB");
			table.remove(key);
			System.out.println("inserting tweet from DB");
			table.insert(convert);
			System.out.println("niserted tweet into DB");
			mongo.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public void saveTweet(String id, String json) {
		Mongo mongo;
		try {
			mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("tweeter");
			DBCollection table = db.getCollection("tweets");
			DBObject dbObject = (DBObject) JSON.parse(json);
			dbObject.put("_id", id);
			// BasicDBObject document = new BasicDBObject();
			// document.put("tweet", json);
			// document.put("_id", id);
			table.insert(dbObject);
			mongo.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public Tweet getTweetWithoutMetadata(String bank) {
		Mongo mongo;
		try {
			mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("tweeter");
			DBCollection table = db.getCollection("tweets");

			// BasicDBObject dbObject = new BasicDBObject();
			// dbObject.put("userName", bank);

			DBObject query = new BasicDBObject("metadata", new BasicDBObject("$exists", false));
			query.put("userName", bank);
			DBObject result = table.findOne(query);
			mongo.close();

			return tweeterDaoConverter.convert(result);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
}
