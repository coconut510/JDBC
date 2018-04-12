package kh.library.controller;

import java.util.ArrayList;

import kh.library.model.dao.RentalDAO;
import kh.library.model.service.RentalService;
import kh.library.model.vo.RentalInfo;

public class RentalController {
	private RentalService rservice = new RentalService();
	public ArrayList<RentalInfo> rentalAllShow() {
		return rservice.rentalAllShow();
	}
	public ArrayList<RentalInfo> rentalSearchId(String id) {
		return rservice.rentalSearchId(id);
	}
	public ArrayList<RentalInfo> rentalSearchBook(String bookName) {
		return rservice.rentalSearchBook(bookName);
	}
	public int rentalAdd(RentalInfo r) {
		return rservice.rentalAdd(r);
	}

}
