package kh.jdbc.pm.model.vo;

public class Product {
	private String product_id;
	private String p_name;
	private int price;
	private String description;
	private String origin_id;
	
	public String getOrigin_id() {
		return origin_id;
	}
	public void setOrigin_id(String origin_id) {
		this.origin_id = origin_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
