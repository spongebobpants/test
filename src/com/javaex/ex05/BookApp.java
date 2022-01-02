package com.javaex.ex05;

import java.util.List;

public class BookApp {
	
	public static void main(String[] args) {
		
		List<BookVo> bList;		
		BookDao bookdao = new BookDao();
		
		//책&작가등록
		//책제목, 출판사, 출판일, 작가id
		bookdao.BookInsert("우리들의 일그러진 영웅", "다림", "1998-02-22",1);
		bookdao.BookInsert("삼국지", "민음사", "2002-03-01",1);
		bookdao.BookInsert("토지", "마로니에북스", "2012-08-15",2);
		
		bList = bookdao.BookSelect();
		//List<BookAllVo> bList = bookalldao.BookAllSelect();
		                //이게 곧 List<BookAllVo> bookList
		//출력
		for(int i=0; i<bList.size(); i++) {
			System.out.println(bList.get(i).getBookId()
					+","+bList.get(i).getTitle()+","+
					bList.get(i).getPubs()+","+
					bList.get(i).getPubDate()+","+
					bList.get(i).getAuthorId()+","+
					bList.get(i).getAuthorName()+","+
					bList.get(i).getAuthorDesc());
		}
		
		
		System.out.println("===========================");
		
		//수정
		//고치려는책번호, 수정할 제목,출판사,출판일,작가id
		bookdao.BookUpdate(1, "우일영", "다림수정", "1998-02-22", 1);
		
		for(int i=0; i<bList.size(); i++) {
			System.out.println(bList.get(i).getBookId()
					+","+bList.get(i).getTitle()+","+
					bList.get(i).getPubs()+","+
					bList.get(i).getPubDate()+","+
					bList.get(i).getAuthorId()+","+
					bList.get(i).getAuthorName()+","+
					bList.get(i).getAuthorDesc());
		}
		
		
		System.out.println("===========================");
		
		//삭제
		//bookalldao.BookAllDelete(2);
		
		
	}

}