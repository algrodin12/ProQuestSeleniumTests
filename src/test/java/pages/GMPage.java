package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class GMPage {

    WebDriver driver;

    public GMPage(WebDriver driver)
    {
        this.driver = driver;
    }
    
    //Wait for GM page to load by id
    public void waitForPageToLoad(String pageId)
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(pageId)));
    }
    
    //Wait for GM page to load by class
    public void waitForPageToLoadByClass(String pageClassName)
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(pageClassName)));
    }
    
    //Verify GM home page logo
    public void verifyGMHomePageLogo()
    {
    	//Verify Window Title
    	Assert.assertTrue(driver.getTitle().contains("General Motors"));
    			
    	//Verify GM Logo is displayed
        Assert.assertTrue(driver.findElement(By.className("gm-menu-bar__logo")).isDisplayed());
        
        //Verify GM logo
	    Assert.assertTrue(driver.findElement(By.className("gm-menu-bar__logo")).getAttribute("src").contains("GM-logo"));
    }
    
    //Verify Career Page
    public void verifyGMCareerPage()
    {
    	//Verify Window Title
    	Assert.assertEquals(driver.getTitle(), ("Careers with General Motors"));
    	
    	//Verify Page Title is displayed
		Assert.assertTrue(driver.findElement(By.className("cc-banner-ctr")).isDisplayed());
		
		//Verify Page Title is Careers
		Assert.assertEquals(driver.findElement(By.className("cc-banner-ctr")).getAttribute("alt"), "Careers at General Motors");
    }
    
    //Scroll to element on page by id
    public void scrollToElement(String elementId)
    {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id(elementId)));
    }
    
    //Scroll to element on page by css
    public void scrollToElementByCss(String cssName)
    {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(cssName)));
    }
    
    //Scroll to element on page by class
    public void scrollToElementByClass(String className)
    {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.className(className)));
    }


    //Navigate to Career Page
    public void navigateToCareerPage()
    {
    	WebElement navBar = driver.findElement(By.cssSelector("#navbar > nav.secondary-nav > div > ul"));
    	List<WebElement> links = navBar.findElements(By.tagName("li"));
    	for (WebElement link : links) 
    	{
    		if (link.findElement(By.tagName("a")).getText().equals("Careers"))
			{
    			link.findElement(By.tagName("a")).click();
    			break;
			}
    	}
    }
 
    //Verify Job Opening Results
    public void verifyAndClickOnJobOpening(String jobName)
    {
    	WebElement jobOpenings = driver.findElement(By.cssSelector("div.phs-jobs-list > div.content-block > ul"));
    	List<WebElement> jobs = jobOpenings.findElements(By.tagName("li"));
    	
    	for (WebElement job : jobs) 
    	{
    		if (job.findElement(By.cssSelector("div.information > span > a > div.job-title > span")).getText().equals(jobName)) 
    			{
    				job.findElement(By.cssSelector("div.information > span > a > div.job-title > span")).click();
    				break;
    			}
    	}
    }
    
    
    //Set input value of GM job search
    public void setInputValue(String input)
    {
    	//get current result count
        String currentResultCount = driver.findElement(By.cssSelector("div.phs-jobs-list-count.au-target > span.result-count")).getText();
    	
        //enter in input in search box
    	driver.findElement(By.id("sub_search_textbox")).click();
        driver.findElement(By.id("sub_search_textbox")).sendKeys(input);
        driver.findElement(By.id("sub_search_textbox")).sendKeys(Keys.ENTER);
        
        //wait for reduced result count due to filter
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("div.phs-jobs-list-count.au-target > span.result-count")), currentResultCount)));
    }

}