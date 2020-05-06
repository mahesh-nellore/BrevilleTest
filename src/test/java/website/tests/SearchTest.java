package website.tests;

import java.io.File;
import java.util.Set;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import website.base.BaseTest;
import website.generic.ExcelUtility;

public class SearchTest extends BaseTest {

	SoftAssert softassert = new SoftAssert();

	@Test(dataProvider = "websiteSearch")
	public void verifySearchFunction(String searchValue) {
		logger = reporter.createTest("SearchTest");
		logger.log(Status.INFO, "Verify Search Function>>");
		homepage.clickOnSearch();
		System.out.println("Search Value>>" + searchValue);
		logger.log(Status.INFO, "Search Value is >>" + searchValue);
		homepage.searchForProduct(searchValue);
		int numberOfSearchResults = searchpage.getSearchResultCount();
		logger.log(Status.INFO, "The Total number of Search results>>" + numberOfSearchResults);
		softassert.assertTrue(numberOfSearchResults != 0);
		Set<String> listOfSearchResultNames = searchpage.getListOfNamesDisplayedAsPerSearch();
		listOfSearchResultNames.forEach((String name) -> {
			logger.log(Status.INFO, "Search Result>>" + name);
		});
		softassert.assertAll();

	}

	@DataProvider(name = "websiteSearch")
	public Object[][] getData() {
		String path = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "websiteTestData.xlsx";
		int sheetIndex = 0;
		Object[][] data = ExcelUtility.getData(path, sheetIndex);
		return data;
	}

}
