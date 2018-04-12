package kh.library.model.dao;

import java.sql.*;
import java.util.ArrayList;

import kh.library.common.JDBCTemplate;
import kh.library.model.vo.Member;

public class MemberDAO {
//	private JDBCTemplate templete = new JDBCTemplate();
	public ArrayList<Member> memberAllShow(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		try {
			
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM customer ORDER BY 1";
			
			rset = stmt.executeQuery(query);
			
			while(rset.next())
			{
				Member m = new Member();
				m.setUser_no(rset.getInt("USER_NO"));
				m.setUser_id(rset.getString("user_id"));
				m.setUser_name(rset.getString("user_name"));
				m.setUser_age(rset.getInt("user_age"));
				m.setAddr(rset.getString("addr"));
				m.setGender(rset.getString("gender"));
				m.setEnroll_date(rset.getDate("enroll_date"));
				list.add(m);
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

	public ArrayList<Member> memberSearchName(Connection conn, String name) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		try {
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM CUSTOMER WHERE USER_NAME LIKE '%"+name +"%'";
			
			rset = stmt.executeQuery(query);
			
			while(rset.next())
			{
				Member m = new Member();
				m.setUser_no(rset.getInt("USER_NO"));
				m.setUser_id(rset.getString("user_id"));
				m.setUser_name(rset.getString("user_name"));
				m.setUser_age(rset.getInt("user_age"));
				m.setAddr(rset.getString("addr"));
				m.setGender(rset.getString("gender"));
				m.setEnroll_date(rset.getDate("enroll_date"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		
		return list;
		
	}

	public Member memberSearchId(Connection conn, String id) {
		Statement stmt = null;
		ResultSet rset = null;
		Member m = null;
		try {
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM CUSTOMER WHERE USER_ID = '" + id + "'";
			
			rset = stmt.executeQuery(query);
			
			if(rset.next())
			{
				m = new Member();
				m.setUser_no(rset.getInt("USER_NO"));
				m.setUser_id(rset.getString("user_id"));
				m.setUser_name(rset.getString("user_name"));
				m.setUser_age(rset.getInt("user_age"));
				m.setAddr(rset.getString("addr"));
				m.setGender(rset.getString("gender"));
				m.setEnroll_date(rset.getDate("enroll_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return m;
		
	}

	public int joinMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String query = "INSERT INTO CUSTOMER VALUES((SELECT MAX(USER_NO) + 1 FROM CUSTOMER),?,?,?,?,?,SYSDATE)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getUser_id());
			pstmt.setString(2, m.getUser_name());
			pstmt.setInt(3, m.getUser_age());
			pstmt.setString(4, m.getAddr());
			pstmt.setString(5, m.getGender());
			
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

	public int updateMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String query = "UPDATE CUSTOMER SET (USER_NAME,ADDR) = (SELECT ?,? FROM DUAL) WHERE USER_ID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getUser_name());
			pstmt.setString(2, m.getAddr());
			pstmt.setString(3, m.getUser_id());
			
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

	public boolean bookRentChk(Connection conn, String id)
	{
		Statement stmt = null;
		int result = 0;
		String query = "SELECT USER_ID FROM LIBRARY WHERE USER_ID  = '" + id +"'";
		
		try {
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		finally
		{
			JDBCTemplate.close(stmt);
		}
		return result<1;
	}
	
	
	public int signOutMember(Connection conn, String id) {
		Statement stmt = null;
		int result = 0;
		
		try {
			
			stmt = conn.createStatement();
			
			String query = "DELETE CUSTOMER WHERE USER_ID = '" +id + "'";
			
			result = stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			JDBCTemplate.close(stmt);
		}
		return result;
	}
}
