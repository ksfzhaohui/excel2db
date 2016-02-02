package org.excel2db.write.genClass;

import java.util.HashMap;
import java.util.Map;

import org.excel2db.write.genClass.generator.CSharpGenerator;
import org.excel2db.write.genClass.generator.JavaGenerator;

public class GeneratorFactory {

	private static Map<String, IGenerator> generatorMap = new HashMap<String, IGenerator>();

	static {
		generatorMap.put("java", new JavaGenerator());
		generatorMap.put("csharp", new CSharpGenerator());
	}

	public static IGenerator getGenerator(String language) {
		IGenerator generator = generatorMap.get(language);
		if (generator == null) {
			throw new RuntimeException("error language:" + language
					+ " support:java,csharp");
		}
		return generator;
	}

}
