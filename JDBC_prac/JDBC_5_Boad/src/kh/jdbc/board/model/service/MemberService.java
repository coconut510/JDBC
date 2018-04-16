package kh.jdbc.board.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import kh.jdbc.board.common.Template;
import kh.jdbc.board.model.dao.MemberDAO;
import kh.jdbc.board.model.vo.Member;

public class MemberService {
	private MemberDAO mdao = new MemberDAO();
	public ArrayList<Member> memberAll() {
		Connection conn = Template.getConnection();
		ArrayList<Member> list = new ArrayList<Member>();
		list = mdao.memberAll(conn);
		Template.close(conn);
		return list;
	}
	public Member memberSearchId(String id) {
		Connection conn = Template.getConnection();
		Member m =  mdao.memberSearchId(conn,id);
		Template.close(conn);
		return m;
	}
	public ArrayList<Member> memberSearchName(String name) {
		Connection conn =Template.getConnection();
		ArrayList<Member> list =  mdao.memberSearchName(conn, name);
		Template.close(conn);
		return list ;
	}
	public int memberJoin(Member m) {
		Connection conn = Template.getConnection();
		int result   = mdao.memberJoin(conn,m);
		if(result>0) Template.commit(conn);
		else Template.rollback(conn);
		Template.close(conn);
		return result;
	}
	public int memberModify(Member m) {
		Connection conn = Template.getConnection();
		int result = mdao.memberModify(conn,m);
		if(result>0) Template.commit(conn);
		else Template.rollback(conn);
		Template.close(conn);
		return result;
	}
	public int memberSignOut(String id) 
	{
		Connection conn = Template.getConnection();
		int result = mdao.memberSignOut(conn,id);
		if(result>0) Template.commit(conn);
		else Template.rollback(conn);
		Template.close(conn);
		return result;
	}

}
