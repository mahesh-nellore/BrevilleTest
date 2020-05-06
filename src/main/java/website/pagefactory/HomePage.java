package website.pagefactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import website.base.BaseTest;

public class HomePage extends BaseTest {

	@FindBy(xpath = "//span[contains(text(),'Search')]")
	private WebElement searchOption;

	@FindBy(css = "img[title =Breville]")
	private WebElement logo;

	@FindBy(css = "li[data-header-link-label=Products]")
	private WebElement productsLabel;

	@FindBy(css = "li[data-parts-accessory-label ='Parts and Accessories']")
	private WebElement partsAndAccessories;

	@FindBy(css = "li[data-header-link-label ='Recipes']")
	private WebElement recipes;

	@FindBy(xpath = "//span[contains(text(),'Search')]")
	private WebElement search;

	@FindBy(xpath = "(//a[contains(@data-module, 'regionSelect')])[1]")
	private WebElement regionSelect;

	@FindBy(css = "div#chatinvitations")
	private WebElement letsChatButton;

	@FindAll(@FindBy(xpath = "(//div[contains(@class,'o-section__body js-header-clp-overlay')])[1]//img"))
	private List<WebElement> listOfproducts;

	@FindAll(@FindBy(xpath = "(//div[contains(@class,'o-section__body js-header-clp-overlay')])[2]//img"))
	private List<WebElement> listOfPartsAndAccessories;

	@FindBy(css = "input#c-searchbar__search-box")
	private WebElement inputTextForSearch;

	@FindBy(css = "ul#predictive-list")
	private WebElement searchResultOption;

	@FindBy(xpath = "(//div[contains(@class,'o-section__body js-header-clp-overlay')])[1]//img[contains(@alt,'Ovens')]")
	private WebElement selectOvensFromProductList;

	public HomePage() {
		PageFactory.initElements(driver, this);

	}

	public String getTitle() {
		String title = driver.getTitle();
		System.out.println("Title>>" + title);
		return title;

	}

	public boolean verifyWebElement(WebElement element) {
		waitForElement(element);
		if (element.isDisplayed())
			return true;
		else
			return false;
	}

	public boolean verifyLogo() {
		return verifyWebElement(logo);

	}

	public boolean verifyProductLabel() {
		return verifyWebElement(productsLabel);

	}

	public boolean verifySearchOption() {
		return verifyWebElement(searchOption);

	}

	public boolean verifyLetsChatButton() {
		scrollToViewTillElement(letsChatButton);
		return verifyWebElement(letsChatButton);
	}

	public void clickOnProducts() {
		productsLabel.click();
	}

	public int numberOfproductsCount() {
		waitForElement(letsChatButton);
		return listOfproducts.size();
	}

	public Set<String> getListOfproducts() {
		Set<String> set = new HashSet<String>();
		listOfproducts.forEach((WebElement element) -> {
			set.add(element.getAttribute("alt"));
		});
		return set;
	}

	public void clickOnPartsAndAccessoriesLink() {
		partsAndAccessories.click();
	}

	public int numberOfPartsAndAccessoriesCount() {
		waitForElement(letsChatButton);
		return listOfPartsAndAccessories.size();
	}

	public Set<String> getListOfPartsAndAccessories() {
		Set<String> set = new HashSet<String>();
		listOfPartsAndAccessories.forEach((WebElement element) -> {
			set.add(element.getAttribute("alt"));
		});
		return set;
	}

	public void clickOnSearch() {
		hardWait(2000);
		waitForElement(searchOption);
		// searchOption.click();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", searchOption);
	}

	public void searchForProduct(String searchValue) {
		waitForElement(inputTextForSearch);
		inputTextForSearch.sendKeys(searchValue);
		hardWait(2500);
		inputTextForSearch.sendKeys(Keys.ENTER);

	}

	public void selectOvensFromProductList() {
		waitForElement(selectOvensFromProductList);
		selectOvensFromProductList.click();
	}

}
