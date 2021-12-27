package com.javaex.ex01;

import java.sql.*;

public class BookUpdate {
	public static void main(String []args) {
		
		//0. import java.sql.*;
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			//1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("성공");
			
			//3.SQL prepared, binding, play
			String query = "" ;
			query += "update into book";
			query += "values(seq_book_id.nextval, ?, ?)";
			System.out.println(query);
			
			pstmt=conn.prepareStatement(query);
			
			pstmt.setString(1,"");
			pstmt.setInt(2,1);
			
			int count =pstmt.executeUpdate();
			
			System.out.println(count + " 건이 저장되었습니다 ");
			
			}catch(ClassNotFoundException e) {
				System.out.println("error: 드라이버 로딩 실패 - " + e);
			} catch (SQLException e) {
			System.out.println("error:" + e);
			} finally {
			// 5. 자원정리
			try {
				if (conn != null) {
				conn.close();
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
