package kh.library.controller;

import java.util.ArrayList;

import kh.library.model.dao.BookDAO;
import kh.library.model.service.BookService;
import kh.library.model.vo.Book;

public class BookController {
	private BookService bookService = new BookService();
	public ArrayList<Book> bookAllShow() {
		return bookService.bookAllShow();
	}
	public Book bookCodeSearch(int bookCode) {
		return bookService.bookCodeSearch(bookCode);
	}
	public int bookAdd(Book b) {
		return bookService.bookAdd(b);
	}
	public int bookDelete(int bookCode) {
		return bookService.bookDelete(bookCode);
	}
	public boolean bookRentChk(int bookCode) {
		return bookService.bookRentChk(bookCode);
	}

}
