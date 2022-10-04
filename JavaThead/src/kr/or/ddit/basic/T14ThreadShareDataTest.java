package kr.or.ddit.basic;

public class T14ThreadShareDataTest {
/*
 	원주율을 계산하는 스레드가 있고, 계산된 워주율을 출력하는 스레드가 있다.
 	워주율을 계산한 후 이 값을 출력하는 프로그램을 작성하시오.
 	(이 때 원주율을 저장하는 객체가 필요하다.)
*/
	public static void main(String[] args) {
		
		ShareData sd = new ShareData();
		
		CalcPIThread cth = new CalcPIThread(sd);
		printPIThread pth = new printPIThread(sd);
		
		cth.start();
		pth.start();
		
	}
}

// 공통 객체 -> 원주율을 계산해서 저장
class ShareData{
	
	private double result; // 원주율이 저장될 변수
	
	/*
	 	volatile : 휘발성이란 뜻 - 항상 변할 수 있다.
		volatile => 선언된 변수를 컴파일러의 최적화 대상에서 제외시킨다.
			즉, 값이 변경되는 즉시 변수에 적용시킨다. 멀티 스레드 환경에서
			하나의 변수가 완벽하게 한번에 작동하도록 보장하는 키워드
			(일종의 동기화)
		
		isOk-> 각각 메소드에 넣게되면 false인 상태이다.
		계산이 끝나면 계산하는 클래스에 있는 isOk의 변수는 true가 되지만!
		출력하는 클래스에 있는 isOk는 처음에 받은 false만 보고 있다.
		멀리 있는 변수는 true로 변경되었는데 이걸 가까이 있는 변수가 아닌 멀리 있는 변수로 가져오게 설정하는 것
		
		이 변수에서 만큼은 속도가 조금 느려져도 공용되는 변수로 가져오게 하라
	 */
	
	volatile private boolean isOk = false; // 원주율 계산이 완료되었는지 확인용.
	
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
	public boolean isOk() {
		return isOk;
	}
	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}
	
}

// 원주율 계산을 위한 스레드 클래스
class CalcPIThread extends Thread{
	
	private ShareData sd;
	
	public CalcPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
	/*
		↓ 원주율 구하는 공식
		원주율 = (1/1 - 1/3 + 1/5 - 1/7 + 1/9 .........) * 4;
				 1  -  3  +  5  -  7  +  9 => 분모
				 0     1     2     3     4 => 분모를 2로 나눈 몫
	 */
		double sum = 0.0;
		for(int i=1; i<=1500000000; i+=2) {
			if( ((i/2) % 2) == 0){
				sum += (1.0 / i);
			}else {
				sum -= (1.0 / i);
			}
		}
		sd.setResult(sum * 4);
		sd.setOk(true); // 원주율 계산이 완료되었음을 나타냄.
	}
	
}

// 계산된 원주율을 출력하는 스레드
class printPIThread extends Thread{
	
	private ShareData sd;
	public printPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		while(true) {
			// 원주율 계산이 완료 될때까지 기다린다.
			if(sd.isOk()) {
				break;
			}
		}
		
		System.out.println();
		System.out.println("계산된 원주율 : " + sd.getResult());
		System.out.println("       PI : " + Math.PI);
	}
}
