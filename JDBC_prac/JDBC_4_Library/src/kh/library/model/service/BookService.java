package kh.library.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import kh.library.common.JDBCTemplate;
import kh.library.model.dao.BookDAO;
import kh.library.model.vo.Book;

public class BookService {
	private BookDAO bookDao = new BookDAO();
	public ArrayList<Book> bookAllShow() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Book> list = bookDao.bookAllShow(conn);
		

		JDBCTemplate.closeConn(conn);
		return list;
	}
	public Book bookCodeSearch(int bookCode) {
		Connection conn = JDBCTemplate.getConnection();
		Book b = bookDao.bookCodeSearch(conn,bookCode);
		JDBCTemplate.closeConn(conn);
		return b;
	}
	public int bookAdd(Book b) {
		Connection conn = JDBCTemplate.getConnection();
		int	result = bookDao.bookAdd(conn,b);
	
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.closeConn(conn);
		return result;
	}
	public int bookDelete(int bookCode) {
		Connection conn = JDBCTemplate.getConnection();
		int result = bookDao.bookDelete(conn,bookCode);
			
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.closeConn(conn);
		return result;
	}
	public boolean bookRentChk(int bookCode) {
		Connection conn = JDBCTemplate.getConnection();
		boolean chk = bookDao.bookRentChk(conn, bookCode);
		JDBCTemplate.closeConn(conn);
		return chk;
	}

}
