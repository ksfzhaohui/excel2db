package test;

public class ReadMain {

	public static void main(String[] args) {
//		GeneralDataManager generalDataManager = new GeneralDataManager();
//		generalDataManager.setNdbFilePath("D:\\ndbtest");
//		generalDataManager.init();
		
		ArrayTestDataManager arrayTestDataManager = new ArrayTestDataManager();
		arrayTestDataManager.setNdbFilePath("D:\\ndbtest");
		arrayTestDataManager.init();
	}
}
