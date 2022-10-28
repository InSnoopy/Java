package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class T06SerlvetSessionTest extends HttpServlet{
/*
	세션( HttpSession ) 객체에 대하여...
	
	- 세션을 통해서 사용자(웹브라우저)별로 구분하여 정보를 관리할 수 있다. (세선ID 이용)
	
	- 쿠키를 사용할 때보다 보안이 향상된다.(서버에 정보가 저장되기 때문에)
	
	- 세션객체를 가져오는 방법
	  HttpSession session = request.getSession(boolean값)
	  Boolean값 : true인 경우 => 세셕객체가 존재하지 않으면 새로 생성.
		   	     false인 경우 => 세션객체가 존재하지 않으면 null 리턴.
		   	     	기본값 = true
		   	     	사용자별 하나의 session만 만들어 준다.
		   	     	
    - 세션 삭제 처리 방법
	1. invalidate()메서드 호출
	2. setMaxInactiveInterval(int interval) 호출 - 일정시간동안 활성화가 되지 않는다면? 이란 뜻
		=> 일정시간(초) 동안 요청이 없으면 세션객체 삭제됨.
	3. web.xml에 <session-cifig> 설정하기(분 단위) - 1분동안 사용하지 않은 session 전체 app
	* 1,2방법은 해당하는 객체에만..
	* 하지만 3번은 전체 session 모든 객체에 사용하지 않은 모든 객체의 session 삭제
	
 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 세션을 가져오는데 없으면 새로 생성한다.
		HttpSession session = req.getSession(true);
		
		// 생성시간 가져오기
		Date createTime = new Date(session.getCreationTime());
		
		// 마지막 접근시간 가져오기
		Date lastAccessTime = new Date(session.getLastAccessedTime());
		
		String title = "재방문을 환영합니다.";
		int visitCnt = 0; // 방문횟수
		String userId = "SEM"; // 사용자 ID
		
		if(session.isNew()) { // 새로만든 세션인지...
			title = "처음 방문을 환영합니다.";
			session.setAttribute("userId", userId);
		}else {
			visitCnt = (int) session.getAttribute("visitCnt");
			visitCnt++; // 방문횟수 추가
			userId = (String) session.getAttribute("userId");
		}
		session.setAttribute("visitCnt", visitCnt);
		
		// 세션 삭제 1번째 방법
		// session.invalidate();
		
		// 세션 삭제 2번째 방법
		// 30초 이후에 세션 삭제
		// session.setMaxInactiveInterval(30);
		
		///////////////////////////////////////////
		
		// 응답 시작...
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		  out.print("<html><head><title>" + title
	                 + "</title></head>"
	                 + "<body>"
	                 + "<h1 align=\"center\">" + title + "</h1>"
	                 + "<h2 align=\"center\">세션정보</h2>"
	                 + "<table border=\"1\" align=\"center\">"
	                 + "<tr bgcolor=\"orange\">"
	                 +"<th>구분</th><th>값</th></tr>"
	                 + "<tr><td>세션ID</td><td>"
	                 + session.getId() + "</td></tr>"
	                 + "<tr><td>생성시간</td><td>"
	                 + createTime + "</td></tr>"
	                 + "<tr><td>마지막 접근시간</td><td>"
	                 + lastAccessTime + "</td></tr>"
	                 + "<tr><td>User ID</td><td>"
	                 + userId + "</td></tr>"
	                 + "<tr><td>방문횟수</td><td>"
	                 + visitCnt + "</td></tr></table>"
	                 + "</body></html>");

	}
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	
	}
	
}