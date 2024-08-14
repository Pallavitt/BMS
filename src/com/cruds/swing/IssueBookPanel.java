package com.cruds.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.cruds.db.BookDAO;
import com.cruds.exception.SMSException;
import com.cruds.test.Author;
import com.cruds.test.Book;
import com.cruds.test.Issue;
import com.cruds.test.Student;

public class IssueBookPanel extends JPanel
{
	
	private JLabel lblUsn;
	private JLabel lblBookisbn;
	
	private JTextField txtUsn;
	private JTextField txtBookisbn;
	
	private JButton btnIssue;
	private JButton btnBack;
	
	final JFrame frame;
	
	JTable table = new JTable();
	DefaultTableModel model = new DefaultTableModel();
	BookDAO dao = new BookDAO();
	List<Book> list = dao.getAll();
	List<Vector<String>> data = dao.getDataForJTable1();
	
	IssueBookPanel(JFrame frame)
	{
		this.frame = frame;
		
		lblUsn = new JLabel("USN:");
		lblUsn.setFont(new Font("Arial", Font.BOLD,16));
		txtUsn = new JTextField(10);
		txtUsn.setFont(new Font("Arial", Font.PLAIN,16));
		lblBookisbn = new JLabel("Book isbn:");
		lblBookisbn.setFont(new Font("Arial", Font.BOLD,16));
		txtBookisbn = new JTextField(10);
		txtBookisbn.setFont(new Font("Arial", Font.PLAIN,16));
		
		table.setRowHeight(40);
		table.setPreferredScrollableViewportSize(new Dimension(700, 600));
		table.setFont(new Font("Arial", Font.PLAIN, 16));
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
	
		btnIssue = new JButton("Issue Book");
		btnIssue.setFont(new Font("Arial", Font.BOLD,16));
		btnIssue.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String usn = txtUsn.getText();
				String bookisbn = txtBookisbn.getText();
				
				BookDAO dao = new BookDAO();
				
				try
				{
					if(dao.create(new Issue(usn,Integer.parseInt(bookisbn))))
					{				
						JOptionPane.showMessageDialog(getParent(), "Issued book Successfully", 
														"Success", JOptionPane.INFORMATION_MESSAGE);
						txtUsn.setText("");
						txtBookisbn.setText("");	
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
				
				((BookDAOMainFrame)frame).showCard("DISPLAY_ISSUE_PANEL");

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
		
		model.addColumn("Bookisbn");
		model.addColumn("Book Title");
		model.addColumn("Category");
		model.addColumn("No of books");
		
		for(Book book : list) 
		{
			model.addRow(new Object[] {book.getBookisbn(), book.getBooktitle(), book.getCategory(), book.getNoofbooks()});
		}
		
		DefaultTableModel usnModel = new DefaultTableModel();
		usnModel.addColumn("USN");
		usnModel.addColumn("Name");
		
		JTable usnTable = new JTable();
		usnTable.setModel(usnModel);
		
		usnTable.setRowHeight(40);
		usnTable.setPreferredScrollableViewportSize(new Dimension(500, 500));
		usnTable.setFont(new Font("Arial", Font.PLAIN, 16));
		usnTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		
		for(Vector<String> student : data)
		{
		    usnModel.addRow(new Object[] {student.get(0), student.get(1)});
		}
		
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e) 
			{
				if (e.getValueIsAdjusting()) 
				{
					return;
				}

				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) 
				{
					Object selectedBookIsbn = table.getValueAt(selectedRow, 0); 
					if (selectedBookIsbn != null)
					{
						String bookIsbn = selectedBookIsbn.toString();
						txtBookisbn.setText(bookIsbn); 
					}
				}
			}
		});
		
		JPanel tablePanel = new JPanel();
		tablePanel.add(new JScrollPane(table));
		add(tablePanel);
		
		usnTable.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e) 
			{
				if (e.getValueIsAdjusting()) 
				{
					return;
				}

				int selectedRow = usnTable.getSelectedRow();
				if (selectedRow != -1) 
				{
					Object selectedUsn = usnTable.getValueAt(selectedRow, 0); 
					if (selectedUsn != null)
					{
						String usn = selectedUsn.toString();
						txtUsn.setText(usn); 
					}
				}
			}
		});

		JPanel usnTablePanel = new JPanel();
		usnTablePanel.add(new JScrollPane(usnTable));
		add(usnTablePanel);

		table.setModel(model);
		
		add(lblUsn);
		add(txtUsn);
		add(lblBookisbn);
		add(txtBookisbn);
		
		
		add(btnIssue);
		add(btnBack);
		
		setVisible(true);
	}
}


