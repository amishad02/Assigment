package utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestContext;

public class TimeKeeper implements ISuiteListener{
	public static final String pattern = "dd/MM/yyyy HH:mm:ss SS";
    public static final SimpleDateFormat format = new SimpleDateFormat(pattern);
    
	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ISuite suite) {
		long totalTime = 0;
        for (ISuiteResult eachResult : suite.getResults().values()) {
            ITestContext ctx = eachResult.getTestContext();
            Date start = ctx.getStartDate();
            Date end = ctx.getEndDate();
            long ms = end.getTime() - start.getTime();
            totalTime += ms;
            System.err.println(ctx.getName() + " started at [" + format.format(start) + "] and "
                + "ended at [" + format.format(end) + "] and took [" + ms + "] ms to run.");
        }
        System.err.println("Total time taken = " + (totalTime/1000) + " s");
		
	}
	
}
