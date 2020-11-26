/*
 * Copyright (c) 2020, HuXiaozhong. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms 设置最小值
 * -Xmx 设置最大值
 * -XX:+HeapDumpOnOutOfMemoryError  当出现内存溢出异常时，Dump出当前的内存转储快照
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * <p>
 * 堆内存溢出异常测试
 */
class HeapOOM {
    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
