package website.tests;

import java.io.File;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import website.apiutility.VerifyEmailAPIs;
import website.base.BaseTest;
import website.generic.ExcelUtility;
import website.generic.GenericUtility;

public class SanityCheck_UAT extends BaseTest {

  private String email;

  @Test(dataProvider = "orderflow")
  public void tc_01_PlaceAndOrder(String data[]) {
    try {

      logger = reporter.createTest("SanityCheck:" + data[1]);
      logger.log(Status.INFO, "TestCase: " + data[0]);
      homepage.selectCountry(data[1]);
      parentWindow = driver.getWindowHandle();
      switchToLatestWindow();
      logger.log(Status.INFO, "Switched to latest window");
      if (homepage.verifyNewsLetterPopUpIsDisplayed()) {
        homepage.closeNewsLetterPopUp();
      }
      if (homepage.verifyEvidonAcceptButton()) {
        homepage.acceptEvidonAcceptButton();
      }
      homepage.clickOnProducts();
      homepage.clickOnKettlesImage();
      String modelnumber = homepage.addKettleToCart();
      Assert.assertTrue(modelnumber != "", "The User failed to add the FG product to the cart. Hence the test case got failed.");
      logger.log(Status.PASS, "It has been validated that the user can add the FG product to the Cart.");
      productspage.clickGoToCart();
      int productCount = transactionpage.getQuantity();
      Assert.assertTrue(productCount > 0, "Failed to display the added products in the cart. hence the test case got failed.");
      logger.log(Status.PASS, "It has been validated that the added products are getting displayed in the cart.");
      boolean verifyUpdateQuantity = transactionpage.vefiryUpdateQuantity();
      Assert.assertTrue(verifyUpdateQuantity, "The user failed to update the quantity of the product in the cart page. Hence the test case failed.");
      logger.log(Status.PASS, "It has been verified that the user is able to update the Quantity in the cart page");
      searchpage.addSubscriptionProduct(data[1]);
      productspage.clickGoToCart();
      Assert.assertTrue(transactionpage.verifyMultipleOptionDisplayedOnCartPage(), "Failed to display login and checkout as guest to the user in the cart page. Hence the test case failed.");
      logger.log(Status.PASS, "It has been validated that the cart page is providing multiple options login & checkout As Guest to the users.");
      String form[] = {
        data[2],
        data[3],
        data[4],
        data[5],
        data[6],
        data[7],
        data[8],
        data[9],
        data[1]
      };
      if ("Registered".equalsIgnoreCase(data[10]) || "NewUser".equalsIgnoreCase(data[10])) {
        boolean isSubscriptionPlanModified = transactionpage.vefiryUpdateplan();
        Assert.assertTrue(isSubscriptionPlanModified, "The user failed to update the plan for the subscription product in the cart page. Hence the test case failed.");
        logger.log(Status.PASS, "It has been verified that the user is able to update the plan for the subscriptionproduct in the cart page");
        Assert.assertFalse(transactionpage.verifyCheckoutAsGuestOptionInCartPage(), "The cart is still showing up checkout as guest option to the user when there is a subscription item. Hence the test case failed.");
        logger.log(Status.PASS, "It has been validated that the cart is not showing up the Checkout As Guest option to the users when there is a subscription product in the Cart.");
        // Get the random Email
        if ("NewUser".equalsIgnoreCase(data[10]) && "ca".equalsIgnoreCase(data[1]) || "NewUser".equalsIgnoreCase(data[10]) && "cafr".equalsIgnoreCase(data[1])) {
          email = GenericUtility.generateEmail() + "@yopmail.com";
          System.out.println("This is for New User Creation CA Region: " + email);
        } else if ("NewUser".equalsIgnoreCase(data[10]) && "us".equalsIgnoreCase(data[1])) {
          email = GenericUtility.generateEmail() + "@yopmail.com";
          System.out.println("This is for New User Creation US Region: " + email);
        } else if ("NewUser".equalsIgnoreCase(data[10]) && "uk".equalsIgnoreCase(data[1])) {
          email = GenericUtility.generateEmail() + "@yopmail.com";
          System.out.println("This is for New User Creation UK Region: " + email);
        } else if ("Registered".equalsIgnoreCase(data[10]) && "ca".equalsIgnoreCase(data[1]) || "Registered".equalsIgnoreCase(data[10]) && "cafr".equalsIgnoreCase(data[1])) {
          email = email_ca;
          System.out.println("This is for CA Registered User: " + email);
        } else if ("Registered".equalsIgnoreCase(data[10]) && "us".equalsIgnoreCase(data[1])) {
          email = email_us;
          System.out.println("This is for US Registered User: " + email);
        } else if ("Registered".equalsIgnoreCase(data[10]) && "uk".equalsIgnoreCase(data[1])) {
          email = email_uk;
          System.out.println("This is for US Registered User: " + email);
        }

        if ("NewUser".equalsIgnoreCase(data[10])) {
          boolean verifyCreateAccount = transactionpage.loginAndCheckout_NewUser(email, data[12]);
          Assert.assertTrue(verifyCreateAccount, "The user is unable to create an account. Hence the test case failed.");
        } else {
          boolean verifyLogin = false;
          if (email != null || email != "" || !email.isEmpty()) verifyLogin = transactionpage.loginAndCheckout(email, data[12]);
          else verifyLogin = transactionpage.loginAndCheckout(data[11], data[12]);
          Assert.assertTrue(verifyLogin, "The user is unable to login. Hence the test case failed.");
        }

      } else {
        transactionpage.clickCheckoutAsGuestButton(data[1]);
        transactionpage.fillTheForm(form);
      }
      String paymentdetails[] = {
        data[3],
        data[4],
        data[5],
        data[6],
        data[7],
        data[8],
        data[9],
        data[1],
        webprop.getProperty("name"),
        webprop.getProperty("cardNumber"),
        webprop.getProperty("expiry"),
        webprop.getProperty("cvvNumber"),
      };
      String paypalDetails[] = {
        data[3],
        data[4],
        data[5],
        data[6],
        data[7],
        data[8],
        data[9],
        data[1],
        webprop.getProperty("paypalemailid").trim(),
        webprop.getProperty("paypalpassword").trim()
      };
      if ("paypal".equalsIgnoreCase(data[14])) transactionpage.paymentUsingPayPal(paypalDetails);
      else transactionpage.paymentUsingCreditCard(paymentdetails);
      String orderNumber_ConfirmationPage = transactionpage.getPurchaseOrderId();
      Assert.assertTrue(orderNumber_ConfirmationPage.length() > 0, "User is failed to place an Order");
      logger.log(Status.PASS, "Verified that user is Successfully placed an Order and the Order number is: " + orderNumber_ConfirmationPage);
      // Set Email Value
      if ("NewUser".equalsIgnoreCase(data[10]) && "ca".equalsIgnoreCase(data[1]) || "NewUser".equalsIgnoreCase(data[10]) && "cafr".equalsIgnoreCase(data[1])) email_ca = email+".full";
      if ("NewUser".equalsIgnoreCase(data[10]) && "us".equalsIgnoreCase(data[1])) email_us = email+".full";
      if ("NewUser".equalsIgnoreCase(data[10]) && "uk".equalsIgnoreCase(data[1])) email_uk = email+".full";

      // Verify Email ID
      String accessTokenUri = webprop.getProperty("accessTokenUri");
      String userIdUri = webprop.getProperty("userIdUri");
      String verifyEmailUri = webprop.getProperty("verifyEmailUri");
      String accessToken = VerifyEmailAPIs.getAccessToken(accessTokenUri);
      logger.log(Status.INFO, "The Access Token: " + accessToken);
      String userID = VerifyEmailAPIs.getUserID(userIdUri, email, accessToken);
      logger.log(Status.INFO, "The User ID: " + userID);
      int statusCode = VerifyEmailAPIs.doVerifyEmail(verifyEmailUri, accessToken, userID);
      logger.log(Status.INFO, "The Status Code: " + statusCode);
      Assert.assertEquals(statusCode, 204, "Failed to verify the Email using API's");
      if (statusCode == 204) {
        if ("Registered".equalsIgnoreCase(data[10]) || "NewUser".equalsIgnoreCase(data[10])) {
          mybreville.clickMyBrevilleMenu();
          mybreville.clickOnBrevilleHubPage();
          boolean verifyLinks_hibpage = mybreville.verifyLinksInHubpage();
          Assert.assertTrue(verifyLinks_hibpage, "All the mandatory page links are not showing up in the hub page. Hence the test case is failed.");
          logger.log(Status.PASS, "It has been verified that all the mandatory page links are showing up in the hub page");
          mybreville.clickOnSubscription_hubpage();
          boolean flag = mybreville.verifyQuantityAndPlanUpdate();
          Assert.assertTrue(flag, "User failed to update the quantity of the subscription. Hence the test case failed.");
          logger.log(Status.PASS, "It has been verified that user is able to update the Quantity of the subscription");
          boolean flag1 = mybreville.verifyUpdateShippingAddr(data[13]);
          Assert.assertTrue(flag1, "User is failed to update the shipping address of the subscription product. Hence test case failed.");
          logger.log(Status.PASS, "It has been Verified that user is able to update the shipping address of the subscription.");
          Assert.assertTrue(mybreville.verifyCancelSubscription(), "User is failed to cancel the subscription. Hence test case failed.");
          logger.log(Status.PASS, "It has been verified that user is able to Cancel the subscription");
          if ("Registered".equalsIgnoreCase(data[10])) {
            mybreville.clickMyBrevilleMenu();
            mybreville.clickPersonalDetails();
            String str = mybreville.getPhoneNumber_personalDetails();
            mybreville.clickEditPersonalDetails();
            mybreville.updatePhoneNumber_PersonalDetails();
            mybreville.ClickSaveOption_PersonalDetails();
            String str1 = mybreville.getPhoneNumber_personalDetails();
            Assert.assertTrue(!str.equalsIgnoreCase(str1), "User is failed update the phone number in personal details");
            logger.log(Status.PASS, "It has been validated that user is able to update the phone number in the personal details, the phone number before and after edit is :" + str + " & " + str1);
          }
          mybreville.clickMyBrevilleMenu();
          mybreville.clickOnBrevilleHubPage();
          mybreville.clickOnOrders_hubpage();
          String ordNumber = mybreville.getFirstOrderNumber_OrdersPage();
          int retrycount = 0;
          while (retrycount < 10) {
            if (ordNumber.trim().equalsIgnoreCase(orderNumber_ConfirmationPage)) {
              break;
            }
            hardWait(10000);
            mybreville.clickMyBrevilleMenu();
            mybreville.clickOnBrevilleHubPage();
            mybreville.clickOnOrders_hubpage();
            ordNumber = mybreville.getFirstOrderNumber_OrdersPage();
            retrycount++;

          }
          logger.log(Status.INFO, "The order number in the confirmation page and the order number showing up in the order page are: " + orderNumber_ConfirmationPage + " & " + ordNumber);
          Assert.assertEquals(orderNumber_ConfirmationPage.trim(), ordNumber.trim(), "The order number placed by the user and the order shown on the order page are not exactly same. Hence the test case failed.");
          logger.log(Status.PASS, "It has been verified that the user created order is shown in Orders page.");
        }
      }

    } catch(Exception e) {
      logger.log(Status.INFO, "Exception: " + e.getMessage());
      System.out.println("Exception is: " + e.getMessage());
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