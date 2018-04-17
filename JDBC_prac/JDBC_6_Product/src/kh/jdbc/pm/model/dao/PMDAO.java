package kh.jdbc.pm.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kh.jdbc.pm.common.Template;
import kh.jdbc.pm.model.vo.Product;

public class PMDAO {

	public ArrayList<Product> showList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Product> list =new ArrayList<Product>();
		try {
			stmt = conn.createStatement();
			String query = "SELECT * FROM PRODUCT";
			rset = stmt.executeQuery(query);
			
			while(rset.next())
			{
				Product p =new Product();
				p.setProduct_id(rset.getString("product_id"));
				p.setP_name(rset.getString("p_name"));
				p.setPrice(rset.getInt("price"));
				p.setDescription(rset.getString("description"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			Template.close(rset);
			Template.close(stmt);
		}
		return list;
	}

	public Product searchId(Connection conn, String searchKey) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product p = null;
		try {
			String query = "SELECT * FROM PRODUCT WHERE P_NAME = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKey);
			rset = pstmt.executeQuery();
			
			if(rset.next())
			{
				p = new Product();
				p.setProduct_id(rset.getString("product_id"));
				p.setP_name(rset.getString("p_name"));
				p.setPrice(rset.getInt("price"));
				p.setDescription(rset.getString("description"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	public Product searchName(Connection conn, String searchKey) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product p = null;
		try {
			String query = "SELECT * FROM PRODUCT WHERE PRODUCT_ID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKey);
			rset = pstmt.executeQuery();
			
			if(rset.next())
			{
				p = new Product();
				p.setProduct_id(rset.getString("product_id"));
				p.setP_name(rset.getString("p_name"));
				p.setPrice(rset.getInt("price"));
				p.setDescription(rset.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

}
