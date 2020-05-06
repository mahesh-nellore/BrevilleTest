package website.pagefactory;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import website.base.BaseTest;

public class ProductsPage extends BaseTest {

	@FindAll(@FindBy(xpath = "//div[contains(@class,'container')]//div[contains(@class,'item__details')]//h4"))
	private List<WebElement> listOfOptionsForParticularProduct;

	@FindBy(css = "button[class$='primary js-addToCartBtn']")
	private WebElement addToCartButton;

	@FindBy(xpath = "//a[contains(text(),'Go to cart')]")
	private WebElement goToCartButton;

	@FindBy(css = "h2#productAddedMsgBox__title")
	private WebElement productAddedMsg;

	@FindBy(css = "div#productAddedMsgBox__dialog>div>div>div>button")
	private WebElement closeProductAddedDialogBox;

	@FindBy(css = "button[class$='read-more']")
	private WebElement moreOption;

	public ProductsPage() {
		PageFactory.initElements(driver, this);
	}

	public void selectItemFromList() {
		waitForElement(moreOption);
		hardWait(5000);
		for (WebElement element : listOfOptionsForParticularProduct) {
			System.out.println("Seleced Element>>" + element.getText());
			element.click();
			break;
		}
	}

	public void selectItemAndClickAddToCart() {
		selectItemFromList();
		waitForElement(addToCartButton);
		hardWait(2000);
		addToCartButton.click();

	}

	public String getProductAddedMsgFromDialog() {
		waitForElement(productAddedMsg);
		System.out.println("Product Added Message>>" + productAddedMsg.getText());
		return productAddedMsg.getText();
	}

	public void clickGoToCart() {
		goToCartButton.click();
	}

}
