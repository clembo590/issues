package bootiful.aot.brap.hints;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Slf4j
@Configuration
class BeanPostProcessorHintsConfiguration {

	@Bean
	LoggedBeanPostProcessor loggingBeanPostProcessor() {
		return new LoggedBeanPostProcessor();
	}

	@Bean
	ApplicationListener<ApplicationReadyEvent> bppApplicationListener(CartService cartService) {
		return e -> cartService.enroll();
	}

}

@Logged
@Service
class CartService {

	public void enroll() {
		System.out.println("enrolling..");
	}

}

@Slf4j
class LoggedBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {

	@Override
	public Class<?> determineBeanType(@NonNull Class<?> beanClass, @NonNull String beanName) throws BeansException {
		if (beanClassMatches(beanClass)) {
			return createProxy(null, beanClass).getProxyClass(beanClass.getClassLoader());
		}
		return beanClass;
	}

	@SneakyThrows
	private static Object intercept(Object object, MethodInvocation invocation) {
		var method = invocation.getMethod();
		var methodName = method.getName();
		log.info("before [{}}]", methodName);
		var result = method.invoke(object, invocation.getArguments());
		log.info("after [{}}]", methodName);
		return result;
	}

	@SneakyThrows
	private static ProxyFactory createProxy(Object o, Class<?> targetClass) {
		var pfb = new ProxyFactory();
		pfb.setProxyTargetClass(true);
		pfb.setTargetClass(targetClass);
		pfb.setInterfaces(targetClass.getInterfaces());
		pfb.addAdvice((MethodInterceptor) invocation -> intercept(o, invocation));
		if (null != o) {
			pfb.setTarget(o);
		}
		return pfb;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (!beanClassMatches(bean.getClass())) {
			return SmartInstantiationAwareBeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
		}
		return createProxy(bean, bean.getClass()).getProxy(bean.getClass().getClassLoader());
	}

	private boolean beanClassMatches(Class<?> beanClazz) {
		return beanClazz != null && beanClazz.getAnnotation(Logged.class) != null;
	}

}

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface Logged {

}
