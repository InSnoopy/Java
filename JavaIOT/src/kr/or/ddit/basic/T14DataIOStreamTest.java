package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *	기본타입 입출력 보조 스트림
 */
public class T14DataIOStreamTest {
	public static void main(String[] args) throws IOException {
		
		FileOutputStream fos = new FileOutputStream("d:/D_Other/test.dat");
		
		// DataOutputStream은 출력용 데이터를 자료형에 맞게 출력해 준다.
		DataOutputStream dos = new DataOutputStream(fos);
		
		// 보조 스트림이 제공해주는 메소드 사용하기
		// 타입마다 byte크기가 다르기 때문에 순서가 중요하다.
		dos.writeUTF("홍길동");	// 문자열 데이터 출력하기
		dos.writeInt(17);		// 정수형으로 데이터 출력하기
		dos.writeFloat(3.14f);	// 실수형(Float)으로 데이터 출력하기
		dos.writeDouble(3.14);	// 실수형(Double)으로 데이터 출력하기
		dos.writeBoolean(true);	// 논리형으로 데이터 출력하기
		
		System.out.println("출력 완료...");
		
		dos.close();
		// ==========================================================
		// 출력한 데이터 읽어오기
		FileInputStream fis = new FileInputStream("d:/D_Other/test.dat");
		DataInputStream dis = new DataInputStream(fis);
		
		// byte크기 때문에 꺼내는 순서도 중요하다.
		System.out.println("문자열 자료 : " + dis.readUTF());
		System.out.println("정수형 자료 : " + dis.readInt());
		System.out.println("실수형(Float) 자료 : " + dis.readFloat());
		System.out.println("실수형(Double) 자료 : " + dis.readDouble());
		System.out.println("논리형 자료 : " + dis.readBoolean());
		
		dis.close();
		
	}
}

// 결국 기본 데이터 타입을 저장할 때는 데이터마다 저장하는 byte의 크기가 다르기 때문에 읽고 쓸 때 순서가 중요하다.
