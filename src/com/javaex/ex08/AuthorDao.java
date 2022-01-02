package com.javaex.ex08;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class AuthorDao {
	
	//field
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "oracle.jdbc.driver.OracleDriver"  ;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"  ;
	private String id = "webdb" ;
	private String pw = "webdb" ;
	
	//constructor
	public AuthorDao() {}
	
	//method general
	private void getConnection() {
		try {
			//1. JDBC Oracle loading
			Class.forName(driver);
			
			//2. Connection add
			conn = DriverManager.getConnection(url, id, pw);
		}catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 "+ e);
		}catch (SQLException e) {
			System.out.println("error:"+e);
		}
	}
	private void close() {
		try {
			if (rs !=null) {
				rs.close();
			}
			if (pstmt !=null) {
				conn.close();
			}
		}catch (SQLException e) {
			System.out.println("error:"+e);
		}
	}
	
	//add author
	public void authorInsert(AuthorVo authorVo)
	{
		getConnection();	
		try {
			//1,2 none
			//3. SQL문 준비 / 바인딩 / 실행
			
			//make String
			String query ="";
			query += " insert into author ";
			query += " values(seq_author_id.nextval, ?, ? )";
			
			pstmt = conn.prepareStatement(query);
			
			//binding
			pstmt.setString(1,  authorVo.getAuthorName());
			pstmt.setString(2,  authorVo.getAuthorDesc());
			
			//play
			int count = pstmt.executeUpdate();
			
			//
			System.out.println(count + " 건이 저장되었습니다 (author) ");
		}catch (SQLException e) {
			System.out.println("error:"+e);
		}
		close();
	}
	//author Delete
	public void authorDelete(int authorId) {
		//connection
		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
						// 문자열 만들기
						String query = "";
						query += " delete from author ";
						query += " where author_id = ? ";
						//System.out.println(query);

						// 문자열 쿼리문으로 만들기
						pstmt = conn.prepareStatement(query);

						// 바인딩
						pstmt.setInt(1, authorId);

						// 실행
						int count = pstmt.executeUpdate();

						// 4.결과처리
						System.out.println(count + " 건이 삭제되었습니다.(작가)");

					} catch (SQLException e) {
						System.out.println("error:" + e);
					} 
					//자원닫기
					close();
				}
					//작가 수정
	public void authorDelete(AuthorVo authorVo) {
		//connection
		getConnection();
		try {
			String query = "";
			query += "set author_name"
			
		}
	}
	
}
