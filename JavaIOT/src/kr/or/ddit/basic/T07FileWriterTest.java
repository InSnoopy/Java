package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class T07FileWriterTest {
	public static void main(String[] args) {
		// 사용자가 입력한 내용을 그대로 파일로 저장하기
		
		// 바이트스트림 = 문자열스트림
		// Input = Reader
		// Output = Writer 
		// 콘솔(표준입력장치)과 연결된 입력용 문자 스트림객체 생성하기
		// InputStreamReader => 바이트 기반 스트림을 문자기반 스트림으로 변환해주는 보조 스트림이다.
		// 보조 스트림은 기본(메인)스트림이 필요하다.
		// System.in : 들어가면 InputStream 객체 타입이다.
		/*
		 InputStreamReader는 바이트(bytes) 스트림에서 문자 스트림으로 연결되는 다리입니다. 
		 01101010011101101000111 -> InputStreamReader -> asdfasdf
		 바이트를 읽고 지정된 문자 집합을 사용하여 문자로 디코딩합니다. 
		 사용하는 문자 집합은 이름으로 지정되거나 명시적으로 제공되거나 플랫폼의 기본 문자 집합이 허용될 수 있습니다.
		InputStreamReader의 read() 메서드 중 하나를 호출할 때마다 기본 바이트 입력 스트림에서 하나 이상의 바이트를 읽을 수 있습니다. 
		바이트를 문자로 효율적으로 변환하려면 기본 스트림에서 필요한 것보다 더 많은 바이트를 미리 읽을 수 있습니다. 현재 읽기 작업을 충족합니다.
		최고의 효율성을 위해 InputStreamReader를 aBufferedReader 내에 래핑하는 것을 고려하십시오. 예를 들어:
 		BufferedReader 객체명
   		= new BufferedReader(new InputStreamReader(System.in));
		 */
		InputStreamReader isr = new InputStreamReader(System.in);
		
		/*
		 문자 파일 작성을 위한 편의 클래스입니다. 이 클래스의 생성자는 기본 문자 인코딩과 기본 바이트 버퍼 크기가 허용된다고 가정합니다. 
		 이러한 값을 직접 지정하려면 FileOutputStream에서 OutputStreamWriter를 구성하십시오.
		파일을 사용할 수 있는지 여부는 기본 플랫폼에 따라 다릅니다. 
		특히 일부 플랫폼에서는 한 번에 하나의 FileWriter(또는 다른 파일 쓰기 개체)만 쓰기 위해 파일을 열 수 있습니다. 
		이러한 상황에서 관련된 파일이 이미 열려 있으면 이 클래스의 생성자가 실패합니다.
		FileWriter는 문자 스트림을 작성하기 위한 것입니다. 원시 바이트 스트림을 작성하려면 FileOutputStream 사용을 고려하십시오
		 */
		FileWriter fw = null; // 파일 출력용 문자 기반 스트림
		
		try {
			// 파일 출력을 위한 스트림 객체 생성하기
			fw = new FileWriter("d:/D_Other/testChar.txt");
			
			int data = 0;
			
			System.out.println("아무거나 입력하세요.");
			// 콘솔에서 입력할 때 입력의 끝을 나타내는 표시는 Ctrl + z키를 누르면 된다.
			
			while((data = isr.read()) != -1) {
				fw.write(data);
			}
			
			System.out.println("작업 끝...");
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				isr.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
