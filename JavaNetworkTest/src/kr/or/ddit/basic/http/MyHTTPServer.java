package kr.or.ddit.basic.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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
	
	public static void main(String[] args) {
		new MyHTTPServer().start();
	}
	
}
// HTTPServer로 index 파일의 정보를 얻기 위함
