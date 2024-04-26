package webcalculator.tests;

import java.util.LinkedHashMap;
import java.util.Map;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.*;
import webcalculator.testcomponents.BaseTest;

public class CalculatorTest extends BaseTest {	
	
	/*
	 * TestCase1
	 * Verify 2+3=5
	 */
	@Test(priority=4,dataProvider = "getData")
	public void TestScenario1(Map<String,String> input) throws InterruptedException {
		homePage.calculateResult("2+3");
		Assert.assertEquals(homePage.returnResult(), input.get("2+3"),"TestScenario1 Assert successful");
		
	}

	/*
	 * TestCase2
	 * Verify 10-2=8
	 */
	@Test(dependsOnMethods = {"TestScenario1"})
	public void TestScenario2() throws InterruptedException {
		homePage.calculateResult("10-2");
		Assert.assertEquals(homePage.returnResult(), "8","TestScenario2 Assert successful");
  	
	}
	
	/*
	 * TestCase3
	 * (10-2)*2 != 20
	 */
	@Test(dependsOnMethods = {"TestScenario2"})
	public void TestScenario3() throws InterruptedException {
		homePage.calculateResult("(10-2)*2");
		Assert.assertNotEquals(homePage.returnResult(), "20","TestScenario3 assert successful");
		
	}
	
	/*
	 * TestCase4
	 * sin(30)=0.5
	 */
	@Test(dependsOnMethods = {"TestScenario3"})
	public void TestScenario4() throws InterruptedException{
		homePage.calculateResult("sin(30)");
		Assert.assertEquals(homePage.returnResult(), "0.5","TestScenario4 assert successful");
	}
	
	@Test(dependsOnMethods = {"TestScenario4"},dataProvider = "getData")
	public void calculatorHistoryTest(Map<String,String> input) {
		homePage.openWebCalculatorHistory();
		homePage.returnWebCalculatorHistory(input);
	}
	
	
	/*
	 * TestData for verifying order history
	 */
	@DataProvider
	public Object[][] getData(){
		 Map<String, String> hm = new LinkedHashMap<String, String>(); 

	     hm.put("sin(30)", "0.5"); 
	     hm.put("(10-2)*2", "16"); 
	     hm.put("10-2", "8"); 
	     hm.put("2+3", "5");
	     
	     return new Object[][] {{hm}};
	}
	
	

}
