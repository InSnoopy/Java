package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class InOupHomeWork {
	public static void main(String[] args) throws IOException  {

        File file = new File("d:/D_Other/Tulips.jpg");
        File copyFile = new File("d:/D_Other/복사할Tulips.jpg");
		
        try {
        	
			long startTime = System.currentTimeMillis();
          
            FileInputStream fis = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(copyFile);
            
            int fileByte = 0; 
            // read()는 파일을 byte로 쪼갠 그 자체를 fileByte에 들어간다.
            // read()메소드가 1byte 읽고 fileByte안에 넣고 계속 반복
            while((fileByte = fis.read()) != -1) {
                fos.write(fileByte);
            }
            //자원사용종료
            fis.close();
            fos.close();
            
            long endTime = System.currentTimeMillis();
			System.out.println("걸린 시간 : " + (endTime - startTime));
			System.out.println("작업 끝...");
			
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}
	
	
}
