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

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;

import com.mytest.common.constants.SymbolConstants;
import com.mytest.common.utils.LogUtil;
import com.mytest.common.constants.CommonConstants;

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
        // 设置http线程池
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(3 * 1000)
            .setConnectTimeout(5 * 1000).setSocketTimeout(30 * 1000).build();
        TrustStrategy trustStrategy = (arg0, arg1) -> true;
        //httpClient设置忽略SSL（trust all）
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, trustStrategy)
                .build();
            httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig)
                .setSslcontext(sslContext).setMaxConnPerRoute(1024).setMaxConnTotal(1280).build();
        } catch (Throwable t) {
            LogUtil.error(LOGGER, t, "构造失败");
        }

    }

    @Override
    public HttpResult execute(HttpRequestBase httpRequestBase) {
        HttpResponse httpResponse;
        Throwable throwable = null;
        HttpResult httpResult = new HttpResult(-1, "");
        long startTime = System.currentTimeMillis();
        try {
            httpResponse = httpClient.execute(httpRequestBase);
            if (httpResponse != null) {
                HttpEntity entity = httpResponse.getEntity();
                if (entity != null) {
                    String responseBody = EntityUtils.toString(entity,
                        CommonConstants.DEFAULT_CHARSET);
                    httpResult = new HttpResult(httpResponse.getStatusLine().getStatusCode(),
                        responseBody);
                    EntityUtils.consume(entity);
                }
            }
        } catch (Throwable t) {
            throwable = t;
        }
        String logScene = httpRequestBase.getURI().toString() + SymbolConstants.EQUAL
                          + SymbolConstants.RIGHT_ANGEL_BRACKET + httpResult.getStatusCode();
        long elapsed = System.currentTimeMillis() - startTime;
        LogUtil.log(LogUtil.getCurrentLog(), throwable, LogUtil.formatScene(logScene,
            String.format("%s elapsed=%dms", httpResult.getResponse(), elapsed)));
        return httpResult;
    }
}
