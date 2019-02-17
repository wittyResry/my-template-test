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

import java.util.ArrayList;
import java.util.List;

/**
 * @author liqingyu
 * @since 2019/02/16
 */
public class RuntimeConstantPoolOOM {

    /**
     * -XX:MetaspaceSize=2m -XX:MaxMetaspaceSize=2m
     * jdk1.8 使用元空间（ Metaspace ）替代了永久代（ PermSize ），因此我们可以在 1.8 中指定 Metaspace 的大小模拟上述两种情况
     *
     * @param args
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
            if (i % 1000000 == 0) {
                System.out.println(i);
            }
        }
    }
}
