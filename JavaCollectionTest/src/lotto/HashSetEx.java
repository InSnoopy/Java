package lotto;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class HashSetEx {

/*
 *  로또를 구매하는 프로그램 작성하기
 
 사용자는 로또를 구매할 때 구매할 금액을 입력하고
 입력한 금액에 맞게 로또번호를 출력한다.
 (단, 로또 한장의 금액은 1000원이고 거스름돈도 계산하여
      출력한다.)

	==========================
         Lotto 프로그램
	--------------------------
	 1. Lotto 구입
	  2. 프로그램 종료
	==========================		 
	메뉴선택 : 1  <-- 입력
			
	 Lotto 구입 시작
		 
	(1000원에 로또번호 하나입니다.)
	금액 입력 : 2500  <-- 입력
			
	행운의 로또번호는 아래와 같습니다.
	로또번호1 : 2,3,4,5,6,7
	로또번호2 : 20,21,22,23,24,25
			
	받은 금액은 2500원이고 거스름돈은 500원입니다.
			
   	 ==========================
         Lotto 프로그램
	--------------------------
	  1. Lotto 구입
	  2. 프로그램 종료
	==========================		 
	메뉴선택 : 2  <-- 입력
		
	감사합니다
 */
	public static void main(String[] args) {
		
//		Random random = new Random();
//		
//		HashSet lottoBox1 = new HashSet();
//		HashSet lottoBox2 = new HashSet();
//
//		for(int i=0; i<5; i++) {
//			
//			int randomNum = random.nextInt(45)+1;
//			boolean isAdded1 = lottoBox1.add(randomNum);	
//			if(isAdded1) {
//
//			}else {
//				i--;
//			}
//			
//		}		
//		
//		for(int i=0; i<5; i++) {
//			
//			int randomNum = random.nextInt(45)+1;	
//			boolean isAdded2 = lottoBox2.add(randomNum);	
//			if(isAdded2) {
//				
//			}else {
//				i--;
//			}
//			
//		}		

		LottoProgram lotto1 = new LottoProgram();
		LottoProgram lotto2 = new LottoProgram();
		
		lotto1.lottoMachine();
		lotto2.lottoMachine();
		System.out.println(lotto1.getLottoBox());
		System.out.println(lotto2.getLottoBox());
		
		HashSet userSet = new HashSet();
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("로또 번호 입력하세요: ");

		userSet.add(Integer.parseInt(scanner.nextLine()));
		userSet.add(Integer.parseInt(scanner.nextLine()));
		userSet.add(Integer.parseInt(scanner.nextLine()));
		userSet.add(Integer.parseInt(scanner.nextLine()));
		userSet.add(Integer.parseInt(scanner.nextLine()));
		userSet.add(Integer.parseInt(scanner.nextLine()));


		
		
		System.out.println(userSet);
		
		lotto1.lottoWin(userSet);
//		for(int i=0; i<5; i++) {
//			
//			int userNum = userNumber+i;
//			userSet.add(userNum);
//			System.out.println(userSet);
//			
//			Iterator it = userSet.iterator();
//			while(it.hasNext()) {				
//				System.out.println(it.next());
//			}
//			
//		}
			
		
//		lotto1.lottoWin(userNumber1);
		
		
//		Iterator it = lottoBox1.iterator();
		
//		while(it.hasNext()) {
//			if(it.next().equals(userNumber)) {
//				System.out.println("맞추셨습니다.");
//			}else {
//				System.out.println("틀렸습니다.");
//			}
//		}
		
		
	}
}


class LottoStore{
	
}

class User{
	
}
