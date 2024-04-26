package webcalculator.pageobjects;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import webcalculator.abstractcomponents.AbstractComponents;

public class CalculatorHomePage extends AbstractComponents {
	
	WebDriver driver;
	
	public CalculatorHomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="Btn0")
	WebElement digitZero;
	
	@FindBy(id="Btn1")
	WebElement digitOne;
	
	@FindBy(id="Btn2")
	WebElement digitTwo;
	
	@FindBy(id="Btn3")
	WebElement digitThree;
	
	@FindBy(id="Btn5")
	WebElement digitFive;
	
	@FindBy(id="Btn8")
	WebElement digitEight;
	
	@FindBy(id="Btn10")
	WebElement digitTen;
	
	@FindBy(id="BtnSin")
	WebElement calculateSine;
	
	@FindBy(id="BtnParanR")
	WebElement rightParanthesis;
	
	@FindBy(id="BtnParanL")
	WebElement leftParanthesis;
	
	@FindBy(id="input")
	WebElement result;
	
	@FindBy(id="BtnCalc")
	WebElement calculate;
	
	@FindBy(id="BtnClear")
	WebElement clearButton;
	
	@FindBy(id="BtnMult")
	WebElement multiplicationButton;
	
	@FindBy(id="BtnDiv")
	WebElement divisionButton;
	
	@FindBy(id="BtnPlus")
	WebElement addButton;
	
	@FindBy(id="BtnMinus")
	WebElement minusButton;
	
	@FindBy(id="histframe")
	WebElement historyFrame;
	
	@FindBy(css="div[class='history btn-group']")
	WebElement webCalculatorHistory;
	
	@FindBy(xpath="//div[@id='histframe']/ul/li")
	List<WebElement> calculatorHistoryList;
	
	/*
	 * Navigates to calculator home page
	 */
	public void goTo() {
		driver.get("https://web2.0calc.com/");
	}
	
	public void click(WebElement element) {
		element.click();
	}
	
	public String returnResult() throws InterruptedException {
		
		Thread.sleep(4000);
		return result.getAttribute("value");
	}

	public void clearButton() {
		clearButton.click();
	}
	
	public void calculateResult(String equation) {
		// TODO Auto-generated method stub
		char[] ch =equation.toCharArray();
		int i=0;
		while(i<ch.length) {
		
			switch(ch[i]) {
			
				case '0':
				digitZero.click();
				break;
				
				case '1':
				digitOne.click();
				break;
				
				case '2':
					digitTwo.click();
					break;
					
				case '3':
					digitThree.click();
					break;
					
				case '5':
					digitFive.click();
					break;
					
				case '8':
					digitEight.click();
					break;
					
				case '+':
					addButton.click();
					break;
				
				case '-':
					minusButton.click();
					break;
					
				case '*':
					multiplicationButton.click();
					break;
					
				case '/':
					divisionButton.click();
					break;
					
				case '(':
					leftParanthesis.click();
					break;
					
				case ')':
					rightParanthesis.click();
					break;
				case 's':
					calculateSine.click();
					i=i+3;
					break;
			}
				
			i++;
		}
		calculate.click();
	}

	public void openWebCalculatorHistory() {
		webCalculatorHistory.click();
	}
	
	public void returnWebCalculatorHistory(Map<String,String> input) {
		if(driver.findElement(By.cssSelector("div[class='history btn-group open']")).isDisplayed()){
			scrollDown();
    	String equation,output;
    	for(WebElement e:calculatorHistoryList){
    		equation = e.findElement(By.className("l")).getText();
    		output = e.findElement(By.className("r")).getText().substring(2);
    		if(input.containsKey(e.findElement(By.className("l")).getText()))
					Assert.assertEquals(output, input.get(e.findElement(By.className("l")).getText()));
    	}
    	
	}
	}
	
	
}
