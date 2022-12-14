package kr.or.ddit.basic;

public class T03LambdaTest {
	static int stVar = 0;
	private String name = "";
	
	public void testMethod(final int temp) {
		
		final int localVar = 50;
		int kor = 100;
		
		/*
		 * 람다식 내부에서 사용되는 지역변수는 모두 final이어야 한다.
		 * 보통은 final을 붙이지 않으면 컴파일러가 자동으로 붙여준다.
		 * 단, 지역변수의 값을 변경하는 식이 있을 경우에는 자동으로 
		 * 붙여주지 않는다.
		 */
		
		// kor = 200; -> 변경하는 순간 밑에 kor지역 변수가 error난다.
		
		// 람다식에서 지역변수사용하기
		LambdaInterface1 lam1 = 
				() -> {
					System.out.println("temp = " + temp);
					System.out.println("localVar = " 
							+ localVar); // 지역변수의 값이 변하면 안된다 그러므로 final이여야 한다.
					System.out.println("kor = " + kor);
					System.out.println("stVar = " + stVar);
					System.out.println("name = " + this.name);
				};
			lam1.test();
	}
	
	public static void main(String[] args) {
		new T03LambdaTest().testMethod(200);
	}
	
	
}
