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
package com.mytest.designpatterns.flyweight;

/**
 * @author liqingyu
 * @since 2020/03/08
 */
public class Client {
    public static void main(String[] args) {
        //ʵ��ͨ��FlyweightFactory������ֻ��������������
        for (int i = 0 ;i < 10; ++i) {
            Flyweight flyweight = null;
            if (i % 2 == 0) {
                flyweight = FlyweightFactory.getFlyweight("type1");
            } else {
                flyweight = FlyweightFactory.getFlyweight("type2");
            }
            flyweight.operate();
        }
        test(args);
    }

    public static void test(String[] args) {
        String str1 = "��г";
        String str2 = "���";
        String str3 = "��г���";
        String str4;

        str4 = str1 + str2;
        System.out.println(str3 == str4);

        //intern():��String�Ķ�������и����͵�ֵ����ֱ�ӷ��ض�����еĶ���
        str4 = (str1 + str2).intern();
        System.out.println(str3 == str4);
    }
}
