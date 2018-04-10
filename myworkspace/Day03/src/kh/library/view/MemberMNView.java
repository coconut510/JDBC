package kh.library.view;

import java.util.ArrayList;
import java.util.Scanner;

import kh.library.controller.MemberController;
import kh.library.model.vo.Member;

public class MemberMNView {
	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	private MainMenu mainMenu;
	public MemberMNView() {}
	public MemberMNView(MainMenu mainMenu)
	{
		this.mainMenu = mainMenu;
	}
	public void memberMNMenu()
	{
		while(true) 
		{
			System.out.println("=========== 책관리 서브 메뉴 ===========");
			System.out.println("1. 회원 전체 조회");
			System.out.println("2. 회원 이름으로 조회");
			System.out.println("3. 회원 아이디로 조회");
			System.out.println("4. 회원가입");
			System.out.println("5. 회원 정보 수정");
			System.out.println("6. 회원 탈퇴");
			System.out.println("7. 메인 메뉴로 이동");
			int menu = sc.nextInt();
			switch(menu)
			{
				case 1: memberAllShow();break;
				case 2:memberSearchName(); break;
				case 3: memberSearchId(); break;
				case 4: joinMember();break;
				case 5: updateMember(); break;
				case 6: signOutMember(); break;
				case 7: backToMainMenu(); break;
			}
		}
	}
	
	public void memberAllShow()
	{
		System.out.println("회원들의 전체 리스트");
		ArrayList<Member> list = new ArrayList<Member>();
		list = mc.memberAllShow();
		for(Member m : list)
		{
			System.out.println(m.toString());
		}
	}
	public void memberSearchName()
	{
		System.out.println("찾으려는 회원의 이름을 입력하세요");
		String name = sc.next();
		ArrayList<Member> list = mc.memberSearchName(name);
		
		if(list.size()>0) 
		{
			for(Member m : list)
			{
				System.out.println(m.toString());
			}
		}
		else
		{
			System.out.println(name + "인 회원이 없습니다.");
		}
	}
	public void memberSearchId()
	{
		System.out.println("찾으려는 회원의 아이디를 입력하세요");
		String id = sc.next();
		Member m = mc.memberSearchId(id);
		if(m!=null) System.out.println(m.toString());
		else System.out.println(id + "회원을 찾을수 없습니다.");
	}
	public void joinMember()
	{
		System.out.println("회원가입");
		System.out.print("회원 아이디를 입력해주세요. ");
		String id = sc.next();
		if(mc.memberSearchId(id)==null)
		{
			Member m = new Member();
			
			m.setUser_id(id);
			
			System.out.print("회원 이름을 입력해 주세요. ");
			m.setUser_name(sc.next());
			
			System.out.print("회원 나이를 입력해 주세요. ");
			m.setUser_age(sc.nextInt());
			
			sc.nextLine();
			System.out.print("회원 주소를 입력해 주세요. ");
			m.setAddr(sc.nextLine());
			
			System.out.print("회원 성별을 입력해 주세요.(남/녀) ");
			m.setGender((sc.next().equals("남")?"M":"F"));
			
			if(mc.joinMember(m)>0) System.out.println("회원가입이 정상적으로 됐습니다.");
			else System.out.println("회원가입에 실패했습니다.");
		}
		else System.out.println(id + "와 같은 아이디가 있습니다. 다른 아이디로 생성해주세요");
	
	}
	public void updateMember()
	{
		System.out.println("정보를 수정할 회원의 아이디를 입력하세요.");
		String id = sc.next();
		if(mc.memberSearchId(id)!=null)
		{
			Member m = new Member();
			m.setUser_id(id);
			System.out.print("새로운 이름을 입력하세요.");
			m.setUser_name(sc.next());
			
			sc.nextLine();
			
			System.out.print("새로운 주소를 입력하세요.");
			m.setAddr(sc.nextLine());
			
			if(mc.updateMember(m)>0) System.out.println("수정이 완료됐습니다.");
			else System.out.println("수정을 실패했습니다.");
		}
		else
		{
			System.out.println(id + " 회원정보를 찾을수 없습니다.");
		}
	}
	public void signOutMember()
	{
		System.out.print("탈퇴할 아이디를 입력하세요");
		String id = sc.next();
		if(mc.memberSearchId(id)!=null)
		{
			if(mc.memberRentChk(id))
			{
				if(mc.signOutMember(id)>0) System.out.println("회원 탈퇴가 완료됐습니다.");
				else System.out.println("회원 탈퇴를 실패했습니다.");
			}
			else
			{
				System.out.println("도서 대여중인 회원은 삭제할수 없습니다.");
			}
		}
		else
		{
			System.out.println(id + " 회원정보를 찾을수 없습니다.");
		}
	}
	public void backToMainMenu()
	{
		mainMenu.mainManu();
	}
}
