package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.AuthorVo;

public class AuthorDao {
	// insert 설계
	public int insert(AuthorVo author) {

		String name = author.getAuthorName();
		String desc = author.getAuthorDesc();

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 프로그램 로딩 가져옴

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";// db주소
			conn = DriverManager.getConnection(url, "webdb", "webdb");// 정보 다 가짐
			System.out.println("접속성공");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into author values(seq_book_id.nextval, ?, ?)";// ?는 변수
			pstmt = conn.prepareStatement(query);// 쿼리를 날리는 문장

			pstmt.setString(1, name);
			pstmt.setString(2, desc);
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 처리");
		} catch (ClassNotFoundException e) {
			System.out.println("error:드라이벌딩 실패." + e);
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
		return 1;

	}

	// 이름
	public List<AuthorVo> select() {

		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 프로그램 로딩 가져옴

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";// db주소
			conn = DriverManager.getConnection(url, "webdb", "webdb");// 정보 다 가짐
			System.out.println("접속성공");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select author_id," + "author_name," + "author_desc " + "from author";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				AuthorVo vo = new AuthorVo(authorId, authorName, authorDesc);
				authorList.add(vo);

			}
		} catch (ClassNotFoundException e) {
			System.out.println("error:드라이벌딩 실패." + e);
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

		return authorList;

	}

	public void delete(AuthorVo author) {
		int num = author.getAuthorId();

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");

			String query = "DELETE from author " + "where author_id=?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);
			pstmt.executeQuery();
		} catch (ClassNotFoundException e) {
			System.out.println("error:드라이벌딩 실패." + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
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
		} // finally

	}// delete

	public void update(AuthorVo author) {
		int num=author.getAuthorId();
		String name=author.getAuthorName();
		String desc=author.getAuthorDesc();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 프로그램 로딩 가져옴

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";// db주소
			conn = DriverManager.getConnection(url, "webdb", "webdb");// 정보 다 가짐
			System.out.println("접속성공");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "update author " + "set author_name=?, " 
			+ "author_desc=? " + "where author_id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, desc);
			pstmt.setInt(3, num);
			pstmt.executeQuery();// commit

			// 4.결과처리

		} catch (ClassNotFoundException e) {
			System.out.println("error:드라이벌딩 실패." + e);
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

}
