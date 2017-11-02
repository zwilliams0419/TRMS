package com.revature.trms;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConnectionFactory
{	
	private static Connection conn;
	private static Logger log = Logger.getRootLogger();
	
	//Returns the existing connection if it is valid. Otherwise, returns a new one
	public static Connection getConnection() throws SQLException {
		if(conn != null) {
			if(conn.isValid(2)) {
				return conn;
			}
			else {
				conn.close();
			}
		}
		
		String endpoint, port, sid, username, password;
		
		/*Properties p = loadProperties();
		endpoint = p.getProperty("endpoint", "jdbc:oracle:thin:@trms.cir40oqtm5cv.us-east-2.rds.amazonaws.com");
		port = p.getProperty("port", "1521");
		sid = p.getProperty("sid", "ORCL");
		username = p.getProperty("username", "master");
		password = p.getProperty("password", "MASTER!!");*/
		
		endpoint = "jdbc:oracle:thin:@trms.cir40oqtm5cv.us-east-2.rds.amazonaws.com:1521:ORCL";
		port = "1521";
		sid = "ORCL";
		username = "master";
		password = "MASTER!!";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn = DriverManager.getConnection(
				endpoint,
				username, password);
		
		return conn;
	}
	
	private static Properties loadProperties() {
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream("connection.properties"));
			return prop;
		} catch (Exception e) {
			//e.printStackTrace();
			log.warn("properties file not found");
			return prop;
		}
	}
}
