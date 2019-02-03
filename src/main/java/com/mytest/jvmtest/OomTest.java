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
 * ������
 * 1. �̹߳���
 * 2. �洢����ʵ��������
 * 3. �������յ���Ҫ��������
 * 4. �����ڴ�ռ䲻����
 * 5. ���ڴ�ռ䣬���޷���չ���׳�oom
 * 6. ���ô���jdk1.8���Ƴ���ʹ��Ԫ�ռ���档���߱���һ�£����Ƕ�jvm��������ʵ�֡�������������Ԫ�ռ䲻��JVM�У�����ʹ�ñ����ڴ�
 * 7. �ִ��ռ��㷨��Eden ��From Survivor��To Survivor�ռ�
 * 8. Stack Overflow Error �� OutOfMemoryError�����ط���ջ��
 * 9. Serial��ParNew��
 *
 * @author liqingyu
 * @since 2019/02/03
 */
public class OomTest {
    static class OOMObject {

    }

    /**
     * ���� Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     * 1�� �����ڴ�й¶����GC root�����������ڴ��޷������գ�����ʼ�������ڴ��������������ʱ�䳤�ˣ����ٳɶ�󣬾ͻᵼ��GC��
     * 2. Dump�ڴ棬ͨ���Ų鷽ʽGC root��������������ռ�õ��ڴ��С��
     *
     * @param args
     */
    public static void main(String[] args) {
        List<OOMObject> list = Lists.newArrayList();
        int i = 0;
        while (true) {
            list.add(new OOMObject());
            if (i % 10000 == 0) {
                LogUtil.info(LogUtil.getCurrentLog(), "��ӵ�" + i + "������list��");
            }
            ++i;
        }
    }
}
