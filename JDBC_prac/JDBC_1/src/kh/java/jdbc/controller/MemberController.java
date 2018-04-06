package kh.java.jdbc.controller;

import kh.java.jdbc.model.dao.MemberDAO;

public class MemberController {
	public MemberController() {}// 기본 생성자
	
	// View 에서 요청이 들어온 작업은
	// Contrroller 에서 DAO로 요청 작업을 해야 함
	
	public void selectAll()
	{
		new MemberDAO().selectAll();
	}
}
