package com.weathershopper.utils;

import com.weathershopper.constants.FrameworkConstants;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Holds the data provider for all the test cases in the framework.
 *
 */
public final class DataProviderUtils {

	private DataProviderUtils() {}
	
	private static List<Map<String, String>> list =	new ArrayList<>();
	
	
	/**
	 * Acts as a data provider for all the test cases.
	 * Parallel=true indicates that each of the iteration will be ran in parallel.
	 */
	@DataProvider(parallel=true)
	public static Object[] getData(Method m) {
		String testname = m.getName();
		if(list.isEmpty()) {
			list = ExcelUtil.getTestDetails(FrameworkConstants.getIterationDatasheet());
			System.out.println(list);
		}
		
		List<Map<String, String>> smalllist = new ArrayList<>();
		
		for(int i=0;i<list.size();i++) {
			if(list.get(i).get("testname").equalsIgnoreCase(testname) &&  
					list.get(i).get("execute").equalsIgnoreCase("yes")) {
						smalllist.add(list.get(i));
			}
		}
		System.out.println(smalllist);
		return smalllist.toArray();
		
	}
	
}
