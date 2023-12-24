package opendrtest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class testing {
	
	  public static WebDriver driver;
    String expectedsignUrl= "https://portaltest.opendr.com/signin";	    
	

    @Test(priority=1)
    public void testnew() throws InterruptedException {
    	System.setProperty("webdriver.chrome.driver", "D:\\Learnings\\Eclipse\\browser\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.MINUTES);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.MINUTES);
		driver.get("https://portaltest.opendr.com/signin");
	//	driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("opendoctorscrm@opendoctors247.com");
		driver.findElement(By.name("password")).clear();	
		driver.findElement(By.name("password")).sendKeys("opendr123");
		driver.findElement(By.xpath("//button[contains(@class,'btn btn-action btn-block highlight_bg')]")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/div[2]/div/div/div[2]/input")).sendKeys("Diagnostic Imaging Centers");
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/table/tbody/tr[2]/td[3]/a")).click();

		String before_filter= driver.findElement(By.xpath("//div[contains(@class,'dataTables_info')]")).getText();
		System.out.println("Records: "+before_filter);
		
		driver.findElement(By.xpath("//i[contains(@class,'fa fa-filter')]")).click();
		driver.findElement(By.id("daterange-btn")).click();
		List<WebElement> list=driver.findElements(By.xpath("//div[contains(@class,'ranges')]"));
		
		for(WebElement element: list){
         System.out.println("Elements are: "+element.getText());
         System.out.println("Next line");
         element.click();
		}
		
		
	//	System.out.println("Filter type: ");
		
		
		
		
	//	driver.findElement(By.xpath("/html/body/div[3]/div[3]/ul/li[7]")).click();
		driver.findElement(By.id("search")).click();
		
		
		/*	driver.findElement(By.linkText("Patients")).click();
		Thread.sleep(3000);
		 driver.findElement(By.xpath("//button[contains(@onclick,'openAddPatient()')]")).click();
		 driver.findElement(By.name("first_name")).sendKeys("Shgd651");
			driver.findElement(By.name("middle_name")).sendKeys("M");
			driver.findElement(By.name("last_name")).sendKeys("Tets");
			driver.findElement(By.name("dob")).sendKeys("11121990");
			driver.findElement(By.id("Male")).isSelected();

			driver.findElement(By.xpath("//input[contains(@class,'btn btn-primary')]")).click();

			  String fn=driver.findElement(By.name("first_name")).getAttribute("value");
		    	String ln=driver.findElement(By.name("last_name")).getAttribute("value");
		    	String dob1=driver.findElement(By.name("dob")).getAttribute("value");
		    	//String gen=driver.findElement(By.id("Male")).getAttribute("checked");
				String state101=driver.findElement(By.id("billing_state")).getAttribute("value");
				Select state1= new Select(driver.findElement(By.id("billing_state")));
				WebElement state=state1.getFirstSelectedOption();
				String sta= state.getText();
				//String state102=driver.findElement(By.id("billing_state")).getAttribute("value");

		    	System.out.println("State is: "+state101);
		    	System.out.println("State2 is: "+sta);


				System.out.println("First Name :"+fn+" Last Name: "+ln+" DOB: "+dob1);		
				String actualtitle=  driver.findElement(By.xpath("//h4[contains(@class,'modal-title')]")).getText();
			System.out.println(" Title "+actualtitle);
			String exptitle="Add Patient";
			if(actualtitle.equals(exptitle))
			{
				System.out.println("Enter here in loop if Actualtitle appears:");
				if(fn.isBlank() || ln.isBlank() || dob1.isBlank())
				{
					 WebElement we= driver.findElement(By.xpath("//div[contains(@class,\"profileDetails addPatient\")]"));
					 String actualAlert=we.getText();
				//	 System.out.println("Text are: "+we.getText());
					 String expAlertMessage="Oops! Need Patient's";
					 Assert.assertTrue(actualAlert.contains(expAlertMessage));
					 
					 System.out.println("Field is blank");
					 driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();
				}
				else if(!fn.matches("[a-zA-Z_._-_]+"))
				{
					String expectedAlert1="Is that really your name? Please try again.";
					String actualAlert1= driver.findElement(By.xpath("//label[contains(@id,'firstlastname-error')]")).getText();
					System.out.println("Alert message: "+actualAlert1);

					Assert.assertEquals(expectedAlert1, actualAlert1, "Alert message fails First Name");
					 System.out.println("Field contain invalid character");
					 driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();
			
				}
				else if(!ln.matches("[a-zA-Z_._-_]+"))
				{
					String expectedAlert="Is that really your name? Please try again.";
					String actualAlert= driver.findElement(By.xpath("//label[contains(@id,'firstlastname-error')]")).getText();
					System.out.println("Alert message: "+actualAlert);
					Assert.assertEquals(expectedAlert, actualAlert, "Alert message fails First Name");
					System.out.println("Field contain invalid character");
					 driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div[2]/a")).click();
			
			
				}
				
			}
			else
			{ 	
				System.out.println("Enter here in loop if patient creation done appears:");

				String dateShowing= driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[3]/div/div")).getText();
				//	String emailShowing=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div[2]/div/div/div/div/div[4]/div[2]/table/tbody/tr[1]/td[2]/div/div")).getText();
				Assert.assertEquals(dob1,dateShowing," Date mismatched Add patient functionality not working");
				//Assert.assertEquals(emailShowing.toLowerCase().contains(fn.toLowerCase()), " Name mismatched: Add patient functionality not working");
					System.out.println("patient has been created: "+ fn);
			}
			
    }
		*/	
			 /*  		
			
	    Thread.sleep(2000);	
	 WebElement we= driver.findElement(By.xpath("//div[contains(@class,\"profileDetails addPatient\")]"));
	 String actualAlert=we.getText();
	 System.out.println("Text are: "+we.getText());
	 String expAlertMessage="Oops! Need Patient's";
	 Assert.assertTrue(actualAlert.contains(expAlertMessage));
	 
    }

 
	@Test(priority=2)
	public void testingdel() {
		
	String exp="TENG345";
	//String exp1=exp.substring(0, 2)+"/"+exp.substring(3, 5);
		String actual="DERRECK TESTING REGAR TESTINGNET";
	System.out.println("");
     Assert.assertTrue(actual.contains(exp)," String not present");
	
	System.out.println(" "+exp.toLowerCase()+" "+actual.toLowerCase());

	
	if(exp.contains("1") || exp.contains("2") || exp.contains("3") || exp.contains("4") || exp.contains("5"))
	{
			System.out.println("True");
	}
	else
	{
		System.out.println("False");
	}
	*/
    }	

}
