package website.pagefactory;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import website.base.BaseTest;

public class ProductsPage extends BaseTest {

  //private static ProductsPage productspage = null;

  @FindAll(@FindBy(xpath = "//div[contains(@class,'container')]//div[contains(@class,'item__details')]//h4"))
  private List < WebElement > listOfOptionsForParticularProduct;

  @FindBy(xpath = "//button[contains(@class,'primary js-addToCartBtn')]")
  private WebElement addToCartButton;

  @FindAll(@FindBy(css = "a[class*='primary']"))
  private List<WebElement> goToCartButton;

  @FindAll(@FindBy(xpath = "//a[contains(@class, 'primary')]"))
  private List < WebElement > priceMismatch;

  @FindBy(css = "h2#productAddedMsgBox__title")
  private WebElement productAddedMsg;

  @FindBy(css = "div#productAddedMsgBox__dialog>div>div>div>button")
  private WebElement closeProductAddedDialogBox;

  @FindBy(css = "button[class$='read-more']")
  private WebElement moreOption;

  @FindBy(css = "span[itemprop='model']")
  private WebElement modelNumber;

  public ProductsPage() {
    PageFactory.initElements(driver, this);
  }

 

  public String getModelNumber() {
	  waitForElementToBeVisible(modelNumber);
	  String modelNum = modelNumber.getText();
    return modelNum;
  }

  public void selectItemFromList() {
    waitForElementToBeVisible(moreOption);
    hardWait(5000);
    for (WebElement element: listOfOptionsForParticularProduct) {
      element.click();
      break;
    }
  }

  public void selectItemAndClickAddToCart() {
    selectItemFromList();
    waitForElementToBeVisible(addToCartButton);
    hardWait(2000);
    addToCartButton.click();

  }

  public String getProductAddedMsgFromDialog() {
    waitForElementToBeVisible(productAddedMsg);
    return productAddedMsg.getText();
  }

  public void clickGoToCart() {	 
	  	int size = goToCartButton.size();		 
		 waitForElementToBeClickable(goToCartButton.get(size-1));
		 goToCartButton.get(size-1).click();
  }

  public void clickOnAddToCartButton() {
    waitForElementToBeClickable(addToCartButton);
    clickElementUsingJavaScriptExecutor(addToCartButton);
  }

  public boolean verifyAddToCartIsPresent() {
    return verifyElementIsDisplayed(addToCartButton);
  }

  public boolean verifyAddItemToCart() {
    try {
      hardWait(5000);            
      if (priceMismatch.size() != 0) {       
        return true;
      }
    } catch(Exception e) {
      //logger.log(Status.INFO, "Exception caught while verify 'Add Item To Cart' button : " + e.getMessage());      
    }
    return false;
  }

  public boolean verifyGoToCartButton() {
	 int size = goToCartButton.size();	
	 waitForElementToBeClickable(goToCartButton.get(size-1));
    return verifyElementIsDisplayed(goToCartButton.get(size-1));
  }

}