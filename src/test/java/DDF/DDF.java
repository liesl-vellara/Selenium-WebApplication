package DDF;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class DDF {
	WebDriver driver;
	public static String URL = "https://opensource-demo.orangehrmlive.com";
  @Test
  public void testcase1() throws BiffException, IOException {
	  WebDriverManager.chromedriver().setup();
	  driver = new ChromeDriver();
	  
	  // creating a variable to store the path of the excel sheet
	  File excel = new File("C:\\Users\\user\\Desktop\\DDF.xls");
	  
	  // go inside the workbook and then get the sheet, then row and column
	  Workbook wb = Workbook.getWorkbook(excel);
	  
	  // getting the sheet directly
	  Sheet sh = wb.getSheet("Sheet1");
	  
	  // getting the rows
	  int rows = sh.getRows();
	  
	  // getting the columns
	  int cols = sh.getColumns();
	  
	  // creating a null to store the contents of a cell
	  //Cell cl = null;
	  
	  // to store the username
	  String username = null;
	  // to store the password
	  String password = null;
	  
	  // to get the number of sheets
	  // it will store the number of sheets
	  //int sheets = wb.getNumberOfSheets();
	  
	  // to get the contents of a cell and pass it through our login and logout
	  for(int i=0; i<rows; i++) {
		  for(int j=0; j< cols; j++) {
			  if(j==0) {
				  username = sh.getCell(j,i).getContents();
			  }
			  else {
				  password = sh.getCell(j, i).getContents();
			  }
		  }
		  // sending the username and password here and clicking login 
		  driver.get(URL);
		  driver.findElement(By.id("txtUsername")).sendKeys(username);
		  driver.findElement(By.id("txtPassword")).sendKeys(password);
	  }
	  
	  
	  
  }
  @BeforeTest
  public void BT() {
  }

  @AfterTest
  public void AT() {
  }

}
