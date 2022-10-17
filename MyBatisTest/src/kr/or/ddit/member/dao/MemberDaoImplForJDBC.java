package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil3;

public class MemberDaoImplForJDBC implements IMemberDao{

	private static IMemberDao memDao;
	private MemberDaoImplForJDBC() {
		
	}
	public static IMemberDao getInstance() {
		if(memDao == null) {
			memDao = new MemberDaoImplForJDBC();
		}
		return memDao;
	}
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public int insertMember(MemberVO mv) {
		
		int cnt = 0;

		try {	

			conn = JDBCUtil3.getConnection();
			
			String sql = ""
					+  "insert into mymember"
					+  "(MEM_ID, MEM_NAME, MEM_TEL, MEM_ADDR, REG_DT) "
					+  "values (?, ?, ?, ?, sysdate)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemId());
			pstmt.setString(2, mv.getMemName());
			pstmt.setString(3, mv.getMemTel());
			pstmt.setString(4, mv.getMemAddr());
			
			cnt = pstmt.executeUpdate();

			// 밑에 이건 view가 하는 일
//			if(cnt > 0) {
//				System.out.println(memId + " 회원정보 추가 성공");
//			}else {
//				System.out.println(memId + " 회원정보 추가 실패");
//			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			
			JDBCUtil3.close(conn, stmt, pstmt, rs);
			
		}
				
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {

		boolean exist = false;
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = ""
					+ " select count(*) as cnt" 
					+ " from mymember"
					+ " where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			
			// 한줄 한줄씩 메서드에 접근 하는 메서드
			while(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt > 0) {
				exist = true;
			}
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return exist;

	}

	@Override
	public int updateMember(MemberVO mv) {
		
		int cnt = 0;
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = ""
					+ "	UPDATE MYMEMBER" 
					+ "	SET MEM_NAME = ?," 
					+ " MEM_TEL = ?," 
				    + " MEM_ADDR = ?" 
					+ " WHERE MEM_ID = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemName());
			pstmt.setString(2, mv.getMemTel());
			pstmt.setString(3, mv.getMemAddr());
			pstmt.setString(4, mv.getMemId());
			
			cnt = pstmt.executeUpdate();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		
		int cnt = 0;
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = ""
					+ "DELETE FROM MYMEMBER WHERE MEM_ID = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, memId);
			
			cnt = pstmt.executeUpdate();	
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public List<MemberVO> listMember() {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = ""
					+ "select * from mymember";
			
			stmt = conn.createStatement();
			// sql로 자료를 가져와서 rs에 넣음
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				MemberVO mv = new MemberVO();
				
				String memId = rs.getString("mem_id");
				mv.setMemId(memId);
				String memName = rs.getString("mem_name");
				mv.setMemName(memName);
				String memTel = rs.getString("mem_tel");
				mv.setMemTel(memTel);
				String memAddr = rs.getString("mem_addr");
				mv.setMemAddr(memAddr);
				
				memList.add(mv);
				
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return memList;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {

		// Collections.emptyList(); -> 비어있는 리스트를 넣어준다. null은 아닌거다.
		// 리시트 안에 값 자체는 변경하지 못한다.
		// ArrayList는 새로운 리스트 만든다.
		// Collections.emptyList();
		List<MemberVO> memList = new ArrayList();
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql =""
					+ " select * from mymember "
					+ " where 1=1 ";
			if(mv.getMemId() != null && !mv.getMemId().equals("")) {
				sql += " and mem_id = ? ";
			}
			if(mv.getMemName() != null && !mv.getMemName().equals("")) {
				sql += " and mem_name = ? ";
			}
			if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
				sql += " and mem_tel = ? ";
			}
			if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
				sql += " and mem_addr like '%' || ? || '%'";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			int index = 1;
			
			if(mv.getMemId() != null && !mv.getMemId().equals("")) {
				pstmt.setString(index++, mv.getMemId());
			}
			if(mv.getMemName() != null && !mv.getMemName().equals("")) {
				pstmt.setString(index++, mv.getMemName());
			}
			if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
				pstmt.setString(index++, mv.getMemTel());
			}
			if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
				pstmt.setString(index++, mv.getMemAddr());
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				MemberVO mv2 = new MemberVO();
				
				String memId = rs.getString("mem_id");
				mv2.setMemId(memId);
				String memName = rs.getString("mem_name");
				mv2.setMemName(memName);
				String memTel = rs.getString("mem_tel");
				mv2.setMemTel(memTel);
				String memAddr = rs.getString("mem_addr");
				mv2.setMemAddr(memAddr);
				
				memList.add(mv2);
				
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return memList;
	}

}
