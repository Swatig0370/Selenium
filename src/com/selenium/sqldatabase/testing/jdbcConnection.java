
package com.selenium.sqldatabase.testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

public class jdbcConnection {


	@Test
	public void connectionSetup() throws ClassNotFoundException, SQLException{
		
		String conUrl = "jdbc:sqlserver://localhost:1433;"+ "databaseName=TestQA; UserName=sa; Password=Password1;"; 
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		System.out.println("Driver Loaded");
		Connection conJdbc = DriverManager.getConnection(conUrl);
		System.out.println("Database Connected successfully");
		
		Statement st = conJdbc.createStatement();
		
		ResultSet resst = st.executeQuery("SELECT * From EmployeeDetails");
		
		//By default the values will get stored at the base index of the resultset array, 
		//hence we need to point it to the first index.
		 
		 while(resst.next())
		 {
			 if(resst.getString("Emp_id").equalsIgnoreCase("1"))
			 {
			 		 
				 //Fetch the column value.
				 //System.out.println(resst.getString("Emp_id")); //Emp_id is the column name in the db.
				 System.out.println(resst.getString("FirstName"));//FirstName is the column name in the db.
				 String emp_name = resst.getString("FirstName");
				 Assert.assertEquals(emp_name, "Swati", "Recores has been updated successfuly");
				 System.out.println("All the records as per the query displayed");
	 
			 }
		 }
	}
		
}
