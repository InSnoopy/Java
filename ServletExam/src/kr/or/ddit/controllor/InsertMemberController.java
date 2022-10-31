package kr.or.ddit.controllor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

// 뒤에 .do를 붙이는건 그냥 이게 서블릿이라고 약속하는 거임
@WebServlet("/member/insert.do")
public class InsertMemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
		req.getRequestDispatcher("/WEB-INF/views/member/insertForm.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 인코딩
		// req.setCharacterEncoding("utf-8");
		// 필터에 등록 완료함
		
		// 파라미터값 가져오기
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");
		
		// 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();
		
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		
		int cnt = memService.registMember(mv);
		
		// forward를 쓰는 경우 요청자는 insert.do를 요청했는데 list가 뜬다.
		// 새로고침하면 에러가 뜨는 문제가 있다.
		// req.getRequestDispatcher("/member/list.do").forward(req, resp);

		
		// insert했는데 최종적으로 list.do가보임 -> 리다이렉트라고 한다.
		// 리다이렉트에서는 클라이언트 입장에서 경로를 적어줘야한다.
		// forward는 아니다.
		resp.sendRedirect(req.getContextPath() + "/member/list.do");
		
		// insert.do 로 요청했지만 방향이 list.do로 변경되었다.
		// 이걸 리다이렉트라고 한다. 이렇게 설정안하면
		// insert.do로 요청한게 남아있기 때문에 error난다.
		// 리다이렉트는 2번 요청이 날라온다.
		// 최종적으로 list.do로 남는다.
		
	}
	
}
