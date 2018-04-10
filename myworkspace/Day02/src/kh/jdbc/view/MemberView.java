package kh.jdbc.view;

import java.util.ArrayList;
import java.util.Scanner;

import kh.jdbc.controller.MemberController;
import kh.jdbc.model.vo.Member;

public class MemberView {
	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	public void start() {
			System.out.println("======= ȸ�� ���� ���α׷� =======");
			while(true) 
			{
				System.out.println("1.��üȸ�� ��ȸ");
				System.out.println("2.���̵�� ȸ�� �˻�");
				System.out.println("3.�̸����� ȸ�� �˻�");
				System.out.println("4.���ο� ȸ�� ����");
				System.out.println("5.ȸ�� ���̵�� ��й�ȣ,�̸���,����ȣ,�ּ� ����");
				System.out.println("6.ȸ�� ���̵�� Ż���ϱ�");
				System.out.println("0.����");
				System.out.print("�޴��Է�");
				switch(sc.nextInt())
				{
					case 1: showAllMember(); break;
					case 2: selectMemberId(); break;
					case 3: selectMemberName(); break;
					case 4: insertNewMember(); break;
					case 5: updateMember(); break;
					case 6: delteMember(); break;
					case 0:System.out.println("���α׷��� �����մϴ�."); return;
				}
			}
	}
	
	public void showAllMember()
	{
		ArrayList<Member> list = mc.showAllMember();
		
		for(Member m : list)
		{
			System.out.println(m.toString());
		}
	}
	
	public void selectMemberId()
	{
		System.out.println("ã�� ���̵� �Է��ϼ���.");
		String memberId = sc.next();
		Member m = mc.selectMemberId(memberId);
		if(m!=null) System.out.println(m.toString());
		else System.out.println(memberId + " �� ������ �����ϴ�.");
	}
	public void selectMemberName()
	{
		System.out.println("ã�� �̸��� �Է��ϼ���");
		String name = sc.next();
		ArrayList<Member> list = mc.selectMemberName(name);
		if(list.size()>0)
		{
			for(Member m :list)
			{
				System.out.println(m.toString());
			}
		}
		else
		{
			System.out.println("�ش� �̸��� ���� ȸ���� �����ϴ�.");
		}
	}
	
	public boolean searchId(String memberId)
	{
		return  mc.selectMemberId(memberId) !=null;
	}
	public void insertNewMember()
	{
		Member m = new Member();
		System.out.print("������ ���̵� �Է����ּ���");
		m.setMemberId(sc.next());
		
		System.out.print("��й�ȣ�� �Է����ּ���");
		m.setMemberPwd(sc.next());
		
		System.out.print("�̸��� �Է����ּ���");
		m.setMemberName(sc.next());
		
		System.out.print("������ �Է����ּ���(��/��)");
		m.setGender((sc.next().equals("��")?"M":"F"));
		
		System.out.print("���̸� �Է����ּ���");
		m.setAge(sc.nextInt());
		
		System.out.print("�̸����� �Է����ּ���");
		m.setEmail(sc.next());
		
		System.out.print("����ȣ�� �Է����ּ���");
		m.setPhone(sc.next());
		
		sc.nextLine();
		System.out.print("�ּҸ� �Է����ּ���");
		m.setAddress(sc.nextLine());
		
		System.out.print("��̸� �Է����ּ���");
		m.setHobby(sc.next());

		if(mc.insertNewMember(m)>0) System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
		else System.out.println("ȸ�����Կ� �����߽��ϴ�.");
	}
	public void updateMember()
	{
		System.out.println("������ ȸ���� ���̵� �Է��ϼ��� ");
		String memberId = sc.next();
		if(searchId(memberId))
		{
			Member m = new Member();
			m.setMemberId(memberId);
			System.out.print("������ ��й�ȣ�� �Է��ϼ��� ");
			m.setMemberPwd(sc.next());
			
			System.out.print("������ �̸����� �Է��ϼ��� ");
			m.setEmail(sc.next());
			
			System.out.print("������ ����ȣ�� �Է��ϼ��� ");
			m.setPhone(sc.next());
			
			sc.nextLine();
			System.out.print("������ �ּҸ� �Է��ϼ��� ");
			m.setAddress(sc.nextLine());
			
			if(mc.updateMember(m)>0) System.out.println("ȸ�������� �����ƽ��ϴ�.");
			else System.out.println("ȸ������ ������ �����߽��ϴ�.");
		}
		else System.out.println("ȸ�������� �����ϴ�.");
	}
	public void delteMember()
	{
		System.out.println("������ ȸ���� ���̵� �Է��ϼ���.");
		String memberId = sc.next();
		if(searchId(memberId))
		{
			if(mc.deleteMember(memberId)>0) System.out.println(memberId + " ȸ���� �����߽��ϴ�.");
			else System.out.println("ȸ�� ������ �����߽��ϴ�.");
		}
		else System.out.println("ȸ�������� �����ϴ�.");
	}
}
