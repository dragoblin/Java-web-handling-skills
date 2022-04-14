package com.spring.ex02;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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

	public String  selectName() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		
		// selectOne() 메서드로 인자로 지정한 SQL문을 실행한 후 한 개의 데이터(문자열)를 반환
		String name = session.selectOne("mapper.member.selectName");
		
		return name;
	} 
		
	public int  selectPwd() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		
		// selectOne() 메서드로 인자로 지정한 SQL문을 실행한 후 한 개의 데이터(정수)를 반환
		int pwd = session.selectOne("mapper.member.selectPwd");
		
		return pwd;
	}
}