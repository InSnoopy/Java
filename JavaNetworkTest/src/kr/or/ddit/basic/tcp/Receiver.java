package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receiver extends Thread{

	private DataInputStream dis;
	
	public Receiver(Socket socket) {
		
		try {
			
			dis = new DataInputStream(socket.getInputStream());
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {

		while(dis != null) {
			try {
				// sender에서 엔터치기 전까지 기다리고있다.
				// dos.writeUTF가 도착하기 전까지 기다린다.
				System.out.println(dis.readUTF());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		super.run();
	}
	
}
