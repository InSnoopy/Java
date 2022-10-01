package hotel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

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
				case 4 :
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
		
		Set<String> keySet = hotelMap.keySet();
		
		if(keySet.size() == 0) {
			System.out.println("등록된 사람이 없습니다.");
		}else {
			Iterator<String> it = keySet.iterator();
			
			while(it.hasNext()) {
				String hotelNum = it.next();
				HotelVO hotelvo = hotelMap.get(hotelNum);
				
				System.out.println("방번호 : " + hotelvo.getGuestRoom() + ",투숙객 : " + hotelvo.getGusetName());
			}
		}
		
	}
	
	public static void main(String[] args) {
		new HotelTest().hotelStart();
	}
	

}
