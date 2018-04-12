package kh.library.controller;

import java.util.ArrayList;

import kh.library.model.dao.MemberDAO;
import kh.library.model.service.MemberService;
import kh.library.model.vo.Member;

public class MemberController {
	private MemberService mservice = new MemberService();
	public ArrayList<Member> memberAllShow() {
		return mservice.memberAllShow();
	}
	public ArrayList<Member> memberSearchName(String name) {
		return mservice.memberSearchName(name);
	}
	public Member memberSearchId(String id) {
		return mservice.memberSearchId(id);
	}
	public int joinMember(Member m) {
		return mservice.joinMember(m);
	}
	public int updateMember(Member m) {
		return mservice.updateMember(m);
	}
	public int signOutMember(String id) {
		return mservice.signOutMember(id);
	}
	public boolean memberRentChk(String id) {
		return mservice.bookRentChk(id);
	}

}
