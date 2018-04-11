package kh.java.jdbc.model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import kh.java.jdbc.model.vo.Member;

//DAO 의 역할
//DBMS에 연결 및 SQL 전송, 결과 리턴
//JDBC
//1. Driver 등록 
//2. Connection (DBMS 에 연결)
//3. Statement 생성
//4. SQL 전송
//5. Result Set (결과)
//6. close (연결해제)

public class MemberDAO {
	private HashMap<String,Member> memberMap = new HashMap<String, Member>();
	public MemberDAO() {}/// 기본 생성자
	public ArrayList<Member> selectAll()
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		try {
			// 1. Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection (DBMS 에 연결성공시 Connection 값이 리턴, 실패히  null값이 리턴)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
										"student",
										"student");
			System.out.println("DB 연결 값 : " + conn);
			
			// 3. Statement 생성
			stmt = conn.createStatement();
			
			// 4. SQL 전송
			String query = "SELECT * FROM MEMBER";
			rset = stmt.executeQuery(query);// 5.전송후에 결과값 리턴 (ResultSet)
			
			//rset.next();-> next메소드는 다음 행을 가리키는 메소드
			// 다음행을 가리켰을때 있으면 true, 없으면 false를 리턴.
			
			
			while(rset.next()) // 다음을 가리켰을때 있다면 루프반복, 없다면 스톱.
			{			
				Member m = new Member();
				// rset은  Key: Value 형태 // key = 컬럼명
				m.setMemberId(rset.getString("member_id"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberPwd(rset.getString("member_pwd"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enroll_date"));
				list.add(m);
				memberMap.put(m.getMemberId(), m);				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}
		// 확인코드
		// System.out.println(list.get(0).getMemberName());
		// System.out.println(list.get(5).getMemberName());
		return list;			
	}
	public Member selectOneId(String memberId)
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Member m =null;
		// 1~6단계
		String query = "SELECT * FROM MEMBER WHERE member_id = '"+ memberId + "'" ;
		try {
			// 1. Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. Connection 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			//3. Statement 생성
			stmt = conn.createStatement();
			
			// 4. 쿼리문 입력
			rset = stmt.executeQuery(query);
			
			if(rset.next()) 
			{
				m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberPwd(rset.getString("member_pwd"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enroll_date"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return m;
	}
	
	public ArrayList<Member> selectOneName(String name)
	{
		ArrayList<Member> list = new ArrayList<Member>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM MEMBER WHERE MEMBER_NAME LIKE'%"+name+"%'";
			rset = stmt.executeQuery(query);
			
			while(rset.next())
			{
				Member m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberPwd(rset.getString("member_pwd"));
				m.setAge(rset.getInt("age"));
				m.setGender(rset.getString("gender"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enroll_date"));
				list.add(m);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}	
		
		return list;
				
	}
	public int insertMember(Member m) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
							"student","student");
			stmt = conn.createStatement();
			
			String query = "INSERT INTO MEMBER VALUES(" + "'" +m.getMemberId() + "'," +
														"'" +m.getMemberPwd() + "'," +"'" +m.getMemberName() + "'," + 
														"'" +m.getGender() + "'," + m.getAge()+ ","+
														"'" +m.getEmail() + "',"+ "'" +m.getPhone() + "',"+ 
														"'" +m.getAddress() + "',"+ "'" +m.getHobby() + "'," + "SYSDATE)"; 
		// insert, update, delete 는 executeUpdate 메소드를 사용하여
	    // 쿼리문을 작동시킴(리턴값이 int 형 -> 쿼리문 정상 실행 개수 리턴)
		// excuteUpdate 메소드는 해당 쿼리문을 실행 시킬 뿐
		// 적용시키는 코드는 아님(commit, rollBack은 별도로 진행 해야함).
		result = stmt.executeUpdate(query);
		
		if(result>0) conn.commit();  // 정상처리 되었다면
		else conn.rollback();// 정상처리 되지 않았다면.
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return result;
	}
	public int updatemember(Member m) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe"
												,"student","student");
			
			stmt = conn.createStatement();
			
			String query = "UPDATE MEMBER "
					+ "SET (MEMBER_PWD,EMAIL,PHONE,ADDRESS) = (SELECT " + "'" + m.getMemberId() + "'," +
																	"'" + m.getEmail() + "'," +
																	"'" + m.getPhone() + "'," +
																	"'" + m.getAddress() + "'" +" FROM DUAL)"
					+ "WHERE MEMBER_ID = '"+m.getMemberId()+ "'";
			System.out.println( m.getAddress());
			
			result = stmt.executeUpdate(query);
			
			System.out.println("결과값 " + result);
			if(result>0) conn.commit();
			else conn.rollback();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	public int deleteMember(String memberId) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			stmt = conn.createStatement();
			
			String query = "DELETE FROM MEMBER WHERE MEMBER_ID = " +"'" +  memberId + "'";
			result = stmt.executeUpdate(query);
			
			if(result>0) conn.commit();
			else conn.rollback();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}





