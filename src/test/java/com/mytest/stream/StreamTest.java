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
package com.mytest.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.mytest.common.utils.LogUtil;

/**
 * @author liqingyu
 * @since 2018/12/19
 * Stream操作分类
 * 中间操作(Intermediate operations)	无状态(Stateless)	unordered() filter() map() mapToInt() mapToLong() mapToDouble() flatMap() flatMapToInt() flatMapToLong() flatMapToDouble() peek()
 * 有状态(Stateful)	distinct() sorted() sorted() limit() skip()
 * 结束操作(Terminal operations)	非短路操作	forEach() forEachOrdered() toArray() reduce() collect() max() min() count()
 * 短路操作(short-circuiting)	anyMatch() allMatch() noneMatch() findFirst() findAny()
 */
public class StreamTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(StreamTest.class);

    @Test
    public void test() {
        //过滤
        List<String> list = Lists.newArrayList("bcd", "cde", "def", "abc", "abced");
        List<String> result = list.stream().filter(e -> e.length() == 3)
            .collect(Collectors.toList());
        LOGGER.warn(String.format("%s", result));
        //取第一个字符
        List<String> result2 = result.stream().map(f -> f.charAt(0)).map(String::valueOf)
            .collect(Collectors.toList());
        LOGGER.warn(String.format("%s", result2));
    }

    @Test
    public void optinal_orElse() {
        List<Integer> list = Arrays.asList();
        //通过reduce方法得到一个Optional类
        int a = list.stream().reduce(Integer::sum).orElse(get("a"));
        LogUtil.info(LogUtil.getCurrentLog(), "a=%d", a);
        int b = list.stream().reduce(Integer::sum).orElseGet(() -> get("b"));
        LogUtil.info(LogUtil.getCurrentLog(), "b=%d", b);
//        a执行了方法
//        1
//        b执行了方法
//        1

    }
    @Test
    public void optinal_test() {
        List<Integer> list =  Arrays.asList(10,20,30);
        //通过reduce方法得到一个Optional类
        int a =  list.stream().reduce(Integer::sum).orElse(get("a"));
        int b =  list.stream().reduce(Integer::sum).orElseGet(() -> get("b"));
        LogUtil.info(LogUtil.getCurrentLog(), "a  "+a);
        LogUtil.info(LogUtil.getCurrentLog(), "b  "+b);
    }

    public static int get(String name) {
        LogUtil.info(LogUtil.getCurrentLog(), name + "执行了方法");
        return 1;
    }
}
