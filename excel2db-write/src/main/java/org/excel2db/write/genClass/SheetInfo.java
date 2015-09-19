package org.excel2db.write.genClass;

import java.util.List;

import org.excel2db.write.TypeEnum;

public class SheetInfo {

	private String name;
	private List<String> columnNames;
	private List<TypeEnum> typeEnums;

	public SheetInfo(String name, List<String> columnNames,
			List<TypeEnum> typeEnums) {
		this.name = name;
		this.columnNames = columnNames;
		this.typeEnums = typeEnums;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}

	public List<TypeEnum> getTypeEnums() {
		return typeEnums;
	}

	public void setTypeEnums(List<TypeEnum> typeEnums) {
		this.typeEnums = typeEnums;
	}

}
