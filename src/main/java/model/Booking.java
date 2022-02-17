package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import service.BookingStatus;
import service.JDBC;

public class Booking{
	public boolean checkSlotAvailability(String date,String type,int slot) {
		boolean isSlotAvailable=false;
		String query="SELECT * FROM appointment"
				+ " INNER JOIN trainer ON appointment.tr_id=trainer.tr_id"
				+ " WHERE tr_type='"+type+"' AND ap_date='"+date+"' AND ap_slot="+slot;
		Connection conn = JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps=conn.prepareStatement(query);
			rs=ps.executeQuery();
			
			if(rs.next())
				return isSlotAvailable;
			else
				return true;
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
		
		return isSlotAvailable;
	}
	public int assignTrainer(String type) {
		int trainerId=0;
		String query="SELECT * FROM trainer"
				+ " WHERE tr_type='"+type+"'";
		Connection conn = JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps=conn.prepareStatement(query);
			rs=ps.executeQuery();
			
			if(rs.next())
				trainerId=rs.getInt("tr_id");
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
		
		return trainerId;
	}
	public int addAppointment(String date,int slot,int clientId,int trainerId,int petId) {
		int appointmentId=0;
		String query="INSERT INTO"
				+ " appointment (ap_date,ap_slot,cl_id,tr_id,pt_id)"
				+" VALUES(?,?,?,?,?)";
		Connection conn=JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		
		try {
			ps=conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1,date);
			ps.setInt(2,slot);
			ps.setInt(3,clientId);
			ps.setInt(4,trainerId);
			ps.setInt(5,petId);
			
			ps.executeUpdate();
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
			generatedKeys.next();
			appointmentId=generatedKeys.getInt(1);
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
		
		return appointmentId;
	}
	public boolean addBooking(int appointmentId,int trainerId,int clientId,int petId) {
		boolean isBooked=false;
		String query="INSERT INTO"
				+ " booking (bk_status,ap_id,cl_id,tr_id,pt_id)"
				+ " VALUES (?,?,?,?,?)";
		Connection conn=JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		
		try {
			ps=conn.prepareStatement(query);
			
			ps.setString(1,BookingStatus.Booked.toString());
			ps.setInt(2,appointmentId);
			ps.setInt(3,clientId);
			ps.setInt(4,trainerId);
			ps.setInt(5,petId);
			
			int result=ps.executeUpdate();
			if(result==1)
				isBooked=true;
			else
				isBooked=false;
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
		
		return isBooked;
	}
	public boolean confirmBooking(String date,int slot,int clientId,int petId,String petType) {
		boolean isSlotAvailable=false;
		boolean isBooked=false;
		
		isSlotAvailable=checkSlotAvailability(date,petType,slot);
		if(isSlotAvailable) {
			int trainerId=assignTrainer(petType);
			if(trainerId!=0) {
				int appointmentId=addAppointment(date,slot,clientId,trainerId,petId);
				if(appointmentId!=0) 
					isBooked=addBooking(appointmentId,trainerId,clientId,petId);
			}	
		}
		
		return isBooked;
	}
	public boolean cancelBooking(int bookingId) {
		boolean isCanceled=false;
		
		String query="SELECT * FROM appointment"
				+ " WHERE ap_id='"+bookingId+"'";
		Connection conn = JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps=conn.prepareStatement(query);
			rs=ps.executeQuery();
			
			if(rs.next())
				if(!rs.getString("ap_status").equalsIgnoreCase(BookingStatus.Completed.toString())) {
					String queryu="UPDATE appointment SET ap_status='"+BookingStatus.Canceled+"' WHERE ap_id="+bookingId;
					PreparedStatement psu=conn.prepareStatement(queryu);
					int result=psu.executeUpdate();
					if(result==1)
						isCanceled=true;
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
		
		return isCanceled;
	}
}
