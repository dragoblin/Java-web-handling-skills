package sec04.ex02;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/* HttpSessionBindingListener를 제외한 Listener를 구현한 모든 이벤트 핸들러는
 * 반드시 애너테이션을 이용해서 Listener로 등록해야 함
 */
@WebListener
public class LoginImpl implements HttpSessionListener {
	String user_id;
	String user_pw;
	static int total_user = 0;
	
    public LoginImpl() {
    }
    
    public LoginImpl(String user_id, String user_pw) {
    	this.user_id = user_id;
    	this.user_pw = user_pw;
    }
	
    @Override
    // 세션 생성 시 이벤트를 처리
    public void sessionCreated(HttpSessionEvent se)  { 
         System.out.println("세션 생성");
         ++total_user;	// 세션 생성 시 접속자 수를 1 증가시킴
    }

	@Override
	// 세션 소멸 시 이벤트를 처리
    public void sessionDestroyed(HttpSessionEvent se)  { 
		System.out.println("세션 소멸");
        --total_user;	// 세션 소멸 시 접속자 수를 1 감소시킴
    }
}
