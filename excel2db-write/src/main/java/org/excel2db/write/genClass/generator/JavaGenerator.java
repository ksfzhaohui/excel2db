package org.excel2db.write.genClass.generator;

import java.util.List;

import org.excel2db.write.genClass.AbstractGenerator;
import org.excel2db.write.util.TypeEnum;

/**
 * java类生成器
 * 
 * @author zhaohui
 * 
 */
public class JavaGenerator extends AbstractGenerator {

	@Override
	public void generatorBean() {
		out.println(JavaSign.Package + config.getPackageRoot() + ";");
		out.println();

		out.println(JavaSign.Public + JavaSign.Class
				+ toFirstUpperCase(info.getName()) + " {");
		out.println();

		List<String> columnNames = info.getColumnNames();
		List<TypeEnum> typeEnums = info.getTypeEnums();

		for (int i = 0; i < typeEnums.size(); i++) {
			TypeEnum type = typeEnums.get(i);
			String columnName = columnNames.get(i);
			out.println(JavaSign.Tab[1] + JavaSign.Private
					+ JavaSign.fullType(type) + columnName + ";");
		}
		out.println();

		for (int i = 0; i < typeEnums.size(); i++) {
			TypeEnum type = typeEnums.get(i);
			String columnName = columnNames.get(i);

			out.println(JavaSign.Tab[1] + JavaSign.Public
					+ JavaSign.fullType(type) + toGetMethod(columnName) + " {");
			out.println(JavaSign.Tab[2] + JavaSign.Return + columnName + ";");
			out.println(JavaSign.Tab[1] + "}");
			out.println();

			out.println(JavaSign.Tab[1] + JavaSign.Public + JavaSign.Void
					+ toSetMethod(columnName, JavaSign.fullType(type)) + " {");
			out.println(JavaSign.Tab[2] + JavaSign.This + "." + columnName
					+ " = " + columnName + ";");
			out.println(JavaSign.Tab[1] + "}");
			out.println();
		}
		out.println("}");
	}

	private String toGetMethod(String columnName) {
		return "get" + toFirstUpperCase(columnName) + "()";
	}

	private String toSetMethod(String columnName, String typeStr) {
		return "set" + toFirstUpperCase(columnName) + "(" + typeStr
				+ columnName + ")";
	}

}
