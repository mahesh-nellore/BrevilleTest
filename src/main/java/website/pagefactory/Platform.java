package website.pagefactory;

import org.testng.Assert;

import com.aventstack.extentreports.Status;

import website.base.BaseTest;

public class Platform extends BaseTest {

	private static Platform platformregistration = null;

	public static Platform getPlatformRegistration() {
		if (platformregistration == null)
			platformregistration = new Platform();
		return platformregistration;
	}

	public void placeOrderForRegisteredUser(String data[]) {
		homepage.selectCountry(data[1]);
		logger.log(Status.INFO, "Selected the Country as:  " + data[1]);
		parentWindow = driver.getWindowHandle();
		switchToLatestWindow();
		if (homepage.verifyNewsLetterPopUpIsDisplayed()) {
			homepage.closeNewsLetterPopUp();
			logger.log(Status.INFO, "Closed the News Letter pop up");
		} else if (homepage.verifyEvidonAcceptButton()) {
			homepage.acceptEvidonAcceptButton();
		}
		homepage.clickOnProducts();
		logger.log(Status.INFO, "Click on Products in Home Page");
		homepage.clickOnKettlesImage();
		logger.log(Status.INFO, "Clicked on Kettles from the PLP Page");
		String modelnumber = homepage.addKettleToCart();
		Assert.assertTrue(modelnumber != "", "Seems like Add To Cart is not available for product");
		logger.log(Status.PASS, "Verifed Add To Cart functionality and the product model number is: " + modelnumber);
		productspage.clickGoToCart();
		logger.log(Status.INFO, "Clicked on Go to Cart");
		int productCount = transactionpage.getQuantity();
		logger.log(Status.INFO, "Products count in the Cart Page is: " + productCount);
		Assert.assertTrue(productCount > 0, "User is Failed to add the product to the cart");
		logger.log(Status.PASS, "Verified that product has been added to the Cart successfully");
		boolean verifyUpdateQuantity = transactionpage.vefiryUpdateQuantity();
		System.out.println("Update Quantity: " + verifyUpdateQuantity);
		Assert.assertTrue(verifyUpdateQuantity, "User is failed to update the quantity in cart page");
		logger.log(Status.PASS, "Verified that User is able to update the Quantity and the quantity is:"
				+ transactionpage.getQuantity());
		hardWait(8000);
		searchpage.addSubscriptionProduct(data[1]);
		productspage.clickGoToCart();
		hardWait(5000);
		String plan = transactionpage.getPlan();
		Assert.assertTrue(plan != null && plan != "", "User is failed to add the subscription item to the cart");
		System.out.println("Verified that user is able to Add the subscription item to the Cart");
		logger.log(Status.PASS, "Verified that user is able to Add the subscription item to the Cart");
		boolean isSubscriptionPlanModified = transactionpage.vefiryUpdateplan();
		Assert.assertTrue(isSubscriptionPlanModified,
				"User is failed to update the plan in cart page for the subscription product");
		logger.log(Status.PASS, "Verified that user is able to update the subscription plan");
		boolean verifyLogin = transactionpage.loginAndCheckout(data[11], data[12]);
		Assert.assertTrue(verifyLogin, "Seems like user is unable to Login system is not redirecting user to SF Login page");
		String paymentdetails[] = { webprop.getProperty("name"), webprop.getProperty("cardNumber"),
				webprop.getProperty("expiry"), webprop.getProperty("cvvNumber"), data[1] };
		transactionpage.paymentUsingCreditCard(paymentdetails);
		String orderNumber_ConfirmationPage = transactionpage.getPurchaseOrderId();
		Assert.assertTrue(orderNumber_ConfirmationPage.length() > 0, "User is failed to place an Order");
		logger.log(Status.PASS, "Verified that user is Successfully placed an Order and the Order number is: "
				+ orderNumber_ConfirmationPage);

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
		Assert.assertTrue(!str.equalsIgnoreCase(str1), "User is failed update the phone number in personal details");
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
		logger.log(Status.PASS, "Verified that user is able to update the Quantity and Plan for a subscription");
		boolean flag1 = mybreville.verifyUpdateShippingAddr(data[13]);
		Assert.assertTrue(flag1,
				"User is failed to update the shipping address for the subscription product in subscription page");
		logger.log(Status.PASS, "Verified that user is able to update the Subscription Address for a subscription");
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
			logger.log(Status.INFO, "Seems like its taking time to display the order in GUI. Retrying one more time..");
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
}
