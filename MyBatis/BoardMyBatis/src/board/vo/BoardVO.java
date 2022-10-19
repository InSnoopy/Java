package board.vo;

import java.sql.Date;

public class BoardVO {

//	위의 테이블을 작성하고 게시판을 관리하는
//	다음 기능들을 구현하시오.
//
//	기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 
//	 
//	------------------------------------------------------------
//
//	게시판 테이블 구조 및 시퀀스
//
//	create table jdbc_board(
//	    board_no number not null,  -- 번호(자동증가)
//	    board_title varchar2(100) not null, -- 제목
//	    board_writer varchar2(50) not null, -- 작성자
//	    board_date date not null,   -- 작성날짜
//	    board_content clob,     -- 내용
//	    constraint pk_jdbc_board primary key (board_no)
//	);
//	create sequence board_seq
//	    start with 1   -- 시작번호
//	    increment by 1; -- 증가값
//			
//	----------------------------------------------------------
//
//	// 시퀀스의 다음 값 구하기
//	//  시퀀스이름.nextVal
	
	
	private int boardNo;
	private String boardTitle;
	private String boardWriter;
	private Date boardDate;
	private String boardContent;
	
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	
	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardWriter=" + boardWriter
				+ ", boardDate=" + boardDate + ", boardContent=" + boardContent + "]";
	}
	
	
	
}
