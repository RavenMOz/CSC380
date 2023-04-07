package application.storage.testcases;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static void main(String[] args) {
		Result r = JUnitCore.runClasses(JUnitCases.class);
		
		for (Failure fail : r.getFailures()) {  
	         System.out.println(fail.toString());  
	    }  
	          
	    System.out.println(r.wasSuccessful()); 
	}
	
}
