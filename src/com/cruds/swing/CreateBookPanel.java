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
import com.cruds.test.Author;
import com.cruds.test.Book;

public class CreateBookPanel extends JPanel{

	private JLabel lblBookisbn;
	private JLabel lblBooktitle;
	private JLabel lblCategory;
	private JLabel lblNoofbooks;
	private JLabel lblAuthorname;
	private JLabel lblAuthormailid;

	private JTextField txtBookisbn;
	private JTextField txtBooktitle;
	private JTextField txtCategory;
	private JTextField txtNoofbooks;
	private JTextField txtAuthorname;
	private JTextField txtAuthormailid;

	private JButton btnCreate;
	private JButton btnBack;
	
	final JFrame frame;
	
	CreateBookPanel(JFrame frame)
	{
		this.frame = frame;
		
		lblBookisbn = new JLabel("Book isbn:");
		lblBookisbn.setFont(new Font("Arial", Font.BOLD,16));
		txtBookisbn = new JTextField(10);
		txtBookisbn.setFont(new Font("Arial", Font.PLAIN,16));
		lblBooktitle = new JLabel("Book title:");
		lblBooktitle.setFont(new Font("Arial", Font.BOLD,16));
		txtBooktitle = new JTextField(10);
		txtBooktitle.setFont(new Font("Arial", Font.PLAIN,16));
		lblCategory = new JLabel("Category:");
		lblCategory.setFont(new Font("Arial", Font.BOLD,16));
		txtCategory = new JTextField(10);
		txtCategory.setFont(new Font("Arial", Font.PLAIN,16));
		lblNoofbooks = new JLabel("No of Books:");
		lblNoofbooks.setFont(new Font("Arial", Font.BOLD,16));
		txtNoofbooks = new JTextField(10);
		txtNoofbooks.setFont(new Font("Arial", Font.PLAIN,16));
		lblAuthorname = new JLabel("Author name:");
		lblAuthorname.setFont(new Font("Arial", Font.BOLD,16));
		txtAuthorname = new JTextField(10);
		txtAuthorname.setFont(new Font("Arial", Font.PLAIN,16));
		lblAuthormailid = new JLabel("Author mailid:");
		lblAuthormailid.setFont(new Font("Arial", Font.BOLD,16));
		txtAuthormailid = new JTextField(10);
		txtAuthormailid.setFont(new Font("Arial", Font.PLAIN,16));
		
		btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Arial", Font.BOLD,16));
		btnCreate.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookisbn = txtBookisbn.getText();
				String booktitle = txtBooktitle.getText();
				String category = txtCategory.getText();
				String noofbooks = txtNoofbooks.getText();
				String authorname = txtAuthorname.getText();
				String authormailid = txtAuthormailid.getText();
				
				BookDAO dao = new BookDAO();
				
				try
				{
					Author author = new Author(authorname, authormailid);
					
					if(dao.create(new Book(Integer.parseInt(bookisbn),booktitle,category,Integer.parseInt(noofbooks),author)))
					{
						JOptionPane.showMessageDialog(getParent(), "Book created successfully", 
														"Success", JOptionPane.INFORMATION_MESSAGE);
						txtBookisbn.setText("");
						txtBooktitle.setText("");
						txtCategory.setText("");
						txtNoofbooks.setText("");
						txtAuthorname.setText("");
						txtAuthormailid.setText("");
					}	
				}
				catch(NumberFormatException nfe)
				{
					getToolkit().beep();
					JOptionPane.showMessageDialog(getParent(), "Bookisbn should be a valid number", 
							"ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				catch(SMSException smse)
				{
					getToolkit().beep();
					JOptionPane.showMessageDialog(getParent(), smse.getInfo(), 
							"ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				((BookDAOMainFrame)frame).showCard("DISPLAY_BOOK_PANEL");

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
		
		add(lblBookisbn);
		add(txtBookisbn);
		add(lblBooktitle);
		add(txtBooktitle);
		add(lblCategory);
		add(txtCategory);
		add(lblNoofbooks);
		add(txtNoofbooks);
		add(lblAuthorname);
		add(txtAuthorname);
		add(lblAuthormailid);
		add(txtAuthormailid);
		
		add(btnCreate);
		add(btnBack);
		setVisible(true);
	}
	
}

