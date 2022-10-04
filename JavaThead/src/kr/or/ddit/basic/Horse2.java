package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Horse2 extends Thread{

	private String horseName;
	private int rank;
	public List<String> stage = new ArrayList();;
	
	public Horse2() {
	}
	public Horse2(String horseName) {
		this.horseName = horseName;
	}



	public String getHorseName() {
		return horseName;
	}
	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public List<String> getStage() {
		return stage;
	}
	public void setStage(List<String> stage) {
		this.stage = stage;
	}


	@Override
	public void run() {
		
		Random random = new Random();
	
		stage.add(horseName + " 말 : ");

		
		for(int i=1; i<10; i++) {
			stage.add("-");			
		}	

		for(int i=1; i<10; i++) {
			
			int x = random.nextInt(1000)+1;
			
			try {
				Thread.sleep(x);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			stage.set(i, ">");				
			if(stage.get(i-1).equals(">")) {
				stage.set(i-1,"-");
			}
			
			for(int j=0; j<10; j++) {
				System.out.print(stage.get(j));	
				
			}
			System.out.println();
			
		}
		
		
		
		rank = HorseTest.horseRank++;
	}
	
}