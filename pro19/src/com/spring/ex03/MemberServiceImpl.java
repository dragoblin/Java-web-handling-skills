package com.spring.ex03;

public class MemberServiceImpl implements MemberService {
	
	// ���ԵǴ� ���� ������ MemberDAO Ÿ���� �Ӽ��� ����
	private MemberDAO memberDAO;
	
	// ���� ���Ͽ��� memberDAO ���� ������ �� setter�� �Ӽ� memberDAO�� ����
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public void listMembers() {
		
		// ���Ե� ���� �̿��� listMembers() �޼��带 ȣ��
		memberDAO.listMembers();
		
	}
}