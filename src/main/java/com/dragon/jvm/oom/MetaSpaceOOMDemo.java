package com.dragon.jvm.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/9/11 17:16
 * @description：
 * @modified By：
 * @version: $
 */
public class MetaSpaceOOMDemo {
    static class OOMTest{}

    /**
     * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
     * Caused by: java.lang.OutOfMemoryError: Metaspace
     * @param args
     */
    public static void main(String[] args) {
        int i = 0;
        try {
            while (true) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, args);
                    }
                });
                enhancer.create();
            }
        } catch (Throwable e) {
            System.out.println("多少次后产生异常：" + i);
            e.printStackTrace();
        }

    }

}
