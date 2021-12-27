package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorUpdate {
	
	public static void main (String []args) {
		
	// 0. import java.sql.*;
	Connection conn=null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
	// 1. JDBC 드라이버 (Oracle) 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
	// 2. Connection 얻어오기
		String url= "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(url, "webdb", "webdb");
		
	// 3. SQL문 준비 / 바인딩 / 실행
	String query="";
	//query=query+"문자열"
	query += "update into author" ;
	query += " values(seq_author_id.nextval,?,?) " ;
	System.out.println(query);
	System.out.println("success");
	//문자열 쿼리문으로 만들기
	pstmt=conn.prepareStatement(query);
	//바인딩
	pstmt.setString(1,"이문열");
	pstmt.setString(2,"경북 영양");
	//실행
	int count =pstmt.executeUpdate();
	// 4.결과처리
	} catch (ClassNotFoundException e) {
	System.out.println("error: 드라이버 로딩 실패 - " + e);
	} catch (SQLException e) {
	System.out.println("error:" + e);
	} finally {
	// 5. 자원정리
	try {
	if (rs != null) {
	rs.close();
	}
	if (pstmt != null) {
	pstmt.close();
	}
	if (conn != null) {
	conn.close();
	}
	} catch (SQLException e) {
	System.out.println("error:" + e);
	}
	}

	}
}
