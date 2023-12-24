package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class crmddt {
	static File src;
	public static ArrayList<Object[]> getExceldata1() throws IOException
	{
		ArrayList<Object[]> crmcreds= new ArrayList<Object[]>();
		
		//reader= new Xls_Reader("C:/Users/Arun/eclipse-workspace/LearningSelTestNG/src/test/java/utility/Excel/CRM.xlsx");
		File src= new File("D:\\Learnings\\Eclipse\\selenium DDT\\datadriven01.xlsx");
		FileInputStream fs= new FileInputStream(src);
		XSSFWorkbook wb=new XSSFWorkbook(fs);
		 XSSFSheet appointmentfilter = wb.getSheetAt(3);
		
			 String filter_by=appointmentfilter.getRow(1).getCell(1).getStringCellValue();
			 String type_exam01=appointmentfilter.getRow(2).getCell(1).getStringCellValue();
			 String type_exam02=appointmentfilter.getRow(2).getCell(2).getStringCellValue();
			 String type_exam03=appointmentfilter.getRow(2).getCell(3).getStringCellValue();
			 String appt_status01=appointmentfilter.getRow(3).getCell(1).getStringCellValue();
			 String appt_status02=appointmentfilter.getRow(3).getCell(2).getStringCellValue();
			 String appt_status03=appointmentfilter.getRow(3).getCell(3).getStringCellValue();
			 String appt_source=appointmentfilter.getRow(4).getCell(1).getStringCellValue();
			 String location01=appointmentfilter.getRow(5).getCell(1).getStringCellValue();
			 String location02=appointmentfilter.getRow(5).getCell(2).getStringCellValue();
			 String location03=appointmentfilter.getRow(5).getCell(3).getStringCellValue();
			 String pre_auth=appointmentfilter.getRow(6).getCell(1).getStringCellValue();
			 String appt_by=appointmentfilter.getRow(7).getCell(1).getStringCellValue();
			
			/*	String exptmessageUsername= login.getRow(i).getCell(2).getStringCellValue();
			String exptmessagePasswd= login.getRow(i).getCell(3).getStringCellValue();
			String username=reader.getCellData("Login", "username", rowCount);
			String password= reader.getCellData("Login", "password", rowCount); */
          Object obj[]= {filter_by,type_exam01,type_exam02,type_exam03,appt_status01,appt_status02,appt_status03,appt_source,location01,location02,location03,pre_auth,appt_by};
          crmcreds.add(obj);
		
		return crmcreds;

}
}
