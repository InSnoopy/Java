package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class T06FileStreamTest {
	public static void main(String[] args) throws IOException {
		
		FileOutputStream fos = null;
		
		try {
			
			// 출력을 위한 FileOutputStream객체 생성
			// FileOutputStream을 생성함과 동시에 해당하는 파일이 생성된다.
			// fos = new FileOutputStream("d:/D_Other/out.txt");
			// true로 하게되면 파일안에 새롭게 계속 내용이 추가된다.
			fos = new FileOutputStream("d:/D_Other/out.txt", true);
			
			for(char ch='A'; ch<= 'Z'; ch++) {
				fos.write(ch);
			}
			
			System.out.println("파일 쓰기 작업 완료....");
			
			// 파일 읽기
			FileInputStream fis = new FileInputStream("d:/D_Other/out.txt");
			
			int data = 0;
			
			while((data = fis.read()) != -1) {
				System.out.print((char) data);
			}
			
			fis.close();
		
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			fos.close();
		}
		
		
	}
}
