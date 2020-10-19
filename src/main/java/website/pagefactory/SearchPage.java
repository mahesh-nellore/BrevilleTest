package website.pagefactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import website.base.BaseTest;

public class SearchPage extends BaseTest {

	@FindBy(css = "div#chatinvitations")
	private WebElement letsChatButton;

	@FindBy(css = "span.js-result-count")
	private WebElement searchResultMsg;

	@FindAll(@FindBy(xpath = "//div[contains(@class,'container')]//div[contains(@class,'item__details')]//h3"))
	private List<WebElement> listOfSearchResults;

	@FindBy(css = "div[class='item__details']>h3")
	private WebElement modelIdBOV900;

	@FindBy(xpath = "//div[contains(@class,'grid-item')]//img")
	private WebElement clickOnSearchresultItem;

	@FindBy(css = "section[class*='no-result-found']")
	private WebElement noRecordsFoundMessage;

	public SearchPage() {
		PageFactory.initElements(driver, this);
	}

	public int getSearchResultCount() {
		waitForElementToBeVisible(searchResultMsg);
		System.out.println("Search Result Count>>" + searchResultMsg.getText());
		return Integer.parseInt(searchResultMsg.getText());
	}

	public Set<String> getListOfNamesDisplayedAsPerSearch() {
		Set<String> set = new HashSet<String>();
		listOfSearchResults.forEach((WebElement element) -> {
			set.add(element.getText());
		});
		return set;
	}

	public void selectModelIdBOV900() {
		waitForElementToBeVisible(modelIdBOV900);
		hardWait(5000);
		modelIdBOV900.click();
	}

	public boolean clickOnSearchResultProduct() {
		if (!verifyElementIsDisplayed(noRecordsFoundMessage)) {
			waitForElementToBeClickable(clickOnSearchresultItem);
			hardWait(2000);
			clickElementUsingJavaScriptExecutor(clickOnSearchresultItem);
			logger.log(Status.INFO, "Clicked on Search Result Item");
			return true;
		}
		return false;

	}

	public void addSearchItemToCart() {
		hardWait(5000);
		logger.log(Status.INFO, "Waiting for few milli seconds..");
		if (productspage.verifyAddToCartIsPresent()) {
			productspage.clickOnAddToCartButton();
		} else {

		}

	}

	public void addSubscriptionProduct(String str) {
		List<String> list = null;
		String [] subscriptions = {"BES001XL", "BES008WHT0NUC1", "sp0001474", "SP0010662",
				"BES0070NUC1", "BES0030NUC1", "BEC2501BCA1", "BWF1000NUC1", "SP0001876", "SP0001801", "SP0001528",
				"SP0001879", "SP0021701"};		
		String [] spareParts = {"SP0016051", "SP0001850", "SP0001834", "SP0001875", "SP0001635",
				"SP0001880", "SP0001789", "SP0001798", "BEC25000US1", "BES001XL", "SP0001881", "SP0001788"};
		String[] spareParts_Eu = {"SP0001801", "SP0020146", "SP0020150", "SP0020143", "SP0020147",
				"SP0020152", "SP0020151", "SP0020149"};
		if("us".equalsIgnoreCase(str)) 
			list = Arrays.asList(spareParts);
		else if("ca".equalsIgnoreCase(str))
			list = Arrays.asList(subscriptions);
		else
			list = Arrays.asList(spareParts_Eu);
		for (String string : list) {
			homepage.clickOnSearch();
			logger.log(Status.INFO, "Searching for the product: " + string);
			System.out.println("Searching for the product: " + string);
			homepage.searchForProduct(string);
			logger.log(Status.INFO, "Waiting for the search result");
			System.out.println("Waiting for the search result");
			if (clickOnSearchResultProduct()) {
				logger.log(Status.INFO, "Waiting for few seconds to redirect from PLP to PDP page");
				boolean isAddToCartDisplayed = productspage.verifyAddToCartIsPresent();
				if (isAddToCartDisplayed) {
					logger.log(Status.INFO, "Add To Cart button is displayed for the product: " + string);
					System.out.println("Add To Cart button is displayed for the product: " + string);
					productspage.clickOnAddToCartButton();
					logger.log(Status.INFO, "Clicked on Add To Cart Button");
					System.out.println("Clicked on Add To Cart Button");
					if (productspage.verifyGoToCartButton() || productspage.verifyAddItemToCart())
						break;
				} else {
					logger.log(Status.INFO, "Add To Cart button is not displayed for the product: " + string);
					System.out.println("Add To Cart button is not displayed for the product: " + string);
				}
			}

		}

	}

}
