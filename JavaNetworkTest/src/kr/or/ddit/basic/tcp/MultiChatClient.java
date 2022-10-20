package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MultiChatClient {

	// 클라이언트 시작
	public void clientStart() {
		
		Socket socket = null;
		
		try {
			socket = new Socket("192.168.35.90", 7777);
			
			System.out.println("서버에 연결되었습니다.");
			
			// 송신용 스레드 생성
			ClientSender sender = new ClientSender(socket);
			sender.start();
			
			ClientReceiver receiver = new ClientReceiver(socket);
			receiver.start();
			
			
			// 수신용 스레드 생성
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
	}
	
	// 메시지를 전송하는 스레드 클래스
	class ClientSender extends Thread{
		
		private DataOutputStream dos;
		private Scanner scan = new Scanner(System.in);
		
		public ClientSender(Socket socket) {
			try {
				dos = new DataOutputStream(socket.getOutputStream());
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			try {
				if(dos != null) {
					// 시작하자마자 자신의 대화명을 서버로 전송
					System.out.println("대화명 >> ");
					dos.writeUTF(scan.nextLine());
				}
				
				while(dos != null) {
					// 키보드로 입력받은 메시지를 서버로 전송
					dos.writeUTF(scan.nextLine());
				}
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	// 메시지를 받는 스레드 클래스
	class ClientReceiver extends Thread{
		
		private DataInputStream dis;

		public ClientReceiver(Socket socket) {
			try {
				dis = new DataInputStream(socket.getInputStream());
			}catch(IOException ex) {
				ex.printStackTrace();
			}
			
		}
		
		@Override
		public void run() {
			while(dis != null) {
				// 서버로부터 수신한 메시지 출력하기
				try {
					System.out.println(dis.readUTF());
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		
		
	}
	
	
}
