package test;

public class ReadMain {

	public static void main(String[] args) {
		CityDataManager cityDataManager = new CityDataManager();
		cityDataManager.setNdbFilePath("D:\\ndbtest\\excelPath");
		cityDataManager.init();
	}
}
