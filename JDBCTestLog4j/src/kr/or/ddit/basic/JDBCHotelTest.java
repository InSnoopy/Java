package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class JDBCHotelTest {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan;

	public JDBCHotelTest() {
		scan = new Scanner(System.in);
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
		
		System.out.println();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		String name = scan.next();

		try {
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "LHJ93", "java");
			
			String sql = ""
					+ " INSERT INTO HOTEL_MNG "
					+ " (ROOM_NUM, GUEST_NAME) "
					+ " VALUES (?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hotelNum);
			pstmt.setString(2, name);
			
			int cnt = pstmt.executeUpdate();
					
			if(cnt > 0) {
				System.out.println("체크인 되었습니다.");
			}else {
				System.out.println("체크인 실패되었습니다.");
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			
			if(rs != null)try {rs.close();}catch(SQLException ex) {}
			if(stmt != null)try {stmt.close();}catch(SQLException ex) {}
			if(pstmt != null)try {pstmt.close();}catch(SQLException ex) {}
			if(conn != null)try {conn.close();}catch(SQLException ex) {}
			
		}
		
	}
	
	private void checkOut() {
		System.out.println("어느방에 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		String hotelNum = scan.next();
		
		try {
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "LHJ93", "java");
			
			String sql = ""
					+ " DELETE FROM HOTEL_MNG "
					+ " WHERE ROOM_NUM = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hotelNum);
			
			int cnt = pstmt.executeUpdate();
					
			if(cnt > 0) {
				System.out.println("체크아웃 되었습니다.");
			}else {
				System.out.println("체크아웃 실패되었습니다.");
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			
			if(rs != null)try {rs.close();}catch(SQLException ex) {}
			if(stmt != null)try {stmt.close();}catch(SQLException ex) {}
			if(pstmt != null)try {pstmt.close();}catch(SQLException ex) {}
			if(conn != null)try {conn.close();}catch(SQLException ex) {}
			
		}
		
		
	}
	
	private void guestRoomList() {
		
		try {
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "LHJ93", "java");
			
			String sql = ""
					+ " SELECT ROOM_NUM, GUEST_NAME "
					+ " FROM HOTEL_MNG";
			
			stmt = conn.createStatement();
			
			//3. 쿼리 실행
			// executeQuery: select => 결과집합(ResultSet)을 반환
			ResultSet resultSet = stmt.executeQuery(sql);	
			  
		    //4. 실행 결과 받기
			while (resultSet.next()) {
			
				String roomNum = resultSet.getString("ROOM_NUM");
				String guestName = resultSet.getString("GUEST_NAME");

				System.out.printf("%s \t %s\n", roomNum, guestName);
		    }
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			
			if(rs != null)try {rs.close();}catch(SQLException ex) {}
			if(stmt != null)try {stmt.close();}catch(SQLException ex) {}
			if(pstmt != null)try {pstmt.close();}catch(SQLException ex) {}
			if(conn != null)try {conn.close();}catch(SQLException ex) {}
			
		}
		
	}
	
	public static void main(String[] args) {
		new JDBCHotelTest().hotelStart();
	}
	

}
