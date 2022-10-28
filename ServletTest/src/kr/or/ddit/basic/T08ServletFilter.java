package kr.or.ddit.basic;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class T08ServletFilter implements Filter{
/*
	서브릿 필터에 대하여...
	
	1. 사용목적
	 - 클라이언트의 요청을 수행하기 전에 가로채 필요한 작업을 수행할 수 있다.
	 - 클라이언트에 응답정보를 제공하기 전에 응답정보에 필요한 작업을 수행할 수 있다.
	 
	2. 사용 예
	 - 인증필터
	 - 데이터 압출필터
	 - 인코딩 필터
	 - 로깅 및 감사처리 필터
	 - 이미지 변환 필터 등.
 */
	
	
	@Override
	public void destroy() {
		// 필터객체가 웹컨테이너에 의해 서비스로부터 제거되기 전에 호출됨.
		System.out.println("T08ServletFilter.destory()"
				+ " 호출됨");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("T08ServletFilter.doFilter()"
				+ " 시작...");
		
		// 클라이언트의 IP주소 가져오기
		String ipAddr = request.getRemoteAddr();
		System.out.println("IP주소: " + ipAddr
				+ "\n포트번호: " + request.getRemotePort()
				+ "\n현재시간: " + new Date());
		
		// 필터체인을 실행한다.(요청 및 응답 객체 전달한다.)
		// 필터가 여러개의 필터가 있기 때문에 체인으로 연결되어 있다.
		// 1번째 필터.. 2번쨰필터.. 이동시키라는게 chain.doFilter()이다.
		// 마지막 필터에서는 서블릿으로 넘어간다.
		chain.doFilter(request, response);
		
		System.out.println("T08ServletFilter.doFilter()"
				+ " 끝...");
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		System.out.println("T08ServletFilter.init()"
				+ " 호출됨");
		
	}

}
