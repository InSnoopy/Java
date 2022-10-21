package kr.or.ddit.basic.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpReceiver {

	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private byte[] msg;
	
	public UdpReceiver() {
		try {
			// 상대방한테 시간 데이터를 받으려고 100byte 여유있는 크기로 할당함
			msg = new byte[100];
			
			// 나중에 포트번호를 알아낼 수 있다. 할당해도 상관은 없다.
			// 소켓 객체 생성(포트번호를 명시하지 않으면 사용가능한 포트번호중에서 임의의 번호로 할당됨.)
			ds = new DatagramSocket();
		}catch(SocketException ex) {
			ex.printStackTrace();
		}
	}
	
	public void start() throws IOException{
		
		// ip주소를 가져와서 만든다. 파라미터를 넣기 위해서
		// 상대방 ip주소이다.
		// 처음 보내는게 의미가 없다. 의미없는 패킷을 받아서 보낸 놈의
		// ip랑 포트번호를 얻기 위함이기 때문이다.
		InetAddress serverAddr = InetAddress.getByName("192.168.35.90");
		dp = new DatagramPacket(msg, 1, serverAddr, 8888);
		ds.send(dp); // 전송
		
		// 상대방이 현재 시간을 보낼 때까지 receive(기다린다);
		dp = new DatagramPacket(msg, msg.length);
		// receive 하고 있으면 받고 안하면 안받는거다.
		ds.receive(dp); // 패킷 수신...
		
		// 담겨진 바이트 개열의 데이터를 getData()로 꺼낸다.
		// 상대방은 문자열을 -> 바이트로 변한해서 보냈고
		// 받는이는 변환된 바이트를 다시 문자열로 변환해서 읽는다.
		System.out.println("현재 서버 시간 => " + new String(dp.getData()));
		
		ds.close(); // 소캣 종료.
		
	}
	
	public static void main(String[] args) throws IOException {
		new UdpReceiver().start();
	}
	
}
