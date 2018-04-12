package kh.library.model.vo;

import java.util.Date;

public class Member {
	private int user_no;
	private String user_id;
	private String user_name;
	private int user_age;
	private String addr;
	private String gender;
	private Date enroll_date;
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getUser_age() {
		return user_age;
	}
	public void setUser_age(int user_age) {
		this.user_age = user_age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getEnroll_date() {
		return enroll_date;
	}
	public void setEnroll_date(Date enroll_date) {
		this.enroll_date = enroll_date;
	}
	
	@Override
	public String toString()
	{
		return this.user_no + "  " + this.user_id +  "  " + this.user_name + "  " + this.user_age + "  " + this.addr + "  " + this.gender + "  " + this.enroll_date;
	}
}
