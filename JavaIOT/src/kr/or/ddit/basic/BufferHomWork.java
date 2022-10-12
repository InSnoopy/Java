package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferHomWork {

	public static void main(String[] args) {
		
        File file = new File("d:/D_Other/Tulips.jpg");
        File copyFile = new File("d:/D_Other/복사할Tulips2.jpg");
		
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        
		try {
			
			// ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("d:/D_Other/memObj.bin")));
			long startTime = System.currentTimeMillis();

			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			
			fos = new FileOutputStream(copyFile);
			bos = new BufferedOutputStream(fos);
			
			int fileByte = 0;
			
			//(fileByte = bis.read()) != -1 => 더이상 읽을 게 없음
			//프로젝트 파일업로드 library => common-fileupload.jar / common-io.jar
			while((fileByte = bis.read()) != -1) {
				bos.write(fileByte);
			}
			
			bos.flush();
			
			long endTime = System.currentTimeMillis();
			System.out.println("걸린 시간 : " + (endTime - startTime));
			System.out.println("작업 끝...");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				bos.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	
}
