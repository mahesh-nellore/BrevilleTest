package website.pagefactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import website.base.BaseTest;

public class HomePage extends BaseTest {

  private static HomePage homepage = null;

  public static String modelNumber = "";

  @FindBy(xpath = "(//span[contains(@class,'search-link-group')]//span[contains(@class,'text')])[1]")
  private WebElement searchOption;

  @FindBy(css = "img[title =Breville]")
  private WebElement logo;

  @FindBy(css = "li[class$='products js-nav-link']")
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
  private List < WebElement > listOfproducts;

  @FindAll(@FindBy(xpath = "(//div[contains(@class,'o-section__body js-header-clp-overlay')])[2]//img"))
  private List < WebElement > listOfPartsAndAccessories;

  @FindBy(css = "input#c-searchbar__search-box")
  private WebElement inputTextForSearch;

  @FindBy(css = "ul#predictive-list")
  private WebElement searchResultOption;

  @FindBy(xpath = "(//div[contains(@class,'o-section__body js-header-clp-overlay')])[1]//img[contains(@alt,'Ovens')]")
  private WebElement selectOvensFromProductList;

  @FindBy(xpath = "(//img[contains(@src,'tea')])[1]")
  private WebElement kettlesImage;

  @FindAll(@FindBy(xpath = "//div[contains(@data-url, 'tea')]"))
  private List < WebElement > listOfKettles;

  @FindBy(css = "a[class$='return-link--arrow']")
  private WebElement returnToKettlesPage;

  @FindBy(xpath = "//div[contains(@class,'close')]")
  private WebElement newsLetterCloseButton;

  @FindBy(id = "regionPopupConfirm__dialog")
  private WebElement regionSelectPopup;

  @FindBy(css = "a[data-country$='de']")
  private WebElement selectGermanyInPopup;

  @FindBy(css = "img[src*='americas']")
  private WebElement selectRegionAmericas;

  @FindBy(css = "a[data-country='us']")
  private WebElement selectCountryUS;

  @FindBy(css = "a[data-country='ca']")
  private WebElement selectCountryCAFR;

  @FindBy(css = "a[href*='ca/en']")
  private WebElement selectCountryCAEN;

  @FindBy(css = "a[href*='uk/en']")
  private WebElement selectCountryUK;

  @FindBy(css = "img[src*='western-europe']")
  private WebElement selectRegionEurope;

  @FindBy(css = "a[data-country='de']")
  private WebElement selectCountryGerman;

  @FindBy(id = "signUpBtn")
  private WebElement newsLetterSignupButton;

  @FindBy(id = "_evidon-accept-button")
  private WebElement evidonAcceptButton;

  private HomePage() {
    PageFactory.initElements(driver, this);
  }

  public boolean isRegionSelectPopupDisplayed() {
    return isElementPresent(regionSelectPopup);

  }

  public boolean verifyEvidonAcceptButton() {
    return verifyElementIsDisplayed(evidonAcceptButton);
  }

  public void acceptEvidonAcceptButton() {
    try {
      clickElementUsingJavaScriptExecutor(evidonAcceptButton);
      logger.log(Status.INFO, "clicked on evidon Acept button");
    } catch(Exception e) {
      // TODO: handle exception
    }
  }

  public void selectCountry(String str) {
    if ("us".equalsIgnoreCase(str)) {
      waitForElementToBeClickable(selectRegionAmericas);
      selectRegionAmericas.click();
      logger.log(Status.INFO, "Chosen continent as America.");
      waitForElementToBeClickable(selectCountryUS);
      selectCountryUS.click();
      logger.log(Status.INFO, "Choseb country as USA");
      hardWait(2000);
      selectRegionAmericas.click();
    } else if ("ca".equalsIgnoreCase(str)) {
      waitForElementToBeClickable(selectRegionAmericas);
      selectRegionAmericas.click();
      logger.log(Status.INFO, "Chosen continent as America.");
      waitForElementToBeClickable(selectCountryCAEN);
      selectCountryCAEN.click();
      logger.log(Status.INFO, "Chosen country as USA.");
      selectRegionAmericas.click();
    } else if ("cafr".equalsIgnoreCase(str)) {
      waitForElementToBeClickable(selectRegionAmericas);
      selectRegionAmericas.click();
      logger.log(Status.INFO, "Chosen continent as America.");
      waitForElementToBeClickable(selectCountryCAFR);
      selectCountryCAFR.click();
      logger.log(Status.INFO, "Chosen country as USA.");
      selectRegionAmericas.click();
    } else if ("eu".equalsIgnoreCase(str)) {
      waitForElementToBeClickable(selectRegionEurope);
      selectRegionEurope.click();
      logger.log(Status.INFO, "Chosen continent as Europe.");
      waitForElementToBeClickable(selectCountryGerman);
      selectCountryGerman.click();
      logger.log(Status.INFO, "Chosen country as German.");
      selectRegionEurope.click();
    } else {
      waitForElementToBeClickable(selectRegionEurope);
      selectRegionEurope.click();
      logger.log(Status.INFO, "Chosen continent as Europe.");
      waitForElementToBeClickable(selectCountryUK);
      selectCountryUK.click();
      logger.log(Status.INFO, "Chosen country as UK.");
    }

  }

  public void selectCountryFromPopUp(String str) {
    while (isRegionSelectPopupDisplayed()) {
      WebElement country = driver.findElement(By.cssSelector("a[data-country$='" + str + "']"));
      waitForElementToBeClickable(country);
      clickElementUsingJavaScriptExecutor(country);
      hardWait(5000);
    }

  }

  public void closeNewsLetterPopUp() {
    logger.log(Status.INFO, "Wait for the close icon to be displayed in the newsletter pop up");
    waitForElementToBeClickable(newsLetterCloseButton);
    newsLetterCloseButton.click();
    logger.log(Status.INFO, "Newsletter popup closed.");
    isNewsLetterPopUpDisplayed = true;
  }

  public boolean verifyNewsLetterPopUpIsDisplayed() {
    hardWait(2000);
    return verifyElementIsDisplayed(newsLetterSignupButton);
  }

  public static HomePage getHomePage() {
    if (homepage == null) homepage = new HomePage();
    return homepage;
  }

  public String getTitle() {
    String title = driver.getTitle();
    System.out.println("Title>>" + title);
    return title;

  }

  public boolean verifyWebElement(WebElement element) {
    waitForElementToBeVisible(element);
    if (element.isDisplayed()) return true;
    else return false;
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
    waitForElementToBeClickable(productsLabel);
    productsLabel.click();
    logger.log(Status.INFO, "Clicking on the products on the home page.");
    if (!isNewsLetterPopUpDisplayed) if (verifyNewsLetterPopUpIsDisplayed()) closeNewsLetterPopUp();
  }

  public int numberOfproductsCount() {
    waitForElementToBeVisible(letsChatButton);
    return listOfproducts.size();
  }

  public Set < String > getListOfproducts() {
    Set < String > set = new HashSet < String > ();
    listOfproducts.forEach((WebElement element) ->{
      set.add(element.getAttribute("alt"));
    });
    return set;
  }

  public void clickOnPartsAndAccessoriesLink() {
    partsAndAccessories.click();
  }

  public int numberOfPartsAndAccessoriesCount() {
    waitForElementToBeVisible(letsChatButton);
    return listOfPartsAndAccessories.size();
  }

  public Set < String > getListOfPartsAndAccessories() {
    Set < String > set = new HashSet < String > ();
    listOfPartsAndAccessories.forEach((WebElement element) ->{
      set.add(element.getAttribute("alt"));
    });
    return set;
  }

  public void clickOnSearch() {
    hardWait(2000);
    waitForElementToBeClickable(searchOption);
    clickElementUsingJavaScriptExecutor(searchOption);
  }

  public void searchForProduct(String searchValue) {
    waitForElementToBeClickable(inputTextForSearch);
    inputTextForSearch.sendKeys(searchValue);
    logger.log(Status.INFO, "Searching for the subscription product: " + searchValue);
    if (verifySearchresultOption()) {
      searchResultOption.click();
    } else {
      inputTextForSearch.sendKeys(Keys.RETURN);
    }
  }

  public boolean verifySearchresultOption() {
    return verifyElementIsDisplayed(searchResultOption);
  }

  public void selectOvensFromProductList() {
    waitForElementToBeVisible(selectOvensFromProductList);
    selectOvensFromProductList.click();
  }

  public void clickOnKettlesImage() {
    waitForElementToBeClickable(kettlesImage);
    clickElementUsingJavaScriptExecutor(kettlesImage);
    logger.log(Status.INFO, "Clicking on the Kettle on the PLP page.");
    if (!isNewsLetterPopUpDisplayed) if (verifyNewsLetterPopUpIsDisplayed()) closeNewsLetterPopUp();
  }

  public String addKettleToCart() {
    hardWait(5000);
    waitForElementToBeClickable(listOfKettles.get(0));
    int totalKettles = (listOfKettles.size()) - 1;
    logger.log(Status.INFO, "The total number of products displayed in the web page under Kettles are: " + totalKettles);
    WebElement element = null;
    while (totalKettles > 0) {
      try {
        element = listOfKettles.get(totalKettles);
        clickElementUsingJavaScriptExecutor(element);
        totalKettles--;
      } catch(Exception e) {
        logger.log(Status.INFO, "Exception occured while adding the kettle to the Cart: " + e.getMessage());
      }
      boolean flag = productspage.verifyAddToCartIsPresent();
      if (flag) {
        modelNumber = productspage.getModelNumber();
        logger.log(Status.INFO, "Add to cart option is available for the product: " + modelNumber);
        productspage.clickOnAddToCartButton();
        if (productspage.verifyGoToCartButton() || productspage.verifyAddItemToCart()) break;
        else {
          waitForElementToBeClickable(returnToKettlesPage);
          clickElementUsingJavaScriptExecutor(returnToKettlesPage);
        }
      } else {
        waitForElementToBeClickable(returnToKettlesPage);
        clickElementUsingJavaScriptExecutor(returnToKettlesPage);
      }

    }
    logger.log(Status.INFO, "Adding the product to the Cart: " + modelNumber);
    return modelNumber;

  }

}