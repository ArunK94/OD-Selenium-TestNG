package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class loginddt {

	static File src;
	public static ArrayList<Object[]> getExceldata() throws IOException
	{
		ArrayList<Object[]> logincreds= new ArrayList<Object[]>();
		
		//reader= new Xls_Reader("C:/Users/Arun/eclipse-workspace/LearningSelTestNG/src/test/java/utility/Excel/CRM.xlsx");
		
		
		File src= new File("C:\\Users\\Arun\\eclipse-workspace\\LearningSelTestNG\\src\\test\\java\\utility\\Excel\\datadriven01.xlsx");
		FileInputStream fs= new FileInputStream(src);
		XSSFWorkbook wb=new XSSFWorkbook(fs);
		XSSFSheet login= wb.getSheetAt(0);
		
		for(int i=1; i<=login.getLastRowNum(); i++)
		{
			
			String username= login.getRow(i).getCell(0).getStringCellValue();
			String password= login.getRow(i).getCell(1).getStringCellValue();
			/*	String exptmessageUsername= login.getRow(i).getCell(2).getStringCellValue();
			String exptmessagePasswd= login.getRow(i).getCell(3).getStringCellValue();
			String username=reader.getCellData("Login", "username", rowCount);
			String password= reader.getCellData("Login", "password", rowCount); */
          Object obj[]= {username,password};
          logincreds.add(obj);
		wb.close();
		}
		return logincreds;
	}
	
}
