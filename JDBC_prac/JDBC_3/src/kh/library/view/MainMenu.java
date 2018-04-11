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
			System.out.println("========== ������ ���� ���α׷� ==========");
			System.out.println("1. å����");
			System.out.println("2. ȸ������");
			System.out.println("3. �뿩����");
			System.out.println("4. ���α׷� ����");
			System.out.print("�޴��� �����ϼ��� -> ");
			int select = sc.nextInt();
			switch(select)
			{
				case 1: bookMenu(); break;
				case 2: memberMenu(); break;
				case 3: rentalMenu(); break;
				case 4: System.out.println("���α׷��� �����մϴ�."); return;
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


