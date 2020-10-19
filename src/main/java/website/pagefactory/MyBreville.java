package website.pagefactory;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

import website.base.BaseTest;
import website.generic.GenericUtility;

public class MyBreville extends BaseTest {

	private static MyBreville mybreville = null;

	@FindBy(id = "js-my-breville-label")
	private WebElement mybrevilleMenu;

	@FindBy(xpath = "(//a[contains(@href,'personal')])[1]")
	private WebElement personalDetails;

	@FindBy(xpath = "//button[contains(text(),'Edit')]")
	private WebElement editButtonInPersonalDetails;

	@FindBy(xpath = "(//button[contains(text(),'Save')])[2]")
	private WebElement savePersonalDetails;

	@FindBy(id = "phoneNumber")
	private WebElement phonenumber_personalDetails;

	@FindBy(xpath = "//p[contains(@class,'phoneText')]")
	private WebElement phoneNumberText_PersonalDetails;

	@FindBy(xpath = "(//a[contains(@class,'breville')])[1]")
	private WebElement myBrevilleHubPage;

	@FindBy(xpath = "(//a[contains(@href, '#orders')])[3]")
	private WebElement orders_hubpage;

	@FindBy(xpath = "(//a[contains(@href, '#subscriptions')])[3]")
	private WebElement subscriptions_hubpage;

	@FindBy(xpath = "(//a[contains(@href, '#personal')])[3]")
	private WebElement personal_hubpage;

	@FindBy(xpath = "(//a[contains(@href, 'support')])[1]")
	private WebElement createSupportTicket_hubpage;

	@FindBy(xpath = "(//a[contains(text(),'Update a support ')])[3]")
	private WebElement updateSupportTicket_hubpage;

	/*
	 * Subscription page
	 */
	@FindBy(xpath = "(//div[contains(@id, 'subscriptionsData')]/div//span[contains(@class,'order-model-id')])[1]")
	private WebElement firstSubscriptionModelNumber;

	@FindBy(xpath = "(//div[contains(@id, 'subscriptionsData')]//div[contains(@class,'order-status')])[2]//span[2]")
	private WebElement nextDeliveryDateOfSubscriptions;

	@FindBy(xpath = "(//div[contains(@class,'quantityEveryEdited')]//img)[1]")
	private WebElement editSubscriptionplanDetails;

	@FindBy(css = "span[class*='quantityText-0']")
	private WebElement quanityValue;

	@FindBy(css = "span[class*='frequencyText-0']")
	private WebElement planValue;

	@FindBy(id = "quantity-0")
	private WebElement quantityDropdown;

	@FindBy(id = "item-subscription-0")
	private WebElement subscriptionDropdown;

	@FindBy(xpath = "(//button[contains(@class,'save-subscriptionDetail')])[1]")
	private WebElement saveSubscriptionButton;

	@FindBy(xpath = "(//div[contains(@class,'addressSave')])[1]//span[2]")
	private WebElement shippingAddr_value;

	@FindBy(name = "s_Address_Line_1")
	private WebElement shippingAddr_Addr1;

	@FindBy(xpath = "(//button[contains(@class,'postShippingAddress')])[1]")
	private WebElement saveShippingAddress;

	@FindBy(xpath = "(//div[contains(@class,'open')]//span)[1]")
	private WebElement firstOrderNumber_OrdersPage;

	@FindBy(xpath = "(//a[contains(@class,'editAddress')])[1]")
	private WebElement editButtonForShippingAddress;

	@FindBy(xpath = "(//button[contains(@class,'cancelSubBtn')])[1]")
	private WebElement cancelButton_subscription;

	@FindBy(css = "button[class*='canceItBtn']")
	private WebElement cancelButton_ConfirmationPopUp;

	@FindBy(xpath = "(//span[contains(text(),'Cancelled')])[2]")
	private WebElement cancelledStatus;

	public MyBreville() {
		PageFactory.initElements(driver, this);
	}

	public static MyBreville getMyBrevillePage() {
		if (mybreville == null)
			mybreville = new MyBreville();
		return mybreville;
	}

	public void clickMyBrevilleMenu() {
		waitForElementToBeClickable(mybrevilleMenu);
		mybrevilleMenu.click();
		logger.log(Status.INFO, "Clicked on My Breville Menu");
	}

	public void clickPersonalDetails() {
		waitForElementToBeClickable(personalDetails);
		personalDetails.click();
		logger.log(Status.INFO, "Clicked on Personal Details option");
	}

	public void clickEditPersonalDetails() {
		waitForElementToBeClickable(editButtonInPersonalDetails);
		editButtonInPersonalDetails.click();
		logger.log(Status.INFO, "clicked on Edit button in personal details");
	}

	public void ClickSaveOption_PersonalDetails() {
		waitForElementToBeClickable(savePersonalDetails);
		savePersonalDetails.click();

	}

	public void updatePhoneNumber_PersonalDetails() {
		waitForElementToBeClickable(phonenumber_personalDetails);
		phonenumber_personalDetails.clear();
		phonenumber_personalDetails.sendKeys(GenericUtility.generateRandomPhoneNumber());
	}

	public String getPhoneNumber_personalDetails() {
		hardWait(8000);
		logger.log(Status.INFO, "Waiting for 10 milli seconds to update the Details..");
		waitForElementToBeVisible(phoneNumberText_PersonalDetails);
		return phoneNumberText_PersonalDetails.getText();
	}

	public void clickOnBrevilleHubPage() {
		waitForElementToBeClickable(myBrevilleHubPage);
		myBrevilleHubPage.click();
		logger.log(Status.INFO, "Clicked on My Breville Hub Page");
	}

	public boolean verifyOrders_hubpage() {
		waitForElementToBeVisible(orders_hubpage);
		boolean flag = isElementPresent(orders_hubpage);
		logger.log(Status.INFO, "Is Orders hyperlink displayed in the hub page: " + flag);
		return flag;
	}

	public void clickOnOrders_hubpage() {
		waitForElementToBeClickable(orders_hubpage);
		orders_hubpage.click();
		logger.log(Status.INFO, "Clicked on Orders in My Breville hub page");
	}

	public boolean verifySubscriptions_hubpage() {
		waitForElementToBeVisible(subscriptions_hubpage);
		boolean flag = isElementPresent(subscriptions_hubpage);
		logger.log(Status.INFO, "Is Subscriptions hyperlink displayed in the hub page: " + flag);
		return flag;
	}

	public void clickOnSubscription_hubpage() {
		waitForElementToBeClickable(subscriptions_hubpage);
		subscriptions_hubpage.click();
		logger.log(Status.INFO, "Clicked on Subscriptions in My Breville hub page");
	}

	public boolean verifyPersonalDetails_hubpage() {
		waitForElementToBeVisible(personal_hubpage);
		boolean flag = isElementPresent(personal_hubpage);
		logger.log(Status.INFO, "Is personal details hyperlink displayed in the hub page: " + flag);
		return flag;
	}

	public boolean verifyCreateSupportTicket_hubPage() {
		waitForElementToBeVisible(createSupportTicket_hubpage);
		boolean flag = isElementPresent(createSupportTicket_hubpage);
		logger.log(Status.INFO, "Is Create support ticket hyperlink displayed in the hub page: " + flag);
		return flag;
	}

	public boolean verifyUpdateSupportTicket_hubpage() {
		waitForElementToBeVisible(updateSupportTicket_hubpage);
		boolean flag = isElementPresent(updateSupportTicket_hubpage);
		logger.log(Status.INFO, "Is Update support ticket hyperlink displayed in the hub page: " + flag);
		return flag;
	}

	public boolean verifyLinksInHubpage() {
		if (verifyOrders_hubpage() && verifySubscriptions_hubpage() && verifyPersonalDetails_hubpage()
				&& verifyCreateSupportTicket_hubPage() && verifyUpdateSupportTicket_hubpage())
			return true;
		else
			return false;
	}

	public String getFirstSubscriptionModelNumber() {
		waitForElementToBeVisible(firstSubscriptionModelNumber);
		String value = firstSubscriptionModelNumber.getText();
		logger.log(Status.INFO, "The Subscription product model number is: " + value);
		return value;
	}

	public String getNextDeliveryDateOfSubscription() {
		waitForElementToBeVisible(nextDeliveryDateOfSubscriptions);
		String value = nextDeliveryDateOfSubscriptions.getText();
		logger.log(Status.INFO, "The Subscription product next delivery date is: " + value);
		return value;
	}

	public String getQuantityValue() {
		waitForElementToBeVisible(quanityValue);
		String value = quanityValue.getText();
		logger.log(Status.INFO, "The Subscription quantity value is: " + value);
		return value;
	}

	public String getPlanValue() {
		waitForElementToBeVisible(planValue);
		String value = planValue.getText();
		logger.log(Status.INFO, "The Subscription plan value is: " + value);
		System.out.println("The Subscription plan value is: " + value);
		return value;
	}

	public void clickOnEditSubscription() {
		waitForElementToBeClickable(editSubscriptionplanDetails);
		editSubscriptionplanDetails.click();
		logger.log(Status.INFO, "Clicked on Edit Quantity");
		System.out.println("Clicked on Edit Quantity");
	}

	public void updateSubscriptionQty(String value) {
		waitForElementToBeClickable(quantityDropdown);
		Select select = new Select(quantityDropdown);
		logger.log(Status.INFO, "Clicked on Quantity drop down");
		System.out.println("Clicked on Quantity drop down");
		List<WebElement> options = select.getOptions();
		for (WebElement webElement : options) {
			String option = webElement.getText();
			if (!value.equalsIgnoreCase(option)) {
				logger.log(Status.INFO, "Option value is: " + option);
				select.selectByVisibleText(option);
				break;
			}
		}
	}

	public void updateSubscriptionPlan(String value) {
		waitForElementToBeClickable(subscriptionDropdown);
		Select select = new Select(subscriptionDropdown);
		logger.log(Status.INFO, "Clicked on Subscription drop down");
		System.out.println("Clicked on Subscription drop down");
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

	public void saveSubscriptionDetail() {
		waitForElementToBeClickable(saveSubscriptionButton);
		saveSubscriptionButton.click();
		logger.log(Status.INFO, "Clicked on Save Subscription button");
		System.out.println("Clicked on Save Subscription button");
		hardWait(5000);
		// waitForElementToBeClickable(cancelButton_Subscriptions);
		logger.log(Status.INFO, "Waiting for few seconds to update the subscription details");
		System.out.println("Waiting for few seconds to update the subscription details");
	}

	public void clickEditButtonForSubscriptionShippingAddr() {
		waitForElementToBeClickable(editButtonForShippingAddress);
		editButtonForShippingAddress.click();
		logger.log(Status.INFO, "Click on edit shipping address button for the subscription product");
	}

	public String getShippingAddr_Addr1_Value() {
		waitForElementToBeClickable(shippingAddr_value);
		hardWait(5000);
		String value = shippingAddr_value.getText().split(",")[0].trim();
		logger.log(Status.INFO, "Shipping Address - Address Line 1 value is: " + value);
		System.out.println("Shipping Address - Address Line 1 value is: " + value);
		return value;
	}

	public void updateShippingAddress_subscriptions(String str) {
		shippingAddr_Addr1.clear();
		shippingAddr_Addr1.sendKeys(str);
		waitForElementToBeClickable(saveShippingAddress);
		saveShippingAddress.click();
		logger.log(Status.INFO, "Clicked on Save Shipping Address");
		System.out.println("Clicked on Save Shipping Address");
		hardWait(5000);
		// waitForElementToBeVisible(cancelButton_Subscriptions);
		logger.log(Status.INFO, "Waiting for few seconds to update the shipping address details");
		System.out.println("Waiting for few seconds to update the shipping address details");
	}

	public boolean verifyQuantityAndPlanUpdate() {
		String qty = getQuantityValue();
		clickOnEditSubscription();
		updateSubscriptionQty(qty);
		saveSubscriptionDetail();
		while (qty.equalsIgnoreCase(getQuantityValue())) {
			hardWait(2000);
			System.out.println("....Waiting for few more seconds to update the subscription details..");
		}
		String qty1 = getQuantityValue();
		boolean quantity = false;
		if (!qty.equalsIgnoreCase(qty1)) {
			logger.log(Status.INFO, "Subscription quantity is updated successfully");
			quantity = true;
		}
		if (quantity)
			return true;
		return false;

	}

	public boolean verifyUpdateShippingAddr(String str) {
		String value = getShippingAddr_Addr1_Value();
		waitForElementToBeClickable(editButtonForShippingAddress);
		logger.log(Status.INFO, "Click on subscription shipping adress edit button");
		System.out.println("Click on subscription shipping adress edit button");
		editButtonForShippingAddress.click();
		// hardWait(5000);
		updateShippingAddress_subscriptions(str);
		while (value.equalsIgnoreCase(getShippingAddr_Addr1_Value())) {
			hardWait(2000);
			System.out.println("....Waiting for few more seconds to update subscription shipping address details..");
		}
		String value1 = getShippingAddr_Addr1_Value();
		if (!value.equalsIgnoreCase(value1))
			return true;
		return false;
	}

	public String getFirstOrderNumber_OrdersPage() {
		hardWait(5000);
		waitForElementToBeVisible(firstOrderNumber_OrdersPage, 90);
		logger.log(Status.INFO, "Waiting for few seconds to load the Orders page");
		String value = firstOrderNumber_OrdersPage.getText();
		System.out.println("Order Number: " + value);
		String orderNumber = value.split("\\.")[1];
		System.out.println("Order number: " + orderNumber);
		logger.log(Status.INFO, "Order number: " + orderNumber);
		return orderNumber;
	}

	public boolean verifyCancelSubscription() {
		logger.log(Status.INFO, "Waiting for the Cancel button for subscriptions..");
		waitForElementToBeClickable(cancelButton_subscription);
		cancelButton_subscription.click();
		logger.log(Status.INFO, "Subscription - Clicked on Cancel button");
		waitForElementToBeClickable(cancelButton_ConfirmationPopUp);
		cancelButton_ConfirmationPopUp.click();
		logger.log(Status.INFO, "Clicked on Cancel button in the Confirmation pop up");
		waitForElementToBeClickable(cancelledStatus);
		if (verifyCancelledStatus())
			return true;
		return false;

	}

	public boolean verifyCancelledStatus() {
		try {
			if (cancelledStatus.isDisplayed())
				return true;
		} catch (Exception e) {
			logger.log(Status.ERROR, "Exception occured while verifiying the Cancelled Status");
		}
		return false;
	}

}
