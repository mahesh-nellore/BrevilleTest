package website.pagefactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import website.base.BaseTest;

public class BeanzHomePage extends BaseTest{
	
	public static String nameOfCoffeeBeanz = "";
	
	@FindBy(xpath = "//body/div[5]/div[1]/div[1]/nav[1]/a[1]/img[1]")
	private WebElement logo;
	
	@FindBy(xpath = "//a[@id='js-my-beanz-sign-up']")
	private WebElement createAccountButton;
	
	@FindBy(xpath = "//a[@id='js-my-beanz-sign-in']")
	private WebElement createAccountLoginButton;
	
	@FindBy(xpath = "//a[contains(@class, 'nav-item nav-link search-defualt') and @title = 'Beanz Search']")
	private WebElement searchIcon;
	
	@FindBy(xpath = "//input[@id='searchbox-input']")
	private WebElement searchTextBox;
	
	@FindBy(xpath = "//a[contains(@class, 'nav-link p-3 ') and @title = 'Shop Coffee']")
	private WebElement shopCoffeeLabel;
	
	@FindBy(xpath = "//a[contains(@class, 'nav-link p-3 ') and @title = 'Our Roasters']")
	private WebElement ourRoasterLabel;
	
	@FindBy(id = "_evidon-accept-button")
	private WebElement evidonAcceptButton;
	
	@FindBy(xpath = "//*[@id='productListSort']//span[contains(text(),'Popular')]")
	private WebElement sortByDropdown;
	
	@FindBy(xpath = "//*[@id='productListSort']//span[contains(text(),'Low to high - Price')]")
	private WebElement lowToHighSortOption;
	
	@FindBy(xpath = "//*[@id='productListSort']//span[contains(text(),'High to low - Price')]")
	private WebElement highToLowSortOption;
	
	@FindAll(@FindBy(xpath = "//p[@class='fromOz']"))
	private List < WebElement > priceElementList;
	
	@FindAll(@FindBy(css = "div[class='goToPage']"))
	private List < WebElement > listOfCoffeeItems;
	
	@FindAll(@FindBy(xpath = ".//*[text()[contains(.,\"Buy Now\")]]"))
	private List < WebElement > listofBuyNow;
	
	@FindBy(xpath = "//button[@id='beanzNewsletterSignUp']")
	private WebElement signUpOnShopCoffee;
	
	  @FindBy(css = "//button[contains(text(),'Reset') and @class = 'btn resetFilter title']")
	  private WebElement resetButton;
	  

	  @FindBy(xpath = "(//img[contains(@src,'tile')])[1]")
	  private WebElement belleEspressoImage;
	  
	  @FindBy(css = "//a[@id='']")
	  private WebElement returnToShopCoffeePage;
	
	public BeanzHomePage() {
		    PageFactory.initElements(driver, this);
		  }
	
	public boolean verifyEvidonAcceptButton() {
	    return verifyElementIsDisplayed(evidonAcceptButton);
	  }
	
	public void acceptEvidonAcceptButton() {
	    try {
	      waitForElementToBeClickable(evidonAcceptButton);
	      clickElementUsingJavaScriptExecutor(evidonAcceptButton);
	      //logger.log(Status.INFO, "clicked on evidon Acept button");
	      hardWait(2000);
	    } catch(Exception e) {
	      // TODO: handle exception
	    }
	  }
	
	 public String getTitle() {
		    String title = driver.getTitle();
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

	 public boolean verifyShopCoffeeLabel() {
		    return verifyWebElement(shopCoffeeLabel);
		  }
	 
	 public void clickOnShopCoffee(String str) {
		    waitForElementToBeClickable(shopCoffeeLabel);
		    shopCoffeeLabel.click();
		  }
	 
	 public void clickOnSortByDropdown() {
		    waitForElementToBeClickable(sortByDropdown);
		    sortByDropdown.click();
		  }
	 
	 public void clickOnLowToHighSortOption() {
		    waitForElementToBeClickable(lowToHighSortOption);
		    lowToHighSortOption.click();
		  }
	 public void clickOnHighToLowSortOption() {
		    waitForElementToBeClickable(highToLowSortOption);
		    highToLowSortOption.click();
		  }
	 
	 public int numberOfCoffeeItems() {
		    waitForElementToBeVisible(signUpOnShopCoffee);
		    return priceElementList.size();
		  //logger.log(Status.INFO,"Searching for the subscription product: " + priceElementList);
		  }
	 
	 public void clickOnKettlesImage(String str) {
		    waitForElementToBeClickable(belleEspressoImage);
		    clickElementUsingJavaScriptExecutor(belleEspressoImage);
		    //logger.log(Status.INFO, "Clicking on the Kettle on the PLP page.");
		  }

	 
	 public void selectItemFromList() {
		    waitForElementToBeVisible(resetButton);
		    hardWait(5000);
		    for (WebElement element: listofBuyNow) {
		      element.click();
		      break;
		    }
		  }
	 
	 
	 
	 public void priceListSort() {
		 ArrayList<Float> priceList = new ArrayList<Float>();
		    for (int i = 0; i<priceElementList.size(); i=i+1) {
		       priceList.add(Float.parseFloat(priceElementList.get(i).getText())); 
		    }  
		    if(!ascendingCheck(priceList)){
		        Assert.fail("Not is ascending order");
		    }
	 }
	 
	 Boolean ascendingCheck(ArrayList<Float> data){         
	        for (int i = 0; i < data.size()-1; i++) {
	            if (data.get(i) > data.get(i+1)) {
	                return false;
	            }       
	         }
	         return true;
	     }
	 
	  public void addKettleToCart() {
		   waitForElementToBeClickable(listOfCoffeeItems.get(0));
		    int totalCoffeeItems = (listOfCoffeeItems.size()) - 1;
		    //logger.log(Status.INFO, "The total number of products displayed in the web page under Kettles are: " + totalKettles);
		    WebElement element = null;
		    int count = 0;
		    while (count < totalCoffeeItems) {
		        try {
		            element = listOfCoffeeItems.get(count);
		            waitForElementToBeClickable(element);
		            clickElementUsingJavaScriptExecutor(element);
		            count++;
		          } catch(Exception e) {
		            //logger.log(Status.INFO, "Exception occured while adding the kettle to the Cart: " + e.getMessage());
		          }
		      boolean flag = addToCartPage.verifyAddToCartIsPresent();
		      if (flag) {
		        //nameOfCoffeeBeanz = addToCartPage.getNameOfCoffeeBeanz();
		        logger.log(Status.INFO, "Add to cart option is available for the product: " + nameOfCoffeeBeanz);
		    	  addToCartPage.clickOnAddToCartButton();
		        boolean isGoToCartButtonDisplayed = addToCartPage.verifyGoToCartButton();
		        if(isGoToCartButtonDisplayed)
		        	break;
		      } else {
		        waitForElementToBeClickable(returnToShopCoffeePage);
		        clickElementUsingJavaScriptExecutor(returnToShopCoffeePage);
		      }
		    }
		 //  return nameOfCoffeeBeanz;

		  }


	
 

}
