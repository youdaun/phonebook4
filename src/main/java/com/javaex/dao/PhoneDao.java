package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {

	@Autowired
	private SqlSession sqlSession;

	//전체리스트 가져오기
	public List<PersonVo> getPersonList() {	
		
		List<PersonVo> pList = sqlSession.selectList("phonebook.selectList");

		return pList;
	}
	
	//전화번호 추가
	public int PersonInsert(PersonVo pvo) { 
		
		return sqlSession.insert("phonebook.insert", pvo);

	}
	
	//사람 삭제
	public int PersonDelete(int no) {
		
		return sqlSession.delete("phonebook.delete", no);

	}

	/*
	public void PersonUpdate(int index, PersonVo pvo) {

		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update person ";
			query += " set    name = ?, ";
			query += "        hp = ?, ";
			query += "        company = ? ";
			query += " where  person_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, pvo.getName());
			pstmt.setString(2, pvo.getHp());
			pstmt.setString(3, pvo.getCompany());
			pstmt.setInt(4, index);

			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[" + count + " 건이 수정되었습니다.]");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

	}

	

	public PersonVo getPerson(int index) {

		getConnection();
		PersonVo pvo = new PersonVo();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select person_id, name, hp, company ";
			query += " from person ";
			query += " where person_id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, index);

			// select문은 update가 아니라 query!!
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int person_id = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				pvo.setPerson_id(person_id);
				pvo.setName(name);
				pvo.setHp(hp);
				pvo.setCompany(company);

			}
		} catch (Exception e) {
			System.out.println("error:" + e);
		}
		close();

		return pvo;
	} */
}