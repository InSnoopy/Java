package kr.or.ddit.basic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @inteface : Annotation 타입으로 선언한다는 뜻 
// Annotation : 주석이란 뜻
// 주석이랑 Annotation의 차이점 : 컴파일러나 또는 다른 프로그램이 사용할 수 있도록 유용한 정보를 정해진 표기법에 따라 표기하기 위한 일종의 메타데이터

/*
	Annotation에 대하여...
 
 	프로그램 소스코드 안에 다른 프로그램을 위한 정보를 미리 약속된 형식으로
	포함시킨 것(JDK1.5 부터 적용)
 
 	주석처럼 프로그램에 영향을 미치지 않으면서도 다른 프로그램에게 유용한 정보를 제공함.
 
	종류      1. 표준 애너테이션
	      2. 메타 애너테이션 (애너테이션을 위한 애너테이션, 즉 애너테이션을 정의할 때 사용하는 애너테이션)
	
	애너테이션 요소의 규칙
	
	1. 요소의 타입은 기본형, String, enum, annotation, class만 허용함.
	2. ()안에 매개변수를 선언할 수 없다.
	3. 예외를 선언할 수 없다.
	4. 요소의 타입에 타입 파라미터(제너릭타입글자)를 사용할 수 없다.
 
 */

// Target이라는 Annotation은 어디에다가 Annotation을 붙일지 지정함
// ElementType은 열거 타입이다.
//@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER}) // 여러개로 적용 하기
@Target(ElementType.METHOD) // 적용 대상 지정함.
// source / class / runtime 해당 실행되는 곳에서만 유지가 된다.
// RetentionPolicy를 안붙이게 된다면 default값인 'class'로 적용된다.
@Retention(RetentionPolicy.RUNTIME) // 유지기간 지정함.
public @interface PrintAnnotation {
	// Annotation의 요소를 선언하는 문법
	// int id = 100; // 상수선언 가능 . static final int id = 100;
	// 타입 요소의 이름() 
	String value() default "-"; // default값은 생략 가능하다.
	int count() default 20;
}
