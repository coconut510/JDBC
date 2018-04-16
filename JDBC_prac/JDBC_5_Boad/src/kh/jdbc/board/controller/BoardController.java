package kh.jdbc.board.controller;

import java.util.ArrayList;

import kh.jdbc.board.model.service.BoardService;
import kh.jdbc.board.model.vo.Board;

public class BoardController {
	private BoardService bs = new BoardService();
	public ArrayList<Board> boardAll() {
		return bs.boardAll();
	}
	public int addBoard(Board b) {
		return bs.addBoard(b);
	}
	public ArrayList<Board> searchWriter(String id) {
		return bs.searchWriter(id);
	}
	public ArrayList<Board> searchTitle(String title) {
		return bs.searchTitle(title);
	}
	public int boardChk(int num) {
		return bs.boardChk(num);
	}
	public int boardModify(Board b) {
		return bs.boardModify(b);
	}
	public int deleteBoard(int num) {
		return bs.deleteBoard(num);
	}

}
