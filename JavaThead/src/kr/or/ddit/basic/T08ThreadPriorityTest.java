package kr.or.ddit.basic;

public class T08ThreadPriorityTest {
   public static void main(String[] args) {
      System.out.println("최대우선순위: "+ ThreadTest1.MAX_PRIORITY);
      System.out.println("최소우선순위: "+ ThreadTest1.MIN_PRIORITY);
      System.out.println("보통우선순위: "+ ThreadTest1.NORM_PRIORITY);
      
      Thread[] ths = new Thread[] {
            new ThreadTest1(),
            new ThreadTest1(),
            new ThreadTest1(),
            new ThreadTest1(),
            new ThreadTest1(),
            new ThreadTest2()
      };
      
      //우선순위는 start() 메서드 호출전에 설정해야 한다.
      //setPriority() 메서드가 우선순위를 정한다.
      for (int i = 0; i < ths.length; i++) {
         if (i==5) {
            ths[i].setPriority(Thread.MAX_PRIORITY);
         }else {
            ths[i].setPriority(Thread.MIN_PRIORITY);
         }
      }
      //우선순위 출력
      for (Thread th : ths) {
         System.out.println(th.getName()+"의 우선순위 :"+th.getPriority());
      }
      for (Thread th : ths) {
         th.start();
      }
      for (Thread th : ths) {
         try {
            th.join();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         
      }
      
      
   }
   

}
//대문자를 출력하는 스레드 
class ThreadTest1 extends Thread{
   @Override
   public void run() {
      for (char ch='A'; ch< 'Z'; ch++) {
         System.out.println("대문자"+ch);
         
         //아무것도 하지 않는 반복문(시간때우기용)
         for (int i = 1; i < 100000000; i++) {
            
         }
      }
   }
}


//소문자를 출력하는 스레드 
class ThreadTest2 extends Thread{
   @Override
   public void run() {
      for (char ch='a'; ch< 'z'; ch++) {
         System.out.println("소문자"+ch);
         
         //아무것도 하지 않는 반복문(시간때우기용)
         for (int i = 1; i < 100000000; i++) {
            
         }
      }
   }
}