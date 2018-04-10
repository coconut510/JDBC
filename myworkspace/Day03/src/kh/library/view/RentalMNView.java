package kh.library.view;

import java.util.Scanner;

public class RentalMNView {
	private Scanner sc = new Scanner(System.in);
	private MainMenu mainMenu;
	public RentalMNView() {}
	public RentalMNView(MainMenu mainMenu)
	{
		this.mainMenu = mainMenu;
	}
	public void rentalMNMenu()
	{
		while(true) 
		{
			System.out.println("=========== 대여관리 서브 메뉴 ===========");
			System.out.println("1. 대여 관리 전체 조회");
			System.out.println("2. 회원 아이디로 대여 조회");
			System.out.println("3. 책 이름으로 대여 조회");
			System.out.println("4. 대여정보 추가");
			System.out.println("5. 메인 메뉴로 이동");
			int menu = sc.nextInt();
			switch(menu)
			{
				case 1: rentalAllShow();break;
				case 2:rentalSearchId(); break;
				case 3: rentalSearchBook(); break;
				case 4: rentalAdd();break;
				case 5: backToMainMenu(); break;
			}
		}
	}
	
	public void rentalAllShow()
	{
		
	}
	public void rentalSearchId()
	{
		
	}
	public void rentalSearchBook()
	{
		
	}
	public void rentalAdd()
	{
		
	}
	public void backToMainMenu()
	{
		mainMenu.mainManu();
	}
}
