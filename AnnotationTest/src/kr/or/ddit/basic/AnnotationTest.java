package kr.or.ddit.basic;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationTest {
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		
		
		
		// 선언된 메서드 목록을 조회하고, 메서드를 실행해 보기
		// Service service = new Service();
		// 아래 코드 2줄 처럼 할 수도 있다.
		// 아래 코드 2줄 방법은 'reflection' 방법이다.
		Class<?> klass = Service.class;
		Service service = (Service) klass.newInstance();
		
		// 아래와 같은 코드
		// Class<?> klass = Service.class;
		// Method[] methodArr = klass.getDeclaredMethods();
		Method[] methodArr = Service.class.getDeclaredMethods();
		
		for(Method m : methodArr) {
			System.out.print(m.getName() + " => ");
			Annotation[] annos = m.getDeclaredAnnotations();
			for(Annotation anno : annos) {
				if(anno.annotationType().getSimpleName().equals("PrintAnnotation")) {
					PrintAnnotation printAnn = (PrintAnnotation) anno;
					for(int i=0; i<printAnn.count(); i++) {
						System.out.print(printAnn.value());
					}
				}
			}
			// invoke : 실행한다는 뜻
			m.invoke(service);
			
			System.out.println();
		}
		
	}
}
