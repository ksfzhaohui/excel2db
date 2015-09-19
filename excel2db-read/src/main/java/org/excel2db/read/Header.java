package org.excel2db.read;

public class Header {

	/** 记录数量 **/
	private int recordSize;
	/** 字段数量 **/
	private int fieldSize;
	/** 名称buff的长度 **/
	private int namebuflength;
	/** 类型buff的长度 **/
	private int typebuflength;
	/** 数据buff的长度 **/
	private int databuflength;

	public Header(int recordSize, int fieldSize, int namebuflength,
			int typebuflength, int databuflength) {
		this.recordSize = recordSize;
		this.fieldSize = fieldSize;
		this.namebuflength = namebuflength;
		this.typebuflength = typebuflength;
		this.databuflength = databuflength;
	}

	@Override
	public String toString() {
		return "headerInfo:[recordSize=" + recordSize + ",fieldSize="
				+ fieldSize + ",namebuflength=" + namebuflength
				+ ",typebuflength=" + typebuflength + ",databuflength="
				+ databuflength + "]";
	}

	public int getRecordSize() {
		return recordSize;
	}

	public void setRecordSize(int recordSize) {
		this.recordSize = recordSize;
	}

	public int getFieldSize() {
		return fieldSize;
	}

	public void setFieldSize(int fieldSize) {
		this.fieldSize = fieldSize;
	}

	public int getNamebuflength() {
		return namebuflength;
	}

	public void setNamebuflength(int namebuflength) {
		this.namebuflength = namebuflength;
	}

	public int getTypebuflength() {
		return typebuflength;
	}

	public void setTypebuflength(int typebuflength) {
		this.typebuflength = typebuflength;
	}

	public int getDatabuflength() {
		return databuflength;
	}

	public void setDatabuflength(int databuflength) {
		this.databuflength = databuflength;
	}

}
