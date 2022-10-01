package kr.or.ddit.basic;

class Util2{
	
	// '제한된 타입 파라미터 문법'
	// T 옆에 extends Number로 통해서 적어도 Number타입 또는 Number의 자식들만 오게 할 수 있다.
	public static <T extends Number> int compare(T t1, T t2) {
	
		// 모든 숫자의 최상위 클래스는 Number이다.
		// Number안에 doubleValue() 메소드를 가지고 있다.
		// Number 자식들 또한 doubleValue() 메소드를 다 가지고 있다.
		// Number나 Number하위 부분만 매개변수로 오게 하고 싶다.
		double v1 = t1.doubleValue(); // T가 어떤 타입인지 몰라
		double v2 = t2.doubleValue();
		
		return Double.compare(v1, v2);
		
	}
}

/*
 * 제한된 타입 파라미터(Bounded Parameter) 예제
 */
public class T03GenericMethodTest {
	public static void main(String[] args) {
		int result1 = Util2.compare(10, 20);
		
		System.out.println(result1);
		
		int result2 = Util2.compare(3.14, 3);
	
		System.out.println(result2);
		// Util2.compare("C", "JAVA"); // 에러 발생
		// 에러 발생 이유는 Number만 매개 변수로 받을 수 있다.
	
	}
}
