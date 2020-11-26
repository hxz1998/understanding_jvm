/*
 * Copyright (c) 2020, HuXiaozhong. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * VM Args: -XX:PermSize=10m -XX:MaxPermSize=10m -XX:+HeapDumpOnOutOfMemoryError
 * 上面这个设置是没用的，因为从JDK8开始，HotSpot虚拟机就没有了永久代这么一说，变成了元空间可通过这样设置来实现：
 * VM Args: -XX:MaxMetaspaceSize=10m
 * -XX:MaxMetaspaceSize=10m 设置最大元空间，默认是-1，即不设置大小（受限于本地）
 * -XX:MetaspaceSize=10m    设置元空间的初始空间大小，以字节为单位，如果达到了该值，就会触发垃圾收集，并且会调整元空间的大小
 * -XX:MinMetaspaceFreeRatio
 * 报错为：Exception in thread "main" java.lang.OutOfMemoryError: Metaspace
 */
public class JVMMethodAreaOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, objects);
                }
            });
            enhancer.create();
        }
    }
}
