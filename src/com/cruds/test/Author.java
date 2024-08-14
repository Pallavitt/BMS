package com.cruds.test;

import com.cruds.exception.SMSException;

public class Author 
{
	private String authorname;
	private String authormailid;
	
	public Author(String authorname, String authormailid) throws SMSException
	{
		if((authorname == null) || authorname.trim().equals(""))
		{
			throw new SMSException("Authorname cannot be null or empty " + authorname);
		}
		
		if((authormailid == null) || authormailid.trim().equals(""))
		{
			throw new SMSException("Authormailid cannot be null or empty " + authormailid);
		}
		
		this.authorname = authorname;
		this.authormailid = authormailid;
	}


	public String getAuthorname() {
		return authorname;
	}


	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}


	public String getAuthormailid() {
		return authormailid;
	}


	public void setAuthormailid(String authormailid) {
		this.authormailid = authormailid;
	}


	@Override
	public String toString() {
		return "Author [authorname=" + authorname + ", authormailid=" + authormailid + "]";
	}
	
	


	


	


	

	
	
	
	

}
