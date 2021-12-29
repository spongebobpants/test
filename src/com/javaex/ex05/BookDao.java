package com.javaex.ex05;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class BookDao {
	//field
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	//생성자
	public BookDao() {}
	//g/s
	//method
	//add book
	public void BookInsert(int authorId, String title, String pub, String pubDate) {
		// 0. import java.sql.*;
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				// 1. JDBC 드라이버 (Oracle) 로딩
				Class.forName(driver);
				
				// 2. Connection 얻어오기
				conn = DriverManager.getConnection(url, id, pw);
				
				// 3. SQL문 준비 / 바인딩 / 실행
				//문자열 만들기
				String query ="";
				query += " insert into book ";
				query += " values(seq_book_id.nextval, ?, ?, ?, ? )";
				
				// 문자열 쿼리문으로 만들기
				pstmt = conn.prepareStatement(query);
				
				// 바인딩
				pstmt.setInt(1, authorId); // 1
				pstmt.setString(2, title); // 2
				pstmt.setString(3,  pub);
				pstmt.setString(4, pubDate);
				
				//실행
				int count = pstmt.executeUpdate();
				
				//4.결과 처리
				System.out.println(count + "건이 생성되었습니다(book)");
			} catch (ClassNotFoundException e) {
				System.out.println("error: 드라이버 로딩 실패 - " + e);
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}finally {

				// 5. 자원정리
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
			//book update
			public void BookUpdate(int bookId, String title) {
				// 0. import java.sql.*;
				Connection conn = null;
				PreparedStatement pstmt = null;
				// ResultSet rs = null;

				try {
					// 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName(driver);

					// 2. Connection 얻어오기
					conn = DriverManager.getConnection(url, id, pw);

					// 3. SQL문 준비 / 바인딩 / 실행
					// 문자열 만들기
					String query ="";
					query += " update book ";
					query += " where book_id ";
					query += "       title ";
					query += " where book_id =? ";
					//System.out.println(query);
					
					//문자열을 쿼리문으로 만들기
					pstmt = conn.prepareStatement(query);
					
					//바인딩
					pstmt.setString(1, title); // 1
					pstmt.setInt(2, bookId); // 2
					
					// 실행
					int count = pstmt.executeUpdate();
					
					// 4.결과처리
					System.out.println(count + " 건이 저장되었습니다.(book)");
				} catch (ClassNotFoundException e) {
					System.out.println("error: 드라이버 로딩 실패 - " + e);
				} catch (SQLException e) {
					System.out.println("error:" + e);
				} finally {

					// 5. 자원정리
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

			//책 삭제
			public void BookDelete(int bookId) {
				// 0. import java.sql.*;
				Connection conn = null;
				PreparedStatement pstmt = null;
				// ResultSet rs = null;
				try {
					// 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName(driver);

					// 2. Connection 얻어오기
					conn = DriverManager.getConnection(url, id, pw);

					// 3. SQL문 준비 / 바인딩 / 실행
					// 문자열 만들기
					String query = "";
					query += " delete from book ";
					query += " where  book_id = ? ";
					//System.out.println(query);

					// 문자열 쿼리문으로 만들기
					pstmt = conn.prepareStatement(query);

					// 바인딩
					pstmt.setInt(1, bookId);

					// 실행
					int count = pstmt.executeUpdate();

					// 4.결과처리
					System.out.println(count + " 건이 삭제되었습니다.(book)");

				} catch (ClassNotFoundException e) {
					System.out.println("error: 드라이버 로딩 실패 - " + e);
				} catch (SQLException e) {
					System.out.println("error:" + e);
				} finally {

					// 5. 자원정리
					try {
						/*
						 * if (rs != null) { rs.close(); }
						 */
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
			
				//책 리스트 가져오기
				public List<BookVo> BookSelect(){
					List<BookVo> bookList = new ArrayList<BookVo>();
					
					// 0. import java.sql.*;
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					
					try {
						// 1. JDBC 드라이버 (Oracle) 로딩
						Class.forName(driver);
						
						// 2. Connection 얻어오기
						conn = DriverManager.getConnection(url, id, pw);
						System.out.println("접속성공");
						// 3. SQL문 준비 / 바인딩 / 실행
						  //문자열 만들기
						String query ="";
						query += " select  bookId, ";
						query += "         title, ";
						query += "         pubs, ";
						query += "         pubdate, ";
						query += " from    book ";
						query += "order by book_id asc";
						//System.out.println(query);
						
						//문자열 쿼리문으로 만들기
						pstmt = conn.prepareStatement(query);
						
						rs=pstmt.executeQuery();
						
						while (rs.next()) {
							int bookId = rs.getInt(1);// 컬럼명, 별명 사용시 별명
							String title = rs.getString(2);
							String pubs = rs.getString(3);
							String pubDate = rs.getString(4);

							BookVo bvo = new BookVo(bookId, title, pubs, pubDate);
							bookList.add(bvo);
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

					return bookList;
				}

			}