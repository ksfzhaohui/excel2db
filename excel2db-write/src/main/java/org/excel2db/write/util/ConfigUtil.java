package org.excel2db.write.util;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigUtil {

	private final static Logger logger = Logger.getLogger(ConfigUtil.class);

	private static Properties prop = new Properties();

	private static List<String> startWithList = new ArrayList<String>();

	static {
		try {
			prop.load(new FileInputStream("config.properties"));
			String startWiths[] = prop.getProperty("sheetStartWith").split(",");
			if (startWiths != null) {
				for (String sw : startWiths) {
					startWithList.add(sw);
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public static List<String> getSheetStartWith() {
		return startWithList;
	}

	public static String getFileSuffix() {
		return prop.getProperty("file_suffix");
	}

}
