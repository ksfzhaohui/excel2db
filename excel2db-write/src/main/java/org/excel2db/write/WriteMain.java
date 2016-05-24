package org.excel2db.write;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.excel2db.write.genClass.Config;
import org.excel2db.write.manager.ExcelParse;
import org.excel2db.write.manager.GeneratorBeanClass;
import org.excel2db.write.manager.NDBGenerator;
import org.excel2db.write.util.EndfixEnum;
import org.excel2db.write.util.FileUtil;

public class WriteMain {
	private final static Logger logger = Logger.getLogger(WriteMain.class);

	public static void main(String[] args) {
		if (args == null || args.length == 0) {
			args = new String[5];
			args[0] = "java";
			args[1] = "D:\\ndbtest";
			args[2] = "test";
			args[3] = "D:\\ndbtest";
			args[4] = "D:\\ndbtest";
		}

		Config config = getConfig(args);
		String excelPath = config.getExcelPath();
		excelPath = excelPath + File.separator;
		logger.info("path:" + excelPath);
		List<String> fileList = FileUtil.getFileList(excelPath);
		logger.info("fileList:" + fileList);

		FileUtil.deleteAllFilesOfDir(new File(config.getNdbPath()),
				NDBGenerator.FILE_SUFFIX);
		FileUtil.deleteAllFilesOfDir(new File(config.getBeanRoot()),
				EndfixEnum.endfix(config.getLanguage()));

		for (String fileName : fileList) {
			logger.info("++++++handle file:【" + fileName + "】++++++");
			ExcelParse excel = new ExcelParse(excelPath + fileName);
			excel.readExcel();

			NDBGenerator db = new NDBGenerator(excel);
			db.writeDB(config.getNdbPath() + File.separator);

			GeneratorBeanClass generator = new GeneratorBeanClass(excel, config);
			generator.generator();
		}
	}

	private static Config getConfig(String[] args) {
		if (args == null || args.length < 1) {
			logger.info("error init param:[language,beanRoot,namespace,excelPath,ndbPath]");
			System.exit(0);
		}
		Config config = null;
		if (args.length != 5 && args.length != 4) {
			logger.info("error init param:[language,beanRoot,namespace,excelPath,ndbPath]");
			System.exit(0);
		}
		if (args.length == 5) {
			config = new Config(args[0], args[1], args[2], args[3], args[4]);
		} else if (args.length == 4) {
			config = new Config(args[0], args[1], "", args[2], args[3]);
		}
		return config;
	}
}
