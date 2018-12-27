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
package com.mytest.http;

import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.slf4j.Logger;

import com.mytest.common.utils.LogUtil;

/**
 * @author liqingyu
 * @since 2018/12/28
 */
public class HttpClientExecutorImpl implements HttpClientExecutor {
    /** 日志打印 */
    private final static Logger LOGGER = LogUtil.getCurrentLog();

    /** httpClient请求客户端 */
    private HttpClient          httpClient;

    /**
     * 构造方法
     */
    public HttpClientExecutorImpl() {
        try {
            LogUtil.digestLog(LOGGER, "构造HttpClientExecutorImpl");
        } catch (Throwable t) {
            LogUtil.error(LOGGER, t, "构造失败");
        }

    }

    @Override
    public HttpRequest execute(HttpRequestBase httpRequestBase) {
        return null;
    }
}
