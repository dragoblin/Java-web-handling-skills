package sec04.ex01;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

// HttpSessionBindingListener를 구현해 세션에 바인딩 시 이벤트를 처리
public class LoginImpl implements HttpSessionBindingListener {
	String user_id;
	String user_pw;
	static int total_user = 0;	// 세션에 바인딩 시 1씩 증가시킴

	public LoginImpl() {
	}

	public LoginImpl(String user_id, String user_pw) {
		this.user_id = user_id;
		this.user_pw = user_pw;
	}

	@Override
	// 세션에 저장 시 접속자 수를 증가시킴
	public void valueBound(HttpSessionBindingEvent arg0) {
		System.out.println("사용자 접속");
		++total_user;
	}

	@Override
	// 세션에서 소멸 시 접속자 수를 감소시킴
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		System.out.println("사용자 접속 해제");
		total_user--;
	}
}