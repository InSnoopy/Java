package kr.or.ddit.basic;

public class T20WaitNotifyTest {
	public static void main(String[] args) {
		
		DataBox dataBox = new DataBox();
		
		ProducerThread pTh = new ProducerThread(dataBox);
		ConsumerThread cTh = new ConsumerThread(dataBox);
		
		pTh.start();
		cTh.start();
		
	}
}

// 데이터를 공통으로 사용하는 클래스 (공유객체)
class DataBox{
	
	private String data;
	
	// data가 null이 아닐대 data값을 반환하는 메서드
	public synchronized String getData() {
		if(this.data == null) { // 데이터가 없다면 wait으로 들어가 있어라
			try {
			System.out.println(Thread.currentThread().getName() + "wait() 호출");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String returnData = this.data;
		System.out.println("읽어온 데이터 : " + returnData);
		this.data = null; // 꺼낸 후 데이터를 초기화 시키는 작업
		System.out.println(Thread.currentThread().getName() + " notify() 호출");
		notify(); // 대기실에서 데이터가 있는데 셋팅 할 사람을 부른다. 없으면 말고~~
		
		return returnData;
	}
	
	// data가 null일 경우에만 자료를 셋팅하는 메서드
	public synchronized void setData(String data) {
		if(this.data != null) { // 데이터가 있다면 설정할 필요가 없다. wait으로 들어가 있어라
			try {
				System.out.println(Thread.currentThread().getName() + " wait() 호출");
				wait();
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		this.data = data;
		System.out.println("셋팅한 데이터 : " + this.data);
		System.out.println(Thread.currentThread().getName() + " notify() 호출");
		notify();
	}
	
}

// 데이터를 셋팅만 하는 스레드
class ProducerThread extends Thread{
	private DataBox dataBox;
	
	public ProducerThread(DataBox dataBox) {
		super("ProducerThread");
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=5; i++) {
			String data = "Data-" + i;
			System.out.println("dataBox.setData(" + data + ") 호출");
			dataBox.setData(data);
		}
	}
	
}

// 데이터를 읽어만 오는 스레드
class ConsumerThread extends Thread{
	private DataBox dataBox;
	
	public ConsumerThread(DataBox dataBox) {
		super("ConsumerThread");
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=5; i++) {
			String data = dataBox.getData();
			System.out.println("dataBox.getData() : " + data);
		}
	
	}
	
}
