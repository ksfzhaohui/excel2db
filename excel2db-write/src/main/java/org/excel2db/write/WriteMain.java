package org.excel2db.write;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.excel2db.write.genClass.Config;
import org.excel2db.write.manager.NDBGenerator;
import org.excel2db.write.manager.ExcelParse;
import org.excel2db.write.manager.GeneratorBeanClass;
import org.excel2db.write.util.EndfixEnum;
import org.excel2db.write.util.FileUtil;

public class WriteMain {
	private final static Logger logger = Logger.getLogger(WriteMain.class);

	public static void main(String[] args) {
		if (args == null) {
			args = new String[4];
			args[0] = "java";
			args[1] = "D:\\ndbtest";
			args[2] = "test";
			args[3] = "D:\\ndbtest";
		}

		Config config = getConfig(args);
		String excelPath = config.getExcelPath();
		excelPath = excelPath + File.separator;
		logger.info("path:" + excelPath);
		List<String> fileList = FileUtil.getData(excelPath);
		logger.info("fileList:" + fileList);

		for (String fileName : fileList) {
			logger.info("process:" + fileName);
			ExcelParse excel = new ExcelParse(excelPath + fileName);
			excel.readExcel();

			NDBGenerator db = new NDBGenerator(excel);
			db.writeDB(excelPath);

			GeneratorBeanClass generator = new GeneratorBeanClass(excel, config);
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
