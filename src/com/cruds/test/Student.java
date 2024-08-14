package com.cruds.test;

import com.cruds.exception.SMSException;

public class Student
{
	private String usn;
	private String name;
	
	public Student(String usn)
	{
		super();
		this.usn = usn;
	}

	public Student(String usn, String name) throws SMSException
	{
		if((usn == null) || usn.trim().equals(""))
		{
			throw new SMSException("USN cannot be null or empty " + usn);
		}
		
		if((name == null) || name.trim().equals(""))
		{
			throw new SMSException("Name cannot be null or empty " + name);
		}
		
		this.usn = usn;
		this.name = name;
	}
	
	public String getUsn() {
		return usn;
	}
	public void setUsn(String usn) {
		this.usn = usn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Student [usn=" + usn + ", name=" + name + "]";
	}
	
	
	
	
	
	

}
