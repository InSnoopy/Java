package kr.or.ddit.basic;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class MySessionBindingListener implements HttpSessionBindingListener{

	// 관심있는 객체가 세션에 바인딩 추가 됐다가 언바인딩 될때마다 관심있으면? 여기에 구현
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("[MySessionBindingListener]"
				+ "valueBound() 호출됨. => "
				+ this.hashCode() + "객체가"
				+ event.getName() + "으로 바인딩 됨.");
		
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("[MySessionBindingListener]"
				+ "valueUnbound() 호출됨. => "
				+ this.hashCode() + "객체가"
				+ event.getName() + "으로 언바인딩 됨.");

		
	}

}
