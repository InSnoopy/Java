package kr.or.ddit.basic;

import java.util.Random;

import javax.swing.JOptionPane;

public class Game {
	
//	컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
//	컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
//	사용자의 가위 바위 보는 showInputDialog() 메서드를 이용하여 입력
//	받는다.
//	입력시간은 5초로 제한하고 카운트 다운을 진행한다.
//	5초안에 입력이 없으면 게임을 진 것으로 처리한다.
//	5초안에 입력이 완료되면 승패를 출력한다.
//	결과예시)
//	=== 결 과 ===
//	컴퓨터 : 가위
//	당 신 : 바위
//	결 과 : 당신이 이겼습니다
	
	public static void main(String[] args) {
		
		Thread countDown = new GameCount();
		countDown.start();
		
		Thread userInput = new GameInput();
		userInput.start();
		
	}
	
}

/**
 *	데이터 입력을 받는 스레드 클래스
 */
class GameInput extends Thread{
	@Override
	public void run() {
		
		Random random = new Random();
		String result ="";
		int computerInt = random.nextInt(2);
		String computerStr = "";
		
		String str = JOptionPane.showInputDialog("가위바위보 입력 : ");
		
		switch(computerInt) {
			case 0:
				computerStr="가위";
				break;
			case 1:
				computerStr="바위";
				break;
			case 2:
				computerStr="보";
				break;
		}
	
		if(str.equals("가위")&&computerStr.equals("보")||
		   str.equals("바위")&&computerStr.equals("가위")||
		   str.equals("보")&&computerStr.equals("바위")) {
			result = "당신이 이겼습니다.";
		}else if(str.equals(computerStr)) {
			result = "무승부";
		}else {
			result = "컴퓨터 승리";
		}
		System.out.println("┌===[ 결과 ]====");
		System.out.println("| 컴퓨터 : " +computerStr);
		System.out.println("| 당 신 : " +str);
		System.out.println("| 결 과 : " +result);
		System.out.println("└==============");
		System.exit(0);
		
	}
	
}

/**
 *	카운트다운 처리를 하는 스레드 클래스
 */
class GameCount extends Thread{
	@Override
	public void run() {
		
		for(int i=5; i>=1; i--) {
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// 10초가 경과되었는데도 입력이 없으면 프로그램을 종료한다.
		System.out.println("5초가 지났습니다. 프로그램을 종료합니다.");
		
		// 남은 스레드 신경 안쓰고 강제 종료할 때 쓰인다.
		System.exit(0); // 프로그램 종료.
	}
}