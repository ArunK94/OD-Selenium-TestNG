package opendrtest;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utility.XLUtility;
import utility.patientcreationddt;

public class patient_creation extends patientcreationddt{
	
	public static WebDriver driver;
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
	
	@DataProvider(name="PatientData")
	public String [][] getData() throws Exception
	{
	/*	ArrayList<Object[]> testdata1= patientcreationddt.getExceldata();
		return testdata1.iterator();  */
		// get the data from cell
		
		String path="D:\\Learnings\\Eclipse\\selenium DDT\\CRM.xlsx";
		XLUtility xlutil= new XLUtility(path);
		
		int totalrows= xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);
				
		String patientData[][]= new String [totalrows][totalcols];
		
		for(int i=1; i<totalrows; i++)
		{
			for(int j=0; j<totalcols; j++)
			{
				patientData[i-1][j]= xlutil.getCellData("Sheet1", i, j);
			}
						
		}
		return patientData;
	} 
	@BeforeMethod
	public void patientclick() throws InterruptedException {
		 driver.findElement(By.linkText("Patients")).click();
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("//button[contains(@onclick,'openAddPatient()')]")).click();
	}
	
	@Test(priority=1, dataProvider="PatientData")
	public void patientCreation(String firstname,String middlename,String lastname,String dob,String gender,String phone,String home,String work,String address1,String address2,String city,String state,String zipcode,String email) throws InterruptedException 
	{
		String exptitle="Add Patient";
		String actualtitle2=  driver.findElement(By.xpath("//h4[contains(@class,'modal-title')]")).getText();
		Assert.assertEquals(exptitle, actualtitle2,"Incorrect Header");
		
		Thread.sleep(2000);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.findElement(By.name("first_name")).sendKeys(firstname);
		driver.findElement(By.name("middle_name")).sendKeys(middlename);
		driver.findElement(By.name("last_name")).sendKeys(lastname);
		driver.findElement(By.name("dob")).sendKeys(dob);
		driver.findElement(By.id(gender)).click();
		driver.findElement(By.id("cell")).sendKeys(phone);
		driver.findElement(By.id("phone_number2")).sendKeys(home);
		driver.findElement(By.id("phone_number")).sendKeys(work);
		driver.findElement(By.id("billing_address_1")).sendKeys(address1);
		driver.findElement(By.id("billing_address_2")).sendKeys(address2);
		driver.findElement(By.id("billing_city")).sendKeys(city);
		Select state1= new Select(driver.findElement(By.id("billing_state")));
		state1.selectByVisibleText(state);
		driver.findElement(By.id("billing_zip")).sendKeys(zipcode);
        driver.findElement(By.id("email_address")).sendKeys(email);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[contains(@class,'btn btn-primary')]")).click();
        Thread.sleep(2000);

        String fn=driver.findElement(By.name("first_name")).getAttribute("value");
     	String ln=driver.findElement(By.name("last_name")).getAttribute("value");
    	String dob1=driver.findElement(By.name("dob")).getAttribute("value");
		//driver.findElement(By.id("Male"));
		String cell1=driver.findElement(By.id("cell")).getAttribute("value");
		String address101=driver.findElement(By.id("billing_address_1")).getAttribute("value");
		String city101=driver.findElement(By.id("billing_city")).getAttribute("value");
		//String state101=driver.findElement(By.id("billing_state")).getAttribute("value");
		String zip101=driver.findElement(By.id("billing_zip")).getAttribute("value");
		String actualtitle=  driver.findElement(By.xpath("//h4[contains(@class,'modal-title')]")).getText();
		System.out.println(" Title "+actualtitle);
		
	if(actualtitle.equals(exptitle))
	{
		if(fn.isBlank() || ln.isBlank() || dob1.isBlank() || cell1.isBlank() || address101.isBlank() || city101.isBlank() || zip101.isBlank())
		{
			 WebElement we= driver.findElement(By.xpath("//div[contains(@class,\"profileDetails addPatient\")]"));
			 String actualAlert=we.getText();
		//	 System.out.println("Text are: "+we.getText());
			 String expAlertMessage="Oops! Need Patient's";
			 Assert.assertTrue(actualAlert.contains(expAlertMessage));
			 driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();
			// String actualtitle3=  driver.findElement(By.xpath("//h4[contains(@class,'modal-title')]")).getText();
		//	 Assert.assertNotEquals(exptitle, actualtitle3,"Incorrect Header");
				
		}
		else if(!fn.matches("[a-zA-Z_._-_]+"))
		{
			String expectedAlert="Is that really your name? Please try again.";
			String actualAlert= driver.findElement(By.xpath("//label[contains(@id,'firstlastname-error')]")).getText();
			Assert.assertEquals(expectedAlert, actualAlert, "Alert message fails First Name");
			 driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();
			 

		}
		else if(!ln.matches("[a-zA-Z_._-_]+"))
		{
			String expectedAlert="Is that really your name? Please try again.";
			String actualAlert= driver.findElement(By.xpath("//label[contains(@id,'firstlastname-error')]")).getText();
			Assert.assertEquals(expectedAlert, actualAlert, "Alert message fails First Name");
		     driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();

		}
		
		else if(!city101.matches("[a-zA-Z_]+"))
		{
			String expectedAlert="Oops! Need Patient's City.";
			String actualAlert= driver.findElement(By.xpath("//label[contains(@id,'billing_city-error')]")).getText();
			Assert.assertEquals(expectedAlert, actualAlert, "Alert message fails First Name");
		    driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();

		}
		
		else if(!zip101.matches("[0-9]+") || zip101.length()<9)
		{
			String expectedAlert="Oops! Need Patient's Zip.";
			String actualAlert= driver.findElement(By.xpath("//label[contains(@id,'billing_zip-error')]")).getText();
			Assert.assertEquals(expectedAlert, actualAlert, "Alert message fails First Name");
		    driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();

		}
		
	}
	else
	{
		String dateShowing= driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[3]/div/div")).getText();
		//	String emailShowing=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[2]/div/div")).getText();
		Assert.assertEquals(dob1,dateShowing," Date mismatched Add patient functionality not working");
		//Assert.assertEquals(emailShowing.toLowerCase().contains(fn.toLowerCase()), " Name mismatched: Add patient functionality not working");
			
	}
	}

	/*
	
	
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
	//	String emailShowing=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[2]/div/div")).getText();
		Assert.assertEquals(dob1, dateShowing," Date mismatched: Add patient functionality not working");
		//Assert.assertEquals(emailShowing.toLowerCase().contains(fn.toLowerCase()), " Name mismatched: Add patient functionality not working");
		}
	
	}  

	
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
	
	@Test(priority=2)
	public void cancel_validation()
	{   
		driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();
		String exptitle="Add Patient";
		String actualtitle2=  driver.findElement(By.xpath("//h4[contains(@class,'modal-title')]")).getText();
		Assert.assertNotEquals(exptitle, actualtitle2,"Incorrect Header");
		
	}
	
	@Test(priority=3)
	public void close_validation()
	{   
		driver.findElement(By.xpath("//button[contains(@class,'close')]")).click();
		String exptitle="Add Patient";
		String actualtitle2=  driver.findElement(By.xpath("//h4[contains(@class,'modal-title')]")).getText();
		Assert.assertNotEquals(exptitle, actualtitle2,"Incorrect Header");
		
	}
	
	
	
	
	@AfterTest
	public void end() {
    driver.close();
}

}
