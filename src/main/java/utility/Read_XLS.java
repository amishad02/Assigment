package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class Read_XLS {
	
	public String filelocation=null;
	public FileInputStream ipstr = null;
	public FileOutputStream opstr = null;
	public HSSFWorkbook wb = null;
	public HSSFSheet ws =null;
	public WebDriver driver;
	public Properties p;
	public HashMap<String, String> qa;

	
	public Read_XLS(String filelocation)
	{
		this.filelocation = filelocation;
		try {
			ipstr = new FileInputStream(filelocation);
			wb = new HSSFWorkbook(ipstr);
			ws = wb.getSheetAt(0);
			ipstr.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public Properties getObjectRepository(String filepath) 
	{ 
		try {
			p = new Properties();   		
			FileInputStream objfile = new FileInputStream(filepath);		
			p.load(objfile); 	
			
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		return p;
	}
	
	
	public int retrieveNoofRows(String wsName) {
		int sheetIndex = wb.getSheetIndex(wsName);
		if(sheetIndex == -1)
		{
			return 0;
		}else {
			ws = wb.getSheetAt(sheetIndex);
			int rowCount = ws.getLastRowNum()+1;
			return rowCount;
		}
		
	}
	
	public int retrieveNoofCols(String wsName)
	{
		int sheetIndex = wb.getSheetIndex(wsName);
		if(sheetIndex == -1)
		{
			return 0;
		}else {
			ws = wb.getSheetAt(sheetIndex);
			int colCount = ws.getRow(0).getLastCellNum();
			return colCount;
		}
	}
	
	public String retrieveToRunFlag(String wsName, String colName, String rowName)
	{
		int sheetIndex = wb.getSheetIndex(wsName);
		int rowNum = retrieveNoofRows(wsName);
		int colNum = retrieveNoofCols(wsName);
		int rowNumber = -1;
		int colNumber = -1;
		if(sheetIndex == -1)
		{
			return "";
		}else {
			HSSFRow SuiteRow = ws.getRow(0);
			for(int i=0;i<colNum;i++)
			{
				if(SuiteRow.getCell(i).getStringCellValue().equals(colName.trim())){
					colNumber = i;
				}
			}
			
			if(colNumber == -1){
				return "";
			}
			
			for(int j=0;j<rowNum;j++){
				HSSFRow SuiteCol = ws.getRow(j);
				if(SuiteCol.getCell(0).getStringCellValue().equals(rowName.trim())){
					rowNumber = j;
				}
			}
			
			if(rowNumber == -1){
				return "";
			}
					
			HSSFRow row = ws.getRow(rowNumber);
			HSSFCell cell = row.getCell(colNumber);
			if(cell == null){
				return "";
			}else{
				String value = cell.getStringCellValue().toString();
				return value;
			}												
		}
	}
		
	
	public boolean writeResult(String wsName, String colName, HashMap<String, String> TestResultTL) {
		int rowNumber = 0;
		String Result = null;
		try {
			int sheetIndex = wb.getSheetIndex(wsName);
			if (sheetIndex == -1)
				return false;
			int colNum = retrieveNoofCols(wsName);
			int colNumber = -1;
			int rowNum = retrieveNoofRows(wsName);

			for (String testCaseName : TestResultTL.keySet()) {
				if (TestResultTL.get(testCaseName).equalsIgnoreCase("PASS")) {
					Result = "PASS";
				} else if (TestResultTL.get(testCaseName).equalsIgnoreCase("FAIL")) {
					Result = "FAIL";
				} else if (TestResultTL.get(testCaseName).equalsIgnoreCase("SKIP")) {
					Result = "SKIP";
				}

				for (int i = 0; i < rowNum; i++) {
					HSSFRow Suiterow = ws.getRow(i);
					if (Suiterow.getCell(0).getStringCellValue().equals(testCaseName)) {
						rowNumber = i;
					}
				}

				HSSFRow Suiterow = ws.getRow(0);
				for (int i = 0; i < colNum; i++) {
					if (Suiterow.getCell(i).getStringCellValue().equals(colName.trim())) {
						colNumber = i;
					}
				}

				if (colNumber == -1) {
					return false;
				}

				HSSFRow Row = ws.getRow(rowNumber);
				HSSFCell cell = Row.getCell(colNumber);
				if (cell == null)
					cell = Row.createCell(colNumber);

				cell.setCellValue(Result);

				if (Result.equalsIgnoreCase("PASS")) {
					HSSFCellStyle style = wb.createCellStyle();
					// style.setFillForegroundColor(HSSFColor.GREEN.index);
					style.setFillForegroundColor(IndexedColors.GREEN.getIndex());

					style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
					cell.setCellStyle(style);

					HSSFFont font = wb.createFont();
					// font.setColor(HSSFColor.BLACK.index);
					font.setColor(IndexedColors.BLACK.getIndex());

					style.setFont(font);

					cell.setCellStyle(style);
				} else if (Result.equalsIgnoreCase("FAIL")) {
					HSSFCellStyle style = wb.createCellStyle();
					/*
					 * style.setFillForegroundColor(HSSFColor.RED.index);
					 * style.setFillBackgroundColor(HSSFColor.RED.index);
					 * style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					 * cell.setCellStyle(style); HSSFFont font =
					 * wb.createFont(); font.setColor(HSSFColor.BLACK.index);
					 * style.setFont(font);
					 * 
					 * cell.setCellStyle(style);
					 */
					style.setFillForegroundColor(IndexedColors.RED.getIndex());
					style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
					cell.setCellStyle(style);
					HSSFFont font = wb.createFont();
					// font.setColor(HSSFColor.BLACK.index);
					font.setColor(IndexedColors.BLACK.getIndex());

					style.setFont(font);

					cell.setCellStyle(style);
				} else {
					HSSFCellStyle style = wb.createCellStyle();
					/*
					 * //style.setFillForegroundColor(HSSFColor.YELLOW.index);
					 * style.setFillBackgroundColor(HSSFColor.YELLOW.index);
					 * style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					 * 
					 * HSSFFont font = wb.createFont();
					 * font.setColor(HSSFColor.BLACK.index);
					 * style.setFont(font);
					 * 
					 * cell.setCellStyle(style);
					 */
					style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());

					style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
					cell.setCellStyle(style);

					HSSFFont font = wb.createFont();
					// font.setColor(HSSFColor.BLACK.index);
					font.setColor(IndexedColors.BLACK.getIndex());

					style.setFont(font);

					cell.setCellStyle(style);
				}

				opstr = new FileOutputStream(filelocation);
				wb.write(opstr);
				opstr.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean writeResult1(String wsName, String colName, HashMap<String, String> LoadTime){
		int rowNumber = 0;
		try{
			int sheetIndex=wb.getSheetIndex(wsName);
			if(sheetIndex==-1)
				return false;			
			int colNum = retrieveNoofCols(wsName);
			int colNumber=-1;
			int rowNum = retrieveNoofRows(wsName);	
			
			for (String testCaseName : LoadTime.keySet()) {
				for (int i = 0; i < rowNum; i++) {
					HSSFRow Suiterow = ws.getRow(i);
					if(Suiterow.getCell(0).getStringCellValue().equals(testCaseName)){
						rowNumber = i;
					}
				}
				
				HSSFRow Suiterow = ws.getRow(0);			
				for(int i=0; i<colNum; i++){				
					if(Suiterow.getCell(i).getStringCellValue().equals(colName.trim())){
						colNumber=i;					
					}					
				}
				
				if(colNumber==-1){
					return false;				
				}
				
				HSSFRow Row = ws.getRow(rowNumber);
				HSSFCell cell = Row.getCell(colNumber);
				if (cell == null)
			        cell = Row.createCell(colNumber);					
				cell.setCellValue(LoadTime.get(testCaseName));
			}
			
			
			opstr = new FileOutputStream(filelocation);
			wb.write(opstr);
			opstr.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean writeResult2(String wsName, String colName, int rowNumber, int Result){
		try{
			int sheetIndex=wb.getSheetIndex(wsName);
			if(sheetIndex==-1)
				return false;			
			int colNum = retrieveNoofCols(wsName);
			int colNumber=-1;
					
			
			HSSFRow Suiterow = ws.getRow(0);			
			for(int i=0; i<colNum; i++){				
				if(Suiterow.getCell(i).getStringCellValue().equals(colName.trim())){
					colNumber=i;					
				}					
			}
			
			if(colNumber==-1){
				return false;				
			}
			
			HSSFRow Row = ws.getRow(rowNumber);
			HSSFCell cell = Row.getCell(colNumber);
			if (cell == null)
		        cell = Row.createCell(colNumber);	
			
			//DecimalFormat df = new DecimalFormat("##.##");
			//df.format(Result);
			//cell.setCellValue(Result);
			
			cell.setCellValue(Result);
			
			opstr = new FileOutputStream(filelocation);
			wb.write(opstr);
			opstr.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public String getEnvUrl(String wsName, String Environment) throws IOException
	{
		try {
			int rowNum = retrieveNoofRows(wsName);
			//int colNum = retrieveNoofCols(wsName);
			int rowNumber = -1;
			//int colNumber = -1;
			String CellData = "";
			for(int i = 0;i<rowNum;i++){
				HSSFRow row = ws.getRow(i);			
				if(row.getCell(0).getStringCellValue().equalsIgnoreCase(Environment)){
					rowNumber = i;
					break;
				}			
			}
			
			HSSFCell Cell = ws.getRow(rowNumber).getCell(1);
			if (Cell == null)
			{			   
				System.out.println("Cell is empty");
				Reporter.log("Cell is empty");
				
			} else if (Cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
			{
				CellData = Cell.getStringCellValue(); 								
			}else if (Cell.getCellType() == HSSFCell.CELL_TYPE_BLANK)
			{
				System.out.println("Cell is empty");
				Reporter.log("Cell is empty");								
			}
			
			return CellData;
			
		} catch (Exception e) {
			return "";
		}
				
	}
	
	public HashMap<String, String> getEnvUrl(String wsName)
    {
        try {
            int rowNum = retrieveNoofRows(wsName);
            //int colNum = retrieveNoofCols(wsName);                                     
            HashMap<String, String> URLList = new HashMap<>();
            for(int i = 0;i<rowNum;i++){
                HSSFRow row = ws.getRow(i);                                                                     
                if(row.getCell(2).getStringCellValue().equalsIgnoreCase("Y")){                                  
                        //System.out.println(row.getCell(1).getStringCellValue());
                        URLList.put(row.getCell(0).getStringCellValue(),row.getCell(1).getStringCellValue());
                }                                                                                                                                                                       
            }                                                                                       
            return URLList;                    
        } catch (Exception e) {
            return null;
        }                            
    }


    
    public ArrayList<HashMap<String, String>> getLoginCredentials(String wsName, String Role){
           try {
                  int rowNum = retrieveNoofRows(wsName);
                  //int colNum = retrieveNoofCols(wsName);
                  ArrayList<HashMap<String, String>> credentials = new ArrayList<>();
                  HashMap<String, String> users = new HashMap<>();
                  for(int i = 0;i<rowNum;i++){
                        HSSFRow row = ws.getRow(i); 
                        if(row.getCell(0).getStringCellValue().equalsIgnoreCase(Role)){
                               if(row.getCell(3).getStringCellValue().equalsIgnoreCase("Y")){                                  
                                      users.put("username", row.getCell(1).getStringCellValue());
                                      users.put("password", row.getCell(2).getStringCellValue());
                                      credentials.add(users);
                               }      
                        }
                        
                        
                  }
                  //credentials.add(users);
                  return credentials;               
           } catch (Exception e) {
                  return null;
           }
    }

	
	
	public String getRole(String wsName, String TestCaseName) throws IOException
	{
		try {
			int rowNum = retrieveNoofRows(wsName);
			int colNum = retrieveNoofCols(wsName);
			int rowNumber = -1;
			int colNumber = -1;
			String CellData = "";
			for(int i = 0;i<rowNum;i++){
				HSSFRow row = ws.getRow(i);			
				if(row.getCell(0).getStringCellValue().equalsIgnoreCase(TestCaseName)){
					rowNumber = i;
					break;
				}			
			}
			
			for(int i = 0;i<colNum;i++){
				HSSFRow row = ws.getRow(i);			
				if(row.getCell(0).getStringCellValue().equalsIgnoreCase("Role")){
					colNumber = i;
					break;
				}			
			}
			
			
			HSSFCell Cell = ws.getRow(rowNumber).getCell(colNumber);
			if (Cell == null)
			{			   
				System.out.println("Cell is empty");
				Reporter.log("Cell is empty");
				
			} else if (Cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
			{
				CellData = Cell.getStringCellValue(); 								
			}else if (Cell.getCellType() == HSSFCell.CELL_TYPE_BLANK)
			{
				System.out.println("Cell is empty");
				Reporter.log("Cell is empty");								
			}
			
			return CellData;
			
		} catch (Exception e) {
			return "";
		}
				
	}
	
	
	
}
