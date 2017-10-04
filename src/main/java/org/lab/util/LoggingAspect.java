package org.lab.util;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	//private static final Logger logger = LoggerFactory.getLogger(GameController.class);  // If you want the class object for GameController,
	//private final Logger logger = LoggerFactory.getLogger(this.getClass());	// If you want the class of the caller (a subclass of GameController),
	
	@Around("execution(* org.lab.controller.*.*(..))")
	public Object gameAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		
		logger.info("Before invoking method :: " + proceedingJoinPoint.toString());
		if (null != proceedingJoinPoint && proceedingJoinPoint.getArgs().length > 0) {
			Arrays.asList(proceedingJoinPoint.getArgs()).stream().forEach((s)-> logger.info(" arg: "+ s.toString()));
		}
		
		Object value = null;
		try {
			value = proceedingJoinPoint.proceed();
		} catch (Exception e) {
			//e.printStackTrace();
			//logger.error("Exception occured: "+ e); 	// It prints only error name and message not stack-trace
			logger.error("Exception occured: "+ e.getMessage(), e);  // print thrown/occurred exception stack-trace (not error) of called method (of controller and service layer) 
			throw e;												// throws error and handled by GlobalExceptionHandler
		}
		logger.info("After invoking method :: return value: "+value);
		return value;
	}
}

/*
2017-06-29 18:55:30.094  INFO 8472 --- [http-nio-8080-exec-1] org.lab.util.LoggingAspect  : Before invoking method :: execution(ResponseEntity org.lab.controller.GameController.getGameByAlias(List,List))
2017-06-29 18:55:30.102  INFO 8472 --- [http-nio-8080-exec-1] org.lab.util.LoggingAspect  :  arg: [sapsidi, Speed]
2017-06-29 18:55:30.102  INFO 8472 --- [http-nio-8080-exec-1] org.lab.util.LoggingAspect  :  arg: [racing]
2017-06-29 18:55:30.326  INFO 8472 --- [http-nio-8080-exec-1] org.lab.util.LoggingAspect  : After invoking method :: return value: <200 OK,[org.lab.dto.GamePlayerDTO@3239a8bc, org.lab.dto.GamePlayerDTO@694e1e2c, org.lab.dto.GamePlayerDTO@6b706407],{}>
*/


/*
 2017-06-29 19:16:27.186 ERROR 1328 --- [io-8080-exec-10] org.lab.util.LoggingAspect       : Exception occured: eeeeeeerrror message

java.lang.Exception: eeeeeeerrror message
	at org.lab.controller.GameController.getGameByAlias(GameController.java:237) ~[classes/:na]
	at org.lab.controller.GameController$$FastClassBySpringCGLIB$$b8ea0335.invoke(<generated>) ~[classes/:na]
	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204) ~[spring-core-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:738) ~[spring-aop-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157) [spring-aop-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85) ~[spring-aop-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.lab.util.LoggingAspect.gameAroundAdvice(LoggingAspect.java:31) ~[classes/:na]
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:1.8.0_65]
	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source) ~[na:1.8.0_65]
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source) ~[na:1.8.0_65]
	at java.lang.reflect.Method.invoke(Unknown Source) ~[na:1.8.0_65]
	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629) [spring-aop-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618) [spring-aop-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70) [spring-aop-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) [spring-aop-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92) [spring-aop-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) [spring-aop-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:673) [spring-aop-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.lab.controller.GameController$$EnhancerBySpringCGLIB$$bee85177.getGameByAlias(<generated>) [classes/:na]
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:1.8.0_65]
	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source) ~[na:1.8.0_65]
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source) ~[na:1.8.0_65]
	at java.lang.reflect.Method.invoke(Unknown Source) ~[na:1.8.0_65]
	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205) [spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:133) [spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:97) [spring-webmvc-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827) [spring-webmvc-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738) [spring-webmvc-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85) [spring-webmvc-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:967) [spring-webmvc-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:901) [spring-webmvc-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970) [spring-webmvc-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:861) [spring-webmvc-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:635) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846) [spring-webmvc-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52) [tomcat-embed-websocket-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99) [spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) [spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:105) [spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) [spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81) [spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) [spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197) [spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) [spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:478) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:80) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:799) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:861) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1455) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source) [na:1.8.0_65]
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source) [na:1.8.0_65]
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at java.lang.Thread.run(Unknown Source) [na:1.8.0_65]

2017-06-29 19:16:27.190  WARN 1328 --- [io-8080-exec-10] .m.m.a.ExceptionHandlerExceptionResolver : Resolved exception caused by Handler execution: java.lang.Exception: eeeeeeerrr
*/
