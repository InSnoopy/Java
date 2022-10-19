package board;

import java.util.List;
import java.util.Scanner;

import board.service.BoardService;
import board.service.IBoardservice;
import board.vo.BoardVO;

public class BoardMain {
	
	private Scanner scan = new Scanner(System.in);
	
	private static BoardMain boardMain;
	private IBoardservice boardService;
	private BoardMain() {
		boardService = BoardService.getInstance();
	}
	public static BoardMain getInstance() {
		if(boardMain==null) {
			boardMain = new BoardMain();
		}
		return boardMain;
	}
	
	// 기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 
	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 게시글 작성");
		System.out.println("  2. 게시글 삭제");
		System.out.println("  3. 게시글 수정");
		System.out.println("  4. 전체 목록 출력");
		System.out.println("  5. 게시글 검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 게시글 등록
					insertBoard();
					break;
				case 2 :  // 게시글 삭제
					deleteBoard();
					break;
				case 3 :  // 게시글 수정
					updateBoard();
					break;
				case 4 :  // 게시글 목록 출력
					listBoard();
					break;
				case 5 :  // 게시글 검색
					searchBoard();
					break;
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
	}
	
	

	private void insertBoard() {

		System.out.print("게시글 제목 >> ");
		String boardTitle = scan.next();
		
		System.out.print("게시글 작성자 >> ");
		String boardWriter = scan.next();
		
		scan.nextLine(); // 버퍼 비우기
		
		System.out.print("게시글 내용 >> ");
		String boardContent = scan.nextLine();
		
		BoardVO bv = new BoardVO();
	
		bv.setBoardTitle(boardTitle);
		bv.setBoardWriter(boardWriter);
		bv.setBoardContent(boardContent);
		
		int cnt = boardService.insertBoard(bv);
		
		if(cnt > 0) {
			System.out.println(boardTitle + " 게시글 등록 성공");
		}else {
			System.out.println(boardTitle + " 게시글 등록 실패");
		}		
		
	}
	
	private void deleteBoard() {
		
		System.out.println();
		System.out.println("삭제할 게시판No를 입력하세요.");
		System.out.print("게시판 NO >> ");
		
		scan.nextLine();
		int boardNo = Integer.parseInt(scan.next());

		int cnt = boardService.deleteBoard(boardNo);
		
		if(cnt > 0) {
			System.out.println(boardNo + " 게시글 삭제 성공");
		}else {
			System.out.println(boardNo + " 게시글 삭제 실패");
		}
		
	}
	
	private void updateBoard() {
		
		boolean exist = false;
		
		int boardNo = 0;
		
		do {
			System.out.println();
			System.out.println("수정할 게시글 NO를 입력하세요.");
			System.out.print("게시글No >> ");
			
			boardNo = Integer.parseInt(scan.next());
			
			exist = boardService.checkBoard(boardNo);
			
			if(!exist) {
				System.out.println("게시글NO가 " + boardNo + "인 게시글은 존재하지 않습니다.");
				System.out.println("다시 입력해주세요.");
			}
			
		}while(!exist);
		
		System.out.print("게시글 제목 >> ");
		String boardTitle = scan.next();
		
		scan.nextLine(); // 버퍼 비우기
		
		System.out.print("게시글 내용 >> ");
		String boardContent = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoardTitle(boardTitle);
		bv.setBoardContent(boardContent);
		bv.setBoardNo(boardNo);
			
		int cnt = boardService.updateBoard(bv);
		
		if(cnt > 0) {
			System.out.println(boardNo + " 게시글 수정 성공");
		}else {
			System.out.println(boardNo + " 게시글 수정 실패");
		}
			
		
	}
	
	private void listBoard() {
		
		System.out.println();
		System.out.println("----------------------------------------------------");
		System.out.println(" No\t제목\t작성자\t날짜\t\t내용");
		System.out.println("----------------------------------------------------");
		
		List<BoardVO> memList = boardService.listBoard();
		
		if(memList.size() == 0) {
			System.out.println("게시글이 존재하지 않습니다.");
		}else {
			
			for (BoardVO bv : memList) {
				System.out.println(bv.getBoardNo() + "\t" + bv.getBoardTitle() + "\t" + bv.getBoardWriter() + "\t" + bv.getBoardDate() + "\t" + bv.getBoardContent());
			}
			
			System.out.println("----------------------------------------------------");
			System.out.println("출력 작업 끝.");			
		}
		
	}
	
	private void searchBoard() {

		// 검색할 회원ID, 회원이름, 전화번호, 주소등을 입력하면
		// 입력한 정보만 사용하여 검색하는 기능을 구현하시오.
		// 주소는 입력한 값이 포함만 되어도 검색되도록 한다.
		// 입력을 하지 않을 자료는 엔터키로 다음 입력으로 넘긴다.
			
		scan.nextLine(); // 버퍼 비우기
		
		System.out.println();
		System.out.println("검색할 게시글 정보를 입력하세요.");
		
		System.out.print("게시글 제목 >> ");
		String boardTitle = scan.nextLine().trim(); //trim으로 공백을 지우고
		
		System.out.print("게시글 작성자 >> ");
		String boardWriter = scan.nextLine().trim();
		
		System.out.print("게시글 내용 >> ");
		String boardContent = scan.nextLine().trim();
	
		
		BoardVO bv = new BoardVO();
		
		bv.setBoardTitle(boardTitle);
		bv.setBoardWriter(boardWriter);
		bv.setBoardContent(boardContent);
		
		List<BoardVO> boardList = boardService.searchBoard(bv);
		
		System.out.println();
		System.out.println("---------------------------------------");
		System.out.println(" 제목\t작성자\t내용");
		System.out.println("---------------------------------------");
		
		if(boardList.size() == 0) {
			System.out.println("게시글이 존재하지 않습니다.");
		}else {
			
			for (BoardVO bv2 : boardList) {
				
				System.out.println(bv2.getBoardTitle() + "\t" + bv2.getBoardWriter() + "\t" + bv2.getBoardContent());
			
			}
			
			System.out.println("---------------------------------------");
			System.out.println("검색 작업 끝.");			
		}
		
	}
	
	public static void main(String[] args) {
		
		BoardMain boardObj = BoardMain.getInstance();
		boardObj.start();
		
	}
}
