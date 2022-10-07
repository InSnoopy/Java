package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04ByteArrayIOTest {
	
	public static void main(String[] args) throws IOException {
		
		
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		byte[] temp = new byte[4]; // 자료를 읽을 때 사용할 배열
		
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int readBytes = 0; // 몇byte를 읽었는지 담는 변수 (4,4,2,-1)순으로 리턴하네요~
		
		// read() -> 파라미터가 없는건 1byte씩 읽는다.
		while((readBytes = bais.read(temp)) != -1) {
//			baos.write(temp); // temp마지막에 [8, 9, 6, 7]로 출력된다..
			baos.write(temp, 0, readBytes); 
			// temp : 어떤 byte타입의 배열을?? 
			// 0 = 몇번째 index부터, 
			// readBytes = 몇번;
			System.out.println("temp : " + Arrays.toString(temp));
		}
		
		outSrc = baos.toByteArray();
		
		
		System.out.println("inSrc = > " + Arrays.toString(inSrc));
		System.out.println("outSrc = > " + Arrays.toString(outSrc));
		
		
				
	}
	

	
}
