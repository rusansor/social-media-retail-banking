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

	public static void saveTweet(String id, String json) {
		Mongo mongo;
		try {
			mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("tweeter");
			DBCollection table = db.getCollection("tweets");
			DBObject dbObject = (DBObject) JSON
					.parse(json);
			dbObject.put("_id", id);
//			BasicDBObject document = new BasicDBObject();
//			document.put("tweet", json);
//			document.put("_id", id);
			table.insert(dbObject);
			mongo.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public Tweet  getTweetWithoutMetadata(String bank) {
		Mongo mongo;
		try {
			mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("tweeter");
			DBCollection table = db.getCollection("tweets");
		
			BasicDBObject dbObject = new BasicDBObject();
			dbObject.put("userName", bank);
			DBObject result = table.findOne(dbObject);
			mongo.close();
			
			return tweeterDaoConverter.convert(result);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
}
