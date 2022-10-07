package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T03ByteArrayIOTest {
	
	public static void main(String[] args) throws IOException {
		
		
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		// 1. 직접 복사하는 방법
//		outSrc = new byte[inSrc.length]; // 공간 확보
//		for(int i=0; i<outSrc.length; i++) {
//			outSrc[i] = inSrc[i];
//		}
//		
//		System.out.println("inSrc = > " + Arrays.toString(inSrc));
//		System.out.println("outSrc = > " + Arrays.toString(outSrc));
		
		
		
		// 2. arraycopy를 이용한 배열 복사하기
//		outSrc = new byte[inSrc.length]; // 공간확보
//		System.arraycopy(inSrc, 0, outSrc, 0, inSrc.length);
//		
//		System.out.println("inSrc = > " + Arrays.toString(inSrc));
//		System.out.println("outSrc = > " + Arrays.toString(outSrc));
		
		
		
		// 3. 스트림 클래스 이용하기
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int data = 0; // 읽어온 바이트 데이터를 저장할 변수
		
		// read() => byte단위로 데이터를 읽어와 int형으로 반환한다.
		//           (int는 4byte) : byte-> int 가능
		//           return을 int타입으로 한 이유는 byte타입에는 -1을 넣을 수 없기 떄문이다.
		//           그래서 read()메소드의 return 타입이 int이다.
		//			  더이상 읽어올 자료가 없으면 -1을 반환한다.
		//           모든 데이터 타입은 byte으로 읽고 쓰기 가능하다. 그렇기 때문에 최소 단위로 읽고 쓴다.
		while((data = bais.read()) != -1) {
			baos.write(data); // 데이터 출력하기 - out에 저장중
		}
		
		// 가지고있는 Byte 배열을 리턴해준다. - outSrc배열에 집어 넣어준다.
		outSrc = baos.toByteArray(); 
		
		bais.close();
		baos.close();
		
				
	}
	

	
}
