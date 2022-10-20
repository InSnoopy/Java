package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpSocketServerTest {
	public static void main(String[] args) throws IOException {
		
		// TCP 소켓 통신을 하기 위해 ServerSocket 객체 생성
		// 7777 -> 포트번호
		// ip주소는 호스트(게스트)를 구분하고
		// 컴퓨터 안에는 여러가지 프로그램이 있다.
		// 이 안에서 프로그램중에 카톡 메세지를 보내고 싶다면
		// 카톡 메세지를 구분하기 위한게 포트
		// 프로세스를 구분해주는게 포트번호
		ServerSocket server = new ServerSocket(7777);
		System.out.println("서버가 접속을 기다립니다...");
		
		// accept()메서드는 Client에서 연결 요청이 올 때까지 계속 기다린다.
		// 연결 요청이 오면 Socket객체를 생성해서 Client의 Socket과 연결됨.
		// 클라이언트가 소켓을 만들면 연결 된다.
		// 이 socket으로 클라이언트와 서버가 연결된다.
		Socket socket = server.accept();

		// ---------------------------------------------------
		// 이 이후는 클라이언트와 연결된 후의 작업을 진행하면 된다.
		
		System.out.println("접속한 클라이언트 정보");
		System.out.println("주소 : " + socket.getInetAddress());
		
		// Client에 메시지 보내기
		
		// OutputStream 객체를 구성하여 전송한다.
		// 접속한 Socket의 getOutputStream() 이용하여 구한다.
		OutputStream out = socket.getOutputStream();
		
		// 보조스트림
		DataOutputStream dos = new DataOutputStream(out);
		
		dos.writeUTF("어서 오세요..."); // 메시지 보내기
		
		System.out.println("메시지를 보냈습니다.");
		
		dos.close();
		
	}
}
