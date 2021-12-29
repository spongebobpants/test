package com.javaex.ex03;

import java.util.List;
public class AuthorApp {
	public static void main(String []args) {
		
		List<AuthorVo>list;
		AuthorDao authorDao = new AuthorDao();
		
		AuthorVo vo01 = new AuthorVo("이문열", "경북 영양");
		authorDao.authorInsert(vo01);
		
		AuthorVo vo02 = new AuthorVo("박경리", "경상남도 통영");
		authorDao.authorInsert(vo02);
		
		AuthorVo vo03 = new AuthorVo("유시민", "17대 국회의원");
		authorDao.authorInsert(vo03);
		
		System.out.println("---------------------");
		list = authorDao.authorSelect();
		for(int i=0; i<list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(vo.getAuthorId()+","+vo.getAuthorName()+","+ vo.getAuthorDesc());
		}
		System.out.println("---------------------");
		
		//작가 수정
		AuthorVo authorVo = new AuthorVo(2, "박경리(edit)", "경상남도 통영(edit)");
		authorDao.authorUpdate(authorVo);
		
		System.out.println("---------------------");
		list = authorDao.authorSelect();
		for(int i=0; i<list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(vo.getAuthorId()+","+vo.getAuthorName()+","+ vo.getAuthorDesc());
		}
		System.out.println("---------------------");
		//작가 삭제
		authorDao.authorDelete(1);

		System.out.println("---------------------");
		list = authorDao.authorSelect();
		for(int i=0; i<list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(vo.getAuthorId()+","+vo.getAuthorName()+","+ vo.getAuthorDesc());
		}
		System.out.println("---------------------");
		
	}
}