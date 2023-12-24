package opendrtest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utility.loginddt;
import utility.patientcreationddt;

public class patient_creation2 {
	
	WebDriver driver=null;
    String expectedsignUrl= "https://portaltest.opendr.com/signin";	    
	
	@BeforeTest
	public void setup() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Learnings\\Eclipse\\browser\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.get("https://portaltest.opendr.com/");
		driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("opendoctorscrm@opendoctors247.com");
		driver.findElement(By.name("password")).clear();	
		driver.findElement(By.name("password")).sendKeys("opendr123");
		driver.findElement(By.xpath("//button[contains(@class,'btn btn-action btn-block highlight_bg')]")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/div[2]/div/div/div[2]/input")).sendKeys("Diagnostic Imaging Centers");
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/table/tbody/tr[2]/td[3]/a")).click();
	Thread.sleep(3000);
		
	}
	
	@BeforeMethod
	public void patientCreationClick() throws InterruptedException {
		 driver.findElement(By.linkText("Patients")).click();
		    Thread.sleep(3000);
		 driver.findElement(By.xpath("//button[contains(@onclick,'openAddPatient()')]")).click();
	    Thread.sleep(2000);
	}  
	/*@DataProvider
	public Iterator<Object[]> gettestPatients() throws Exception
	{
		ArrayList<Object[]> testdata01= patientcreationddt.getExceldata();
		return testdata01.iterator();
	}  */
	
	@Test(priority=1)
	public void patientCreation() throws InterruptedException {
		driver.findElement(By.name("first_name")).sendKeys("Namey");
		driver.findElement(By.name("middle_name")).sendKeys("Namey");
		driver.findElement(By.name("last_name")).sendKeys("Namey");
		driver.findElement(By.name("dob")).sendKeys("09021994");
		driver.findElement(By.id("Male")).click();
		driver.findElement(By.id("cell")).sendKeys("3452345254");
		driver.findElement(By.id("phone_number2")).sendKeys("3412345254");
		driver.findElement(By.id("phone_number")).sendKeys("3452311254");
		driver.findElement(By.id("billing_address_1")).sendKeys("TEST 2342");
		driver.findElement(By.id("billing_address_2")).sendKeys("TEST 2342");
		driver.findElement(By.id("billing_city")).sendKeys("Test");
		Select state1= new Select(driver.findElement(By.id("billing_state")));
		state1.selectByVisibleText("Arizona");
		driver.findElement(By.id("billing_zip")).sendKeys("423452344");
        driver.findElement(By.id("email_address")).sendKeys("test2324@mailcatch.com");
        driver.findElement(By.xpath("//input[contains(@class,'btn btn-primary')]")).click();
        
        String fn=driver.findElement(By.name("first_name")).getAttribute("value");
     	String ln=driver.findElement(By.name("last_name")).getAttribute("value");
    	String dob1=driver.findElement(By.name("dob")).getAttribute("value");
		//driver.findElement(By.id("Male"));
		String cell1=driver.findElement(By.id("cell")).getAttribute("value");
		String address101=driver.findElement(By.id("billing_address_1")).getAttribute("value");
		String city101=driver.findElement(By.id("billing_city")).getAttribute("value");
		String state101=driver.findElement(By.id("billing_state")).getAttribute("value");
		String zip101=driver.findElement(By.id("billing_zip")).getAttribute("value");
		String actualtitle=  driver.findElement(By.xpath("//h4[contains(@class,'modal-title')]")).getText();
		System.out.println(" Title "+actualtitle);
		
	String exptitle="Add Patient";
	
	if(actualtitle.equals(exptitle))
	{
		  if(fn.isBlank())
			{
	    	  String actual_error= driver.findElement(By.id("firstlastname-error")).getText();
	    	  String expected_error="Oops! Need Patient's Name.";
			  System.out.println(" First Name is Empty: ");
			  Assert.assertEquals(actual_error, expected_error,"First Name field is not empty");
	 		  driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();
				String dateShowing= driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[3]/div/div")).getText();
			 	String nameShowing=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[1]/a/div")).getText();
				Assert.assertNotEquals(dob1, dateShowing," Date mismatched: Add patient functionality not working");
				Assert.assertNotEquals(fn, nameShowing, " Name mismatched: Add patient functionality not working");
			}
			else
			{
				System.out.println(" First Name is not empty: "+fn);
		 		  driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();
				String dateShowing= driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[3]/div/div")).getText();
				String nameShowing=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[1]/a/div")).getText();
				Assert.assertNotEquals(dob1, dateShowing," Date mismatched: Add patient functionality not working");
				Assert.assertNotEquals(fn, nameShowing, " Name mismatched: Add patient functionality not working");
				
			}
	      if(ln.isBlank())
	 		{
	     	  String actual_error= driver.findElement(By.id("firstlastname-error")).getText();
	     	  String expected_error="Oops! Need Patient's Name.";
	 		  System.out.println(" Last Name is Empty: ");
	 		  Assert.assertEquals(actual_error, expected_error,"Last Name field is not empty");
	 		  driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();
				String dateShowing= driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[3]/div/div")).getText();
			 	String nameShowing=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[1]/a/div")).getText();
				Assert.assertNotEquals(dob1, dateShowing," Date mismatched: Add patient functionality not working");
				Assert.assertNotEquals(fn, nameShowing, " Name mismatched: Add patient functionality not working");
	 		}
	 		else
	 		{
	 			System.out.println(" Last Name is not empty: "+ln);
		 		  driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();
				String dateShowing= driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[3]/div/div")).getText();
				String nameShowing=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[1]/a/div")).getText();
				Assert.assertNotEquals(dob1, dateShowing," Date mismatched: Add patient functionality not working");
				Assert.assertNotEquals(fn, nameShowing, " Name mismatched: Add patient functionality not working");
				
	 		}
	      if(dob1.isBlank())
	 		{
	     	  String actual_error= driver.findElement(By.id("dob-error")).getText();
	     	  String expected_error="Oops! Need Patient's DOB.";
	 		  System.out.println(" DOB is Empty: ");
	 		  Assert.assertEquals(actual_error, expected_error,"DOB field is not empty");
	 		  driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();
				String dateShowing= driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[3]/div/div")).getText();
			 	String nameShowing=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[1]/a/div")).getText();
				Assert.assertNotEquals(dob1, dateShowing," Date mismatched: Add patient functionality not working");
				Assert.assertNotEquals(fn, nameShowing, " Name mismatched: Add patient functionality not working");
	 		}
	 		else
	 		{
	 			System.out.println(" DOB is not empty: "+dob1);
		 		  driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();
				String dateShowing= driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[3]/div/div")).getText();
				String nameShowing=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[1]/a/div")).getText();
				Assert.assertNotEquals(dob1, dateShowing," Date mismatched: Add patient functionality not working");
				Assert.assertNotEquals(fn, nameShowing, " Name mismatched: Add patient functionality not working");
				
	 		}
	      if(cell1.isBlank())
	 		{
	     	  String actual_error= driver.findElement(By.id("phoneNumber-error")).getText();
	     	  String expected_error="Oops! Need Patient's digits!";
	 		  System.out.println(" Phone number is Empty: ");
	 		  Assert.assertEquals(actual_error, expected_error,"Phone number field is not empty");
	 		  driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();
				String dateShowing= driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[3]/div/div")).getText();
			 	String nameShowing=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[1]/a/div")).getText();
				Assert.assertNotEquals(dob1, dateShowing," Date mismatched: Add patient functionality not working");
				Assert.assertNotEquals(fn, nameShowing, " Name mismatched: Add patient functionality not working");
				
	 		}
	 		else
	 		{
	 			System.out.println(" Phone number is not empty: "+cell1);
		 		  driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();
				String dateShowing= driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[3]/div/div")).getText();
				String nameShowing=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[1]/a/div")).getText();
				Assert.assertNotEquals(dob1, dateShowing," Date mismatched: Add patient functionality not working");
				Assert.assertNotEquals(fn, nameShowing, " Name mismatched: Add patient functionality not working");
				
	 		}
	      if(address101.isBlank())
	 		{
	     	  String actual_error= driver.findElement(By.id("billing_address_1-error")).getText();
	     	  String expected_error="Oops! Need Patient's Street Address.";
	 		  System.out.println(" Address is Empty: ");
	 		  Assert.assertEquals(actual_error, expected_error,"Address field is not empty");
	 		  driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();
				String dateShowing= driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[3]/div/div")).getText();
				String nameShowing=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[1]/a/div")).getText();
				Assert.assertNotEquals(dob1, dateShowing," Date mismatched: Add patient functionality not working");
				Assert.assertNotEquals(fn, nameShowing, " Name mismatched: Add patient functionality not working");
			
	 		}
	 		else
	 		{
	 			System.out.println(" Address is not empty: "+address101);
		 		  driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();
				String dateShowing= driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[3]/div/div")).getText();
				String nameShowing=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[1]/a/div")).getText();
				Assert.assertNotEquals(dob1, dateShowing," Date mismatched: Add patient functionality not working");
				Assert.assertNotEquals(fn, nameShowing, " Name mismatched: Add patient functionality not working");
				
	 		}
	      if(state101.isBlank())
	 		{
	     	  String actual_error= driver.findElement(By.id("billing_state-error")).getText();
	     	  String expected_error="Oops! Need Patient's State.";
	 		  System.out.println(" State is Empty: ");
	 		  Assert.assertEquals(actual_error, expected_error,"State field is not empty");
	 		  driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();
				String dateShowing= driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[3]/div/div")).getText();
				String nameShowing=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[1]/a/div")).getText();
				Assert.assertNotEquals(dob1, dateShowing," Date mismatched: Add patient functionality not working");
				Assert.assertNotEquals(fn, nameShowing, " Name mismatched: Add patient functionality not working");
	 		}
	 		else
	 		{
	 			System.out.println(" State is not empty: "+state101);
		 		  driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();
				String dateShowing= driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[3]/div/div")).getText();
				String nameShowing=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[1]/a/div")).getText();
				Assert.assertNotEquals(dob1, dateShowing," Date mismatched: Add patient functionality not working");
				Assert.assertNotEquals(fn, nameShowing, " Name mismatched: Add patient functionality not working");
				
	 		}
	      if(city101.isBlank())
	 		{
	     	  String actual_error= driver.findElement(By.id("billing_city-error")).getText();
	     	  String expected_error="Oops! Need Patient's City.";
	 		  System.out.println(" City is Empty: ");
	 		  Assert.assertEquals(actual_error, expected_error,"City field is not empty");
	 		  driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();
				String dateShowing= driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[3]/div/div")).getText();
				String nameShowing=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[1]/a/div")).getText();
				Assert.assertNotEquals(dob1, dateShowing," Date mismatched: Add patient functionality not working");
				Assert.assertNotEquals(fn, nameShowing, " Name mismatched: Add patient functionality not working");
	 		}
	 		else
	 		{
	 			System.out.println(" City is not empty: "+city101);
		 		  driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();
				String dateShowing= driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[3]/div/div")).getText();
				String nameShowing=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[1]/a/div")).getText();
				Assert.assertNotEquals(dob1, dateShowing," Date mismatched: Add patient functionality not working");
				Assert.assertNotEquals(fn, nameShowing, " Name mismatched: Add patient functionality not working");
				
	 		}
	      if(zip101.isBlank())
	    		{
	        	  String actual_error= driver.findElement(By.id("billing_zip-error")).getText();
	        	  String expected_error="Oops! Need Patient's Zip.";
	    		  System.out.println(" Zip-code is Empty: ");
	    		  Assert.assertEquals(actual_error, expected_error,"Zip code field is not empty");
		 		  driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();
					String dateShowing= driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[3]/div/div")).getText();
					String nameShowing=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[1]/a/div")).getText();
					Assert.assertNotEquals(dob1, dateShowing," Date mismatched: Add patient functionality not working");
					Assert.assertNotEquals(fn, nameShowing, " Name mismatched: Add patient functionality not working");
	    		}
	    		else
	    		{
	    			System.out.println(" Zip code is not empty: "+zip101);
	  	 		  driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();
					String dateShowing= driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[3]/div/div")).getText();
					String nameShowing=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[1]/a/div")).getText();
					Assert.assertNotEquals(dob1, dateShowing," Date mismatched: Add patient functionality not working");
					Assert.assertNotEquals(fn, nameShowing, " Name mismatched: Add patient functionality not working");
					
	    		}
	      Thread.sleep(1000);
		
	}
	else
	{
		String dateShowing= driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[3]/div/div")).getText();
		String nameShowing=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[1]/a/div")).getText();
		Assert.assertEquals(dob1, dateShowing," Date mismatched: Add patient functionality not working");
		Assert.assertEquals(fn, nameShowing, " Name mismatched: Add patient functionality not working");
	}
	
//	Assert.assertNotEquals(exptitle, actualtitle,`    );	
	
	
	
	}  
	/*
	@Test(priority=1)
	public void blank_validation() throws InterruptedException {
		driver.findElement(By.name("first_name")).sendKeys("");
		driver.findElement(By.name("last_name")).sendKeys("Lionmmer");
		Thread.sleep(3000);
		//driver.findElement(By.xpath("//input[contains(@class,'btn btn-primary')]")).click();
      String abc = driver.findElement(By.xpath("//label[contains(@class,'error')]")).getText();
        System.out.println("Data is: "+abc);
	    String fn=driver.findElement(By.name("first_name")).getAttribute("value");
     	String ln=driver.findElement(By.name("last_name")).getAttribute("value");
    	String dob=driver.findElement(By.name("dob")).getAttribute("value");
		driver.findElement(By.id("Male"));
		String cell=driver.findElement(By.id("cell")).getAttribute("value");
		String address1=driver.findElement(By.id("billing_address_1")).getAttribute("value");
		String city=driver.findElement(By.id("billing_city")).getAttribute("value");
		String state=driver.findElement(By.id("billing_state")).getAttribute("value");
		String zip=driver.findElement(By.id("billing_zip")).getAttribute("value");

      if(fn.isBlank())
		{
    	  String actual_error= driver.findElement(By.id("firstlastname-error")).getText();
    	  String expected_error="Oops! Need Patient's Name.";
		  System.out.println(" First Name is Empty: ");
		  Assert.assertEquals(actual_error, expected_error,"First Name field is not empty");
		}
		else
		{
			System.out.println(" Data is not empty: "+fn);
		}
      if(ln.isBlank())
 		{
     	  String actual_error= driver.findElement(By.id("firstlastname-error")).getText();
     	  String expected_error="Oops! Need Patient's Name.";
 		  System.out.println(" Last Name is Empty: ");
 		  Assert.assertEquals(actual_error, expected_error,"Last Name field is not empty");
 		}
 		else
 		{
 			System.out.println(" Last Name is not empty: "+fn);
 		}
      if(dob.isBlank())
 		{
     	  String actual_error= driver.findElement(By.id("dob-error")).getText();
     	  String expected_error="Oops! Need Patient's DOB.";
 		  System.out.println(" DOB is Empty: ");
 		  Assert.assertEquals(actual_error, expected_error,"DOB field is not empty");
 		}
 		else
 		{
 			System.out.println(" DOB is not empty: "+fn);
 		}
      if(cell.isBlank())
 		{
     	  String actual_error= driver.findElement(By.id("phoneNumber-error")).getText();
     	  String expected_error="Oops! Need Patient's digits!";
 		  System.out.println(" Phone number is Empty: ");
 		  Assert.assertEquals(actual_error, expected_error,"Phone number field is not empty");
 		}
 		else
 		{
 			System.out.println(" Phone number is not empty: "+fn);
 		}
      if(address1.isBlank())
 		{
     	  String actual_error= driver.findElement(By.id("billing_address_1-error")).getText();
     	  String expected_error="Oops! Need Patient's Street Address.";
 		  System.out.println(" Address is Empty: ");
 		  Assert.assertEquals(actual_error, expected_error,"Address field is not empty");
 		}
 		else
 		{
 			System.out.println(" Address is not empty: "+fn);
 		}
      if(state.isBlank())
 		{
     	  String actual_error= driver.findElement(By.id("billing_state-error")).getText();
     	  String expected_error="Oops! Need Patient's State.";
 		  System.out.println(" State is Empty: ");
 		  Assert.assertEquals(actual_error, expected_error,"State field is not empty");
 		}
 		else
 		{
 			System.out.println(" State is not empty: "+fn);
 		}
      if(city.isBlank())
 		{
     	  String actual_error= driver.findElement(By.id("billing_city-error")).getText();
     	  String expected_error="Oops! Need Patient's City.";
 		  System.out.println(" City is Empty: ");
 		  Assert.assertEquals(actual_error, expected_error,"City field is not empty");
 		}
 		else
 		{
 			System.out.println(" City is not empty: "+fn);
 		}
      if(zip.isBlank())
    		{
        	  String actual_error= driver.findElement(By.id("billing_zip-error")).getText();
        	  String expected_error="Oops! Need Patient's Zip.";
    		  System.out.println(" Zip-code is Empty: ");
    		  Assert.assertEquals(actual_error, expected_error,"Zip code field is not empty");
    		}
    		else
    		{
    			System.out.println(" Zip code is not empty: "+fn);
    		}
      Thread.sleep(10000);
		
	}
	*/
	
/*	@AfterMethod
	public void trim() {
		driver.quit();
	}
	*/
	public void patientend() {
		driver.findElement(By.linkText("Appointments")).click();

	}
	
	@AfterTest
	public void end() {
		driver.quit();
	}

}
