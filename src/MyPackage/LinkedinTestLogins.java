package MyPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
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

public class LinkedinTestLogins {
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
	@Test
    public void testLogins() throws InterruptedException{
        
		int noOfTests = Integer.parseInt(testData.getProperty("noOfTests"));
		while(noOfTests > 0){
			driver.get(baseUrl);
			driver.findElement(By.id("login-email")).sendKeys(testData.getProperty("un"+noOfTests));
			driver.findElement(By.id("login-password")).sendKeys(testData.getProperty("pwd"+noOfTests));
			
			try {
				WebElement sub=driver.findElement(By.id("login-submit"));
				JavascriptExecutor js = (JavascriptExecutor) driver;    
				js.executeScript("arguments[0].click();", sub);
				Thread.sleep(1000);
				String loginfailed="https://www.linkedin.com/uas/login-submit";
			    String currenturl= driver.getCurrentUrl();
				System.out.println("Title : "+currenturl);
				if (loginfailed.equals(currenturl))
					System.out.println("Invalid Credentails : "+noOfTests+" Username="+testData.getProperty("un"+noOfTests)+" Password="+testData.getProperty("pwd"+noOfTests));
				else{
					System.out.println("Valid Credentials : "+noOfTests+" Username="+testData.getProperty("un"+noOfTests)+" Password="+testData.getProperty("pwd"+noOfTests));
						List<WebElement> links =driver.findElements(By.tagName("a"));
						
						int count=0;
						
						for(WebElement link : links){ 
				        	String check="account-settings";
				        	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
						    if (count==0){
				        		if (link.getAttribute("class").contains(check))
				        		System.out.println("Setting"+link.getText());
				        			if(link.getAttribute("class").equals("account-submenu-split-link"))
				        				{
				        					//JavascriptExecutor js = (JavascriptExecutor) driver; 
				        				
				        					js.executeScript("arguments[0].click();", link);
				        					driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				        					driver.navigate().back();
				        					driver.navigate().refresh();
				        					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				        					count=count+1;
				        				}
				        				}
						}
				}
				 
			//} catch (InterruptedException e1) {
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
