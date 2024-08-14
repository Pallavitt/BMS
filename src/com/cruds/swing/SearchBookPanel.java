package com.cruds.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.cruds.db.BookDAO;
import com.cruds.test.Author;
import com.cruds.test.Book;
import com.cruds.test.Issue;

public class SearchBookPanel extends JPanel
{
	private JButton btnSearchbybooktitle;
	private JButton btnSearchbyauthorname;
	private JButton btnSearchbycategory;
	private JButton btnSearchbyusn;
	private JButton btnBack;
	
	BookDAO dao = new BookDAO();
	
	final JFrame frame;
	
	public SearchBookPanel(JFrame frame)
	{
		this.frame = frame;
		
		
		btnSearchbybooktitle = new JButton("Search by Book title");
		btnSearchbybooktitle.setFont(new Font("Arial", Font.BOLD,16));
		btnSearchbybooktitle.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				SearchbyBooktitle();
			}
		});
		
		btnSearchbyauthorname = new JButton("Search by Authorname");
		btnSearchbyauthorname.setFont(new Font("Arial", Font.BOLD,16));
		btnSearchbyauthorname.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				SearchbyAuthorname();
			}
		});
		
		btnSearchbycategory = new JButton("Search by Category");
		btnSearchbycategory.setFont(new Font("Arial", Font.BOLD,16));
		btnSearchbycategory.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				SearchbyCategory();
			}
		});
		
		btnSearchbyusn = new JButton("Search by Usn");
		btnSearchbyusn.setFont(new Font("Arial", Font.BOLD,16));
		btnSearchbyusn.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				SearchbyUsn();
			}
		});
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Arial", Font.BOLD,16));
		btnBack.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				((BookDAOMainFrame) frame).showCard("BUTTONS_MENU_PANEL");
			}
		});
		
		JPanel SearchButtons = new JPanel(new FlowLayout(4));
		SearchButtons.add(btnSearchbybooktitle);
		SearchButtons.add(btnSearchbyauthorname);
		SearchButtons.add(btnSearchbycategory);
		SearchButtons.add(btnSearchbyusn);
		SearchButtons.add(btnBack);
		
		add(SearchButtons);
	}
	
	private void SearchbyBooktitle()
	{
		String booktitle = getInput("Enter the Book title to search:" , "Search by Book title");
		if(isValidInput(booktitle))
		{
			List<Book> searchResult = dao.get(booktitle);
			displaySearchResult(searchResult);
		}
	}
	
	private void SearchbyAuthorname()
	{
		String authorname = getInput("Enter the Authorname to search:" , "Search by Authorname");
		if(isValidInput(authorname))
		{
			List<Book> searchResult = dao.get2(authorname);
			displaySearchResult(searchResult);
		}
	}
	
	private void SearchbyCategory()
	{
		String category = getInput("Enter the Category to search:" , "Search by Category");
		if(isValidInput(category))
		{
			List<Book> searchResult = dao.get1(category);
			displaySearchResult(searchResult);
		}
	}
	
	private void SearchbyUsn()
	{
		String usn = getInput("Enter the usn to search:" , "Search by Usn");
		if(isValidInput(usn))
		{
			List<Issue> searchResult = dao.get3(usn);
			displaySearchResult1(searchResult);
		}
	}
	
	private boolean isValidInput(String input)
	{
		if (input == null || input.trim().isEmpty())
		{
			showMessage("Enter the Book title, Authorname, Category or Usn" , "Input Error" , JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	private void displaySearchResult(List<Book> searchResult)
	{
		if(searchResult != null && !searchResult.isEmpty())
		{
			JFrame resultFrame = new JFrame("Search Results:");
			resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			String[] columnNames = {"Bookisbn", "Book Title", "Category", "No of Books", "Author Name", "Author mailid"};
            Object[][] rowData = new Object[searchResult.size()][columnNames.length];

            for (int i = 0; i < searchResult.size(); i++)
            {
                Book book = searchResult.get(i);
                Author author = book.getAuthor();

                rowData[i][0] = book.getBookisbn();
                rowData[i][1] = book.getBooktitle();
                rowData[i][2] = book.getCategory();
                rowData[i][3] = book.getNoofbooks();
                rowData[i][4] = author.getAuthorname();
                rowData[i][5] = author.getAuthormailid();
            }
            
            JTable table = new JTable(rowData, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            resultFrame.add(scrollPane);

            resultFrame.pack();
            resultFrame.setLocationRelativeTo(this);
            resultFrame.setVisible(true);
        }
		else
		{
            showMessage("No books found.", "No Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }
	
	private void displaySearchResult1(List<Issue> searchResult)
	{
	    if(searchResult != null && !searchResult.isEmpty())
	    {
	        JFrame resultFrame = new JFrame("Search Results:");
	        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        
	        String[] columnNames = {"Name", "Book Title", "Return Date"};
	        Object[][] rowData = new Object[searchResult.size()][columnNames.length];

	        for (int i = 0; i < searchResult.size(); i++) 
	        {
	            Issue issue = searchResult.get(i);

	            rowData[i][0] = issue.getName();
	            rowData[i][1] = issue.getBooktitle();
	            rowData[i][2] = issue.getReturndate();
	            
	        }
	        
	        JTable table = new JTable(rowData, columnNames);
	        JScrollPane scrollPane = new JScrollPane(table);
	        resultFrame.add(scrollPane);

	        resultFrame.pack();
	        resultFrame.setLocationRelativeTo(this);
	        resultFrame.setVisible(true);
	    }
	    else
	    {
	        showMessage("No books to return.", "No Results", JOptionPane.INFORMATION_MESSAGE);
	    }
	}

    private String getInput(String message, String title) 
    {
        return JOptionPane.showInputDialog(this, message, title, JOptionPane.PLAIN_MESSAGE);
    }

    private void showMessage(String message, String title, int messageType) 
    {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
}


		
	