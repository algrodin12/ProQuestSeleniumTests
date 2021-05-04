package tests;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;		

import pages.GooglePage;
import pages.GMPage;

public class GoogleTest {		
	    WebDriver driver;
	    GooglePage objGoogleSearch;
	    GMPage objGM;

		@BeforeMethod
		public void beforeTest() 
		{	
	        driver = new ChromeDriver();
		}		
		
		/**
	     * Navigate to https://www.google.com
	     * Verify Google Home Page & Logo
	     * Search for 'General Motors'
	     * Loop through 1st page of results to find https://www.gm.com
	     * Click on https://www.gm.com
	     * Verify GM Home Page & Logo
	     */
		@Test				
		public void googleTest()
		{	
			//Create Google Page object
			objGoogleSearch = new GooglePage(driver);
	
			//Navigate to Google
	        driver.get("https://www.google.com");

	        //Wait for Google Page to Load
	        objGoogleSearch.waitForPageToLoadByXpath("//form[@action='/search']");
	        
	        //Verify Google Home Page
	        objGoogleSearch.verifyGoogleHomePage();
	        
		    //Set input value for Search & Enter
		    objGoogleSearch.setInputValue("General Motors");

		    //Wait for results to load
	        objGoogleSearch.waitForPageToLoadById("rcnt");
	        
	        //loop through results on first page for www.GM.com and click it
	        objGoogleSearch.selectGoogleResult("https://www.gm.com"); 
	        
	        //Create GM Page object
	        objGM = new GMPage(driver);
			
	        //Wait for GM Page to Load
	        objGM.waitForPageToLoad("main");
	        
	        //Verify GM Logo
	        objGM.verifyGMHomePageLogo();
		}	
		
		@AfterMethod
		public void afterTest() 
		{
			driver.quit();			
		}		
}