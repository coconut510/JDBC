package kh.library.view;

import java.util.ArrayList;
import java.util.Scanner;

import kh.library.controller.BookController;
import kh.library.model.vo.Book;

public class BookMNView {
	private MainMenu mainMenu;
	private BookController bookController = new BookController();
	public BookMNView(){}
	public BookMNView(MainMenu mainMenu)
	{
		this.mainMenu = mainMenu;
	}
	private Scanner sc = new Scanner(System.in);
	public void bookMNMenu()
	{
		while(true) {
			System.out.println("=========== å���� ���� �޴� ===========");
			System.out.println("1. ��ü å ��ȸ");
			System.out.println("2. å �ڵ�� ��ȸ");
			System.out.println("3. å �߰��ϱ�");
			System.out.println("4. å �����ϱ�");
			System.out.println("5. ���� �޴��� �̵�");
			int menu = sc.nextInt();
			switch(menu)
			{
				case 1: bookAllShow();break;
				case 2:bookCodeSearch(); break;
				case 3: bookAdd(); break;
				case 4: bookDelete();break;
				case 5: backToMainMenu(); break;
			}
		}
	}
	
	public void bookAllShow()
	{
		System.out.println("å ��ü ��� ���");
		ArrayList<Book> list = new ArrayList<Book>();
		list = bookController.bookAllShow();
		for(Book b :list)
		{
			System.out.println(b.toString());
		}
	}
	public void bookCodeSearch()
	{
		System.out.println("��ȸ�� å�� �ڵ带 �Է��ϼ���.");
		int bookCode = sc.nextInt();
		Book b = bookController.bookCodeSearch(bookCode);
		if(b!=null)
		{
			System.out.println(b.toString());
		}
		else
		{
			System.out.println("�ش� �ڵ�� ��ġ�ϴ� å�� �����ϴ�.");
		}
	}
	public void bookAdd()
	{
		System.out.println("å �߰��ϱ�");
//		1  ���̷���  ����ȣ  4500  ���﹮ȭ��  �ڹ�/����
		Book b = new Book();
		
		sc.nextLine();
		System.out.print("å �̸��� �Է��ϼ���");
		b.setBook_name(sc.nextLine());
		
		System.out.print("å ���ڸ� �Է��ϼ���");
		b.setWriter(sc.next());
		
		System.out.print("å ������ �Է��ϼ���");
		b.setBook_price(sc.nextInt());
		
		sc.nextLine();
		System.out.println("å ���ǻ縦 �Է��ϼ���");
		b.setPublisher(sc.nextLine());
		
		System.out.println("å �帣�� �Է��ϼ���");
		b.setGenre(sc.next());
		
		if(bookController.bookAdd(b)>0) System.out.println(b.getBook_name() + " å�� �߰��ƽ��ϴ�.");
		else System.out.println("å �߰��� �����߽��ϴ�.");
	}
	public void bookDelete()
	{
		System.out.println("������ å�� �ڵ带 �Է��ϼ���");
		int bookCode = sc.nextInt();
		if(bookController.bookRentChk(bookCode))
		{
			if(bookController.bookDelete(bookCode)>0 ) System.out.println("å�� ���������� �����ƽ��ϴ�.");
			else System.out.println("å ������ �����߽��ϴ�.");
		}
		else
		{
			System.out.println("�뿩���� å�� �����Ҽ� �����ϴ�.");
		}
		
	}
	public void backToMainMenu()
	{
		mainMenu.mainManu();
	}
	
}
