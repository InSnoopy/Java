package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T04LambdaTest {
	public static void main(String[] args) {
		
		List<String> list = new ArrayList<String>();
		
		list.add("홍길동1");
		list.add("홍길동2");
		list.add("홍길동3");
		list.add("홍길동4");
		list.add("홍길동5");
		
//		for (String str : list) {
//			System.out.println(str);
//		}

		// forEach라는 메소드는 Consumer<> 객체를 (name) 받아 이건 익명객체이기 때문에 람다식으로 표현 가능
		// 메서드 안에 메서드를 넣는 느낌이다.
		list.forEach(name->System.out.println(name));

	}
}
