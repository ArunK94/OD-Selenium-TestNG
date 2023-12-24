package opendrtest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utility.loginddt;

public class opendr_login {
	WebDriver driver=null;
    String expectedsignUrl= "https://portaltest.opendr.com/signin";
    

	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "D:\\Learnings\\Eclipse\\browser\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://portaltest.opendr.com/");
		driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();

	}
		
	@DataProvider()
	public Iterator<Object[]> gettestData() throws IOException
	{
		ArrayList<Object[]> testdata= loginddt.getExceldata();
		return testdata.iterator();
	}
	
	@Test(dataProvider="gettestData")
	public void login(String username, String password) throws Exception {
		
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.name("password")).clear();	
		driver.findElement(By.name("password")).sendKeys(password);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(@class,'btn btn-action btn-block highlight_bg')]")).click();
		Thread.sleep(2000);
	    String exptmessage="Oops! Try again.";

		
		String actualUrl= driver.getCurrentUrl();
		
		if(actualUrl.equals(expectedsignUrl))
		{  
		Thread.sleep(3000);
		System.out.println("Incorrect Login Credentials: "+username+" : PASSED");
		String incorrectmsg= driver.findElement(By.xpath("//label[contains(@for,'username')]")).getText();
		String incorrectmessagePasswd= driver.findElement(By.xpath("//label[contains(@for,'Password')]")).getText();
		Assert.assertEquals(incorrectmsg, exptmessage, "Incorrect Username failed");
		Assert.assertEquals(incorrectmessagePasswd, exptmessage, "Incorrect Password failed");
		}
		else
		{  
		Thread.sleep(2000);
		String expectedpracticeUrl= "https://portaltest.opendr.com/crm/kpi/practicelogin";
		//String actualpracticeUrl= driver.getCurrentUrl();

		if(actualUrl.equals(expectedpracticeUrl))
		{
		System.out.println("User successfully logged-In: "+username+" : PASSED");
		String actualloginText= driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/div/div/h2")).getText();
		String exploginText="List of Practices";
		Assert.assertEquals(actualloginText, exploginText,"Login credentials failed");
		//Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(@class,'dropdown-toggle btn btn-default btn-sm')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]")).click();
		}

		else
		{
		System.out.println("User successfully logged-In: "+username+" : PASSED");
		String actualloginnText= driver.findElement(By.xpath("/html/body/div[1]/div/section[1]/h1/text()")).getText();
		String exploginnText="List of Practices";
		Assert.assertEquals(actualloginnText, exploginnText,"List of Appointments");
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(@title,'Signout')]")).click();
		
		}
	}
  }
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
