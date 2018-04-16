package kh.jdbc.board.view;

import java.util.ArrayList;
import java.util.Scanner;

import kh.jdbc.board.controller.BoardController;
import kh.jdbc.board.model.vo.Board;

public class BoardView {
	private MainView mv ;
	private BoardController bc = new BoardController();
	private Scanner sc = new Scanner(System.in);
	public BoardView() {}
	public BoardView(MainView mv) {
		this.mv = mv;
	}

	public void startBoardView() {
		while(true) 
		{

			System.out.println("= = = = = 게시판 서브메뉴 = = = = =");
			System.out.println("1.게시판전체 검색");
			System.out.println("2.게시물 등록");
			System.out.println("3.작성자로 검색");
			System.out.println("4.제목으로 검색");
			System.out.println("5.게시물 수정(제목,내용) idx로 수정");
			System.out.println("6.게시물 삭제");
			System.out.println("7.메인메뉴로");
			switch(sc.nextInt())
			{
			case 1: boardAll(); break;
			case 2: addBoard();break;
			case 3: searchWriter();break;
			case 4: searchTitle();break;
			case 5: boardModify();break;
			case 6: deleteBoard();break;
			case 7: backToMain();break;
			}

		}
	}
	private void backToMain() {
		mv.startMainView();
	}
	private void deleteBoard() {
		System.out.println("삭제할 게시물의 번호를 입력하세요.");
		int num = sc.nextInt();
		int result = 0;
		if(boardChk(num))
		{
			result = bc.deleteBoard(num);
			if(result>0) System.out.println("게시물을 삭제했습니다.");
			else System.out.println("게시물 삭제에 실패했습니다.");
		}
		else System.out.println("해당 번호를 가진 게시물이 없습니다.");
	}
	public boolean boardChk(int num)
	{
		return bc.boardChk(num)>0; 
	}
	private void boardModify() {
		int result = 0;
		System.out.print("수정할 게시물의 번호를 입력하세요. -> ");
		int num = sc.nextInt();
		if(boardChk(num))
		{
			Board b = new Board();
			b.setBoard_no(num);
			sc.nextLine();
			System.out.println("수정할 제목을 입력하세요");
			b.setTitle(sc.nextLine());
			
			
			System.out.println("수정할 내용을 입력하세요.");
			b.setContent(sc.nextLine());
			
			result = bc.boardModify(b);
		}
		else System.out.println("해당  번호를 가진 게시물이 없습니다.");
	}
	private void searchTitle() {
		System.out.print("찾는 게시물의 제목(일부라도)을 입력하세요.  ");
		sc.nextLine();
		String title = sc.nextLine();
		ArrayList<Board> list =new ArrayList<Board>();
		list = bc.searchTitle(title);
		if(list.size()>0)
		{
			for(Board b : list)
			{
				System.out.println(b.toString());
			}
		}
		else System.out.println("해당 제목이 포함된 게시물이 없습니다.");

	}
	private void searchWriter() {
		System.out.print("게시글을 보고 싶은 작성자 아이디를 입력하세요.");
		String id = sc.next();
		if(! new MemberView().memberIdChk(id))
		{
			ArrayList<Board> list = new ArrayList<Board>();
			list = bc.searchWriter(id);
			if(list.size()>0) {
				for(Board b : list)
				{
					System.out.println(b.toString());
				}
			}
			else System.out.println("게시물이 없습니다.");
		}
		else System.out.println("해당 아이디를 가진 회원이 존재하지 않습니다.");

	}
	private void addBoard() {
		Board b = new Board();
		String type = "일반";
		int result = 0;
		System.out.println("게시글 작성");
		System.out.println("게시글 타입 선택 (1:공지,2:일반,3:비밀 *이외의 번호 입력시 자동으로 일반) -> ");
		int num = sc.nextInt();
		b.setDiv(num==1?"공지":(num==3?"비밀":"일반"));

		sc.nextLine();
		System.out.print("제목 입력 -> ");
		b.setTitle(sc.nextLine());
		System.out.print("작성자 아이디 입력 -> ");
		String id = sc.next();
		if(! new MemberView().memberIdChk(id))
		{
			b.setWriter(id);
		}
		else System.out.println("해당 아이디를 가진 회원이 존재하지 않습니다.");

		sc.nextLine();

		System.out.println("내용을 입력하세요. ");
		b.setContent(sc.nextLine());

		result = bc.addBoard(b);
	}
	private void boardAll() {
		System.out.println("게시판 전체 출력");
		ArrayList<Board> list = new ArrayList<Board>();
		list = bc.boardAll();
		for(Board b : list)
		{
			System.out.println(b.toString());
		}
	}

}
