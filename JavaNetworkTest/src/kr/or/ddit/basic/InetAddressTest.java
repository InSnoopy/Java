package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
	public static void main(String[] args) throws UnknownHostException {
		
		// InetAddress 클래스 => IP주소를 다루기 위한 클래스
		
		// getByName() 메서드는 www.naver.com 또는 SEM-PC등과 같은 
		// 머신이름이나 IP주소를 통해 유효한 InetAddress객체를 제공한다.
		// 주소 자체의 유효성 체크가 이루어짐.
		// 만약에 주소가 유효하지 않는 주소라면 UnknownHostException 예외가 발생한다.
		
		// 네이버의 IP정보 가져오기
		// www.naver.com : 도메인 주소
		// 223.130.200.107 : 도메인 주소가 가지고 있는 실제로 된 주소
		// 원래 www.naver.com의 주소는 223.130.200.107로 되어 있다. 
		// Host Name에 223.130.200.107를 넣어도 Address는 223.130.200.107 출력
		// Host Name에 www.naver.com를 넣어도 Address는 223.130.200.107 출력
		InetAddress naverIp = InetAddress.getByName("www.naver.com");
		System.out.println("Host Name => " + naverIp.getHostName());
		System.out.println("Host Address => " + naverIp.getHostAddress());
		System.out.println();
		
		// 자기 자신 컴퓨터의 IP정보 가져오기
		// 로컬도 위에 내용과 같다.
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("내 컴퓨터의 Host Name => " + localIp.getHostName());
		System.out.println("내 컴퓨터의 Host Address => " + localIp.getHostAddress());
		System.out.println();
		
		// IP 주소가 여러개인 호스트의 정보 가져오기
		// 네이버같은 경우 ip주소가 여러개 일 수도 있다.
		InetAddress[] naverIps = InetAddress.getAllByName("www.naver.com");
		for(InetAddress iAddr : naverIps) {
			System.out.println(iAddr.toString());
		}
	
	}
	
	
}
