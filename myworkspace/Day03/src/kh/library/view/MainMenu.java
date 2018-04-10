package kh.library.view;

import java.util.Scanner;

public class MainMenu {
	private BookMNView bmView = new BookMNView(this);
	private MemberMNView memberView = new MemberMNView(this);
	private RentalMNView rentalView = new RentalMNView(this);
	private Scanner sc = new Scanner(System.in);
	public void mainManu()
	{
		while(true) 
		{
			System.out.println("========== 도서관 관리 프로그램 ==========");
			System.out.println("1. 책관리");
			System.out.println("2. 회원관리");
			System.out.println("3. 대여관리");
			System.out.println("4. 프로그램 종료");
			System.out.print("메뉴를 선택하세요 -> ");
			int select = sc.nextInt();
			switch(select)
			{
				case 1: bookMenu(); break;
				case 2: memberMenu(); break;
				case 3: rentalMenu(); break;
				case 4: System.out.println("프로그램을 종료합니다."); return;
			}
		}
	}
	public void bookMenu()
	{
		bmView.bookMNMenu();
	}
	public void memberMenu()
	{
		memberView.memberMNMenu();
	}
	public void rentalMenu()
	{
		rentalView.rentalMNMenu();
	}
}


