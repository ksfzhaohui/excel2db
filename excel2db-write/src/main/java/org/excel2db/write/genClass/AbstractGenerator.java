package org.excel2db.write.genClass;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.log4j.Logger;
import org.excel2db.write.util.EndfixEnum;

public abstract class AbstractGenerator implements IGenerator {

	private final static Logger logger = Logger
			.getLogger(AbstractGenerator.class);

	protected SheetInfo info;
	protected Config config;
	protected PrintStream out;

	@Override
	public void generator(SheetInfo info, Config config) {
		this.info = info;
		this.config = config;

		String beanRoot = config.getBeanRoot();
		File dir = new File(beanRoot);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		String language = config.getLanguage();
		info.setName(toFirstUpperCase(info.getName()));

		String fullName = beanRoot + File.separator + info.getName()
				+ EndfixEnum.endfix(language);

		File beanFile = new File(fullName);
		if (beanFile.exists()) {
			beanFile.delete();
		}
		try {
			beanFile.createNewFile();
			out = new PrintStream(new FileOutputStream(beanFile));
			generatorBean();
		} catch (IOException e) {
			logger.error("generator bean error", e);
			throw new RuntimeException(e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	protected String toFirstUpperCase(String columnName) {
		String firstStr = columnName.substring(0, 1);
		columnName = columnName.replaceFirst(firstStr, firstStr.toUpperCase());
		return columnName;
	}

	public abstract void generatorBean();
}
