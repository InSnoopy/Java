package kr.or.ddit.basic;

public class SingletonTest {
	public static void main(String[] args) {
		
		// MySingleton test1 = new MySingletop(); // new 명령 불가
		
		// getInstance()를 이용하여 객체 생성
		MySingleton text2 = MySingleton.getInstance();
		text2.displayText();
		
		MySingleton text3 = MySingleton.getInstance();
		
		System.out.println("text2 => " + text2);
		System.out.println("text3 => " + text3);
		
		
	}
}
