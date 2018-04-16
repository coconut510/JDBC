package kh.jdbc.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import static kh.jdbc.common.MemberTemplete.*;
import kh.jdbc.model.vo.Member;

public class MemberDAO {
	private Properties prop = new Properties();
	public MemberDAO() {
			try {
			prop.load(new FileReader("resource/query.properties"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}
	}
	public ArrayList<Member> selectAll(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = prop.getProperty("selectAll");
		
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
			close(rset);
			close(stmt);
		}
		return list;
	}

	public Member selectMemberId(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		try {
			String query =prop.getProperty("selectOne");

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			close(rset);
			close(pstmt);
		}
		return m;
	}

	public int deletemember(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String query = prop.getProperty("delete");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			
			result = pstmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Member> selectMemberName(Connection conn, String name) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		try {
			String query = prop.getProperty("selectSearchName") ;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertNewMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String query = prop.getProperty("insert");
			
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
			close(pstmt);
		}
		return result;
	}

	public int insertDelMember(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String query = prop.getProperty("deleteInInsert");

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
		}
		return result;
	}

	public int updateMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String query = prop.getProperty("update");
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
			close(pstmt);
		}
		return result;
	}
}
