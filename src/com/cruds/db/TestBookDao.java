package com.cruds.db;

import java.sql.Date;

import com.cruds.test.Author;
import com.cruds.test.Book;
import com.cruds.test.Issue;
import com.cruds.test.Student;


public class TestBookDao 
{
	public static void main(String[] args)
	{
			BookDAO dao = new BookDAO();
			System.out.println(dao.get4());
			
			//System.out.println(dao.get3("1CG19CS003"));
			//dao.create(new Issue("1CG19IS005",currentdate(),currentdate(),106));		
			//System.out.println(dao.get2("JK Rowling"));
			/*List<Book> list = dao.getAll();
			
			for(Book b: list)
			{
				System.out.println(b);
			}*/
			
			//dao.create(new Student("1CG19IS005", "Mohank"));
			//System.out.println(dao.get1("Technical"));
	}

	private static Date currentdate() {
		// TODO Auto-generated method stub
		return null;
	}
}
