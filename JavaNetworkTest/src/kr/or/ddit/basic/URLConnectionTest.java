package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class URLConnectionTest {
	public static void main(String[] args) throws IOException {
	
		// URLConnection => 애플리케이션과 URL간의 통신연결을 위한 추상 클래스
		
		// 특정 서버 (ex:네이버)에 접속하여 정보 가져오기
		// https로 해야지 바로 간다.
		// http는 https를 거쳐서 간다.
		URL url = new URL("https://www.naver.com/index.html");
		
		// Header 정보 가져오기
		
		// URLConnection 객체 구하기
		URLConnection urlConn = url.openConnection();
		
		System.out.println("Content-Type : " + urlConn.getContentType());
		System.out.println("Encoding : " + urlConn.getContentEncoding());
		System.out.println("Content : " + urlConn.getContent());
		System.out.println();
		
		// 전체 Header정보 출력하기
		// 출력은 HTTP프로토콜 방식으로 출력된다.
		Map<String, List<String>> headerMap = urlConn.getHeaderFields();
		
		Iterator<String> it = headerMap.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			System.out.println(key + " : " + headerMap.get(key));
		}
		System.out.println("-------------------------------------");
		
		// 이렇게 해서 getInputStream을 가져올 수 있다.
		InputStream is = urlConn.getInputStream();
		
		// HTML 파일을 우리 눈으로 보이게 한다.
		// 던지는 파일이 Object로 반환하기 때문에 강제 타입 변환 했다.
		InputStreamReader isr = new InputStreamReader((InputStream) urlConn.getContent());
		
		int data = 0;
		while((data = isr.read()) != -1) {
			System.out.print((char) data);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
