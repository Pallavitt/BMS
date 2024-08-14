package com.cruds.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import com.cruds.exception.SMSException;
import com.cruds.test.Author;
import com.cruds.test.Book;
import com.cruds.test.Issue;
import com.cruds.test.Student;

public class BookDAO 
{
	public boolean create(Book b) throws SMSException//To create 
	{
		String sql = "insert into book(bookisbn,booktitle,category,noofbooks) values(?,?,?,?)";
		String sql2 = "insert into author(authorname,authormailid,bookisbn) values (?,?,?)";
		int rows = 0;
		int rows2 = 0;
		
		try(Connection conn = DBConnection.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, b.getBookisbn());
			ps.setString(2, b.getBooktitle());
			ps.setString(3, b.getCategory());
			ps.setInt(4, b.getNoofbooks());
			
			rows = ps.executeUpdate();
			
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps2.setString(1, b.getAuthor().getAuthorname());
			ps2.setString(2, b.getAuthor().getAuthormailid());
			ps2.setInt(3, b.getBookisbn());

			rows2 = ps2.executeUpdate();
		}
			
			catch(SQLException sqle)
			{
				if(sqle.getMessage().contains("Duplicate"))
				{
					throw new SMSException("Bookisbn already exists: " + b.getBookisbn());
				}
				else
				{
					throw new SMSException("Database error: " + sqle.getMessage());
				}
				//e.printStackTrace();
			}
			return rows>0;
	}
	
	
	public boolean create(Student s) 
 	{
 		String sql = "insert into student(usn, name) values(?,?)";		
 		int rows = 0;
 		
 		try(Connection conn = DBConnection.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s.getUsn());
			ps.setString(2, s.getName());
		
			rows = ps.executeUpdate();
		}
 		catch(SQLException e)
 		{
 			e.printStackTrace();
 		}
 		return rows>0;
 	}
	
	public boolean create(Issue i) 
 	{
 		String sql = "insert into issue(usn, issueddate, returndate, bookisbn) values(?,?,?,?)";		
 		int rows = 0;
 	
 		try(Connection conn = DBConnection.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, i.getUsn());
			ps.setDate(2, Date.valueOf(LocalDate.now()));
			ps.setDate(3, Date.valueOf(LocalDate.now().plusDays(7)));
			ps.setInt(4, i.getBookisbn());
			
			rows = ps.executeUpdate();
		}
 		catch(SQLException e)
 		{
			e.printStackTrace();
		}
		return rows>0;	
 	}
	
	

	public List<Book> get(String booktitle)
	{
		String sql = "select b.bookisbn,b.booktitle,b.category,b.noofbooks,a.authorname,a.authormailid from book b,author a where b.bookisbn = a.bookisbn and booktitle like?";
	    Book b = null;
		Author a = null;
		List<Book> listbooktitle = new ArrayList<>();
		
		try(Connection conn = DBConnection.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + booktitle + "%");
			ResultSet rs = ps.executeQuery();
			
			if(rs != null && rs.next())
			{
				a = new Author(rs.getString(5),rs.getString(6));
				b = new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),a);
				listbooktitle.add(b);
			}
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return listbooktitle;
	}
	
	
	public List<Book> get1(String category)
	{
		String sql = "select b.bookisbn,b.booktitle,b.category,b.noofbooks,a.authorname,a.authormailid from book b,author a where b.bookisbn = a.bookisbn and category=?";
	    Book b = null;
	    Author a = null;
	    List<Book> listcategory = new ArrayList<>();
		
		
		try(Connection conn = DBConnection.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, category);
			ResultSet rs = ps.executeQuery();
			
			while(rs != null && rs.next())
			{
				a = new Author(rs.getString(5),rs.getString(6));
				b = new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),a);
				listcategory.add(b);
			}
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return listcategory;
	}
	

	
	public List<Book> get2(String authorname)
	{
		String sql = "select b.bookisbn,b.booktitle,b.category,b.noofbooks,a.authorname,a.authormailid from book b,author a where b.bookisbn = a.bookisbn and authorname like?";
		Book b = null;
	    Author a = null;
	    List<Book> listauthor = new ArrayList<>();
		
		
		try(Connection conn = DBConnection.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + authorname + "%");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs != null && rs.next())
			{
				a = new Author(rs.getString(5),rs.getString(6));
				b = new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),a);
				listauthor.add(b);
			}
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return listauthor;
	}
	
 	public List<Book> getAll()
	{
		String sql = "select b.bookisbn,b.booktitle,b.category,b.noofbooks,a.authorname,a.authormailid from book b, author a where b.bookisbn=a.bookisbn";
		Book b = null;
		Author a = null;
		List<Book> list = new ArrayList<>();

		try(Connection conn = DBConnection.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs != null && rs.next())
			{
				a = new Author(rs.getString(5), rs.getString(6));
				b = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),a);
				list.add(b);
			}
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
		} 
		return list;
	}
 	
 	public Vector<Vector<String>> getDataForJTable()
	{
		String sql = "select b.bookisbn,b.booktitle,b.category,b.noofbooks,a.authorname,a.authormailid from book b, author a where b.bookisbn=a.bookisbn";
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		Vector<String> row = null;
		
		try(Connection conn = DBConnection.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs != null && rs.next())
			{
				row = new Vector<>();
				row.add(String.valueOf(rs.getInt(1)));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(String.valueOf(rs.getInt(4)));
				row.add(rs.getString(5));
				row.add(rs.getString(6));
				data.add(row);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		} 
		return data;
		
	}
 	
 	public boolean deletebook(String bookisbn)
	{
		String sql = "delete from author where bookisbn=?";
		int rows = 0;
		
		try(Connection conn = DBConnection.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, bookisbn);
			
			rows = ps.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return rows > 0;
	}
	
 	
 	  public boolean create(String usn, int isbn)
 	  {
 		 String sql = "insert into issue(usn, isbn, issue_date, return_date) VALUES (?, ?, currentdate, currentdate)";
	     try(Connection conn = DBConnection.getConnection())
	     {
	    	 PreparedStatement ps = conn.prepareStatement(sql);
	    	 ps.setString(1, usn);
	         ps.setInt(2, isbn);

	         ps.executeUpdate();
	     }
	     catch(SQLException e)
	     {
	    	 e.printStackTrace();
	     }
		return false;
	     
 	  }
	    		
 	public List<Issue> get3(String usn)
	{
		String sql = "select s.name,b.booktitle,i.returndate from book b,issue i,student s where i.bookisbn = b.bookisbn and i.usn = s.usn and s.usn=?";
		Issue i = null;
	
		List<Issue> issue = new ArrayList<>();
		
		try(Connection conn = DBConnection.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, usn);
			ResultSet rs = ps.executeQuery();
			
			while(rs != null && rs.next())
			{
				i = new Issue(rs.getString("name"),rs.getString("booktitle"),rs.getDate("returndate"));
				issue.add(i);
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return issue;
	}
 	
 	public List<Issue> get4()
 	{
 		String sql = "select s.name, b.booktitle, i.returndate from book b, student s, issue i where b.bookisbn = i.bookisbn and s.usn = i.usn and i.returndate = curdate()";
 	    Issue i = null;
 		List<Issue> bookreturn = new ArrayList<>(); 	
 	    
 	    try(Connection conn = DBConnection.getConnection())
 	    {
 	    	PreparedStatement ps = conn.prepareStatement(sql);
 	    	ResultSet rs = ps.executeQuery();
 	    	
 	    	while(rs != null && rs.next())
 	    	{
 	    		i = new Issue(rs.getString(1),rs.getString(2),rs.getDate(3));
 	    		bookreturn.add(i);
 	    	}
 	    }
 	    catch(SQLException e)
 	    {
 	    	e.printStackTrace();
 	    }
 	    return bookreturn;
 	}
 	
 	public Vector<Vector<String>> getDataForJTable1()
	{

		String sql = "select usn,name from student";
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		Vector<String> row = null;
		
		try(Connection conn = DBConnection.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs != null && rs.next())
			{
				row = new Vector<>();
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				data.add(row);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		} 
		return data;
	}
 	

	public Vector<Vector<String>> getDataForJTable2()
	{

		String sql = "select usn,issueddate,returndate,bookisbn from issue";
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		Vector<String> row = null;
		
		try(Connection conn = DBConnection.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs != null && rs.next())
			{
				row = new Vector<>();
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(rs.getString(4));
				data.add(row);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		} 
		return data;
	}	
	
	public Vector<Vector<String>> getDataForJTable3() {
	    String sql = "select s.name, b.booktitle, i.returndate from book b, student s, issue i where b.bookisbn = i.bookisbn and s.usn = i.usn and i.returndate = curdate()";
	    Vector<Vector<String>> data = new Vector<Vector<String>>();
	    Vector<String> row = null;

	    try (Connection conn = DBConnection.getConnection()) {
	        PreparedStatement ps = conn.prepareStatement(sql);

	        ResultSet rs = ps.executeQuery();

	        while (rs != null && rs.next()) {
	            row = new Vector<>();
	            row.add(rs.getString(1));
	            row.add(rs.getString(2));
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            row.add(dateFormat.format(rs.getDate(3)));
	            data.add(row);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return data;
	}

}



		

	

	



