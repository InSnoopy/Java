package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class T13BufferedIOTest {
	public static void main(String[] args) {
		
		// 문자기반의 Buffered 스트림 사용하기
		
		FileReader fr = null;
		BufferedReader br = null;
		
		
		try {
			
			// buffer기능을 추가해서 퍼포먼스 향상하기
//			// ./ -> 현재 프로젝트 src앞에 ./는 생략했다.
			fr = new FileReader("src/kr/or/ddit/basic/T12BufferedIOTest.java");
//			
//			int data = 0;
//			
//			while((data = fr.read()) != -1) {
//				System.out.print((char) data);
//			}
			
			// 한줄씩 읽을 수 있도록 해주는 readLine()을 이용하기 위해
			// BufferedReader 사용하기
			br = new BufferedReader(fr);
			
			String temp = "";
			// 코드 줄번호 입력하기 위해
			int cnt = 1;
			// 원래 문자열 한글자씩 읽었지만 readLine은 한줄씩
			// read()랑 return 타입이 다르다.
			// 더 이상 읽을게 없으면 read와 다르게 null이 리턴된다.
			while((temp = br.readLine()) != null) {
				System.out.printf("%4d : %s\n", cnt++, temp);
			}
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				br.close();					
				//fr.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
