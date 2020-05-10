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
package com.mytest.designpatterns.mediator;

/**
 * @author liqingyu
 * @since 2020/03/12
 */
public abstract class Mediator {
    protected ConcreteColleague1 c1;
    protected ConcreteColleague2 c2;
    public abstract void doSomething1();
    public abstract void doSomething2();

    /**
     * Getter method for property <tt>c1</tt>.
     *
     * @return property value of c1
     */
    public ConcreteColleague1 getC1() {
        return c1;
    }

    /**
     * Setter method for property <tt>c1</tt>.
     *
     * @param c1 value to be assigned to property c1
     */
    public void setC1(ConcreteColleague1 c1) {
        this.c1 = c1;
    }

    /**
     * Getter method for property <tt>c2</tt>.
     *
     * @return property value of c2
     */
    public ConcreteColleague2 getC2() {
        return c2;
    }

    /**
     * Setter method for property <tt>c2</tt>.
     *
     * @param c2 value to be assigned to property c2
     */
    public void setC2(ConcreteColleague2 c2) {
        this.c2 = c2;
    }
}
