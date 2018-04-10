package kh.library.controller;

import java.util.ArrayList;

import kh.library.model.dao.BookDAO;
import kh.library.model.vo.Book;

public class BookController {
	private BookDAO bookDAO = new BookDAO();
	public ArrayList<Book> bookAllShow() {
		return bookDAO.bookAllShow();
	}
	public Book bookCodeSearch(int bookCode) {
		return bookDAO.bookCodeSearch(bookCode);
	}
	public int bookAdd(Book b) {
		return bookDAO.bookAdd(b);
	}
	public int bookDelete(int bookCode) {
		return bookDAO.bookDelete(bookCode);
	}
	public boolean bookRentChk(int bookCode) {
		return bookDAO.bookRentChk(bookCode);
	}

}
