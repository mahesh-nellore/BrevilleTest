package website.pagefactory;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import website.base.BaseTest;

public class BeanzAddToCartPage extends BaseTest {
	
	 @FindAll(@FindBy(css = "a[class*='primary']"))
	  private List<WebElement> goToCartButton;
	 
	 @FindBy(xpath = "//button[contains(@class,'btn btn-primary addToCartBtn fluid')]")
	  private WebElement addToCartButton;
	 
	 @FindBy(xpath = "//button[contains(text(),'Add to Cart')]")
	 private WebElement addToCartFromDetailPage;
	 
	 @FindBy(xpath = "//p[contains(text(),'Espresso | Blend | Darker Roast')]")
	 private WebElement beanzNameInCartPage;
	 
	 public BeanzAddToCartPage() {
		    PageFactory.initElements(driver, this);
		  }
	 
	 public void clickGoToCart() {	 
		  	int size = goToCartButton.size();		 
			 waitForElementToBeClickable(goToCartButton.get(size-4));
			 goToCartButton.get(size-4).click();
	  }
	 
	 public void clickOnAddToCartButton() {
		    waitForElementToBeClickable(addToCartFromDetailPage);
		    clickElementUsingJavaScriptExecutor(addToCartFromDetailPage);
		  }
	 
	 public boolean verifyAddToCartIsPresent() {
		    return verifyElementIsDisplayed(addToCartFromDetailPage);
		  }
	 
	 public String getNameOfCoffeeBeanz() {
		  waitForElementToBeVisible(beanzNameInCartPage);
		  String name = beanzNameInCartPage.getText();
	    return name;
	  }
	 
	 public boolean verifyGoToCartButton() {
		 int size = goToCartButton.size();	
		 waitForElementToBeClickable(goToCartButton.get(size-4));
	    return verifyElementIsDisplayed(goToCartButton.get(size-4));
	  }


}
