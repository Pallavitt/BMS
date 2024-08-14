package com.cruds.swing;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BookDAOMainFrame extends JFrame {
	
    private JPanel cardPanel;
    private CardLayout cardLayout;
    
    public BookDAOMainFrame() {

    	cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        
        JPanel card1 = new ButtonsMenuPanel(this);
        JPanel card2 = new CreateBookPanel(this);
        JPanel card3 = new DisplayBookPanel(this);
        JPanel card4 = new SearchBookPanel(this);
        JPanel card5 = new CreateStudentPanel(this);
        JPanel card6 = new DisplayStudentPanel(this);
        JPanel card7 = new IssueBookPanel(this);
        JPanel card8 = new DisplayIssueBookPanel(this);
        JPanel card9 = new ReturnBooksPanel(this);
        JPanel card10 = new DisplayReturnBooksPanel(this);
        
        cardPanel.add(card1, "BUTTONS_MENU_PANEL");
        cardPanel.add(card2, "CREATE_BOOK_PANEL");
        cardPanel.add(card3, "DISPLAY_BOOK_PANEL");
        cardPanel.add(card4, "SEARCH_BOOK_PANEL");
        cardPanel.add(card5, "CREATE_STUDENT_PANEL");
        cardPanel.add(card6, "DISPLAY_STUDENT_PANEL");
        cardPanel.add(card7, "CREATE_ISSUE_PANEL");
        cardPanel.add(card8, "DISPLAY_ISSUE_PANEL");
        cardPanel.add(card9, "RETURN_BOOKS_PANEL");
        cardPanel.add(card10, "DISPLAY_RETURN_BOOKS_PANEL");
       
        add(cardPanel);

        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void showCard(String cardName)
    {
    	cardLayout.show(cardPanel, cardName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookDAOMainFrame());
    }
}