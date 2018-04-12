package kh.library.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kh.library.model.vo.Book;
import kh.library.model.vo.Member;

public class BookDAO {
	public ArrayList<Book> bookAllShow(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Book> list = new ArrayList<Book>();
		try {
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM BOOK ORDER BY 1";
			
			System.out.println(query);
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				rset.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	public Book bookCodeSearch(Connection conn, int bookCode) {
		Statement stmt = null;
		ResultSet rset = null;
		Book b = null;
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				rset.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	public int bookAdd(Connection conn, Book b) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String query = "INSERT INTO BOOK VALUES((SELECT MAX(BOOK_NO) +1 FROM BOOK),?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1,b.getBook_name());
			pstmt.setString(2,b.getWriter());
			pstmt.setInt(3,b.getBook_price());
			pstmt.setString(4,b.getPublisher());
			pstmt.setString(5,b.getGenre());
			result = pstmt.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public Book bookChk(String name)
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT BOOK_NO FROM BOOK WHERE BOOK_NAME  = '" + name + "'";
		Book b = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			if(rset.next())
			{
				b = new Book();
				b.setBook_no(rset.getInt("book_no"));
			}
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return b;
	}
	
	public boolean bookRentChk(Connection conn, int bookCode)
	{
		Statement stmt = null;
		int result = 0;
		String query = "SELECT BOOK_NO FROM LIBRARY WHERE BOOK_NO  = " + bookCode;
		
		try {
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		finally
		{
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result<1;
	}

	public int bookDelete(Connection conn, int bookCode) {
		Statement stmt = null;
		int result = 0;
		try {
			stmt = conn.createStatement();
			
			String query = "DELETE FROM BOOK WHERE BOOK_NO = " + bookCode;
			
			result = stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}

}
