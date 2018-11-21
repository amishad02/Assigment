package utility;

import java.util.HashMap;

public class SuiteUtility {	
		
	public static boolean WriteResultUtility(Read_XLS xls, String sheetName, String ColName, HashMap<String, String> TestResultTL){			
		return xls.writeResult(sheetName, ColName, TestResultTL);		 	
	}
	
	
}

