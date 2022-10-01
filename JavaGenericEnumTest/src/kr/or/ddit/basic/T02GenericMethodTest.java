package kr.or.ddit.basic;

class Util{
	/*
	 * 제너릭 메서드 <T, R> R method(T t)
	 * 
	 * 파라미터 타입과 리턴타입으로 타입글자를 가지는 메서드
	 * 
	 * 선언 방법 : 리턴타입 앞에 <> 기호를 추가하고 타입글자를 기술한 후 사용함.
	 */
	public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
		
		boolean keyCompare = p1.getKey().equals(p2.getKey());
		boolean valueCompare = p1.getValue().equals(p2.getValue());
		
		return keyCompare && valueCompare;
	}
}
/*
 * 멀티타입<K, V>를 가지는 제너릭 클래스
 * @param <K>
 * @param <V>
 */

// Pair 클래스가 어떤 타입으로 들어올지 모르니깐 제너릭 클래스로 설정한 것
// 제너릭에서 들어올 수 있는 타입이 어느정도 제한을 주는 방법이 있다.
// 타입을 제한하는 문법이 '제한된 타입 파라미터 문법'
// 제너럭 클래스에도 '제한된 타입 파라미터'를 설정 가능하다.
class Pair<K, V>{
	private K key;
	private V value;
	
	public Pair(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	
	// 키와 값을 모두 출력
	// 전역 변수, 지역 변수 개념처럼
	// 제너릭 클래스 안에 제너릭 메서드로 선언하면
	// 제너릭 메서드가 우선순위로 읽힌다.
	public <K, V> void displayAll(K key, V val) {
		System.out.println(key + " : " + val);
	}
	
}


public class T02GenericMethodTest {
	public static void main(String[] args) {
		
		// 제너릭 클래스도 객체를 생성할 때는 무조건 타입을 알려줘야한다.
		Pair<Integer, String> p1 = new Pair<Integer, String>(1, "홍길동");
		Pair<Integer, String> p2 = new Pair<Integer, String>(1, "홍길동");
		
		// 구체적 타입을 명시적으로 지정(생략가능)
		// 제너릭 메서드도 제너릭 클래스와 동일하게 타입을 넣어야한다.
		// 하지만 이건 생략이 가능하다. 가능한 이유는 p1,p2가 제너릭 클래스가 어떤 타입인지 선언이 되어있기 때문이다.
		boolean result1 = Util.<Integer, String>compare(p1, p2);
		
		if(result1) {
			System.out.println("논리(의미)적으로 동일한 객체임.");
		}else {
			System.out.println("논리(의미)적으로 동일한 객체 아님.");
		}
		
		// 제너릭 클래스로 이번에는 타입이 String,String으로 선언했다.
		// 이렇게 같은 클래스여도 타입을 여러가지고 뽑아 낼 수 있다.
		Pair<String, String> p3 = new Pair<String, String>("001", "홍길동");
		Pair<String, String> p4 = new Pair<String, String>("002", "홍길동");
		
		boolean result2 = Util.compare(p3, p4);
		
		if(result2) {
			System.out.println("논리(의미)적으로 동일한 객체임.");
		}else {
			System.out.println("논리(의미)적으로 동일한 객체 아님.");
		}
		
		// p1.<String, Integer>displayAll("키", 180);
		p1.displayAll("키", 180); // 위에랑 같은 내용임
		
		
	}
}
