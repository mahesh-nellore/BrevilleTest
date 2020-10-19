package website.pagefactory;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import website.base.BaseTest;

public class ProductsPage extends BaseTest {

	private static ProductsPage productspage = null;

	@FindAll(@FindBy(xpath = "//div[contains(@class,'container')]//div[contains(@class,'item__details')]//h4"))
	private List<WebElement> listOfOptionsForParticularProduct;

	@FindBy(xpath = "//button[contains(@class,'primary js-addToCartBtn')]")
	private WebElement addToCartButton;

	@FindBy(css = "a[class$='primary']")
	private WebElement goToCartButton;
	
	@FindAll(@FindBy(xpath = "//a[contains(@class, 'primary')]"))
	private List<WebElement> priceMismatch;

	@FindBy(css = "h2#productAddedMsgBox__title")
	private WebElement productAddedMsg;

	@FindBy(css = "div#productAddedMsgBox__dialog>div>div>div>button")
	private WebElement closeProductAddedDialogBox;

	@FindBy(css = "button[class$='read-more']")
	private WebElement moreOption;

	@FindBy(css = "span[itemprop='model']")
	private WebElement modelNumber;

	private ProductsPage() {
		PageFactory.initElements(driver, this);
	}

	public static ProductsPage getProductsPage() {
		if (productspage == null)
			productspage = new ProductsPage();
		return productspage;
	}

	public String getModelNumber() {
		waitForElementToBeVisible(modelNumber);
		return modelNumber.getText();
	}

	public void selectItemFromList() {
		waitForElementToBeVisible(moreOption);
		hardWait(5000);
		for (WebElement element : listOfOptionsForParticularProduct) {
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
		hardWait(5000);
		logger.log(Status.INFO,"Is Price Mismatch is True: "+priceMismatch.size());
		logger.log(Status.INFO,"Xpath: "+priceMismatch.get(priceMismatch.size()-1));
		
		if(priceMismatch.size()>1) {
			int count = 0;
			for (WebElement webElement : priceMismatch) {
				logger.log(Status.INFO, webElement.getText());
					if(count == priceMismatch.size()-1)
						webElement.click();
					count++;
			}
		}else {
			logger.log(Status.INFO,"There is no price mismatch");
			/*WebElement element = driver.findElement(By.xpath("(//a[contains(@class, 'primary')])[2]"));
			waitForElementToBeClickable(element);
			element.click();*/
		}				
		waitForElementToBeVisible(goToCartButton);
		goToCartButton.click();
	}

	public void clickOnAddToCartButton() {
		waitForElementToBeVisible(addToCartButton);
		clickElementUsingJavaScriptExecutor(addToCartButton);
	}

	public boolean verifyAddToCartIsPresent() {
		//return isElementPresent(addToCartButton);
		return verifyElementIsDisplayed(addToCartButton);

	}
	
	public boolean verifyAddItemToCart() {
		try {
			hardWait(5000);
			logger.log(Status.INFO, "waiting for few seconds for the Pop up to be opened");
			System.out.println("waiting for few seconds for the Pop up to be opened");
			if(priceMismatch.size()!=0) {
				logger.log(Status.INFO, "Add Item to Cart is Displayed");
				System.out.println("Add Item to Cart is Displayed");
				return true;
			}
		}catch(Exception e){
			logger.log(Status.INFO, "Exception caught while verify 'Add Item To Cart' button : "+e.getMessage());
			System.out.println("Exception caught while verify 'Add Item To Cart' button : "+e.getMessage());
		}return false;
	}
	
	public boolean verifyGoToCartButton() {
		//waitForElementToBeVisible(goToCartButton);
		//return isElementPresent(goToCartButton);
		return verifyElementIsDisplayed(goToCartButton);
	}

}
