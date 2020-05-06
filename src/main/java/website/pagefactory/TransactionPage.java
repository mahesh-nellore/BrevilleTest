package website.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

}
