package tests;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;		

import pages.GooglePage;
import pages.ProQuestPage;

public class GoogleTest {		
	    WebDriver driver;
	    GooglePage objGoogleSearch;
	    ProQuestPage objProQuest;

		@BeforeMethod
		public void beforeTest() 
		{	
	        driver = new ChromeDriver();
		}		
		
		/**
	     * Navigate to https://www.google.com
	     * Verify Google Home Page & Logo
	     * Search for 'ProQuest'
	     * Loop through 1st page of results to find https://www.proquest.com
	     * Click on https://www.proquest.com
	     * Verify ProQuest Home Page & Logo
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
		    objGoogleSearch.setInputValue("ProQuest");

		    //Wait for results to load
	        objGoogleSearch.waitForPageToLoadById("rcnt");
	        
	        //loop through results on first page for www.proquest.com and click it
	        objGoogleSearch.selectGoogleResult("https://www.proquest.com"); 
	        
	        //Create ProQuest Page object
	        objProQuest = new ProQuestPage(driver);
			
	        //Wait for ProQuest Page to Load
	        objProQuest.waitForPageToLoad("content");
	        
	        //Verify ProQuest Logo
	        objProQuest.verifyProQuestHomePageLogo();
		}	
		
		@AfterMethod
		public void afterTest() 
		{
			driver.quit();			
		}		
}