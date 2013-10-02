package vladimir.chugunov.CoinExchange;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Automatically generated class for string externalization 
 */
@SuppressWarnings("javadoc")
public class Messages {
	private static final String BUNDLE_NAME = "vladimir.chugunov.CoinExchange.messages"; 

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	private Messages() {
	}
}
