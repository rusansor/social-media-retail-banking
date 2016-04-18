package org.banking.twitter.dao;

import org.banking.twitter.model.Tweet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends CrudRepository<Tweet, String> {

	// public Tweet findById(String id);

}