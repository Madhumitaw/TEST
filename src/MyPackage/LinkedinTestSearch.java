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

public class LinkedinTestSearch {
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
	/* Verification of the search functionality using the data driven approach.
     * The search data is present in the credentials.properties file
     
	 * Script written by:Madhumita
	 * Date:11/11/2016
	*/
	@Test
	 public void testsearch() throws InterruptedException{
	        
			int noOfSearch = Integer.parseInt(testData.getProperty("noOfSTests"));
			String check="account-settings";
				driver.get(baseUrl);
				driver.findElement(By.id("login-email")).sendKeys(testData.getProperty("us"+1));
				driver.findElement(By.id("login-password")).sendKeys(testData.getProperty("ps"+1));
				
				try {
					driver.findElement(By.id("login-submit")).click();
					Thread.sleep(1000);
					while (noOfSearch>0)
					{
					
					WebElement search =driver.findElement(By.id("main-search-box"));
					search.clear();
					search.sendKeys(testData.getProperty("s"+noOfSearch));
					WebElement btn =driver.findElement(By.name("search"));
					btn.click();
					
					driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
					WebElement val=driver.findElement(By.id("results_count"));
					String res=val.getText();
					String[] parts = res.split("result");
					System.out.println("Count::"+res+"--"+"\n");
					if(parts[0].equals("0"))
						System.out.println("Invalid search");
					
			
				noOfSearch--;
					}
					
					 
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("Error"+e1);
				}
				
				driver.navigate().back();
				
			
	                
	    }
	
	@After
    public void closureActivities(){
        driver.quit();
    }
}
