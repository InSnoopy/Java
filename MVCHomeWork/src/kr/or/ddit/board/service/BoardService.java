package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;

public class BoardService implements IBoardService{

	private IBoardDao boardDao;
	
	public BoardService() {
		boardDao = new BoardDao();
	}
	
	@Override
	public int insertBoard(BoardVO bv) {
		int cnt = boardDao.insertBoard(bv);
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = boardDao.updateBoard(bv);
		return cnt;
	}

	@Override
	public int deleteBoard(int no) {
		int cnt = boardDao.deleteBoard(no);
		return cnt;
	}

	@Override
	public List<BoardVO> listBoard() {
		List<BoardVO> boardList = boardDao.listBoard();
		return boardList;
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkBoard(int no) {
		// TODO Auto-generated method stub
		return false;
	}

}
