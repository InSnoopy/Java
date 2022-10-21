package kr.or.ddit.basic.udp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpFileReceiver {

	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private byte[] buffer;
	
	public UdpFileReceiver(int port) {
		try {
			// 데이터 수신을 위한 포트번호 설정
			ds = new DatagramSocket(port);
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 데이터 수신하기
	 * @return 바이트 배열
	 * @throws IOException
	 */
	public byte[] receiveData() throws IOException {
		buffer = new byte[1000];
		dp = new DatagramPacket(buffer, buffer.length);
		ds.receive(dp);
		
		return dp.getData();
	}
	
	public void start() throws Exception{
		
		long fileSize = 0;
		long totalReadBytes = 0;
		
		int readBytes = 0;
		
		System.out.println("파일 수신 대기 중...");
		
		// receiveData() 상대방이 보내면 바이트 기반으로 받으면 그걸 String으로 만든다.
		// 처음에 상대방은 start를 보냈다.
		// receiveData() 받을때까지 멈춰있다.
		String str = new String(receiveData()).trim();
		
		if(str.equals("start")) {
			// 전송 파일명 받기
			str = new String(receiveData()).trim();
			
			FileOutputStream fos = new FileOutputStream("d:/D_Other" + str);
			 
			// 전송 파일 크기 (bytes) 받기
			str = new String(receiveData()).trim();
			fileSize = Long.parseLong(str);
			
			long startTime = System.currentTimeMillis();
			
			while(true) {
				byte[] data = receiveData();
				// getLength하면 실제 패키지 안에 담겨진 크기를 얻어올 수 있다.
				// 1300byte가 올때 
				// 처음에는 1천
				// 두번째에는 300byte로 온다.
				// 두번쨰는 getLength()에서 300byte로 데이터 크기로 읽어 올 수 있다.
				readBytes = dp.getLength(); // 받은 데이터 크기
				fos.write(data, 0, readBytes);
				
				totalReadBytes += readBytes;
				System.out.println("진행 상태 : " 
						+ totalReadBytes 
						+ "/" + fileSize 
						+ " Byte(s) ("
						+ (totalReadBytes*100 / fileSize)
						+ " %)");
				// 읽은 바이트수가 전체 파일 사이즈와 비교해서 같거나 클 때 
				// 다 읽었다는 뜻
				if(totalReadBytes >= fileSize) {
					break;
				}
				
			}
			
			long endTime = System.currentTimeMillis();
			long diffTime = endTime - startTime;
			double transferSpped = fileSize / diffTime;
			
			System.out.println("걸린 시간 : " + diffTime + " (ms)");
			System.out.println("평균 전송속도 : " + transferSpped + " Bytes/ms");
			System.out.println("수신 완료...");
			
			fos.close();
			ds.close();
			
			
		}
		
	
		
		
		
		
	}
	
	public static void main(String[] args) throws Exception {
		
		new UdpFileReceiver(8888).start();
		
	}
	
}
