package tests;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;		

import pages.ProQuestPage;

public class ProQuestTest {		
	    WebDriver driver;
	    ProQuestPage objProQuest;
	    
		@BeforeMethod
		public void beforeTest() 
		{	
	        driver = new ChromeDriver();
		}		
		
		/**
	     * Navigate to https://www.proquest.com/
	     * Verify ProQuest Home Page & Logo
	     * Loop through nav bar to select ABOUT US
	     * Look through sub nav bar to select CAREERS
	     * Go to Career Page
	     * Search for Jobs
	     * Verify Job Opening Results
	     */
		@Test				
		public void ProQuestTest()
		{	
			//Create ProQuest Page object
			objProQuest = new ProQuestPage(driver);
	
			//Navigate to ProQuest
	        driver.get("https://www.proquest.com/");

	        //Wait for ProQuest Page to Load
	        objProQuest.waitForPageToLoad("content");
	        
	        //Verify ProQuest Logo
	        objProQuest.verifyProQuestHomePageLogo();

	        //Wait for ProQuest Page to Load
	        objProQuest.waitForPageToLoad("content");
			
	        //Navigate to Career Page
	        objProQuest.navigateToCareerPage();

		    //Wait for Career Page to load
	        objProQuest.waitForPageToLoad("wrap");	        
	        
	        //Verify Career Page
	        objProQuest.verifyProQuestCareerPage();

	        //Scroll into view of job opening search bar
	        objProQuest.scrollToElement("input-q");

	        //Set input value in search
			objProQuest.setInputValue("selenium");

		    //Wait for Filtered Job Opening Search Results
	        objProQuest.waitForJobSearchPageToLoad();	        

	        //Verify number of job openings, position, country
	        objProQuest.verifyJobOpenings("Software Test Engineer");
	        
		    //Wait for Career Page to load
	        objProQuest.waitForPageToLoad("PageContent");	        
	        
		}	
		
		@AfterMethod
		public void afterTest() 
		{
			driver.quit();			
		}		
}