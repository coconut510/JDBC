package kh.jdbc.pm.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import kh.jdbc.pm.controller.PMController;
import kh.jdbc.pm.model.vo.Product;

public class PMView extends JFrame{
	private PMController pmC = new PMController();
	private GridBagLayout gridbag = new GridBagLayout();
	private GridBagConstraints gbc = new GridBagConstraints();
	
	private JPanel tablePanel = new JPanel();
	private JPanel managePanel = new JPanel();
	private JPanel listPanel = new JPanel();
	private JPanel searchPanel = new JPanel();
	private JPanel descriptionPanel = new JPanel();
	private JPanel btnPanel = new JPanel();
	
	private String[] columNames = {"product_id","p_name","price","description"};
//	private HashMap<Integer,Product> localMap = new HashMap<Integer,Product>();
	private String originId="";
	private Object[][] data = {};
	// 모델 생성(데이터, 컬럼명)
	private	DefaultTableModel model = new DefaultTableModel(data, columNames)
				{
					@Override
					public boolean isCellEditable(int row, int column)
					{
						return false;
					}
				};

	// JTable 생성(모델)
	private JTable table = new JTable(model); 
	JScrollPane scrollpane = new JScrollPane(table);
	
	public PMView()
	{
		this.setTitle("상품 관리 프로그램");
		this.setLayout(new BorderLayout());
		this.setSize(900, 550);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		tableSet();
		listSet();
		searchSet();
		emptyInit();
		descriptionSet();
		btnInit();
		initValue();
		viewInit();
		this.setVisible(true);
	}
	
	public void removeAll()
	{
//		int row = model.getRowCount();
//		for(int i = row-1; i>=0 ;i-- )
//		{
//			model.removeRow(i);
//		}
		model.setRowCount(0);
//		model.getDataVector().removeAllElements();
//		model.fireTableDataChanged();
	}
	public void initValue()
	{
		removeAll();
		ArrayList<Product> list = pmC.showList();
//		localMap.clear();
		int i = 0;
		for(Product p :list)
		{
			Object[] obj = {p.getProduct_id(),p.getP_name(),p.getPrice(),p.getDescription()};
//			localMap.put(i++, p);
			model.addRow(obj);
		}
		this.repaint();
	}
	
	public void tableSet()
	{
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
				if(row!=-1) 
				{
					String id = table.getValueAt(row, 0).toString();
					String name = table.getValueAt(row, 1).toString();
					int price = (int) table.getValueAt(row, 2);
					String desc = table.getValueAt(row, 3).toString();
					productIdTP.setText(id);
					productNameTP .setText(name);
					spinner.setValue(price);
					productDsTP.setText(desc);
					originId = id;
				}
			}
		});
		scrollpane.setSize(new Dimension(550,600));
		tablePanel.add(scrollpane,BorderLayout.CENTER);
	}
	
	private JRadioButton productIdRb = new JRadioButton("Product ID",true);
	private JRadioButton productNameRb = new JRadioButton("Product Name");
	private JButton showListBtn = new JButton("목록 보기");
	private ButtonGroup rGroup = new ButtonGroup();
	private JPanel listTempPanel = new JPanel();
	public void listSet()
	{
		rGroup.add(productIdRb);
		rGroup.add(productNameRb);
		listPanel.setLayout(new GridLayout(0,1));
		listTempPanel.setPreferredSize(new Dimension(300,50));
		showListBtn.addActionListener(e->initValue());
		listTempPanel.add(productIdRb);
		listTempPanel.add(productNameRb);
		listTempPanel.add(showListBtn);

	
	}
	
	private JTextField searchKey = new JTextField();
	private JButton serachBtn = new JButton("검색");
	public void searchSet()
	{
		searchKey.setPreferredSize(new Dimension(250,25));
		searchPanel.setPreferredSize(new Dimension(300,50));
		searchPanel.add(searchKey);
		searchPanel.add(serachBtn);
		serachBtn.addActionListener(e->searchResult());
		
		listPanel.add(listTempPanel);
		listPanel.add(searchPanel);
		managePanel.add(listPanel);
	}
	public void searchResult()
	{
		removeAll();
		ArrayList<Product> list = pmC.serach(productIdRb.isSelected(),
				  productNameRb.isSelected(),
				  searchKey.getText());
		if(list.size()>0) 
		{
			for(Product p :list) 
			{
				Object[] obj = {p.getProduct_id(), p.getP_name(),p.getPrice(),p.getDescription()};
				model.addRow(obj);
			}
		}
		else
		{
			popup( searchKey.getText() + " 상품을 찾을수 없습니다.");
		}
		searchKey.setText("");
	}
	
	private JLabel title = new JLabel("                                             -----상세 보기-----");
	
	private JPanel productIdPanel = new JPanel();
	private JLabel productIdLabel = new JLabel("상품     ID :");
	private JTextField productIdTP = new JTextField();
	
	private JPanel productNamePanel = new JPanel();
	private JLabel productNameLabel = new JLabel("상  품  명 :");
	private JTextField productNameTP = new JTextField();
	
	private JPanel pricePanel = new JPanel();
	private SpinnerModel numberModel = new SpinnerNumberModel(0,0,50000000,1000);
	private JSpinner spinner =new JSpinner(numberModel);
	private JLabel priceLabel = new JLabel("가         격 :  .                                    .");
	
	
	private JPanel productDsPanel =new JPanel();
	private JLabel productDsLabel = new JLabel("상품설명 :");
	private JTextField productDsTP = new JTextField();
	public void descriptionSet()
	{
		title.setPreferredSize(new Dimension(250,20));
		managePanel.add(title);
		
		productIdTP.setPreferredSize(new Dimension(250,25));
		productIdPanel.setLayout(new FlowLayout());
		productIdPanel.add(productIdTP,FlowLayout.LEFT);
		productIdPanel.add(productIdLabel,FlowLayout.LEFT);
		
		productNameTP.setPreferredSize(new Dimension(250,25));
		productNamePanel.setLayout(new FlowLayout());
		productNamePanel.add(productNameLabel);
		productNamePanel.add(productNameTP);
		
		pricePanel.setLayout(new FlowLayout());
		pricePanel.add(priceLabel,FlowLayout.LEFT);
		pricePanel.add(spinner);
		
		productDsTP.setPreferredSize(new Dimension(250,25));
		productNamePanel.setLayout(new FlowLayout());
		productDsPanel.add(productDsLabel);
		productDsPanel.add(productDsTP);	
		
		descriptionPanel.setLayout(new GridLayout(0,1,80,10));
		
		managePanel.add(productIdPanel);
		managePanel.add(productNamePanel);
		managePanel.add(pricePanel);
		managePanel.add(productDsPanel);
		descriptionPanel.setPreferredSize(new Dimension(300,500));
		

//		managePanel.add(descriptionPanel);
	}
	
	private JButton addBtn = new JButton("추가");
	private JButton editBtn = new JButton("수정");
	private JButton delBtn = new JButton("삭제");
	
	public void btnInit()
	{
		btnPanel.add(addBtn);
		addBtn.addActionListener(e-> addProductBtn());
		btnPanel.add(editBtn);
		editBtn.addActionListener(e-> editProductBtn());
		btnPanel.add(delBtn);
		delBtn.addActionListener(e-> delProductBtn());
		managePanel.add(btnPanel);
//		managePanel.add(descriptionPanel);
	}
	
	public void addProductBtn()
	{
		if(pmC.serach(true,false, productIdTP.getText()).size()==0) 
		{
			Product p = new Product();
			p.setProduct_id(productIdTP.getText());
			p.setP_name(productNameTP.getText());
			p.setPrice((int)spinner.getValue());
			p.setDescription(productDsTP.getText());
			
			if(pmC.addProduct(p)>0)
			{
				popup(productNameTP.getText() + "상품이 정상적으로 추가됐습니다.");
				Object[] obj = {p.getProduct_id(),p.getP_name(),p.getPrice(),p.getDescription()};
				model.addRow(obj);
			}
			else popup("상품추가에 실패했습니다.");
		}
		else
		{
			popup("이미 있는 상품입니다.");
		}
	}
	public void editProductBtn()
	{
		if(table.getSelectedRow()!=-1) 
		{
			if(productIdTP.getText().length()>0 && productNameTP.getText().length()>0  && (int)spinner.getValue()>0 &&productDsTP.getText().length()>0 )
			{
				Product p = new Product();
				p.setProduct_id(productIdTP.getText());
				p.setP_name(productNameTP.getText());
				p.setPrice((int)spinner.getValue());
				p.setDescription(productDsTP.getText());
				p.setOrigin_id(originId);
				
				if(pmC.editProduct(p)>0) 
				{
					Object[] obj =  {p.getProduct_id(), p.getP_name(),p.getPrice(),p.getDescription()};
					popup(productNameTP.getText() + "상품이 정상적으로 수정됐습니다.");
					int row = table.getSelectedRow();
					model.insertRow(row, obj);
					model.removeRow(row+1);
				}
				else popup("상품 정보 수정에 실패했습니다.");
			}
			else
			{
				popup("빈 항목이 있거나 가격이 0원이면 상품을 추가할수 없습니다.");
			}
		}
		else
		{
			popup("수정할 상품을 선택해주세요.");
		}
	
	}
	public void delProductBtn()
	{
		if(table.getSelectedRow()!=-1) 
		{
			if(pmC.serach(true,false, productIdTP.getText())!=null) 
			{
				if(pmC.delProduct(productIdTP.getText())>0) 
				{
					popup(productNameTP.getText() + "상품이 정상적으로 삭제됐습니다.");
					model.removeRow(table.getSelectedRow());
				}
				else popup("상품삭제에 실패했습니다.");
			}
			else popup(productIdTP.getText() + " 아이디인 상품이 존재하지 않습니다.");
		}
		else
		{
			popup("수정할 상품을 선택해주세요.");
		}
	}
	private JPanel emptyPanel = new JPanel();
	public void emptyInit()
	{
		managePanel.add(emptyPanel);
	}

	public void popup(String message)
	{
		JOptionPane.showMessageDialog(this,message);
	}
	public void viewInit()
	{
		this.add(tablePanel, BorderLayout.WEST);
		tablePanel.setPreferredSize(new Dimension(550,0));
		tablePanel.setLayout(new BorderLayout());
		
		managePanel.setLayout(new GridLayout(0,1));
		managePanel.setSize(new Dimension(350,800));
		this.add(managePanel, BorderLayout.CENTER);
	}
}
