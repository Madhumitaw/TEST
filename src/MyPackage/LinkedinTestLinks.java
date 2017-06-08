package MyPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LinkedinTestLinks {
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
	/*
	 * Objects:Test all links on the home page:
	 * Script written by:Madhumita
 * Date:11/08/2016
	 */
	
	@Test
    public void testlinks() throws InterruptedException{
    	driver.get(baseUrl);
    	List<WebElement> links = driver.findElements(By.tagName("a"));//store the links in List
       
        ArrayList<String> linkNames = new ArrayList<String>();
        for(WebElement linkElement : links){
        	if(linkElement.getText().length() > 0)
        		linkNames.add(linkElement.getText());//add the links to Array List
        }               
        int i=0;

        for(String linkName : linkNames){   
        	i=i+1;
        	try{
        		JavascriptExecutor js = (JavascriptExecutor) driver; 
            	WebElement currentElement = driver.findElement(By.linkText(linkName));
            	((JavascriptExecutor) driver).executeScript("arguments[0].click();", currentElement);//Click on the link
            	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            	System.out.println(i+":Title of page: "+linkName);
            	driver.navigate().back();//Navigate back to base URL
            	}
        	
        	catch(NoSuchElementException e){
        		//e.printStackTrace();
        		System.out.println(linkName + " generated exception:"+e);
        	}

        }          
       }
	@After
    public void closureActivities(){
        driver.quit();
    }

}
