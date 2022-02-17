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

public class Client extends Profile implements User{
	private Pet pet;
	private Booking booking=new Booking();
	
	//Getter and Setter
	public Pet getPet() {
		return pet;
	}
	public void setPet(Pet pet) {
		this.pet = pet;
	}
	
	public boolean bookAppointment(String date,int slot) {
		boolean isBooked=false;
		
		isBooked=booking.confirmBooking(date,slot,id,pet.getId(),pet.getType());
		
		return isBooked;
	}
	public boolean cancelBooking(int bookingId) {
		boolean isCancelled=false;
		
		isCancelled=booking.cancelBooking(bookingId);
		
		return isCancelled;
	}
	public List<Appointment> viewBooking() {
		List<Appointment> bookingList=new ArrayList<>();
		String query="SELECT * FROM appointment "
				+ "INNER JOIN trainer ON appointment.tr_id=trainer.tr_id "
				+ "WHERE cl_id="+id;
		Connection conn=JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps=conn.prepareStatement(query);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				Appointment appointment=new Appointment();
				Trainer trainer=new Trainer();
				
				trainer.setId(rs.getInt("tr_id"));
				trainer.setName(rs.getString("tr_name"));
				trainer.setPhoneNumber(rs.getString("tr_phone_number"));
				trainer.setEmail(rs.getString("tr_email"));
				trainer.setAddress(rs.getString("tr_address"));
				trainer.setType(rs.getString("tr_type"));
				
				appointment.setPet(pet);
				appointment.setTrainer(trainer);
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
	
	@Override
	public boolean login(String userId,String password) {
		boolean isLogin=false;
		String query="SELECT * FROM client "
				+ "INNER JOIN pet ON client.pt_id=pet.pt_id "
				+ "WHERE cl_email=? AND cl_password=?";
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
				
				id=rs.getInt("cl_id");
				name=rs.getString("cl_name");
				phoneNumber=rs.getString("cl_phone_number");
				email=rs.getString("cl_email");
				address=rs.getString("cl_address");
				
				pet=new Pet();
				pet.setId(rs.getInt("pt_id"));
				pet.setName(rs.getString("pt_name"));
				pet.setDob(LocalDate.parse(rs.getString("pt_dob"), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
				pet.setType(rs.getString("pt_type"));
				pet.setBreed(rs.getString("pt_breed"));
				
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
		//Action of Logout
		return true;
	}
	
	
}
