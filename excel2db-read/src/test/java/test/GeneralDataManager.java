package test;

import java.util.List;

import org.excel2db.read.NdbDataManager;

public class GeneralDataManager extends NdbDataManager<GeneralData> {

	@Override
	public void init() {
		super.init();

		List<GeneralData> list = getBeanList();
		for (GeneralData city : list) {
			System.out.println(city.getName1() + "," + city.getName2() + ","
					+ city.getName3() + "," + city.getName4());
		}
	}

	@Override
	public String getNdbName() {
		return "general";
	}

	@Override
	public Class<GeneralData> clazz() {
		return GeneralData.class;
	}

}
