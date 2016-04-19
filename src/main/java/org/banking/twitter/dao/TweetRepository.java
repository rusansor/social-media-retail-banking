package org.banking.twitter.dao;

import org.banking.twitter.model.Tweet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends MongoRepository<Tweet, String> {

	// public Tweet findById(String id);

	public Tweet findByMetadataIsNull();

}