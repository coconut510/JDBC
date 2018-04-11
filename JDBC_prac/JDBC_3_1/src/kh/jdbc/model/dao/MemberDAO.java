package kh.jdbc.model.dao;

import java.sql.*;
import java.util.ArrayList;

import kh.jdbc.model.vo.Member;

public class MemberDAO {

	public ArrayList<Member> selectAll(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "select * from member";
		
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				rset.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public Member selectMemberId(Connection conn, String memberId) {
		Statement stmt = null;
		ResultSet rset = null;
		Member m = null;
		try {
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM MEMBER WHERE MEMBER_ID = '" + memberId + "'";
			
			rset = stmt.executeQuery(query);
			
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				rset.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return m;
	}

	public int deletemember(Connection conn, String memberId) {
		Statement stmt = null;
		int result = 0;
		try {
			stmt = conn.createStatement();
			
			String query = "DELETE FROM MEMBER WHERE MEMBER_ID = '" + memberId +"'";
			
			result = stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public ArrayList<Member> selectMemberName(Connection conn, String name) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list =new ArrayList<Member>();
		try {
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM MEMBER WHERE MEMBER_NAME LIKE '%" + name + "%'" ;
			
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				rset.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	public int insertNewMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String query = "INSERT INTO MEMBER VALUES (?,?,?,?,?,?,?,?,?,SYSDATE)";
			
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int insertDelMember(Connection conn, String memberId) {
		Statement stmt = null;
		int result = 0;
		
		try {
			stmt = conn.createStatement();
			
			String query = "INSERT INTO DEL_MEMBER (SELECT MEMBER_ID, MEMBER_NAME,PHONE,ENROLL_DATE, SYSDATE FROM MEMBER"
					+ " WHERE MEMBER_ID = '" + memberId +"')";
			
			result = stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String query = "UPDATE MEMBER SET (MEMBER_PWD,EMAIL,PHONE,ADDRESS) = (SELECT ?,?,?,? FROM DUAL)"
					+ " WHERE MEMBER_ID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
}
