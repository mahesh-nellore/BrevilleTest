package website.pagefactory;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.aventstack.extentreports.Status;

import website.base.BaseTest;

public class TransactionPage extends BaseTest {

	private static TransactionPage transactionpage = null;

	@FindBy(id = "onetimecheckoutasguest")
	private WebElement checkoutAsGuest_Registered;

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

	@FindBy(id = "tncCheckboxCreditCart")
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

	@FindBy(id = "password")
	private WebElement authPassword;

	@FindBy(id = "authWindow")
	private WebElement authIframe;
	
	@FindBy(id = "Cardinal-CCA-IFrame")
	private WebElement cardinalIframe;

	@FindBy(name = "UsernamePasswordEntry")
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

	private TransactionPage() {

		PageFactory.initElements(driver, this);
	}

	public static TransactionPage getTransactionPage() {
		if (transactionpage == null)
			transactionpage = new TransactionPage();
		return transactionpage;
	}

	public int getQuantity() {
		waitForElementToBeVisible(quantityDropDown);
		Select select = new Select(quantityDropDown);
		return Integer.parseInt(select.getFirstSelectedOption().getText());
	}

	public String getPlan() {
		waitForElementToBeVisible(subscriptionPlanDropdown);
		Select select = new Select(subscriptionPlanDropdown);
		return select.getFirstSelectedOption().getText();
	}

	public void updateQuantity() {
		String value = Integer.toString(getQuantity() + 1);
		System.out.println("Quantity: " + value);
		Select select = new Select(quantityDropDown);
		select.selectByVisibleText(value);
	}

	public void updatePlan() {
		String value = getPlan();
		logger.log(Status.INFO, "Selected plan is :" + value);
		System.out.println("Selected plan is :" + value);
		Select select = new Select(subscriptionPlanDropdown);
		List<WebElement> options = select.getOptions();
		for (WebElement webElement : options) {
			String option = webElement.getText();
			logger.log(Status.INFO, "Option value is: " + option);
			System.out.println("Option value is: " + option);
			if (!value.equalsIgnoreCase(option)) {
				select.selectByVisibleText(option);
				break;
			}
		}
	}

	public boolean vefiryUpdateQuantity() {
		int initialValue = getQuantity();
		updateQuantity();
		int editedValue = getQuantity();
		if (initialValue < editedValue)
			return true;
		else
			return false;
	}

	public boolean vefiryUpdateplan() {
		String initialValue = getPlan();
		updatePlan();
		String editedValue = getPlan();
		if (!initialValue.equalsIgnoreCase(editedValue))
			return true;
		else
			return false;
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

	public void loginAndCheckout(String uname, String pwd) {
		hardWait(8000);
		logger.log(Status.INFO, "Waiting for few Seconds");
		waitForElementToBeClickable(createAccountLoginButton);
		clickElementUsingJavaScriptExecutor(createAccountLoginButton);
		System.out.println("Clicked On Create account/Login option");
		logger.log(Status.INFO, "Clicked on Login Button");
		waitForElementToBeClickable(loginButton);
		loginButton.click();
		System.out.println("Clicked on Login Button");
		logger.log(Status.INFO, "Clicked on Login Button");
		hardWait(10000);
		logger.log(Status.INFO, "Wait for 10 milli seconds...");
		waitForElementToBeClickable(username);
		username.sendKeys(uname);
		System.out.println("Entered username: " + uname);
		logger.log(Status.INFO, "Entered username: " + uname);
		password.sendKeys(pwd);
		System.out.println("Entered password: " + pwd);
		logger.log(Status.INFO, "Entered password: " + pwd);
		loginButton_SF.click();
		System.out.println("Clicked on Login Button");
		logger.log(Status.INFO, "Clicked on Login Button");
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
		} else if("eu".equalsIgnoreCase(form[8])) {
			region.sendKeys(form[6]);
		}
		phoneNumber.clear();
		phoneNumber.sendKeys(form[7]);
		continueToPayment.click();

	}

	public void paymentUsingCreditCard(String str[]) {
		if (verifyCardHolderName())
			logger.log(Status.INFO, "After merge cart user is navigated to Checkout page.");
		else {
			logger.log(Status.INFO,
					"Seems like Cart has orders in it, hence redirected to Cart page instead of checkout page..");
			checkoutOptForLoggedInUsr.click();
			logger.log(Status.INFO, "Clicked On Chekout Button..");
			waitForElementToBeClickable(cardHolderName);
		}
		cardHolderName.sendKeys(str[0]);
		switchToFrame(iframeBrainTree);
		creditCardNumber.sendKeys(str[1]);
		switchToParentFrame();
		switchToFrame(iframeExpiryDate);
		expiryDate.sendKeys(str[2]);
		switchToParentFrame();
		switchToFrame(iframeCvv);
		cvv.sendKeys(str[3]);
		switchToParentFrame();
		hardWait(2000);
		clickElementUsingJavaScriptExecutor(termsAndConditionsCheckbox);
		waitForElementToBeVisible(submitOrderButton);
		submitOrderButton.click();
		hardWait(5000);
		if ("eu".equalsIgnoreCase(str[4]) || "uk".equalsIgnoreCase(str[4])) {
			logger.log(Status.INFO, " The Order belongs to EU region");
			hardWait(2000);
			switchToFrame(cardinalIframe);
			logger.log(Status.INFO, "Switched to the CC iframe");
			switchToFrame(authIframe);
			logger.log(Status.INFO, "Switched to the Auth iframe");
			authPassword.sendKeys("123");
			submitButtonAuthPage.click();
			hardWait(2000);
			switchToParentFrame();
		}

	}

	public String getPurchaseOrderId() {
		hardWait(5000);
		waitForElementToBeVisible(purchaseOrderId);
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
		} catch (Exception e) {
			logger.log(Status.INFO,
					"Exception Occured while waiting for the cardHolderName and the exception is:" + e.getMessage());
		}
		return false;
	}

	public boolean verifyStateDropdown() {
		return verifyElementIsDisplayed(state);
	}

	public boolean verifyRegionInputTextbox() {
		return verifyElementIsDisplayed(region);
	}

}
