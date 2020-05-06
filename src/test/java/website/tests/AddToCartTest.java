package website.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import website.base.BaseTest;

public class AddToCartTest extends BaseTest {

	SoftAssert softassert = new SoftAssert();

	@Test(priority = 0)
	public void addItemToCart() {
		logger = reporter.createTest("AddToCartTest");
		logger.log(Status.INFO, "<<Add Item to Cart Test Started>>");
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
		logger.log(Status.INFO, "<<Add Item to Cart Test End>>");
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

}
