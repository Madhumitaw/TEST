package MyPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LinkedinTestAgreement {
	private WebDriver driver;
	private String baseUrl;	
	
	@Before
    public void setUp() throws FileNotFoundException, IOException{
		System.setProperty("webdriver.gecko.driver", "C://Users//madhumita//Downloads//geckodriver//geckodriver.exe");
        driver = new FirefoxDriver();
        
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
     
        baseUrl = "https://www.linkedin.com";
        
        
        
    }
	/*String manipulation :Verify the agreement text is present above the registration button
	 * Script written by:Madhumita
	 * Date:11/11/2016
	 */
	@Test
    public void testAgreementText(){
    	driver.get(baseUrl);
    	WebElement agg = driver.findElement(By.className("agreement"));//store the links in List
    	String aggtext="By clicking Join now, you agree to the LinkedIn User Agreement, Privacy Policy, and Cookie Policy.";
    	if(agg.getText().equals(aggtext))
       System.out.println("Agreement text is displayed while registration");
    	else
    		System.out.println("Agreement text is not displayed while registration");
       }
	
	@After
    public void closureActivities(){
        driver.quit();
    }
}
