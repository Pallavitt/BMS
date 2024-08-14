package com.cruds.swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.cruds.db.BookDAO;
import com.cruds.exception.SMSException;
import com.cruds.test.Issue;
import com.cruds.test.Student;

public class ReturnBooksPanel extends JPanel
{

	private JLabel lblUsn;
	private JTextField txtUsn;
	
	private JButton btnSearch;
	private JButton btnBack;
	
	private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;
	
	final JFrame frame;
	
	 ReturnBooksPanel(JFrame frame)
	{
		this.frame = frame;
		
		lblUsn = new JLabel("Usn:");
		lblUsn.setFont(new Font("Arial", Font.BOLD,16));
		txtUsn = new JTextField(10);
		txtUsn.setFont(new Font("Arial", Font.PLAIN,16));
		
		String[] columnNames = {"Name", "Book title", "Returndate"};
	    model = new DefaultTableModel(columnNames, 0);
	    
	    table = new JTable(model);
	    JScrollPane scrollPane = new JScrollPane(table);
		
		
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Arial", Font.BOLD,16));
		btnSearch.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e) {
				String usn = txtUsn.getText();
				BookDAO dao = new BookDAO();
				
			    model.setRowCount(0);
				try
				{
					 List<Issue> issue = dao.get3(usn);
			
					 if(issue != null && !issue.isEmpty())
	                 {
						 for (int i = 0; i < issue.size(); i++) 
						 {
						     Issue issue1 = issue.get(i);

						     Object[] row = {issue1.getName(), issue1.getBooktitle(), issue1.getReturndate()};
						     model.addRow(row);
						 }

	                 }
	                    else
	                    {
	                    	JOptionPane.showMessageDialog(getParent(), "No books returned for the specified USN", 
                                    "Information", JOptionPane.INFORMATION_MESSAGE);
	                    }
				}
				
				catch(SMSException smse)
				{
					
					JOptionPane.showMessageDialog(getParent(), smse.getInfo(), 
							"ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				

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
		
		add(btnSearch);
		add(btnBack);
		add(scrollPane);
		add(table);
		
		
		setVisible(true);
	}	
}




