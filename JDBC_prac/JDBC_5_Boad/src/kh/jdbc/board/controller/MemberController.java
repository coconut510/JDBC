package kh.jdbc.board.controller;

import java.util.ArrayList;

import kh.jdbc.board.model.service.MemberService;
import kh.jdbc.board.model.vo.Member;

public class MemberController {
	private MemberService ms = new MemberService();
	public ArrayList<Member> memberAll() {
		return ms.memberAll();
	}
	public Member memberSearchId(String id) {
		return ms.memberSearchId(id);
	}
	public ArrayList<Member> memberSearchName(String name) {
		return ms.memberSearchName(name);
	}
	public int memberJoin(Member m) {
		return ms.memberJoin(m);
	}
	public int memberModify(Member m) {
		return ms.memberModify(m);
	}
	public int memberSignOut(String id) {
		return ms.memberSignOut(id);
	}

}
