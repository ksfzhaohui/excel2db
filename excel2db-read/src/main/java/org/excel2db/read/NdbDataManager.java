package org.excel2db.read;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.excel2db.read.util.BeanUtil;

/**
 * ndb管理抽象类
 * 
 * @author zhaohui
 * 
 * @param <T>
 */
public abstract class NdbDataManager<T> {

	private DBFile dbFile;
	private String ndbFilePath;
	private List<T> beanList = new ArrayList<T>();

	public abstract String getNdbName();

	public abstract Class<T> clazz();

	public void init() {
		dbFile = new DBFile();
		dbFile.init(ndbFilePath + File.separator + getNdbName() + ".ndb");

		List<Map<String, Object>> dataMap = dbFile.getDataMap();
		for (Map<String, Object> map : dataMap) {
			beanList.add(BeanUtil.reverse(map, clazz()));
		}
	}

	public List<T> getBeanList() {
		return beanList;
	}

	public String getNdbFilePath() {
		return ndbFilePath;
	}

	public void setNdbFilePath(String ndbFilePath) {
		this.ndbFilePath = ndbFilePath;
	}

}
