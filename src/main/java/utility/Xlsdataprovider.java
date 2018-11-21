package utility;

import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;



public class Xlsdataprovider {
	static Xlsdataprovider objXls = new Xlsdataprovider();
	
	@DataProvider(name="Regression")
	public static Object[][] Containssanityfetchdata(Method m)
	{
		FetchExcelDataSet excelDatSet = new FetchExcelDataSet();
		
		Object[][] dataset = excelDatSet.getDataSetAsObjectArray(System.getProperty("user.dir")+"\\src\\main\\resources\\excelfiles\\RegressionSh_Tests.xls", "Regression",m.getName());
		/*Object[][] dataset = excelDatSet.getDataSetAsObjectArray(objXls.getClass().getClassLoader().getResource("excelfiles/Dashboard_Tests.xls").getFile().toString(), "Time_Load",m.getName());*/
		return dataset;
	}
	

	
	

}
