package website.tests;

import java.io.File;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import website.base.BaseTest;
import website.generic.ExcelUtility;

public class AddToCartTest extends BaseTest {

	SoftAssert softassert = new SoftAssert();

	@Test(priority = 0)
	public void addItemToCart() {
		logger = reporter.createTest("AddToCartTest");
		logger.log(Status.INFO, "Add Item to Cart Test Started>>");
		homepage.clickOnProducts();
		homepage.selectOvensFromProductList();
		productspage.selectItemAndClickAddToCart();
		String productAddedMsg = productspage.getProductAddedMsgFromDialog();
		logger.log(Status.INFO, "Product Added Message>>" + productAddedMsg);
		softassert.assertEquals(productAddedMsg, webprop.getProperty("productaddedmsg"));
		productspage.clickGoToCart();
		logger.log(Status.INFO, "Verified Checkout As Guest Status>>" + transactionpage.verifyCheckoutAsGuest());
		softassert.assertTrue(transactionpage.verifyCheckoutAsGuest());
		String subTotal = transactionpage.getSubTotal();
		logger.log(Status.INFO, "Sub Total >>" + subTotal);
		softassert.assertTrue(subTotal != null);
		logger.log(Status.INFO, "Add Item to Cart Test End>>");
		softassert.assertAll();
	}

	@Test(priority = 1)
	public void removeTheItemFromCart() {
		logger.log(Status.INFO, "<<Remove Item to Cart Test Started>>");
		transactionpage.removeProduct();
		String msg = transactionpage.getCartEmptyMsg();
		logger.log(Status.INFO, "Message After removing the Item from cart>>" + msg);
		softassert.assertEquals(msg, webprop.getProperty("productremovemsg"));
		softassert.assertAll();
		logger.log(Status.INFO, "<<Remove Item to Cart Test End>>");

	}

	@Test(priority = 2)
	public void completeTillPayment() {
		String path = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "websiteTestData.xlsx";
		int sheetIndex = 1;
		String email = ExcelUtility.getCellValue(path, sheetIndex, 1, 0);
		String firstname = ExcelUtility.getCellValue(path, sheetIndex, 2, 0);
		String lastname = ExcelUtility.getCellValue(path, sheetIndex, 3, 0);
		String address1 = ExcelUtility.getCellValue(path, sheetIndex, 4, 0);
		String address2 = ExcelUtility.getCellValue(path, sheetIndex, 5, 0);
		String city = ExcelUtility.getCellValue(path, sheetIndex, 6, 0);
		String zipcode = ExcelUtility.getCellValue(path, sheetIndex, 7, 0);
		String statevalue = ExcelUtility.getCellValue(path, sheetIndex, 8, 0);
		String phone = ExcelUtility.getCellValue(path, sheetIndex, 9, 0);
		String form[] = { email, firstname, lastname, address1, address2, city, zipcode, statevalue, phone };
		String searchKey = webprop.getProperty("search");
		logger.log(Status.INFO, "<<Remove Item to Cart Test Started>>");
		homepage.clickOnSearch();
		System.out.println("Search Value>>" + searchKey);
		logger.log(Status.INFO, "Search Value is >>" + searchKey);
		homepage.searchForProduct(searchKey);
		searchpage.selectModelIdBOV900();
		productspage.clickOnAddToCartButton();
		productspage.clickGoToCart();
		transactionpage.clickCheckoutAsGuestButton();
		transactionpage.fillTheForm(form);

	}

}
