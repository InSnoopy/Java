package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 파일 읽기 예제
 */
public class T05FileStreamTest {

	public static void main(String[] args) {
	
		// FileInputStream 객체를 이용한 파일 내용 읽기
		FileInputStream fis =null;
		
		try {
			
			fis = new FileInputStream("d:/D_Other/test2.txt");

			int data = 0;
			while((data = fis.read()) != -1) {
				// 읽어온 내용 출력하기
				// 한글을 제대로 보여주려면 2byte씩 보여줘야한다.
				// byte기반은 1byte밖에 안되서 한글을 못보여준다.
				System.out.print((char) data);
			}
					
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		
	}	
	
}
