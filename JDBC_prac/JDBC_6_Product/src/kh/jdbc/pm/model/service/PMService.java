package kh.jdbc.pm.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kh.jdbc.pm.common.Template;
import kh.jdbc.pm.model.dao.PMDAO;
import kh.jdbc.pm.model.vo.Product;

public class PMService {
	private PMDAO pmDAO = new PMDAO();
	public ArrayList<Product> showListBtn() {
		Connection conn = Template.getConnection();
		ArrayList<Product> list = pmDAO.showList(conn);
		Template.close(conn);
		return list;
	}
	public Product search(String searchKey, String type) {
		Connection conn = Template.getConnection();
		Product p = type.equals("id")?pmDAO.searchId(conn,searchKey ):
									  pmDAO.searchName(conn,searchKey );
		Template.close(conn);
		return p;
	}

}
