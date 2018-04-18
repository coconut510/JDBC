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
	public ArrayList<Product> search(String searchKey, String type) {
		Connection conn = Template.getConnection();
		ArrayList<Product> list = type.equals("id")?pmDAO.searchId(conn,searchKey ):
									  pmDAO.searchName(conn,searchKey );
		Template.close(conn);
		return list;
	}
	public int addProduct(Product p) {
		Connection conn = Template.getConnection();
		int result = pmDAO.addProduct(conn,p);
		if(result>0) Template.commit(conn);
		else Template.rollback(conn);
		Template.close(conn);
		return result;
	}
	public int editProduct(Product p) {
		Connection conn = Template.getConnection();
		int result = pmDAO.editProduct(conn, p);
		if(result>0) Template.commit(conn);
		else Template.rollback(conn);
		Template.close(conn);
		return result;
	}
	public int delProduct(String id) {
		Connection conn = Template.getConnection();
		int result = pmDAO.delProduct(conn, id);
		if(result>0) Template.commit(conn);
		else Template.rollback(conn);
		Template.close(conn);
		return result;
	}

}
