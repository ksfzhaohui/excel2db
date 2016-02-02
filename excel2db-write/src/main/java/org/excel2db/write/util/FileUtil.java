package org.excel2db.write.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	public static List<String> getData(String path) {
		List<String> fileList = new ArrayList<String>();
		File f = new File(path);
		if (f.isDirectory()) {
			File[] fs = f.listFiles();
			if (fs != null) {
				for (int i = 0; i < fs.length; i++) {
					fileList.addAll(getData(fs[i].getPath()));
				}
			}
		} else if (f.getName().endsWith(".xls")) {
			fileList.add(f.getName());
		}
		return fileList;
	}

	public static void main(String[] args) {
		System.out.println(getData("D:\\"));
	}
}
