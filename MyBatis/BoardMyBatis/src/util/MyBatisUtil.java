package util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * SqlSession 객체를 제공하는 팩토리 클래스
 */
public class MyBatisUtil {
	
	private static SqlSessionFactory sessionFactory;
	
	
	// SqlSessionFactory 팩토리
	static {
		
		try {
			// 1-1. xml 설정파일 읽어오기
			// 설정파일의 인코딩 정보 설정하기 (인코딩 정보 설정 한글이 돌아갈 수 있기 때문에)
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("mybatis-config.xml");
			
			// 1-2. 위에서 읽어온 Reader객체를 이용하여
			// SqlSessionFactory객체 생성하기
			sessionFactory = new SqlSessionFactoryBuilder().build(rd);
			
			rd.close();
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * SqlSesstion 객체를 제공하는 팩토리 메소드
	 * @return SqlSession 객체
	 */
	public static SqlSession getSqlSession() {
		return sessionFactory.openSession();
	}
	
	/**
	 * SqlSesstion 객체를 제공하는 팩토리 메소드
	 * @param autoCommit 오토커밋 여부
	 * @return SqlSession 객체
	 */
	public static SqlSession getSqlSession(boolean autoCommit) {
		return sessionFactory.openSession(autoCommit);
	}
	

}