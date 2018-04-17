package kh.java.com.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MyFrame2 extends JFrame {
	// 스피너
	// 버튼
	// 출력
	
	// 스피너 모델 생성
	SpinnerModel numberModel = new SpinnerNumberModel(0,-5000000,5000000,10000);
	// 스피너 생성
	JSpinner spinner =new JSpinner(numberModel);
	// 버튼 생성
	JButton button = new JButton("출력");
	
	// 출력 라벨 생성
	JLabel label = new JLabel("0");
	public void comInit()
	{
		spinner.setSize(new Dimension(100,100));
		label.setPreferredSize(new Dimension(400,300));
		this.add(spinner,BorderLayout.CENTER);
		this.add(button, BorderLayout.SOUTH);
		this.add(label, BorderLayout.NORTH);
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				label.setText(spinner.getValue().toString());
			}
			
		});
	}
	public MyFrame2()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		comInit();
		this.setSize(400,400);
		this.setVisible(true);
	}
}