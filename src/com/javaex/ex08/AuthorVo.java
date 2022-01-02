package com.javaex.ex08;

public class AuthorVo {
	private int authorId;
	private String authorName;
	private String authorDesc;
	
	//constructor
	public AuthorVo() {}
	
	public AuthorVo(String authorName, String authorDesc) {
		this.authorName=authorName;
		this.authorDesc=authorDesc;
	}

	public AuthorVo(int authorId) {
		this.authorId=authorId;
	}
	
	//getter setter
	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorDesc() {
		return authorDesc;
	}

	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}
	
	
	
	//toString
	
	
}
