/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mytest.jvmtest;

import java.util.List;

import com.google.common.collect.Lists;
import com.mytest.common.utils.LogUtil;

/**
 * 堆区：
 * 1. 线程共享
 * 2. 存储对象实例和数组
 * 3. 垃圾回收的主要管理区域
 * 4. 物理内存空间不连续
 * 5. 无内存空间，且无法扩展是抛出oom
 * 6. 永久带在jdk1.8被移除，使用元空间代替。两者本质一致，都是对jvm方法区的实现。最大的区别在于元空间不在JVM中，而是使用本地内存
 * 7. 分代收集算法，Eden 、From Survivor，To Survivor空间
 * 8. Stack Overflow Error 和 OutOfMemoryError（本地方法栈）
 * 9. Serial、ParNew、
 *
 * @author liqingyu
 * @since 2019/02/03
 */
public class OomTest {
    static class OOMObject {

    }

    /**
     * 现象： Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     * 1、 存在内存泄露（与GC root关联，导致内存无法被回收），开始并不会内存溢出，但是运行时间长了，积少成多后，就会导致GC。
     * 2. Dump内存，通过排查方式GC root引用链，看对象占用的内存大小。
     *
     * @param args
     */
    public static void main(String[] args) {
        List<OOMObject> list = Lists.newArrayList();
        int i = 0;
        while (true) {
            list.add(new OOMObject());
            if (i % 10000 == 0) {
                LogUtil.info(LogUtil.getCurrentLog(), "添加第" + i + "个对象到list中");
            }
            ++i;
        }
    }
}
