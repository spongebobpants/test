package com.javaex.ex01;
import java.sql.*;
public class BookSelectAll {
	
	//private String driver 
	public static void main(String []args) {
		//책+작가 데이터  가져오기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  author.author_id id, ";
			query += "			author_name, ";
			query += "			author_desc, ";
			query += "			book_id, ";
			query += "			title, ";
			query += "			pubs, ";
			query += "			pub_date ";
			query += " from author, book ";
			query += " where book.author_id = author.author_id ";
			//System.out.println(query);
            
			//binding 문자열만들기
			pstmt=conn.prepareStatement(query);
			//play
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				int author_id=rs.getInt("author_id");
				String author_name=rs.getString("author_name");
				String author_desc=rs.getString("author_desc");
				int book_id=rs.getInt("book_id");
				String title=rs.getString("title");
				String pubs=rs.getString("pubs");
				String pub_date=rs.getString("pub_date");
				System.out.println(author_id+","+ author_name+","+author_desc+", "+book_id+", "+title+", "+pubs+", "+pub_date);
			}
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
