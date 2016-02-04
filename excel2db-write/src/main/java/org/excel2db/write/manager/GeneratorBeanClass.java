package org.excel2db.write.manager;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.excel2db.write.genClass.Config;
import org.excel2db.write.genClass.GeneratorFactory;
import org.excel2db.write.genClass.IGenerator;
import org.excel2db.write.genClass.SheetInfo;
import org.excel2db.write.util.TypeEnum;

/**
 * 生成bean类
 * 
 * @author zhaohui
 * 
 */
public class GeneratorBeanClass {

	private final static Logger logger = Logger.getLogger(NDBGenerator.class);

	private static final String NAME_ENDFIX = "Data";

	private Config config;
	private Map<String, List<String>> columnNameMap;
	private Map<String, List<TypeEnum>> columnTypeMap;

	public GeneratorBeanClass(ExcelParse excelManager, Config config) {
		columnNameMap = excelManager.getColumnNameMap();
		columnTypeMap = excelManager.getColumnTypeMap();
		this.config = config;
	}

	public void generator() {
		logger.info("start generator bean file....");
		try {
			Set<String> keys = columnNameMap.keySet();
			for (String key : keys) {
				List<String> columnNames = columnNameMap.get(key);
				List<TypeEnum> typeEnums = columnTypeMap.get(key);

				SheetInfo info = new SheetInfo(key + NAME_ENDFIX, columnNames,
						typeEnums);
				IGenerator generator = GeneratorFactory.getGenerator(config
						.getLanguage());
				generator.generator(info, config);
			}
			logger.info("end generator bean file....");
		} catch (Exception e) {
			logger.error("generator bean error", e);
		}
	}
}
