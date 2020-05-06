package website.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import website.base.BaseTest;

public class TransactionPage extends BaseTest {

	@FindBy(xpath = "//a[contains(text(),'Checkout as Guest')]")
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

	@FindBy(xpath = "//button[contains(text(),'Continue to Shipping')]")
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

	@FindBy(xpath = "//button[contains(text(),'Continue To Payment')]")
	private WebElement continueToPayment;

	@FindBy(css = "input#phoneNumber_shipping")
	private WebElement phoneNumber;

	public TransactionPage() {

		PageFactory.initElements(driver, this);
	}

	public boolean verifyCheckoutAsGuest() {
		waitForElement(checkoutAsGuest);
		return checkoutAsGuest.isDisplayed();
	}

	public String getSubTotal() {
		waitForElement(totalPrice);
		System.out.println("Get the Sub Total>>" + totalPrice.getText());
		return totalPrice.getText();

	}

	public void removeProduct() {
		waitForElement(productRemove);
		productRemove.click();
		waitForElement(removeItemInDialogBox);
		removeItemInDialogBox.click();
	}

	public String getCartEmptyMsg() {
		int count = 0;
		boolean result = false;
		while (count < 2) {
			try {
				hardWait(5000);
				result = cartEmptyMsg.isDisplayed();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			count++;
			if (result)
				break;
		}

		System.out.println("Cart is Empty Message>>" + cartEmptyMsg.getText());
		return cartEmptyMsg.getText();
	}

	public void clickCheckoutAsGuestButton() {
		waitForElement(checkoutAsGuest);
		checkoutAsGuest.click();
	}

	public void fillTheForm(String form[]) {
		waitForTheElementToVisible(emailAddress);
		hardWait(2000);
		emailAddress.sendKeys(form[0]);
		continueToShippingButton.click();
		waitForElement(firstname);
		hardWait(5000);
		firstname.sendKeys(form[1]);
		hardWait(1000);
		lastname.sendKeys(form[2]);
		hardWait(1000);
		address1.sendKeys(form[3]);
		hardWait(1000);
		address2.sendKeys(form[4]);
		hardWait(1000);
		city.sendKeys(form[5]);
		hardWait(1000);
		zipcode.sendKeys(form[6]);
		Select select = new Select(state);
		select.selectByVisibleText(form[7]);
		hardWait(1000);
		phoneNumber.clear();
		hardWait(1000);
		phoneNumber.sendKeys(form[8]);
		continueToPayment.click();

	}

}
