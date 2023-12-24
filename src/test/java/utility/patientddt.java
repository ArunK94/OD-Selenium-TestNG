package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class patientddt {
	static File src;
	public static ArrayList<Object[]> getExceldata2() throws IOException
	{
		ArrayList<Object[]> patient_name= new ArrayList<Object[]>();
		
		//reader= new Xls_Reader("C:/Users/Arun/eclipse-workspace/LearningSelTestNG/src/test/java/utility/Excel/CRM.xlsx");
		File src= new File("D:\\Learnings\\Eclipse\\selenium DDT\\datadriven01.xlsx");
		FileInputStream fs= new FileInputStream(src);
		XSSFWorkbook wb1=new XSSFWorkbook(fs);
		 XSSFSheet patientfilter = wb1.getSheetAt(2);
		DataFormatter data= new DataFormatter();
		
			for(int i=1; i<=patientfilter.getLastRowNum(); i++)
			{
			 XSSFCell cell1=patientfilter.getRow(i).getCell(0);
			 XSSFCell cell2=patientfilter.getRow(i).getCell(1);
			 
			 String filter_by=data.formatCellValue(cell1);
			 String patient_detail= data.formatCellValue(cell2);
		
			 /*	String exptmessageUsername= login.getRow(i).getCell(2).getStringCellValue();
			String exptmessagePasswd= login.getRow(i).getCell(3).getStringCellValue();
			String username=reader.getCellData("Login", "username", rowCount);
			String password= reader.getCellData("Login", "password", rowCount); */
          Object obj1[]= {filter_by,patient_detail};
          patient_name.add(obj1);
			}
		return patient_name;

}

}
