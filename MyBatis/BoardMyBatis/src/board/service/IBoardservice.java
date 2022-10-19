package board.service;

import java.util.List;

import board.vo.BoardVO;

public interface IBoardservice {

	/**
	 * BoardVO에 담겨진 데이터를 DB에 insert하는 메서드
	 * @param bv DB에 insert할 데이터가 저장된 BoardVO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환됨.
	 */
	public int insertBoard(BoardVO bv);
	
	/**
	 * 
	 * @param 
	 * @return 
	 */
	public boolean checkBoard(int no);
	
	/**
	 * 하나의 BoardVO객체를 이용하여 DB정보를 update하는 메서드
	 * @param bv update할 게시글이 들어있는 BoardVO객체
	 * @return 작업성공 : 1, 작업실패: 0
	 */
	public int updateBoard(BoardVO bv);
	
	/**
	 * 게시글No를 매개변수로 받아서 해당 게시글을 삭제하는 메서드
	 * @param boardNo 삭제할 게시글No
	 * @return 작업성공: 1, 작업실패 : 0
	 */
	public int deleteBoard(int no);
	
	/**
	 * DB에 테이블에 존재하는 전체 게시글를 조회하기 위한 메서드
	 * @return 게시글을 담고있는 List타입의 객체
	 */
	public List<BoardVO> listBoard();


	/**
	 * BoardVO에 담긴 데이터를 이용하여 게시글을 검색하는 메서드
	 * @param bv 게시글을 검색하기 위한 데이터
	 * @return 검색된 결과를 담고 있는 List타입의 객체
	 */
	public List<BoardVO> searchBoard(BoardVO bv);
	
	
	
}
