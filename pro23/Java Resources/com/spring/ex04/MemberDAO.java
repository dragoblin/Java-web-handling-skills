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
				
				/* MemberDAO�� �� �޼��� ȣ�� �� mybatis/SqlMapConfig.xml���� 
				 * ���� ������ ���� �� �����ͺ��̽����� ���� �غ� �� */
				String resource = "mybatis/SqlMapConfig.xml";
				
				Reader reader = Resources.getResourceAsReader(resource);
				
				// ���̹�Ƽ���� �̿��ϴ� sqlMapper ��ü�� ������
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
	    
	    // selectOne : ���ڵ� �� ���� ��ȸ�� �� ���
	    MemberVO memberVO = session.selectOne("mapper.member.selectMemberById", id);
	    // id : �������� �Ѿ�� id�� ���� selectOne() �޼��� ȣ�� �� �ش� SQL���� ���� ������ ����
	    
	    return memberVO;		
	}

	public List<MemberVO> selectMemberByPwd(int pwd) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<MemberVO> membersList = null;
		
		// selectList : ��й�ȣ�� ���� ȸ���� ���� ���� ���� �� �����Ƿ� selectList() �޼���� ��ȸ
		membersList= session.selectList("mapper.member.selectMemberByPwd", pwd);
		// pwd : ���� �������� pwd�� SQL���� ���� ������ ����
		
		return membersList;
	}
	
	public int insertMember(MemberVO memberVO) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = 0;
		
		// ������ id�� SQL���� memberVO�� ���� �����Ͽ� ȸ�� ������ ���̺� �߰�
		result = session.insert("mapper.member.insertMember", memberVO);
		
		// ���� Ŀ���̹Ƿ� �ݵ�� commit() �޼��带 ȣ���Ͽ� ���� �ݿ�
		session.commit();
		
		return result;
	}
	
	public int insertMember2(Map<String,String> memberMap) {
        sqlMapper = getInstance();
        SqlSession session = sqlMapper.openSession();
        
        // memberMap : �޼���� ���޵� HashMap�� �ٽ� SQL������ ����
        int result = session.insert("mapper.member.insertMember2", memberMap);
        
        session.commit();	
        return result;		
	}
	
	public int updateMember(MemberVO memberVO) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		
		// update�� ȣ�� �� SqlSession�� update() �޼��带 �̿�
		int result = session.update("mapper.member.updateMember", memberVO);
		
		session.commit();
		return result;
	}
	
	public int deleteMember(String id) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = 0;
		
		// delete���� �����Ϸ��� SqlSession�� delete() �޼��带 �̿�
		result = session.delete("mapper.member.deleteMember", id);
		
		session.commit();	// SQL���� ������ �� �ݵ�� Ŀ��
		
		return result;
    }
	
	public List<MemberVO> searchMember(MemberVO memberVO) {
        sqlMapper = getInstance();
        SqlSession session = sqlMapper.openSession();
        
        // ȸ�� �˻�â���� ���޵� �̸��� ���� ���� memberVO�� �����Ͽ� SQL������ ����
        List list = session.selectList("mapper.member.searchMember", memberVO);
        
        return list;		
    }
	
	public List<MemberVO> foreachSelect(List nameList) {
        sqlMapper = getInstance();
        SqlSession session = sqlMapper.openSession();
        
        // �˻� �̸��� ����� nameList�� SQL������ ����
        List list = session.selectList("mapper.member.foreachSelect", nameList);
        
        return list;		
    }
	
	public int foreachInsert(List memList){
        sqlMapper = getInstance();
        SqlSession session = sqlMapper.openSession();
        
        // int result : insert���� ���������� ����Ǹ� ����� ��ȯ
        int result = session.insert("mapper.member.foreachInsert", memList);
        // ȸ�� ������ ����� memList�� SQL������ ����
        
        session.commit();	// �ݵ�� commit()�� ȣ��
        
        return result ;		
	}
	
	 public List<MemberVO> selectLike(String name) {
		 sqlMapper = getInstance();
	     SqlSession session = sqlMapper.openSession();
	     List list = session.selectList("mapper.member.selectLike", name);
	     return list;		
	 }
}