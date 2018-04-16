package kh.jdbc.board.model.vo;

import java.util.Date;

public class Member {
	private int member_idx;
	private String member_id;
	private String member_pwd;
	private String member_name;
	private String email;
	private String address;
	private String phone;
	private Date enroll_date;
	
	@Override
	public String toString()
	{
		return this.member_idx + "  " + this.member_id + "  " + this.member_pwd + "  " + this.member_name + "  " + 
		this.email + "  " + address + "  " + phone + "  " +  enroll_date;  
	}
	public int getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_pwd() {
		return member_pwd;
	}
	public void setMember_pwd(String member_pwd) {
		this.member_pwd = member_pwd;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getEnroll_date() {
		return enroll_date;
	}
	public void setEnroll_date(Date enroll_date) {
		this.enroll_date = enroll_date;
	}
	
}
