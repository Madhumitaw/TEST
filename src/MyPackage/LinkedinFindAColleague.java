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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LinkedinFindAColleague {
	private WebDriver driver;
	private String baseUrl;	
	private Properties testData,regdata;
	@Before
    public void setUp() throws FileNotFoundException, IOException{
		testData = new Properties();
        testData.load(new FileInputStream("testdata/credentials.properties"));
        regdata = new Properties();
        regdata.load(new FileInputStream("testdata/regdata.properties"));		
		System.setProperty("webdriver.gecko.driver", "C://Users//madhumita//Downloads//geckodriver//geckodriver.exe");
        driver = new FirefoxDriver();
        
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
     
        baseUrl = "https://www.linkedin.com";
    }
	/* Verification of the Find A Colleague functionality using the data driven approach.
     * The search data is present in the regdata.properties file
     
	 * Script written by:Madhumita
	 * Date:11/07/2016
	*/
	
	@Test
    public void testFindAColleague() {
        
		int noOfTests = Integer.parseInt(regdata.getProperty("noOffindTest"));
		while(noOfTests > 0){
			driver.get(baseUrl);
			WebElement btn=driver.findElement(By.className("submit-btn"));
			System.out.println("Button enabled::"+btn.isEnabled());
			String fname=regdata.getProperty("f"+noOfTests);
			String lname=regdata.getProperty("l"+noOfTests);
			driver.findElement(By.name("first")).sendKeys(fname);
			driver.findElement(By.name("last")).sendKeys(lname);
			System.out.println("Button enabled::"+btn.isEnabled());

			try {
				JavascriptExecutor js = (JavascriptExecutor) driver; 
        		js.executeScript("arguments[0].click();",btn );
        	
        		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        		driver.navigate().back();
				
				 
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("Error"+e1);
			}
			
			driver.navigate().back();
			
			noOfTests--;
		}
                
    } 
	
	@After
    public void closureActivities(){
        driver.quit();
    }
}
