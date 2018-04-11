package kh.library.view;

import java.util.ArrayList;
import java.util.Scanner;

import kh.library.controller.MemberController;
import kh.library.model.vo.Member;

public class MemberMNView {
	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	private MainMenu mainMenu;
	public MemberMNView() {}
	public MemberMNView(MainMenu mainMenu)
	{
		this.mainMenu = mainMenu;
	}
	public void memberMNMenu()
	{
		while(true) 
		{
			System.out.println("=========== å���� ���� �޴� ===========");
			System.out.println("1. ȸ�� ��ü ��ȸ");
			System.out.println("2. ȸ�� �̸����� ��ȸ");
			System.out.println("3. ȸ�� ���̵�� ��ȸ");
			System.out.println("4. ȸ������");
			System.out.println("5. ȸ�� ���� ����");
			System.out.println("6. ȸ�� Ż��");
			System.out.println("7. ���� �޴��� �̵�");
			int menu = sc.nextInt();
			switch(menu)
			{
				case 1: memberAllShow();break;
				case 2:memberSearchName(); break;
				case 3: memberSearchId(); break;
				case 4: joinMember();break;
				case 5: updateMember(); break;
				case 6: signOutMember(); break;
				case 7: backToMainMenu(); break;
			}
		}
	}
	
	public void memberAllShow()
	{
		System.out.println("ȸ������ ��ü ����Ʈ");
		ArrayList<Member> list = new ArrayList<Member>();
		list = mc.memberAllShow();
		for(Member m : list)
		{
			System.out.println(m.toString());
		}
	}
	public void memberSearchName()
	{
		System.out.println("ã������ ȸ���� �̸��� �Է��ϼ���");
		String name = sc.next();
		ArrayList<Member> list = mc.memberSearchName(name);
		
		if(list.size()>0) 
		{
			for(Member m : list)
			{
				System.out.println(m.toString());
			}
		}
		else
		{
			System.out.println(name + "�� ȸ���� �����ϴ�.");
		}
	}
	public void memberSearchId()
	{
		System.out.println("ã������ ȸ���� ���̵� �Է��ϼ���");
		String id = sc.next();
		Member m = mc.memberSearchId(id);
		if(m!=null) System.out.println(m.toString());
		else System.out.println(id + "ȸ���� ã���� �����ϴ�.");
	}
	public void joinMember()
	{
		System.out.println("ȸ������");
		System.out.print("ȸ�� ���̵� �Է����ּ���. ");
		String id = sc.next();
		if(mc.memberSearchId(id)==null)
		{
			Member m = new Member();
			
			m.setUser_id(id);
			
			System.out.print("ȸ�� �̸��� �Է��� �ּ���. ");
			m.setUser_name(sc.next());
			
			System.out.print("ȸ�� ���̸� �Է��� �ּ���. ");
			m.setUser_age(sc.nextInt());
			
			sc.nextLine();
			System.out.print("ȸ�� �ּҸ� �Է��� �ּ���. ");
			m.setAddr(sc.nextLine());
			
			System.out.print("ȸ�� ������ �Է��� �ּ���.(��/��) ");
			m.setGender((sc.next().equals("��")?"M":"F"));
			
			if(mc.joinMember(m)>0) System.out.println("ȸ�������� ���������� �ƽ��ϴ�.");
			else System.out.println("ȸ�����Կ� �����߽��ϴ�.");
		}
		else System.out.println(id + "�� ���� ���̵� �ֽ��ϴ�. �ٸ� ���̵�� �������ּ���");
	
	}
	public void updateMember()
	{
		System.out.println("������ ������ ȸ���� ���̵� �Է��ϼ���.");
		String id = sc.next();
		if(mc.memberSearchId(id)!=null)
		{
			Member m = new Member();
			m.setUser_id(id);
			System.out.print("���ο� �̸��� �Է��ϼ���.");
			m.setUser_name(sc.next());
			
			sc.nextLine();
			
			System.out.print("���ο� �ּҸ� �Է��ϼ���.");
			m.setAddr(sc.nextLine());
			
			if(mc.updateMember(m)>0) System.out.println("������ �Ϸ�ƽ��ϴ�.");
			else System.out.println("������ �����߽��ϴ�.");
		}
		else
		{
			System.out.println(id + " ȸ�������� ã���� �����ϴ�.");
		}
	}
	public void signOutMember()
	{
		System.out.print("Ż���� ���̵� �Է��ϼ���");
		String id = sc.next();
		if(mc.memberSearchId(id)!=null)
		{
			if(mc.memberRentChk(id))
			{
				if(mc.signOutMember(id)>0) System.out.println("ȸ�� Ż�� �Ϸ�ƽ��ϴ�.");
				else System.out.println("ȸ�� Ż�� �����߽��ϴ�.");
			}
			else
			{
				System.out.println("���� �뿩���� ȸ���� �����Ҽ� �����ϴ�.");
			}
		}
		else
		{
			System.out.println(id + " ȸ�������� ã���� �����ϴ�.");
		}
	}
	public void backToMainMenu()
	{
		mainMenu.mainManu();
	}
}
