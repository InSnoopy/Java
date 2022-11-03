package kr.or.ddit.controllor;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.comm.service.AtchFileServiceImpl;
import kr.or.ddit.comm.service.IAtchFileService;
import kr.or.ddit.comm.vo.AtchFileVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@MultipartConfig
@WebServlet("/member/update.do")
public class UpdatememberController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 파라미터 값 조회
		String memId = req.getParameter("memId");
		
		// 서비스객체 생성
		IMemberService memService = MemberServiceImpl.getInstance();
		
		MemberVO mv = memService.getMember(memId);
		
		if(mv.getAtchFileId() > 0) { // 첨부파일이 존재하면...
			
			// 첨부파일 목록 조회
			IAtchFileService fileService = AtchFileServiceImpl.getInstance();
			AtchFileVO atchFileVO = new AtchFileVO();
			atchFileVO.setAtchFileId(mv.getAtchFileId());
			
			List<AtchFileVO> atchFileList = fileService.getAtchFileList(atchFileVO);
			
			req.setAttribute("atchFileList", atchFileList);
			
		}else {
			
		}
		
		req.setAttribute("mv", mv);
		
		
		// 업데이트 폼화면 출력
		req.getRequestDispatcher("/WEB-INF/views/member/updateForm.jsp").forward(req, resp);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");
		
		long atchFileId = Long.parseLong(req.getParameter("atchFileId"));
		
		// 서비스 객체 생성하기
		IAtchFileService fileService = AtchFileServiceImpl.getInstance(); 
		IMemberService memService = MemberServiceImpl.getInstance();
		
		AtchFileVO atchFileVO = new AtchFileVO();
		
		
		// 첨부파일 목록 저장하기(공통기능)
		atchFileVO = fileService.saveAtchFileList(req);
		
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		if(atchFileVO == null) {
			mv.setAtchFileId(atchFileId);
		}else {
			mv.setAtchFileId(atchFileVO.getAtchFileId());
		}
		
		int cnt = memService.modifyeMember(mv);
		
		String msg = "";
		if(cnt > 0) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		
		req.getSession().setAttribute("msg", msg);
		
		
		resp.sendRedirect(req.getContextPath() + "/member/list.do");
		
	}
	
	
}
