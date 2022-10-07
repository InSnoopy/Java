package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 성능 향상을 위한 보조스트림 예제 (바이트 기반)
 */
public class T12BufferedIOTest {
	public static void main(String[] args) {
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
				
		try {
			fos = new FileOutputStream("d:/D_Other/bufferTest.txt");
			
			// 버퍼의 크기를 지정하지 않으면 기본적으로 버퍼의 크기가 8192byte(8kb)로 설정된다.
			// 버퍼크기가 5인 스트림 생성
			// 무작정 버퍼의 크기를 크게하면 차지하는 메모리가 너무 많다.
			bos = new BufferedOutputStream(fos, 5);
			
			for(char ch='1'; ch<='9'; ch++) {
				// 바로 file에다가 write쓰는게 아니라
				// bos(버퍼)안에 먼저 write쓰고 꽉차면 그 때 file에다가 write한다.
				// file에다가 write쓰면 bos는 비워지는게 계속 반복한다.
				// bos가 꽉차야지 file에 write하게 된다.
				// 이때 bos.flush()라는 메소드를 쓰게 된다면 꽉 차지 않았지만 
				// 남은 데이터를 file에 write하게 한다.
				bos.write(ch);
			}
			
			// 작업을 종료하기전에 버퍼에 남아있는 데이터를 모두 출력시킨다. (close시 자동 호출됨)
			bos.flush(); 
			
			System.out.println("작업 끝...");
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
				
				
				
	}
	
}
