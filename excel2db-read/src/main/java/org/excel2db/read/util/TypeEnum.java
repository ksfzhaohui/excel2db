package org.excel2db.read.util;

public enum TypeEnum {

	INT('i'), FLOAT('f'), LONG('l'), STRING('s'), DOUBLE('d');

	private char value;

	private TypeEnum(char value) {
		this.value = value;
	}

	public char getValue() {
		return value;
	}

	public static TypeEnum type(byte b) {
		char type = (char) b;
		if (type == 'i') {
			return INT;
		} else if (type == 'f') {
			return FLOAT;
		} else if (type == 'l') {
			return LONG;
		} else if (type == 's') {
			return STRING;
		} else if (type == 'd') {
			return DOUBLE;
		} else {
			throw new RuntimeException("error type:" + type
					+ "support:int,float,long,string,double");
		}
	}

}
