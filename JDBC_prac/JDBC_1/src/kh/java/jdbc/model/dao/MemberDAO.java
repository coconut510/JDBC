package kh.java.jdbc.model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
		System.out.println(list.get(0).getMemberName());
		System.out.println(list.get(5).getMemberName());
		return list;			
	}
}





