package website.tests;

import java.util.Set;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import website.base.BaseTest;

public class HomeTest extends BaseTest {

	SoftAssert softassert = new SoftAssert();

	@Test(priority = 0)
	public void verifyHomePage() {
		logger = reporter.createTest("HomeTest");
		String title = homepage.getTitle();
		logger.log(Status.INFO, "Actual title>>" + title);
		String expectedtitle = webprop.getProperty("title");
		logger.log(Status.INFO, "Expected title>>" + expectedtitle);
		softassert.assertEquals(title, expectedtitle);
		softassert.assertAll();
	}

	@Test(priority = 1)
	public void verifyLabels() {
		logger.log(Status.INFO, "Verified Logo");
		softassert.assertTrue(homepage.verifyLogo());
		logger.log(Status.INFO, "Verified Product Label");
		softassert.assertTrue(homepage.verifyProductLabel());
		logger.log(Status.INFO, "Verified Search Option");
		softassert.assertTrue(homepage.verifySearchOption());
		logger.log(Status.INFO, "Verified Lets Chat Button to Make Sure Page Loaded successfully");
		softassert.assertTrue(homepage.verifyLetsChatButton());
		softassert.assertAll();
	}

	@Test(priority = 2)
	public void verifyProducts() {
		logger.log(Status.INFO, "Verify Products Link>>");
		logger.log(Status.INFO, "Clicked On product Link");
		homepage.clickOnProducts();
		int countOfProducts = homepage.numberOfproductsCount();
		logger.log(Status.INFO, "Count of the Products Displayed>>" + countOfProducts);
		softassert.assertTrue(countOfProducts > 0);
		Set<String> listofProductNames = homepage.getListOfproducts();
		listofProductNames.forEach((String name) -> {
			logger.log(Status.INFO, "Name of the product>>" + name);
		});
		logger.log(Status.INFO, "Verified Products Link>>");
		softassert.assertAll();
	}

	@Test(priority = 3)
	public void verifyPartsAndAccessories() {
		logger.log(Status.INFO, "Verify Parts and Accessories Link>>");
		logger.log(Status.INFO, "Clicked On Parts and Accessories Link");
		homepage.clickOnPartsAndAccessoriesLink();
		int countOfPartsAndAccessories = homepage.numberOfPartsAndAccessoriesCount();
		logger.log(Status.INFO, "Count of the Parts And Accessories Displayed>>" + countOfPartsAndAccessories);
		softassert.assertTrue(countOfPartsAndAccessories > 0);
		Set<String> listofPartsAndAccessories = homepage.getListOfPartsAndAccessories();
		listofPartsAndAccessories.forEach((String name) -> {
			logger.log(Status.INFO, "Name of the Parts and Accessories>>" + name);
		});
		logger.log(Status.INFO, "Verified Parts And Accessories Link>>");
		softassert.assertAll();
	}

}
