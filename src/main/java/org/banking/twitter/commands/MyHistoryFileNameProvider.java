package org.banking.twitter.commands;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultHistoryFileNameProvider;
import org.springframework.stereotype.Component;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyHistoryFileNameProvider extends DefaultHistoryFileNameProvider {

	public String getHistoryFileName() {
		return "social-media-retail-banking.log";
	}
	
}
