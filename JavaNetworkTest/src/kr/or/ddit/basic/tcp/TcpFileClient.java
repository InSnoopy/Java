package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TcpFileClient {
	// 클라이언트는 서버에 접속하여 서버가 보내주는 파일을 저장한다.
	private Socket socket;
	private FileOutputStream fos;
	private DataOutputStream dos;
	private DataInputStream dis;
	
	public void clientStart() {
		
		File file = new File("d:/D_Other/br5.jpg");
		
		try {
			
			socket = new Socket("192.168.35.90", 7777);
			// 소켓접속이 성공하면 받고 싶은 파일명을 보낸다.
			dos = new DataOutputStream(socket.getOutputStream());
			// file 이름을 보낸다. (존재하는지 체크하기 위해서)
			dos.writeUTF(file.getName());
			
			dis = new DataInputStream(socket.getInputStream());
			
			// 답변 받은 메시지를 담아서 "OK"라면 파일이 있다라는 뜻
			String resultMsg = dis.readUTF();
			if(resultMsg.equals("OK")) {
				fos = new FileOutputStream(file);
				
				// 파일 읽어 온다.
				BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				
				int data = 0;
				while((data = bis.read()) != -1) {
					bos.write(data);
				}
				
				bis.close();
				bos.close();
				System.out.println("파일 다운로드 완료...");
			}
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		
	}
	
	
	public static void main(String[] args) {
		new TcpFileClient().clientStart();
		
	}

	
}
