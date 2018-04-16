package kh.jdbc.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kh.jdbc.board.common.Template;
import kh.jdbc.board.model.vo.Board;

public class BoardDAO {

	public ArrayList<Board> boardAll(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = new ArrayList<Board>();
		
		try {
			stmt= conn.createStatement();
			String query = "SELECT * FROM BOARD";
			rset = stmt.executeQuery(query);
			while(rset.next())
			{
				Board b = new Board();
				b.setBoard_no(rset.getInt("BOARD_NO"));
				b.setDiv(rset.getString("DIV"));
				b.setTitle(rset.getString("TITLE"));
				b.setContent(rset.getString("CONTENT"));
				b.setWriter(rset.getString("WRITER"));
				b.setWrite_date(rset.getDate("WRITE_DATE"));
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			Template.close(rset);
			Template.close(stmt);
		}
		return list;
	}

	public int addBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String query = "INSERT INTO BOARD VALUES(BOARD_IDX.NEXTVAL,?,?,?,?,SYSDATE)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getDiv());
			pstmt.setString(2, b.getTitle());
			pstmt.setString(3, b.getContent());
			pstmt.setString(4, b.getWriter());
			
			result = pstmt.executeUpdate();
			if(result>0) Template.commit(conn);
			else Template.rollback(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			Template.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Board> searchWriter(Connection conn, String id) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = new ArrayList<Board>();
		
		try {
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM BOARD WHERE WRITER = '" + id + "'";
			
			rset = stmt.executeQuery(query);
			
			while(rset.next())
			{
				Board b = new Board();
				b.setBoard_no(rset.getInt("BOARD_NO"));
				b.setDiv(rset.getString("DIV"));
				b.setTitle(rset.getString("TITLE"));
				b.setContent(rset.getString("CONTENT"));
				b.setWriter(rset.getString("WRITER"));
				b.setWrite_date(rset.getDate("WRITE_DATE"));
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			Template.close(rset);
			Template.close(stmt);
		}
		
		return list;
	}

	public ArrayList<Board> searchTitle(Connection conn, String title) {
		ArrayList<Board> list = new ArrayList<Board>();
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM BOARD WHERE TITLE LIKE '%" + title +"%'";
			
			rset = stmt.executeQuery(query);
			
			while(rset.next())
			{
				Board b = new Board();
				b.setBoard_no(rset.getInt("BOARD_NO"));
				b.setDiv(rset.getString("DIV"));
				b.setTitle(rset.getString("TITLE"));
				b.setContent(rset.getString("CONTENT"));
				b.setWriter(rset.getString("WRITER"));
				b.setWrite_date(rset.getDate("WRITE_DATE"));
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			Template.close(rset);
			Template.close(stmt);
		}
		return list;
	}

	public int boardChk(Connection conn,int num) {
		Statement stmt =null;
		ResultSet rset =null;
		int result = 0;
		try {
			stmt = conn.createStatement();
			String query = "SELECT * FROM BOARD WHERE BOARD_NO = "+num;
			rset = stmt.executeQuery(query);
			if(rset.next()) result = 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			Template.close(rset);
			Template.close(stmt);
		}
		return result;
	}

	public int boardModify(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query ="UPDATE BOARD SET (TITLE,CONTENT) = (SELECT ?,? FROM DUAL) WHERE BOARD_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setInt(3, b.getBoard_no());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			Template.close(pstmt);
		}
		return result;
	}

	public int deleteBoard(Connection conn, int num) {
		int result = 0;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String query = "DELETE FROM BOARD WHERE BOARD_NO = "+ num;
			
			result = stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			Template.close(stmt);
		}
		return result;
	}

}
