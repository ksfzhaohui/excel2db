package test;

import java.util.List;

import org.excel2db.read.NdbDataManager;

public class ArrayTestDataManager extends NdbDataManager<ArrayTestData> {

	@Override
	public void init() {
		super.init();

		List<ArrayTestData> list = getBeanList();
		for (ArrayTestData array : list) {
			System.out.println(array.getPid().length + "," + array.getName2().length + ","
					+ array.getName3().length + "," + array.getName4().length + ","
					+ array.getName5().length);
		}
	}

	@Override
	public String getNdbName() {
		return "P_arrayTest";
	}

	@Override
	public Class<ArrayTestData> clazz() {
		return ArrayTestData.class;
	}

}
