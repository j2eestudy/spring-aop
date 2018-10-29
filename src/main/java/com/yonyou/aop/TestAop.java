package com.yonyou.aop;

import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

@Component
@Aspect
public class TestAop {

    private static final Logger logger = LoggerFactory.getLogger(TestAop.class);

    private static final Gson gson = new Gson();

    @Pointcut("execution (public * com.yonyou.controller..*(..))")
    public void pointCutMethod(){};

    @Around("pointCutMethod()")
    public Object aroundMethod(ProceedingJoinPoint pjd) {
        Object result = null;
        String methodName = pjd.getSignature().getName();
        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        //请求url
        String url = request.getRequestURI();
        try {
            System.out.println("The method " + methodName + " begins with " + Arrays.asList(pjd.getArgs()));
            result =  pjd.proceed();
            System.out.println("The method " + methodName + " ends with " + result);
        } catch (Throwable throwable) {
            System.out.println(throwable.toString());
        }

        System.out.println("拦截了员工的操作,url是:"+url);
        System.out.println("返回的结果是"+result);



        //参数列表
        String param = null;
        Map<String, String[]> parameterMap = request.getParameterMap();
        if(parameterMap!=null&& parameterMap.size()>0 ){
            param = gson.toJson(parameterMap);
            System.out.println("请求中的参数param"+param);
        }


        return result;
    }
}
