package website.pagefactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

	public SearchPage() {
		PageFactory.initElements(driver, this);
	}

	public int getSearchResultCount() {
		waitForElement(searchResultMsg);
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
		waitForElement(modelIdBOV900);
		hardWait(5000);
		modelIdBOV900.click();
	}

}
