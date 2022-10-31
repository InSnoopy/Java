package kr.or.ddit.controllor;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet(value = "/member/list.do")
public class ListMemberController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 서비스 객체 생성하기
		IMemberService memberService = MemberServiceImpl.getInstance();
		
		// 회원목록 조회
		List<MemberVO> memList = memberService.listMember();
		
		// 잠깐 보관
		req.setAttribute("memList", memList);
		
		// 회원목록 화면 처리하기
		// Displatcher : 발송하다라는 뜻
		// 요청을 컨트롤러가 받은걸 뷰한테 던진다.
		// Forward -> 뜻 전달하다.
		// 포어드는 백단에서 일어나는 실행 흐름
		// 포어드는 다른 애한테 이 일을 준다.?
		// 너가 해 ~~ 이 역할 너가 하라고~~ 던지는게 포어드
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/list.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
