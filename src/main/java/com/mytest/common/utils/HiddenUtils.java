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
package com.mytest.common.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @author liqingyu
 * @since 2018/06/02
 */
public class HiddenUtils {

    /**
     * ��ȡ���غ���ֻ���ַ
     *
     * <li>mobileΪ�ջ��߷Ƿ�����ԭ������</li>
     * <li>���ع��򣺱���ǰ��3λ�ͺ���4λ</li>
     *
     * @param mobile
     * @return
     */
    public static String getHiddenMobile(String mobile) {
        //ȥ��ͷβ�հ��ַ�
        mobile = StringUtils.trim(mobile);

        //�հ��ַ����ؿ��ַ���
        if (StringUtils.isBlank(mobile)) {
            return StringUtils.EMPTY;
        }

        if (ValidateUtils.isValidMobile(mobile)) {
            //������ֻ����룬��½�ֻ��������ֻ�����+�ŵ��ֻ�
            return StringUtils.overlay(mobile, "****", 3, 7);
        }
        //�����ʽ�����ʼ���ַ���ֻ����룬���أ�����������
        return mobile;
    }

    /**
     * ��ȡ���غ�������ַ
     *
     * <li>emailΪ�ջ��߷Ƿ�����ԭ������</li>
     * <li>���ع�������@ǰ��3λ,���@ǰ������3λ��ȡ��һλ+��***��</li>
     *
     * @param email
     * @return
     */
    public static String getHiddenEmail(String email) {
        email = StringUtils.trim(email);
        if (ValidateUtils.isValidEmail(email)) {
            int index = StringUtils.indexOf(email, "@");
            if (index == -1) {
                return email;
            }
            // @ǰ������3λ����ȡ��һ+��***��
            StringBuilder sb = new StringBuilder();
            if (index <= 3) {
                sb.append(StringUtils.substring(email, 0, index)).append("***");
            } else {
                sb.append(StringUtils.substring(email, 0, 3)).append("***");
            }
            return sb.append(StringUtils.substring(email, index)).toString();
        }
        return email;
    }
}
