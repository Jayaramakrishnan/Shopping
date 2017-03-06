package com.crackers.common;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class EmailHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailHelper.class);

	public static String getFilledEmailTemplate(Map<String, String> templateElementsMap, String template) {
		LOGGER.info("Filling the Email Template");
		for (Map.Entry<String, String> entry : templateElementsMap.entrySet()) {
			String key = entry.getKey();
			if (entry.getValue() != null) {
				template = StringUtils.replace(template, key, entry.getValue());
			}
		}
		return template;
	}

	public static String replaceNullInString(String input) {
		return input == null ? "" : input;
	}
}
