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
			System.out.println("=========== �뿩���� ���� �޴� ===========");
			System.out.println("1. �뿩 ���� ��ü ��ȸ");
			System.out.println("2. ȸ�� ���̵�� �뿩 ��ȸ");
			System.out.println("3. å �̸����� �뿩 ��ȸ");
			System.out.println("4. �뿩���� �߰�");
			System.out.println("5. ���� �޴��� �̵�");
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
