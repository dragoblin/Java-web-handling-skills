package com.spring.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.member.dao.MemberDAO;

public class MemberServiceImpl implements MemberService {
	private MemberDAO memberDAO;
	
	// memberDAO 속성에 setter를 이용하여 설정 파일에서 생성된 memberDAO 빈을 주입
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public List listMembers() throws DataAccessException {
		List membersList = null;
		membersList = memberDAO.selectAllMembers();
		return membersList;
	}

}