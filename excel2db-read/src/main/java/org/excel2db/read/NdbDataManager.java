package org.excel2db.read;

import java.io.File;
import java.util.List;
import java.util.Map;


public abstract class NdbDataManager<T> {

	private DBFile<T> dbFile;
	private String ndbFilePath;

	public abstract String getNdbName();

	public abstract Class<T> clazz();

	public void init() {
		dbFile = new DBFile<T>();
		dbFile.init(ndbFilePath + File.separator + getNdbName() + ".ndb");

		List<T> beanList = dbFile.getBeanList();
		List<Map<String, Object>> dataMap = dbFile.getDataMap();
		for (Map<String, Object> map : dataMap) {
			beanList.add(BeanUtil.reverse(map, clazz()));
		}
	}

	public List<T> getBeanList() {
		return dbFile.getBeanList();
	}

	public String getNdbFilePath() {
		return ndbFilePath;
	}

	public void setNdbFilePath(String ndbFilePath) {
		this.ndbFilePath = ndbFilePath;
	}

}
