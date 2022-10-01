package kr.or.ddit.basic;

public class T09ThreadDaemon {
	public static void main(String[] args) {
		
		AutoSaveThread autoSave = new AutoSaveThread();
		
		// 데몬 스레드로 설정하기 (start() 호출전에 설정한다.)
		// 모든 Thread는  setDaemon(false)이 기본값
		autoSave.setDaemon(true);
		
		autoSave.start();
		
		try {
			for(int i=1; i<=20; i++) {
				
				System.out.println("작업 " + i);
				
				Thread.sleep(1000);
			}
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		
		System.out.println("메인 스레드 종료...");
	}
}

/**
 *	자동 저장 기능 제공하는 스레드 (3초에 한번씩 저장하기)
 */
class AutoSaveThread extends Thread{
	public void save() {
		System.out.println("작업 내용을 저장합니다...");
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				// 3초동안 멈춘다. Zzz.. 잔다
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			save(); // 자동저장 기능 호출
		}
	}
}
