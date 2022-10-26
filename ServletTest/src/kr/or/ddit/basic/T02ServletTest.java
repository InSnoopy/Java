package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T02ServletTest extends HttpServlet{
/*
	서블릿 동작 방식에 대하여...
	
	1. 사용자(클라이언트)가 URL을 클릭하면 HTTP 요청을 서블릿 컨테이너로 전송한다.
	2. 컨테이너는 web.xml에 정의된 url패턴을 확인하여 어느서블릿을 통해 처리해야 할지를 검색한다.
		(로딩[객체생성]이 안된 경우에는 로딩[객체생성]함. 로딩[객체생성]시 init() 메서드 호출됨.)
	3. 서블릿 컨테이너는 요청을 처리할 개별 스레드를 생성하여 해당 서브릿 객체의 service()메서드를 호출하도록 한다.
		(이때, HttpServletRequest 및  HttpServletResponse 객체를 생성하여 파라미터로 넘겨준다.)
	4. service()메서드는 메서드타입을 체크하여 적절한 메서드를 호출한다.
		(doGet, doPost, doPut, doDelete 등)
	5. 요청처리가 완료되면 요청 및 응답 객체는 소멸한다.
	6. 컨테이너로부터 서블릿 객체가 제거되는 경우에는 destory()가 호출됨.
	
 */
	
	
	// 브라우저가 요청해오면 이 2개만 있으면 다 실행된다.
	// 2개다 동시에 하고 싶다면 서로 메소드를 부르면 된다.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Request 객체의 메서드 확인하기
		// 
		// body안에 메세지의 어떤 encoding 타입인지 ex) utf-8
		System.out.println("getCharacterEncoding : " + req.getCharacterEncoding());
		// 요청한 메시지에 컨텐츠 이름
		System.out.println("getContentLength : " + req.getContentLength());
		// url ?뒤에 나온 쿼리문
		// name=박인수&age=18 -> name=%EB%B0%95%EC%9D%B8%EC%88%98&age=18 이런식(%인코딩이라고도 한다)
		System.out.println("getQueryString : " + req.getQueryString());
		// /애플리케이션 이름/서브릿 url?
		System.out.println("getRequestURI : " + req.getRequestURI());
		// 어떤 방식인지
		System.out.println("getMethod : " + req.getMethod());
		// 요청한 사람이 어떤 ip인지
		System.out.println("getRemoteAddr : " + req.getRemoteAddr());
		// 요청한 사람의 port번호
		System.out.println("getRemotePort : " + req.getRemotePort());
		// 요청한 사람의 프로토콜의 버전
		System.out.println("getProtocol : " + req.getProtocol());
		
		// Post방식으로 넘어오는 body데이터를 인코딩 설정함.
		// GET방식은 톰캣의 URIEncoding 설정을 통해 해결,
		// 반드시 Request 객체에서 값을 가져오기 전에 먼저 설정해야 함.
		req.setCharacterEncoding("UTF-8");
		
		// 요청 메시지로부터 name속성값 가져옴.
		// 요청한 key값?
		String name = req.getParameter("name");
		
		System.out.println("name => " + name);
		
		// 요청객체에 데이터 저장하기
		// object다 넣을 수 있다.
		// 꺼낼때는 getAttribute()로 꺼낸다.
		req.setAttribute("tel", "1111-1111");
		req.setAttribute("addr", "대전시 중구 오류동");
		
		// 요청 메시지는 getParameter로
		// 내가 저장한 request 자체에 저장한 데이터는 getAttribute로
		System.out.println(req.getAttribute("addr"));
		
		//////////////////////////////////////////////
		
		// 응답 메시지 인코딩 설정
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain"); // plain -> 일반적인 String
		
		PrintWriter out = resp.getWriter(); // 문자기반
		out.println("name => " + name);
		out.println("서브릿 경로 => " + req.getServletPath());
		// 컨텍스트 -> 웹어플리케이션 이름
		out.println("컨텍스트 경로 => " + req.getContextPath());
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
