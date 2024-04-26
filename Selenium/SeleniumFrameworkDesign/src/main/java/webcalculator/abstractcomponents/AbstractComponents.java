package webcalculator.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {

	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
	}
	
	public void waitForElementToAppear(By FindBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
		
	}
	
	public void scrollDown() {
		JavascriptExecutor jse = ((JavascriptExecutor)driver);
    	jse.executeScript("window.scrollTo(0,250)");
	}

}
