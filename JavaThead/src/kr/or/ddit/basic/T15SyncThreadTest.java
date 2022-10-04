package kr.or.ddit.basic;

public class T15SyncThreadTest {
	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		
		WorkerThread th1 = new WorkerThread("1번스레드", sObj);
		WorkerThread th2 = new WorkerThread("2번스레드", sObj);
		
		th1.start();
		th2.start();
	}
}

// 공통으로 사용할 객체
class ShareObject{
	
	private int sum = 0;
	
	// 필요한 임계영역에서만 동기화 처리하는 것이 좋다.
	// 동기화 하는 방법1 : 메서드 자체에 동기화 처리하기
	// synchronized(동기화) : 한놈씩 서로 돌아가면서 서로 작업을 하겠금 한다.
	// 이걸 안쓰면 sum이 여러 스레드가 동시에 들어와서 작업하면서 이상하게 출력된다.
//	synchronized public void add() {
//		for(int i=0; i<1000000000; i++) {} // 시간때우기용
//		
//		int n = sum;
//		n += 10;
//		sum = n;
//
//		System.out.println(Thread.currentThread().getName()+ " 합계: " + sum);
//		
//	}
	
	
	public void add() {
		
		// 동기화 하는 방법2 : 동기화 블록으로 설정하기
		// mutex : Mutual exclusion Object(상호배제 : 동시접근 차단)
		// this : ShareObject -> 자기 자신 객체
		synchronized(this) {
			
			for(int i=0; i<1000000000; i++) {} // 시간때우기용
			
			int n = sum;
			n += 10;
			sum = n;
			
			System.out.println(Thread.currentThread().getName()+ " 합계: " + sum);
		}
		
		
		
	}
}

// 작업을 수행하는 스레드
class WorkerThread extends Thread{
	private ShareObject sObj;
	
	public WorkerThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			// 동기화 하는 방법2 : 동기화 블록으로 설정하기2
			synchronized (sObj) {
				sObj.add();
			}
		}
	}
}
