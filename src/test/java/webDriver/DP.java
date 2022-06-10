package webDriver;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;



public class DP {
	WebDriver driver;
	
	
  @Test(dataProvider = "dp")
  public void f(String n, String s) throws InterruptedException {
	  System.out.println("This is DP class");
	  driver.get("https://opensource-demo.orangehrmlive.com/");
	  driver.findElement(By.id("txtUsername")).sendKeys(n);
	  driver.findElement(By.id("txtPassword")).sendKeys(s);
	  driver.findElement(By.id("btnLogin")).click();
	  driver.findElement(By.id("welcome")).click();
	  Thread.sleep(5000);
	  driver.findElement(By.xpath("//*[@id='welcome-menu']/ul/li[3]/a")).click();
	  
	  
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { "Admin", "admin123" },
      new Object[] { "Admin", "admin123" },
    };
  }
  @BeforeTest
  public void beforeTest() {
	  WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
  }

  @AfterTest
  public void afterTest() {
	  driver.close();
  }

}
