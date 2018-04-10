package kh.jdbc.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kh.jdbc.model.vo.Member;

public class MemberDAO {

	public ArrayList<Member> showAllMember() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM MEMBER";
			rset = stmt.executeQuery(query);
			
			while(rset.next())
			{
				Member m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPwd(rset.getString("member_pwd"));
				m.setMemberName(rset.getString("member_name"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enroll_date"));
				list.add(m);
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

	public Member selectMemberrId(String memberId) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Member m = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM MEMBER WHERE MEMBER_ID ='"+memberId+ "'";
			rset = stmt.executeQuery(query);
			
			if(rset.next())
			{
				m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPwd(rset.getString("member_pwd"));
				m.setMemberName(rset.getString("member_name"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enroll_date"));
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
		return m;
	}

	public ArrayList<Member> selectMemberName(String name) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM MEMBER WHERE MEMBER_NAME LIKE '%"+name + "%'";
			
			rset = stmt.executeQuery(query);
			
			while(rset.next())
			{
				Member m  = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPwd(rset.getString("member_pwd"));
				m.setMemberName(rset.getString("member_name"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enroll_date"));
				list.add(m);
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

	public int insertNewMember(Member m) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			stmt = conn.createStatement();
			
			String query = "INSERT INTO MEMBER VALUES(" + "'" +m.getMemberId() + "'," +
														  "'" +m.getMemberPwd() + "'," +
														  "'" +m.getMemberName() + "'," + 
														  "'" +m.getGender() + "'," + 
														  m.getAge()+ ","+
														  "'" +m.getEmail() + "',"+ 
														  "'" +m.getPhone() + "',"+ 
														  "'" +m.getAddress() + "',"+ 
														  "'" +m.getHobby() + "'," + "SYSDATE)"; 
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

	public int updateMember(Member m) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			stmt = conn.createStatement();
			
			String query = "UPDATE MEMBER "
						+ "SET (member_pwd,phone,email,address) = (SELECT '" + m.getMemberPwd() +"',"+
																		 "'" + m.getPhone() +"'," + 
																		 "'" + m.getEmail() +"'," + 
																		 "'" + m.getAddress() +"' FROM DUAL)"
				 		+ " WHERE member_id = '" + m.getMemberId() +"'";
			
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deletemember(String memberId) {
		Connection conn = null;
		Statement stmt = null;
		int result =0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			String query = "DELETE FROM MEMBER WHERE MEMBER_ID = '" + memberId + "'";
			
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
		
	}

}
