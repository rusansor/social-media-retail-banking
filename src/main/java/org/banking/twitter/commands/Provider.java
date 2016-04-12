package org.banking.twitter.commands;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultBannerProvider;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class Provider extends DefaultBannerProvider  {

	public String getBanner() {
		StringBuffer buf = new StringBuffer();
		buf.append("==============================================================" + OsUtils.LINE_SEPARATOR);
		buf.append("*                                                            *"+ OsUtils.LINE_SEPARATOR);
		buf.append("*            Social Media Retail Banking (Twitter)           *" +OsUtils.LINE_SEPARATOR);
		buf.append("*                                                            *"+ OsUtils.LINE_SEPARATOR);
		buf.append("==============================================================" + OsUtils.LINE_SEPARATOR);
		buf.append("Version:" + this.getVersion());
		return buf.toString();
	}

	public String getVersion() {
		return "0.1";
	}

	public String getWelcomeMessage() {
		return "Welcome to Social Media Retail Banking (Twitter) CLI";
	}
	
}