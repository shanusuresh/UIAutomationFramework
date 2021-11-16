package com.weathershopper.listeners;


import com.weathershopper.constants.FrameworkConstants;
import com.weathershopper.utils.ExcelUtil;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implements {@link org.testng.IMethodInterceptor} to leverage the abstract methods
 * Mostly used to read the data from excel and decides on which tests needs to run.
 * 
 * <pre>Please make sure to add the listener details in the testng.xml file</pre>
 */
public class MethodInterceptor implements IMethodInterceptor{
	
	/**
	 * Intercepts the existing test methods and changes the annotation value at the run time
	 * Values are fetched from the excel sheet.
	 * User has to choose yes/No in the RunManager sheet.
	 * Suppose if there are 3 tests named a,b,c needs to be run, it reads the excel and understand user wants to 
	 * run only a and c, then it will return the same after performing the comparisons.
	 */

	@Override
	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {

		//loginandlogouttest and newTest

		List<Map<String, String>> list = ExcelUtil.getTestDetails(FrameworkConstants.getRunmangerDatasheet());
		List<IMethodInstance> result = new ArrayList<>();

		for(int i=0;i<methods.size();i++) {
			for(int j=0;j<list.size();j++) {
				if(methods.get(i).getMethod().getMethodName().equalsIgnoreCase(list.get(j).get("testname")) &&
					list.get(j).get("execute").equalsIgnoreCase("yes")) {
						methods.get(i).getMethod().setDescription((list.get(j).get("testdescription")));
						methods.get(i).getMethod().setInvocationCount(Integer.parseInt(list.get(j).get("count")));
						methods.get(i).getMethod().setPriority(Integer.parseInt(list.get(j).get("priority")));
						result.add(methods.get(i));
					}
				
			}
		}
		
		return result;
	}

}
