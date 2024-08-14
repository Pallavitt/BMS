package com.cruds.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.cruds.db.BookDAO;

public class DisplayBookPanel extends JPanel
{	
    final JFrame frame;
	
	private JTable table;
	private JScrollPane pane;
	private Vector<String> colNames = new Vector<>();
    BookDAO dao = new BookDAO();
	
	JButton btnBack;
	JButton btnDelete;
	
	DisplayBookPanel(JFrame frame)
	{
		this.frame = frame;
		
		colNames.add("Bookisbn");
		colNames.add("Booktitle");
		colNames.add("Category");
		colNames.add("Noofbooks");
		colNames.add("Authorname");
		colNames.add("Authormailid");
		
		table = new JTable(dao.getDataForJTable(), colNames);
		pane = new JScrollPane(table);
		
		table.setRowHeight(40);
		table.setPreferredScrollableViewportSize(new Dimension(800, 700));
		table.setFont(new Font("Arial", Font.PLAIN, 16));
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
			
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Arial", Font.BOLD,16));
		btnBack.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				((BookDAOMainFrame) frame).showCard("CREATE_BOOK_PANEL");
			}
		});
		
		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Arial", Font.BOLD,16));
		btnDelete.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int idx = table.getSelectedRow();
				String bookisbn = (String) table.getModel().getValueAt(idx, 0);
				dao.deletebook(bookisbn);
				table.setModel(new DefaultTableModel(dao.getDataForJTable(),colNames));	
			}
		});
		
		add(pane);
		add(btnBack, BorderLayout.SOUTH);
		add(btnDelete, BorderLayout.SOUTH);
		
		addComponentListener(new ComponentAdapter() 
		{
			public void componentShown(ComponentEvent e)
			{
				table.setModel(new DefaultTableModel(dao.getDataForJTable(), colNames));
			}
		});
		
		setVisible(true);
	}

}

