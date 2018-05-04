package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class TestBase {
	private String[] conf = new String[] { "conf/spring-mybatis.xml", "conf/springmvc.xml" };

	public ApplicationContext getContext() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		return ac;
	}
}
