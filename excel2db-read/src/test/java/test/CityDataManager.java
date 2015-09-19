package test;

import java.util.List;

import org.excel2db.read.NdbDataManager;

public class CityDataManager extends NdbDataManager<CityData> {

	@Override
	public void init() {
		super.init();

		List<CityData> list = getBeanList();
		for (CityData city : list) {
			System.out.println(city.getName1() + "," + city.getName2() + ","
					+ city.getName3() + "," + city.getName4());
		}
	}

	@Override
	public String getNdbName() {
		return "city";
	}

	@Override
	public Class<CityData> clazz() {
		return CityData.class;
	}

}
