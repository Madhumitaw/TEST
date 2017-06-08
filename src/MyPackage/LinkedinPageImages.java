package MyPackage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LinkedinPageImages {

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
	 * Objects:Check all the images are present
	 * and verify if the images are displayed when the page is loaded
	 * Script written by:Madhumita
	 * Date:11/08/2016
	  */
		@Test
	    public void testImages(){
	    	driver.get(baseUrl);
	        List<WebElement> imagecheck = driver.findElements(By.cssSelector("[data-delayed-url *='http']"));
	        int i=0;
	        for(WebElement imageElement : imagecheck){ 
	        	i++;
	        	String altValue = imageElement.getAttribute("alt"); 
	           	System.out.println(i+": "+imageElement.getAttribute("data-delayed-url"));
	        	System.out.println("This image is displayed on Page Load:"+imageElement.isDisplayed());
	        	if(altValue != null && !altValue.equals("")){
	        	
	        		System.out.println("Alt found as : "+ altValue+"\n"+"-------"+"\n");
	        	}
	        	else{
	        		System.out.println("Alt Text is missing"+"\n"+"-------");
	        		
	        	}
	        }                              
	       }
	
		@After
	    public void closureActivities(){
	        driver.quit();
	    }

    
}