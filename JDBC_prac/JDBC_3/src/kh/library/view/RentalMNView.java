package kh.library.view;

import java.util.ArrayList;
import java.util.Scanner;

import kh.library.controller.RentalController;
import kh.library.model.dao.BookDAO;
import kh.library.model.dao.MemberDAO;
import kh.library.model.vo.Book;
import kh.library.model.vo.RentalInfo;

public class RentalMNView {
	private Scanner sc = new Scanner(System.in);
	private RentalController rc = new RentalController();
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
		System.out.println("�뿩 ������ ��� ���");
		ArrayList<RentalInfo> list = new ArrayList<RentalInfo>();
		list = rc.rentalAllShow();
		for(RentalInfo r :list)
		{
			System.out.println(r.toString());
		}
	}
	public void rentalSearchId()
	{
		System.out.print("�뿩������ �˻��� ȸ�� ���̵� �Է��ϼ���.");
		String id = sc.next();
		ArrayList<RentalInfo> list = new ArrayList<RentalInfo>();
		list = rc.rentalSearchId(id);
		if(list.size()>0)
		{
			for(RentalInfo r : list)
			{
				System.out.println(r.allToString());
			}
		}
		else System.out.println("�ش� Id ������ �����ϴ�.");
	}
	public void rentalSearchBook()
	{
		sc.nextLine();
		System.out.print("�뿩��Ͽ� �ִ� å�� �̸��� �Է��ϼ���.");
		String bookName = sc.nextLine();
		ArrayList<RentalInfo> list = new ArrayList<RentalInfo>();
		list = rc.rentalSearchBook(bookName);
		if(list.size()>0)
		{
			for(RentalInfo r : list)
			{
				System.out.println(r.allToString());
			}
		}
		else
		{
			System.out.println(bookName + " å �뿩 ������ �����ϴ�." );
		}
	}
	public void rentalAdd()
	{	
		System.out.print("å�� �뿩�ϴ� ȸ���� ���̵� �Է��ϼ���");
		String id = sc.next();
		if(new MemberDAO().memberSearchId(id)!=null)
		{
			sc.nextLine();
			System.out.println("�뿩�� å�� �̸��� �Է��ϼ���.");
			String bookName = sc.nextLine();
			Book bookId = new BookDAO().bookChk(bookName);
			if(bookId!=null)
			{
				RentalInfo r = new RentalInfo();
				r.setUser_id(id);
				r.setBook_no(bookId.getBook_no());
				if(rc.rentalAdd(r)>0) System.out.println("�뿩 ������ �߰��ƽ��ϴ�.");
				else System.out.println("�뿩���� �߰��� �����߽��ϴ�.");
			}
			else
			{
				System.out.println("�뿩�Ϸ��� å�� �����ϴ�.");
			}
		}
		else
		{
			System.out.println(id + "�� ���� ������ �����ϴ�.");
		}
		
		System.out.println("");
	}
	public void backToMainMenu()
	{
		mainMenu.mainManu();
	}
}
