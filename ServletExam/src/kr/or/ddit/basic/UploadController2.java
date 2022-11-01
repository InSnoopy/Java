package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * 서블릿 3부터 지원하는 Part 인터페이스를 이용한 파일업로드 예제
 */
// Mutipart의 데이터가 날라온다는걸 미리 알려준다.
@MultipartConfig
@WebServlet("/test/upload2.do")
public class UploadController2 extends HttpServlet{

	private static final String UPLOAD_DIR = "upload_files";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Multipart Parsing 전에 파라미터 정보 조회해 보기
		System.out.println("Multipart Parsing 전 => " + req.getParameter("sender"));
		

		// 웹애플리케이션 루트 디렉토리 기준으로 업로드 경로 설정하기
		// 웹컨텐츠에 저장하면 외부에서 접근가능 경로로 접근 가능하다.

		String uploadPath = "d:/D_Other/" + UPLOAD_DIR; // 내가 원하는 경로에 저장하고 싶을 때 -> 서브릿을 통해서만 접근 할 수 있다. 
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		try {
			
			String fileName = "";
			for(Part part : req.getParts()) {
				
				System.out.println(part.getHeader("content-disposition"));
				fileName = getFileName(part);
				if(fileName != null && !fileName.equals("")) {
					// 폼필드가 아니거나 파일이 비어있지 않은 경우...
					part.write(uploadPath + File.separator + fileName); // 파일 저장
					System.out.println(uploadPath + File.separator + fileName + " => 저장 완료!!!");
				}
				
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		//^ 을 앱이 존재하는 루트 안에 저장하고 싶을때! 업로드할 경로를 준비하는 과정
		
	}

	
	/**
	 * Part 객체를 파싱하여 파일이름 추출하기
	 * @param part 파싱할 Part객체
	 * @return 파일명: 존재하지 않으면 NULL 리턴함(폼필드인 경우)
	 */
	private String getFileName(Part part) {

	/*
	   Content-Disposition 헤더에 대하여...
	   
	   multipart body를 위해 사용되는 경우 ex)파일 업로드
	   
	   Content-Disposition: form-data
	   Content-Disposition: form-data; name="fieldName"
	   Content-Disposition: form-data; name="fieldName"; filename="a.jpg" <- file인 경우 이게 날라온다.
	   	   
	 */
		for(String content : part.getHeader("Content-Disposition").split(";")) {
			if(content.trim().startsWith("filename")) {
				return content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
			}
		}
		return null; // filename이 존재하지 않을 경우...(폼필드)
	}
	
}
