package org.banking.twitter.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class Configuration {
	
	public List<String> getBanks() {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream("src/main/resources/banks-list.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			String banks = properties.getProperty("banks");
			return new ArrayList<String>(Arrays.asList(banks.split(",")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return Collections.EMPTY_LIST;
	}
}
