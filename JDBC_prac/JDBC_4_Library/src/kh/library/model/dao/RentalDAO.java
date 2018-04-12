package kh.library.model.dao;

import java.sql.*;
import java.util.ArrayList;

import kh.library.common.JDBCTemplate;
import kh.library.model.vo.RentalInfo;

public class RentalDAO {
	public ArrayList<RentalInfo> rentalAllShow(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<RentalInfo> list = new ArrayList<RentalInfo>();
		
		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		
		return list;
	}

	public ArrayList<RentalInfo> rentalSearchId(Connection conn, String id) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<RentalInfo> list = new ArrayList<RentalInfo>();
		try {
			
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return list;
	}

	public ArrayList<RentalInfo> rentalSearchBook(Connection conn, String bookName) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<RentalInfo> list = new ArrayList<RentalInfo>();
		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		
		return list;
	}

	public int rentalAdd(Connection conn, RentalInfo r) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String query = "INSERT INTO LIBRARY VALUES((SELECT MAX(LEASE_NO) + 1 FROM LIBRARY), ?,?,SYSDATE,SYSDATE)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, r.getBook_no());
			pstmt.setString(2, r.getUser_id());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
