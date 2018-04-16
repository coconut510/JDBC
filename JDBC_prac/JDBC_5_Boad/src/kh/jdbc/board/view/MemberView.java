package kh.jdbc.board.view;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

import kh.jdbc.board.controller.MemberController;
import kh.jdbc.board.model.vo.Member;

public class MemberView {
	private MainView mv;
	private MemberController mc = new MemberController();
	private Scanner sc =new Scanner(System.in);
	public MemberView() {}
	public MemberView(MainView mv )
	{
		this.mv = mv;
	}
	public void startMemberView() {
		while(true) 
		{
			System.out.println("= = = = = = 회원관리 서브 메뉴 = = = = = =");
			System.out.println("1. 전체회원조회 ");
			System.out.println("2. 회원 아이디로 조회 ");
			System.out.println("3. 회원 이름으로 조회 ");
			System.out.println("4. 회원 가입");
			System.out.println("5. 회원 정보수정(주소, 전화번호, 이메일) ");
			System.out.println("6. 회원탈퇴 ");
			System.out.println("7. 메인메뉴로 ");
			System.out.print("메뉴 입력 -> ");
			switch(sc.nextInt())
			{
				case 1: memberAll(); break;
				case 2: memberSearchId();break;
				case 3: memberSearchName();break;
				case 4: memberJoin();break;
				case 5: memberModify();break;
				case 6: memberSignOut();break;
				case 7: backToMain();break;
			}
		}
	}
	private void backToMain() {
		mv.startMainView();
	}
	private void memberSignOut() {
		System.out.println("탈퇴할 회원의 아이디를 입력하세요.");
		String id = sc.next();
		if(!memberIdChk(id))
		{
			int result = mc.memberSignOut(id);
			if(result>0)System.out.println(id+" 회원탈퇴에 성공했습니다.");
			else System.out.println("회원탈퇴를 실패했습니다.");
		}
		else System.out.println(id + " 회원을 찾을수 없습니다.");
	}
	private void memberModify() {
		System.out.print("수정할 회원의 아이디를 입력하세요.");
		String id = sc.next();
		if(!memberIdChk(id))
		{
			Member m = new Member();
			m.setMember_id(id);
			sc.nextLine();
			
			System.out.print("수정할 주소를 입력하세요 ");
			m.setAddress(sc.nextLine());
			
			System.out.print("수정할 전화번호를 입력하세요 ");
			m.setPhone(sc.next());
			
			System.out.println("수정할 이메일을 입력하세요");
			m.setEmail(sc.next());
			
			mc.memberModify(m);
		}
		else System.out.println(id + " 회원을 찾을수 없습니다.");
	}
	public boolean memberIdChk(String id)
	{
		return mc.memberSearchId(id)==null; 
	}
	private void memberJoin() {
		System.out.println("회원가입");
		Member m = new Member();

		System.out.println("가입할 아이디를 입력하세요 : ");
		m.setMember_id(sc.next());
		if(memberIdChk(m.getMember_id())) 
		{

			System.out.println("비밀번호를 입력하세요 : ");
			m.setMember_pwd(sc.next());

			System.out.println("이름를 입력하세요 : ");
			m.setMember_name(sc.next());

			System.out.println("이메일을 입력하세요 : ");
			m.setEmail(sc.next());

			sc.nextLine();
			System.out.println("주소를 입력하세요 : ");
			m.setAddress(sc.nextLine());

			System.out.println("폰번호를 입력하세요 : ");
			m.setPhone(sc.next());
			
			mc.memberJoin(m);
		}
		else
		{
			System.out.println(m.getMember_id() + "는 이미 사용중인 아이디 입니다.");
		}

	}
	private void memberSearchName() {
		System.out.println("찾으려는 이름을 입력하세요 -> ");
		String name = sc.next();
		ArrayList<Member> list = mc.memberSearchName(name);
		if(list.size()>0)
		{
			for(Member m : list)
			{
				System.out.println(m.toString());
			}
		}
		else System.out.println(name + " 이름을 가진  회원이 없습니다.");
	}
	private void memberSearchId() {
		System.out.print("찾으려는 아이디를 입력하세요 -> ");
		String id = sc.next();
		Member m = mc.memberSearchId(id);
		if(m !=null) System.out.println(m.toString());
		else System.out.println("찾는 아이디가 없습니다.");
	}
	private void memberAll() {
		ArrayList<Member> list = mc.memberAll();
		System.out.println("전체 회원");
		for(Member m : list)
		{
			System.out.println(m.toString());
		}
	}



}
