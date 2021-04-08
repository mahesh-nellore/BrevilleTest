package website.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import website.pagefactory.BeanzAddToCartPage;
import website.pagefactory.BeanzHomePage;
import website.pagefactory.BeanzTransactionPage;
import website.pagefactory.HomePage;
import website.pagefactory.MyBreville;
import website.pagefactory.ProductsPage;
import website.pagefactory.SearchPage;
import website.pagefactory.TransactionPage;

public class BaseTest {
  public static ExtentReports reporter;
  public static ExtentTest logger;
  public static WebDriver driver;
  public static Properties webprop;
  public static Properties prodprop;
  public static HomePage homepage = null;
  public static BeanzHomePage beanzHomepage = null;
  public static SearchPage searchpage = null;
  public static ProductsPage productspage = null;
  public static BeanzAddToCartPage addToCartPage = null;
  public static TransactionPage transactionpage = null;
  public static BeanzTransactionPage beanzTransactionpage = null;
  public static MyBreville mybreville = null;
  public static String parentWindow;
  public static boolean isNewsLetterPopUpDisplayed = false;
  //public static String email;
  public static String email_ca;
  public static String email_us;
  public static String email_uk;

  

@BeforeSuite
  public static void initBaseConfig() {
    ExtentHtmlReporter extreporter = new ExtentHtmlReporter(
    System.getProperty("user.dir") + File.separator + "Reports" + File.separator + "websiteDemo.html");
    reporter = new ExtentReports();
    reporter.attachReporter(extreporter);
    logger = reporter.createTest("Base Test");
    webprop = new Properties();
    prodprop = new Properties();
    try {
      webprop.load(new FileInputStream(System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "website.properties"));
      prodprop.load(new FileInputStream(System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "prod.properties"));
      //logger.log(Status.INFO, webprop.getProperty("URL"));
    } catch(FileNotFoundException e) {

} catch(IOException e) {

}

  }

  @BeforeMethod@Parameters({
    "browser",
    "env",
    "project"
  })
  public static void setUp(String browser, String env, String project) {
    if (browser.equalsIgnoreCase("chrome")) {
      WebDriverManager.chromedriver().version("89.0").setup();
      ChromeOptions options = new ChromeOptions();
      options.addArguments("ignore-certificate-errors");
      options.addArguments("start-maximized");
      options.addArguments("enable-automation");      
      options.addArguments("--disable-infobars");
      options.addArguments("--disable-dev-shm-usage");
      options.addArguments("--disable-browser-side-navigation");
      options.addArguments("--disable-gpu");
      //options.addArguments("--headless");
      options.setPageLoadStrategy(PageLoadStrategy.EAGER);
      options.addArguments("--disable-gpu");
      options.addArguments("disable-features=NetworkService");
      options.addArguments("--force-device-scale-factor=1");
      driver = new ChromeDriver(options);
      //logger.log(Status.INFO, "Launch Chrome Browser");
    } else {
      WebDriverManager.firefoxdriver().setup();
      driver = new FirefoxDriver();
      //logger.log(Status.INFO, "Launch Firefox Browser");
    }
    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();
    driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    //logger.log(Status.INFO, "Maximized the Window");
    String url = "";
    if (env.equalsIgnoreCase("prod")) url = prodprop.getProperty("URL");
    else if (project.equalsIgnoreCase("mp")) url = webprop.getProperty("BeanzURL");
    else url = webprop.getProperty("URL");

    //logger.log(Status.INFO, "Navigate to the URL: " + url);
    driver.get(url);
    beanzHomepage = new BeanzHomePage();
    addToCartPage = new BeanzAddToCartPage();
    homepage = new HomePage();
    searchpage = new SearchPage();
    productspage = new ProductsPage();
    transactionpage = new TransactionPage();
    beanzTransactionpage = new BeanzTransactionPage();
    mybreville = new MyBreville();
  }

  @AfterTest
  public void tearDown() {
    driver.quit();
  }

  public void scrollToViewTillElement(WebElement element) { ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
  }

  public void waitForElementToBeVisible(WebElement element) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, 30);
      wait.until(ExpectedConditions.visibilityOf(element));
    } catch(Exception e) {
      //logger.log(Status.INFO, "Seems like the element is not displayed in GUI and find the exact error message in the below line...." + element);
      //logger.log(Status.INFO, "Exception Wait for Element: " + e.getMessage());
    }
  }

  public void waitForElementToBeVisible(WebElement element, long seconds) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, seconds);
      wait.until(ExpectedConditions.visibilityOf(element));
    } catch(Exception e) {
      //logger.log(Status.INFO, "Seems like the element is not displayed in GUI and find the exact error message in the below line...." + element);
      //logger.log(Status.INFO, "Exception Wait for Element: " + e.getMessage());
    }
  }

  public void waitForElementToBeClickable(WebElement element) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, 60);
      wait.until(ExpectedConditions.elementToBeClickable(element));
    } catch(Exception e) {
      //logger.log(Status.INFO, "Seems like the element is not displayed in GUI and find the exact error message in the below line...." + element);
      //logger.log(Status.INFO, "Exception Wait for Element>>" + e.getMessage());
    }
  }
  public void waitForElementsToBeVisible(List<WebElement> element) {
	    try {
	      WebDriverWait wait = new WebDriverWait(driver, 60);
	      wait.until(ExpectedConditions.visibilityOfAllElements(element));
	    } catch(Exception e) {
	      //logger.log(Status.INFO, "Seems like the element is not displayed in GUI and find the exact error message in the below line...." + element);
	      //logger.log(Status.INFO, "Exception Wait for Element>>" + e.getMessage());
	    }
	  }
  /*public void waitForElementToBeDisplayed(WebElement element) {
	    try {
	      WebDriverWait wait = new WebDriverWait(driver, 30);
	      wait.until(ExpectedConditions.visibilityOf(element));
	    } catch(Exception e) {
	      //logger.log(Status.INFO, "Seems like the element is not displayed in GUI and find the exact error message in the below line...." + element);
	      //logger.log(Status.INFO, "Exception Wait for Element>>" + e.getMessage());
	    }
	  }*/

  public String getScreenShot() {
    TakesScreenshot ts = (TakesScreenshot) driver;
    File src = ts.getScreenshotAs(OutputType.FILE);
    String path = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + System.currentTimeMillis() + ".png";
    System.out.println(path);
    File des = new File(path);
    try {
      FileUtils.copyFile(src, des);
    } catch(IOException e) {
      //logger.log(Status.INFO, "Exception Wait for Element>>" + e.getMessage());
    }
    return path;

  }

  public void hardWait(long time) {
    try {
      Thread.sleep(time);
      //logger.log(Status.INFO, "Waiting for few seconds to load the page completly...");
    } catch(InterruptedException e) {

}
  }

  public void switchToFrame(WebElement element) {
    waitForElementToBeVisible(element);
    driver.switchTo().frame(element);
    hardWait(1000);
  }

  public void switchToParentFrame() {
    driver.switchTo().defaultContent();
    hardWait(1000);
  }

  public void waitForTheElementToVisible(WebElement element) {
    int count = 0;
    boolean result = false;
    while (count < 2) {
      try {
        hardWait(5000);
        result = element.isDisplayed();
      } catch(Exception e) {
        System.out.println(e.getMessage());
      }
      count++;
      if (result) break;
    }
  }

  public boolean verifyElementVisibility(WebElement element) {
    int count = 0;
    while (count < 2) {
      hardWait(5000);
      try {
        if (element != null) {
          //logger.log(Status.INFO, "The Element is present in the DOM: " + element);
          hardWait(2000);
          if (element.isDisplayed()) {
            //logger.log(Status.INFO, "The Element is Displayed on the Page: " + element);
            return true;
          }
        }

      } catch(Exception e) {
        //logger.log(Status.INFO, "Exception caught while waiting for the Element: " + e.getMessage());
      }
      count++;

    }
    return false;

  }

  public boolean isElementPresent(WebElement element) {
    try {
      waitForElementToBeVisible(element);
      //logger.log(Status.INFO, "Waiting for 10 seconds to load the page");
      if (element.isDisplayed()) return true;
    } catch(Exception e) {
      //logger.log(Status.INFO, "Error occured while verifying the element is present on the Page" + e.getMessage());
    }
    return false;

  }

  public void clickElementUsingJavaScriptExecutor(WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].click()", element);
  }

  public void switchToLatestWindow() {
    Set < String > allwindows = driver.getWindowHandles();
    for (String string: allwindows) {
      //logger.log(Status.INFO, driver.getWindowHandle());
      driver.switchTo().window(string);
    }
  }

  public boolean verifyElementIsDisplayed(WebElement element) {
	  waitForElementToBeVisible(element);
    try {
      return element.isDisplayed();

    } catch(Exception e) {
      //logger.log(Status.INFO, "exception occurred while validating the webelement and the error message in the below line: " + element);
    }
    return false;
  }

  public void clickTabAndEnter() {
    Actions action = new Actions(driver);
    action.sendKeys(Keys.TAB).build().perform();
    action.release().perform();
    action.sendKeys(Keys.ENTER).build().perform();
    action.release().perform();

  }

  @AfterMethod
  public void teardown(ITestResult result) {
    if (result.getStatus() == ITestResult.FAILURE) {
      String path = getScreenShot();
      try {
        logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());

      } catch(IOException e) {

}
    }
    reporter.flush();
    driver.quit();
  }

}