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
package com.mytest.designpatterns.chain;

/**
 * @author liqingyu
 * @since 2020/03/08
 */
public class Client {
    public static void main(String[] args) {
        //���������еĴ���ڵ�
        ConcreteHandler1 handler1 = new ConcreteHandler1();
        ConcreteHandler2 handler2 = new ConcreteHandler2();
        ConcreteHandler3 handler3 = new ConcreteHandler3();
        //�������еĽ׶�˳��,1-->2-->3
        handler1.setNext(handler2);
        handler2.setNext(handler3);
        //ִ��
        handler1.process("hello world");
    }
}
