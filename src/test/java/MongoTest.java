import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

public class MongoTest {

	public static void main(String[] args) throws UnknownHostException {
		Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("tweeter");
		DBCollection table = db.getCollection("tweets");

		BasicDBObject searchObject = new BasicDBObject();
		searchObject.append("lang", "es");
		DBCursor cursor = table.find(searchObject);
		while (cursor.hasNext()) {

			System.out.println(cursor.next().get("age"));
		}
		mongo.close();
	}
}
