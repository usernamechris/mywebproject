package org.mycompany.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component // spring빈으로 인식되기 위해 설정
@Aspect // aop기능을 이용하는 클래스 선언
public class SampleAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);
	
	@Before("execution(* org.mycompany.service.MessageService*.*(..))") //pointcut 지정.
	public void startLog(JoinPoint jp) {
		logger.info("************************************");
		logger.info("parametas: " + Arrays.toString(jp.getArgs())); // 전달되는 모든 파라미터들을 object배열로 가져온다.
		logger.info("advice type: " + jp.getKind()); // 해당 advice의 타입을 알아낸다.
		logger.info("method: " + jp.getSignature().toString()); // 실행하는 대상객체의 메소드에 대한 정보를 알아낸다.
		logger.info("target: " + jp.getTarget().getClass()); // target객체를 알아낼때 사용
		logger.info("advice: " + jp.getThis().getClass()); // advice를 행하는 객체를 알아낼때 사용
		logger.info("************************************");
	}
	
	@Around("execution(* org.mycompany.service.MessageService*.*(..))") //pointcut 지정.
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("======================================");
		long startTime = System.currentTimeMillis();
		logger.info(Arrays.toString(pjp.getArgs()));
		
		Object result = pjp.proceed(); // 실제 메소드 호출 
		
		long endTime = System.currentTimeMillis();
		
		logger.info(pjp.getSignature().getName() + ": " + (endTime - startTime));

		logger.info("======================================");

		return result;
	}

}
