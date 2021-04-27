package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProQuestPage {

    WebDriver driver;

    public ProQuestPage(WebDriver driver)
    {
        this.driver = driver;
    }
    
    //Wait for ProQuest page to load
    public void waitForPageToLoad(String pageId)
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(pageId)));
    }
    
    //Verify ProQuest home page logo
    public void verifyProQuestHomePageLogo()
    {
    	//Verify ProQuest Logo is displayed
        Assert.assertTrue(driver.findElement(By.className("pq-logo")).isDisplayed());
        
        //Verify ProQuest logo
	    Assert.assertEquals(driver.findElement(By.className("pq-logo")).getAttribute("title"), "ProQuest");
    }
    
    //Verify Career Page
    public void verifyProQuestCareerPage()
    {
    	//Verify Window Title
		Assert.assertEquals(driver.getTitle(), "ProQuest - Careers");
    	
    	//Verify Page Title is displayed
		Assert.assertTrue(driver.findElement(By.cssSelector(".page-header > h1")).isDisplayed());
		
		//Verify Page Title is Careers
		Assert.assertEquals(driver.findElement(By.cssSelector(".page-header > h1")).getText(), "Careers");
    }
    
    //Scroll to element on page
    public void scrollToElement(String elementId)
    {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id(elementId)));
    }

    //Navigate to Career Page in new window
    public void navigateToCareerPage()
    {
    	driver.findElement(By.className("navbar-toggle")).click();
    	WebElement navBar = driver.findElement(By.id("marketingmenu1"));
    	List<WebElement> links = navBar.findElements(By.tagName("li"));
    	for (WebElement link : links) 
    	{
    		if (link.findElement(By.tagName("a")).getText().equals("Careers"))
			{
    			link.findElement(By.tagName("a")).click();
			}
    	}
    	
	    // Switch to new window opened
	    for(String winHandle : driver.getWindowHandles()){
	        driver.switchTo().window(winHandle);
	    } 
    }
    
    //Wait for ProQuest page to load
    public void waitForJobSearchPageToLoad()
    {
    	// Switch to new window opened
	    for(String winHandle : driver.getWindowHandles()){
	        driver.switchTo().window(winHandle);
	    } 
    }

    //Verify Job Opening Results
    public void verifyJobOpenings(String jobName)
    {
    	WebElement jobOpenings = driver.findElement(By.cssSelector("#Opportunities > div:nth-child(3)"));
    	List<WebElement> jobs = jobOpenings.findElements(By.className("opportunity"));
    	
    	for (WebElement job : jobs) 
    	{
    		//Verify Job Name
    		if (job.findElement(By.className("opportunity-link")).getText().contains(jobName)) 
    			{
    				job.findElement(By.className("opportunity-link")).click();
    				break;
    			}
    	}
    }
    
    
    //Set input value of ProQuest job search
    public void setInputValue(String input)
    {
        driver.findElement(By.id("input-q")).sendKeys(input);
        driver.findElement(By.id("input-q")).sendKeys(Keys.ENTER);
    }

}