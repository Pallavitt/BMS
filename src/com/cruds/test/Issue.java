package com.cruds.test;

import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import com.cruds.exception.SMSException;

public class Issue 
{
	private String usn;
	private Date issueddate;
	private Date returndate;
	private int bookisbn;
	
	private Book book;
	private Student student;
	private String booktitle;
	private String name;
	
	
	
	public Issue(String usn, Date issueddate, Date returndate, int bookisbn) throws SMSException 
	{
		this.usn = usn;
		this.issueddate = issueddate;
		this.returndate = returndate;
		this.bookisbn = bookisbn;
	}

	public Issue(String usn, int bookisbn) throws SMSException
	{
		
		if((usn == null) || usn.equals(""))
		{
			throw new SMSException("Usn cannot be null or empty " + usn);
		}
		
        this.usn = usn;
        this.bookisbn = bookisbn;
        this.issueddate = Date.valueOf(LocalDate.now());
        this.returndate = Date.valueOf(LocalDate.now().plusDays(7));
    }
	
	public Issue(String name, String booktitle, Date returndate) throws SMSException
	{
        this.name = name;
        this.booktitle = booktitle;
        this.returndate = returndate;
    }
	
	
	public Issue(String usn) {
		// TODO Auto-generated constructor stub
	}

	public String getUsn() {
		return usn;
	}


	public void setUsn(String usn) {
		this.usn = usn;
	}


	public Date getIssueddate() {
		return issueddate;
	}


	public void setIssueddate(Date issueddate) {
		this.issueddate = issueddate;
	}


	public Date getReturndate() {
		return returndate;
	}


	public void setReturndate(Date returndate) {
		this.returndate = returndate;
	}


	public int getBookisbn() {
		return bookisbn;
	}


	public void setBookisbn(int bookisbn) {
		this.bookisbn = bookisbn;
	}


	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}
	
	public String getBooktitle() {
		return booktitle;
	}

	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Issue [returndate=" + returndate + ", booktitle=" + booktitle + ", name=" + name + "]";
	}
}