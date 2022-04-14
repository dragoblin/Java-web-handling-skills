package com.spring.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.member.vo.MemberVO;

// id�� memberDAO�� ���� �ڵ� ����
@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	
	// XML ���� ���Ͽ��� ������ id�� sqlSession�� ���� �ڵ� ����
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllMemberList() throws DataAccessException {
		List<MemberVO> membersList = null;
		
		// ���Ե� sqlSession ������ selectList() �޼��带 ȣ���ϸ鼭 SQL���� id�� ����
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		
		return membersList;
	}

	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		
		// ���Ե� sqlSession ������ insert() �޼��带 ȣ���ϸ鼭 SQL���� id�� memberVO�� ����
		int result = sqlSession.insert("mapper.member.insertMember", memberVO);
		
		return result;
	}

	@Override
	public int deleteMember(String id) throws DataAccessException {
		
		// ���Ե� sqlSession ������ delete() �޼��带 ȣ���ϸ鼭 SQL���� id�� ȸ�� ID�� ����
		int result =  sqlSession.delete("mapper.member.deleteMember", id);
		return result;
	}
}