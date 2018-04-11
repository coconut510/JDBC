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
			System.out.println("=========== 책관리 서브 메뉴 ===========");
			System.out.println("1. 전체 책 조회");
			System.out.println("2. 책 코드로 조회");
			System.out.println("3. 책 추가하기");
			System.out.println("4. 책 삭제하기");
			System.out.println("5. 메인 메뉴로 이동");
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
		System.out.println("책 전체 목록 출력");
		ArrayList<Book> list = new ArrayList<Book>();
		list = bookController.bookAllShow();
		for(Book b :list)
		{
			System.out.println(b.toString());
		}
	}
	public void bookCodeSearch()
	{
		System.out.println("조회할 책의 코드를 입력하세요.");
		int bookCode = sc.nextInt();
		Book b = bookController.bookCodeSearch(bookCode);
		if(b!=null)
		{
			System.out.println(b.toString());
		}
		else
		{
			System.out.println("해당 코드와 일치하는 책이 없습니다.");
		}
	}
	public void bookAdd()
	{
		System.out.println("책 추가하기");
//		1  마이러브  이충호  4500  서울문화사  코믹/순정
		Book b = new Book();
		
		sc.nextLine();
		System.out.print("책 이름을 입력하세요");
		b.setBook_name(sc.nextLine());
		
		System.out.print("책 저자를 입력하세요");
		b.setWriter(sc.next());
		
		System.out.print("책 가격을 입력하세요");
		b.setBook_price(sc.nextInt());
		
		sc.nextLine();
		System.out.println("책 출판사를 입력하세요");
		b.setPublisher(sc.nextLine());
		
		System.out.println("책 장르를 입력하세요");
		b.setGenre(sc.next());
		
		if(bookController.bookAdd(b)>0) System.out.println(b.getBook_name() + " 책이 추가됐습니다.");
		else System.out.println("책 추가에 실패했습니다.");
	}
	public void bookDelete()
	{
		System.out.println("삭제할 책의 코드를 입력하세요");
		int bookCode = sc.nextInt();
		if(bookController.bookRentChk(bookCode))
		{
			if(bookController.bookDelete(bookCode)>0 ) System.out.println("책이 정상적으로 삭제됐습니다.");
			else System.out.println("책 삭제에 실패했습니다.");
		}
		else
		{
			System.out.println("대여중인 책은 삭제할수 없습니다.");
		}
		
	}
	public void backToMainMenu()
	{
		mainMenu.mainManu();
	}
	
}
