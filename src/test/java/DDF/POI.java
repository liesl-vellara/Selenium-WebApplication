package DDF;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class POI {
	WebDriver driver;
	@Test
	public void testcase1() throws InvalidFormatException, IOException, InterruptedException
	{
		// setting up chrome
		WebDriverManager.chromedriver().setup();
		
		
		File excel = new File("C:\\Users\\user\\Desktop\\POI.xlsx");
		// input the xls sheet
		// we use fileinputstream if we want to read from the file
		// if we want to write data in to the file
		FileInputStream fis = new FileInputStream(excel);
		
		//two methods in apache POI
		// 1. xssf to read xlsx format
		// 2. hssf to read xls format
		
		// getting the workbook
		// this is for xlsx
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		// getting the workbook to work with xls
		//HSSFWorkbook wb = new HSSFWorkbook(fis);
		
		// I want to get the sheet for xssf workbook
		XSSFSheet sh = wb.getSheet("Sheet1");
		
		// I want to read the data from the xssf sheet
		// to get the last row number
		int rowcount = sh.getLastRowNum();
		
		// row count starts from zero 
		for(int i = 0 ; i <= rowcount; i++) 
		{
			// cell of zero is the left most cell
			// the consecutive cell is one
			String username = sh.getRow(i).getCell(0).getStringCellValue();
			String password = sh.getRow(i).getCell(1).getStringCellValue();
			System.out.println(username);
			System.out.println(password);
			
			
			// creating an instance for chrome
			driver = new ChromeDriver();
			// the url
			driver.get("https://opensource-demo.orangehrmlive.com/");
			driver.findElement(By.id("txtUsername")).sendKeys(username);
			driver.findElement(By.id("txtPassword")).sendKeys(password);
			// closing the instance
			driver.close();
		}
		
	}

}
