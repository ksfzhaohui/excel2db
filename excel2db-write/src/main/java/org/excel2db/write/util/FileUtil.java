package org.excel2db.write.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	/** excel文件后缀 **/
	public static final String XLS = ".xls";
	public static final String XLSX = ".xlsx";

	/**
	 * 获取指定路径下所有的excel文件
	 * 
	 * @param path
	 * @return
	 */
	public static List<String> getFileList(String path) {
		List<String> fileList = new ArrayList<String>();
		if (path == null || path.equals("")) {
			return fileList;
		}
		File f = new File(path);
		if (f.isDirectory()) {
			File[] fs = f.listFiles();
			if (fs != null) {
				for (int i = 0; i < fs.length; i++) {
					fileList.addAll(getFileList(fs[i].getPath()));
				}
			}
		} else if (f.getName().endsWith(XLS) || f.getName().endsWith(XLSX)) {
			fileList.add(f.getName());
		}
		return fileList;
	}

	/**
	 * 删除指定目录的所有文件
	 * 
	 * @param path
	 * @param suffix
	 *            指定后缀
	 */
	public static void deleteAllFilesOfDir(File path, String suffix) {
		if (!path.exists())
			return;
		if (path.isFile()) {
			path.delete();
			return;
		}
		File[] files = path.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().endsWith(suffix)) {
				deleteAllFilesOfDir(files[i], suffix);
			}
		}
		path.delete();
	}
}
