package com.selenium.sqldatabase.testing;


import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DataBaseTestingTestCases {

	//DataBaseConnector dbconn = new DataBaseConnector();
	//Test to verify Employee ID '1' has Employee Name 'Swati'
	@Test(priority = 1)
	public void testVerifySpecificRecord(){
		String sqlQuery = "SELECT FirstName FROM EmployeeDetails WHERE Emp_id=1";
		String expectedName = "Swati";
		
		//Getting the Employee name by Employee id
		String actualEmpNameById = DataBaseConnector.executeSQLQuery("QA", sqlQuery);
		System.out.println("Employee name reterived from database : " + actualEmpNameById);
		Assert.assertEquals(actualEmpNameById, expectedName);
		
	}
	
	@Test(priority = 2)
	public void testVerifyListOfRecord(){
		boolean flag = false;
		List<String> listofDBValues = new ArrayList<String>();
		String sqlQuery = "SELECT FirstName FROM EmployeeDetails";
		String expectedName = "Swati";
		
		//Getting the Employee name from the table.
		listofDBValues = DataBaseConnector.executeSQLQuery_List("QA", sqlQuery);
		for(String strName: listofDBValues){
			if(strName.equalsIgnoreCase(expectedName)){
				flag = true;
				break;				
			}
		}
		
        Assert.assertTrue(flag, "Retrieved values are not matching with Expected values");
	}
	
	

}
