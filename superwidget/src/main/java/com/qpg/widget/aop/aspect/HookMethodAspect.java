package com.qpg.widget.aop.aspect;

import com.qpg.widget.aop.trace.HookMethodTrace;
import com.qpg.widget.aop.util.Preconditions;
import com.qpg.widget.aop.util.reflect.Reflect;
import com.qpg.widget.aop.util.reflect.ReflectException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import java.lang.reflect.Method;

@Aspect
public class HookMethodAspect {

    private static final String POINTCUT_METHOD = "execution(@com.qpg.widget.aop.trace.HookMethodTrace * *(..))";

    private static final String POINTCUT_CONSTRUCTOR = "execution(@com.qpg.widget.aop.trace.HookMethodTrace *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithHookMethod() {
    }

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedHookMethod() {
    }

    @Around("methodAnnotatedWithHookMethod() || constructorAnnotatedHookMethod()")
    public void hookMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        HookMethodTrace hookMethod = method.getAnnotation(HookMethodTrace.class);

        if (hookMethod==null) return;

        String beforeMethod = hookMethod.beforeMethod();
        String afterMethod = hookMethod.afterMethod();

        if (Preconditions.isNotBlank(beforeMethod)) {
            try {
                Reflect.on(joinPoint.getTarget()).call(beforeMethod);
            } catch (ReflectException e) {
                e.printStackTrace();
              //  L.e("no method "+beforeMethod);
            }
        }

        joinPoint.proceed();

        if (Preconditions.isNotBlank(afterMethod)) {
            try {
                Reflect.on(joinPoint.getTarget()).call(afterMethod);
            } catch (ReflectException e) {
                e.printStackTrace();
              //  L.e("no method "+afterMethod);
            }
        }
    }
}
