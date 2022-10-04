package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HorseTest {
	
	static int horseRank = 1;

	public static void main(String[] args) {
		
		
		List<Horse2> horseList = new ArrayList();
		
		horseList.add(new Horse2("1번말"));
		horseList.add(new Horse2("2번말"));
		horseList.add(new Horse2("3번말"));
		horseList.add(new Horse2("4번말"));
		horseList.add(new Horse2("5번말"));
		
		
//		Thread th3 = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				
//				for(int j=0; j<5; j++) {				
//					System.out.print(horseList.get(j).getStage());
//					System.out.println();	
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//
//			}
//		});
//		
//		th3.start();

		
//		Horse3 horse1 = new Horse3(horseList.get(0));
//		Horse3 horse2 = new Horse3(horseList.get(1));
//		Horse3 horse3 = new Horse3(horseList.get(2));
//		Horse3 horse4 = new Horse3(horseList.get(3));
//		Horse3 horse5 = new Horse3(horseList.get(4));
		
		for (Horse2 horse : horseList) {
			horse.start();			
		}

			
//		horse1.start();
//		horse2.start();
//		horse3.start();
//		horse4.start();
//		horse5.start();

//		horse3.setDaemon(true);
//		horse3.start();
		
		for (Horse2 horse : horseList) {
			try {
				horse.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (Horse2 horse : horseList) {
			System.out.println(horse.getHorseName() + " : " + horse.getRank() + "등");
		}
		
	}
	
}


