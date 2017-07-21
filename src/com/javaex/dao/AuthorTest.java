package com.javaex.dao;

import java.util.List;

import com.javaex.vo.AuthorVo;

public class AuthorTest {

	public static void main(String[] args) {
		
		AuthorDao dao=new AuthorDao();
		
		/*AuthorVo vo=new AuthorVo("이고잉","생활코딩운영자");
		
		dao.insert(vo);*/

		
		/*List<AuthorVo> authorList=dao.select();
		System.out.println(authorList.toString());*/
		AuthorVo vo=new AuthorVo(7,"조석","네이버 웹툰");
		dao.update(vo);

	}

}
