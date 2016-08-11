package org.excel2db.read.util;

public enum TypeEnum {

	INT('i'), FLOAT('f'), LONG('l'), STRING('s'), DOUBLE('d'), INTS('x'), FLOATS(
			'y'), LONGS('z'), STRINGS('j'), DOUBLES('k');

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
		} else if (type == 'x') {
			return INTS;
		} else if (type == 'y') {
			return FLOATS;
		} else if (type == 'z') {
			return LONGS;
		} else if (type == 'j') {
			return STRINGS;
		} else if (type == 'k') {
			return DOUBLES;
		} else {
			throw new RuntimeException(
					"error type:"
							+ type
							+ "support:int,float,long,string,double,ints,floats,longs,strings,doubles");
		}
	}

}
