package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.member.vo.MemberVO;

public class MyBatisTest {
	public static void main(String[] args) {
		
		// MyBatis를 이용하여 DB데이터를 처리하는 작업 순서
		// 1. MyBatis의 환경설정 파일을 읽어와 실행시킨다.
		
		SqlSessionFactory sqlSessionFactory = null;
		
		try {
			// 1-1. xml 설정파일 읽어오기
			// 설정파일의 인코딩 정보 설정하기 (인코딩 정보 설정 한글이 돌아갈 수 있기 때문에)
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("mybatis-config.xml");
			
			// 1-2. 위에서 읽어온 Reader객체를 이용하여
			// SqlSessionFactory객체 생성하기
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);
			
			rd.close();
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
//		///////////////////////////////////////////////////////////
//		
//		// 2. 실행할 SQL문에 맞는 쿼리문을 호출하여 원하는 작업을 수행한다.
//		
//		// 2-1. insert 연습
//		System.out.println("insert 작업 시작...");
//		
//		// 1) 저장할 데이터를 VO에 담는다.
//		MemberVO mv = new MemberVO();
//		mv.setMemId("d001");
//		mv.setMemName("강강찬");
//		mv.setMemTel("010-1111-1111");
//		mv.setMemAddr("경남 진주시");
//		
//		// 2) SqlSession 객체를 이용하여 해당 쿼리문을 실행한다.
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		
//		try {
//			
//			// memberTest -> memberTest.xml 에서 지정해논 namespace
//			// .실행할 해당 쿼리 id
//			int cnt = sqlSession.insert("memberTest.insertMember", mv);
//			
//			if(cnt > 0) {
//				System.out.println("insert작업 성공.");
//			}else {
//				System.out.println("insert작업 실패.");				
//			}
//			
//			// 명시적으로 커밋을 해줘야한다.
//			sqlSession.commit(); // 커밋
//			
//		}catch(PersistenceException ex) {
//			// 트랜잭션 관리
//			sqlSession.rollback(); // 롤백
//			throw new RuntimeException("데이터 등록중 예외발생", ex);
//		}finally {
//			// 커넥션 풀 방식이라 커넥션을 완전 종료하는게 아니라 다시 풀로 반납하는 것이다.
//			sqlSession.close(); // 세션 종료
//		}
		
		System.out.println("--------------------------------------");
		
		// 2-2. update 작업 연습
		System.out.println("update 작업 시작...");

		MemberVO mv = new MemberVO();
		mv.setMemId("d001");
		mv.setMemName("김서빈");
		mv.setMemTel("010-1111-1111");
		mv.setMemAddr("대전");
		
		// 2) SqlSession 객체를 이용하여 해당 쿼리문을 실행한다.
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			

			int cnt = sqlSession.update("memberTest.updateMember", mv);
			
			if(cnt > 0) {
				System.out.println("update작업 성공.");
			}else {
				System.out.println("update작업 실패.");				
			}
			
			// 명시적으로 커밋을 해줘야한다.
			sqlSession.commit(); // 커밋
			
		}catch(PersistenceException ex) {
			// 트랜잭션 관리
			sqlSession.rollback(); // 롤백
			throw new RuntimeException("데이터 수정중 예외발생", ex);
		}finally {
			// 커넥션 풀 방식이라 커넥션을 완전 종료하는게 아니라 다시 풀로 반납하는 것이다.
			sqlSession.close(); // 세션 종료
		}
		
		
		
		
	}
}
