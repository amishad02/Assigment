package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

import testsuitebase.TestResultStatus;

public class FetchExcelDataSet {
	HashMap<Integer, LinkedHashMap<String, String>> hashDataSet = new HashMap<Integer, LinkedHashMap<String, String>>();
	public ArrayList<HashMap<String, String>> xlsxDataSet = null;
	public HashMap<String, ArrayList<String>> xlsxDataSet2 = null;
	 int countBefore=0;
	 int countAfter=0;
	 public XSSFSheet sheet = null;
	    public XSSFRow row = null;
	    public XSSFCell cell = null;
	public HashMap<Integer, LinkedHashMap<String, String>> makeTestData(String strExcelPath, String sheetName, String testCaseName) {
		FetchExcelDataSet fetchDataSet = new FetchExcelDataSet();
		HSSFSheet excelSheet = null;
		try{
			FileInputStream excelfilestream = new FileInputStream(strExcelPath);
			@SuppressWarnings("resource")
			HSSFWorkbook excelWorkbook = new HSSFWorkbook(excelfilestream);
			excelSheet = excelWorkbook.getSheet(sheetName);
			int numRows = excelSheet.getLastRowNum();
			int columnIndex = -1;
			for(int count=0; count < excelSheet.getRow(0).getLastCellNum(); count++) {
				if(excelSheet.getRow(0).getCell(count).getStringCellValue().equalsIgnoreCase("TestCaseName")) {
					columnIndex=count;
					break;
				}
			}
			/*for(int rowCount=1, validRows=1; rowCount<=numRows; rowCount++) {
				if(excelSheet.getRow(rowCount).getCell(columnIndex).getStringCellValue().equalsIgnoreCase(testCaseName)&& 					
						excelSheet.getRow(rowCount).getCell(0).getStringCellValue().equalsIgnoreCase("Y")) {
					hashDataSet.put(validRows-1, fetchDataSet.getRowData(excelSheet, rowCount));
					validRows++;
				}
			}*/
			for(int rowCount=1, validRows=1; rowCount<=numRows; rowCount++) {
				if(excelSheet.getRow(rowCount).getCell(columnIndex).getStringCellValue().equalsIgnoreCase(testCaseName)) {
					hashDataSet.put(validRows-1, fetchDataSet.getRowData(excelSheet, rowCount));
					validRows++;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return hashDataSet;
	}

	private LinkedHashMap<String, String> getRowData(HSSFSheet excelSheet,int rowCount) {
		LinkedHashMap<String, String> hashRowData = new LinkedHashMap<String, String>();
		HSSFRow headerRow = excelSheet.getRow(0);
		HSSFRow row = excelSheet.getRow(rowCount);
		int totalInputValues = row.getLastCellNum();
		for(int cellCount=0; cellCount<totalInputValues; cellCount++) {
			HSSFCell headerCell = headerRow.getCell(cellCount);
			HSSFCell cell = row.getCell(cellCount, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cell.setCellType(CellType.STRING);
			String cellValue = cell.getStringCellValue();
			hashRowData.put(headerCell.getStringCellValue(), cellValue);
		}
		
		return hashRowData;
	}
	
	private LinkedHashMap<String, String> getData(HashMap<Integer, LinkedHashMap<String, String>> hashMap, int rowNumber) {
		LinkedHashMap<String, String> hashData = null;
		hashData = hashMap.get(rowNumber);
		return hashData;
	}
	
	public Object[][] getDataSetAsObjectArray(String strExcelPath, String sheetName, String testCaseName) {
		HashMap<Integer, LinkedHashMap<String, String>> hashDataSet = makeTestData(strExcelPath, sheetName, testCaseName);
		
		Object[][] objArray = new Object[hashDataSet.size()][1];
		for(int i=0; i<hashDataSet.size(); i++) {
			objArray[i][0] = getData(hashDataSet, i);
		}
		return objArray;
	}
	
	@SuppressWarnings("resource")
	public ArrayList<HashMap<String, String>> getXlsxData(String strExcelPath, String sheetName) throws IOException {		
		XSSFSheet excelSheet = null;		
		FileInputStream excelfilestream = new FileInputStream(strExcelPath);		
		XSSFWorkbook excelWorkbook = new XSSFWorkbook(excelfilestream);
		excelSheet = excelWorkbook.getSheet(sheetName);
		int numRows = excelSheet.getLastRowNum();				
		HashMap<String, String>  hashRowData = null;
		XSSFRow headerRow = excelSheet.getRow(1);
		int totalInputValues = headerRow.getLastCellNum();
		xlsxDataSet = new ArrayList<HashMap<String,String>>();
		for (int i = 2; i <= numRows; i++) {	
			hashRowData = new HashMap<String, String>();
			XSSFRow row = excelSheet.getRow(i);
			for(int count=0; count < totalInputValues; count++) {				
				XSSFCell headerCell = headerRow.getCell(count);
				XSSFCell cell = row.getCell(count);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String cellValue = cell.getStringCellValue();
				hashRowData.put(headerCell.getStringCellValue(), cell.getStringCellValue());					
			}		
			xlsxDataSet.add(hashRowData);
		}																				
		return xlsxDataSet;				
	}
	
	@SuppressWarnings("resource")
	public HashMap<String, ArrayList<String>> getXlsxData2(String strExcelPath, String sheetName) throws IOException {		
		XSSFSheet excelSheet = null;		
		FileInputStream excelfilestream = new FileInputStream(strExcelPath);		
		XSSFWorkbook excelWorkbook = new XSSFWorkbook(excelfilestream);
		excelSheet = excelWorkbook.getSheet(sheetName);
		int numRows = excelSheet.getLastRowNum();
		//int numRows = 21;
		ArrayList<String>  hashRowData = null;
		XSSFRow headerRow = excelSheet.getRow(1);
		int totalInputValues = headerRow.getLastCellNum();
		xlsxDataSet2 = new HashMap<>();
		for (int i = 0; i < totalInputValues;) {
			hashRowData = new ArrayList<>();
			for(int j=2; j <= numRows; j++) {	
				XSSFRow row = excelSheet.getRow(j);				
				XSSFCell cell = row.getCell(i);	
				DataFormatter df = new DataFormatter();			
				/*if(cell == null){
					cellValue = "";
				}else if(cell.getCellType() == XSSFCell.CELL_TYPE_BLANK){
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cellValue = "";
				}else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cellValue = cell.getStringCellValue();
				}*/	
				/*cell.setCellType(Cell.CELL_TYPE_STRING);
				String cellValue = cell.getStringCellValue();*/
				String cellValue = df.formatCellValue(cell);
				if(cellValue.length() == 0){
					cellValue = "blank";
				}
				hashRowData.add(cellValue);
			}	
			i = i+1;
			xlsxDataSet2.put("column"+i,hashRowData);
		/*for (int i = 2; i <= numRows; i++) {				
			XSSFRow row = excelSheet.getRow(i);
			for(int count=0; count < totalInputValues; count++) {	
				hashRowData = new ArrayList<>();
				XSSFCell cell = row.getCell(count);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String cellValue = cell.getStringCellValue();	
				hashRowData.add(cellValue);
			}		
			xlsxDataSet2.add(hashRowData);
		}*/																										
		}
		return xlsxDataSet2;
		
	}
	
	
	public int readExcel(String Path , String Sheet,int colName,int rowno) throws IOException
	{   int agrement_count =0;
		
		 try
	        {
		
		     File src=new File(Path);
			int col_Num = -1;
			FileInputStream fis=new FileInputStream(src);
		    // load the workbook
		   XSSFWorkbook wb=new XSSFWorkbook(fis);
		  // get the sheet which you want to modify or create
		 
		     XSSFSheet sheetName= wb.getSheetAt(0);
			  int i= sheetName.getLastRowNum();
		
		  System.out.println(" last row " +i);
		  
		   if(i<1){
		     System.out.println("Sheet Cannot be empty");
		
		   }

		   else
		   {
			  
			   agrement_count= (i-rowno);
			   System.out.println(" no of  AGREMENT "+  agrement_count + " Total no of row:   "+i);
		   }
		   
		   
		/*   row = sheetName.getRow(2);
		 
		 
          for(int j = 0; j< row.getLastCellNum(); j++)
        {    
        	  String col=row.getCell(j).getStringCellValue().toString();
        	  if(row.getCell(j).getStringCellValue().trim().equals(colName.trim()))
                col_Num = j;
           
              
           }
		   
		  for(int k=4;k<=i;k++)
		 {
			  System.out.println(k+"   rowno" +col_Num );
			String data = sheetName.getRow(4).getCell(2).getStringCellValue();
			
			if(data.equals(null) || data.equals(""))
				{
					    count++;
						
				}
			System.out.println(" count is"  + count +" =====data ===" +data );
			
	    }
		*/   
		   
	      } 


	        catch(Exception e)
	        {
	            e.printStackTrace();
	            
	        
	    }
		 return  agrement_count;
	}
	
	public void FetchMovemnetReportData(String Path ,int rowno) throws IOException
	{			 
		
		
			     File src=new File(Path);
				FileInputStream fis=new FileInputStream(src);
			    // load the workbook
			   XSSFWorkbook wb=new XSSFWorkbook(fis);
		       XSSFSheet sheetName= wb.getSheetAt(0);
		         int noOfColumns = sheetName.getRow(1).getLastCellNum();
		         int lastrow=sheetName.getLastRowNum();
	           
	            String[] Headers = new String[noOfColumns];
	            String st1= sheetName.getRow(1).getCell(2).toString();
	            System.out.println(noOfColumns +" no of clomns " + lastrow +" last row =="+st1);
	            for (int j=2;j<noOfColumns;j++)
	            {
	                Headers[j] = sheetName.getRow(1).getCell(j).getStringCellValue();
	              //  System.out.println( Headers[j].toString() +" value of header");
	              
	            }
	            
	                for (int a=2;a<noOfColumns;a++){
	                    if(Headers[a].equals("BAD"))
	                    { 
		                    	for(int i=2;i<lastrow;i++)
		                    	{
		                         String BAD_DATA=sheetName.getRow(i).getCell(a).getStringCellValue();
		                         System.out.println(BAD_DATA +" BAD_DATA");
		                     
		                    	}
	                   	}
	                }
	}
	@SuppressWarnings("resource")
	public ArrayList<String> getXlsxHeader(String strExcelPath, String sheetName) throws IOException {		
		XSSFSheet excelSheet = null;		
		FileInputStream excelfilestream = new FileInputStream(strExcelPath);		
		XSSFWorkbook excelWorkbook = new XSSFWorkbook(excelfilestream);
		excelSheet = excelWorkbook.getSheet(sheetName);
		//int numRows = excelSheet.getLastRowNum();
		ArrayList<String> hashRowData = new ArrayList<>();	
		XSSFRow headerRow = excelSheet.getRow(1);
		int totalInputValues = headerRow.getLastCellNum();
		
		for (int i = 0; i < totalInputValues;) {				
			XSSFCell cell = headerRow.getCell(i);	
			DataFormatter df = new DataFormatter();							
			String cellValue = df.formatCellValue(cell);
			if(cellValue.length() == 0){
				cellValue = "blank";
			}
			hashRowData.add(cellValue);
																					
		}
		return hashRowData;
		
	}
	
	@SuppressWarnings("resource")
	public void writeToExcel(String filename, String sheetName, String colName,String data) throws IOException{
		FileInputStream ipstr = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\excelfiles\\"+filename+".xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(ipstr);
		ipstr.close();
		XSSFSheet ws = wb.getSheet(sheetName);
		XSSFRow Suiterow = ws.getRow(0);		
		XSSFRow row;
		XSSFCell cell;
		int numRows = ws.getLastRowNum();
		int colNum = Suiterow.getLastCellNum();
		int colNumber = 0; 
		for(int i=0; i<colNum; i++){				
			if(Suiterow.getCell(i).getStringCellValue().equals(colName.trim())){
				colNumber=i;					
			}					
		}		
		row = ws.createRow(++numRows);
		cell = row.createCell(colNumber);
		cell.setCellValue(data);
		FileOutputStream opstr = new FileOutputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\excelfiles\\"+filename+".xlsx");
		wb.write(opstr);
		opstr.close();
	}
	
	@SuppressWarnings("resource")
	public void writeCompareResultToExcel(String filename, String sheetName,String ID1, String ID2, String mismatchColName
			, String data1, String data2) throws IOException{
		FileInputStream ipstr = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\excelfiles\\"+filename+".xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(ipstr);
		ipstr.close();
		XSSFSheet ws = wb.getSheet(sheetName);
		XSSFRow Suiterow = ws.getRow(0);		
		XSSFRow row;
		XSSFCell cell;
		int numRows = ws.getLastRowNum();
		int colNum = Suiterow.getLastCellNum();
		int colNumber = 0; 
		/*for(int i=0; i<colNum; i++){				
			if(Suiterow.getCell(i).getStringCellValue().equals(colName.trim())){
				colNumber=i;					
			}					
		}	*/	
		row = ws.createRow(++numRows);
		cell = row.createCell(0);
		cell.setCellValue(ID1);
		cell = row.createCell(1);
		cell.setCellValue(ID2);
		cell = row.createCell(2);
		cell.setCellValue(mismatchColName);
		cell = row.createCell(3);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue(data1);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell = row.createCell(4);
		cell.setCellValue(data2);
		FileOutputStream opstr = new FileOutputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\excelfiles\\"+filename+".xlsx");
		wb.write(opstr);
		opstr.close();
	}
	
	public void compareTsv(String file1path, String file2path, String reportName) throws IOException{
		BufferedReader reader = null;
		BufferedReader reader2 = null;
		List<String> Header = new ArrayList<String>();
		List<String> splitHeader1 = new ArrayList<String>();
		List<String> Header2 = new ArrayList<String>();
		List<String> splitHeader2 = new ArrayList<String>();
		List<String> mainHeader = new ArrayList<String>();
		List<String> mainHeader2 = new ArrayList<String>();
		List<String> key1 = new ArrayList<String>();
		List<String> key2 = new ArrayList<String>();
		List<String> ID = new ArrayList<String>();
		List<String> ID2 = new ArrayList<String>();
		List<String> mID = new ArrayList<String>();
		List<String> mID2 = new ArrayList<String>();
		List<String> mHeader = new ArrayList<String>();
		List<String> list = null;
		List<String> list2 = null;
		HashMap<String, List<String>> rowMap = new HashMap<>();
		HashMap<String, List<String>> rowMap2 = new HashMap<>();
		HashMap<String, List<String>> dataMap = new HashMap<>();
		HashMap<String, List<String>> dataMap2 = new HashMap<>();		
		//Xls 1
		try {
			reader = new BufferedReader(new FileReader(file1path));
			String a;
			int count = 0;
			int j = 1;
			String[] arr = null;
			while ((a = reader.readLine())!= null) {				
				arr = a.split("\t");
				if (count>1) {
					if(reportName.equalsIgnoreCase("templateA")){
						ID.add(arr[2].toString());
					}else{
						ID.add(arr[0].toString());
					}
										
				}
				list = new ArrayList<String>();									
				for (int i = 0; i < arr.length; i++) {
					if (count == 0) {
						splitHeader1.add(arr[i].toString());
					}
					if (count == 1) {
						Header.add(arr[i].toString());
					}					
					if (count > 1) {
						String val = null;
						/*if(arr[i] == null){
							val = "blank";
						}else {
							val = arr[i].toString();
						}*/
						if(!arr[i].isEmpty()){
							val = arr[i].toString();
						}else{
							val = "blank";
						}						
						list.add(val);
					}
				}	
				if(count > 1){
					String key = "";
					if(reportName.equalsIgnoreCase("relationship") || reportName.equalsIgnoreCase("TradeSummary") || reportName.equalsIgnoreCase("TradeValidation")){
						if(arr[0].length()>0 ){
							key = arr[0];
						}else{
							key = "Row"+(count+1);
						}
					}
					if(reportName.equalsIgnoreCase("templateA")){
						if(arr[2].length()>0 ){
							key = arr[2];
						}else{
							key = "Row"+(count+1);
						}
					}
					if(reportName.equalsIgnoreCase("templateB")){
						if(arr[0].length()>0 ){
							key = arr[0];
						}else{
							key = "Row"+(count+1);
						}
					}
					if (reportName.equalsIgnoreCase("Obligor")){
						if(arr[0].length()>0 && arr[2].length()>0 ){
							key = arr[0]+"_"+arr[2];
						}else{
							key = "Row"+(count+1);
						}
					}
					if (reportName.equalsIgnoreCase("Facility")){
						try {
							if(arr[4] == null && arr[5] == null && arr[9] == null){
								key = "Row"+(count+1);
							}else if(arr[4].length()>0 && arr[5].length()>0 && arr[9].length()>0){
								key = arr[4]+"_"+arr[5]+"_"+arr[9];
							}/*else{
								key = "Row"+(count+1);
							}*/
						} catch (Exception e) {
							// TODO Auto-generated catch block
							System.out.println(count+2);
							//e.printStackTrace();
						}
					}
					key1.add(key);
					rowMap.put(key, list);
				}
				count++;
				/*rowMap.put("row"+j, list);*/
				j++;
			}	
			System.out.println(ID.size()+" "+rowMap.size());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		if(Header.size() == splitHeader1.size()){
			for (int i = 0; i < Header.size(); i++) {
				if(splitHeader1.get(i).equalsIgnoreCase(Header.get(i))){
					mainHeader.add(splitHeader1.get(i));
				}else{
					mainHeader.add(splitHeader1.get(i)+"_"+Header.get(i));
				}				
			}
		}
		//Xls 2		
		try {
			reader2 = new BufferedReader(new FileReader(file2path));
			String a;
			int count = 0;			
			int j = 1;
			String[] arr = null;
			while ((a = reader2.readLine())!= null) {								
				arr = a.split("\t");
				if (count>1) {					
					if(reportName.equalsIgnoreCase("templateA")){
						ID2.add(arr[2].toString());
					}else{
						ID2.add(arr[0].toString());	
					}
				}
				list2 = new ArrayList<String>();								
				for (int i = 0; i < arr.length; i++) {
					if (count == 0) {
						splitHeader2.add(arr[i].toString());
					}
					if (count == 1) {
						Header2.add(arr[i].toString());
					}	
					if (count > 1) {					
						String val = null;
						if(!arr[i].isEmpty()){
							val = arr[i].toString();
						}else{
							val = "blank";
						}						
						list2.add(val);
					}
				}	
				if(count > 1){
					String key = "";
					if(reportName.equalsIgnoreCase("relationship") || reportName.equalsIgnoreCase("TradeSummary") || reportName.equalsIgnoreCase("TradeValidation")){
						if(arr[0].length()>0 ){
							key = arr[0];
						}else{
							key = "Row"+(count+1);
						}
					}
					if(reportName.equalsIgnoreCase("templateA")){
						if(arr[2].length()>0 ){
							key = arr[2];
						}else{
							key = "Row"+(count+1);
						}
					}
					if(reportName.equalsIgnoreCase("templateB")){
						if(arr[0].length()>0 ){
							key = arr[0];
						}else{
							key = "Row"+(count+1);
						}
					}
					if (reportName.equalsIgnoreCase("Obligor")){
						if(arr[0].length()>0 && arr[2].length()>0 ){
							key = arr[0]+"_"+arr[2];
						}else{
							key = "Row"+(count+1);
						}
					}
					if (reportName.equalsIgnoreCase("Facility")){
						if(count == 120){
							System.out.println("s");
						}
						try {
							if(arr[4].length()>0 && arr[5].length()>0 && arr[9].length()>0){
								key = arr[4]+"_"+arr[5]+"_"+arr[9];
							}else{
								key = "Row"+(count+1);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							System.out.println(count+2);
							//e.printStackTrace();
						}
					}
					key2.add(key);
					rowMap2.put(key, list2);
				}				
				count++;
				/*rowMap2.put("row"+j, list2);*/
				j++;				
			}
			System.out.println(ID2.size()+" "+rowMap2.size());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				reader2.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(Header2.size() == splitHeader2.size()){
			for (int i = 0; i < Header2.size(); i++) {
				if(splitHeader2.get(i).equalsIgnoreCase(Header2.get(i))){
					mainHeader2.add(splitHeader1.get(i));
				}else{
					mainHeader2.add(splitHeader2.get(i)+"_"+Header2.get(i));
				}
				
			}
		}
		
		for (int i = 0; i < ID.size(); i++) {
			if (!ID2.contains(ID.get(i))) {
				/*System.out.println(ID.get(i));*/
				mID.add(ID.get(i));					
			}
		}
		System.out.println(mID.size());	
		for (int i = 0; i < ID2.size(); i++) {
			if (!ID.contains(ID2.get(i))) {
				/*System.out.println(ID.get(i));*/
				mID2.add(ID2.get(i));
			}
		}
		System.out.println(mID2.size());
										
		
		if(Header.size() == Header2.size()){
			if(key2.size()>key1.size()){
				for (int i = 0; i < key2.size(); i++) {			
					String value1 = key2.get(i) ;				
					for (int k = 0; k < key1.size(); k++) {
						String value2 = key1.get(k);
						if (value2.equalsIgnoreCase(value1)) {
							dataMap.put(value2, rowMap.get(value2));
							dataMap2.put(value1, rowMap2.get(value1));
							break;
						}
					}
				}
			}else if(key2.size() == key1.size()){
				for (int i = 0; i < key2.size(); i++) {
					String value1 = key2.get(i) ;
					for (int k = 0; k < key1.size(); k++) {
						String value2 = key1.get(k);
						if (value2.equalsIgnoreCase(value1)) {
							dataMap.put(value2, rowMap.get(value2));
							dataMap2.put(value1, rowMap2.get(value1));
							break;
						}
					}
				}
			}else if(key2.size()<key1.size()){
				for (int i = 0; i < key1.size(); i++) {
					String value1 = key1.get(i) ;
					for (int k = 0; k < key2.size(); k++) {
						String value2 = key2.get(k);
						if (value2.equalsIgnoreCase(value1)) {
							dataMap.put(value1, rowMap.get(value1));
							dataMap2.put(value2, rowMap2.get(value2));
							break;
						}
					}
				}
			}
			System.out.println(dataMap.size()+" "+dataMap2.size());
		}
		
		if(mainHeader.size()>mainHeader2.size()){
			for (int i = 0; i < mainHeader.size(); i++) {
				if (!mainHeader2.contains(mainHeader.get(i))) {
					/*System.out.println(ID.get(i));*/
					mHeader.add(mainHeader.get(i));
				}	
			}
								
		}else if(mainHeader.size()<mainHeader2.size()){
			for (int i = 0; i < mainHeader2.size(); i++) {
				if (!mainHeader.contains(mainHeader2.get(i))) {
					/*System.out.println(ID.get(i));*/
					mHeader.add(mainHeader2.get(i));
				}	
			}			
		}
		
		
		int l =1;
		/*HashMap<String, String> dataMismatch = new HashMap<>();*/
		List<String> dataMismatch = new ArrayList<String>();
		String rowNum1=null,rowNum2=null,com1=null,com2=null;		
		List<String> data1=null,data2=null;
		if(mainHeader.size() == mainHeader2.size()){					
			try {
				for (String key : dataMap.keySet()) {
					data1 = dataMap.get(key);
					data2 = dataMap2.get(key);

					for (int j = 0; j < data1.size(); j++) {
						com1 = data1.get(j).toString();
						com2 = data2.get(j).toString();
						if(!com1.equalsIgnoreCase(com2)){
							/*System.out.println("Data mismatch for "+data1.get(0));
							System.out.println("Mismatch column Name "+mainHeader.get(j));
							System.out.println("data1 "+com1);
							System.out.println("data2 "+com2);*/
							if(reportName.equalsIgnoreCase("Relationship")){								
								dataMismatch.add(key+"|"+mainHeader.get(j)+"|"+com1+"|"+com2);
							}else if (reportName.equalsIgnoreCase("Obligor")) {								
								dataMismatch.add(key+"|"+mainHeader.get(j)+"|"+com1+"|"+com2);
							}else if (reportName.equalsIgnoreCase("Facility")) {
								dataMismatch.add(key+"|"+mainHeader.get(j)+"|"+com1+"|"+com2);
							}else if (reportName.equalsIgnoreCase("TradeSummary")) {
								dataMismatch.add(key+"|"+mainHeader.get(j)+"|"+com1+"|"+com2);
							}else if (reportName.equalsIgnoreCase("TradeValidation")) {
								dataMismatch.add(key+"|"+mainHeader.get(j)+"|"+com1+"|"+com2);
							}else if (reportName.equalsIgnoreCase("templateA")) {
								dataMismatch.add(key+"|"+mainHeader.get(j)+"|"+com1+"|"+com2);
							}else if (reportName.equalsIgnoreCase("templateB")) {
								dataMismatch.add(key+"|"+mainHeader.get(j)+"|"+com1+"|"+com2);
							}
							
							TestResultStatus.Testfail = true;
						}
					}					
					/*System.out.println(l+" row comparison completed");*/
					l++;
				}
			} catch (Exception e) {	
				if(reportName.equalsIgnoreCase("relationship") || reportName.equalsIgnoreCase("TradeSummary") || reportName.equalsIgnoreCase("TradeValidation")){
					System.out.println("Issue in comparing: : "+data1.get(0));
				}
				if (reportName.equalsIgnoreCase("Obligor")){
					System.out.println("Issue in comparing: : "+data1.get(0)+"_"+data1.get(2));
				}
				if (reportName.equalsIgnoreCase("Facility")){
					System.out.println("Issue in comparing: : "+data1.get(4)+"_"+data1.get(5)+"_"+data1.get(9));					
				}if (reportName.equalsIgnoreCase("templateA")) {
					System.out.println("Issue in comparing: : "+data1.get(2));
				}if (reportName.equalsIgnoreCase("templateB")) {
					System.out.println("Issue in comparing: : "+data1.get(0));
				}
				
				e.printStackTrace();
			}
		}else{
			System.out.println("Column count mismatch in Excel. PROD column count : "+Header.size()+" UAT column count : "+Header2.size());
		}
		
		System.out.println("Total rows compared: "+l);
		if(mHeader.size()>0){
			/*File file = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\excelfiles\\mHeader.tsv");*/
			File file = new File(this.getClass().getClassLoader().getResource("excelfiles/mHeader.tsv").getFile());
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for (String string : mHeader) {
				bw.write(string);
				bw.write("\t");
				bw.write("\n");			
			}
			bw.close();
			if(mainHeader.size()>mainHeader2.size()){
				if(file2path.contains("UAT")){
					reportLog("mHeader", "MissingColumn_UAT_"+reportName, "tsv");
				}else if(file2path.contains("SIT")){
					reportLog("mHeader", "MissingColumn_SIT_"+reportName, "tsv");
				}else if(file2path.contains("PROD")){
					reportLog("mHeader", "MissingColumn_PROD_"+reportName, "tsv");
				}				
			}else if(mainHeader.size()<mainHeader2.size()){
				if(file1path.contains("UAT")){
					reportLog("mHeader", "MissingColumn_UAT_"+reportName, "tsv");
				}else if(file1path.contains("SIT")){
					reportLog("mHeader", "MissingColumn_SIT_"+reportName, "tsv");
				}	else if(file1path.contains("PROD")){
					reportLog("mHeader", "MissingColumn_PROD_"+reportName, "tsv");
				}				
			}
			
		}
		
		if(mID.size()>0){
			/*File file = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\excelfiles\\mIDPROD.tsv");*/
			File file = new File(this.getClass().getClassLoader().getResource("excelfiles/mIDPROD.tsv").getFile());
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for (String string : mID) {
				bw.write(string);
				bw.write("\t");
				bw.write("\n");			
			}
			/*bw.flush();*/
			bw.close();
			if(file2path.contains("UAT")){
				reportLog("mIDPROD", "MissingRecord_UAT_"+reportName, "tsv");
			}else if(file2path.contains("SIT")){
				reportLog("mIDPROD", "MissingRecord_SIT_"+reportName, "tsv");
			}	else if(file2path.contains("PROD")){
				reportLog("mIDPROD", "MissingRecord_PROD_"+reportName, "tsv");
			}	
			/*reportLog("mIDPROD", "MissingRecord_UAT_"+reportName, "tsv");*/
		}
		
		if(mID2.size()>0){
			/*File file3 = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\excelfiles\\mIDUAT.tsv");*/
			File file3 = new File(this.getClass().getClassLoader().getResource("excelfiles/mIDUAT.tsv").getFile());
			BufferedWriter bw3 = new BufferedWriter(new FileWriter(file3));
			for (String string : mID2) {
				bw3.write(string);
				bw3.write("\t");
				bw3.write("\n");			
			}
			/*bw.flush();*/
			bw3.close();
			if(file1path.contains("UAT")){
				reportLog("mIDUAT", "MissingRecord_UAT_"+reportName, "tsv");
			}else if(file1path.contains("SIT")){
				reportLog("mIDUAT", "MissingRecord_SIT_"+reportName, "tsv");
			}	else if(file1path.contains("PROD")){
				reportLog("mIDUAT", "MissingRecord_PROD_"+reportName, "tsv");
			}
			/*reportLog("mIDUAT", "MissingRecord_PROD_"+reportName, "tsv");*/
		}
				
		
		if(dataMismatch.size()>0){
			/*File file2 = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\excelfiles\\dataMismatch.tsv");*/
			File file2 = new File(this.getClass().getClassLoader().getResource("excelfiles/dataMismatch.tsv").getFile());
			BufferedWriter bw2 = new BufferedWriter(new FileWriter(file2));
			/*for (String string : dataMismatch.keySet()) {
				String data = dataMismatch.get(string);
				String arrStr[]=data.split("\\|");
				for (int j = 0; j < arrStr.length; j++) {
					bw2.write(arrStr[j]);
					bw2.write("\t");
				}			
				bw2.write("\n");			
			}	*/
			for (int i = 0; i < dataMismatch.size(); i++) {
				String data = dataMismatch.get(i);
				String arrStr[]=data.split("\\|");
				for (int j = 0; j < arrStr.length; j++) {
					bw2.write(arrStr[j]);
					bw2.write("\t");
				}			
				bw2.write("\n");
			}
			bw2.close();
			reportLog("dataMismatch", "DataMismatch_"+reportName, "tsv");
		}		
	
	  }
	
	
	public int  fileCount(){
		
	  File dir = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\excelfiles\\");
	   countBefore= dir.listFiles().length;
	   System.out.println(countBefore    +"   countBefore ");
	     return countBefore;
	}
	public void countDownload(){
		
          System.out.println( " call   count downloaded");
		  File dir = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\excelfiles\\");
		   countAfter= dir.listFiles().length;
		   System.out.println(countAfter    +"   countAfter ");
		    if(countBefore<=countAfter)
		    {
		    Assert.fail(" file not downloaded ");	
		    }
		    
			System.out.println(countBefore  +"  "+ countAfter);
		}
	public String latestFileName(){
		File theNewestFile = null;
		
	    File dir = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\excelfiles\\");
	  	/*File dir = new File(this.getClass().getClassLoader().getResource("excelfiles").getFile());*/
	    FileFilter fileFilter = new WildcardFileFilter("*.xlsx");
	    File[] files = dir.listFiles(fileFilter);
	   int i= dir.listFiles().length;
	     if (files.length > 0) {
	        /** The newest file comes first **/
	       Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
	        theNewestFile = files[0];
	    }
		return theNewestFile.toString();
	}
	
	public void reportLog(String srcFileName, String reportName, String extension){
		/*File srcFile = new File(System.getProperty("user.dir")+"\\src\\excelfiles\\"+srcFileName+".tsv");*/
		File srcFile = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\excelfiles\\"+srcFileName+"."+extension);
		/*File srcFile = new File(this.getClass().getClassLoader().getResource("excelfiles/"+srcFileName+"."+extension).getFile());*/
        /*String destDir = System.getProperty("user.dir")+"\\target\\reportlog";*/
		String destDir = "reportlog";
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
        String destFile = reportName+" - "+dateFormat.format(new Date()) + "."+extension;
        try {
                FileUtils.copyFile(srcFile, new File(destDir + "/" + destFile));
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } 
	}
	
}
