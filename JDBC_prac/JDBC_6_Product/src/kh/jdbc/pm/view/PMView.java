package kh.jdbc.pm.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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
		int row = model.getRowCount();
		for(int i = row-1; i>=0 ;i-- )
		{
			model.removeRow(i);
		}
	}
	public void initValue()
	{
		removeAll();
		ArrayList<Product> list = pmC.showList();
		for(Product p :list)
		{
			Object[] obj = {p.getProduct_id(),p.getP_name(),p.getPrice(),p.getDescription()};
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
//				int row = table.getSelectedRow();
//				String name = table.getValueAt(row, 0).toString();
//				String age = table.getValueAt(row, 1).toString();
//				String addr = table.getValueAt(row, 2).toString();
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
		Product p = pmC.serachBtn(productIdRb.isSelected(),
				  productNameRb.isSelected(),
				  searchKey.getText());
		System.out.println(p.toString());
		Object[] obj = {p.getProduct_id(),p.getP_name(),p.getPrice(),p.getDescription()};
		model.addRow(obj);
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
		addBtn.addActionListener(e-> pmC.addProduct());
		btnPanel.add(editBtn);
		editBtn.addActionListener(e-> pmC.editProduct());
		btnPanel.add(delBtn);
		delBtn.addActionListener(e-> pmC.delProduct());
		managePanel.add(btnPanel);
//		managePanel.add(descriptionPanel);
	}
	private JPanel emptyPanel = new JPanel();
	public void emptyInit()
	{
		managePanel.add(emptyPanel);
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
