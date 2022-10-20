package kr.or.ddit.basic.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpChatServer {
	public static void main(String[] args) {
		
		ServerSocket server = null;
		Socket socket = null;
		
		try {
			
			server = new ServerSocket(7777);
			System.out.println("서버 준비 완료...");
			socket = server.accept();
			System.out.println("접속되었습니다....");
			
			Sender sender = new Sender(socket);
			sender.start();
			
			Receiver receiver = new Receiver(socket);
			receiver.start();		
			
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		
	}
}
