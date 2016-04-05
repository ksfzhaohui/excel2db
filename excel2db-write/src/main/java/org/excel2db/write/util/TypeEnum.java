package org.excel2db.write.util;

public enum TypeEnum {

	INT('i'), FLOAT('f'), LONG('l'), STRING('s'), DOUBLE('d');

	private char value;

	private TypeEnum(char value) {
		this.value = value;
	}

	public byte value() {
		return (byte) value;
	}

	public static int size(TypeEnum type) {
		if (type == INT || type == FLOAT) {
			return 4;
		} else if (type == LONG || type == DOUBLE) {
			return 8;
		} else {
			throw new RuntimeException("error type:" + type
					+ " support:int,float,long,double");
		}
	}

	public static TypeEnum type(String type) {
		if (type.equalsIgnoreCase("int")) {
			return INT;
		} else if (type.equalsIgnoreCase("float")) {
			return FLOAT;
		} else if (type.equalsIgnoreCase("long")) {
			return LONG;
		} else if (type.equalsIgnoreCase("string")) {
			return STRING;
		} else if (type.equalsIgnoreCase("double")) {
			return DOUBLE;
		} else {
			return null;
		}
	}

}
