package com.myspring.pro30.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.pro30.member.vo.MemberVO;

// id가 memberDAO인 빈을 자동 생성
@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	
	// XML 설정 파일에서 생성한 id가 sqlSession인 빈을 자동 주입
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllMemberList() throws DataAccessException {
		List<MemberVO> membersList = null;
		
		// 주입된 sqlSession 빈으로 selectList() 메서드를 호출하면서 SQL문의 id를 전달
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		
		return membersList;
	}

	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		
		// 주입된 sqlSession 빈으로 insert() 메서드를 호출하면서 SQL문의 id와 memberVO를 전달
		int result = sqlSession.insert("mapper.member.insertMember", memberVO);
		
		return result;
	}

	@Override
	public int deleteMember(String id) throws DataAccessException {
		
		// 주입된 sqlSession 빈으로 delete() 메서드를 호출하면서 SQL문의 id와 회원 ID를 전달
		int result =  sqlSession.delete("mapper.member.deleteMember", id);
		return result;
	}
	
	@Override
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException {
		
		/* 메서드 호출 시 전달된 memberVO를 SQL문으로 전달해 ID와 비밀번호에 대한
		 * 회원 정보를 MemberVO 객체로 반환 */
		MemberVO vo = sqlSession.selectOne("mapper.member.loginById", memberVO);
		
		return vo;
	}
}