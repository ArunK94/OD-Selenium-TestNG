package opendrtest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utility.crmddt;

public class opendr_appt_listing {
      // TODO Auto-generated method stub
		WebDriver driver=null;
	    String expectedsignUrl= "https://portaltest.opendr.com/signin";	    

		@BeforeTest
		public void setup() throws InterruptedException
		{
			System.setProperty("webdriver.chrome.driver", "D:\\Learnings\\Eclipse\\browser\\chromedriver.exe");
			driver= new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get("https://portaltest.opendr.com/");
			driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
			driver.findElement(By.id("username")).clear();
			driver.findElement(By.id("username")).sendKeys("opendoctorscrm@opendoctors247.com");
			driver.findElement(By.name("password")).clear();	
			driver.findElement(By.name("password")).sendKeys("opendr123");
			driver.findElement(By.xpath("//button[contains(@class,'btn btn-action btn-block highlight_bg')]")).click();
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/div[2]/div/div/div[2]/input")).sendKeys("Diagnostic Imaging Centers");
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/table/tbody/tr[2]/td[3]/a")).click();
		Thread.sleep(5000);
		}
	    
		@DataProvider()
		public Iterator<Object[]> gettestData1() throws IOException
		{
			ArrayList<Object[]> testdata1= crmddt.getExceldata1();
			return testdata1.iterator();
		} 
		
		@BeforeMethod
		public void appoinment_module() {
			driver.findElement(By.xpath("/html/body/div[1]/aside/section/ul/li[2]/a/span")).click();
		}
		
		@Test( priority=1, dataProvider="gettestData1")
		public void apppointment(String filter_by,String type_exam01,String type_exam02,String type_exam03,String appt_status01,String appt_status02,String appt_status03,String appt_source,String location01,String location02,String location03,String pre_auth,String appt_by) throws InterruptedException {
			String before_filter= driver.findElement(By.xpath("//div[contains(@class,'dataTables_info')]")).getText();

			driver.findElement(By.xpath("//i[contains(@class,'fa fa-filter')]")).click();
			driver.findElement(By.id("daterange-btn")).click();
			driver.findElement(By.xpath("/html/body/div[3]/div[3]/ul/li[7]")).click();
			driver.findElement(By.id("search")).click();

			//Filter type
			driver.findElement(By.cssSelector("button[data-id='filter_type']")).click();
			driver.findElement(By.partialLinkText(filter_by)).click();

			//Type of test
			driver.findElement(By.cssSelector("button[data-id='service']")).click();
			driver.findElement(By.cssSelector("button[class='actions-btn bs-deselect-all btn btn-default']")).click();
			driver.findElement(By.partialLinkText(type_exam01)).click();
			driver.findElement(By.partialLinkText(type_exam02)).click();
			driver.findElement(By.partialLinkText(type_exam03)).click();

			//Appointment Status
			driver.findElement(By.cssSelector("button[data-id='status']")).click();
			driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div[2]/div[1]/div/div/div[3]/div/div[2]/div/div/div/div/div/button[2]")).click();
			driver.findElement(By.partialLinkText(appt_status01)).click();
			driver.findElement(By.partialLinkText(appt_status02)).click();
			driver.findElement(By.partialLinkText(appt_status03)).click();

			//Appt Source:
			driver.findElement(By.cssSelector("button[data-id='appt_source']")).click();
			driver.findElement(By.linkText(appt_source)).click();


			//Location:
			driver.findElement(By.cssSelector("button[data-id='location']")).click();
			driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div[2]/div[1]/div/div/div[2]/div/div[4]/div/div/div/div/div/button[2]")).click();
			driver.findElement(By.partialLinkText(location01)).click();
			driver.findElement(By.partialLinkText(location02)).click();
			driver.findElement(By.partialLinkText(location03)).click();
			// driver.findElement(By.partialLinkText(location0)).click();

			//Pre-Auth  
			driver.findElement(By.cssSelector("button[data-id='preauth_type']")).click();
			Thread.sleep(1000);
			driver.findElement(By.partialLinkText(pre_auth)).click();

			//Appointmnt created by
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("button[data-id='appt_created_by']")).click();
			driver.findElement(By.partialLinkText(appt_by)).click();

			driver.findElement(By.id("search")).click();
			String after_filter= driver.findElement(By.xpath("//div[contains(@class,'dataTables_info')]")).getText();
            Assert.assertNotEquals(after_filter, before_filter, "Filter issue");
            }
		
		@Test(priority=2)
		public void apptSort() throws InterruptedException {

			driver.findElement(By.xpath("//th[contains(text(),'Patient')]")).click();
			Thread.sleep(2000);
			WebElement sortchk1 = driver.findElement(By.xpath(
					"/html/body/div[1]/div/section[2]/div[3]/div/div/div/div/div[2]/div[2]/table/tbody/tr[1]/td[1]/div/a"));
			String patient_name1 = sortchk1.getText();
			Thread.sleep(2000);

			driver.findElement(By.xpath("//th[contains(text(),'Patient')]")).click();
			Thread.sleep(2000);
			WebElement sortchk2 = driver.findElement(By.xpath(
					"/html/body/div[1]/div/section[2]/div[3]/div/div/div/div/div[2]/div[2]/table/tbody/tr[1]/td[1]/div/a"));
			String patient_name2 = sortchk2.getText();
			Assert.assertNotEquals(patient_name1, patient_name2, "Sorting by Patient Name is not working");
			Thread.sleep(2000);

			// Sorting Referred By
			driver.findElement(By.xpath("//th[contains(text(),'Referred by')]")).click();
			Thread.sleep(2000);
			WebElement sortref1 = driver.findElement(By.xpath(
					"/html/body/div[1]/div/section[2]/div[3]/div/div/div/div/div[2]/div[2]/table/tbody/tr[1]/td[2]/div"));
			String reffered1 = sortref1.getText();
			Thread.sleep(2000);

			driver.findElement(By.xpath("//th[contains(text(),'Referred by')]")).click();
			Thread.sleep(2000);
			WebElement sortref2 = driver.findElement(By.xpath(
					"/html/body/div[1]/div/section[2]/div[3]/div/div/div/div/div[2]/div[2]/table/tbody/tr[1]/td[2]/div"));
			String reffered2 = sortref2.getText();
			Assert.assertNotEquals(reffered2, reffered1, "Sorting by Referring Doctor is not working");
			Thread.sleep(2000);

			// Sorting DOB
			driver.findElement(By.xpath("//th[contains(text(),'DOB')]")).click();
			Thread.sleep(2000);
			WebElement sortdob1 = driver.findElement(By.xpath(
					"/html/body/div[1]/div/section[2]/div[3]/div/div/div/div/div[2]/div[2]/table/tbody/tr[1]/td[3]/div"));
			String dob1 = sortdob1.getText();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//th[contains(text(),'DOB')]")).click();
			Thread.sleep(2000);
			WebElement sortdob2 = driver.findElement(By.xpath(
					"/html/body/div[1]/div/section[2]/div[3]/div/div/div/div/div[2]/div[2]/table/tbody/tr[1]/td[3]/div"));
			String dob2 = sortdob2.getText();
			Assert.assertNotEquals(dob2, dob1, "Sorting by DOB is not working");
			Thread.sleep(2000);

			// Sorting Appointment date
			driver.findElement(By.xpath("//th[contains(text(),'Appointment On')]")).click();
			Thread.sleep(2000);
			WebElement sortappt_date1 = driver.findElement(By.xpath(
					"/html/body/div[1]/div/section[2]/div[3]/div/div/div/div/div[2]/div[2]/table/tbody/tr[1]/td[4]/div"));
			String appt_date1 = sortappt_date1.getText();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//th[contains(text(),'Appointment On')]")).click();
			Thread.sleep(2000);
			WebElement sortappt_date2 = driver.findElement(By.xpath(
					"/html/body/div[1]/div/section[2]/div[3]/div/div/div/div/div[2]/div[2]/table/tbody/tr[1]/td[4]/div"));
			String appt_date2 = sortappt_date2.getText();
			Assert.assertNotEquals(appt_date1, appt_date2, "Sorting by Appointment Date is not working");
			Thread.sleep(2000);

			//Sorting Booking date
			driver.findElement(By.xpath("//th[contains(text(),'Booked On')]")).click();
			Thread.sleep(2000);
			WebElement sortbook_date1 = driver.findElement(By.xpath(
					"/html/body/div[1]/div/section[2]/div[3]/div/div/div/div/div[2]/div[2]/table/tbody/tr[1]/td[4]/div"));
			String book_date1 = sortbook_date1.getText();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//th[contains(text(),'Booked On')]")).click();
			Thread.sleep(2000);
			WebElement sortbook_date2 = driver.findElement(By.xpath(
					"/html/body/div[1]/div/section[2]/div[3]/div/div/div/div/div[2]/div[2]/table/tbody/tr[1]/td[4]/div"));
			String book_date2 = sortbook_date2.getText();
			Assert.assertNotEquals(book_date2, book_date1, "Sorting by Booking Date is not working");
			Thread.sleep(2000);
			

			// Sorting Exam Name:
			Thread.sleep(2000);
			driver.findElement(By.xpath("//th[contains(text(),'Exam name')]")).click();
			Thread.sleep(2000);
			WebElement sortexam1 = driver.findElement(By.xpath(
					"/html/body/div[1]/div/section[2]/div[3]/div/div/div/div/div[2]/div[2]/table/tbody/tr[1]/td[4]/div"));
			String exam1 = sortexam1.getText();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//th[contains(text(),'Exam name')]")).click();
			Thread.sleep(2000);
			WebElement sortexam2 = driver.findElement(By.xpath(
					"/html/body/div[1]/div/section[2]/div[3]/div/div/div/div/div[2]/div[2]/table/tbody/tr[1]/td[4]/div"));
			String exam2 = sortexam2.getText();
			Assert.assertNotEquals(exam1, exam2, "Sorting by Exam Name is not working");

		}
		
		@Test(priority=3)
		public void pagination() throws InterruptedException {
			Thread.sleep(2000);
			WebElement page01 = driver.findElement(By.xpath(
					"/html/body/div[1]/div/section[2]/div[3]/div/div/div/div/div[2]/div[2]/table/tbody/tr[1]/td[5]/div"));
			String page011 = page01.getText();
			Thread.sleep(2000);
			driver.findElement(By.linkText("2")).click();
			Thread.sleep(1000);
			// driver.findElement(By.linkText("3")).click();
			// Thread.sleep(1000);
			driver.findElement(By.linkText("Previous")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("Next")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("Next")).click();
			Thread.sleep(2000);
			WebElement page02 = driver.findElement(By.xpath(
					"/html/body/div[1]/div/section[2]/div[3]/div/div/div/div/div[2]/div[2]/table/tbody/tr[1]/td[5]/div"));
			String page022 = page02.getText();
			Assert.assertNotEquals(page011, page022, "Pagination functionality is not working");
          }
		
		@Test(priority=4)
		public void patientProfileScreen() throws InterruptedException {
			List<WebElement> list= driver.findElements(By.xpath("//div[contains(@class,'patientTooltip underlineHover left')]"));
	System.out.println(" List of Patient Names: count "+list.size());
	
	for(int i=0; i<list.size(); i++)
	{
		String patient_name=list.get(i).getText();
		list.get(i).click();
		Thread.sleep(3000);
		String expt_patient_name= driver.findElement(By.xpath("//h3[contains(@class,'profile-username text-center')]")).getText();
		Assert.assertEquals(patient_name, expt_patient_name, "Patient name click not navigate to Patient Profile Screen");
		driver.navigate().back();
		break;

	}
		
	}
		
		@Test(priority=5)
		public void date_filter() throws InterruptedException {
			driver.findElement(By.xpath("//i[contains(@class,'fa fa-filter')]")).click();
			  String before_filter= driver.findElement(By.xpath("//div[contains(@class,'dataTables_info')]")).getText();

			driver.findElement(By.id("daterange-btn")).click();
			List<WebElement> list=driver.findElements(By.xpath("//div[contains(@class,'ranges')]"));
			
			for(WebElement element: list){
				
	       if(element.getText().trim().equals("Tomorrow"))
	    	   {System.out.println("One");
        	   element.click();
   		    	driver.findElement(By.id("search")).click();
                Thread.sleep(3000);
 			  String after_filter= driver.findElement(By.xpath("//div[contains(@class,'dataTables_info')]")).getText();
 	           Assert.assertNotEquals(after_filter, before_filter, "Filter issue");
 				driver.findElement(By.xpath("//i[contains(@class,'fa fa-filter')]")).click();
 				driver.findElement(By.id("daterange-btn")).click();

	    	   }
	       if(element.getText().trim().equals("Yesterday"))
    	   {	    	   System.out.println("Two");

    	   element.click();
		    	driver.findElement(By.id("search")).click();
                Thread.sleep(3000);
			  String after_filter= driver.findElement(By.xpath("//div[contains(@class,'dataTables_info')]")).getText();
	           Assert.assertNotEquals(after_filter, before_filter, "Filter issue");
    	   }
	       if(element.getText().trim().equals("Next 7 Days"))
    	   {	    	  System.out.println("Three");

    	      element.click();
 		    	driver.findElement(By.id("search")).click();
                Thread.sleep(3000);

			  String after_filter= driver.findElement(By.xpath("//div[contains(@class,'dataTables_info')]")).getText();
	           Assert.assertNotEquals(after_filter, before_filter, "Filter issue");
    	   }
	       if(element.getText().trim().equals("Last 7 Days"))
    	   { 	    	   System.out.println("Four");

    	   element.click();
		    	driver.findElement(By.id("search")).click();
                Thread.sleep(3000);

			  String after_filter= driver.findElement(By.xpath("//div[contains(@class,'dataTables_info')]")).getText();
	           Assert.assertNotEquals(after_filter, before_filter, "Filter issue");
    	   }
	       if(element.getText().trim().equals("Next 30 Days"))
    	   { 
	    	   System.out.println("Five");

	    	   element.click();
		    	driver.findElement(By.id("search")).click();
                Thread.sleep(3000);

			  String after_filter= driver.findElement(By.xpath("//div[contains(@class,'dataTables_info')]")).getText();
	           Assert.assertNotEquals(after_filter, before_filter, "Filter issue");
    	   }
	       if(element.getText().trim().equals("Last 30 Days"))
    	   {  System.out.println("Six");
    	   element.click();
		    	driver.findElement(By.id("search")).click();
                Thread.sleep(3000);

			  String after_filter= driver.findElement(By.xpath("//div[contains(@class,'dataTables_info')]")).getText();
	           Assert.assertNotEquals(after_filter, before_filter, "Filter issue");
    	   }
	       if(element.getText().trim().equals("This Month"))
    	   {   System.out.println("Seven");
    	   element.click();
		    	driver.findElement(By.id("search")).click();
                Thread.sleep(3000);

			  String after_filter= driver.findElement(By.xpath("//div[contains(@class,'dataTables_info')]")).getText();
	           Assert.assertNotEquals(after_filter, before_filter, "Filter issue");
    	   }
	       if(element.getText().trim().equals("Next Month"))
    	   {    System.out.println("Eight");
    	   element.click();
		    	driver.findElement(By.id("search")).click();
                Thread.sleep(3000);

			  String after_filter= driver.findElement(By.xpath("//div[contains(@class,'dataTables_info')]")).getText();
	           Assert.assertNotEquals(after_filter, before_filter, "Filter issue");
    	   }
	       if(element.getText().trim().equals("Last Month"))
    	   {   System.out.println("Nine");
    	   element.click();
		    	driver.findElement(By.id("search")).click();
                Thread.sleep(3000);

			  String after_filter= driver.findElement(By.xpath("//div[contains(@class,'dataTables_info')]")).getText();
	           Assert.assertNotEquals(after_filter, before_filter, "Filter issue");
    	   }
	       
			}
			
		}
		
        @AfterTest
		public void tearDown1()
		{
			driver.quit();
		}
	}
