package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService{

	private static IMemberService memService;
	private IMemberDao memDao;
	
	private MemberServiceImpl() {
		memDao = MemberDaoImpl.getInstance();
	}
	
	public static IMemberService getInstance() {
		if(memService==null) {
			memService = new MemberServiceImpl();
		}
		return memService;
	}

	@Override
	public int registMember(MemberVO mv) {
		int cnt = memDao.insertMember(mv);
		
		// 데이터 관련된건 memDao에 다 시키고 그 외에 
		// 비즈니스 로직은 Service가 한다.
		
		//if(cnt > 0) {
		//	//메일발송 기능 호출...
		//}
		
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {
		boolean exist = memDao.checkMember(memId);
		return exist;
	}

	@Override
	public int modifyeMember(MemberVO mv) {
		int cnt = memDao.updateMember(mv);
		return cnt;
	}

	@Override
	public int removeMember(String memId) {
		int cnt = memDao.deleteMember(memId);
		return cnt;
	}

	@Override
	public List<MemberVO> listMember() {
		List<MemberVO> memList = memDao.listMember(); 
		return memList;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		List<MemberVO> memList = memDao.searchMember(mv); 
		return memList;
	}

	
	
}
