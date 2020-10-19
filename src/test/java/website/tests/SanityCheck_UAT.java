package website.tests;

import java.io.File;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

import website.base.BaseTest;
import website.generic.ExcelUtility;

public class SanityCheck_UAT extends BaseTest {

	@Test(dataProvider = "orderflow")
	public void tc_01_PlaceAndOrder(String data[]) {
		logger = reporter.createTest("SanityCheck_UAT");
		logger.log(Status.INFO, "TestCase: " + data[0]);
		homepage.selectCountry(data[1]);
		logger.log(Status.INFO, "Selected the Country as:  " + data[1]);
		String parentwindow = driver.getWindowHandle();
		logger.log(Status.INFO, "Parent Window | Main Window" + parentwindow);
		switchToLatestWindow();
		homepage.closeNewsLetterPopUp();
		logger.log(Status.INFO, "Closed the News Letter pop up");
		System.out.println("Waiting for 10 seconds");
		homepage.clickOnProducts();
		logger.log(Status.INFO, "Click on Products in Home Page");
		// hardWait(5000);
		homepage.clickOnKettlesImage();
		logger.log(Status.INFO, "Clicked on Kettles from the PLP Page");
		String modelnumber = homepage.addKettleToCart();
		Assert.assertTrue(modelnumber != "");
		logger.log(Status.PASS, "Verifed Add To Cart functionality and the product model number is: " + modelnumber);
		productspage.clickGoToCart();
		logger.log(Status.INFO, "Clicked on Go to Cart");
		int productCount = transactionpage.getQuantity();
		logger.log(Status.INFO, "Products count in the Cart Page is: " + productCount);
		Assert.assertTrue(productCount > 0);
		logger.log(Status.PASS, "Verified that product has been added to the Cart successfully");
		boolean verifyUpdateQuantity = transactionpage.vefiryUpdateQuantity();
		System.out.println("Update Quantity: " + verifyUpdateQuantity);
		Assert.assertTrue(verifyUpdateQuantity);
		logger.log(Status.PASS, "Verified that User is able to update the Quantity and the quantity is:"
				+ transactionpage.getQuantity());
		hardWait(8000);
		searchpage.addSubscriptionProduct(data[1]);
		productspage.clickGoToCart();
		hardWait(5000);
		String form[] = { data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9] };
		if ("Registered".equalsIgnoreCase(data[10])) {
			String plan = transactionpage.getPlan();
			Assert.assertTrue(plan != null && plan != "");
			System.out.println("Verified that user is able to Add the subscription item to the Cart");
			logger.log(Status.PASS, "Verified that user is able to Add the subscription item to the Cart");
			boolean isSubscriptionPlanModified = transactionpage.vefiryUpdateplan();
			Assert.assertTrue(isSubscriptionPlanModified);
			logger.log(Status.PASS, "Verified that user is able to update the subscription plan");
			transactionpage.loginAndCheckout(data[11], data[12]);
		} else {
			transactionpage.clickCheckoutAsGuestButton(data[1]);
			transactionpage.fillTheForm(form);
		}
		String paymentdetails[] = { webprop.getProperty("name"), webprop.getProperty("cardNumber"),
				webprop.getProperty("expiry"), webprop.getProperty("cvvNumber"), data[1] };
		transactionpage.paymentUsingCreditCard(paymentdetails);
		String orderNumber = transactionpage.getPurchaseOrderId();
		Assert.assertTrue(orderNumber.length() > 0);
		logger.log(Status.PASS,
				"Verified that user is Successfully placed an Order and the Order number is: " + orderNumber);
		if ("Registered".equalsIgnoreCase(data[10])) {
			mybreville.clickMyBrevilleMenu();
			mybreville.clickPersonalDetails();
			String str = mybreville.getPhoneNumber_personalDetails();
			logger.log(Status.INFO, "Phone number value in personal details: " + str);
			System.out.println("Phone number value in personal details: " + str);
			mybreville.clickEditPersonalDetails();
			mybreville.updatePhoneNumber_PersonalDetails();
			mybreville.ClickSaveOption_PersonalDetails();
			String str1 = mybreville.getPhoneNumber_personalDetails();
			logger.log(Status.INFO, "Updated phone number value: " + str1);
			System.out.println("Updated phone number value: " + str1);
			Assert.assertTrue(!str.equalsIgnoreCase(str1));
			logger.log(Status.PASS, "Verified that user is able to update the personal details");
			mybreville.clickMyBrevilleMenu();
			mybreville.clickOnBrevilleHubPage();
			boolean verifyLinks_hibpage = mybreville.verifyLinksInHubpage();
			Assert.assertTrue(verifyLinks_hibpage);
			logger.log(Status.PASS, "Verified that the links are displaying properly in the hub page");
			mybreville.clickOnSubscription_hubpage();
			boolean flag = mybreville.verifyQuantityAndPlanUpdate();
			Assert.assertTrue(flag);
			logger.log(Status.PASS, "Verified that user is able to update the Quantity and Plan for a subscription");
			boolean flag1 = mybreville.verifyUpdateShippingAddr(data[13]);
			Assert.assertTrue(flag1);
			logger.log(Status.PASS, "Verified that user is able to update the Subscription Address for a subscription");
			Assert.assertTrue(mybreville.verifyCancelSubscription());
			logger.log(Status.PASS, "Verified that user is able to Cancel the subscription");
			mybreville.clickMyBrevilleMenu();
			mybreville.clickOnBrevilleHubPage();
			mybreville.clickOnOrders_hubpage();
			String ordNumber = mybreville.getFirstOrderNumber_OrdersPage();
			int retrycount = 0;
			while (orderNumber.equalsIgnoreCase(ordNumber) || retrycount < 2) {
				hardWait(5000);
				logger.log(Status.INFO,
						"Seems like its taking time to display the order in GUI. Retrying one more time..");
				mybreville.clickMyBrevilleMenu();
				mybreville.clickOnBrevilleHubPage();
				mybreville.clickOnOrders_hubpage();
				ordNumber = mybreville.getFirstOrderNumber_OrdersPage();
				retrycount++;
			}
			Assert.assertEquals(orderNumber, ordNumber);
			logger.log(Status.PASS, "Verified that created order is displayed in Orders page.");
		}

		driver.close();
		logger.log(Status.INFO, "Closed the child window");
		hardWait(2000);
		logger.log(Status.INFO, "Waiting for few seconds before switching to Parent Window");
		driver.switchTo().window(parentwindow);
		logger.log(Status.INFO, "Switched to the Parent | Main window");
		logger.log(Status.INFO, "-------TestCase_01: is Ended---");

	}

	@DataProvider(name = "orderflow")
	public Object[][] getData() {
		String path = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "testData.xlsx";
		int sheetIndex = 0;
		Object[][] data = ExcelUtility.getData(path, sheetIndex);
		return data;

	}

}
