package sec04.ex03;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			// connDB();
			con = dataFactory.getConnection();
			String query = "select * from t_member";
			System.out.println("prepareStatement: " + query);
			pstmt = con.prepareStatement(query); 
			ResultSet rs = pstmt.executeQuery(query);
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void addMember(MemberVO memberVO) {
		try {
			// DataSource를 이용해 데이터베이스와 연결
			con = dataFactory.getConnection();
			// 테이블에 저장할 회원 정보를 받아옴
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			
			// insert문을 문자열로 만듬
			String query = "insert into t_member";
			query += " (id,pwd,name,email)";
			query += " values(?,?,?,?)";
			System.out.println("prepareStatement: " + query);
			pstmt = con.prepareStatement(query);
			// insert문의 각 '?'에 순서대로 회원 정보를 세팅
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			// 회원 정보를 테이블에 추가
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delMember(String id) {
		try {
			con = dataFactory.getConnection();
			
			// delete문을 문자열로 만듬
			String query = "delete from t_member" + " where id=?";
			System.out.println("prepareStatement:" + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);	// 첫 번째 '?'에 전달된 ID를 인자로 넣음
			// delete문을 실행해 테이블에서 해당 ID의 회원 정보를 삭제
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
