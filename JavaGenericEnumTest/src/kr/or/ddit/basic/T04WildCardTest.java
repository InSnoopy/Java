package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T04WildCardTest {
/*
 * 와일드 카드에 대하여...
 * 
 * 와일드카드(?)는 제너릭 타입을 이용한 타입 안전한 코딩을 위해 사용되는 
 * 특별한 종류의 인수(Argument)로서 변수선언, 객체생성 및 메서드 정의할 때 사용된다.
 * 	(제너릭 타입 선언시에는 사용할 수 없다)
 * 
 * <? extends T> => 와일드 카드의 상한 제한. T와 그 자손들만 가능.
 * <? super T> => 와일드 카드의 하한 제한. T와 그 조상들만 가능.
 * <?>         => 허용가능한 모든 타입이 가능.
 * 
 * 제너릭이 있는 와일드카드는 없다.
 * 타입이 제너릭해??? -> 무슨 타입인지 모른다.
 * 
 * 앞에 쓴 객체중에 p1이라는 변수가 Pair객체의 타입이 어떤 타입인지 상관 없다는 뜻이다.
 * 와일드카드를 썼다는 건 제너릭 클래스를 사용 했다는 뜻이기도 하다.
 * Pair<?, ?> p1 = new Pair<Integer, String>(1, "홍길동");
 * 
 */
	
	public static void main(String[] args) {
		
		// List는 제너릭 인터페이스였다.!
		// strList의 변수는 List의 타입이기만 하면 된다.
		// ArrayList<> <-안에 String이 들어가도 되고 Number가 들어가도 된다.
		// List<?> strList = new ArrayList<Integer>();
		
		FruitBox<Fruit> fruitBox = new FruitBox<Fruit>(); // 과일 상자 Fruit 생략됨
		FruitBox<Apple> appleBox = new FruitBox<Apple>(); // 사과 상자

		// FruitBox <T extends Fruit> 로 선언하면 Fruit 타입이 아니면 생성 자체를 못한다. 
		// FruitBox<Gabage> gabageBox = new FruitBox<>(); // 쓰레기 상자
		
		fruitBox.add(new Apple());
		fruitBox.add(new Grape());
		
		appleBox.add(new Apple());
		// appleBox.add(new Grape()); <- appleBox는 Apple타입만 담을 수 있다.
	
		// gabageBox.add(new Gabage());
		// gabageBox.add(new Gabage());
		
		Juicer.makeJuice(fruitBox);
//		Juicer.makeJuice(appleBox);
		// 쓰레기는 쥬스로 만들면 안되지만 들어간다.
		// Juicer 클래스에 <T> -> <T extends Fruit>로 변경하면 Fruit타입만 들어 올 수 있다.
		// fruitBox 클래스에 <T> -> <T extends Fruit>로 변경해도 된다.
		//  Juicer.makeJuice(gabageBox);
	
	}
}

// 쥬스 클래스
class Juicer{
	
	// static <T> void makeJuice(FruitBox<Fruit> box) {
	// FruitBox<?> -> 로 사용 가능한 이유는
	// FruitBox 객체 생성하려면 무조건 Fruit 타입이여만 만들 수 있기 때문에..
	static void makeJuice(FruitBox<Fruit> box) {
		String fruitListStr = ""; // 과일 목록
		
		int cnt = 0;
		for(Object f : box.getFruitList()) {
			if(cnt == 0) {
				fruitListStr += f;
			}else {
				fruitListStr += "," + f;
			}
			
			cnt++;
		}
		
		System.out.println(fruitListStr + " => 쥬스 완성!!!");
	}
}

// 과일 클래스
class Fruit{
	private String name; // 과일 이름

	public Fruit(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "과일 (" + name + ")";
	}	
}

// 사과 클래스
class Apple extends Fruit{
	
	public Apple() {
		super("사과");
	}
}

// 포도 클래스
class Grape extends Fruit{
	
	public Grape() {
		super("포도");
	}
}



// 과일 상자 (제너릭 클래스)
class FruitBox<T extends Fruit>{
	
	private List<T> fruitList;

	public FruitBox() {
		fruitList = new ArrayList<T>();
	}

	public List<T> getFruitList() {
		return fruitList;
	}
	
	public void add(T fruit) {
		fruitList.add(fruit);
	}
	
	
	
}


// 쓰레기 상자
class Gabage {
	private String name;

	public Gabage() {
		this.name = "쓰레기";
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "쓰레기";
	}	
}