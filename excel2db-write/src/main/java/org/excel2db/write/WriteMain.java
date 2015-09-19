package org.excel2db.write;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.excel2db.write.genClass.Config;
import org.excel2db.write.genClass.EndfixEnum;

public class WriteMain {
	private final static Logger logger = Logger.getLogger(WriteMain.class);

	public static void main(String[] args) {
		Config config = getConfig(args);
		String excelPath = config.getExcelPath();
		excelPath = excelPath + File.separator;
		logger.info("path:" + excelPath);
		List<String> fileList = FileUtil.getData(excelPath);
		logger.info("fileList:" + fileList);

		for (String fileName : fileList) {
			logger.info("process:" + fileName);
			ExcelManager excel = new ExcelManager(excelPath + fileName);
			excel.readExcel();

			DBManager db = new DBManager(excel);
			db.writeDB(excelPath);

			GeneratorManager generator = new GeneratorManager(excel, config);
			generator.generator();
		}
	}

	private static Config getConfig(String[] args) {
		if (args == null || args.length < 1) {
			logger.info("no language param support:java,csharp");
			System.exit(0);
		}
		String language = args[0];
		EndfixEnum type = EndfixEnum.getType(language);
		Config config = null;
		switch (type) {
		case JAVA:
			if (args.length != 4) {
				logger.info("error init param,param:language,beanRoot,packageRoot,excelPath");
				System.exit(0);
			}
			break;
		case CSHARP:
			if (args.length != 4) {
				logger.info("error init param,param:language,beanRoot,namespace,excelPath");
				System.exit(0);
			}
			break;
		}
		config = new Config(args[0], args[1], args[2], args[3]);
		return config;
	}
}
