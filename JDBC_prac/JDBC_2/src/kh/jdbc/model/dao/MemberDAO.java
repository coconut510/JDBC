package kh.jdbc.model.dao;

import java.sql.*;
import java.util.ArrayList;

import kh.jdbc.model.vo.Member;

public class MemberDAO {
	public ArrayList<Member> showAllMember()
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "select * from member";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
					
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			while(rset.next())
			{
				Member m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPwd(rset.getString("member_pwd"));
				m.setMemberName(rset.getString("member_name"));
				m.setAge(rset.getInt("age"));
				m.setGender(rset.getString("gender"));
				m.setEmail(rset.getString("email"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setPhone(rset.getString("phone"));
				m.setEnrollDate(rset.getDate("enroll_date"));
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
	
	
	public Member selectMemberrId(String memberId)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		
		// 기존방식 STATEMENT
//		String query = "SELECT * FROM MEMBER WHERE MEMBER_ID = '" + memberId + "'"; 
		
		// PREPARED STATEMENT 방식.(? 라는 위치 홀도로 표시)
		String query = "SELECT * FROM MEMBER WHERE MEMBER_ID =?";		

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			// Connection 객체를 이용하여 preparedStatement 객체를
			// 생성함! preparedStatement 는 생성할 때 query를 담고 생성
			pstmt = conn.prepareStatement(query);	
			
			// setString 메소드를 이용하여 '?' 홀도로 인해 미완성되어 있는 query 를
			// 최종적으로 완성 시킴
			pstmt.setString(1, memberId);
			
			// preparedStatement 방식도 executeQuery 메소드를 이용하여
			// 쿼리문을 전송 및 실행 시킴(단, query 문은 담지 않음)
			// 실행시킨 결과(ResultSet) 은 rset에 담아 처리(기존과 동일)
			rset = pstmt.executeQuery();
			
			if(rset.next())
			{
				m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPwd(rset.getString("member_pwd"));
				m.setMemberName(rset.getString("member_name"));
				m.setAge(rset.getInt("age"));
				m.setGender(rset.getString("gender"));
				m.setEmail(rset.getString("email"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setPhone(rset.getString("phone"));
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return m;
	}
	
	public ArrayList<Member> selectMemberName(String memberName)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Member> list = new ArrayList<Member>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			String query = "SELECT * FROM MEMBER WHERE MEMBER_NAME LIKE ?";// ? =>    '%'길동'%'
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" +memberName + "%");
			
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				Member m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPwd(rset.getString("member_pwd"));
				m.setMemberName(rset.getString("member_name"));
				m.setAge(rset.getInt("age"));
				m.setGender(rset.getString("gender"));
				m.setEmail(rset.getString("email"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setPhone(rset.getString("phone"));
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}


	public int insertNewMember(Member m) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query  = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPwd());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			
			result = pstmt.executeUpdate();
			
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return result;
	}


	public int updateMember(Member m) {
		Connection conn =null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			String query = "UPDATE MEMBER SET (MEMBER_PWD, EMAIL,PHONE,ADDRESS) = (SELECT ?,?,?,? FROM DUAL)"
					+ "WHERE MEMBER_ID=?";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, m.getMemberPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getMemberId());

			result = pstmt.executeUpdate();
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
				pstmt.close();
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
		PreparedStatement pstmt = null;
		int result = 0;
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			String query = "DELETE FROM MEMBER WHERE MEMBER_ID = ?";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberId);

			result = pstmt.executeUpdate();
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
}
