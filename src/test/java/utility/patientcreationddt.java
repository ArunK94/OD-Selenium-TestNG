package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class patientcreationddt {
	
	static File src;
	public static ArrayList<Object[]> getExceldata() throws IOException, Exception
	{
		ArrayList<Object[]> patients= new ArrayList<Object[]>();
		File src= new File("D:\\Learnings\\Eclipse\\selenium DDT\\datadriven01.xlsx");
		FileInputStream fs= new FileInputStream(src);
		XSSFWorkbook wb2=new XSSFWorkbook(fs);
		 XSSFSheet patient = wb2.getSheetAt(4);
		DataFormatter data= new DataFormatter();
		
		for(int i=1; i<=patient.getLastRowNum(); i++)
		{	
			/*	
		String firstname=patient.getRow(i).getCell(0).getStringCellValue();
		 String middlename= patient.getRow(i).getCell(1).getStringCellValue();
		 String lastname=patient.getRow(i).getCell(2).getStringCellValue();
		 String dob=patient.getRow(i).getCell(3).getStringCellValue();
		 String gender=patient.getRow(i).getCell(4).getStringCellValue();
		 String phone= patient.getRow(i).getCell(5).getStringCellValue();
		 String home=patient.getRow(i).getCell(6).getStringCellValue();
		 String work= patient.getRow(i).getCell(7).getStringCellValue();
		 String address1=patient.getRow(i).getCell(8).getStringCellValue();
		 String address2=patient.getRow(i).getCell(9).getStringCellValue();
		 String city= patient.getRow(i).getCell(10).getStringCellValue();
		 String state= patient.getRow(i).getCell(11).getStringCellValue();
		 String zipcode= patient.getRow(i).getCell(12).getStringCellValue();
		 String email= patient.getRow(i).getCell(13).getStringCellValue();   */
			
		 XSSFCell cell1=patient.getRow(i).getCell(0);
		 XSSFCell cell2=patient.getRow(i).getCell(1);
		 XSSFCell cell3=patient.getRow(i).getCell(2);
		 XSSFCell cell4=patient.getRow(i).getCell(3);
		 XSSFCell cell5=patient.getRow(i).getCell(4);
		 XSSFCell cell6=patient.getRow(i).getCell(5);
		 XSSFCell cell7=patient.getRow(i).getCell(6);
		 XSSFCell cell8=patient.getRow(i).getCell(7);
		 XSSFCell cell9=patient.getRow(i).getCell(8);
		 XSSFCell cell10=patient.getRow(i).getCell(9);
		 XSSFCell cell11=patient.getRow(i).getCell(10);
		 XSSFCell cell12=patient.getRow(i).getCell(11);
		 XSSFCell cell13=patient.getRow(i).getCell(12);
		 XSSFCell cell14=patient.getRow(i).getCell(13);
					 
		 String firstname=data.formatCellValue(cell1);
		 String middlename= data.formatCellValue(cell2);
		 String lastname=data.formatCellValue(cell3);
		 String dob= data.formatCellValue(cell4);
		 String gender=data.formatCellValue(cell5);
		 String phone= data.formatCellValue(cell6);
		 String home=data.formatCellValue(cell7);
		 String work= data.formatCellValue(cell8);
		 String address1=data.formatCellValue(cell9);
		 String address2= data.formatCellValue(cell10);
		 String city=data.formatCellValue(cell11);
		 String state= data.formatCellValue(cell12);
		 String zipcode=data.formatCellValue(cell13);
		 String email= data.formatCellValue(cell14);
	
		 /*	String exptmessageUsername= login.getRow(i).getCell(2).getStringCellValue();
		String exptmessagePasswd= login.getRow(i).getCell(3).getStringCellValue();
		String username=reader.getCellData("Login", "username", rowCount);
		String password= reader.getCellData("Login", "password", rowCount); */
      Object obj2[]= {firstname,middlename,lastname,dob,gender,phone,home,work,address1,address2,city,state,zipcode,email};
      patients.add(obj2);
     wb2.close();
		}
		fs.close();
		return patients;		
		
	}

}
