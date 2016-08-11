package org.excel2db.read;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.excel2db.read.util.Constants;
import org.excel2db.read.util.TypeEnum;

/**
 * ndb文件的抽象类
 * 
 * @author zhaohui
 * 
 * @param <T>
 */
public class DBFile {
	private final static Logger logger = Logger.getLogger(DBFile.class);

	/** header的长度 **/
	private static final int HEADER_LENGTH = 5 * 4;
	/** 字符串编码格式 **/
	private static final String STRING_ENCODING = "UTF-8";

	private Header header;
	private List<String> columnNames = new ArrayList<String>();
	private List<TypeEnum> typeEnums = new ArrayList<TypeEnum>();
	private List<Map<String, Object>> dataMap = new ArrayList<Map<String, Object>>();

	public void init(String filePath) {
		FileChannel fc = null;
		try {
			fc = new FileInputStream(filePath).getChannel();
			parseHeader(fc);
			parseColumnName(fc);
			parseColumnType(fc);
			parseDate(fc);
		} catch (Exception e) {
			logger.error("init dbfile error", e);
		} finally {
			if (fc != null) {
				try {
					fc.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * 解析字段的类型
	 * 
	 * @param fc
	 * @throws IOException
	 */
	private void parseDate(FileChannel fc) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(header.getDatabuflength());
		fc.read(buffer);
		buffer.flip();
		for (int i = 0; i < header.getRecordSize(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int k = 0; k < typeEnums.size(); k++) {
				TypeEnum type = typeEnums.get(k);
				String column = columnNames.get(k);
				Object obj = null;
				if (type == TypeEnum.INT) {
					obj = buffer.getInt();
				} else if (type == TypeEnum.FLOAT) {
					obj = buffer.getFloat();
				} else if (type == TypeEnum.LONG) {
					obj = buffer.getLong();
				} else if (type == TypeEnum.STRING) {
					obj = getString(buffer);
				} else if (type == TypeEnum.DOUBLE) {
					obj = buffer.getDouble();
				} else if (type == TypeEnum.INTS) {
					String values[] = getString(buffer).split(
							Constants.SEPARATOR);
					int ints[] = new int[values.length];
					for (int j = 0; j < values.length; j++) {
						ints[j] = Integer.valueOf(values[j]);
					}
					obj = ints;
				} else if (type == TypeEnum.FLOATS) {
					String values[] = getString(buffer).split(
							Constants.SEPARATOR);
					float floats[] = new float[values.length];
					for (int j = 0; j < values.length; j++) {
						floats[j] = Float.valueOf(values[j]);
					}
					obj = floats;
				} else if (type == TypeEnum.LONGS) {
					String values[] = getString(buffer).split(
							Constants.SEPARATOR);
					long longs[] = new long[values.length];
					for (int j = 0; j < values.length; j++) {
						longs[j] = Long.valueOf(values[j]);
					}
					obj = longs;
				} else if (type == TypeEnum.STRINGS) {
					obj = getString(buffer).split(Constants.SEPARATOR);
				} else if (type == TypeEnum.DOUBLES) {
					String values[] = getString(buffer).split(
							Constants.SEPARATOR);
					double doubles[] = new double[values.length];
					for (int j = 0; j < values.length; j++) {
						doubles[j] = Double.valueOf(values[j]);
					}
					obj = doubles;
				}
				map.put(column, obj);
			}
			dataMap.add(map);
		}
	}

	/**
	 * 解析字段的类型
	 * 
	 * @param fc
	 * @throws IOException
	 */
	private void parseColumnType(FileChannel fc) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(header.getTypebuflength());
		fc.read(buffer);
		buffer.flip();
		for (int i = 0; i < header.getFieldSize(); i++) {
			byte b = buffer.get();
			typeEnums.add(TypeEnum.type(b));
		}
		logger.info(typeEnums);
	}

	/**
	 * 解析字段的名称
	 * 
	 * @param fc
	 * @throws IOException
	 */
	private void parseColumnName(FileChannel fc) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(header.getNamebuflength());
		fc.read(buffer);
		buffer.flip();
		for (int i = 0; i < header.getFieldSize(); i++) {
			columnNames.add(getString(buffer));
		}
		logger.info(columnNames);
	}

	/**
	 * 解析头部
	 * 
	 * @param fc
	 * @throws IOException
	 */
	private void parseHeader(FileChannel fc) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(HEADER_LENGTH);
		fc.read(buffer);
		buffer.flip();
		int records = buffer.getInt();
		int fields = buffer.getInt();
		int namebuflength = buffer.getInt();
		int typebuflength = buffer.getInt();
		int databuflength = buffer.getInt();
		header = new Header(records, fields, namebuflength, typebuflength,
				databuflength);
		logger.info(header.toString());
	}

	private String getString(ByteBuffer buffer)
			throws UnsupportedEncodingException {
		int len = buffer.getInt();
		byte dst[] = new byte[len];
		buffer.get(dst);
		return new String(dst, STRING_ENCODING);
	}

	public List<Map<String, Object>> getDataMap() {
		return dataMap;
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

	public List<TypeEnum> getTypeEnums() {
		return typeEnums;
	}

	public void setTypeEnums(List<TypeEnum> typeEnums) {
		this.typeEnums = typeEnums;
	}

}
