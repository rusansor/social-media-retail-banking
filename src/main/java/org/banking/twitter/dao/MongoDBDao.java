package org.banking.twitter.dao;

import java.net.UnknownHostException;

import org.banking.twitter.model.Metadata;
import org.banking.twitter.model.Tweet;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

@Component
public class MongoDBDao {
	TweeterDaoConverter tweeterDaoConverter = new TweeterDaoConverter();

	public void addMetadata(Tweet tweet, Metadata metadata) {
		Mongo mongo;
		try {
			mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("tweeter");
			DBCollection collection = db.getCollection("tweets");
			System.out.println("inserting tweet with id: " + tweet.getId()
					+ " into DB with metadata.categoryDefinition: " + metadata.getCategoryDefinition().toString());
			collection.update(new BasicDBObject("_id", tweet.getId()),
					new BasicDBObject("$set", new BasicDBObject("metadata",
							new BasicDBObject("categoryDefinition", metadata.getCategoryDefinition().toString()))));

			mongo.close();
			System.out.println("iserted tweet into DB with metadata");
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
