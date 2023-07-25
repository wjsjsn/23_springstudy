package ex03_DI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestMain_DI {
	public static void main(String[] args) {
		// 스프링 컨테이너는 설정 정보를 이용해야함
		ApplicationContext context = 
				new GenericXmlApplicationContext("ex03_DI/config.xml");

		MyProcess p1 = (MyProcess) context.getBean("m1");
		p1.prn();
		System.out.println("============");

		MyProcess p2 = (MyProcess) context.getBean("m2");
		p2.prn();
		System.out.println("============");

		MyProcess p3 = (MyProcess) context.getBean("m3");
		p3.prn();
		System.out.println("============");
	}
}
