package webcalculator.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import webcalculator.pageobjects.CalculatorHomePage;

public class BaseTest {

	public WebDriver driver;
	public CalculatorHomePage homePage;
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop=new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\webcalculator\\resources\\GlobalConfiguration.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null? System.getProperty("browser"): prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\Jars\\geckodriver.exe");
			driver = new FirefoxDriver();
		
		}
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    	driver.manage().window().maximize();
    	return driver;
	}
	
	@BeforeClass
	public CalculatorHomePage launchApplication() throws IOException {
		driver = initializeDriver();
		homePage = new CalculatorHomePage(driver);
		homePage.goTo();
		return homePage;
	}
	
	/*
	 * Clears the result before each test	
	 */
	@BeforeMethod
	public void clearScreen() {
		homePage.clearButton();
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
	/*
	 * Capture Screenshot
	 */
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
		// TODO Auto-generated method stub
		TakesScreenshot ts = (TakesScreenshot)driver;
		File fs = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"\\reports"+testcaseName +".png");
		FileUtils.copyFile(fs, file);
		return System.getProperty("user.dir")+"\\reports"+testcaseName +".png";
		
	}
}
