package sec03.ex01;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class EncoderFilter
 */
@WebFilter("/*")
// WebFilter 애너테이션을 이용해 모든 요청이 필터를 거치게 함
public class EncoderFilter implements Filter {	// 사용자 정의 필터는 반드시 Filter 인터페이스를 구현해야 함
	ServletContext context;
    /**
     * Default constructor. 
     */
    public EncoderFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	// doFilter() 안에서 실제 필터 기능을 구현
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
	throws IOException, ServletException {
		System.out.println("doFilter 호출");
		request.setCharacterEncoding("utf-8");	// 한글 인코딩 설정 작업
		// 웹 애플리케이션의 컨텍스트 이름을 가져옴
		String context = ((HttpServletRequest) request).getContextPath();
		// 웹 브라우저에서 요청한 요청 URI을 가져옴
		String pathinfo = ((HttpServletRequest) request).getRequestURI();
		// 요청 URI의 실제 경로를 가져옴
		String realPath = request.getRealPath(pathinfo);
		
		String mesg = " Context 정보 : " + context
						+ "\n URI 정보 : " + pathinfo
						+ "\n 물리적 경로 : " + realPath;
		System.out.println(mesg);
		
		long begin = System.currentTimeMillis();  // 요청 필터에서 요청 처리 전의 시각을 구함
		chain.doFilter(request, response);	// 다음 필터로 넘기는 작업 수행
		
		long end = System.currentTimeMillis();	// 응답 필터에서 요청 처리 후의 시각을 구함
		// 작업 요청 전과 후의 시각 차를 구해 작업 수행 시간을 구함
		System.out.println("작업 시간 : " + (end-begin) + "ms");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("utf-8 인코딩............");
		context = fConfig.getServletContext();
	}
}