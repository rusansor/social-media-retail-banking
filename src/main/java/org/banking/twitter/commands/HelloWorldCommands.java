package org.banking.twitter.commands;

import org.banking.twitter.fetcher.TweetsFetcher;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldCommands implements CommandMarker {
	
//	private boolean simpleCommandExecuted = false;
	
//	@CliAvailabilityIndicator({"hw simple"})
//	public boolean isSimpleAvailable() {
//		return true;
//	}
	
//	@CliAvailabilityIndicator({"hw complex"})
//	public boolean isComplexAvailable() {
//		if (simpleCommandExecuted) {
//			return true;
//		} else {
//			return false;
//		}
//	}
		
	@CliCommand(value = "fetch-and-store-tweets", help = "Fetch and store tweets")
	public String simple()    {
//		simpleCommandExecuted = true;
		TweetsFetcher.fetch();
		return "done";
	}
	
	
	
	
	
	
}
