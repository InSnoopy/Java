package board.service;

import java.util.List;

import board.dao.BoardDao;
import board.dao.IBoardDao;
import board.vo.BoardVO;

public class BoardService implements IBoardservice{

	private static IBoardservice boardService;
	private IBoardDao boardDao;
	
	private BoardService() {
		boardDao = BoardDao.getInstance();
	}
	
	public static IBoardservice getInstance() {
		if(boardService==null) {
			boardService = new BoardService();
		}
		return boardService;
	}
	
	@Override
	public int insertBoard(BoardVO bv) {
		int cnt = boardDao.insertBoard(bv);
		return cnt;
	}

	@Override
	public boolean checkBoard(int no) {
		return boardDao.checkBoard(no);
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
		List<BoardVO> boardList = boardDao.searchBoard(bv); 
		return boardList;
	}

}
