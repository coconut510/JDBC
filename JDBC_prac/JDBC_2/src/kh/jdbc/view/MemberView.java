package kh.jdbc.view;

import java.util.ArrayList;
import java.util.Scanner;

import kh.jdbc.controller.MemberController;
import kh.jdbc.model.vo.Member;

public class MemberView {
	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	public void start() {
			System.out.println("======= 회원 관리 프로그램 =======");
			while(true) 
			{
				System.out.println("1.전체회원 조회");
				System.out.println("2.아이디로 회원 검색");
				System.out.println("3.이름으로 회원 검색");
				System.out.println("4.새로운 회원 가입");
				System.out.println("5.회원 아이디로 비밀번호,이메일,폰번호,주소 변경");
				System.out.println("6.회원 아이디로 탈퇴하기");
				System.out.println("0.종료");
				System.out.print("메뉴입력 ");
				switch(sc.nextInt())
				{
					case 1: showAllMember(); break;
					case 2: selectMemberId(); break;
					case 3: selectMemberName(); break;
					case 4: insertNewMember(); break;
					case 5: updateMember(); break;
					case 6: delteMember(); break;
					case 0:System.out.println("프로그램을 종료합니다."); return;
				}
			}
	}
	
	public void showAllMember()
	{
		ArrayList<Member> list = mc.showAllMember();
		
		for(Member m : list)
		{
			System.out.println(m.toString());
		}
	}
	
	public void selectMemberId()
	{
		System.out.println("찾는 아이디를 입력하세요.");
		String memberId = sc.next();
		Member m = mc.selectMemberId(memberId);
		if(m!=null) System.out.println(m.toString());
		else System.out.println(memberId + " 의 정보가 없습니다.");
	}
	public void selectMemberName()
	{
		System.out.println("찾는 이름을 입력하세요");
		String name = sc.next();
		ArrayList<Member> list = mc.selectMemberName(name);
		if(list.size()>0)
		{
			for(Member m :list)
			{
				System.out.println(m.toString());
			}
		}
		else
		{
			System.out.println("해당 이름을 가진 회원이 없습니다.");
		}
	}
	
	public boolean searchId(String memberId)
	{
		return  mc.selectMemberId(memberId) !=null;
	}
	public void insertNewMember()
	{
		Member m = new Member();
		System.out.print("가입할 아이디를 입력해주세요");
		m.setMemberId(sc.next());
		
		System.out.print("비밀번호를 입력해주세요");
		m.setMemberPwd(sc.next());
		
		System.out.print("이름을 입력해주세요");
		m.setMemberName(sc.next());
		
		System.out.print("성별을 입력해주세요(남/여)");
		m.setGender((sc.next().equals("남")?"M":"F"));
		
		System.out.print("나이를 입력해주세요");
		m.setAge(sc.nextInt());
		
		System.out.print("이메일을 입력해주세요");
		m.setEmail(sc.next());
		
		System.out.print("폰번호를 입력해주세요");
		m.setPhone(sc.next());
		
		sc.nextLine();
		System.out.print("주소를 입력해주세요");
		m.setAddress(sc.nextLine());
		
		System.out.print("취미를 입력해주세요");
		m.setHobby(sc.next());

		if(mc.insertNewMember(m)>0) System.out.println("회원가입이 완료되었습니다.");
		else System.out.println("회원가입에 실패했습니다.");
	}
	public void updateMember()
	{
		System.out.println("변경할 회원의 아이디를 입력하세요 ");
		String memberId = sc.next();
		if(searchId(memberId))
		{
			Member m = new Member();
			m.setMemberId(memberId);
			System.out.print("변경할 비밀번호를 입력하세요 ");
			m.setMemberPwd(sc.next());
			
			System.out.print("변경할 이메일을 입력하세요 ");
			m.setEmail(sc.next());
			
			System.out.print("변경할 폰번호를 입력하세요 ");
			m.setPhone(sc.next());
			
			sc.nextLine();
			System.out.print("변경할 주소를 입력하세요 ");
			m.setAddress(sc.nextLine());
			
			if(mc.updateMember(m)>0) System.out.println("회원정보가 수정됐습니다.");
			else System.out.println("회원정보 수정을 실패했습니다.");
		}
		else System.out.println("회원정보가 없습니다.");
	}
	public void delteMember()
	{
		System.out.println("삭제할 회원의 아이디를 입력하세요.");
		String memberId = sc.next();
		if(searchId(memberId))
		{
			if(mc.deleteMember(memberId)>0) System.out.println(memberId + " 회원을 삭제했습니다.");
			else System.out.println("회원 삭제에 실패했습니다.");
		}
		else System.out.println("회원정보가 없습니다.");
	}
}
