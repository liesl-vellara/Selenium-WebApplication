package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class parallelTesting {
	//it is cross browser testing
	// multiple browser running the code
	WebDriver driver;
	
	//Data driven testing was otherwise known as parameterization (means running code with multiple data)
	// if I want to run both the browsers parallel, we can use the annotation "Parameter"
	// Parameter means that I am going to run the code with multiple browser
	@Parameters("mybrowser")
	@BeforeTest
	public void BT(String mybrowser)
	{
		if (mybrowser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			
			driver = new FirefoxDriver();
			
		}else if (mybrowser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			
			driver = new ChromeDriver();
		}
		
	}
	
	@Test
	public void testcase1() throws InterruptedException
	{
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		Thread.sleep(10000);
		driver.findElement(By.id("welcome")).click();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@id='welcome-menu']/ul/li[3]/a")).click();
	}
}
