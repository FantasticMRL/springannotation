package com.mrl.spring.annotation.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;

/**
 *  
 *  日志切面类
 */

@Aspect //切面类
public class LogAspects
{
    
    //本类引用
    @Pointcut("execution(public int com.mrl.spring.annotation.aop.MathCalculate.*(..))")
    public void pointCut() {};

    //JoinPoint一定要放在参数表的第一位
    //@Before在目标方法之前切入，切入点表达式
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("方法签名:"+signature.getName()+"方法参数:"+Arrays.asList(args));
        System.out.println("除法Start");
    }

    //@After无论方法是否正常结束
    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("方法签名:"+signature.getName()+"方法参数:"+Arrays.asList(args));
        System.out.println("除法End");
    }

    //正常返回
    @AfterReturning(value="pointCut()",returning="result")
    public void logReturn(Object result) {
        System.out.println("除法Return,返回值是："+result);
    }

    @AfterThrowing(value="pointCut()",throwing="e")
    public void logException(Exception e) {
        System.out.println("除法Exception:"+e);
    }

    /**
     * @Author lwq
     * @Description 环绕通知
     * @Date 2018/11/28 9:31
     * @Param [proceedingJoinPoint]
     * @return void
     **/
//    @Around("pointCut()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint){
        Object object = new Object();
        try {
            object = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return object;
    }

}
