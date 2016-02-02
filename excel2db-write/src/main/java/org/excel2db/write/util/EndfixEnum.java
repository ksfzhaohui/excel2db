package org.excel2db.write.util;

public enum EndfixEnum {

	JAVA("java", ".java"), //
	CSHARP("csharp", ".cs");

	private String language;
	private String endfix;

	EndfixEnum(String language, String endfix) {
		this.language = language;
		this.endfix = endfix;
	}

	public static String endfix(String language) {
		if (language.equals(JAVA.language)) {
			return JAVA.endfix;
		} else if (language.equals(CSHARP.language)) {
			return CSHARP.endfix;
		} else {
			throw new RuntimeException("error language:" + language
					+ " support:java,csharp");
		}
	}

	public static EndfixEnum getType(String language) {
		if (language.equals(JAVA.language)) {
			return JAVA;
		} else if (language.equals(CSHARP.language)) {
			return CSHARP;
		} else {
			throw new RuntimeException("error language:" + language
					+ " support:java,csharp");
		}
	}

	public String getEndfix() {
		return endfix;
	}

	public String getLanguage() {
		return language;
	}

}
