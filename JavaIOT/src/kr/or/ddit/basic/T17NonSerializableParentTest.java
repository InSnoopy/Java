package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * 부모클래스가 Serializable 인터페이스를 구현하고 있지 않을 경우, 부모 객체의 필드값 처리 방법
 * 
 * 	1. 부모클래스가 Serializable 인터페이스를 구현하도록 한다.
 *	-. 부모클래스가 Serializable 인터페이스를 구현하면 모든 자식들이 Serializable이 되기 때문에.. 내가 원하는 자식만 하고 싶다.
 *
 * 	2. 자식클래스에 writeObject()와 readObject() 메서드를 이용하여 부모객체의 필드값을 처리
 * 
 */

public class T17NonSerializableParentTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("d:/D_Other/nonSerializableTest.bin")));
		
		Child child = new Child();
		child.setParentName("부모");
		child.setChildName("자식");
		
		oos.writeObject(child); // 직렬화
		oos.close();
		
		/////////////////////////////////////////////////
		
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("d:/D_Other/nonSerializableTest.bin")));
		
		Child child2 = (Child) ois.readObject(); // 역직렬화
		System.out.println("parentName : " + child2.getParentName());
		System.out.println("childName : " + child2.getChildName());
		
		ois.close();
		
	}
}

class Parent{
	
	private String parentName;

	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}

class Child extends Parent implements Serializable{
	
	private String childName;

	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	
	/*
	 * 직렬화 될 때 자동으로 호출됨.
	 * (접근제한자가 private이 아니면 자동호출되지 않음.)
	 */
	private void writeObject(ObjectOutputStream oos) throws IOException{
		oos.writeUTF(getParentName()); // 내가 수동으로 write를 만들었다.
		oos.defaultWriteObject(); // 기본 write 기능
	}
	
	/*
	 * 역직렬화가 될 때 자동으로 호출됨.
	 * (접근제한자가 private이 아니면 자동 호출되지 않음.)
	 */
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
		setParentName(ois.readUTF());
		ois.defaultReadObject(); // readObject 메소드에 기본적으로 ois.defaultReadObject()로 되어있음.
	}
	
}






















