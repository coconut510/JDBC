package kh.jdbc.board.model.vo;

import java.util.Date;

public class Board {
	private int board_no;
	private String  div;
	private String title;
	private String content;
	private String writer;
	private Date write_date;
	
	public String toString()
	{
		return this.board_no + "  " + this.div + "  " +  this.title  + "  " +  this.writer  + "  " +  this.write_date
				 + " \n\n" + this.content  + " \n\n"; 
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getDiv() {
		return div;
	}
	public void setDiv(String div) {
		this.div = div;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}
	
	
}
