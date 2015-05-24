package com.girija.learning.selenium.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
/**
 * This class is created by externalize strings option in eclipse
 * */
public class PropertyConfigurations {
	private static final String BUNDLE_NAME = "config"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private PropertyConfigurations() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
