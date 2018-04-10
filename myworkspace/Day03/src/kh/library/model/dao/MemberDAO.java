package kh.library.model.dao;

import java.sql.*;
import java.util.ArrayList;

import kh.library.model.vo.Member;

public class MemberDAO {

	public ArrayList<Member> memberAllShow() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM customer";
			
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
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<Member> memberSearchName(String name) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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

	public Member memberSearchId(String id) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Member m = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
		return m;
		
	}

	public int joinMember(Member m) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			stmt = conn.createStatement();
			
			String query = "INSERT INTO MEMBER VALUES((SELECT MAX(USER_NO) + 1 FROM MEMBER),"
												+	 "'" + m.getUser_id() + "',"
												+	 "'" + m.getUser_name() + "',"
												+	 "" + m.getUser_age() + ","
												+	 "'" + m.getAddr() + "',"
												+	 "'" + m.getGender() + "',"
												+	 "SYSDATE)";
			result = stmt.executeUpdate(query);
			
			if(result>0) conn.commit();
			else conn.rollback();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
