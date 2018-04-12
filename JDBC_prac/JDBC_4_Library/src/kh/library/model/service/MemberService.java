package kh.library.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import kh.library.common.JDBCTemplate;
import kh.library.model.dao.MemberDAO;
import kh.library.model.vo.Member;

public class MemberService {
	private MemberDAO mdao = new MemberDAO();
	public ArrayList<Member> memberAllShow() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Member> list = mdao.memberAllShow(conn);
		JDBCTemplate.closeConn(conn);
		return list;
	}
	public ArrayList<Member> memberSearchName(String name) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Member> list = mdao.memberSearchName(conn, name);
		JDBCTemplate.closeConn(conn);
		
		return list;
	}
	public Member memberSearchId(String id) {
		Connection conn = JDBCTemplate.getConnection();
		Member 	m = mdao.memberSearchId(conn,id);
		JDBCTemplate.closeConn(conn);
		return m;
	}
	public int joinMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mdao.joinMember(conn,m);
		
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.closeConn(conn);
		return result;
	}
	public int updateMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int	result = mdao.updateMember(conn,m);
			
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
			
		JDBCTemplate.closeConn(conn);
		return result;
	}
	public int signOutMember(String id) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mdao.signOutMember(conn,id);
			
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.closeConn(conn);
		return result;
	}
	public boolean bookRentChk(String id) {
		Connection conn = JDBCTemplate.getConnection();
		boolean	chk = mdao.bookRentChk(conn,id);
		JDBCTemplate.closeConn(conn);
		return chk;
	}

}
