package kh.java.com.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MyFrame extends JFrame {
	// 컬럼명
	String [] columnNames = {"이름", "나이","주소"};
	
	
	// 데이터 생성
	// 데이터는 2차원 Object 타입으로 생성.
	
	Object[][] data = {
				{"홍길동", 20, "경기도 부천"},
				{"김말동", 30, "서울시 관악"},
				{"고길동", 40, "제주도 서귀포"}
			};
	
	// 모델 생성(데이터, 컬럼명)
	DefaultTableModel model = new DefaultTableModel(data, columnNames)
			{
				@Override
				public boolean isCellEditable(int row, int column)
				{
					return false;
				}
			};

	// JTable 생성(모델)
	private JTable table = new JTable(model);
	
	//JScrollPane 생성(table)
	JScrollPane scrollpane = new JScrollPane(table);
	JPanel frameCenterPanel = new JPanel();
	JPanel frameSouthPanel = new JPanel();
	JPanel fspEastPanel = new JPanel();
	JPanel fspCenterPanel = new JPanel();
	
	JLabel name = new JLabel("  이름 : ");
	JLabel age = new JLabel("  나이 : ");
	JLabel addr = new JLabel("  주소 : ");
	
	JTextField nameTF = new JTextField();
	JTextField ageTF = new JTextField();
	JTextField addrTF = new JTextField();
	
	JButton updateBtn = new JButton("수정");
	JButton deleteBtn = new JButton("삭제");
	JButton insertBtn = new JButton("추가");
	
	public void panelSetting()
	{
		frameCenterPanel.setLayout(new BorderLayout());
		frameCenterPanel.add(scrollpane);
		frameCenterPanel.setBackground(Color.RED);
		frameSouthPanel.setBackground(Color.BLUE);
		frameSouthPanel.setPreferredSize(new Dimension(0,100));
		
//		fspEastPanel.setBackground(Color.YELLOW);
		fspEastPanel.add(name);
		fspEastPanel.add(nameTF);
		fspEastPanel.add(age);
		fspEastPanel.add(ageTF);
		fspEastPanel.add(addr);
		fspEastPanel.add(addrTF);
		fspEastPanel.setLayout(new GridLayout(3,2));
		fspEastPanel.setPreferredSize(new Dimension(this.getWidth()/2,0));
		
		
		fspCenterPanel.add(updateBtn);
		fspCenterPanel.add(deleteBtn);
		fspCenterPanel.add(insertBtn);
		
		fspCenterPanel.setLayout(new GridLayout());
//		fspCenterPanel.setBackground(Color.GREEN);
		fspCenterPanel.setPreferredSize(new Dimension(this.getWidth()/2,0));
		
		frameSouthPanel.setLayout(new BorderLayout());
		frameSouthPanel.add(fspEastPanel, BorderLayout.WEST);
		frameSouthPanel.add(fspCenterPanel, BorderLayout.CENTER);
		
		this.add(frameCenterPanel, BorderLayout.CENTER);
		this.add(frameSouthPanel, BorderLayout.SOUTH);
	}
	
	public MyFrame()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(400,400);
		this.setLayout(new BorderLayout());
		comInit();
		this.setVisible(true);
	}
	public void comInit() {
		panelSetting();
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String name = table.getValueAt(row, 0).toString();
				String age = table.getValueAt(row, 1).toString();
				String addr = table.getValueAt(row, 2).toString();
				
				nameTF.setText(name);
				ageTF.setText(age);
				addrTF.setText(addr);
			}
		});
		insertBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameTF.getText();
				String age = ageTF.getText();
				String addr = addrTF.getText();
				Object[] data = {name, age, addr};
				if(name.length()>0) model.addRow(data);
			}
		});

		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row!=-1) {
					model.removeRow(row);
					nameTF.setText("");
					ageTF.setText("");
					addrTF.setText("");
					table.clearSelection();
				}
			}
		});
		
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row!=-1) {
					table.setValueAt(nameTF.getText(), row, 0);
					table.setValueAt(ageTF.getText(), row, 1);
					table.setValueAt(addrTF.getText(), row, 2);
					table.clearSelection();
				}
			}
		});
	}

}
