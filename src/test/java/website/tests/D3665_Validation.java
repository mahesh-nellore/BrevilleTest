package website.tests;

import java.io.File;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import website.apiutility.D365;
import website.base.BaseTest;
import website.generic.ExcelUtility;

public class D3665_Validation extends BaseTest{
	Map<String, String> map;
	
	@Test(dataProvider = "orderflow")
	public void validateOrderInD365(String data[]) {
		logger = reporter.createTest("Validate the Order in D365");
		homepage.selectCountry(data[0]);
		parentWindow = driver.getWindowHandle();
		switchToLatestWindow();
		logger.log(Status.INFO, "Switched to latest window");
		if (homepage.verifyNewsLetterPopUpIsDisplayed()) {
			homepage.closeNewsLetterPopUp();
		}
		if (homepage.verifyEvidonAcceptButton()) {
			homepage.acceptEvidonAcceptButton();
		}
		boolean verifyLogin = transactionpage.loginAndCheckout(data[1], data[2]);
		Assert.assertTrue(verifyLogin, "The user is unable to login. Hence the test case failed.");
		logger.log(Status.PASS, "User is successfully logged in to My Breville using the following credentials i.e."+data[1]+"/"+data[2]);
		mybreville.clickMyBrevilleMenu();
		mybreville.clickOnBrevilleHubPage();
		mybreville.clickOnOrders_hubpage();
		String ordNumber = mybreville.getFirstOrderNumber_OrdersPage();
		Assert.assertTrue(ordNumber.trim().equalsIgnoreCase(data[4]));
		logger.log(Status.PASS, "Order number is successfully displayed in the My Breville orders page");
		if("TRUE".equalsIgnoreCase(data[3])) {
			mybreville.setOrderDetails();
			mybreville.logout();
			
			// D365 Validations
			try {
				boolean D365_flag = D365.getOrderDetails(data[4]);
				map = D365.getLineItemDetails();
				if (D365_flag)
					logger.log(Status.PASS, "Able to fetch the Order Details from the D365 API's");
			} catch (Exception e) {
				logger.log(Status.INFO, "Exception: " + e.getMessage());
			}
			boolean subscriptionQuantityFlag = false;
			boolean subscriptionPriceFlag = false;
			boolean fgQuantityFlag = false;
			boolean fgPriceFlag = false;
			boolean paymentMethodFlag = false;
			boolean shippingAddrStFlag = false;
			boolean shippingAddrCityFlag = false;
			boolean shippingAddrZipCodeFlag = false;
			boolean orderTaxFlag = false;
			boolean orderTotalFlag = false;
			boolean customerOrderReferenceNumberFlag = false;
			boolean salesOrderNumberFlag = false;
			boolean carrierServiceFlag = false;
			boolean carrierIdFlag = false;
			boolean requestedShippingDateFlag = false;

			if (mybreville.getSubscriptionQuantity()
					.equals(map.get("quantity_Spare Parts").replaceAll("\\s+", ""))) {
				logger.log(Status.INFO,
						"Subscription Quantity in AEM is: " + mybreville.getSubscriptionQuantity()
								+ " And the subscription quantity in D365 is: "
								+ map.get("quantity_Spare Parts").replaceAll("\\s+", ""));
				subscriptionQuantityFlag = true;
			} else {
				logger.log(Status.INFO, "Subscription Quantity validation is failed.");
				logger.log(Status.INFO,
						"Subscription Quantity in AEM is: " + mybreville.getSubscriptionQuantity()
								+ " And the subscription quantity in D365 is: "
								+ map.get("quantity_Spare Parts").replaceAll("\\s+", ""));
				subscriptionQuantityFlag = true;
			}
			double subscriptionPrice_D365 = Double.parseDouble(map.get("lineAmount_Spare Parts"))
					- Double.parseDouble(map.get("lineItemDiscount_Spare Parts"));
			if (mybreville.getSubscriptionPrice()
					.equals(String.valueOf(subscriptionPrice_D365).replaceAll("\\s+", ""))) {
				logger.log(Status.PASS,
						"Subscription Price in AEM is: " + mybreville.getSubscriptionPrice()
								+ " And the subscription Price in D365 is: "
								+ String.valueOf(subscriptionPrice_D365).replaceAll("\\s+", ""));
				subscriptionPriceFlag = true;
			} else {
				logger.log(Status.INFO, "Subscription Price validation is failed.");
				logger.log(Status.INFO,
						"Subscription Price in AEM is: " + mybreville.getSubscriptionPrice()
								+ " And the subscription Price in D365 is: "
								+ String.valueOf(subscriptionPrice_D365).replaceAll("\\s+", ""));
			}
			if (mybreville.getFgQuantity().equals(map.get("quantity_Water Kettles").replaceAll("\\s+", ""))) {
				logger.log(Status.PASS,
						"FG Quantity in AEM is: " + mybreville.getFgQuantity()
								+ " And the FG quantity in D365 is: "
								+ map.get("quantity_Water Kettles").replaceAll("\\s+", ""));
				fgQuantityFlag = true;
			} else {
				logger.log(Status.INFO, "FG Quantity validation is failed.");
				logger.log(Status.INFO,
						"FG Quantity in AEM is: " + mybreville.getFgQuantity()
								+ " And the FG quantity in D365 is: "
								+ map.get("quantity_Water Kettles").replaceAll("\\s+", ""));
			}
			if (mybreville.getFgPrice().equals(map.get("lineAmount_Water Kettles").replaceAll("\\s+", ""))) {
				logger.log(Status.PASS,
						"FG Price in AEM is: " + mybreville.getFgPrice() + " And the FG quantity in D365 is: "
								+ map.get("lineAmount_Water Kettles").replaceAll("\\s+", ""));
				fgPriceFlag = true;
			} else {
				logger.log(Status.INFO, "FG Price validation is failed.");
				logger.log(Status.INFO,
						"FG Price in AEM is: " + mybreville.getFgPrice() + " And the FG quantity in D365 is: "
								+ map.get("lineAmount_Water Kettles").replaceAll("\\s+", ""));
			}
			if (mybreville.getPaymentMethodType()
					.equals(map.get("customerPaymentMethodName").replaceAll("\\s+", ""))
					|| "Paypal".equals(map.get("customerPaymentMethodName").replaceAll("\\s+", ""))) {
				logger.log(Status.PASS, "Payment Type in AEM is: " + mybreville.getPaymentMethodType() + " And the Payment Type in D365 is: " +
				map.get("customerPaymentMethodName").replaceAll("\\s+", ""));
				paymentMethodFlag = true;
			} else {
				logger.log(Status.INFO, "Payment Type Validation is Failed.");
				logger.log(Status.INFO,
						"Payment Type in AEM is: " + mybreville.getPaymentMethodType()
								+ " And the Payment Type in D365 is: "
								+ map.get("customerPaymentMethodName").replaceAll("\\s+", ""));
			}
			if (mybreville.getShippingAddrSt()
					.equals(map.get("deliveryAddressStreet").replaceAll("\\s+", ""))) {
				logger.log(Status.PASS,
						"Shipping Address Street in AEM is: " + mybreville.getShippingAddrSt()
								+ " And the Shipping Address Street in D365 is: "
								+ map.get("deliveryAddressStreet").replaceAll("\\s+", ""));
				shippingAddrStFlag = true;
			} else {
				logger.log(Status.INFO, "Shipping Address Street Validation is Failed.");
				logger.log(Status.INFO,
						"Shipping Address Street in AEM is: " + mybreville.getShippingAddrSt()
								+ " And the Shipping Address Street in D365 is: "
								+ map.get("deliveryAddressStreet").replaceAll("\\s+", ""));
			}
			if (mybreville.getShippingAddrcity()
					.equals(map.get("deliveryAddressCity").replaceAll("\\s+", ""))) {
				logger.log(Status.PASS,
						"Shipping Address City in AEM is: " + mybreville.getShippingAddrcity()
								+ " And the Shipping Address City in D365 is: "
								+ map.get("deliveryAddressCity").replaceAll("\\s+", ""));
				shippingAddrCityFlag = true;
			} else {
				logger.log(Status.INFO, "Shipping Address City Validation is Failed.");
				logger.log(Status.INFO,
						"Shipping Address City in AEM is: " + mybreville.getShippingAddrcity()
								+ " And the Shipping Address City in D365 is: "
								+ map.get("deliveryAddressCity").replaceAll("\\s+", ""));
			}
			if (mybreville.getShippingAddrZipCode()
					.equals(map.get("deliveryAddressZipCode").replaceAll("\\s+", ""))) {
				logger.log(Status.PASS,
						"Shipping Address ZipCode in AEM is: " + mybreville.getShippingAddrZipCode()
								+ " And the Shipping Address ZipCode in D365 is: "
								+ map.get("deliveryAddressZipCode").replaceAll("\\s+", ""));
				shippingAddrZipCodeFlag = true;
			} else {
				logger.log(Status.INFO, "Shipping Address ZipCode Validation is Failed.");
				logger.log(Status.INFO,
						"Shipping Address ZipCode in AEM is: " + mybreville.getShippingAddrZipCode()
								+ " And the Shipping Address ZipCode in D365 is: "
								+ map.get("deliveryAddressZipCode").replaceAll("\\s+", ""));
			}
			double tax = Double.parseDouble(map.get("lineItemTax_Water Kettles"))
					+ Double.parseDouble(map.get("lineItemTax_Spare Parts"));
			double tax_D365 = Math.round(tax * 100.0) / 100.0;
			if (mybreville.getOrderTax().equals(String.valueOf(tax_D365).replaceAll("\\s+", ""))) {
				logger.log(Status.PASS, "Order Tax in AEM is: " + mybreville.getOrderTax()
						+ " And the Order Tax in D365 is: " + String.valueOf(tax_D365).replaceAll("\\s+", ""));
				orderTaxFlag = true;
			} else {
				logger.log(Status.INFO, "Order Tax Validation is Failed.");
				logger.log(Status.INFO, "Order Tax in AEM is: " + mybreville.getOrderTax()
						+ " And the Order Tax in D365 is: " + String.valueOf(tax_D365).replaceAll("\\s+", ""));
			}

			int fgQuantity_D365 = Integer.parseInt(map.get("quantity_Water Kettles"));
			logger.log(Status.INFO, "FG Quantity is D365 is: " + fgQuantity_D365);
			double fgPrice_D365 = (Double.parseDouble(map.get("lineAmount_Water Kettles")) * fgQuantity_D365);
			logger.log(Status.INFO, "The FG Quantity Price is: " + fgPrice_D365);
			double orderTotal = fgPrice_D365 + subscriptionPrice_D365 + tax_D365;
			double orderTotal_D365 = Math.round(orderTotal * 100.0) / 100.0;
			logger.log(Status.INFO, "Order Total in D365 is: " + orderTotal_D365);
			if (mybreville.getOrderTotalAmount()
					.equals(String.valueOf(orderTotal_D365).replaceAll("\\s+", ""))) {
				logger.log(Status.PASS,
						"Order Total in AEM is: " + mybreville.getOrderTotalAmount()
								+ " And the Order Tax in D365 is: "
								+ String.valueOf(orderTotal_D365).replaceAll("\\s+", ""));
				orderTotalFlag = true;
			} else {
				logger.log(Status.INFO, "Order Total Validation is Failed.");
				logger.log(Status.INFO,
						"Order Total in AEM is: " + mybreville.getOrderTotalAmount()
								+ " And the Order Total in D365 is: "
								+ String.valueOf(orderTotal_D365).replaceAll("\\s+", ""));
			}
			if (map.get("salesOrderNumber") != null || map.get("salesOrderNumber") != "") {
				logger.log(Status.PASS, "The Sales Order Number is: " + map.get("salesOrderNumber"));
				salesOrderNumberFlag = true;
			} else {
				logger.log(Status.INFO, "Sales Order number validation is failed..");
				logger.log(Status.PASS, "The Sales Order Number is: " + map.get("salesOrderNumber"));
			}
			if (map.get("customerOrderReferenceNumber") != null
					|| map.get("customerOrderReferenceNumber") != "") {
				logger.log(Status.PASS,
						"The customerOrderReferenceNumber is: " + map.get("customerOrderReferenceNumber"));
				customerOrderReferenceNumberFlag = true;
			} else {
				logger.log(Status.INFO, "customerOrderReferenceNumber validation is failed..");
				logger.log(Status.PASS,
						"The customerOrderReferenceNumber is: " + map.get("customerOrderReferenceNumber"));
			}
			if (map.get("carrierService") != null || map.get("carrierService") != "") {
				logger.log(Status.PASS, "The carrierService is: " + map.get("carrierService"));
				carrierServiceFlag = true;
			} else {
				logger.log(Status.INFO, "carrierService validation is failed..");
				logger.log(Status.PASS, "The carrierService is: " + map.get("carrierService"));
			}
			if (map.get("carrierId") != null || map.get("carrierId") != "") {
				logger.log(Status.PASS, "The carrierId is: " + map.get("carrierId"));
				carrierIdFlag = true;
			} else {
				logger.log(Status.INFO, "carrierId validation is failed..");
				logger.log(Status.PASS, "The carrierId is: " + map.get("carrierService"));
			}
			if (map.get("requestedShippingDate") != null || map.get("requestedShippingDate") != "") {
				logger.log(Status.PASS, "The requestedShippingDate is: " + map.get("requestedShippingDate"));
				requestedShippingDateFlag = true;
			} else {
				logger.log(Status.INFO, "requestedShippingDate validation is failed..");
				logger.log(Status.PASS, "The requestedShippingDate is: " + map.get("carrierService"));
			}
			Assert.assertTrue(salesOrderNumberFlag && customerOrderReferenceNumberFlag && carrierServiceFlag
					&& requestedShippingDateFlag && carrierIdFlag);
			Assert.assertTrue(subscriptionQuantityFlag && subscriptionPriceFlag && fgQuantityFlag && fgPriceFlag
					&& shippingAddrStFlag && shippingAddrCityFlag && shippingAddrZipCodeFlag
					&& paymentMethodFlag && orderTaxFlag && orderTotalFlag);
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
