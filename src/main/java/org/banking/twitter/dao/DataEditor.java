package org.banking.twitter.dao;

import java.util.Scanner;

import org.banking.twitter.model.Tweet;

public class DataEditor {

	public static void main(String[] args) {

		do {
			MongoDBDao mongoDBDao = new MongoDBDao();
			Tweet tweetWithoutMetadata = mongoDBDao.getTweetWithoutMetadata("santander_resp");
			System.out.println(tweetWithoutMetadata.getText() + "    =>   ");
			System.out.println("Enter type of tweet: skip(0), acquisition(1), engagement(2) or retention(3):");

			Scanner scanIn = new Scanner(System.in);
			int inputString = scanIn.nextInt();

			scanIn.close();
			System.out.println("Name entered : " + inputString);
		} while (true);
	}

}
