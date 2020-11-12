package website.tests;

import java.io.File;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import website.base.BaseTest;
import website.generic.ExcelUtility;

public class SanityCheck extends BaseTest {

	@Test(dataProvider = "ca", priority = 1, enabled = false)
	public void tc_01_PlaceAndOrderForCA(String data[]) {
			logger = reporter.createTest("SanityCheck: "+data[0]);
			logger.log(Status.INFO, "TestCase: " + data[0]);
			platformregistration.placeOrderForRegisteredUser(data);
			
	}
	
	@Test(dataProvider = "us", priority = 2)
	public void tc_01_PlaceAndOrderForUS(String data[]) {
		logger = reporter.createTest("SanityCheck: "+data[0]);
		logger.log(Status.INFO, "TestCase: " + data[0]);
		platformregistration.placeOrderForRegisteredUser(data);
	}

	@DataProvider(name = "ca")
	public Object[][] getData_ca() {
		String path = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "testData.xlsx";
		int sheetIndex = 1;
		Object[][] data = ExcelUtility.getData(path, sheetIndex);
		return data;

	}
	@DataProvider(name = "us")
	public Object[][] getData_us() {
		String path = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "testData.xlsx";
		int sheetIndex = 2;
		Object[][] data = ExcelUtility.getData(path, sheetIndex);
		return data;

	}
}
