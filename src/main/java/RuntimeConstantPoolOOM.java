/*
 * Copyright (c) 2020, HuXiaozhong. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

import java.util.HashSet;
import java.util.Set;

/**
 * VM Args: -Xmx2m
 * 因为JDK8已经把原本放在永久代的字符串常量池移到了 Java堆 中，
 * 所以限制了堆大小之后，会模拟出来堆内存溢出异常
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }

        // 测试 String.intern() 返回引用
        // 在JDK7之后，创建的String都是在堆上
        /*String str1 = new StringBuilder().append("计算机").append("软件").toString();
        System.out.println(str1 == str1.intern());  // true，自己创建的当然能捕捉到

        String str2 = new StringBuilder().append("Ja").append("va").toString();
        System.out.println(str2 == str2.intern());  // false，并不是自己创建的 Java*/
    }
}
