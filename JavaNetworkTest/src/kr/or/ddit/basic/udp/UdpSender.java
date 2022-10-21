package kr.or.ddit.basic.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;


// 파일을 그냥 보낸다. 받는 사람이 자리에 없어도 일단 보낸다.
// 연결을 하지 않고 소켓을 가지고 데이터를 보내는데
// 데이타그램패킷의 역할은 보내고 싶은 바이트 배열을 데이타그램 패키지안에다가 담아서 소켓을 통해서
// 상대방으로 쑝~ 쏜다. 상대방이 받든 안받든 그냥 보낸다.
// 패킷은 결국 담는 그릇? 개념
public class UdpSender {

	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private byte[] msg; // 패킷 송수신을 위한 바이트배열 선언
	
	public UdpSender(int port) {
		try {
			// 메시지 수신을 위한 포트번호 설정
			// 포트번호를 설정하지 않으면 랜덤으로 뽑아낸다.
			ds = new DatagramSocket(port);
		}catch(SocketException ex) {
			ex.printStackTrace();
		}
	}
	
	public void start() throws IOException{
		
		while(true) {
			// 데이터를 수신하기 위한 패킷을 생성한다.
			// 바이트 배열 하나 생성
			// 데이터 파캣 페키지를 만드는데 파라미터로 바이트 배열, length를 넣는다.
			// 이 예제는 보내는 놈의 정보를 얻고 그 정보를 꺼내기 위한 정보만 넣으려고 하니깐 1byte짜리로 한거다.
			// 이 예제의 패킷은 수신용으로만 쓴다.
			msg = new byte[1];
			dp = new DatagramPacket(msg, msg.length);
			
			System.out.println("패킷 수신 대기 중...");
			
			// 패킷을 통해 데이터를 수신(receive)한다.
			// 상대방이 보내는걸 받는다.
			// 상대방이 쏠때까지 멈춰있다.
			ds.receive(dp);
			
			System.out.println("패킷 수신 완료.");
			
			// 수신한 패킷으로 부터 client의 IP주소 및 포트번호를 얻는다.
			// 정보를 얻어서 내가 그 사람의 데이터를 보낼것이다.
			InetAddress ipAddr = dp.getAddress();
			int port = dp.getPort();
			
			// 서버의 현재 시간을 시분초 형태([hh:mm:ss])로 클라이언트에 보내준다.
			SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
			String time = sdf.format(new Date());
			
			// 모든 데이터를 주고 받을때는 byte로 보내야 하기 때문에 byte데이터로 변환한다.
			// 시간문자열을 byte배열로 변환한다.
			msg = time.getBytes();
			
			// 패킷을 생성해서 Client에게 전송(send)한다.
			// 3,4는 상대방의 ip주소와 포트번호를 입력하는 것이다.
			dp = new DatagramPacket(msg, msg.length, ipAddr, port);
			ds.send(dp); // 전송 시작...
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		new UdpSender(8888).start();
	}
	
}
