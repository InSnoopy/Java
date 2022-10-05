package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

public class T01FileTest {
	public static void main(String[] args) throws IOException {
		
		// File 객체 만들기 연습
		
		// 1. new File(String 파일 또는 경로명 )
		// 	=> 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의
		// 	   구분문자는 '\' 또는 '/'를 사용할 수 있다.
		// File이라는 클래스로 file 객체를 만든 것 뿐
		// file이 실제로 만들어지는게 아니라 있다고 가정
		// \<- 두번쓰는 이유는 리터럴 문자 하나 \를 쓰고 싶다면 두번 써야한다.
		// ex) \n <- 이것처럼 escape인것을 알려주는 \하나 + 실제 리터럴 \ 하나
		File file = new File("d:\\D_Other\\test.txt"); 
		System.out.println("파일명 : " + file.getName());
		System.out.println("파일 여부 : " + file.isFile()); // 이것이 파일이냐! return 값은 boolean
		System.out.println("디렉토리(폴더) 여부 : " 
						+ file.isDirectory());
		// isFile, isDirectory가 둘다 false인 경우 test.txt라는 파일이 없나는 뜻
		System.out.println("-------------------------");
		
		// File은 파일, 디렉토리 두개다 핸들링한다.
		File file2 = new File("d:\\D_Other");
		System.out.println(file2.getName() + "은 ");
		if(file2.isFile()) {
			System.out.print("파일");
		}else if(file2.isDirectory()){
			System.out.println("디렉토리(폴더)");
		}
		System.out.println("------------------------");
		
		// 2. new File(File parent, String child)
		// => 'parent'디렉토리 안에 있는 'child'파일 또는 디렉토리를 갖는다.
		
		File file3 = new File(file2, "test.txt");
		System.out.println(file3.getName() + "의 용량 크기 : "
				+ file3.length() + "(bytes)");
		
		// 3. new File(String parent, String child)
		// 경로 앞에 .이 들어갔다면 상대 경로를 의미한다.
		// .은 현재 위치
		// .. 현재 있는 위치의 상위
		File file4 = new File(".\\D_Other\\test\\..", "test.txt");
		System.out.println("절대 경로 : " + file4.getAbsolutePath());
		System.out.println("경로 : " + file4.getPath()); // 생성자 그대로 가져옴
		System.out.println("표준경로 : " + file4.getCanonicalPath()); // 최종계산결과
		
		/*
		 * 디렉토리(폴더) 만들기
		 * 
		 *  1. mkdir() => File객체의 경로 중 마지막 위치의 디렉토리를 만든다.
		 *  		   => 중간의 경로가 모두 미리 만들어져 있어야 한다.
		 *  
		 *  2. mkdirs() => 중간의 경로가 없으면 중간의 경로도 새롭게 만든 후
		 *  			=> 마지막 위치의 디렉토리를 만들어 준다.
		 *  
		 *  => 위 두 메서드 모두 만들기를 성공하면 ture, 실패하면 false를 반환함.
		 */
		
		File file5 = new File("d:/D_Other/연습용");
		
		if(file5.mkdir()) {
			System.out.println(file5.getName() + " 만들기 성공!");
		}else {
			System.out.println(file5.getName() + " 만들기 실패!");
		}
		System.out.println();
		
		File file6 = new File("d:/D_Other/test/java/src");
		if(file6.mkdirs()) {
			System.out.println(file6.getName() + " 만들기 성공!");
		}else {
			System.out.println(file6.getName() + " 만들기 실패!");
		}
		
	}
}
