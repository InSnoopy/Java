package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MultiChatServer {
	
	// 대화명, 클라이언트의 Socket을 저장하기 위한 Map변수 선언
	private Map<String, Socket> clients;
	
	// 생성자
	public MultiChatServer() {
		// 동기화 처리가 가능하도록 Map객체 생성
		clients = Collections.synchronizedMap(new HashMap<>());
	}
	
	// 서버 시작
	public void serverStart() {
		
		Socket socket = null;
		
		try {
			
			// try 안에서 선언하게 되면 자동으로 close() 된다. 그냥 문법이다.
			ServerSocket serverSocket = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다.");
			
			while(true) {
				
				// 클라이언트의 접속을 대기한다.
				socket = serverSocket.accept();
				
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "] 에서 접속하였습니다.");
				// 메시지 전송 처리를 하는 스레드 생성 및 실행
				ServerReceiver receiver = new ServerReceiver(socket);
				receiver.start();
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 대화방 즉, Map에 저장된 전체 유저에게 안내메시지를 전송하는 메서드 
	 * @param msg
	 */
	public void sendMessage(String msg) {
		// Map에 저장된 사용자의 대화명 리스트 추출(key값 구하기)
		Iterator<String> it = clients.keySet().iterator();
		while(it.hasNext()) {
			try {
				String name = it.next(); // 대화명
				
				// 대화명에 해당하는 Socket객체에서 OutputStream꺼내기
				DataOutputStream dos = new DataOutputStream(clients.get(name).getOutputStream());
				dos.writeUTF(msg); // 메시지 전송하기
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}	
	};
	
	/**
	 * 대화방 즉, Map에 저장된 전체 유저에게 대화메시지를 전송하는 메서드 
	 * @param msg
	 * @param from 
	 */
	public void sendMessage(String msg, String from) {
		sendMessage("[" + from + "]" + msg);
	}
	
	
	/**
	 * target에게 귓속말을 보낸다.
	 * @param msg
	 * @param from
	 * @param target
	 */
	public void sendMessage(String msg, String from, String target) {
		
			try {
				DataOutputStream dos = 
						new DataOutputStream(clients.get(target).getOutputStream());
				dos.writeUTF("[" + from + "님의 귓속말 ]" + msg); // 메시지 전송하기
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}
	}
	
	// 서버에서 클라이언트로 메시지를 전송할 Thread클래스를 Inner클래스로
	//  정의하면 부모(Outer)클래스의 멤버들을 직접 사용할 수 있다.
	class ServerReceiver extends Thread{
		
		private Socket socket;
		private DataInputStream dis;
		private String name;
		
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			
			try {
				// 수신용
				dis = new DataInputStream(socket.getInputStream());
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			try {
				// 서버에서 클라이언트가 보내는 최초의 메시지 즉, 대화명을 수신해야 한다.
				name = dis.readUTF();
				
				// 대화명을 받아서 다른 모든 클라이언트에게 대화방 참여 메시지를 보낸다.
				sendMessage("#" + name + "님이 입장했습니다.");
				
				// 대화명과 소켓객체를 Map에 저장한다.
				clients.put(name, socket);
				
				System.out.println("현재 서버 접속자 수는 " + clients.size() + "명 입니다.");
				
				// 이 이후의 메시지 처리는 반복문으로 처리한다.
				// 한 클라이언트가 보낸 메시지를 다른 모든 클라이언트들에게 보내준다.
				
				while(dis != null) {
					sendMessage(dis.readUTF(), name);
				}
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}finally {
				// 이 finally 영역이 실행된다는 것은 클라이언트의 접속이 종료되었다는 의미이다.
				sendMessage(name + "님이 나갔0.습니다.");
				
				// Map에서 해당 대화명을 삭제한다.
				clients.remove(name);
				
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "] 에서 접속을 종료하였습니다.");
				System.out.println("현재 서버 접속자 수는 " + clients.size() + "명 입니다.");
				
				
			}
		}
		
	}
	
	public static void main(String[] args) {
		new MultiChatClient().clientStart();
	}
	
	
}
