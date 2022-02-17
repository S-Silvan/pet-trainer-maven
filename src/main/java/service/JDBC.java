package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
	private static JDBC jdbc;
	private static Connection connection;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/pettrainer","root","");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static JDBC getInstance() {
		if(jdbc==null)
			jdbc=new JDBC();
		return jdbc;
	}
	public Connection getConnection() {
		return connection;
	}
}
