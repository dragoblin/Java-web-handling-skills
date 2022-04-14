package com.myboot03.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myboot03.member.vo.MemberVO;

@Mapper	// ���� �� �������̽����� ���� ������ �о� ���̵��� ����
@Repository("memberDAO")
public interface MemberDAO {
	
	// ���� ������ id�� selectAllMemberList�� SQL���� ȣ��
	public List selectAllMemberList() throws DataAccessException;
	
	// ���� ������ id�� insertMember�� SQL���� ȣ��
	public int insertMember(MemberVO memberVO) throws DataAccessException ;
	
	// ���� ������ id�� deleteMember�� SQL���� ȣ��
	public int deleteMember(String id) throws DataAccessException;
	
	// ���� ������ id�� loginById�� SQL���� ȣ��
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException;
	
}