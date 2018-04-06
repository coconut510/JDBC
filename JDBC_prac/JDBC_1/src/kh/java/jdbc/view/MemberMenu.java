package kh.java.jdbc.view;

import java.util.Scanner;

import kh.java.jdbc.controller.MemberController;

public class MemberMenu {
	private Scanner sc = new Scanner(System.in);
	public MemberMenu() {}
	public void mainMenu()
	{
		
		int select;
		while(true) 
		{
			System.out.println("\n= = = = = = 회원관리 프로그램 = = = = = =\n");
			System.out.println("1. 회원 정보 전체 조회");
			System.out.println("2. 회원 아이디 조회");
			System.out.println("3. 회원 이름으로 조회");
			System.out.println("4. 회원가입");
			System.out.println("5. 회원정보 변경");
			System.out.println("6. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			System.out.print("선택(0~6)");
			select = sc.nextInt();
			switch(select)
			{
				case 1:selectAll(); break;
				case 2:selectOneId();break;
				case 3:selectOneName();break;
				case 4:insertName();break;
				case 5:updateMember();break;
				case 6:deleteMember();break;
				case 0: 
					System.out.println("정말 끝내시겠습니까?(y/n)");
					if(sc.next().toUpperCase().charAt(0)=='Y') System.exit(0);
					break;
				default : System.out.println("번호를 잘못 선택하셨습니다.");
			}
		}			
	}
	
	/// 회원 전체 정보 조회 메소드
	public void selectAll()
	{
		// 회원 전체 정보를 컨트롤러에게 요청
		new MemberController().selectAll();
	}
	// 회원 아이디로 1명 검색
	public void selectOneId()
	{
		
	}
	// 회원 이름으로 1명 검색
	public void selectOneName()
	{
		
	}
	// 회원가입
	public void insertName()
	{
		
	}
	// 회원 정보 수정
	public void updateMember()
	{
		
	}
	// 회원탈퇴
	public void deleteMember()
	{
		
	}
	
	
	
	
	
	
	
	
	
	
}
