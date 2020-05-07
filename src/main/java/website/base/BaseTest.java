package website.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import website.pagefactory.HomePage;
import website.pagefactory.ProductsPage;
import website.pagefactory.SearchPage;
import website.pagefactory.TransactionPage;

public class BaseTest {
	public static ExtentReports reporter;
	public static ExtentTest logger;
	public static WebDriver driver;
	public static Properties webprop;
	public static HomePage homepage = null;
	public static SearchPage searchpage = null;
	public static ProductsPage productspage = null;
	public static TransactionPage transactionpage = null;

	@BeforeSuite
	public static void initBaseConfig() {
		ExtentHtmlReporter extreporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + File.separator + "Reports" + File.separator + "websiteDemo.html");
		reporter = new ExtentReports();
		reporter.attachReporter(extreporter);
		logger = reporter.createTest("Base Test");
		webprop = new Properties();
		try {
			webprop.load(new FileInputStream(System.getProperty("user.dir") + File.separator + "testdata"
					+ File.separator + "website.properties"));
			System.out.println(webprop.getProperty("URL"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@BeforeTest
	@Parameters({ "browser" })
	public static void setUp(String browser) {
		// initBaseConfig();
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			logger.log(Status.INFO, "Launch Chrome Browser");
		} else {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			logger.log(Status.INFO, "Launch Firefox Browser");
		}
		driver.manage().window().maximize();
		logger.log(Status.INFO, "Maximized the Window");
		driver.get(webprop.getProperty("URL"));
		logger.log(Status.INFO, "Base URL>>" + webprop.getProperty("URL"));
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		System.out.println(driver);
		homepage = new HomePage();
		searchpage = new SearchPage();
		productspage = new ProductsPage();
		transactionpage = new TransactionPage();

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	public void scrollToViewTillElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}

	public void waitForElement(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			System.out.println("Exception>>" + e.getMessage());
			logger.log(Status.INFO, "Exception Wait for Element>>" + e.getMessage());
		}
	}

	public String getScreenShot() {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator
				+ System.currentTimeMillis() + ".png";
		System.out.println(path);
		File des = new File(path);
		try {
			FileUtils.copyFile(src, des);
		} catch (IOException e) {
			logger.log(Status.INFO, "Exception Wait for Element>>" + e.getMessage());
		}
		return path;

	}

	public void hardWait(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void waitForTheElementToVisible(WebElement element) {
		int count = 0;
		boolean result = false;
		while (count < 2) {
			try {
				hardWait(5000);
				result = element.isDisplayed();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			count++;
			if (result)
				break;
		}
	}

	@AfterMethod
	public void teardown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			String path = getScreenShot();
			try {
				logger.fail(result.getThrowable().getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		reporter.flush();

	}

}
