package org.banking.twitter;
import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

public class MongoDBDao {

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
}
