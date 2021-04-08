package website.pagefactory;

import java.util.List;

import javax.lang.model.element.QualifiedNameable;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

import website.base.BaseTest;

public class BeanzTransactionPage extends BaseTest {
	
	@FindBy(xpath = "//a[contains(text(),'Or continue to Check as Guest')]")
	private WebElement checkoutAsGuest_Registered;
	
	@FindBy(xpath = "//a[@class='btn btn-primary-outline fluid js-checkout-btn']")
	private WebElement oneTimeLoggedIn;
	
	@FindBy(xpath = "//a[@class='btn btn-primary fluid js-checkout-btn']")
	private WebElement oneTimeCreateAccount;
	
	@FindBy(xpath = "(//button[contains(@id , 'dropdownMenuButton')])[1]")
	private WebElement subscriptionPlanDropdown;
	
	@FindBy(xpath = "//button[@id='cartDropdownMenuButton']") //Send Me Dropdown
	private WebElement quantityDropDown;
	
	@FindBy(xpath = "//li[contains(text(),'Cart Total')]")
	private WebElement cartTotal;
	
	@FindBy(xpath = "//a[(@class = 'js-product-remove')]")
	private WebElement remove;
	
	@FindBy(xpath = "//p[contains(text(),'Your Cart is Empty.')]")
	private WebElement cartEmptyMsg;
	
	// Checkout As Guest
	@FindBy(css = "#email-address")
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

	@FindBy(css = "button[class$='submit-btn']")
	private WebElement continueToPayment;

	@FindBy(css = "input#phoneNumber_shipping")
	private WebElement phoneNumber;
	
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

	@FindBy(id = "loggedinCheckout")
	private WebElement checkoutOptForLoggedInUsr;

	@FindBy(xpath = "//div[contains(@id,'spinner__dialog')and(contains(@style,'none'))]")
	private WebElement loaderImage;

	@FindBy(xpath = "//input[contains(@id,'payPaypal')]/..//label")
	private WebElement paypalRadioButton;

	@FindBy(id = "payPaypal")
	private WebElement paypalDiv;

	/*
	 * @FindBy(css = "div[aria-label*='PayPal']") private WebElement
	 * payWithpaypalButton;
	 */

	@FindBy(xpath = "//div[contains(@aria-label,'PayPal')]/div//span")
	private WebElement payWithpaypalButton;

	/*
	 * @FindBy(id = "paypalBtn") private WebElement payWithpaypalButton;
	 */

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

	@FindBy(css = "p[class='loader']")
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
	
	@FindBy(id = "checkoutasguest")
	private WebElement checkoutAsGuest;
	
	@FindBy(id = "loggedinSignup")
	private WebElement createAccountButton_Cartpage;
	
	@FindBy(id = "js-sign-up-label")
	private WebElement createAccountLoginButton;
	
	@FindBy(xpath = "(//div[contains(@class,'c-login-btn')])[1]")
	private WebElement loginButton;

	public BeanzTransactionPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean verifyProductOutOfStockAlert() {
		while (transactionpage.verifyLoaderImage()) {
			hardWait(5000);
		}
		boolean flag = verifyElementIsDisplayed(errorPopUpDialogBox);
		return flag;

	}

	public void handleProductOutOfStockAlert() {
		try {
			String text = errorPopUpTitle.getText();
			//logger.log(Status.INFO, "Error Text is: "+text);
			alertModalOkButton.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public boolean vefiryUpdateplan() {
		String initialValue = getPlan();
		updatePlan();
		while (verifyLoaderImage()) {
			hardWait(5000);
		}
		hardWait(2000);
		String editedValue = getPlan();
		//logger.log(Status.INFO, "The plan for the product before edit is and the plan after update is: " + initialValue+ " & " + editedValue);
		if (!initialValue.equalsIgnoreCase(editedValue))
			return true;
		else
			return false;
	}
	
	public String getPlan() {
		waitForElementToBeClickable(subscriptionPlanDropdown);
	//	subscriptionPlanDropdown.click();
		hardWait(3000);
		Select select = new Select(subscriptionPlanDropdown);
		return select.getFirstSelectedOption().getText();
	}
	
	public void updatePlan() {
		String value = getPlan();
		hardWait(3000);
		Select select = new Select(subscriptionPlanDropdown);
		List<WebElement> options = select.getOptions();
		for (WebElement webElement : options) {
			String option = webElement.getText();
			if (!value.equalsIgnoreCase(option)) {
				select.selectByVisibleText(option);
				break;
			}
		}
	}
	
	public boolean verifyLoaderImage() {
		boolean flag = false;
		try {
			if (loaderImage.isDisplayed())
				flag = true;
		} catch (Exception e) {
			logger.log(Status.INFO, "Exception occured while verifying the loader image");
		}
		return flag;
	}
	
	public boolean verifyCheckoutAsGuestOptionInCartPage() {
		boolean flag = false;
		try {
			hardWait(1000);
			while (verifyLoaderImage()) {
				hardWait(5000);
			}
			if(checkoutAsGuest_Registered.isDisplayed())
				flag = true;
		}catch (Exception e) {
			logger.log(Status.INFO,e.getMessage());
		}return flag;
	}
	
	public void selectEveryDropdown()
	{
		waitForElementToBeClickable(subscriptionPlanDropdown);
		Select select = new Select(subscriptionPlanDropdown);
		select.selectByIndex(1);
	}
	
	public void selectSendMeDropdown()
	{
		waitForElementToBeClickable(quantityDropDown);
		Select select = new Select(quantityDropDown);
		select.selectByIndex(1);
	}
	
	public int getQuantity() {
		waitForElementToBeClickable(quantityDropDown);
		Select select = new Select(quantityDropDown);
		int qty = Integer.parseInt(select.getFirstSelectedOption().getText());
		return qty;
	}

	
	public boolean vefiryUpdateQuantity() {
		int initialValue = getQuantity();
		updateQuantity();
		hardWait(2000);
		while (verifyLoaderImage()) {
			hardWait(2000);
		}
		/*
		 * if (verifyProductOutOfStockAlert()) handleProductOutOfStockAlert(); while
		 * (verifyLoaderImage()) { hardWait(2000); }
		 */
		int editedValue = getQuantity();
		//logger.log(Status.INFO, "The quantity of the product before edit is and the quantity after update is: "+ initialValue + " & " + editedValue);
		if (initialValue < editedValue)
			return true;
		else
			return false;
	}
	
	public void updateQuantity() {
		String value = Integer.toString(getQuantity() + 1);
		Select select = new Select(quantityDropDown);
		select.selectByVisibleText(value);
	}
	
	public boolean verifyMultipleOptionDisplayedOnCartPage() {
		while (verifyLoaderImage()) {
			hardWait(5000);
		}
		boolean flag = false;
		waitForElementToBeClickable(checkoutAsGuest_Registered);
		waitForElementToBeClickable(oneTimeLoggedIn);
		waitForElementToBeClickable(oneTimeCreateAccount);
		
		// hardWait(5000);
		if (verifyElementIsDisplayed(oneTimeCreateAccount) && verifyElementIsDisplayed(oneTimeLoggedIn) && verifyElementIsDisplayed(checkoutAsGuest_Registered))
			flag = true;
		return flag;

	}
	
	public void clickOnCreateAccountButton_CartPage() {
		waitForElementToBeClickable(createAccountButton_Cartpage);
		createAccountButton_Cartpage.click();
		logger.log(Status.INFO, "Clicked on Create account/Login button in Cart page");
	}
	
	public boolean verifyEmailTextBox_CreateAccount() {
		return verifyElementIsDisplayed(emailTextBox_CreateAccount);
	}
	
	public boolean verifyusername() {
		waitForElementToBeClickable(username);
		return verifyElementIsDisplayed(username);
	}
	
	public boolean loginAndCheckout_NewUser(String uname, String pwd) {
		boolean flag = false;
		hardWait(2000);
		clickOnCreateAccountButton_CartPage();
		// hardWait(10000);
		waitForElementToBeVisible(emailTextBox_CreateAccount, 90);
		if (verifyEmailTextBox_CreateAccount()) {
			waitForElementToBeClickable(emailTextBox_CreateAccount);
			emailTextBox_CreateAccount.sendKeys(uname);
			//logger.log(Status.INFO, "Entered Email Id:" + uname);
			hardWait(2000);
			waitForElementToBeClickable(passwordTextBox_CreateAccount);
			passwordTextBox_CreateAccount.sendKeys(pwd);
			//logger.log(Status.INFO, "Entered password: " + pwd);
			hardWait(2000);
			waitForElementToBeClickable(continueButton_CreateAccount);
			continueButton_CreateAccount.click();
			//ogger.log(Status.INFO, "Clicking on continue button");
			flag = true;
			hardWait(2000);
		}
		return flag;
	}
	
	public boolean loginAndCheckout(String uname, String pwd) {
		boolean flag = false;
		hardWait(8000);
		waitForElementToBeClickable(createAccountLoginButton);
		clickElementUsingJavaScriptExecutor(createAccountLoginButton);
		waitForElementToBeClickable(loginButton);
		loginButton.click();
		//logger.log(Status.INFO, "Clicking on the Login option from My Breville menu.");
		hardWait(5000);
		if (verifyusername()) {
			flag = true;
			waitForElementToBeClickable(username);
			username.sendKeys(uname);
			//logger.log(Status.INFO, "Entered username: " + uname);
			password.sendKeys(pwd);
			//logger.log(Status.INFO, "Entered password: " + pwd);
			hardWait(2000);
			loginButton_SF.click();
			//logger.log(Status.INFO, "Clicked on Login Button");
		} else {
			logger.log(Status.ERROR, "Seems like system is not redirecting to SF login page when user clicks on Login.");
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
		phoneNumber.clear();
		phoneNumber.sendKeys(form[7]);
		continueToPayment.click();
	}
	
	







	
	public String getCartTotal() {
		waitForElementToBeVisible(cartTotal);
		return cartTotal.getText();
	}

	public void removeProduct() {
		waitForElementToBeVisible(remove);
		remove.click();
		waitForElementToBeVisible(cartEmptyMsg);
		cartEmptyMsg.getText();
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
			} catch (Exception e) {
			}
			count++;
			if (result)
				break;
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
	
	
	
	

}
