package com.cruds.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cruds.db.BookDAO;
import com.cruds.exception.SMSException;
import com.cruds.test.Book;
import com.cruds.test.Student;

public class CreateStudentPanel extends JPanel
{
	private JLabel lblUsn;
	private JLabel lblName;
	
	private JTextField txtUsn;
	private JTextField txtName;

	private JButton btnCreate;
	private JButton btnBack;
	
	final JFrame frame;
	
	CreateStudentPanel(JFrame frame)
	{
		this.frame = frame;
		
		lblUsn = new JLabel("USN:");
		lblUsn.setFont(new Font("Arial", Font.BOLD,16));
		txtUsn = new JTextField(10);
		txtUsn.setFont(new Font("Arial", Font.PLAIN,16));
		lblName = new JLabel("Name:");
		lblName.setFont(new Font("Arial", Font.BOLD,16));
		txtName = new JTextField(10);
		txtName.setFont(new Font("Arial", Font.PLAIN,16));
		
		btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Arial", Font.BOLD,16));
		btnCreate.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String usn = txtUsn.getText();
				String name = txtName.getText();
				
				BookDAO dao = new BookDAO();
				
				try
				{
					if(dao.create(new Student(usn,name)))
					{
						JOptionPane.showMessageDialog(getParent(), "Student created successfully", 
														"Success", JOptionPane.INFORMATION_MESSAGE);
						txtUsn.setText("");
						txtName.setText("");
					}
				}
		
				catch(SMSException smse)
				{
					getToolkit().beep();
					JOptionPane.showMessageDialog(getParent(), smse.getInfo(), 
							"ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				((BookDAOMainFrame)frame).showCard("DISPLAY_STUDENT_PANEL");

			}
		});
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Arial", Font.BOLD,16));
		btnBack.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				((BookDAOMainFrame)frame).showCard("BUTTONS_MENU_PANEL");
			}
		});
		
		add(lblUsn);
		add(txtUsn);
		add(lblName);
		add(txtName);
		
		add(btnCreate);
		add(btnBack);
		setVisible(true);
	}
	
}



