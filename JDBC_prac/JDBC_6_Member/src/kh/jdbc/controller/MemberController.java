package kh.jdbc.controller;

import java.util.ArrayList;

import kh.jdbc.Exception.MemberException;
import kh.jdbc.model.dao.MemberDAO;
import kh.jdbc.model.service.MemberService;
import kh.jdbc.model.vo.Member;

public class MemberController {

	private MemberService mservice = new MemberService();
	public ArrayList<Member> showAllMember() {
		ArrayList<Member> list = new ArrayList<Member>();
		try {
			list= mservice.showAllMember();
		} catch (MemberException e) {
			System.out.println("Controller 전체 회원 조회 실패");
			System.out.println(e.getMessage());
		}			
		return list;
	}
	public Member selectMemberId(String memberId) {
		return mservice.selectMemberrId(memberId);
	}
	public ArrayList<Member> selectMemberName(String name) {
		return mservice.selectMemberName(name);
	}
	public int insertNewMember(Member m)	 {
		return mservice.insertNewMember(m);
	}
	public int updateMember(Member m) {
		return mservice.updateMember(m);
	}
	public int deleteMember(String memberId) {
		return mservice.deletemember(memberId);
	}
	
}