package kh.library.controller;

import java.util.ArrayList;

import kh.library.model.dao.MemberDAO;
import kh.library.model.vo.Member;

public class MemberController {
	private MemberDAO mdao = new MemberDAO();
	public ArrayList<Member> memberAllShow() {
		return mdao.memberAllShow();
	}
	public ArrayList<Member> memberSearchName(String name) {
		return mdao.memberSearchName(name);
	}
	public Member memberSearchId(String id) {
		return mdao.memberSearchId(id);
	}
	public int joinMember(Member m) {
		return mdao.joinMember(m);
	}

}
