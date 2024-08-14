package com.cruds.jdbc;

import java.util.List;
import java.util.Scanner;

import com.cruds.db.BookDAO;
import com.cruds.test.Author;
import com.cruds.test.Book;
import com.cruds.test.Issue;

public class BookJdbcDemo 
{
	public static void main(String[] args) 
	{
		BookDAO dao = new BookDAO();
		String choice = "";
		Scanner sc = new Scanner(System.in);
		
		do
		{
			System.out.println("Please select a choice");
			System.out.println("1.Add Book");
			System.out.println("2.Search a book based on book title");
			System.out.println("3.Search books based on category");
			System.out.println("4.Search books based on author");
			System.out.println("5.List All Books along with author information");
			System.out.println("6.Enter a book to issue");
			System.out.println("7.List books issued to student based on usn");
			System.out.println("8.List books which are to be returned for current date");
			System.out.println("9.Exit application");
			
			
			choice = sc.nextLine();
			
			switch(choice)
			{
			case "1":
				System.out.println("Add Book:");
				int bookisbn;
				while(true)
				{
					System.out.println("Enter book isbn:");
					try
					{
						bookisbn = sc.nextInt();
						sc.nextLine();
						break;
					}
					catch(NumberFormatException e)
					{
						System.out.println("Bookisbn should be a number");
					}
				}
				
				System.out.println("Enter book title:");
				String booktitle = sc.nextLine();
				System.out.println("Enter category:");
				String category = sc.nextLine();
				System.out.println("Enter no of books:");
				int noofbooks = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter author name:");
				String authorname = sc.nextLine();
				System.out.println("Enter author mailid:");
				String authormailid = sc.nextLine();
				Author a = new Author(authorname,authormailid);
				dao.create(new Book(bookisbn,booktitle,category,noofbooks,a));
				System.out.println("Book added succesfully");
				break;
				
			case "2":
				System.out.println("Search a book based on book title:");
				String searchbooktitle = sc.nextLine();
				List<Book> listbooktitle = dao.get(searchbooktitle);
				
				boolean found=false;
			    for(Book b: listbooktitle)
			    {
			    	if(b.getBooktitle().startsWith(searchbooktitle))
			    	{
			    		found = true;
			    		System.out.println(b);
			    		break;
			    	}
			    }
			    if(found)
				{
					System.out.println(searchbooktitle + " book found");
				}
				else
				{
					System.out.println(searchbooktitle + " book not found");
				}
				break;
				
			case "3":
				System.out.println("Search books based on category:");
				String searchcategory = sc.nextLine();
				List<Book> listcategory = dao.get1(searchcategory);
				boolean found1 = false;
				for(Book b: listcategory)
				{
					while(b.getCategory().startsWith(searchcategory))
					{
						found1 = true;
						System.out.println(b);
						break;
					}
				}
				if(found1)
				{
					System.out.println(searchcategory + " category found");
				}
				else
				{
					System.out.println(searchcategory + " category not found");
				}
				break;
				
			case "4":
				System.out.println("Search books based on author:");
				String searchauthor = sc.nextLine();
				List<Book> listauthor = dao.get2(searchauthor);
				
				boolean found2 = false;
				for(Book b: listauthor)
				{
					while(b.getAuthor().getAuthorname().startsWith(searchauthor))
					{
						found2 = true;
						System.out.println(b);
						break;
					}
				}
				if(found2)
				{
					System.out.println(searchauthor + " found");
				}
				else
				{
					System.out.println(searchauthor + " Not found");
				}
				break;
					
				
			  case "5":
				System.out.println("Display all books:");
				List<Book> list = dao.getAll();
				
				for(Book b: list)
				{
					System.out.println(b);
				}
				break;
				
			  case "6":
				  System.out.println("Enter a book to issue");
				  System.out.println("Enter the usn of student:");
				  String usn = sc.nextLine();
				  System.out.println("Enter the book isbn:");
				  int isbn = sc.nextInt();
				  dao.create(new Issue(usn,isbn));
				  System.out.println("Book issued successfully");
				  break;
				  
			  case "7":  
				  System.out.println("List books issued to student based on usn:");
				  String searchusn = sc.nextLine();
				  int count = 0;
				  boolean found3 = false;
				  List<Issue> issue = dao.get3(searchusn);
				  for(Issue i: issue)
				  {
					  found3 = true;
					  System.out.println(i);
					  count++;
				  }
				  if(found3)
				  {
					  System.out.println(count + " Books issued to " + searchusn);
				  }
				  else
				  {
					  System.out.println("No books issued to " + searchusn);
				  }
				  break;
				  
				 
			  case "8":
				  System.out.println("List books which are to be returned for current date:");
				  int count1 = 0;
				  List<Issue> bookreturn = dao.get4();
				  
		
				  for(Issue i: bookreturn)
				  {
					  System.out.println(i);
					  count1++;
				  }
				  System.out.println(count1 + " Books are to be returned on current date");
				  break;
				  
			case "9":
				System.out.println("Exit");
				
			default:
				System.out.println("Invalid choice");
			}
		}while(!choice.equals("9"));
		sc.close();
	}
	
}
