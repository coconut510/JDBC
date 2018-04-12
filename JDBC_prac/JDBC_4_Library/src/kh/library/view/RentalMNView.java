package kh.library.view;

import java.util.ArrayList;
import java.util.Scanner;

import kh.library.controller.RentalController;
import kh.library.model.dao.BookDAO;
import kh.library.model.dao.MemberDAO;
import kh.library.model.service.MemberService;
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
		System.out.println("대여 정보를 모두 출력");
		ArrayList<RentalInfo> list = new ArrayList<RentalInfo>();
		list = rc.rentalAllShow();
		for(RentalInfo r :list)
		{
			System.out.println(r.toString());
		}
	}
	public void rentalSearchId()
	{
		System.out.print("대여정보를 검색할 회원 아이디를 입력하세요.");
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
		else System.out.println("해당 Id 정보가 없습니다.");
	}
	public void rentalSearchBook()
	{
		sc.nextLine();
		System.out.print("대여목록에 있는 책의 이름을 입력하세요.");
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
			System.out.println(bookName + " 책 대여 정보가 없습니다." );
		}
	}
	public void rentalAdd()
	{	
		System.out.print("책을 대여하는 회원의 아이디를 입력하세요");
		String id = sc.next();
		if(new MemberService().memberSearchId(id)!=null)
		{
			sc.nextLine();
			System.out.println("대여할 책의 이름을 입력하세요.");
			String bookName = sc.nextLine();
			Book bookId = new BookDAO().bookChk(bookName);
			if(bookId!=null)
			{
				RentalInfo r = new RentalInfo();
				r.setUser_id(id);
				r.setBook_no(bookId.getBook_no());
				if(rc.rentalAdd(r)>0) System.out.println("대여 정보가 추가됐습니다.");
				else System.out.println("대여정보 추가에 실패했습니다.");
			}
			else
			{
				System.out.println("대여하려는 책이 없습니다.");
			}
		}
		else
		{
			System.out.println(id + "에 대한 정보가 업습니다.");
		}
		
		System.out.println("");
	}
	public void backToMainMenu()
	{
		mainMenu.mainManu();
	}
}