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

public class Trainer extends Profile implements User{
	private String type;
	
	@Override
	public boolean login(String userId,String password) {
		boolean isLogin=false;
		String query="SELECT * FROM trainer "
				+ "WHERE tr_email=? AND tr_password=?";
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
				
				id=rs.getInt("tr_id");
				name=rs.getString("tr_name");
				phoneNumber=rs.getString("tr_phone_number");
				email=rs.getString("tr_email");
				address=rs.getString("tr_address");
				type=rs.getString("tr_type");
				
				return isLogin;
			}else {
				return isLogin;
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
		//Actions of logout
		return true;
	}
	
	public List<Appointment> viewBooking() {
		List<Appointment> bookingList=new ArrayList<>();
		String query="SELECT * FROM appointment "
				+ "INNER JOIN client ON appointment.cl_id=client.cl_id "
				+ "INNER JOIN pet ON appointment.pt_id=pet.pt_id "
				+ "WHERE tr_id="+id;
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
				
				client.setId(rs.getInt("cl_id"));
				client.setName(rs.getString("cl_name"));
				client.setPhoneNumber(rs.getString("cl_phone_number"));
				client.setEmail(rs.getString("cl_email"));
				client.setAddress(rs.getString("cl_address"));
				
				pet.setId(rs.getInt("pt_id"));
				pet.setName(rs.getString("pt_name"));
				pet.setType(rs.getString("pt_type"));
				pet.setBreed(rs.getString("pt_breed"));
				pet.setDob(LocalDate.parse(rs.getString("pr_dob"),DateTimeFormatter.ofPattern("dd-MM-yyyy")));
				
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
	
	public boolean updateAppointmentStatus(String status,int appointmentId) {
		boolean isUpdated=false;
		int result=0;
		
		String query="UPDATE appointment SET ap_status=? WHERE ap_id=?";
		Connection conn=JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		
		try {
			ps=conn.prepareStatement(query);
			ps.setString(1, status);
			ps.setInt(2, appointmentId);
			
			result=ps.executeUpdate();
			if(result==1)
				isUpdated=true;
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
		
		return isUpdated;
	}
	
	//Getters and Setters
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
