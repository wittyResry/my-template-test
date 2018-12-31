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

import com.mytest.common.domain.ToString;

/**
 * @author liqingyu
 * @since 2018/12/28
 */
public class HttpResult extends ToString {

    /** http状态码 */
    private int    statusCode;

    /** http返回结果 */
    private String response;

    /**
     * 默认构造方法
     */
    public HttpResult() {
    }

    /**
     * 构造方法
     *
     * @param statusCode
     * @param response
     */
    public HttpResult(int statusCode, String response) {
        this.statusCode = statusCode;
        this.response = response;
    }

    /**
     * Getter method for property <tt>statusCode</tt>.
     *
     * @return property value of statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Setter method for property <tt>statusCode</tt>.
     *
     * @param statusCode value to be assigned to property statusCode
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Getter method for property <tt>response</tt>.
     *
     * @return property value of response
     */
    public String getResponse() {
        return response;
    }

    /**
     * Setter method for property <tt>response</tt>.
     *
     * @param response value to be assigned to property response
     */
    public void setResponse(String response) {
        this.response = response;
    }
}
