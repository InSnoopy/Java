package kr.or.ddit.basic.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.StringTokenizer;

/**
 *	간단한 웹서버 예제
 */
public class MyHTTPServer {
	
	private final int port = 8888;
	private final String encoding = "UTF-8";
	
	// 서버 시작
	public void start() {
		
		System.out.println("HTTP 서버가 시작되었습니다.");
		
		try(ServerSocket server = new ServerSocket(this.port)) {
			while(true) {
				try {
					Socket socket = server.accept();
					
					// Http 요청 처리를 위한 스레드 객체 생성
					HttpHander hander = new HttpHander(socket);
					hander.start();
					
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}
	/**
	 * Http 요청 처리를 위한 스레드 클래스
	 */
	private class HttpHander extends Thread{
		
		private final Socket socket;
		
		public HttpHander(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			
			OutputStream out = null;
			BufferedReader br = null;
			
			try {
				
				out = new BufferedOutputStream(socket.getOutputStream());
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				// 요청헤더 정보 파싱하기
				// += <- 퍼포먼스가 조금 떨어진다 이 기능을 퍼포먼스 좋게 한게 빌더
				// 한줄씩 읽어서 담는다.
				// header 와 body 사이에 공백이 있다 그전 까지 누적 시킨다.
				StringBuilder sb = new StringBuilder();
				
				// Request Line
				String reqLine = br.readLine();
				
				printMsg("Request Line: ", reqLine);
				
				while(true) {
					String str = br.readLine();
					
					// Empty Line 체크
					if(str.equals("")) break;
					
					sb.append(str + "\n");
				}
				
				// 헤더 정보(Header)
				// status line + header
				String reqHeaderStr = sb.toString();
				
				printMsg("요청헤더 : ", reqHeaderStr);
				
				String reqPath = ""; // 요청 경로
				
				// 요청 페이지 정보 가져오기
				// 파라미터가 두개는 String을 쪼각 쪼각 내는데 그 기준을 2번째 파라미터에 넣으면 된다.
				// 파라미터 하나는 공백별로 쪼갠다.
				// StringTokenizer는 쪼개준다.
				// ex) GET /index.html HTTP/1.1 -> 이 String 을 쪼갠다.
				StringTokenizer st = new StringTokenizer(reqLine);
				while(st.hasMoreTokens()) {
					String token = st.nextToken();
					if(token.startsWith("/")) {
						reqPath = token;
						// reqPath => /dow/image/abc.jpg?
						break;
					}
				}
				
				System.out.println("reqPAth => " + reqPath);
				
				// URL 디코딩 처리(한글깨짐문제 해결)
				reqPath = URLDecoder.decode(reqPath, encoding);
				
				// 현재 프로젝트부터 시작
				String filePath = "./WebContent" + reqPath;
				
				// 해당 파일 이름을 이용하여 Content-type 정보 추출하기
				// MIME 타입
				// body안에 정보가 어떤 타입인지 contentType에 넣어논다.
				// body안에 어떤게 들어 있는지 확인하기 위한 MIME 타입
				String contentType = URLConnection.getFileNameMap().getContentTypeFor(filePath);
				
				// CSS파일인 경우 인식이 안되서 추가함.
				if(contentType == null && filePath.endsWith(".css")) {
					contentType = "text/css";
				}
				
				File file = new File(filePath);
				if(!file.exists()) {
					makeErrorPage(out, 404, "Not Found");
					return;
				}
				
				byte[] body = makeResponseBody(filePath);
				
				byte[] header = makeResponseHeader(body.length, contentType); 
				
				// 응답 헤더 데이터 보내기
				out.write(header);
				
				// 응답 내용 보내기 전 반드시 Empty Line을 보내야 한다.
				// \r\n -> 운영체제가 어떤 운영체제든간에 다 한줄씩 먹게 된다.
				out.write("\r\n\r\n".getBytes());
				
				// 응답 내용 데이터 보내기
				out.write(body);
				out.flush();
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}


		private void printMsg(String title, String msg) {
			System.out.println("============================");
			System.out.println(title);
			System.out.println("============================");
			System.out.println(msg);
			System.out.println("----------------------------");
		}
		
	}
	
	// error 페이지를 만들어주는 메서드
	/**
	 * 에러 페이지 생성
	 * @param out 출력용 스트림
	 * @param statusCode 상태코드
	 * @param errMsg 에러메시지
	 */
	private void makeErrorPage(OutputStream out, int statusCode, String errMsg) {
		String statusLine = "HTTP/1.1" + " " + statusCode + " " + errMsg;
		
		try {
			
			out.write(statusLine.getBytes());
			out.flush();
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * 응답헤더 생성하기
	 * @param length 응답내용 크기
	 * @param contentType MIME타입
	 * @return 바이트 배열
	 */
	private byte[] makeResponseHeader(int length, String contentType) {
		String header = "HTTP/1.1 200 OK\r\n"
				+ "Server: MyHTTPServer 1.0\r\n"
				+ "Content-length: " + length + "\r\n"
				+ "Content-type: " + contentType
				+ "; charset=" + this.encoding;
		
		return header.getBytes();
	}

	/**
	 * 응답 내용 생성하기
	 * @param filePath 응답으로 사용할 파일경로
	 * @return 바이트배열 데이터
	 */
	private byte[] makeResponseBody(String filePath) {
		
		FileInputStream fis = null;
		byte[] data = null;
		
		try {
			
			File file = new File(filePath);
			data = new byte[(int) file.length()];
			
			fis = new FileInputStream(file);
			fis.read(data);
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return data;
	}

	
	public static void main(String[] args) {
		new MyHTTPServer().start();
	}
	
}
// HTTPServer로 index 파일의 정보를 얻기 위함
