package common.spring.test;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

class Message {
	
	String text;
	
	private Message(String text) { // 외부에서 생성자를 통해 오브젝트 만들 수 없다
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public static Message newMessage(String text) {
		return new Message(text);
	}
}

class MessageFactoryBean implements FactoryBean<Message> {
	
	String text;
	
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public Message getObject() throws Exception {
		return Message.newMessage(this.text);
	}

	@Override
	public Class<? extends Message> getObjectType() {
		return Message.class;
	}

	@Override
	public boolean isSingleton() {
		return false; // 매번 요청할 때마다 새로운 오브젝트를 만든다는 뜻. 이 팩토리빈의 동작방식이고, 스프링에 등록되면 싱글톤으로 동작
	}
	
}

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration // 설정파일 미지정시 클래스이름 + -context.xml이 디폴트로 사용
public class FactoryBeanTest {

	@Autowired
	ApplicationContext context;
	
	@Test
	public void getMessageFromFactoryBean() {
		Object message = context.getBean("message");
		assertTrue(message instanceof Message);
		assertThat(((Message)message).getText(), is("Factory Bean"));
	}
	
	@Test
	public void getFactoryBean() {
		Object message = context.getBean("&message"); // 팩토리 빈 자체
		assertTrue(message instanceof MessageFactoryBean);
	}
	
	@Test
	public void proxyFactoryBean() {
		ProxyFactoryBean pfBean = new ProxyFactoryBean(); // 스피링에서 제공
		pfBean.setTarget(new HelloTarget());
		pfBean.addAdvice(new UppercaseAdvice());
		
		Hello proxiedHello = (Hello) pfBean.getObject();
		
		assertThat(proxiedHello.sayHello("Han"), is("HELLO HAN"));
		assertThat(proxiedHello.sayHi("Han"), is("HI HAN"));
		assertThat(proxiedHello.sayThankYou("Han"), is("THANK YOU HAN"));

	}
	
	@Test
	public void pointcutAdvisor() {
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		pfBean.setTarget(new HelloTarget());
		
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedName("sayH*");
		
		pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));
		
		Hello proxiedHello = (Hello) pfBean.getObject();
		
		assertThat(proxiedHello.sayHello("Han"), is("HELLO HAN"));
		assertThat(proxiedHello.sayHi("Han"), is("HI HAN"));
		assertThat(proxiedHello.sayThankYou("Han"), is("Thank You Han")); // 포인터컷의 선정 조건이 아니므로
		
	}
	
	
	static class UppercaseAdvice implements MethodInterceptor { // 스프링에서 제공

		@Override
		public Object invoke(MethodInvocation invocation) throws Throwable {

			String ret = (String) invocation.proceed();
			return ret.toUpperCase();
		}
	}
	
	static interface Hello {
		String sayHello(String name);
		String sayHi(String name);
		String sayThankYou(String name);
	}
	
	static class HelloTarget implements Hello {

		@Override
		public String sayHello(String name) {
			return "Hello " + name;
		}

		@Override
		public String sayHi(String name) {
			return "Hi " + name;
		}

		@Override
		public String sayThankYou(String name) {
			return "Thank You " + name;
		}
		
	}

}
