package kh.jdbc.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kh.jdbc.board.common.Template;
import kh.jdbc.board.model.dao.BoardDAO;
import kh.jdbc.board.model.vo.Board;

public class BoardService {
	private BoardDAO bdao = new BoardDAO();
	public ArrayList<Board> boardAll() {
		Connection conn = Template.getConnection();
		ArrayList<Board> list =bdao.boardAll(conn);
		Template.close(conn);
		return list;
	}
	public int addBoard(Board b) {
		Connection conn = Template.getConnection();
		int result = bdao.addBoard(conn,b);
		if(result>0) Template.commit(conn);
		else Template.rollback(conn);
		Template.close(conn);
		return result;
	}
	public ArrayList<Board> searchWriter(String id) {
		Connection conn =Template.getConnection();
		ArrayList<Board> list = bdao.searchWriter(conn,id);
		Template.close(conn);
		return list;
	}
	public ArrayList<Board> searchTitle(String title) {
		Connection conn =Template.getConnection();
		ArrayList<Board> list = bdao.searchTitle(conn,title);
		Template.close(conn);
		return list;
	}
	public int boardChk(int num) {
		Connection conn = Template.getConnection();
		int result = bdao.boardChk(conn,num);
		if(result>0) Template.commit(conn);
		else Template.rollback(conn);
		Template.close(conn);
		return result;
	}
	public int boardModify(Board b) {
		Connection conn = Template.getConnection();
		int result = bdao.boardModify(conn, b);
		if(result>0) Template.commit(conn);
		else Template.rollback(conn);
		Template.close(conn);
		return result;
	}
	public int deleteBoard(int num) {
		Connection conn = Template.getConnection();
		int result = bdao.deleteBoard(conn,num);
		if(result>0) Template.commit(conn);
		else Template.rollback(conn);
		Template.close(conn);
		return result;
	}

}
