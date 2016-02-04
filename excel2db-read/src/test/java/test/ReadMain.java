package test;

public class ReadMain {

	public static void main(String[] args) {
		GeneralDataManager generalDataManager = new GeneralDataManager();
		generalDataManager.setNdbFilePath("D:\\ndbtest\\excelPath");
		generalDataManager.init();
	}
}
