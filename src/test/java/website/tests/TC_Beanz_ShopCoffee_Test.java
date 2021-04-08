package website.tests;

import java.io.File;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import website.base.BaseTest;
import website.generic.ExcelUtility;
import website.generic.GenericUtility;

public class TC_Beanz_ShopCoffee_Test extends BaseTest {
	
	private String email;
	Map<String, String> map;
	
	@Test(dataProvider = "orderflow")
	public void shopCoffeeLabel(String data[]) {
		try {
			logger = reporter.createTest("SanityCheck:" + data[1]);
			logger.log(Status.INFO, "TestCase: " + data[0]);
			parentWindow = driver.getWindowHandle();
			switchToLatestWindow();
			logger.log(Status.INFO, "Switched to latest window");
			if (beanzHomepage.verifyEvidonAcceptButton()) {
				beanzHomepage.acceptEvidonAcceptButton();
			}
			beanzHomepage.clickOnShopCoffee(data[1]);
			hardWait(2000);
			//String nameOfCoffeeBeanz = beanzHomepage.addKettleToCart();
			beanzHomepage.addKettleToCart();
			hardWait(2000);
			addToCartPage.clickGoToCart();
			
			Assert.assertTrue(beanzTransactionpage.verifyMultipleOptionDisplayedOnCartPage(),
					"Failed to display login and checkout as guest to the user in the cart page. Hence the test case failed.");
			logger.log(Status.PASS,
					"It has been validated that the cart page is providing multiple options login & checkout As Guest to the users.");
			String form[] = { data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[1] };
			
			if ("Registered".equalsIgnoreCase(data[10]) || "NewUser".equalsIgnoreCase(data[10])) {
				boolean isSubscriptionPlanModified = beanzTransactionpage.vefiryUpdateplan();
				
				Assert.assertTrue(isSubscriptionPlanModified,
						"The user failed to update the plan for the subscription product in the cart page. Hence the test case failed.");
				logger.log(Status.PASS,
						"It has been verified that the user is able to update the plan for the subscriptionproduct in the cart page");
				Assert.assertFalse(beanzTransactionpage.verifyCheckoutAsGuestOptionInCartPage(),
						"The cart is still showing up checkout as guest option to the user when there is a subscription item. Hence the test case failed.");
				logger.log(Status.PASS,
						"It has been validated that the cart is not showing up the Checkout As Guest option to the users when there is a subscription product in the Cart.");
			
				// Get the random Email
				if ("NewUser".equalsIgnoreCase(data[10])) {
					email = "test" + GenericUtility.generateEmail() + "@yopmail.com";
					logger.log(Status.INFO, "This is for New User Creation CA Region: " + email);
				} 
				 else if ("Registered".equalsIgnoreCase(data[10])) {
					email = email_ca;
					logger.log(Status.INFO, "This is for CA Registered User: " + email);
				}
				if ("NewUser".equalsIgnoreCase(data[10])) {
					boolean verifyCreateAccount = beanzTransactionpage.loginAndCheckout_NewUser(email, data[12]);
					Assert.assertTrue(verifyCreateAccount,
							"The user is unable to create an account. Hence the test case failed.");
					logger.log(Status.PASS, "Verified that User is able to create the account successfully");
				} else {
					boolean verifyLogin = false;
					
					if (email != null || email != "" || !email.isEmpty())
						verifyLogin = beanzTransactionpage.loginAndCheckout(email, data[12]);
						
					else
						verifyLogin = transactionpage.loginAndCheckout(data[11], data[12]);
					Assert.assertTrue(verifyLogin, "The user is unable to login. Hence the test case failed.");
					logger.log(Status.PASS, "Verified that User is able to login successfully with the created credentials");
				}	
			}
			else {
				beanzTransactionpage.clickCheckoutAsGuestButton(data[1]);
				beanzTransactionpage.fillTheForm(form);
			}
			
			
			//hardWait(2000);
			//beanzTransactionpage.selectEveryDropdown();
			//beanzTransactionpage.selectSendMeDropdown();

			
		}
		catch (Exception e) {
			logger.log(Status.INFO, "Exception: " + e.getMessage());
			Assert.fail("Exception occured hence failing the test case.");
		}	
	}
	
	@DataProvider(name = "orderflow")
	public Object[][] getData() {
		String path = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "testData.xlsx";
		int sheetIndex = 0;
		Object[][] data = ExcelUtility.getData(path, sheetIndex);
		return data;

	}

}
