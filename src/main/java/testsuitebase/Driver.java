package testsuitebase;

import java.util.List;

import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

public class Driver {
	static Driver objDrv = new Driver();
		
	public static void main(String[] args) {		
		TestNG testng = new TestNG();
		String xmlfilename = System.getProperty("user.dir")+"\\src\\main\\resources\\testng.xml";	
		/*String xmlfilename = objDrv.getClass().getClassLoader().getResource("testng.xml").getFile().toString();*/ 
		List<XmlSuite> suite;
		try {
			suite = (List<XmlSuite>)(new Parser(xmlfilename).parse());
			testng.setXmlSuites(suite);
			testng.run();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
