package org.excel2db.write.util;

public class ExcelColumn {

	public static void main(String[] args) {
		String colstr = "AA";
		int colIndex = excelColStrToNum(colstr, colstr.length());
		System.out.println("'" + colstr + "' column index of " + colIndex);

		colIndex = 26;
		colstr = excelColIndexToStr(colIndex);
		System.out.println(colIndex + " column in excel of " + colstr);

		colstr = "AAAA";
		colIndex = excelColStrToNum(colstr, colstr.length());
		System.out.println("'" + colstr + "' column index of " + colIndex);

		colIndex = 466948;
		colstr = excelColIndexToStr(colIndex);
		System.out.println(colIndex + " column in excel of " + colstr);
	}

	/**
	 * Excel column index begin 1
	 * 
	 * @param colStr
	 * @param length
	 * @return
	 */
	public static int excelColStrToNum(String colStr, int length) {
		int num = 0;
		int result = 0;
		for (int i = 0; i < length; i++) {
			char ch = colStr.charAt(length - i - 1);
			num = (int) (ch - 'A' + 1);
			num *= Math.pow(26, i);
			result += num;
		}
		return result;
	}

	/**
	 * Excel column index begin 1
	 * 
	 * @param columnIndex
	 * @return
	 */
	public static String excelColIndexToStr(int columnIndex) {
		if (columnIndex <= 0) {
			return null;
		}
		String columnStr = "";
		columnIndex--;
		do {
			if (columnStr.length() > 0) {
				columnIndex--;
			}
			columnStr = ((char) (columnIndex % 26 + (int) 'A')) + columnStr;
			columnIndex = (int) ((columnIndex - columnIndex % 26) / 26);
		} while (columnIndex > 0);
		return columnStr;
	}
}
