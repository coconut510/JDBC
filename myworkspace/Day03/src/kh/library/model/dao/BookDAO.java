package kh.library.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kh.library.model.vo.Book;
import kh.library.model.vo.Member;

public class BookDAO {
	public ArrayList<Book> bookAllShow() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Book> list = new ArrayList<Book>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM BOOK";
			
			rset = stmt.executeQuery(query);
			
			while(rset.next())
			{
				Book b = new Book();
				b.setBook_no(rset.getInt("book_no"));
				b.setBook_name(rset.getString("book_name"));
				b.setWriter(rset.getString("book_writer"));
				b.setBook_price(rset.getInt("book_price"));
				b.setPublisher(rset.getString("publisher"));
				b.setGenre(rset.getString("genre"));
				list.add(b);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	public Book bookCodeSearch(int bookCode) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Book b = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn  = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM BOOK WHERE BOOK_NO = '" + bookCode + "'";
			
			rset = stmt.executeQuery(query);
			
			if(rset.next())
			{
				b = new Book();
				b.setBook_no(rset.getInt("book_no"));
				b.setBook_name(rset.getString("book_name"));
				b.setWriter(rset.getString("book_writer"));
				b.setBook_price(rset.getInt("book_price"));
				b.setPublisher(rset.getString("publisher"));
				b.setGenre(rset.getString("genre"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	public int bookAdd(Book b) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			stmt = conn.createStatement();
			
			String query = "INSERT INTO BOOK VALUES((SELECT MAX(BOOK_NO) +1 FROM BOOK), "
					+ "'" + b.getBook_name() +"',"
					+ "'" + b.getWriter() +"',"
					+ "" + b.getBook_price() +","
					+ "'" + b.getPublisher() +"',"
					+ "'" + b.getGenre() +"'"
					+ ")";
			result = stmt.executeUpdate(query);
			
			if(result>0) conn.commit();
			else conn.rollback();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public boolean bookRentChk(int bookCode)
	{
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		String query = "SELECT BOOK_NO FROM LIBRARY WHERE BOOK_NO  = " + bookCode;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(query);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		
		return result<1;
	}

	public int bookDelete(int bookCode) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			stmt = conn.createStatement();
			
			String query = "DELETE FROM BOOK WHERE BOOK_NO = " + bookCode;
			
			result = stmt.executeUpdate(query);
			if(result>0) conn.commit();
			else conn.rollback();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}

}
