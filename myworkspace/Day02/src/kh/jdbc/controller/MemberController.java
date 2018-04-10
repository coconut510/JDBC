package kh.jdbc.controller;

import java.util.ArrayList;

import kh.jdbc.model.dao.MemberDAO;
import kh.jdbc.model.vo.Member;

public class MemberController {

	private MemberDAO mdao = new MemberDAO();
	public ArrayList<Member> showAllMember() {
		return mdao.showAllMember();
	}
	public Member selectMemberId(String memberId) {
		return mdao.selectMemberrId(memberId);
	}
	public ArrayList<Member> selectMemberName(String name) {
		return mdao.selectMemberName(name);
	}
	public int insertNewMember(Member m)	 {
		return mdao.insertNewMember(m);
	}
	public int updateMember(Member m) {
		return mdao.updateMember(m);
	}
	public int deleteMember(String memberId) {
		return mdao.deletemember(memberId);
	}
	
}
