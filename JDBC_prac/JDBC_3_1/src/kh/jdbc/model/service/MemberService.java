package kh.jdbc.model.service;

import java.sql.*;
import java.util.ArrayList;

import kh.jdbc.model.dao.MemberDAO;
import kh.jdbc.model.vo.Member;

public class MemberService {
// DAO는 DB에 접근하여 데이터를 가져오거나 삽입, 삭제 등의 행위를 하는
// 역할의 객체 일뿐! DB접속 연결을 하는 역할도 DAO의 역할은 아니다.
// DB에 연결하기 위한 역할은 Service에서 진행
	
	
	public ArrayList<Member> showAllMember() {
		Connection conn = null;
		ArrayList<Member> list = new ArrayList<Member>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			conn.setAutoCommit(false);
			list = new MemberDAO().selectAll(conn);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public Member selectMemberrId(String memberId) {
		Connection conn = null;
		Member m = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			conn.setAutoCommit(false);
			MemberDAO dao = new MemberDAO();
			m = dao.selectMemberId(conn, memberId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return m;
	}

	public ArrayList<Member> selectMemberName(String name) {
		Connection conn = null;
		ArrayList<Member> list = new ArrayList<Member>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			conn.setAutoCommit(false);
			MemberDAO dao = new MemberDAO();
			list = dao.selectMemberName(conn, name);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public int insertNewMember(Member m) {
		Connection conn = null;
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			conn.setAutoCommit(false);
			MemberDAO dao = new MemberDAO();
			
			result = dao.insertNewMember(conn, m);
			
			if(result>0) conn.commit();
			else conn.rollback();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateMember(Member m) {
		Connection conn = null;
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			conn.setAutoCommit(false);
			MemberDAO dao = new MemberDAO();
			result = dao.updateMember(conn,m);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deletemember(String memberId) {
		// 회원 탈퇴 작업시(탈퇴  Service)
		// 1. 기존 테이블 -> 탈퇴 한 회원 테이블로 복사
		// 2. 기존 테이블에서 삭제.
		Connection conn = null;
		int result = 0;
		int result2  = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			conn.setAutoCommit(false);
			
			MemberDAO dao = new MemberDAO();
			result2 = dao.insertDelMember(conn, memberId);
			result = dao.deletemember(conn,memberId);
			
			if(result>0 && result2>0) conn.commit();
			else conn.rollback();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return (result>0 && result2>0)?1:0 ;
	}

}
