package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookDelete {
	public void main(String []args) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe ";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			//3. SQL prepared, binding, play
			String query="";
			query += " delete from book ";
			query += "where book_id =? ";
			System.out.println(query);
			
			//문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
			
			//binding
			pstmt.setInt(1,1);
			
			//play
			int count = pstmt.executeUpdate();
			
			System.out.println(count + " 건이 삭제되었습니다  ");
			}catch (ClassNotFoundException e) {
				System.out.println("error : 드라이버 로딩실패 " + e);
			}catch (SQLException e) {
			System.out.println("error: " + e);
	
			}finally {
				try {
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