package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {

	@Autowired
	private SqlSession sqlSession;

	// 전체리스트 가져오기
	public List<PersonVo> getPersonList() {

		List<PersonVo> pList = sqlSession.selectList("phonebook.selectList");
		return pList;
	}

	// 전화번호 추가
	/*
	public int PersonInsert(PersonVo pvo) {

		return sqlSession.insert("phonebook.insert", pvo);
	}
	*/
	
	// 전화번호 추가(파라미터 여러개로 받을때)
	public int PersonInsert(PersonVo pvo) {
		
		String name = "황일영";
		String hp = "010-2222-2222";
		String company = "02-2222-2222";
		
		Map<String, String> personMap = new HashMap<String, String>();
		personMap.put("name", name);
		personMap.put("hp", hp);
		personMap.put("company", company);

		return sqlSession.insert("phonebook.insert", personMap);
	}

	// 사람 삭제
	public int PersonDelete(int no) {
		System.out.println(no);
		return sqlSession.delete("phonebook.delete", no);
	}

	//사람 수정
	public int PersonUpdate(int index, PersonVo pvo) {

		pvo.setPerson_id(index);
		return sqlSession.update("phonebook.update", pvo);

	}

	public PersonVo getPerson(int index) {

		return sqlSession.selectOne("phonebook.selectPerson", index);
	}

}