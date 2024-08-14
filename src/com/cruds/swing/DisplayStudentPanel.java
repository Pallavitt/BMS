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

public class DisplayStudentPanel extends JPanel{
	
    final JFrame frame;
	
	private JTable table;
	private JScrollPane pane;
	private Vector<String> colNames = new Vector<>();
	
    BookDAO dao = new BookDAO();
	
	JButton btnBack;
	
	DisplayStudentPanel(JFrame frame)
	{
		this.frame = frame;
		colNames.add("Usn");
		colNames.add("Name");
		
		table = new JTable(dao.getDataForJTable1(), colNames);
		pane = new JScrollPane(table);
		
		table.setRowHeight(40);
		table.setPreferredScrollableViewportSize(new Dimension(700, 600));
		table.setFont(new Font("Arial", Font.PLAIN, 16));
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Arial", Font.BOLD,16));
		btnBack.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				((BookDAOMainFrame)frame).showCard("CREATE_STUDENT_PANEL");
			}
		});
		
		add(pane);
		add(btnBack, BorderLayout.SOUTH);
		
		
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e)
			{
				table.setModel(new DefaultTableModel(dao.getDataForJTable1(), colNames));
			}
		});
		setVisible(true);
	}

}

