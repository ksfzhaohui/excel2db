package org.excel2db.write.genClass.sign;

import org.excel2db.write.util.TypeEnum;

public class CSharpSign {

	public static String Private = "private ";
	public static String Public = "public ";
	public static String Void = "void ";
	public static String Return = "return ";
	public static String This = "this";
	public static String Class = "class ";
	public static String NameSpace = "namespace ";

	public static final String[] using = new String[] { "using UnityEngine;",
			"using System.Collections;" };

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
			return "string ";
		case DOUBLE:
			return "double ";
		default:
			throw new RuntimeException("error type:" + type
					+ " support:int,float,long,string,double");
		}
	}

}
