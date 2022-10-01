package kr.or.ddit.basic;

import java.util.ArrayList;

public enum Planet {

	수성(2439), 
	금성(6052), 
	지구(6371), 
	화성(3390), 
	목성(69911), 
	토성(58232), 
	천왕성(25362), 
	해왕성(24622);
	
	private int radius;
	
	Planet(int radius){
		
		this.radius = radius;
	}
	
	// 값을 반환하는 메서드(Setter)
	public int getRadius() {
		return this.radius;
	}
	
	public double PlanetArea() {
		return  4*Math.PI*radius*radius;
	}
	
	
	public static void main(String[] args) {
		
		Planet[] x = Planet.values();
		for(Planet p : x) {
			System.out.println(p.name() + "의 면적 : " + p.PlanetArea());
		}
		

	}
	
}



