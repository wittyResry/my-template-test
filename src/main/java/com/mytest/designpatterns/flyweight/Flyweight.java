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
public abstract class Flyweight {
    /** 内部状态 */
    private String intrinsic;
    /** 外部状态 */
    protected String extrinsic;

    /**
     * 构造方法
     */
    public Flyweight(String extrinsic) {
        this.extrinsic = extrinsic;
    }

    /**
     * 业务处理
     */
    public abstract void operate();

    /**
     * Getter method for property <tt>intrinsic</tt>.
     *
     * @return property value of intrinsic
     */
    public String getIntrinsic() {
        return intrinsic;
    }

    /**
     * Setter method for property <tt>intrinsic</tt>.
     *
     * @param intrinsic value to be assigned to property intrinsic
     */
    public void setIntrinsic(String intrinsic) {
        this.intrinsic = intrinsic;
    }
}
