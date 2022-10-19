package board.dao;

import java.util.List;

import board.vo.BoardVO;
import comm.dao.MyBatisDao;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoardDao extends MyBatisDao implements IBoardDao{

	private static IBoardDao boardDao;
	private BoardDao() {
		
	}
	public static IBoardDao getInstance() {
		if(boardDao==null) {
			boardDao = new BoardDao();
		}
		return boardDao;
	}
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public int insertBoard(BoardVO bv) {

		return insert("board.insertBoard", bv);
		
	}

	@Override
	public boolean checkBoard(int no) {

		boolean isExist = false;
		
		int cnt = (int) selectOne("board.checkBoard", no); 
		if(cnt < 0) {
			isExist = true;
		}
		
		return isExist;

	}

	@Override
	public int updateBoard(BoardVO bv) {

		return update("board.updateBoard", bv);
	}

	@Override
	public int deleteBoard(int no) {
		
		return delete("board.deleteBoard", no);
		
	}

	@Override
	public List<BoardVO> listBoard() {
		
		return selectList("board.listBoard", null);
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		
		return selectList("board.searchBoard",bv);
		
	}

}
