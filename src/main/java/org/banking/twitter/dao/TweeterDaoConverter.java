package org.banking.twitter.dao;

import org.banking.twitter.model.CategoryDefinition;
import org.banking.twitter.model.Metadata;
import org.banking.twitter.model.Tweet;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class TweeterDaoConverter {

	public Tweet convert(DBObject dbObject) {
		Tweet tweet = new Tweet();

		tweet.setId((String) dbObject.get("_id"));
		tweet.setFavorites(new Integer((String) dbObject.get("favorites")));
		tweet.setText((String) dbObject.get("text"));
		Object objectMetadata = dbObject.get("metadata");
		if (objectMetadata != null) {
			Metadata metadata = new Metadata();
			String categoryDefinition = (String) dbObject.get("metadata.categoryDefinition");
			System.out.println("!!!!category found!!!");
			if (categoryDefinition != null) {
				metadata.setCategoryDefinition(CategoryDefinition.valueOf(categoryDefinition));
			}

			tweet.setMetadata(metadata);
		}
		return tweet;
	}

	public DBObject convert(Tweet tweet) {
		DBObject dbObject = new BasicDBObject();
		dbObject.put("_id", tweet.getId());
		DBObject metadata = new BasicDBObject();
		metadata.put("categoryDefinition", tweet.getMetadata().getCategoryDefinition().toString());
		dbObject.put("metadata", metadata);
		return dbObject;
	}

}
