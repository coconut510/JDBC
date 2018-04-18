package kh.jdbc.pm.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import kh.jdbc.pm.common.Template;
import kh.jdbc.pm.model.vo.Product;

public class PMDAO {
	private Properties prop = new Properties();
	public PMDAO() {
		try {
			prop.load(new FileReader("resource/query.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Product> showList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Product> list =new ArrayList<Product>();
		try {
			stmt = conn.createStatement();
			String query = prop.getProperty("all");
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

	public ArrayList<Product> searchId(Connection conn, String searchKey) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		try {
			String query = prop.getProperty("idSearch");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + searchKey + "%");
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				Product p = new Product();
				p = new Product();
				p.setProduct_id(rset.getString("product_id"));
				p.setP_name(rset.getString("p_name"));
				p.setPrice(rset.getInt("price"));
				p.setDescription(rset.getString("description"));
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<Product> searchName(Connection conn, String searchKey) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		try {
			String query = prop.getProperty("nameSearch");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + searchKey+"%");
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				Product p = new Product();
				p = new Product();
				p.setProduct_id(rset.getString("product_id"));
				p.setP_name(rset.getString("p_name"));
				p.setPrice(rset.getInt("price"));
				p.setDescription(rset.getString("description"));
				list.add(p);
				System.out.println("이름 포함 리스트");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			Template.close(rset);
			Template.close(pstmt);
		}
		return list;
	}

	public int addProduct(Connection conn, Product p) {
		PreparedStatement  pstmt = null;
		int result = 0;
		String query =prop.getProperty("addProduct");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, p.getProduct_id());
			pstmt.setString(2, p.getP_name());
			pstmt.setInt(3, p.getPrice());
			pstmt.setString(4, p.getDescription());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			Template.close(pstmt);
		}
		return result;
	}

	public int editProduct(Connection conn, Product p) {
		
		PreparedStatement  pstmt = null;
		int result = 0;
		String query =prop.getProperty("update");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, p.getProduct_id());
			pstmt.setString(2, p.getP_name());
			pstmt.setInt(3, p.getPrice());
			pstmt.setString(4, p.getDescription());
			pstmt.setString(5, p.getOrigin_id());
			result = pstmt.executeUpdate();
			System.out.println(p.getOrigin_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			Template.close(pstmt);
		}
		return result;
	}

	public int delProduct(Connection conn, String id) {
		PreparedStatement  pstmt = null;
		int result = 0;
		String query = prop.getProperty("delProduct");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			Template.close(pstmt);
		}
		return result;
	}

}
