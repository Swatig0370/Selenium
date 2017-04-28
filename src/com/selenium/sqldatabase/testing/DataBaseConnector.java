package com.selenium.sqldatabase.testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBaseConnector {
	
	private static String dbUserName;
	private static String dbPassword;
	
	 //Should be defined as jdbc:sqlserver://host:port/database name
	private static String databaseURLQA= "jdbc:sqlserver://localhost:1433;" + "databaseName=TestQA;";
    private static String databaseURLDev= "jdbc:sqlserver://localhost:1433;" + "databaseName=TestDev;";
    private static String databaseURLProd= "jdbc:sqlserver://localhost:1433;" + "databaseName=TestProd;";
    
    public static String executeSQLQuery(String testEnv, String sqlQuery){
    	
    	String connectionUrl = "";
    	Connection connection ;
    	
    	String resultValue = "";
    	ResultSet rs ;
    	
    	//To connect to QA Database
    	if(testEnv.equalsIgnoreCase("QA")){
    		connectionUrl = databaseURLQA;
    		dbUserName = "sa";
    		dbPassword = "Password1";
    	}
    	//To connect to Dev Database
    	else if(testEnv.equalsIgnoreCase("Dev")){
    		connectionUrl = databaseURLDev;
    		dbUserName = "sa";
    		dbPassword = "Password1";
    	}
    	//To connect to Prod Database
    	else if(testEnv.equalsIgnoreCase("Prod")){
    		connectionUrl = databaseURLProd;
    		dbUserName = "sa";
    		dbPassword = "Password1";
    	}
    	try{
    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	}catch(ClassNotFoundException e){
    		System.out.println("Error Message: "+ e.getMessage());
    	}
    	
    	try{
    		connection = DriverManager.getConnection(connectionUrl, dbUserName, dbPassword);
    		if (connection != null){
    			
    			System.out.println("Connected to database...");
    		}
    		else{
    			System.out.println("Database connection failed "+testEnv+" Environment");
    		}
    		Statement statemnt = connection.createStatement();
    		rs = statemnt.executeQuery(sqlQuery);
    			
    		try{
    			while(rs.next()){
    				resultValue = rs.getString(1).toString();
    			}
    		}catch(SQLException e){
    			e.printStackTrace();
    		}
    		    		
    		 catch(NullPointerException err){
    			System.out.println("No records obtained from this specific query.");
    			err.printStackTrace();
    		 }
    		finally{
    			if(connection!=null){
    				try{
    					connection.close();
    				}catch(SQLException ex){
    					System.out.println("SQL Exception:" + ex.getStackTrace());
    				}
    			}
    		}
    		
    	}catch(SQLException sqlEx){
    		System.out.println("SQL Exception:" + sqlEx.getStackTrace());
    	}
    	
    	return resultValue;
    }
    
public static ArrayList<String> executeSQLQuery_List(String testEnv, String sqlQuery){
    	
    	String connectionUrl = "";
    	Connection connection ;
    	
    	ArrayList<String> resultValue = new ArrayList<String>();
    	ResultSet rs ;
    	
    	//To connect to QA Database
    	if(testEnv.equalsIgnoreCase("QA")){
    		connectionUrl = databaseURLQA;
    		dbUserName = "sa";
    		dbPassword = "Password1";
    	}
    	//To connect to Dev Database
    	else if(testEnv.equalsIgnoreCase("Dev")){
    		connectionUrl = databaseURLDev;
    		dbUserName = "sa";
    		dbPassword = "Password1";
    	}
    	//To connect to Prod Database
    	else if(testEnv.equalsIgnoreCase("Prod")){
    		connectionUrl = databaseURLProd;
    		dbUserName = "sa";
    		dbPassword = "Password1";
    	}
    	try{
    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	}catch(ClassNotFoundException e){
    		e.printStackTrace();
    	}
    	
    	try{
    		connection = DriverManager.getConnection(connectionUrl, dbUserName, dbPassword);
    		if (connection != null){
    			
    			System.out.println("Connected to database...");
    		}
    		else{
    			System.out.println("Database connection failed "+testEnv+" Environment");
    		}
    		Statement statemnt = connection.createStatement();
    		rs = statemnt.executeQuery(sqlQuery);
    			
    		try{
    			while(rs.next()){
    				//to get the number of columns returned by the query.
    				int columnCount = rs.getMetaData().getColumnCount();
    				StringBuilder stringBuilder = new StringBuilder();
    				for(int iCounter = 1; iCounter <= columnCount;iCounter++){
    					stringBuilder.append(rs.getString(iCounter).trim()+"");
    				}
    				String reqValue = stringBuilder.substring(0, stringBuilder.length());
    				resultValue.add(reqValue);
    			}
    		}catch(SQLException e){
    			e.printStackTrace();
    		}
    		    		
    		 catch(NullPointerException err){
    			System.out.println("No records obtained from this specific query." + err.getStackTrace());
    		 }
    		finally{
    			if(connection!=null){
    				try{
    					connection.close();
    				}catch(SQLException ex){
    					System.out.println("SQL Exception:" + ex.getStackTrace());
    				}
    			}
    		}
    		
    	}catch(SQLException sqlEx){
    		System.out.println("SQL Exception:" + sqlEx.getStackTrace());
    	}
    	
    	return resultValue;
    	
	}
    
    

	
	
}
