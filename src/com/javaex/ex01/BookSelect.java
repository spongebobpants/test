package com.javaex.ex01;
import java.sql.*;

public class BookSelect {
	public static void main(String []args) {
		//책 데이터  가져오기
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			//1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query ="";
			query += " select book_id, ";
			query += "        title, ";
			query += "        pubs, ";
			query += "        pub_date, ";
            query += "        author_id ";
            query += " from book ";
			System.out.println(query);
			//문자열 만들기
			pstmt=conn.prepareStatement(query);
			//실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bookId=rs.getInt(1);
				String title=rs.getString(2);
				String pubs=rs.getString(3);
				String pub_date=rs.getString(4);
				int authorId=rs.getInt(5);
				System.out.println(bookId +","+title+","+pubs+","+pub_date+","+authorId);
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

			