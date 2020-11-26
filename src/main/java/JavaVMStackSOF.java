/*
 * Copyright (c) 2020, HuXiaozhong. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

/**
 * VM Args: -Xss128k
 * -Xss: 减小栈内存容量
 * 虚拟机栈和本地方法栈测试
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("Stack length: " + oom.stackLength);
            throw e;
        }
    }
}
