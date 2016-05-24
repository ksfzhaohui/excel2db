package org.excel2db.write.manager;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.excel2db.write.util.TypeEnum;

/**
 * 读取excel
 * 
 * @author zhaohui
 * 
 */
public class ExcelParse {

	private final static Logger logger = Logger.getLogger(ExcelParse.class);

	/** 数据开始行 **/
	private static int DATA_STAR_ROW = 5;
	/** 类型的行 **/
	private static int TYPE_ROW = 2;
	/** 名字的行 **/
	private static int NAME_ROW = 0;

	/** excel文件路径 **/
	private String filePath;

	/** 字段名称,字段类型,数据 **/
	private Map<String, List<String>> columnNameMap = new HashMap<String, List<String>>();
	private Map<String, List<TypeEnum>> columnTypeMap = new HashMap<String, List<TypeEnum>>();
	private Map<String, List<List<String>>> dataMap = new HashMap<String, List<List<String>>>();

	public ExcelParse(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * 读取excel
	 */
	public void readExcel() {
		logger.info("start read excel");
		Workbook book = null;
		try {
			book = WorkbookFactory.create(new FileInputStream(filePath));

			int sheetNum = book.getNumberOfSheets();
			for (int num = 0; num < sheetNum; num++) {
				Sheet sheet = book.getSheetAt(num);
				int rows = getRowNum(book, num);
				int columns = getColumnNum(book, num);

				List<Integer> columnList = new ArrayList<Integer>();
				List<TypeEnum> typeList = new ArrayList<TypeEnum>();
				List<String> nameList = new ArrayList<String>();

				for (int column = 0; column < columns; column++) {
					String type = getValue(sheet, column, TYPE_ROW);
					TypeEnum typeEnum = TypeEnum.type(type);
					if (typeEnum != null) {
						typeList.add(typeEnum);
						String name = getValue(sheet, column, NAME_ROW);
						if (name.equals("")) {
							throw new RuntimeException(
									"nameNullException:column=" + (column + 1));
						}
						nameList.add(name);
						columnList.add(column);
					}
				}

				String sheetName = sheet.getSheetName();
				columnNameMap.put(sheetName, nameList);
				columnTypeMap.put(sheetName, typeList);

				List<List<String>> dataList = new ArrayList<List<String>>();
				next: for (int row = DATA_STAR_ROW; row <= rows; row++) {
					List<String> list = new ArrayList<String>();
					for (int index = 0; index < columnList.size(); index++) {
						int column = columnList.get(index);
						TypeEnum typeEnum = typeList.get(index);

						String content = getValue(sheet, column, row);
						if (index == 0 && content.equals("")) {
							break next;
						}
						String value = getInitValue(content);

						checkValue(typeEnum, value, row, column);
						list.add(value);
					}
					dataList.add(list);
				}

				dataMap.put(sheetName, dataList);
			}
			logger.info("end read excel");
		} catch (Exception e) {
			logger.error("readExcel error", e);
		}
	}

	/**
	 * 返回Excel最大行index值，实际行数要加1
	 * 
	 * @return
	 */
	public int getRowNum(Workbook book, int sheetIndex) {
		Sheet sheet = book.getSheetAt(sheetIndex);
		return sheet.getLastRowNum();
	}

	/**
	 * 返回数据的列数
	 * 
	 * @return
	 */
	public int getColumnNum(Workbook book, int sheetIndex) {
		Sheet sheet = book.getSheetAt(sheetIndex);
		Row row = sheet.getRow(0);
		if (row != null && row.getLastCellNum() > 0) {
			return row.getLastCellNum();
		}
		return 0;
	}

	/**
	 * 获取单元格中的数值
	 * 
	 * @param sheet
	 * @param column
	 * @param row
	 * @return
	 */
	private String getValue(Sheet sheet, int column, int row) {
		Row rowObj = sheet.getRow(row);
		Cell cell = rowObj.getCell(column);
		if (cell == null) {
			return "";
		}
		cell.setCellType(Cell.CELL_TYPE_STRING);
		return cell.getStringCellValue().trim();
	}

	/**
	 * 对excle中单元格数组就行检查
	 * 
	 * @param typeEnum
	 * @param value
	 * @param row
	 * @param column
	 */
	private void checkValue(TypeEnum typeEnum, String value, int row, int column) {
		try {
			if (typeEnum == TypeEnum.INT) {
				Integer.valueOf(value);
			} else if (typeEnum == TypeEnum.FLOAT) {
				Float.valueOf(value);
			} else if (typeEnum == TypeEnum.LONG) {
				Long.valueOf(value);
			} else if (typeEnum == TypeEnum.DOUBLE) {
				Double.valueOf(value);
			}
		} catch (Exception e) {
			throw new RuntimeException("numberFormatException:row=" + (row + 1)
					+ ",column=" + (column + 1));
		}
	}

	/**
	 * 获取非string类型的初始值
	 * 
	 * @param value
	 * @return
	 */
	private String getInitValue(String value) {
		if (value.equals("")) {
			return "0";
		}
		return value;
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
