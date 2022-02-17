package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import service.JDBC;

public class Visitor {
	public int addPet(String name,String type,String breed,String dob) {
		int result=0;
		String query="INSERT INTO"
				+" pet"
				+" (pt_name,pt_type,pt_breed,pt_dob)"
				+" VALUES(?,?,?,?)";
		Connection conn=JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		
		try {
			ps=conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1,name);
			ps.setString(2,type);
			ps.setString(3,breed);
			ps.setString(4,dob);
			
			ps.executeUpdate();
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
			generatedKeys.next();
			return generatedKeys.getInt(1);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}finally {
			if(ps!=null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return result;
	}
	
	public boolean addClient(String name,String phone,String email,String address,String password,int petId){
		String query="INSERT INTO"
				+" client"
				+" (cl_name,cl_phone_number,cl_email,cl_address,cl_password,pt_id)"
				+" VALUES(?,?,?,?,?,?)";
		Connection conn=JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		
		try {
			ps=conn.prepareStatement(query);
			
			ps.setString(1,name);
			ps.setString(2,phone);
			ps.setString(3,email);
			ps.setString(4,address);
			ps.setString(5,password);
			ps.setInt(6,petId);
			
			int result=ps.executeUpdate();
			if(result==1)
				return true;
			else
				return false;
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}finally {
			if(ps!=null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return false;
	}
}
