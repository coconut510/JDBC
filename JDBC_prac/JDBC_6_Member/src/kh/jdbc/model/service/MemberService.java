package kh.jdbc.model.service;

import java.sql.*;
import java.util.ArrayList;

import kh.jdbc.Exception.MemberException;
import kh.jdbc.common.MemberTemplete;
import kh.jdbc.model.dao.MemberDAO;
import kh.jdbc.model.vo.Member;

public class MemberService {
// DAO는 DB에 접근하여 데이터를 가져오거나 삽입, 삭제 등의 행위를 하는
// 역할의 객체 일뿐! DB접속 연결을 하는 역할도 DAO의 역할은 아니다.
// DB에 연결하기 위한 역할은 Service에서 진행
	
	
	public ArrayList<Member> showAllMember() throws MemberException {
		Connection conn = MemberTemplete.getConnection();
		ArrayList<Member> list = new MemberDAO().selectAll(conn);
		MemberTemplete.close(conn);
		return list;
	}

	public Member selectMemberrId(String memberId) {
		Connection conn = MemberTemplete.getConnection();
		MemberDAO dao = new MemberDAO();
		Member 	m = dao.selectMemberId(conn, memberId);
		MemberTemplete.close(conn);
		return m;
	}

	public ArrayList<Member> selectMemberName(String name) {
		Connection conn = MemberTemplete.getConnection();
		MemberDAO dao = new MemberDAO();
		ArrayList<Member> list = dao.selectMemberName(conn, name);
		MemberTemplete.close(conn);
		return list;
	}

	public int insertNewMember(Member m) {
		Connection conn = MemberTemplete.getConnection();
		MemberDAO dao = new MemberDAO();
		int result = dao.insertNewMember(conn, m);
			
		if(result>0) MemberTemplete.commit(conn);
		else MemberTemplete.rollback(conn);
		MemberTemplete.close(conn);
		return result;
	}

	public int updateMember(Member m) {
		Connection conn = MemberTemplete.getConnection();
		MemberDAO dao = new MemberDAO();
		int result = dao.updateMember(conn,m);
		MemberTemplete.close(conn);
		return result;
	}

	public int deletemember(String memberId) {
		// 회원 탈퇴 작업시(탈퇴  Service)
		// 1. 기존 테이블 -> 탈퇴 한 회원 테이블로 복사
		// 2. 기존 테이블에서 삭제.
		Connection conn = MemberTemplete.getConnection();
		MemberDAO dao = new MemberDAO();
		int result = dao.deletemember(conn,memberId);
		int result2 = dao.insertDelMember(conn, memberId);
			
		if(result>0 && result2>0) MemberTemplete.commit(conn);
		else MemberTemplete.rollback(conn);
		MemberTemplete.close(conn);
		
		return (result>0 && result2>0)?1:0 ;
	}

}
