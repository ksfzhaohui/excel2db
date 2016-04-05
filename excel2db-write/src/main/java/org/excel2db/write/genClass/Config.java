package org.excel2db.write.genClass;

/**
 * 初始化配置信息
 * 
 * @author zhaohui
 * 
 */
public class Config {

	private String language;// 语言
	private String beanRoot;// bean生成路径
	private String packageRoot;// 包路径
	private String excelPath;
	private String ndbPath;

	public Config(String language, String beanRoot, String packageRoot,
			String excelPath, String ndbPath) {
		this.language = language;
		this.beanRoot = beanRoot;
		this.packageRoot = packageRoot;
		this.excelPath = excelPath;
		this.ndbPath = ndbPath;
	}

	public Config(String language, String beanRoot, String excelPath) {
		this.language = language;
		this.beanRoot = beanRoot;
		this.excelPath = excelPath;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getBeanRoot() {
		return beanRoot;
	}

	public void setBeanRoot(String beanRoot) {
		this.beanRoot = beanRoot;
	}

	public String getPackageRoot() {
		return packageRoot;
	}

	public void setPackageRoot(String packageRoot) {
		this.packageRoot = packageRoot;
	}

	public String getExcelPath() {
		return excelPath;
	}

	public void setExcelPath(String excelPath) {
		this.excelPath = excelPath;
	}

	public String getNdbPath() {
		return ndbPath;
	}

	public void setNdbPath(String ndbPath) {
		this.ndbPath = ndbPath;
	}

}
