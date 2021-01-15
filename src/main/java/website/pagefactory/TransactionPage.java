package website.pagefactory;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.aventstack.extentreports.Status;
import website.base.BaseTest;

public class TransactionPage extends BaseTest {

  //private static TransactionPage transactionpage = null;

  @FindBy(id = "onetimecheckoutasguest")
  private WebElement checkoutAsGuest_Registered;

  @FindBy(id = "onetimeloggedinSignup")
  private WebElement oneTimeLoggedInSignup;

  @FindBy(id = "loggedinSignup")
  private WebElement createAccountButton_Cartpage;

  @FindBy(id = "js-sign-up-label")
  private WebElement createAccountLoginButton;

  @FindBy(xpath = "(//div[contains(@class,'c-login-btn')])[1]")
  private WebElement loginButton;

  @FindBy(id = "checkoutasguest")
  private WebElement checkoutAsGuest;

  @FindBy(css = "td[class='totals__price js-total-price']")
  private WebElement totalPrice;

  @FindBy(css = "td[class='product__description']>span[class$='name']")
  private WebElement productName;

  @FindBy(css = "button[class$='remove']")
  private WebElement productRemove;

  @FindBy(xpath = "//a[contains(text(),'Remove this Item')]")
  private WebElement removeItemInDialogBox;

  @FindBy(css = "div[class='c_empty__product']>p")
  private WebElement cartEmptyMsg;

  // Checkout As Guest
  @FindBy(css = "input#email-address")
  private WebElement emailAddress;

  @FindBy(css = "button[class$='shipping-btn']")
  private WebElement continueToShippingButton;

  @FindBy(css = "input#firstName_shipping")
  private WebElement firstname;

  @FindBy(css = "input#lastName_shipping")
  private WebElement lastname;

  @FindBy(css = "input#streetAddress1_shipping")
  private WebElement address1;

  @FindBy(css = "input#aptSuiteLabel_shipping")
  private WebElement address2;

  @FindBy(css = "input#city_shipping")
  private WebElement city;

  @FindBy(css = "input#zipCode_shipping")
  private WebElement zipcode;

  @FindBy(css = "select#state_shipping")
  private WebElement state;

  @FindBy(id = "region_shipping")
  private WebElement region;

  @FindBy(css = "button[class$='submit-btn']")
  private WebElement continueToPayment;

  @FindBy(css = "input#phoneNumber_shipping")
  private WebElement phoneNumber;

  @FindBy(id = "item-quantity-0")
  private WebElement quantityDropDown;

  @FindBy(id = "cardholder-name")
  private WebElement cardHolderName;

  @FindBy(id = "credit-card-number")
  private WebElement creditCardNumber;

  @FindBy(id = "expiration")
  private WebElement expiryDate;

  @FindBy(id = "cvv")
  private WebElement cvv;

  @FindBy(xpath = "//input[contains(@id,'tncCheckboxCreditCart')]/../label")
  private WebElement termsAndConditionsCheckbox;

  @FindBy(id = "ccPayment")
  private WebElement submitOrderButton;

  @FindBy(css = "span[class='js-purchage-id']")
  private WebElement purchaseOrderId;

  @FindBy(id = "braintree-hosted-field-number")
  private WebElement iframeBrainTree;

  @FindBy(id = "braintree-hosted-field-expirationDate")
  private WebElement iframeExpiryDate;

  @FindBy(id = "braintree-hosted-field-cvv")
  private WebElement iframeCvv;

  @FindBy(name = "challengeDataEntry")
  private WebElement authPassword;

  @FindBy(id = "authWindow")
  private WebElement authIframe;

  @FindBy(id = "Cardinal-CCA-IFrame")
  private WebElement cardinalIframe;

  @FindBy(css = "input[value*='SUBMIT']")
  private WebElement submitButtonAuthPage;

  @FindBy(xpath = "//input[contains(@name,'Username')]")
  private WebElement username;

  @FindBy(xpath = "//input[contains(@name,'password')]")
  private WebElement password;

  @FindBy(css = "button[class*='sfdc_button_breville']")
  private WebElement loginButton_SF;

  @FindBy(id = "item-subscription-1")
  private WebElement subscriptionPlanDropdown;

  @FindBy(id = "loggedinCheckout")
  private WebElement checkoutOptForLoggedInUsr;

  @FindBy(xpath = "//div[contains(@id,'spinner__dialog')and(contains(@style,'none'))]")
  private WebElement loaderImage;

  @FindBy(xpath = "//input[contains(@id,'payPaypal')]/..//label")
  private WebElement paypalRadioButton;

  @FindBy(id = "payPaypal")
  private WebElement paypalDiv;

  @FindBy(css = "div[aria-label*='PayPal']")
  private WebElement payWithpaypalButton;
  
 /* @FindBy(id = "paypalBtn")
  private WebElement payWithpaypalButton;*/

  @FindBy(id = "email")
  private WebElement paypalEmailID;

  @FindBy(id = "btnNext")
  private WebElement paypalNextButton;

  @FindBy(id = "password")
  private WebElement paypalPassword;

  @FindBy(id = "btnLogin")
  private WebElement paypalLoginButton;

  @FindBy(id = "fiSubmitButton")
  private WebElement paypalContinueButton;

  @FindBy(id = "consentButton")
  private WebElement paypalAgreeAndPayButton;

  @FindBy(xpath = "//input[contains(@id,'tncCheckboxPayPal')]/..//label")
  private WebElement paypalTermsAndConditionsCheckBox;

  @FindBy(id = "paypalBtn")
  private WebElement paypalSubmitOrder;

  @FindBy(xpath = "//div[contains(@id,'preloaderSpinner')and contains(@style,'display: none;')]")
  private WebElement displayLoaderOnPayPalWindow;

  @FindBy(xpath = "iframe[class='xcomponent-component-frame xcomponent-visible']")
  private WebElement payWithPayPalButtonIframe;

  @FindBy(xpath = "//input[contains(@id,'payKlarna')]/..//label")
  private WebElement payKlarnaRadioButton;

  @FindBy(xpath = "(//input[contains(@id,'payCredit')]/..//label)[2]")
  private WebElement payCreditRadioButton;

  @FindBy(css = "button[class*='submit-btn']")
  private WebElement continuePaymentButton;

  @FindBy(css = "a[class*='goToCartMulberrySC']")
  private WebElement alertModalOkButton;

  @FindBy(css = "div[aria-labelledby*='errorPopUp']")
  private WebElement errorPopUpDialogBox;

  @FindBy(id = "errorPopUp__title")
  private WebElement errorPopUpTitle;

  // Create Account Page

  @FindBy(xpath = "//input[@name='email']")
  private WebElement emailTextBox_CreateAccount;

  @FindBy(xpath = "//input[@name='password']")
  private WebElement passwordTextBox_CreateAccount;

  @FindBy(css = "button[class*='button_breville']")
  private WebElement continueButton_CreateAccount;
  
  @FindAll(@FindBy(tagName = "iframe"))
  private List<WebElement> allFrames;
  
  // Save Shipping Address popup
  @FindBy(css = "button[class='saveBtn']")
  private WebElement saveShippingAddrButton;

  public TransactionPage() {

    PageFactory.initElements(driver, this);
  }

 /* public static TransactionPage getTransactionPage() {
    if (transactionpage == null) transactionpage = new TransactionPage();
    return transactionpage;
  }*/

  public boolean verifyProductOutOfStockAlert() {
    while (transactionpage.verifyLoaderImage()) {
      hardWait(5000);
      System.out.println("Waiting for the page to be loaded");
    }
    boolean flag = verifyElementIsDisplayed(errorPopUpDialogBox);
    System.out.println("Is Alert Displayed on the page: " + flag);
    return flag;

  }

  public void handleProductOutOfStockAlert() {
    try {
      String text = errorPopUpTitle.getText();
      System.out.println("Error Text is: " + text);
      alertModalOkButton.click();
      System.out.println("Clicking on alert pop up Ok Button");
    } catch(Exception e) {
      // TODO: handle exception
    }
  }

  public int getQuantity() {
    waitForElementToBeClickable(quantityDropDown);
    Select select = new Select(quantityDropDown);
    int qty = Integer.parseInt(select.getFirstSelectedOption().getText());
    return qty;
  }

  public String getPlan() {
    waitForElementToBeClickable(subscriptionPlanDropdown);
    Select select = new Select(subscriptionPlanDropdown);
    return select.getFirstSelectedOption().getText();
  }

  public void updateQuantity() {
    String value = Integer.toString(getQuantity() + 1);
    Select select = new Select(quantityDropDown);
    select.selectByVisibleText(value);
  }

  public void updatePlan() {
    String value = getPlan();
    Select select = new Select(subscriptionPlanDropdown);
    List < WebElement > options = select.getOptions();
    for (WebElement webElement: options) {
      String option = webElement.getText();
      if (!value.equalsIgnoreCase(option)) {
        select.selectByVisibleText(option);
        break;
      }
    }
  }

  public boolean vefiryUpdateQuantity() {
    int initialValue = getQuantity();
    updateQuantity();
    hardWait(2000);
    while (verifyLoaderImage()) {
      hardWait(2000);
    }
    if (verifyProductOutOfStockAlert()) handleProductOutOfStockAlert();
    while (verifyLoaderImage()) {
      hardWait(2000);
    }
    int editedValue = getQuantity();
    logger.log(Status.INFO, "The quantity of the product before edit is and the quantity after update is: " + initialValue + " & " + editedValue);
    if (initialValue < editedValue) return true;
    else return false;
  }

  public boolean vefiryUpdateplan() {
    String initialValue = getPlan();
    updatePlan();
    while (verifyLoaderImage()) {
      hardWait(5000);
    }
    hardWait(2000);
    String editedValue = getPlan();
    logger.log(Status.INFO, "The plan for the product before edit is and the plan after update is: " + initialValue + " & " + editedValue);
    if (!initialValue.equalsIgnoreCase(editedValue)) return true;
    else return false;
  }

  public String getSubTotal() {
    waitForElementToBeVisible(totalPrice);
    return totalPrice.getText();

  }

  public void removeProduct() {
    waitForElementToBeVisible(productRemove);
    productRemove.click();
    waitForElementToBeVisible(removeItemInDialogBox);
    removeItemInDialogBox.click();
  }

  public boolean verifyIsCartEmpty() {
    hardWait(2000);
    return verifyElementIsDisplayed(cartEmptyMsg);
  }

  public String getCartEmptyMsg() {
    int count = 0;
    boolean result = false;
    while (count < 2) {
      try {
        hardWait(5000);
        result = cartEmptyMsg.isDisplayed();
      } catch(Exception e) {}
      count++;
      if (result) break;
    }
    return cartEmptyMsg.getText();
  }

  public void clickCheckoutAsGuestButton(String str) {
    hardWait(8000);
    if ("ca".equalsIgnoreCase(str)) {
      waitForElementToBeClickable(checkoutAsGuest_Registered);
      checkoutAsGuest_Registered.click();
    } else {
      waitForElementToBeClickable(checkoutAsGuest);
      checkoutAsGuest.click();
    }

  }

  public boolean loginAndCheckout(String uname, String pwd) {
    boolean flag = false;
    hardWait(8000);
    waitForElementToBeClickable(createAccountLoginButton);
    clickElementUsingJavaScriptExecutor(createAccountLoginButton);
    waitForElementToBeClickable(loginButton);
    loginButton.click();
    logger.log(Status.INFO, "Clicking on the Login option from My Breville menu.");
    hardWait(5000);
    if (verifyusername()) {
      flag = true;
      waitForElementToBeClickable(username);
      username.sendKeys(uname);
      logger.log(Status.INFO, "Entered username: " + uname);
      password.sendKeys(pwd);
      logger.log(Status.INFO, "Entered password: " + pwd);
      loginButton_SF.click();
      logger.log(Status.INFO, "Clicked on Login Button");
    } else {
      logger.log(Status.INFO, "Seems like system is not redirecting to SF login page when user clicks on Login.");
    }
    return flag;
  }

  public boolean loginAndCheckout_NewUser(String uname, String pwd) {
    boolean flag = false;
    hardWait(2000);
    clickOnCreateAccountButton_CartPage();
    hardWait(10000);
    waitForElementToBeVisible(emailTextBox_CreateAccount, 60);
    if (verifyEmailTextBox_CreateAccount()) {
      waitForElementToBeClickable(emailTextBox_CreateAccount);
      emailTextBox_CreateAccount.sendKeys(uname);
      logger.log(Status.INFO, "Entered Email Id:" + uname);
      hardWait(2000);
      waitForElementToBeClickable(passwordTextBox_CreateAccount);
      passwordTextBox_CreateAccount.sendKeys(pwd);
      logger.log(Status.INFO, "Entered password: " + pwd);
      hardWait(2000);
      waitForElementToBeClickable(continueButton_CreateAccount);
      continueButton_CreateAccount.click();
      logger.log(Status.INFO, "Clicking on continue button");
      flag = true;
    }
    return flag;
  }

  public void fillTheForm(String form[]) {
    waitForTheElementToVisible(emailAddress);
    hardWait(2000);
    emailAddress.sendKeys(form[0]);
    hardWait(1000);
    waitForElementToBeClickable(continueToShippingButton);
    clickElementUsingJavaScriptExecutor(continueToShippingButton);
    waitForElementToBeVisible(firstname);
    firstname.sendKeys(form[1]);
    lastname.sendKeys(form[2]);
    address1.sendKeys(form[3]);
    city.sendKeys(form[4]);
    zipcode.sendKeys(form[5]);
    if ("ca".equalsIgnoreCase(form[8]) || "us".equalsIgnoreCase(form[8])) {
      Select select = new Select(state);
      select.selectByVisibleText(form[6]);
    } else if ("eu".equalsIgnoreCase(form[8])) {
      region.sendKeys(form[6]);
    }
    phoneNumber.clear();
    phoneNumber.sendKeys(form[7]);
    continueToPayment.click();

  }

  public void paymentUsingPayPal(String str[]) {
    if (verifyCardHolderName()) logger.log(Status.INFO, "After merge cart user is navigated to Checkout page.");
    else if (verifyFirstName_Checkoutpage()) {
      waitForElementToBeVisible(firstname);
      firstname.sendKeys(str[0]);
      lastname.sendKeys(str[1]);
      address1.sendKeys(str[2]);
      city.sendKeys(str[3]);
      zipcode.sendKeys(str[4]);
      if ("ca".equalsIgnoreCase(str[7]) || "cafr".equalsIgnoreCase(str[7]) || "us".equalsIgnoreCase(str[7])) {
        Select select = new Select(state);
        select.selectByVisibleText(str[5]);
      } else if ("eu".contains(str[7])) {
        region.sendKeys(str[5]);
      }
      phoneNumber.clear();
      phoneNumber.sendKeys(str[6]);
      continueToPayment.click();
      hardWait(8000);
      System.out.println(driver.getWindowHandles().size());
      if(verifyElementIsDisplayed(saveShippingAddrButton)) {
    	  System.out.println("----------------------------------------");
    	  System.out.println("Save Shipping Address pop up is displayed");
    	  System.out.println("----------------------------------------");
    	  waitForElementToBeClickable(saveShippingAddrButton);
    	  saveShippingAddrButton.click();
      }else {
    	  System.out.println("Save Shipping address pop up is not displayed");
      }
    	 
      

    } else if (verifyContinuePaymentButton()) continuePaymentButton.click();
    else {
      logger.log(Status.INFO, "Seems like Cart has orders in it, hence redirected to Cart page instead of checkout page..");
      checkoutOptForLoggedInUsr.click();
      logger.log(Status.INFO, "Clicked On Chekout Button..");
    }

    String parentWindow = driver.getWindowHandle();
    waitForElementToBeClickable(paypalRadioButton);
    paypalRadioButton.click();
    logger.log(Status.INFO, "Clicking on paypal radio button");
    hardWait(10000);
    int count = 1;
    for (WebElement element : allFrames) {
		String value = element.getAttribute("name");
		System.out.println("The value is: "+value);
		if(value.contains("xcomponent__ppbutton__4_0_165")) {
			System.out.println("The paypal iframe is Available");
		}
	}
    System.out.println("---------------------------------------------------------");
    for (WebElement element : allFrames) {
		System.out.println(element.getAttribute("id"));
	}
    System.out.println("---------------------------------------------------------");
    for (WebElement element : allFrames) {
		System.out.println(element.getAttribute("src"));
	}
    System.out.println("---------------------------------------------------------");
    driver.switchTo().frame(3);
    logger.log(Status.INFO, "Switched to the frame");
    System.out.println("---Switched to the Frame----");
    hardWait(10000);
    System.out.println("---------------------------------");
    System.out.println(verifyElementIsDisplayed(payWithpaypalButton));
    System.out.println("---------------------------------");
    /*waitForElementToBeVisible(payWithpaypalButton, 90);
    waitForElementToBeClickable(payWithpaypalButton);*/
    payWithpaypalButton.click();
    logger.log(Status.INFO, "Clicking on pay with paypal button");
    hardWait(8000);
    switchToLatestWindow();
    logger.log(Status.INFO, "switched to the latest window");
    while (verifyLoaderImageOnPaypalWindow()) {
      hardWait(2000);
    }
    waitForElementToBeVisible(paypalEmailID, 60);
    waitForElementToBeClickable(paypalEmailID);
    if(verifyPaypalEmail()) {
    	paypalEmailID.sendKeys(str[8]);
        logger.log(Status.INFO, "Entered username: " + str[8]);
        waitForElementToBeClickable(paypalNextButton);
        paypalNextButton.click();
        logger.log(Status.INFO, "Clicking on Next button");
        while (verifyLoaderImageOnPaypalWindow()) {
            hardWait(2000);
          }
        hardWait(5000);
    }
    waitForElementToBeVisible(paypalPassword, 60);
    waitForElementToBeClickable(paypalPassword);
    if(verifyPaypalPassword()) {
    	waitForElementToBeClickable(paypalPassword);
        paypalPassword.sendKeys(str[9]);
        logger.log(Status.INFO, "Entered password: " + str[9]);
        waitForElementToBeClickable(paypalLoginButton);
        paypalLoginButton.click();
        logger.log(Status.INFO, "Clicking on Login button");
        while (verifyLoaderImageOnPaypalWindow()) {
          hardWait(2000);
        }
        hardWait(5000);
    }
    if(verifyPaypalContinueButton()) {
    	waitForElementToBeClickable(paypalContinueButton);
        paypalContinueButton.click();
        logger.log(Status.INFO, "Clicking on paypal continue button");
        while (verifyLoaderImageOnPaypalWindow()) {
          hardWait(2000);
        }
        hardWait(5000);
    }
    
    waitForElementToBeClickable(paypalAgreeAndPayButton);
    hardWait(5000);
    clickElementUsingJavaScriptExecutor(paypalAgreeAndPayButton);
    logger.log(Status.INFO, "Clicking on Agree button");
    while (verifyLoaderImageOnPaypalWindow()) {
      hardWait(2000);
    }
    hardWait(5000);
    driver.switchTo().window(parentWindow);
    logger.log(Status.INFO, "Switched back to parent window");
    waitForElementToBeClickable(paypalTermsAndConditionsCheckBox);
    hardWait(2000);
    clickElementUsingJavaScriptExecutor(paypalTermsAndConditionsCheckBox);
    logger.log(Status.INFO, "Selected terms and conditions checkbox");
    waitForElementToBeClickable(paypalSubmitOrder);
    paypalSubmitOrder.click();
    logger.log(Status.INFO, "Clicking on the submit button");

  }

  public void paymentUsingCreditCard(String str[]) {
    if (verifyCardHolderName()) logger.log(Status.INFO, "After merge cart user is navigated to Checkout page.");
    else if (verifyFirstName_Checkoutpage()) {
      waitForElementToBeVisible(firstname);
      firstname.sendKeys(str[0]);
      lastname.sendKeys(str[1]);
      address1.sendKeys(str[2]);
      city.sendKeys(str[3]);
      zipcode.sendKeys(str[4]);
      if ("ca".equalsIgnoreCase(str[7]) || "cafr".equalsIgnoreCase(str[7]) || "us".equalsIgnoreCase(str[7])) {
        Select select = new Select(state);
        select.selectByVisibleText(str[5]);
      } else if ("eu".equalsIgnoreCase(str[7])) {
        region.sendKeys(str[5]);
      }
      phoneNumber.clear();
      phoneNumber.sendKeys(str[6]);
      continueToPayment.click();
      hardWait(8000);
      System.out.println(driver.getWindowHandles().size());
      if(verifyElementIsDisplayed(saveShippingAddrButton)) {
    	  System.out.println("----------------------------------------");
    	  System.out.println("Save Shipping Address pop up is displayed");
    	  System.out.println("----------------------------------------");
    	  waitForElementToBeClickable(saveShippingAddrButton);
    	  saveShippingAddrButton.click();
      }else {
    	  System.out.println("Save Shipping address pop up is not displayed");
      }

    } else if (verifyContinuePaymentButton()) continuePaymentButton.click();
    else {
      logger.log(Status.INFO, "Seems like Cart has orders in it, hence redirected to Cart page instead of checkout page..");
      checkoutOptForLoggedInUsr.click();
      logger.log(Status.INFO, "Clicked On Chekout Button..");
    }
    waitForElementToBeClickable(cardHolderName);
    cardHolderName.sendKeys(str[8]);
    switchToFrame(iframeBrainTree);
    creditCardNumber.sendKeys(str[9]);
    switchToParentFrame();
    switchToFrame(iframeExpiryDate);
    expiryDate.sendKeys(str[10]);
    switchToParentFrame();
    switchToFrame(iframeCvv);
    cvv.sendKeys(str[11]);
    switchToParentFrame();
    hardWait(2000);
    clickElementUsingJavaScriptExecutor(termsAndConditionsCheckbox);
    waitForElementToBeVisible(submitOrderButton);
    submitOrderButton.click();
    hardWait(5000);
    if ("eu".equalsIgnoreCase(str[7]) || "uk".equalsIgnoreCase(str[7])) {
      logger.log(Status.INFO, " The Order belongs to EU region");
      System.out.println("The Order belongs to EU Region");
      hardWait(8000);
      System.out.println("-------------------------------------");
      System.out.println(verifyElementIsDisplayed(cardinalIframe));
      switchToFrame(cardinalIframe);
      logger.log(Status.INFO, "Switched to the CC iframe");
      authPassword.sendKeys("1234");
      submitButtonAuthPage.click();
      hardWait(2000);
      switchToParentFrame();
    }

  }

  public String getPurchaseOrderId() {
	waitForElementToBeVisible(purchaseOrderId, 90);
    waitForElementToBeClickable(purchaseOrderId);
    while (verifyLoaderImage()) {
      hardWait(5000);
    }
    hardWait(8000);
    return purchaseOrderId.getText();
  }

  public boolean verifyfirstname() {
    waitForElementToBeVisible(firstname, 120);
    return isElementPresent(firstname);
  }

  public boolean verifyCheckoutOptForLoggedInUsr() {
    hardWait(8000);
    return verifyElementIsDisplayed(checkoutOptForLoggedInUsr);
    // isElementPresent(checkoutOptForLoggedInUsr);
  }

  public boolean verifyCardHolderName() {
    waitForElementToBeClickable(cardHolderName);
    try {
      return cardHolderName.isDisplayed();
    } catch(Exception e) {
      logger.log(Status.INFO, "Exception Occured while waiting for the cardHolderName and the exception is:" + e.getMessage());
    }
    return false;
  }

  public boolean verifyContinuePaymentButton() {
    return verifyElementIsDisplayed(continuePaymentButton);
  }

  public boolean verifyStateDropdown() {
    return verifyElementIsDisplayed(state);
  }

  public boolean verifyRegionInputTextbox() {
    return verifyElementIsDisplayed(region);
  }

  public boolean verifyusername() {
    waitForElementToBeClickable(username);
    return verifyElementIsDisplayed(username);
  }

  public boolean verifySignup() {
    return verifyElementIsDisplayed(createAccountLoginButton);
  }

  public boolean verifyMultipleOptionDisplayedOnCartPage() {
    while (verifyLoaderImage()) {
      hardWait(5000);
    }
    boolean flag = false;
    waitForElementToBeClickable(checkoutAsGuest_Registered);
    waitForElementToBeClickable(oneTimeLoggedInSignup);
    hardWait(5000);
    if (verifyElementIsDisplayed(checkoutAsGuest_Registered) && verifyElementIsDisplayed(oneTimeLoggedInSignup)) flag = true;
    return flag;

  }

  public boolean verifyCheckoutAsGuestOptionInCartPage() {
	  hardWait(8000);
	  while (verifyLoaderImage()) {
      hardWait(5000);
    }
    return verifyElementIsDisplayed(checkoutAsGuest_Registered);
  }

  public boolean verifyLoaderImage() {
    boolean flag = false;
    try {
      if (loaderImage.isDisplayed()) flag = true;
    } catch(Exception e) {
      logger.log(Status.INFO, "Exception occured while verifying the loader image");
    }
    return flag;
  }

  public boolean verifyLoaderImageOnPaypalWindow() {
    boolean flag = false;
    try {
      if (displayLoaderOnPayPalWindow.isDisplayed()) flag = true;
    } catch(Exception e) {
      logger.log(Status.INFO, "Exception occured while verifying the loader image");
    }
    return flag;
  }

  public boolean verifyPayKlarnaRadioButton() {
    return verifyElementIsDisplayed(payKlarnaRadioButton);
  }

  public boolean verifyPaypalIframe() {
    return verifyElementIsDisplayed(payWithPayPalButtonIframe);
  }

  public void clickOnCreateAccountButton_CartPage() {
    waitForElementToBeClickable(createAccountButton_Cartpage);
    createAccountButton_Cartpage.click();
    logger.log(Status.INFO, "Clicked on Create account/Login button in Cart page");
  }

  public boolean verifyEmailTextBox_CreateAccount() {
    return verifyElementIsDisplayed(emailTextBox_CreateAccount);
  }

  public boolean verifyFirstName_Checkoutpage() {
    return verifyElementIsDisplayed(firstname);
  }
  
  public boolean verifyPaypalEmail() {
	  return verifyElementIsDisplayed(paypalEmailID);
  }
  
  public boolean verifyPaypalPassword() {
	  return verifyElementIsDisplayed(paypalPassword);
  }

  public boolean verifyPaypalContinueButton() {
	  return verifyElementIsDisplayed(paypalContinueButton);
  }
}