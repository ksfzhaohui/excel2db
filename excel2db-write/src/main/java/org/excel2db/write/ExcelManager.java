package org.excel2db.write;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;

public class ExcelManager {

	private final static Logger logger = Logger.getLogger(ExcelManager.class);

	/** 数据开始行 **/
	private static int DATA_STAR_ROW = 5;

	/** excel文件路径 **/
	private String filePath;

	/** 字段名称,字段类型,数据 **/
	private Map<String, List<String>> columnNameMap = new HashMap<String, List<String>>();
	private Map<String, List<TypeEnum>> columnTypeMap = new HashMap<String, List<TypeEnum>>();
	private Map<String, List<List<String>>> dataMap = new HashMap<String, List<List<String>>>();

	public ExcelManager(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * 读取excel
	 */
	public void readExcel() {
		logger.info("start read excel....");
		Workbook book = null;
		try {
			book = Workbook.getWorkbook(new File(filePath));

			Sheet sheets[] = book.getSheets();
			for (Sheet sheet : sheets) {
				int rows = sheet.getRows();
				Cell columnNames[] = sheet.getRow(0);
				Cell columnTypes[] = sheet.getRow(2);
				columnNameMap.put(sheet.getName(), getConentList(columnNames));
				columnTypeMap.put(sheet.getName(), toType(columnTypes));

				List<List<String>> dataList = new ArrayList<List<String>>();
				for (int i = DATA_STAR_ROW; i < rows; i++) {
					Cell columnData[] = sheet.getRow(i);
					dataList.add(getConentList(columnData));
				}

				dataMap.put(sheet.getName(), dataList);
			}
			logger.info("end read excel....");
		} catch (Exception e) {
			logger.error("readExcel error", e);
		} finally {
			if (book != null) {
				book.close();
			}
		}
	}

	/**
	 * 转换为设定的类型
	 * 
	 * @param cells
	 * @return
	 */
	private List<TypeEnum> toType(Cell cells[]) {
		List<String> contents = getConentList(cells);
		List<TypeEnum> typeList = new ArrayList<TypeEnum>();
		for (String type : contents) {
			typeList.add(TypeEnum.type(type));
		}
		return typeList;
	}

	/**
	 * 获取cell中的数据
	 * 
	 * @param cells
	 * @return
	 */
	private List<String> getConentList(Cell cells[]) {
		List<String> contents = new ArrayList<String>();
		for (Cell cell : cells) {
			contents.add(cell.getContents());
		}
		// logger.info(contents);
		return contents;
	}

	public Map<String, List<String>> getColumnNameMap() {
		return columnNameMap;
	}

	public Map<String, List<TypeEnum>> getColumnTypeMap() {
		return columnTypeMap;
	}

	public Map<String, List<List<String>>> getDataMap() {
		return dataMap;
	}

}
