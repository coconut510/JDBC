package kh.jdbc.board.view;

import java.util.Scanner;

public class MainView {

	private Scanner sc = new Scanner(System.in);
	private MemberView mv = new MemberView(this);
	private BoardView bv = new BoardView(this);
	public void startMainView() {
		while(true) {
			System.out.println("= = = = = = 메인메뉴 = = = = = =");
			System.out.println("1. 회원관리 ");
			System.out.println("2. 게시판 관리 ");
			System.out.println("3. 프로그램 종료 ");
			System.out.print("입력 -> ");
			switch(sc.nextInt())
			{
			case 1: memberMenu(); break;
			case 2: boarMenu(); break;
			default:System.out.println("프로그램을 종료합니다."); 
				System.exit(0);
			break;
			}
		}
	}

	public void memberMenu()
	{
		mv.startMemberView();
	}

	public void boarMenu()
	{
		bv.startBoardView();
	}

}
