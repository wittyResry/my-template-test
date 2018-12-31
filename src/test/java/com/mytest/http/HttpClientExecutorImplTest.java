package com.mytest.http;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.mytest.common.utils.LogUtil;

/**
 * @author liqingyu
 * @since 2018/12/28
 */
public class HttpClientExecutorImplTest {

    @Test
    public void execute_post() throws UnsupportedEncodingException {
        HttpClientExecutor httpClientExecutor = new HttpClientExecutorImpl();
        JSONObject params = new JSONObject();
        params.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        params.put("args", new JSONObject().put("a", "1"));

        HttpPost httpPost = new HttpPost("https://www.baidu.com");
        httpPost.setEntity(new StringEntity(params.toJSONString()));
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(500)
                .setConnectTimeout(500).build();
        httpPost.setConfig(requestConfig);
        HttpResult httpResult = httpClientExecutor.execute(httpPost);
        LogUtil.info(LogUtil.getCurrentLog(), "httpResult:%s", httpResult);
    }

    @Test
    public void execute_get() {
        HttpClientExecutor httpClientExecutor = new HttpClientExecutorImpl();
        HttpGet httpGet = new HttpGet("https://www.baidu.com");
        HttpResult httpResult = httpClientExecutor.execute(httpGet);
        LogUtil.info(LogUtil.getCurrentLog(), "%s", httpResult);
    }
}