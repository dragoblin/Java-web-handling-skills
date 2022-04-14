package com.spring.ex04;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.spring.ex01.MemberVO;

public class MemberDAO {
	public static SqlSessionFactory sqlMapper = null;
	private static SqlSessionFactory getInstance() {
		if (sqlMapper == null) {
			try {
				
				/* MemberDAO의 각 메서드 호출 시 mybatis/SqlMapConfig.xml에서 
				 * 설정 정보를 읽은 후 데이터베이스와의 연동 준비를 함 */
				String resource = "mybatis/SqlMapConfig.xml";
				
				Reader reader = Resources.getResourceAsReader(resource);
				
				// 마이바티스를 이용하는 sqlMapper 객체를 가져옴
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
				
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sqlMapper;
	}

	public List<MemberVO> selectAllMemberList() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<MemberVO> memlist = null;
		memlist = session.selectList("mapper.member.selectAllMemberList");
		return memlist;
	}
	
	public MemberVO selectMemberById(String id){
		sqlMapper=getInstance();
	    SqlSession session=sqlMapper.openSession();
	    
	    // selectOne : 레코드 한 개만 조회할 때 사용
	    MemberVO memberVO = session.selectOne("mapper.member.selectMemberById", id);
	    // id : 서블릿에서 넘어온 id의 값을 selectOne() 메서드 호출 시 해당 SQL문의 조건 값으로 전달
	    
	    return memberVO;		
	}

	public List<MemberVO> selectMemberByPwd(int pwd) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<MemberVO> membersList = null;
		
		// selectList : 비밀번호가 같은 회원은 여러 명이 있을 수 있으므로 selectList() 메서드로 조회
		membersList= session.selectList("mapper.member.selectMemberByPwd", pwd);
		// pwd : 정수 데이터인 pwd를 SQL문의 조건 값으로 전달
		
		return membersList;
	}
	
	public int insertMember(MemberVO memberVO) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = 0;
		
		// 지정한 id의 SQL문에 memberVO의 값을 전달하여 회원 정보를 테이블에 추가
		result = session.insert("mapper.member.insertMember", memberVO);
		
		// 수동 커밋이므로 반드시 commit() 메서드를 호출하여 영구 반영
		session.commit();
		
		return result;
	}
	
	public int insertMember2(Map<String,String> memberMap) {
        sqlMapper = getInstance();
        SqlSession session = sqlMapper.openSession();
        
        // memberMap : 메서드로 전달된 HashMap을 다시 SQL문으로 전달
        int result = session.insert("mapper.member.insertMember2", memberMap);
        
        session.commit();	
        return result;		
	}
	
	public int updateMember(MemberVO memberVO) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		
		// update문 호출 시 SqlSession의 update() 메서드를 이용
		int result = session.update("mapper.member.updateMember", memberVO);
		
		session.commit();
		return result;
	}
	
	public int deleteMember(String id) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = 0;
		
		// delete문을 실행하려면 SqlSession의 delete() 메서드를 이용
		result = session.delete("mapper.member.deleteMember", id);
		
		session.commit();	// SQL문을 실행한 후 반드시 커밋
		
		return result;
    }
	
	public List<MemberVO> searchMember(MemberVO memberVO) {
        sqlMapper = getInstance();
        SqlSession session = sqlMapper.openSession();
        
        // 회원 검색창에서 전달된 이름과 나이 값을 memberVO에 설정하여 SQL문으로 전달
        List list = session.selectList("mapper.member.searchMember", memberVO);
        
        return list;		
    }
	
	public List<MemberVO> foreachSelect(List nameList) {
        sqlMapper = getInstance();
        SqlSession session = sqlMapper.openSession();
        
        // 검색 이름이 저장된 nameList를 SQL문으로 전달
        List list = session.selectList("mapper.member.foreachSelect", nameList);
        
        return list;		
    }
	
	public int foreachInsert(List memList){
        sqlMapper = getInstance();
        SqlSession session = sqlMapper.openSession();
        
        // int result : insert문이 성공적으로 실행되면 양수를 반환
        int result = session.insert("mapper.member.foreachInsert", memList);
        // 회원 정보가 저장된 memList를 SQL문으로 전달
        
        session.commit();	// 반드시 commit()을 호출
        
        return result ;		
	}
	
	 public List<MemberVO> selectLike(String name) {
		 sqlMapper = getInstance();
	     SqlSession session = sqlMapper.openSession();
	     List list = session.selectList("mapper.member.selectLike", name);
	     return list;		
	 }
}