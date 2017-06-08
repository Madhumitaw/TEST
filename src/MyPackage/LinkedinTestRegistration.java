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
import org.openqa.selenium.firefox.FirefoxDriver;

public class LinkedinTestRegistration {
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
	/*
	 * testRegistration:
	 * tests the valid inputs for which user can register successfully.
	 * Using the data driven approach the input data is read from the regdata.properties file 
	 * For all invalid credentials the error displayed on the screen is captured and string manipulation is done to ensure correct 
	 * error was displayed
	 * Script written by:Madhumita
	 * Date:11/07/2016
	 * */
	@Test
    public void testRegistration() throws InterruptedException{
        
		int noOfTests = Integer.parseInt(regdata.getProperty("noOfRegTests"));//set the number of reg test cases
		while(noOfTests > 0){//programmatic approach
			driver.get(baseUrl);
			driver.findElement(By.id("reg-firstname")).sendKeys(regdata.getProperty("fn"+noOfTests));//enter first name
			driver.findElement(By.id("reg-lastname")).sendKeys(regdata.getProperty("ln"+noOfTests));//enter last name
			driver.findElement(By.id("reg-email")).sendKeys(regdata.getProperty("e"+noOfTests));//enter email
			driver.findElement(By.id("reg-password")).sendKeys(regdata.getProperty("p"+noOfTests));//enter password
			
			try {
				driver.findElement(By.id("registration-submit")).click();
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
				String msg=driver.findElement(By.className("alert-content")).getText();
				String regfailed="Please enter";
				Boolean valid=msg.isEmpty();
				if (valid)
				System.out.println("Valid Registration Test case No:"+noOfTests);
				else{
					if(msg.contains(regfailed))
					{ if (msg.contains("first name"))
						System.out.println("First Name is Blank (Test case No):"+noOfTests);
						else if(msg.contains("last name"))
						System.out.println("Last Name is Blank (Test case No):"+noOfTests);
						else if(msg.contains("email"))
						System.out.println("Email Id is invalid (Test case No):"+noOfTests);
						else if(msg.contains("password"))
						System.out.println("Password is invalid (Test case No):"+noOfTests);
				}
				else if (msg.contains("Password must be 6 characters or more"))
					System.out.println("Length of password is less than 6 (Test case No):"+noOfTests);
				
				}
				
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
