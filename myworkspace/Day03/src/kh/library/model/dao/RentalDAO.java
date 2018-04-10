package kh.library.model.dao;

import java.sql.*;
import java.util.ArrayList;

import kh.library.model.vo.RentalInfo;

public class RentalDAO {

	public ArrayList<RentalInfo> rentalAllShow() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<RentalInfo> list = new ArrayList<RentalInfo>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			stmt = conn.createStatement();
			
			String query = "SELECT LEASE_NO , USER_ID, USER_NAME, BOOK_NAME FROM (LIBRARY "																				
																				+ "LEFT JOIN CUSTOMER USING (USER_ID))"
																				+ "LEFT JOIN BOOK USING (BOOK_NO)"
																				+ " ORDER BY 1";			

			rset = stmt.executeQuery(query);
			
		
			while(rset.next())
			{
				RentalInfo r = new RentalInfo();
				r.setLease_no(rset.getInt("lease_no"));
				r.setUser_id(rset.getString("user_id"));
				r.setUser_name(rset.getString("user_name"));
				r.setBook_name(rset.getString("book_name"));
				list.add(r);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	public ArrayList<RentalInfo> rentalSearchId(String id) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		ArrayList<RentalInfo> list = new ArrayList<RentalInfo>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM LIBRARY WHERE USER_ID ='" + id +"'";
			rset = stmt.executeQuery(query);
			
			while(rset.next())
			{
				RentalInfo r  = new RentalInfo();
				r.setLease_no(rset.getInt("lease_no"));
				r.setBook_no(rset.getInt("book_no"));
				r.setUser_id(rset.getString("user_id"));
				r.setLeas_date(rset.getDate("lease_date"));
				r.setReturn_date(rset.getDate("return_date"));
				list.add(r);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList<RentalInfo> rentalSearchBook(String bookName) {
		Connection conn =null;
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<RentalInfo> list = new ArrayList<RentalInfo>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM LIBRARY WHERE BOOK_NO = (SELECT BOOK_NO FROM BOOK WHERE BOOK_NAME = '" + bookName + "')";
			
			rset = stmt.executeQuery(query);
			
			while(rset.next())
			{
				RentalInfo r  = new RentalInfo();
				r.setLease_no(rset.getInt("lease_no"));
				r.setBook_no(rset.getInt("book_no"));
				r.setUser_id(rset.getString("user_id"));
				r.setLeas_date(rset.getDate("lease_date"));
				r.setReturn_date(rset.getDate("return_date"));
				list.add(r);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}

	public int rentalAdd(RentalInfo r) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			stmt = conn.createStatement();
			String query = "INSERT INTO LIBRARY VALUES((SELECT MAX(LEASE_NO) + 1 FROM LIBRARY), "
													+ "'" + r.getBook_no() + "'," 
													+ "'" + r.getUser_id() + "',"
													+ "SYSDATE,SYSDATE)";
			System.out.println(query);
			result = stmt.executeUpdate(query);
			if(result>0) conn.commit();
			else conn.rollback();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
