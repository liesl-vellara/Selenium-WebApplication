package webDriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.List;
import java.util.Set;

import javax.swing.event.MenuKeyEvent;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SimpleCommands {
	
	// adding the interface so that we can call any browser (webdrivermanager dependencies)
	WebDriver driver;
	
	// making a static variable to store the url since that is common
	public static String URL = "https://opensource-demo.orangehrmlive.com";
	public static String URL1 = "https://www.google.com";
	public static String URL2 = "https://demoqa.com/browser-windows";
	public static String URL3 = "https://demo.guru99.com/test/newtours";
	
	//adding a BeforeTest (from testng) to execute before any test method
	// in the before test, we will open chrome, firefox or anything
	
	@BeforeTest
	public void BT()
	{
		// for chorme driver class from the webdriver manager
		//ChromeDriver driver = new ChromeDriver();
		
		// for firefox we can write
		//FirefoxDriver driver = new FirefoxDriver();
		
		// like above we can use IE, safari, opera which is viewable in the Maven
		// dependencies 
		
		// to make it common to any browser we have to change 
		// ChromeDriver and make it as WebDriver, also we cannot know when the chrome
		// application get updated, and we cannot keep up the version
		// if there is a new version, it will download
		WebDriverManager.chromedriver().setup();
		
		//We need to create a new instance of browser everytime we run
		// that is to say: driver is a variable, 
		//webdrivermanager is an interface that contains all the class
		// it will open a new instance of browser for my url
		driver = new ChromeDriver();
		
		// I want to max my screen
		driver.manage().window().maximize();
		
		//get is the method to navigate to the URL 
		//driver.get(URL);
		
		//alternative method to navigate to the url
		//driver.navigate().to("https://opensource-demo.orangehrmlive.com");
		
						
	}
	
	// Writing the test (test cases) to login, run an assert to check if title matches
	// expected titles and finally logout
	// to disable the test, we use enabled = false
	@Test(enabled = false, priority = 0)
	public void testcase1() throws InterruptedException
	{
		// printing the title of the page
		//System.out.println(driver.getTitle());
		
		// getting the actual title and storing it
		// the expected title stores the value
		String ExpectedTitle = "OrangeHRM";
		String ActualTitle = driver.getTitle();
		
		//running an assert to check
		//Assert.assertEquals(ActualTitle, ExpectedTitle);
		
		//if we want to perform any action in the application, i will go with driver
		//because driver has the instance of the application
		// we will send the value "Admin" by using id with findElement method
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		
		// doing the same for password
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		//driver contains the entire browser
		// in the browser or webapp, we have to find the element by id/name/xpath/css/dom
		
		//putting an if condition, so that the program will execute when 
		// actual title is equal to expected title, then we will logout
		if(ExpectedTitle.equals(ActualTitle))
		{
		
			//I want to click on the button, we use click
			driver.findElement(By.id("btnLogin")).click();
		
			//from login, I am moving to home page, we will have to give sometime
			//we use thread
			Thread.sleep(5000);
		
			// now I want to click on the 'welcome' menu to reach logout
			driver.findElement(By.id("welcome")).click();
			// giving time to load the page
			Thread.sleep(5000);
		
			//now to click on the logout button using xpath
			driver.findElement(By.xpath("//*[@id='welcome-menu']/ul/li[3]/a")).click();
		}
		else {
			//Give a print that the title does not match
			System.out.println("The assert on title failed");
		}
		
	}
	@Test(enabled = false, priority = 1)
	public void testcase2() throws InterruptedException
	{
		// I want to enter the username as a web element
		//WebElement is an interface that can store an element, we cannot add values
		// ie; sendKeys
		WebElement UN = driver.findElement(By.id("txtUsername"));
		
		// to check if the WebElement UN is enabled
		if (UN.isEnabled())
		{
			//if there is data in the web element, we can clear it with clear
			UN.clear();
			
			//now pass the values to the username
			UN.sendKeys("Admin");
			
		}
		// if the UN that is passed, has it displayed that particular value
		// to do that we can use .isDisplayed()
		// if the value is displayed, go an enter the password
		//and click on okay button
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click(); 
		
		// we will use other locators ('linkText') and ('partialLinkText')
		// to use the linkText to click on Admin
		driver.findElement(By.linkText("Admin")).click();
		
		//giving time for the system
		Thread.sleep(2000);
		
		//to check a check box on orange hrm
		//we inspect the item and use it
		driver.findElement(By.id("ohrmList_chkSelectRecord_10")).click();
		
		//seeing the values
		Thread.sleep(3000);
		if(driver.findElement(By.id("ohrmList_chkSelectRecord_10")).isSelected())
		{
			System.out.println("The element is selected");
		}
		
	}
	
	@Test(priority = 2, enabled = false)
	public void testcase3()
	{
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		
		//I want to know how many links are there after I login
		// we store them in a list of web element
		List<WebElement> link = driver.findElements(By.tagName("a"));
		
		// we find the size of the list to know how many links are there
		System.out.println(link.size());
		
		//to print the name of the links
		for(int i=0; i< link.size(); i++)
		{
			//not all 109 links are shown
			System.out.println(link.get(i).getText()); 
		}
		
		
	}
	
	@Test(priority = 3, enabled = false)
	public void testcase4() throws InterruptedException
	{
		//to enter the username
		// we want to see what the attribute of name is
		String value = driver.findElement(By.name("userName")).getAttribute("name");
		// seeing what is stored in the name attribute
		System.out.println(value);
		
		// using value to send keys to the attribute
		driver.findElement(By.name(value)).sendKeys("liesl");
		driver.findElement(By.name("password")).sendKeys("liesl");
		driver.findElement(By.name("submit")).click();
		Thread.sleep(8000);
		
		// clicking on the flight link
		driver.findElement(By.xpath("//*[contains(text(), 'Flights')]")).click();
		Thread.sleep(3000);
		
		// click on the one-way radio button
		driver.findElement(By.xpath("//*[@name='tripType' and @value='oneway']")).click();
		Thread.sleep(3000);
		
		// selecting the drop down menu
		WebElement dd = driver.findElement(By.name("passCount"));
		
		// Selecting the Drop Down menu (not the options on the dropdown)
		// We use the Keyword Select that is part of selenium
		Select sc = new Select(dd);
		
		// storing the values of the dropdown menu into values using getOptions method in Select
		List<WebElement> values = sc.getOptions();
		
		// getting the size of the list
		System.out.println("This number of options in Dropdown list is: " + values.size());
		
		
		// to print the values of the dropdown list
		for(int i=0; i < values.size(); i++)
		{
			System.out.println(values.get(i).getText());
		}
		
		//how to select values
		/*
		 * we can get the first option selected
		 * we can get with value
		 * we can get with visible text
		 */
		sc.selectByIndex(3);
		sc.selectByValue("3");
		sc.selectByVisibleText("2");
		
		
	}
	
	@Test(priority=4, enabled=false)
	public void testcase5() throws InterruptedException
	{
		// sending keys to username
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		
		// doing the same for password
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		
		// clicking on the login
		driver.findElement(By.id("btnLogin")).click();
		
		// giving time to load
		Thread.sleep(3000);
		
		// listing the number of links
		//I want to know how many links are there after I login
		// we store them in a list of web element
		//List<WebElement> link = driver.findElements(By.tagName("a"));
				
		// we find the size of the list to know how many links are there
		//System.out.println(link.size());
				
		//to print the name of the links
		// this is a traditional for look
		//for(int i=0; i< link.size(); i++)
		//{
			//not all 109 links are shown
			//System.out.println(link.get(i).getText()); 
		//}
		
		// making a 'For Enhanced Loop'
		/*for(WebElement e: link)
		{
			System.out.println(e.getText());
			//System.out.println(e.getAttribute("href"));
		}*/
		
		// to navigate to the PIM
		//driver.findElement(By.linkText("PIM")).click();
		
		// sleep
		//Thread.sleep(2000);
		// to navigate one step back
		//driver.navigate().back();
		//Thread.sleep(2000);
		
		// to navigate one step forward
		//driver.navigate().forward();
		//Thread.sleep(2000);
		// to refresh
		//driver.navigate().refresh();
		//Thread.sleep(2000);
		
		WebElement link1 = driver.findElement(By.linkText("PIM"));
		
		// initiallising the actions to move the mouse
		Actions act = new Actions(driver);
		
		// now using build. perform
		act.moveToElement(link1).build().perform();
		Thread.sleep(5000);
		
		act.sendKeys(Keys.ARROW_DOWN).build().perform();
		Thread.sleep(2000);
		act.sendKeys(Keys.ARROW_DOWN).build().perform();
		Thread.sleep(2000);
		
		// to enter the option with a key
		act.sendKeys(Keys.ENTER).build().perform();
		
		

	}
	
	@Test(priority=5, enabled = false)
	public void testcase6()
	{
		// sending keys to username
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
				
		// doing the same for password
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		
		//using action
		//WebElement link = driver.findElement(By.linkText("Login"));
		Actions act = new Actions(driver);
		
		//act.moveToElement(link).build().perform();
		
		act.sendKeys(Keys.ENTER).build().perform();
		
		
	}
	
	@Test(priority=6, enabled = false)
	public void testcase7() throws InterruptedException, AWTException
	{
		driver.get(URL1);
		
		Actions act = new Actions(driver);
		
		WebElement link = driver.findElement(By.linkText("Gmail"));
		
		// I want to right click to see the options
		act.contextClick(link).build().perform();
		Thread.sleep(3000);
		
		//We import Robot because it will interact with the right click of the 
		// context click
		Robot rc = new Robot();
		// any key events, any menu activities, we use robot
		
		// clicking on the first option of the right click
		rc.keyPress(MenuKeyEvent.VK_DOWN);
		Thread.sleep(3000);
	
		//choosing the second option
		//rc.keyPress(MenuKeyEvent.VK_DOWN);
		
		// to click on the enter button on context click
		rc.keyPress(MenuKeyEvent.VK_ENTER);
		Thread.sleep(3000);
		
		// to switch tabs
		rc.keyPress(MenuKeyEvent.VK_CONTROL);
		rc.keyPress(MenuKeyEvent.VK_TAB);
		//System.out.println(driver.getTitle());
		
		//get the handle of one tab, it will store the first window opened
		// it can be for a browser
		//String parentTab = driver.getWindowHandle();
		
		//used to store all the tabs that are opened
		// it will identify how many windows, how many tabs
		
		Set<String> tabs = driver.getWindowHandles();
		
		// for enhanced loop for tabs
		for(String handles: tabs)
		{
			// it will switch to the other tab in the below line
			driver.switchTo().window(handles);
			System.out.println(driver.getTitle());
			driver.get("https://opensource-demo.orangehrmlive.com/");
			// gets the title
			System.out.println(driver.getTitle());
			
			// it will first print the parent window
			// we changed the parent url to orange hrm
			// it then will get the name of the child window (gmail)
			// then that child site is changed to orange 
		}		
		
	}
	
	
	@Test(priority = 7, enabled = false)
	public void testcase8() throws AWTException
	{
		// 1. Navigate to the url: https://demoqa.com/browser-windows 
		// stored in a public static variable
		driver.get(URL2);
		
		// 2. use string to store the parent window
		String parentTab = driver.getWindowHandle();
		// printing the parent tab
		System.out.println(parentTab);
		
		// 3. open two or three windows and store everything in child(?) window
		// 1st window
		for(int i=0; i<3; i++)
		{
			driver.findElement(By.id("windowButton")).click();
		}
		
		
		// switching to the parent tab
		driver.switchTo().window(parentTab);
		System.out.println(driver.getTitle());
		
		// changing parent window to orange hrm
		driver.get(URL);
		System.out.println(driver.getTitle());
		driver.close();
		
		// storing the parent and child windows
		Set<String> tabs = driver.getWindowHandles();
		System.out.println(tabs);
		
		
		for(String handles: tabs)
		{
			// switching to the handles in the tabs list
			driver.switchTo().window(handles);
			// printing the name
			System.out.println(driver.getTitle());
			
			// 5. switching to google
			driver.get(URL1);
			System.out.println(driver.getTitle());
			
			// 6. closing the parent tab
			driver.close();
			
		}
		// 7. closing the child tabs
		//driver.quit();
		
		
	}
	
	
	@Test(priority = 8, enabled = false)
	public void testcase9() throws InterruptedException
	{
		// Navigate to the url: https://demoqa.com/alerts
		// stored in a public static variable
		driver.get("https://demoqa.com/alerts");
		
		// clicking on the alert element
		driver.findElement(By.id("alertButton")).click();
		
		// alert is a key word in selenium
		Alert alt = driver.switchTo().alert();
		
		
		// clicking on the ok button
		alt.accept();
		Thread.sleep(3000);
		
		
		//clicking on the third alert element
		driver.findElement(By.id("confirmButton")).click();
		
		// to click on the cancel button
		alt.dismiss();
		Thread.sleep(3000);
		
		// clicking on the prompt button
		driver.findElement(By.id("promtButton")).click();
		Thread.sleep(8000);
		
		// adding values in the prompt box
		alt.sendKeys("We are learning about alert in selenium");
		// to get the text of the alert button
		//String alertinfo = alt.getText();
		//System.out.println(alertinfo);
		Thread.sleep(3000);
		
		alt.accept();
	}
	
	@Test(priority = 9, enabled = false)
	public void testcase10() throws InterruptedException
	{
		driver.get(URL3);
		// the title of the pages that are under construction is called
		// the string as shown below
		String underConstruction = "Under Construction: Mercury Tours";
		
		// storing the links with tag names
		List<WebElement> link = driver.findElements(By.tagName("a"));
		
		// getting the size of the list to make a new array which will store the linkText
		String[] linktext = new String [link.size()];
		
		int i = 0;
		
		//intirating through link
		for(WebElement e: link)
		{
			linktext[i] = e.getText();
			i++;
		}
		
		// now iterating and asserting with underconstruction
		for(String t : linktext)
		{
			driver.findElement(By.linkText(t)).click();
			Thread.sleep(3000);
			
			// adding an if statement to assert
			if (driver.getTitle().equals(underConstruction))
			{
				System.out.println(t + " is under construction");
			}
			else
			{
				System.out.println(t + " is working");
			}
			// navigating back
			driver.navigate().to(URL3);
			Thread.sleep(3000);
		}
	}

	@Test(priority=10, enabled=false)
	public void testcase11() throws InterruptedException
	{
		driver.get(URL);
		//Actions act = new Actions(driver);
		
		
		// using act to page down and page up
		//act.sendKeys(Keys.PAGE_DOWN).build().perform();
		//act.sendKeys(Keys.PAGE_UP).build().perform();
		
		// learning about frames
		
		// there are two interfaces
		// we will use javascript executor now
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		// getting the title with jsexecutor
		String title = js.executeScript("return document.title").toString();
		System.out.println(title);
		
		//getting the url with jsexecutor
		String url = js.executeScript("return document.URL").toString();
		System.out.println(url);
		
		//using webdriver
		//System.out.println(driver.findElement(By.id("divUsername")).getText());
		
		//when we want the inner text for all the elements
		String innertext = js.executeScript("return document.documentElement.innerText").toString();
		//System.out.println(innertext);
		
		//when we want the inner text of a particular element
		String usertext = js.executeScript("return document.getElementById('divUsername').innerText").toString();
		System.out.println(usertext);
		
		//to refresh the page
		//the '0' is used to refresh the main page
		js.executeScript("history.go(0)");
		
		// adding value to the username
		js.executeScript("document.getElementById('txtUsername').value='Admin'");
		Thread.sleep(3000);
		
		js.executeScript("document.getElementById('txtPassword').value='admin123'");
		
		js.executeScript("document.getElementById('btnLogin').click()");
		
		js.executeScript("return document.getElementById('menu_pim_viewPimModule')");
		js.executeScript("return document.getElementById('menu_pim_viewEmployeeList').click()");
		
		// scrolling to down
		js.executeScript("window.scrollBy(0, document.body.scrollHeight)");
		Thread.sleep(3000);
		// to scroll up to the top of the page
		js.executeScript("window.scrollBy(0, document.body.scrollHeight)");
		Thread.sleep(3000);
		// to scroll to a particular element
		js.executeScript("document.getElementById('ohrmList_chkSelectRecord_81').scrollIntoView()");
		
		// js can get a data frame which web driver cannot
		String table = js.executeScript("return document.getElementById('resultTable').innerText").toString();
		
		// source pages are created with html, so we can fetch those links too from the table
		//String table = js.executeScript("return document.getElementById('resultTable').innerHTML").toString();
		System.out.println(table);
	}
	
	@Test(priority = 11, enabled=false)
	public void testcase12() throws InterruptedException
	{
		driver.get("https://demoapp.skillrary.com");
		Thread.sleep(3000);
		driver.findElement(By.linkText("LOGIN")).click();
		driver.findElement(By.id("email")).sendKeys("user");
		driver.findElement(By.id("password")).sendKeys("user");
		driver.findElement(By.id("last")).click();
		
		//trying to get the table data in a table
		driver.findElement(By.xpath("//tbody/tr[2]/td[5]/preceding-sibling:: td[2]/button ")).click();
		
		driver.get("https://demoqa.com/webtables");
		Thread.sleep(3000);
		
		
		
		//trying to get the table data in a table
		WebElement age = driver.findElement(By.xpath("//*[@class='rt-tr -odd']//following-sibling :: div[2]"));
		
		// printing the webelement
		System.out.println(age.getText());
	}
	
	@Test(priority=12, enabled=true)
	public void testcase13() throws InterruptedException
	{
		// do broken links
		driver.get("https://demoqa.com/broken");
		Thread.sleep(10000);
		// scrolling to a particular place
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.getElementById('item-4').scrollIntoView()");
		WebElement a = driver.findElement(By.linkText("Click Here for Valid Link"));
		a.click();
		Thread.sleep(3000);
		String expectedTitle = "ToolsQA";
		if (driver.getTitle().equals(expectedTitle))
		{
			System.out.println("Link works");
		}else {
			System.out.println("Link broken");
		}
		
		driver.navigate().back();
		Thread.sleep(3000);
		
		// scrolling to a particular place
		js.executeScript("document.getElementById('item-4').scrollIntoView()");
		WebElement b = driver.findElement(By.linkText("Click Here for Broken Link"));
		b.click();
		Thread.sleep(3000);
		if (driver.getTitle().equals(expectedTitle))
		{
			System.out.println("Link works");
		}else {
			System.out.println("Link broken");
		}
		
	}
	
	@Test(priority = 13, enabled = false)
	public void testcase14()
	{
		//adding elements to a link
		driver.get("https://demoqa.com/upload-download");
		//uploadFile
		/*path of document on my desktop: "C:\Users-user\Desktop\dummy.txt"
		add extra slashes*/
		driver.findElement(By.id("uploadFile")).sendKeys("C:\\Users\\user\\Desktop\\dummy.txt");
	}
	@Test(priority =14, enabled=false)
	public void testcase15()
	{
		driver.get("https://demoqa.com/frames");
		// upload a document
		List<WebElement> Frame = driver.findElements(By.tagName("iframe"));
		
		for(int i = 0; i< Frame.size(); i++){
			String name = Frame.get(i).getAttribute("Id");
			System.out.println(name);
		}
		// switching to frame 1
		driver.switchTo().frame("frame1");
		
		//getting the text in frame 1
		System.out.println(driver.findElement(By.id("sampleHeading")).getText());
		
		//switching back to the original
		driver.switchTo().defaultContent();
		
		// going to frame 2
		driver.switchTo().frame("frame2");
		
		//scrolling using js executor
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, document.body.scrollHeight)");
		
	}
	
	@Test(priority =15, enabled=false)
	public void testcase16() throws InterruptedException
	{
		driver.get("https://demoqa.com/dynamic-properties");
		driver.findElement(By.id("enableAfter")).click();
		Thread.sleep(10000);
		String value = driver.findElement(By.tagName("p")).getAttribute("id");
		System.out.println(value);
		
	}
	@Test(priority = 16, enabled=false)
	public void testcase17() {
		
	}
	// we want to run steps after a steps have been executed
	@AfterTest(enabled=false)
	public void AT()
	{
		//after the test method has been executed, it will close everything
		driver.close();
	}
	
	

}
