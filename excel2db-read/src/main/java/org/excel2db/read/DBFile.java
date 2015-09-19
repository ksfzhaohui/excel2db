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

public class DBFile<T> {
	private final static Logger logger = Logger.getLogger(DBFile.class);

	/** header的长度 **/
	private static final int HEADER_LENGTH = 5 * 4;

	private Header header;
	private List<String> columnNames = new ArrayList<String>();
	private List<TypeEnum> typeEnums = new ArrayList<TypeEnum>();
	private List<Map<String, Object>> dataMap = new ArrayList<Map<String, Object>>();
	private List<T> beanList = new ArrayList<T>();

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
		return new String(dst, "UTF-8");
	}

	public List<Map<String, Object>> getDataMap() {
		return dataMap;
	}

	public List<T> getBeanList() {
		return beanList;
	}

}
