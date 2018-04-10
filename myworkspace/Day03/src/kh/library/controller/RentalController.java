package kh.library.controller;

import java.util.ArrayList;

import kh.library.model.dao.RentalDAO;
import kh.library.model.vo.RentalInfo;

public class RentalController {
	private RentalDAO rdao = new RentalDAO();
	public ArrayList<RentalInfo> rentalAllShow() {
		return rdao.rentalAllShow();
	}
	public ArrayList<RentalInfo> rentalSearchId(String id) {
		return rdao.rentalSearchId(id);
	}
	public ArrayList<RentalInfo> rentalSearchBook(String bookName) {
		return rdao.rentalSearchBook(bookName);
	}
	public int rentalAdd(RentalInfo r) {
		return rdao.rentalAdd(r);
	}

}
