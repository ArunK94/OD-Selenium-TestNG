package opendrtest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utility.crmddt;
import utility.patientddt;

public class opendr_patient_listing {

	// TODO Auto-generated method stub
		WebDriver driver=null;
	    String expectedsignUrl= "https://portaltest.opendr.com/signin";	    

		@BeforeTest
		public void setup3() throws InterruptedException
		{
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
		
		@DataProvider()
		public Iterator<Object[]> gettestData2() throws IOException
		{
			ArrayList<Object[]> testdata2= patientddt.getExceldata2();
			return testdata2.iterator();
		} 
		
		@Test(priority=1,dataProvider="gettestData2")
		public void patient_listing(String filter_by, String patient_detail) throws InterruptedException {
			 driver.findElement(By.linkText("Patients")).click();
			 driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
			 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				
			 driver.findElement(By.xpath("//button[contains(@class,'btn dropdown-toggle bs-placeholder btn-default')]")).click();
			 Thread.sleep(1000);
	         driver.findElement(By.linkText(filter_by)).click();
	    
	         driver.findElement(By.xpath("//input[contains(@type,'search')]")).sendKeys(patient_detail);
			 driver.findElement(By.xpath("//input[contains(@class,'btnGo btn btn-default')]")).click();
			 Thread.sleep(2000);

			 String name=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[1]/a/div")).getText();
			 String email=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[2]/div/div")).getText();
			 String mrn=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[5]/div/div")).getText();
			 String dob=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[3]/div/div")).getText();
			 String phone=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[4]/div/div")).getText();

			 Thread.sleep(3000);

			 driver.findElement(By.xpath("//input[contains(@class,'btnReset btn btn-default')]")).click();

			if(filter_by.equals("Name")) 
			{
				// System.out.println(" Data "+name+" "+filter_by+" "+patient_detail);

				Assert.assertTrue(name.toLowerCase().contains(patient_detail.toLowerCase()),"Filter by Name fails");
			
			}
			else if(filter_by.equals("Email"))
			{
			    //	 System.out.println(" Data "+email+" "+filter_by+" "+patient_detail);

				Assert.assertTrue(email.toLowerCase().contains(patient_detail.toLowerCase()), "Filter by Email id fails");
			}
			else if(filter_by.equals("MRN"))
			{
				
				// System.out.println(" Data "+mrn+" "+filter_by+" "+patient_detail);

				Assert.assertTrue(mrn.contains(patient_detail), "Filter by MRN id fails");

			}
			else if(filter_by.equals("Phone"))
			{ 	
				//	 System.out.println(" Data "+phone+" "+filter_by+" "+patient_detail);

				Assert.assertTrue(phone.contains(patient_detail.substring(0, 3)), "Filter by DOB fails");
			}
			else if(filter_by.equals("DOB"))
			{ 	//		 System.out.println(" Data "+dob+" "+filter_by+" "+patient_detail);

				Assert.assertTrue(dob.contains(patient_detail.substring(0, 2)), "Filter by DOB fails");
			}
			 
	    }
		
		@Test(priority=2)
		public void pat_sorting() throws InterruptedException {
			 driver.findElement(By.linkText("Patients")).click();
			 Thread.sleep(3000);
										
				//Sorting Email Address By
				
				driver.findElement(By.xpath("//th[contains(text(),'Email Address')]")).click();
				Thread.sleep(2000);
				WebElement sortemail1 = driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[2]/div/div"));
				String email1 = sortemail1.getText();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//th[contains(text(),'Email Address')]")).click();
				Thread.sleep(2000);
				WebElement sortemail2 = driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[2]/div/div"));
				String email2 = sortemail2.getText();
				Assert.assertNotEquals(email2, email1, "Sorting by Email Address is not working");
				Thread.sleep(2000); 
				
				// Sorting Patient Name By
				driver.findElement(By.xpath("//th[contains(text(),'Name')]")).click();
				Thread.sleep(2000);
				WebElement sortname = driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[1]/a/div"));
			
				String name1 = sortname.getText();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//th[contains(text(),'Name')]")).click();
				Thread.sleep(2000);
				WebElement sortname2 = driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[1]/a/div"));
				String name2 = sortname2.getText();
				Assert.assertNotEquals(name2, name1, "Sorting by Patient Name is not working");
				Thread.sleep(2000); 
				
                //Sorting DOB By
				
				driver.findElement(By.xpath("//th[contains(text(),'DOB')]")).click();
				Thread.sleep(2000);
				WebElement sortdob1 = driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[3]/div/div"));
				String dob1 = sortdob1.getText();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//th[contains(text(),'DOB')]")).click();
				Thread.sleep(2000);
				WebElement sortdob2 = driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[3]/div/div"));
				String dob2 = sortdob2.getText();
				Assert.assertNotEquals(dob2, dob1, "Sorting by DOB is not working");
				Thread.sleep(2000);
				
                 //Sorting Phone By
				
				driver.findElement(By.xpath("//th[contains(text(),'Phone')]")).click();
				Thread.sleep(2000);
				WebElement sortphone1 = driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[4]/div/div"));
				String phone1 = sortphone1.getText();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//th[contains(text(),'Phone')]")).click();
				Thread.sleep(2000);
				WebElement sortphone2 = driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[4]/div/div"));
				String phone2 = sortphone2.getText();
				Assert.assertNotEquals(phone2, phone1, "Sorting by Phone is not working");
				Thread.sleep(2000);
				
                 //Sorting MRN By
				
				driver.findElement(By.xpath("//th[contains(text(),'MRN')]")).click();
				Thread.sleep(2000);
				WebElement sortmrn1 = driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[4]/div/div"));
				String mrn1 = sortmrn1.getText();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//th[contains(text(),'MRN')]")).click();
				Thread.sleep(2000);
				WebElement sortmrn2 = driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[4]/div/div"));
				String mrn2 = sortmrn2.getText();
				Assert.assertNotEquals(mrn2, mrn1, "Sorting by MRN is not working");
				Thread.sleep(2000);	
				
		}
		
		@Test(priority=3)
		public void patient_pagination() throws InterruptedException {
			 driver.findElement(By.linkText("Patients")).click();
			Thread.sleep(2000);
			WebElement page01 = driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[2]/div/div"));
			String page012 = page01.getText();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[contains(@data-dt-idx,'3')]")).click();
			Thread.sleep(1000);
			// driver.findElement(By.linkText("3")).click();
			// Thread.sleep(1000);
			driver.findElement(By.linkText("Previous")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("Next")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("Next")).click();
			Thread.sleep(2000);
			WebElement page02 = driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[2]/div/div"));
			String page023 = page02.getText();
			Assert.assertNotEquals(page012, page023, "Pagination functionality is not working");
 }
			
		
		@AfterTest
		public void end()
		{
			driver.close();
		}
	}


