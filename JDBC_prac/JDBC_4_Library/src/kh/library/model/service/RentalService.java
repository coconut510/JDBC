package kh.library.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import kh.library.common.JDBCTemplate;
import kh.library.model.dao.RentalDAO;
import kh.library.model.vo.RentalInfo;

public class RentalService {
	private RentalDAO rdao = new RentalDAO();
	public ArrayList<RentalInfo> rentalAllShow() {
		
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<RentalInfo> list = rdao.rentalAllShow(conn);
		
		JDBCTemplate.closeConn(conn);
		return list;
	}

	public ArrayList<RentalInfo> rentalSearchId(String id) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<RentalInfo> list = rdao.rentalSearchId(conn,id);


		JDBCTemplate.closeConn(conn);
		return list;
	}

	public ArrayList<RentalInfo> rentalSearchBook(String bookName) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<RentalInfo> list = rdao.rentalSearchBook(conn,bookName);

		JDBCTemplate.closeConn(conn);
		return list;
	}

	public int rentalAdd(RentalInfo r) {
		Connection conn = JDBCTemplate.getConnection();
		int result = rdao.rentalAdd(conn,r);

		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.closeConn(conn);

		return result;
	}
	
}
