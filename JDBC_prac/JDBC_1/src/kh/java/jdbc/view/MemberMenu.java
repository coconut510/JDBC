package kh.java.jdbc.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import kh.java.jdbc.controller.MemberController;
import kh.java.jdbc.model.vo.Member;

public class MemberMenu {
	private MemberController mc =  new MemberController();
	private Scanner sc = new Scanner(System.in);
	public MemberMenu() {}
	public void mainMenu()
	{
		
		int select;
		while(true) 
		{
			System.out.println("\n= = = = = = 회원관리 프로그램 = = = = = =\n");
			System.out.println("1. 회원 정보 전체 조회");
			System.out.println("2. 회원 아이디 조회");
			System.out.println("3. 회원 이름으로 조회");
			System.out.println("4. 회원가입");
			System.out.println("5. 회원정보 변경");
			System.out.println("6. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			System.out.print("선택(0~6)");
			select = sc.nextInt();
			switch(select)
			{
				case 1:selectAll(); break;
				case 2:selectOneId();break;
				case 3:selectOneName();break;
				case 4:insertName();break;
				case 5:updateMember();break;
				case 6:deleteMember();break;
				case 0: 
					System.out.println("정말 끝내시겠습니까?(y/n)");
					if(sc.next().toUpperCase().charAt(0)=='Y') System.exit(0);
					break;
				default : System.out.println("번호를 잘못 선택하셨습니다.");
			}
		}			
	}
	
	/// 회원 전체 정보 조회 메소드
	public void selectAll()
	{		
		// 회원 전체 정보를 컨트롤러에게 요청
		ArrayList<Member> list = mc.selectAll();
		if(list==null)
		{
			System.out.println("요청한 정보가 없습니다.");
		}
		else
		{
			System.out.println("=======전체 회원 조회=======");
			System.out.println("아이디  이름  비밀번호  성별  나이  이메일  전화번호  주소  취미  가입날짜");
			for(Member m : list)
			{
				System.out.println(m.toString());
			}
		}
			
	}
	// 회원 아이디로 1명 검색
	public void selectOneId()
	{
		System.out.println("검색할 아이디를 입력하세요.");
		String memberId = sc.next();
		Member member = mc.selectOneId(memberId);
		
		if(member == null) System.out.println(memberId + "에 대한 정보가 없습니다.");
		else 
		{
			System.out.println("아이디 : " + member.getMemberId());
			System.out.println("이름 : " + member.getMemberName());
			System.out.println("비밀번호 : " + member.getMemberPwd());
			System.out.println("나이: " + member.getAge());
			System.out.println("성별 : " + (member.getGender().equals("M")?"남":"여"));
			System.out.println("이메일 : " + member.getEmail());
			System.out.println("폰번호 : " + member.getPhone());
			System.out.println("주소 : " + member.getAddress());
			System.out.println("취미 : " + member.getHobby());
			System.out.println("가입일 : " + member.getEnrollDate());
		}
	}
	// 회원 이름으로 1명 검색
	public void selectOneName()
	{
		System.out.println("검색할 이름을 입력하세요.");
		String memberName = sc.next();
		ArrayList<Member> list = mc.selectOneName(memberName);
		if(list.size()>0)
		{
			for(Member m: list)
			{
				System.out.println(m.toString());
			}
		}
		else
		{
			System.out.println(memberName + "에 대한 정보가 없습니다.");
		}
	}
	// 회원가입
	public void insertName()
	{
		Member m = new Member();
		System.out.println("==== 회원가입 ====");
		System.out.print("아이디 입력 : " );
		m.setMemberId(sc.next());
		
		System.out.print("비밀번호 입력 : " );
		m.setMemberPwd(sc.next());
		
		System.out.print("이름 입력 : " );
		m.setMemberName(sc.next());

		System.out.print("나이 입력 : " );
		m.setAge(sc.nextInt());
		
		System.out.print("성별 입력(남/여) : " );
		m.setGender((sc.next().equals("남")?"M":"F"));
		
		System.out.print("이메일 입력 : " );
		m.setEmail(sc.next());
		
		System.out.print("폰번호 입력 : " );
		m.setPhone(sc.next());
		
		sc.nextLine();
	
		System.out.print("주소 입력 : " );
		m.setAddress(sc.nextLine());
		
		
		System.out.print("취미 입력 : " );
		m.setHobby(sc.next());
		
		if(mc.insertMember(m)>0) System.out.println("입력이 완료됐습니다.");
		else System.out.println("입력을 실패했습니다.");
	}
	// 회원 정보 수정
	public void updateMember()
	{
		System.out.print("조회할 아이디를 입력하세요");
		String memberId = sc.next();
		Member m = new Member();
		
		m.setMemberId(memberId);
		
		System.out.print("수정할 암호를 입력하세요 : ");
		m.setMemberPwd(sc.next());
		
		System.out.print("수정할 이메일을 입력하세요 : ");
		m.setEmail(sc.next());
		
		System.out.print("수정할 주소를 입력하세요 : ");
	
		sc.nextLine();
		m.setAddress( sc.nextLine());
		
		System.out.print("수정할 전화번호를 입력하세요 : ");
		m.setPhone(sc.next());					
		
		if(mc.updateMember(m)>0) System.out.println("회원정보가 수정됐습니다.");
		else System.out.println("회원정보 수정이 실패했습니다.");			
	}
	// 회원탈퇴
	public void deleteMember()
	{
		System.out.print("탈퇴할 아이디를 입력하세요");
		String memberId = sc.next();
		if(mc.deleteMember(memberId)>0) System.out.println(memberId + " 회원 삭제를 완료했습니다.");
		else System.out.println("삭제에 실패했습니다.");
	}
}
