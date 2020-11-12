package website.tests;

import java.io.File;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import website.base.BaseTest;
import website.generic.ExcelUtility;

public class SanityCheck_UAT2_backup extends BaseTest {

	@Test(dataProvider = "orderflow")
	public void tc_01_PlaceAndOrder(String data[]) {
		//String parentwindow = "";
		try {
			logger = reporter.createTest("SanityCheck:"+data[1]);
			logger.log(Status.INFO, "TestCase: " + data[0]);
			homepage.selectCountry(data[1]);
			parentWindow = driver.getWindowHandle();
			switchToLatestWindow();
			logger.log(Status.INFO, "Switched to latest window");
			if (homepage.verifyNewsLetterPopUpIsDisplayed()) {
				homepage.closeNewsLetterPopUp();
			} else if (homepage.verifyEvidonAcceptButton()) {
				homepage.acceptEvidonAcceptButton();
			}
			homepage.clickOnProducts();
			homepage.clickOnKettlesImage();
			String modelnumber = homepage.addKettleToCart();
			Assert.assertTrue(modelnumber != "", "The User failed to add the FG product to the cart. Hence the test case got failed.");
			logger.log(Status.PASS,
					"It has been validated that the user can add the FG product to the Cart" + modelnumber);
			productspage.clickGoToCart();			
			int productCount = transactionpage.getQuantity();
			Assert.assertTrue(productCount > 0, "User is Failed to add the product to the cart");
			logger.log(Status.PASS, "Verified that product has been added to the Cart successfully");
			boolean verifyUpdateQuantity = transactionpage.vefiryUpdateQuantity();
			Assert.assertTrue(verifyUpdateQuantity, "User is failed to update the quantity in cart page");
			logger.log(Status.PASS, "Verified that User is able to update the Quantity and the quantity is:"
					+ transactionpage.getQuantity());
			searchpage.addSubscriptionProduct(data[1]);
			productspage.clickGoToCart();
			String form[] = { data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[1] };
			if ("Registered".equalsIgnoreCase(data[10])) {
				/*String plan = transactionpage.getPlan();
				Assert.assertTrue(plan != null && plan != "",
						"User is failed to add the subscription item to the cart");
				System.out.println("Verified that user is able to Add the subscription item to the Cart");
				logger.log(Status.PASS, "Verified that user is able to Add the subscription item to the Cart");*/
				boolean isSubscriptionPlanModified = transactionpage.vefiryUpdateplan();
				Assert.assertTrue(isSubscriptionPlanModified,
						"User is failed to update the plan in cart page for the subscription product");
				logger.log(Status.PASS, "Verified that user is able to update the subscription plan");
				boolean verifyLogin = transactionpage.loginAndCheckout(data[11], data[12]);
				Assert.assertTrue(verifyLogin, "User is Failed to Login..");
			} else {
				transactionpage.clickCheckoutAsGuestButton(data[1]);
				transactionpage.fillTheForm(form);
			}
			String paymentdetails[] = { webprop.getProperty("name"), webprop.getProperty("cardNumber"),
					webprop.getProperty("expiry"), webprop.getProperty("cvvNumber"), data[1] };
			transactionpage.paymentUsingCreditCard(paymentdetails);
			String orderNumber_ConfirmationPage = transactionpage.getPurchaseOrderId();
			Assert.assertTrue(orderNumber_ConfirmationPage.length() > 0, "User is failed to place an Order");
			logger.log(Status.PASS, "Verified that user is Successfully placed an Order and the Order number is: "
					+ orderNumber_ConfirmationPage);
			if ("Registered".equalsIgnoreCase(data[10])) {
				mybreville.clickMyBrevilleMenu();
				mybreville.clickPersonalDetails();
				String str = mybreville.getPhoneNumber_personalDetails();
				logger.log(Status.INFO, "Phone number value in personal details: " + str);
				//System.out.println("Phone number value in personal details: " + str);
				mybreville.clickEditPersonalDetails();
				mybreville.updatePhoneNumber_PersonalDetails();
				mybreville.ClickSaveOption_PersonalDetails();
				String str1 = mybreville.getPhoneNumber_personalDetails();
				logger.log(Status.INFO, "Updated phone number value: " + str1);
				//System.out.println("Updated phone number value: " + str1);
				Assert.assertTrue(!str.equalsIgnoreCase(str1),
						"User is failed update the phone number in personal details");
				logger.log(Status.PASS, "Verified that user is able to update the personal details");
				mybreville.clickMyBrevilleMenu();
				mybreville.clickOnBrevilleHubPage();
				boolean verifyLinks_hibpage = mybreville.verifyLinksInHubpage();
				Assert.assertTrue(verifyLinks_hibpage, "Failed to verify the links in the hub page");
				logger.log(Status.PASS, "Verified that the links are displaying properly in the hub page");
				mybreville.clickOnSubscription_hubpage();
				boolean flag = mybreville.verifyQuantityAndPlanUpdate();
				Assert.assertTrue(flag,
						"User is failed to update the quantity for the subscription product in subscription page");
				logger.log(Status.PASS,
						"Verified that user is able to update the Quantity and Plan for a subscription");
				boolean flag1 = mybreville.verifyUpdateShippingAddr(data[13]);
				Assert.assertTrue(flag1,
						"User is failed to update the shipping address for the subscription product in subscription page");
				logger.log(Status.PASS,
						"Verified that user is able to update the Subscription Address for a subscription");
				Assert.assertTrue(mybreville.verifyCancelSubscription(), "User is failed to cancel the subscription");
				logger.log(Status.PASS, "Verified that user is able to Cancel the subscription");
				mybreville.clickMyBrevilleMenu();
				mybreville.clickOnBrevilleHubPage();
				mybreville.clickOnOrders_hubpage();
				String ordNumber = mybreville.getFirstOrderNumber_OrdersPage();
				int retrycount = 0;
				while (retrycount < 3) {
					System.out.println("Order Number from Confirmation page is: " + orderNumber_ConfirmationPage);
					System.out.println("Order Number from Order page is: " + ordNumber);
					if (ordNumber.trim().equalsIgnoreCase(orderNumber_ConfirmationPage)) {
						break;
					}
					hardWait(5000);
					logger.log(Status.INFO,
							"Seems like its taking time to display the order in GUI. Retrying one more time..");
					mybreville.clickMyBrevilleMenu();
					mybreville.clickOnBrevilleHubPage();
					mybreville.clickOnOrders_hubpage();
					ordNumber = mybreville.getFirstOrderNumber_OrdersPage();
					retrycount++;

				}
				Assert.assertEquals(orderNumber_ConfirmationPage.trim(), ordNumber.trim(),
						"Failed to verify the order placed by the user in orders page, Seems like order is not displayed in Orders page");
				logger.log(Status.PASS, "Verified that created order is displayed in Orders page.");
			}
		} catch (Exception e) {
			logger.log(Status.INFO, "Exception: " + e.getMessage());
			System.out.println("Exception is: "+e.getMessage());
			Assert.fail("Exception occured hence failing the test case.");
		}
	}

	@DataProvider(name = "orderflow")
	public Object[][] getData() {
		String path = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "testData.xlsx";
		int sheetIndex = 1;
		Object[][] data = ExcelUtility.getData(path, sheetIndex);
		return data;

	}
}
