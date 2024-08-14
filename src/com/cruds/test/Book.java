package com.cruds.test;

import com.cruds.exception.SMSException;

public class Book
{ 
	private int bookisbn;
	private String booktitle;
	private String category;
	private int noofbooks;
	
	private Author author;
	
	
	public Book(int bookisbn, String booktitle, String category, int noofbooks, Author author) throws SMSException 
	{
		this(bookisbn,booktitle,category,noofbooks);
		this.author = author;
	}


	public Book(int bookisbn, String booktitle, String category, int noofbooks) throws SMSException
	{
		if((booktitle == null) || booktitle.trim().equals(""))
		{
			throw new SMSException("Booktitle cannot be null or empty " + booktitle);
		}
		
		if((category == null) || category.trim().equals(""))
		{
			throw new SMSException("Category cannot be null or empty " + category );
		}
		
		this.bookisbn = bookisbn;
		this.booktitle = booktitle;
		this.category = category;
		this.noofbooks = noofbooks;
	}
	
	

	public int getBookisbn() {
		return bookisbn;
	}
	public void setBookisbn(int bookisbn) {
		this.bookisbn = bookisbn;
	}
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getNoofbooks() {
		return noofbooks;
	}
	public void setNoofbooks(int noofbooks) {
		this.noofbooks = noofbooks;
	}

	public Author getAuthor()
	{
		return author;
	}
	
	public void setAuthor(Author author)
	{
		this.author = author;
	}


	@Override
	public String toString() {
		return "Book [bookisbn=" + bookisbn + ", booktitle=" + booktitle + ", category=" + category + ", noofbooks="
				+ noofbooks + ", author=" + author + "]";
	}
	
	
	
	

}
