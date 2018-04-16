package kh.jdbc.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kh.jdbc.board.common.Template;
import kh.jdbc.board.model.vo.Member;

public class MemberDAO {

	public ArrayList<Member> memberAll(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		try {
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM MEMBER";
			
			rset = stmt.executeQuery(query);
			while(rset.next())
			{
				Member m = new Member();
				m.setMember_idx(rset.getInt("member_no"));
				m.setMember_id(rset.getString("member_id"));
				m.setMember_pwd(rset.getString("member_pwd"));
				m.setMember_name(rset.getString("member_name"));
				m.setEmail(rset.getString("email"));
				m.setAddress(rset.getString("address"));
				m.setPhone(rset.getString("phone"));
				m.setEnroll_date(rset.getDate("enroll_date"));
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			Template.close(rset);
			Template.close(stmt);
		}
		return list;
	}

	public Member memberSearchId(Connection conn, String id) {
		Statement stmt = null;
		ResultSet rset = null;
		Member m = null;
		try {
			stmt = conn.createStatement();

			String query = "SELECT * FROM MEMBER WHERE MEMBER_ID = '" + id +"'";
			rset = stmt.executeQuery(query);
			if(rset.next())
			{
				m = new Member();
				m.setMember_idx(rset.getInt("member_no"));
				m.setMember_id(rset.getString("member_id"));
				m.setMember_pwd(rset.getString("member_pwd"));
				m.setMember_name(rset.getString("member_name"));
				m.setEmail(rset.getString("email"));
				m.setAddress(rset.getString("address"));
				m.setPhone(rset.getString("phone"));
				m.setEnroll_date(rset.getDate("enroll_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			Template.close(rset);
			Template.close(stmt);
		}
		return m;
	}

	public ArrayList<Member> memberSearchName(Connection conn, String name) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		try {
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM MEMBER WHERE MEMBER_NAME LIKE '%" + name + "%'";
			
			rset = stmt.executeQuery(query);
			
			while(rset.next())
			{
				Member m = new Member();
				m.setMember_idx(rset.getInt("member_no"));
				m.setMember_id(rset.getString("member_id"));
				m.setMember_pwd(rset.getString("member_pwd"));
				m.setMember_name(rset.getString("member_name"));
				m.setEmail(rset.getString("email"));
				m.setAddress(rset.getString("address"));
				m.setPhone(rset.getString("phone"));
				m.setEnroll_date(rset.getDate("enroll_date"));
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			Template.close(rset);
			Template.close(stmt);
		}
		return list;
	}

	public int memberJoin(Connection conn, Member m) {
		PreparedStatement stmt = null;
		int result = 0;
		String query ="INSERT INTO MEMBER VALUES(MEMBER_IDX.NEXTVAL,?,?,?,?,?,?,SYSDATE)";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, m.getMember_id());
			stmt.setString(2, m.getMember_pwd());
			stmt.setString(3, m.getMember_name());
			stmt.setString(4, m.getEmail());
			stmt.setString(5, m.getAddress());
			stmt.setString(6, m.getPhone());
			
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int memberModify(Connection conn, Member m) {
		PreparedStatement stmt = null;
		int result = 0;
		String query = "UPDATE MEMBER SET (EMAIL,PHONE,ADDRESS) = (SELECT ?,?,? FROM DUAL)"
				+ " WHERE MEMBER_ID  = ?";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, m.getEmail());
			stmt.setString(2, m.getPhone());
			stmt.setString(3, m.getAddress());
			stmt.setString(4, m.getMember_id());
			
			result = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int memberSignOut(Connection conn, String id) {
		Statement stmt = null;
		int result = 0;
		try {
			stmt = conn.createStatement();
			String query = "DELETE FROM MEMBER WHERE MEMBER_ID = '"	+id + "'";
			result = stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
