package webDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNg {
	WebDriver driver;
	
	@BeforeTest
	public void BT() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	@Test
	public void testcase1()
	{
		System.out.println("This is TestNG");
	}
	@AfterTest
	public void AT() {
		driver.close();
	}

}
