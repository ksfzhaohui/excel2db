package org.excel2db.read.util;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class SuffixUtil {

	private final static Logger logger = Logger.getLogger(SuffixUtil.class);

	private static Properties prop = new Properties();

	static {
		try {
			prop.load(new FileInputStream("suffix.properties"));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public static String getFileSuffix() {
		return prop.getProperty("file_suffix");
	}

}
