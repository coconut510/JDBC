package kh.library.model.vo;

import java.util.Date;

public class RentalInfo {
	private int lease_no;
	private int book_no;
	private String book_name;
	private String user_name;
	private String user_id;
	private Date leas_date;
	private Date return_date;
	
	
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getLease_no() {
		return lease_no;
	}
	public void setLease_no(int lease_no) {
		this.lease_no = lease_no;
	}
	public int getBook_no() {
		return book_no;
	}
	public void setBook_no(int book_no) {
		this.book_no = book_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getLeas_date() {
		return leas_date;
	}
	public void setLeas_date(Date leas_date) {
		this.leas_date = leas_date;
	}
	public Date getReturn_date() {
		return return_date;
	}
	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
	
	@Override
	public String toString()
	{
		return this.lease_no + "  " + this.user_id +" "+ this.user_name + "  " + this.book_name ;
	}
	
	public String allToString()
	{
		return this.lease_no + "  " + this.book_no +" "+ this.user_id + "  " + this.leas_date + "  " + this.return_date ;
	}
}
