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
  private List < WebElement > listOfSearchResults;

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

  public Set < String > getListOfNamesDisplayedAsPerSearch() {
    Set < String > set = new HashSet < String > ();
    listOfSearchResults.forEach((WebElement element) ->{
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
      logger.log(Status.INFO, "The product is displayed on the web page based on search criteria And selected the displayed product");

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
    hardWait(2000);
    List < String > list = null;
    String[] subscriptions = {
      "BES001XL",
      "BES008WHT0NUC1",
      "sp0001474",
      "SP0010662",
      "BES0070NUC1",
      "BES0030NUC1",
      "BEC2501BCA1",
      "BWF1000NUC1",
      "SP0001876",
      "SP0001801",
      "SP0001528",
      "SP0001879",
      "SP0021701"
    };
    String[] spareParts = {
      "sp0010662",
      "SP0010402",
      "BEC25000US1",
      "bes008wht0nuc1",
      "bes0070nuc1",
      "sp0001474",
      "SP0016051",
      "SP0001850",
      "SP0001834",
      "SP0001875",
      "SP0001635",
      "SP0001880",
      "SP0001789",
      "SP0001798",
      "BEC25000US1",
      "BES001XL",
      "SP0001881",
      "SP0001788"
    };
    String[] spareParts_Uk = {
      "SES008WHT0NEU1",
      "BES007UK",
      "BEC250UK",
      "BES006UK",
    };
    String[] spareParts_Eu = {
      "SES008WHT0NEU1",
      "SES007NEU0NEU1",
      "SES006NEU0NEU1",
      "SEC250NEU0NEU1",
      "BES007UK",
      "BEC250UK",
      "BES006UK",
      "SP0020075",
      "SP0020082",
      "SP0021689",
      "SP0020085",
      "SP0021595",
      "SP0001801",
      "SP0020146",
      "SP0020150",
      "SP0020143",
      "SP0020147",
      "SP0020152",
      "SP0020151",
      "SP0020149"
    };
    String[] spareparts_au = {
      "BES012CLR0NAN1",
      "BES040GRY0NAN1",
      "BES015CLR0NAN1",
      "BES006",
      "BES009CLR0NAN1",
      "BES010CLR0NAN1",
      "BES480BSS0NAN1",
      "BES030BSS0NAN1",
      "BES013CLR0NAN1",
      "BWF100",
      "BES035BLK0NAN1",
      "BES011CLR0NAN1",
      "BES008WHT0NAN1",
      "BCB100BSS",
      "SP0016051",
      "AQP-CJUG",
      "AQP-24CS",
      "AQP-BM4"
    };
    if ("us".equalsIgnoreCase(str)) list = Arrays.asList(spareParts);
    else if ("ca".equalsIgnoreCase(str) || "cafr".equalsIgnoreCase(str)) list = Arrays.asList(subscriptions);
    else if ("uk".equalsIgnoreCase(str)) list = Arrays.asList(spareParts_Uk);
    else if ("au".equalsIgnoreCase(str)) list = Arrays.asList(spareparts_au);
    else list = Arrays.asList(spareParts_Eu);
    for (String string: list) {
      homepage.clickOnSearch();
      homepage.searchForProduct(string);
      if (clickOnSearchResultProduct()) {
    	  hardWait(5000);
        boolean isAddToCartDisplayed = productspage.verifyAddToCartIsPresent();
        if (isAddToCartDisplayed) {
          logger.log(Status.INFO, "Add To Cart button is displayed for the product: " + string);
          productspage.clickOnAddToCartButton();
          logger.log(Status.INFO, "Clicked on Add To Cart Button");
          hardWait(8000);
          while(transactionpage.verifyLoaderImage()) {
        	  hardWait(5000);
          }
          if (transactionpage.verifyProductOutOfStockAlert()) {
            System.out.println("Pop up opened on clicking Add to cart button");
            transactionpage.handleProductOutOfStockAlert();
            productspage.clickOnAddToCartButton();
            hardWait(3000);
            if (transactionpage.verifyProductOutOfStockAlert()) {
              System.out.println("Pop up opened on clicking Add to cart button");
              transactionpage.handleProductOutOfStockAlert();
            } 
          } else if (productspage.verifyGoToCartButton() || productspage.verifyAddItemToCart()) {
          	break;
          }
        }else {
            logger.log(Status.INFO, "Add To Cart button is not displayed for the product: " + string);
            System.out.println("Add To Cart button is not displayed for the product: " + string);
          }

      }

    }
  }
}