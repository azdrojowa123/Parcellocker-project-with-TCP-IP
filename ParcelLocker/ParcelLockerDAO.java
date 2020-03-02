package ParcelLocker;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



public class ParcelLockerDAO {

		private Connection myConn;
		
	public ParcelLockerDAO() throws Exception {
			

			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/locker?serverTimezone=UTC", "student", "student");
			
			System.out.println("DB connection successful to");
	
		}
	
	public void addParcelLocker(ParcelLocker theParcelLocker) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into parcellockers"
					+ " (localhost)"
					+ " values (?)");
			
			// set params
			myStmt.setInt(1,  theParcelLocker.getLocalhost());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt,null);
		}
		
	}
	
	public List<ParcelLocker> getAllParcelLockers() throws Exception {
		List<ParcelLocker> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from parcellockers");
			
			while (myRs.next()) {
				ParcelLocker tempEmployee = convertRowToEmployee(myRs);
				list.add(tempEmployee);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<ParcelLocker> searchEmployees(String lastName) throws Exception {
		List<ParcelLocker> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			lastName += "%";
			myStmt = myConn.prepareStatement("select * from parcellockers where localhost like ?");
			
			myStmt.setString(1, lastName);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				ParcelLocker tempEmployee = convertRowToEmployee(myRs);
				list.add(tempEmployee);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private ParcelLocker convertRowToEmployee(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("id");
		int  localhost = myRs.getInt("localhost");
	
		
		ParcelLocker tempEmployee = new ParcelLocker(localhost);
		
		return tempEmployee;
	}

	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}

	public static void main(String[] args) throws Exception {
		
		ParcelLockerDAO dao = new ParcelLockerDAO();
		System.out.println(dao.searchEmployees("thom"));
		System.out.println(dao.getAllParcelLockers());
	}
}
