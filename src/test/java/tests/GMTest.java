package tests;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;		

import pages.GMPage;

public class GMTest {		
	    WebDriver driver;
	    GMPage objGM;
	    
		@BeforeMethod
		public void beforeTest() 
		{	
	        driver = new ChromeDriver();
		}		
		
		/**
	     * Navigate to https://www.gm.com/
	     * Verify GM Home Page & Logo
	     * Look through nav bar to select CAREERS
	     * Go to Career Page
	     * Search for Jobs
	     * Verify Job Opening Results
	     */
		@Test				
		public void GMTest()
		{	
			//Create GM Page object
			objGM = new GMPage(driver);
	
			//Navigate to GM
	        driver.get("https://www.gm.com/");

	        //Wait for GM Page to Load
	        objGM.waitForPageToLoad("main");
	        
	        //Verify GM Logo
	        objGM.verifyGMHomePageLogo();
	        
	        //Navigate to Careers Page
	        objGM.navigateToCareerPage();

	        //Wait for GM Page to Load
	        objGM.waitForPageToLoadByClass("home-page");    
	        
	        //Verify Career Page
	        objGM.verifyGMCareerPage();

	        //Scroll into view
			objGM.scrollToElementByClass("ph-widget");

			//Select See New Opportunities
			driver.findElement(By.linkText("See career opportunities")).click();
	        	
			//Wait for GM Page to Load
			objGM.waitForPageToLoadByClass("ph-page"); 

	        //Scroll into view
			objGM.scrollToElementByClass("phs-widget-block-area");

			//Select Engineering
			driver.findElement(By.linkText("Engineering")).click();

			//Wait for GM Page to Load
	        objGM.waitForPageToLoadByClass("ph-page-container");   
	        
	        //Scroll into view
	        objGM.scrollToElement("sub_search_textbox");
	        
	        //Set input value in search
			objGM.setInputValue("selenium");
	        
	        //Verify job opening for Software Test Engineer
			objGM.verifyAndClickOnJobOpening("Software Test Engineer");
	        
	        //Wait for GM Page to Load
	        objGM.waitForPageToLoadByClass("phs-job-details-area");    
		}	
		
		@AfterMethod
		public void afterTest() 
		{
			driver.quit();			
		}		
}