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
package com.mytest.designpatterns.interpreter;

import java.util.Stack;

/**
 * @author liqingyu
 * @since 2020/03/08
 */
public class Client {
    public static void main(String[] args) {
        Stack<Expression> stack = new Stack<Expression>();
        stack.add(new TerminalExpression());
        /*
		for(;;){
			//进行语法判断，并产生递归调用
		}
		*/
        //产生一个完整的语法树，由各各个具体的语法分析进行解析
        Expression exp = stack.pop();

        //具体元素进入场景，8进制，2进制...
        exp.interpreter("10进制数");
    }
}
