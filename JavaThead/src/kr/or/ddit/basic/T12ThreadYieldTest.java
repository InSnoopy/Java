package kr.or.ddit.basic;

public class T12ThreadYieldTest {
/*
 	yield() 메서드에 대하여...
 	
 	1. 현재 실행 대기중인 동등한 우선순위 이상의 다른 스레드에게  실행기회를 제공한다.(양보)
 	2. 현재 실행중인 스레드의 상태를 Runnable 상태로 바꾼다.
 	   Runnable(실행) -> Runnable(실행대기)로 변경된다. (CPU 반납)
 	3. yield()메서드를 실행한다고 해서 현재 실행중인 스레드가 곧바로 Runnable 상태로 
 	     전이된다고 확신할 수는 없다.
 	4. 이런 메소드가 있구나만 해라.. 양보가 마지막에 끝나야하지만.. 비양보가 늦게 끝나는 경우도 6:4 비율정도네..
 	
 */
	public static void main(String[] args) {
		
		Thread th1 = new YieldThreadEx1();
		Thread th2 = new YieldThreadEx2();
		
		th1.start();
		th2.start();
	}
}

class YieldThreadEx1 extends Thread{
	
	public YieldThreadEx1() {
		super("양보 스레드");
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			System.out.println(Thread.currentThread().getName()+ " : " +i);
			Thread.yield(); // 양보하기
		}
		
	}
	
}

class YieldThreadEx2 extends Thread{
	
	public YieldThreadEx2() {
		super("비양보 스레드");
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			System.out.println(Thread.currentThread().getName()+ " : " +i);
		}
		
	}
	
}