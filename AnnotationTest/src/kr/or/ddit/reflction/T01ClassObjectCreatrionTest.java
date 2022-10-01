package kr.or.ddit.reflction;

import kr.or.ddit.basic.Service;

/*
 * Class오브젝트(클래스 정보를 담고 있는)를 생성하기
 * @author 305-SEM
 */

public class T01ClassObjectCreatrionTest {
/*
	Java Reflection에 대하여...
	
	1. 자바 리플렉션은 런타임 시점에 클래스 또는 멤버변수, 메서드 생성자 등에 대한
	      정보를 가져오거나 수정할 수 있고, 새로운 객체를 생성하거나 메서드를 실행할 수 있다.
	   (컴파일 시점에 해당 정보를 알수 없는 경우(소스코드 부재)에 유용하게 사용될 수 있음)
	2. Reflection API는 java.lang.reflect패키지와 java.lang.Class를 통해 제공된다.
	3. java.lang.Class의 주요 메서드
	   - getName(), getSuperclass(), getInterfaces(), getModifiers() 등
	4. java.lang.reflection 패키지의 주요 클래스
	   - Field, Method, Constructor, Modifier 등.
 
 */
	Class
	public static void main(String[] args) throws ClassNotFoundException {
		
		/* 
		      클래스 타입의 클래스 오브젝트 얻어오는 방법
		      오브젝트에 대한 내용들을 하나하나 접근하기 위해서는 클래스 정보를 얻어와야한다.
		      그래서! 오브젝트에 먼저 접근해야 한다.
		*/ 
		
		// 첫번째 방법 : Class.forName() 이용
		Class<?> klass = Class.forName("kr.or.ddit.basic.Service");
		
		// 두번째 방법 : getClass() 이용
		Service service = new Service();
		Class<?> clazz = service.getClass();
		
		// 세번째 방법 : .class 이용
		clazz = Service.class; // class Object를 리턴해준다.
		
	}

}
