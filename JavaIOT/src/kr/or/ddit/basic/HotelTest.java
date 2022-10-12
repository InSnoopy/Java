package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.crypto.NullCipher;

public class HotelTest {
   
   private Scanner scan;
   private Map<String, HotelVO> hotelMap;
   
   public HotelTest() {
      scan = new Scanner(System.in);
      hotelMap = new HashMap<String, HotelVO>();
   }
   

   
   public void menu() {
      System.out.println();
      System.out.println("메뉴를 선택하세요.");
      System.out.println(" 1. 체크인");
      System.out.println(" 2. 체크아웃");
      System.out.println(" 3. 객실상태");
      System.out.println(" 4. 업무종료");
      System.out.print(" 번호입력 >>");
      System.out.println();
   }
   
   public void hotelStart() {
	   
	      ObjectInputStream ois = null;
	      
	      try {
	         
	         ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("d:/D_Other/hotelObj.bin")));
	         
	         // 담을 그릇
	         Object obj = null;
	   
	         // 읽기 작업
	         // obj = ois.readObject() : 이 과정에 역직렬화 
	         // readObject()안에서 일어나고 있다는 것이다.
	         while((obj = ois.readObject()) != null) {
	            // 읽어온 데이터를 원래의 객체형으로 변환 후 사용한다.
	            hotelMap = (Map<String, HotelVO>) obj;
	                     
	         }

	      } catch (IOException e) {
	         System.out.println("출력작업 끝...");
	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      }finally {
	         try {
	            ois.close();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	      } 
	   
	   
      System.out.println("===============================");
      System.out.println("어떤 업무를 하시겠습니까?");
      System.out.println("===============================");
      
      while(true) {
         
         menu();
         
         int menuNum = scan.nextInt();
         
         switch(menuNum) {
            case 1 : checkIn();
               break;
            case 2 : checkOut();
               break;
            case 3 : guestRoomList();
               break;
            case 4 : programEnd();
               System.out.println("호텔 문을 닫았습니다.");
               return;
            default :
               System.out.println("잘못 입력했습니다. 다시 입력하세요.");
         }
      }
   }


   private void checkIn() {
      
      System.out.println();
      System.out.println("어느방에 체크인 하시겠습니까?");
      
      System.out.print("방번호 입력 => ");
      String hotelNum = scan.next();
      
      HotelVO hotelvo = hotelMap.get(hotelNum);
      
      if(hotelvo != null) {
         System.out.println(hotelNum + "방에는 이미 사람이 있습니다.");
         return;
      }
      
      System.out.println();
      System.out.println("누구를 체크인 하시겠습니까?");
      System.out.print("이름 입력 => ");
      String name = scan.next();
      
      HotelVO hotelVO = new HotelVO(hotelNum ,name);
      hotelMap.put(hotelNum, hotelVO);
      System.out.println("체크인 되었습니다.");
      
   }
   
   private void checkOut() {
      System.out.println("어느방에 체크아웃 하시겠습니까?");
      System.out.print("방번호 입력 => ");
      String hotelNum = scan.next();
      
      HotelVO hotelvo = hotelMap.get(hotelNum);
      if(hotelvo == null) {
         System.out.println(hotelNum + "체크인한 방이 아닙니다.");
         return;
      }
      
      hotelMap.remove(hotelNum);
      System.out.println("체크아웃 되었습니다.");
   }
   
   private void guestRoomList() {
      
      
//      ObjectInputStream ois = null;
//      
//      try {
//         
//         ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("d:/D_Other/hotelObj.bin")));
//         
//         // 담을 그릇
//         Object obj = null;
//   
//         // 읽기 작업
//         // obj = ois.readObject() : 이 과정에 역직렬화 
//         // readObject()안에서 일어나고 있다는 것이다.
//         while((obj = ois.readObject()) != null) {
//            // 읽어온 데이터를 원래의 객체형으로 변환 후 사용한다.
//            hotelMap = (Map<String, HotelVO>) obj;
//            
//            Set<String> keySet = hotelMap.keySet();
//            
//            if(keySet.size() == 0) {
//               System.out.println("등록된 사람이 없습니다.");
//            }else {
//               for (String key : keySet) {
//                  System.out.println("방번호 : " + key);
//                  System.out.println("투숙객 : " + hotelMap.get(key).getGusetName());
//                  System.out.println("---------------------");
//               }
//            }
//            
//         }
//
//      } catch (IOException e) {
//         System.out.println("출력작업 끝...");
//      } catch (ClassNotFoundException e) {
//         e.printStackTrace();
//      }finally {
//         try {
//            ois.close();
//         } catch (IOException e) {
//            e.printStackTrace();
//         }
//      }
      
      
   }
   
   private void programEnd() {
      
      ObjectOutputStream oos = null;
      
      try {
         
         oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("d:/D_Other/hotelObj.bin")));         
         
         // 쓰기 작업
         oos.writeObject(hotelMap);
         
      }catch(IOException ex) {
         ex.printStackTrace();
      }finally {
         try {
            oos.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      
      
   }
   
   public static void main(String[] args) {
      new HotelTest().hotelStart();
   }
   

}

