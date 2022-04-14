package com.myboot03.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myboot03.member.vo.MemberVO;

@Mapper	// 실행 시 인터페이스에서 매퍼 파일을 읽어 들이도록 지정
@Repository("memberDAO")
public interface MemberDAO {
	
	// 매퍼 파일의 id가 selectAllMemberList인 SQL문을 호출
	public List selectAllMemberList() throws DataAccessException;
	
	// 매퍼 파일의 id가 insertMember인 SQL문을 호출
	public int insertMember(MemberVO memberVO) throws DataAccessException ;
	
	// 매퍼 파일의 id가 deleteMember인 SQL문을 호출
	public int deleteMember(String id) throws DataAccessException;
	
	// 매퍼 파일의 id가 loginById인 SQL문을 호출
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException;
	
}