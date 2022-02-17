package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import service.JDBC;

public class Admin extends Profile implements User{
	public List<Appointment> viewBooking() {
		List<Appointment> bookingList=new ArrayList<>();
		String query="SELECT * FROM appointment "
				+ "INNER JOIN client ON appointment.cl_id=client.cl_id "
				+ "INNER JOIN pet ON appointment.tr_id=trainer.tr_id "
				+ "INNER JOIN pet ON appointment.pt_id=pet.pt_id ";
		Connection conn=JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps=conn.prepareStatement(query);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				Appointment appointment=new Appointment();
				Client client=new Client();
				Pet pet=new Pet();
				Trainer trainer=new Trainer();
				
				client.setId(rs.getInt("cl_id"));
				client.setName(rs.getString("cl_name"));
				client.setPhoneNumber(rs.getString("cl_phone_number"));
				client.setEmail(rs.getString("cl_email"));
				client.setAddress(rs.getString("cl_address"));
				
				trainer.setId(rs.getInt("tr_id"));
				trainer.setName(rs.getString("tr_name"));
				trainer.setPhoneNumber(rs.getString("tr_phone_number"));
				trainer.setEmail(rs.getString("tr_email"));
				trainer.setAddress(rs.getString("tr_address"));
				trainer.setType(rs.getString("tr_type"));
				
				pet.setId(rs.getInt("pt_id"));
				pet.setName(rs.getString("pt_name"));
				pet.setType(rs.getString("pt_type"));
				pet.setBreed(rs.getString("pt_breed"));
				pet.setDob(LocalDate.parse(rs.getString("pr_dob"),DateTimeFormatter.ofPattern("dd-MM-yyyy")));
				
				appointment.setTrainer(trainer);
				appointment.setPet(pet);
				appointment.setClient(client);
				appointment.setId(rs.getInt("ap_id"));
				appointment.setDate(LocalDate.parse(rs.getString("ap_date"),DateTimeFormatter.ofPattern("dd-MM-yyyy")));
				appointment.setSlot(rs.getInt("ap_slot"));
				appointment.setStatus(rs.getString("ap_status"));
				
				bookingList.add(appointment);
			}
			
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
		
		return bookingList;
	}
	public List<Trainer> viewTrainers() {
		List<Trainer> trainerList=new ArrayList<>();
		String query="SELECT * FROM trainer";
		Connection conn=JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps=conn.prepareStatement(query);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				Trainer trainer=new Trainer();
				
				trainer.setId(rs.getInt("tr_id"));
				trainer.setName(rs.getString("tr_name"));
				trainer.setPhoneNumber(rs.getString("tr_phone_number"));
				trainer.setEmail(rs.getString("tr_email"));
				trainer.setAddress(rs.getString("tr_address"));
				trainer.setType(rs.getString("tr_type"));
				
				trainerList.add(trainer);
			}
			
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
		
		return trainerList;
	}
	public boolean addTrainer(String name,String phoneNumber,String email,String address,String password,String type) {
		boolean isTrainerAdded=false;
		
		String query="INSERT INTO"
				+" trainer"
				+" (tr_name,tr_phone_number,tr_email,tr_address,tr_password,tr_type)"
				+" VALUES(?,?,?,?,?,?)";
		Connection conn=JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		
		try {
			ps=conn.prepareStatement(query);
			
			ps.setString(1,name);
			ps.setString(2,phoneNumber);
			ps.setString(3,email);
			ps.setString(4,address);
			ps.setString(5,password);
			ps.setString(6,type);
			
			int result=ps.executeUpdate();
			if(result==1)
				isTrainerAdded=true;
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
		
		return isTrainerAdded;
	}
	@Override
	public boolean login(String userId, String password) {
		boolean isLogin=false;
		String query="SELECT * FROM admin "
				+ "WHERE ad_email=? AND ad_password=?";
		Connection conn = JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps=conn.prepareStatement(query);
			
			ps.setString(1,userId);
			ps.setString(2,password);
			
			rs=ps.executeQuery();
			if(rs.next()) {
				isLogin=true;
				
				id=rs.getInt("ad_id");
				name=rs.getString("ad_name");
				phoneNumber=rs.getString("ad_phone_number");
				email=rs.getString("ad_email");
				address=rs.getString("ad_address");
			}
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
		
		return isLogin;
	}
	@Override
	public boolean logout() {
		//Action of Logout
		return true;
	}
}
