package kh.java.jdbc.controller;

import java.util.ArrayList;

import kh.java.jdbc.model.dao.MemberDAO;
import kh.java.jdbc.model.vo.Member;

public class MemberController {
	private MemberDAO  mdao = new MemberDAO();
	public MemberController() {}// 기본 생성자
	
	// View 에서 요청이 들어온 작업은
	// Contrroller 에서 DAO로 요청 작업을 해야 함	
	public ArrayList<Member> selectAll()
	{
		ArrayList<Member> list =mdao.selectAll();
		// DAO에서 넘어온 List가 비워져 있다면.
		if(list.isEmpty()) return null; // null 리턴
		else return list;// list객체 리턴.
	}
	public Member selectOneId(String id)
	{
		return mdao.selectOneId(id);
	}
	public ArrayList<Member> selectOneName(String memberName)
	{
		ArrayList<Member> list= mdao.selectOneName(memberName);
		return list;
	}

	public int insertMember(Member m) {
		return mdao.insertMember(m);
	}

	public int updateMember(Member m) {
		return mdao.updatemember(m);
	}

	public int deleteMember(String memberId) {
		return	mdao.deleteMember(memberId);
	}
}
