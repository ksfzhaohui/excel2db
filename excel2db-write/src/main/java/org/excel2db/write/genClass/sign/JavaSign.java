package org.excel2db.write.genClass.sign;

import org.excel2db.write.util.TypeEnum;

public class JavaSign {

	public static String Package = "package ";
	public static String Private = "private ";
	public static String Public = "public ";
	public static String Void = "void ";
	public static String Return = "return ";
	public static String This = "this";
	public static String Class = "class ";
	public static String Left_Brace = " {";
	public static String Right_Brace = "}";

	public static final String[] Tab = new String[] { "", "\t", "\t\t",
			"\t\t\t", "\t\t\t\t", "\t\t\t\t\t", "\t\t\t\t\t\t" };

	public static String fullType(TypeEnum type) {
		switch (type) {
		case INT:
			return "int ";
		case FLOAT:
			return "float ";
		case LONG:
			return "long ";
		case STRING:
			return "String ";
		case DOUBLE:
			return "double ";
		case INTS:
			return "int[] ";
		case FLOATS:
			return "float[] ";
		case LONGS:
			return "long[] ";
		case STRINGS:
			return "String[] ";
		case DOUBLES:
			return "double[] ";
		default:
			throw new RuntimeException(
					"error type:"
							+ type
							+ " support:int,float,long,string,double,ints,floats,longs,strings,doubles");
		}
	}
}
