package kh.jdbc.controller;

import java.util.ArrayList;

import kh.jdbc.model.dao.MemberDAO;
import kh.jdbc.model.service.MemberService;
import kh.jdbc.model.vo.Member;

public class MemberController {

	private MemberService mservice = new MemberService();
	public ArrayList<Member> showAllMember() {
		return mservice.showAllMember();
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
