package org.excel2db.write.util;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigUtil {

	private final static Logger logger = Logger.getLogger(ConfigUtil.class);

	private static Properties prop = new Properties();

	static {
		try {
			prop.load(new FileInputStream("config.properties"));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	public static String getSheetStartWith(){
		return prop.getProperty("sheetStartWith");
	}
}
