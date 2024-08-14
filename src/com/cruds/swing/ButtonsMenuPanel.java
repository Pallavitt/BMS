package com.cruds.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ButtonsMenuPanel extends JPanel 
{
	JLabel lblButonsMenu;
	
	private JButton btnCreateBook;
	private JButton btnSearchBook;
	private JButton btnDiplayBook;
	private JButton btnCreateStudent;
	private JButton btnIssueBook;
	private JButton btnReturnBooks;
	private JButton btnDisplayReturnBooks;
	private JButton btnExit;

	final JFrame frame;

    public ButtonsMenuPanel(JFrame frame)
    {
    	this.frame = frame;
    	
    	lblButonsMenu = new JLabel("Buttons Menu:");
    	lblButonsMenu.setForeground(Color.DARK_GRAY);
    	lblButonsMenu.setFont(new Font("Times New Roman", Font.BOLD,20));
    	
    	btnCreateBook = new JButton("Create Book");
    	btnCreateBook.setFont(new Font("Arial", Font.BOLD,16));
		btnCreateBook.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				((BookDAOMainFrame)frame).showCard("CREATE_BOOK_PANEL");
			}
		});
		
		btnSearchBook = new JButton("Search Book");
		btnSearchBook.setFont(new Font("Arial", Font.BOLD,16));
		btnSearchBook.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				((BookDAOMainFrame)frame).showCard("SEARCH_BOOK_PANEL");
			}
		});
		
		btnDiplayBook = new JButton("Diplay Books");
		btnDiplayBook.setFont(new Font("Arial", Font.BOLD,16));
		btnDiplayBook.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				((BookDAOMainFrame)frame).showCard("DISPLAY_BOOK_PANEL");
			}
		});
		
		btnCreateStudent = new JButton("Create Student");
		btnCreateStudent.setFont(new Font("Arial", Font.BOLD,16));
		btnCreateStudent.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				((BookDAOMainFrame)frame).showCard("CREATE_STUDENT_PANEL");
			}
		});
		
		btnIssueBook = new JButton("Issue book");
		btnIssueBook.setFont(new Font("Arial", Font.BOLD,16));
		btnIssueBook.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				((BookDAOMainFrame)frame).showCard("CREATE_ISSUE_PANEL");
			}
		});
		
		btnReturnBooks = new JButton("Return Books");
		btnReturnBooks.setFont(new Font("Arial", Font.BOLD,16));
		btnReturnBooks.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				((BookDAOMainFrame)frame).showCard("RETURN_BOOKS_PANEL");
				
			}
		});
		
		btnDisplayReturnBooks = new JButton("Display Return Books");
		btnDisplayReturnBooks.setFont(new Font("Arial", Font.BOLD,16));
		btnDisplayReturnBooks.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				((BookDAOMainFrame)frame).showCard("DISPLAY_RETURN_BOOKS_PANEL");
				
			}
		});
		
		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Arial", Font.BOLD,16));
		btnExit.setBackground(Color.GREEN);
		btnExit.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		});
		
		add(lblButonsMenu);
		add(btnCreateBook);
		add(btnSearchBook);
		add(btnDiplayBook);
		add(btnCreateStudent);
		add(btnIssueBook);
		add(btnReturnBooks);
		add(btnDisplayReturnBooks);
		add(btnExit);
    	
    }
}
    	
    	      
       

        

