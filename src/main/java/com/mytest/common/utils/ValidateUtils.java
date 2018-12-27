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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * @author liqingyu
 * @since 2018/06/02
 */
public class ValidateUtils {

    /** Email��ʽ��������ʽ */
    private final static String  REGEX_EMAIL                        = "^([a-zA-Z0-9_\\.\\-\\+])+\\@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,20}$";

    /** Email pattern */
    private final static Pattern REGEX_EMAIL_PATTERN                = Pattern.compile(REGEX_EMAIL);

    /** ��½�ֻ������������ʽ */
    private static final String  REGEX_CHINA_MOBILE                 = "^(([+]?[0]{0,2}86)|([+]?0{0,2}86-))?1(3|4|5|7|8)\\d{9}$";
    /** china mobile pattern */
    private final static Pattern REGEX_CHINA_MOBILE_PATTERN         = Pattern
        .compile(REGEX_CHINA_MOBILE);

    /** �����ֻ������������ʽ */
    private static final String  REGEX_INTERNATIONAL_MOBILE         = "^[+]?[0]{0,2}[1-9]{1}\\d{0,7}-\\d{6,14}$";
    /** international mobile pattern */
    private final static Pattern REGEX_INTERNATIONAL_MOBILE_PATTERN = Pattern
        .compile(REGEX_INTERNATIONAL_MOBILE);

    /**
     * �ж�������ַ����Ƿ��ǺϷ�email��ʽ
     *
     * @param   src     ���жϵ������ַ���
     * @return  True    �ǺϷ���email
     *          False   ���ǺϷ���email
     */
    public static boolean isValidEmail(String src) {
        // ��Ч�����룬ֱ�ӷ���false
        if (StringUtils.isBlank(src)) {
            return false;
        }
        Matcher pm = REGEX_EMAIL_PATTERN.matcher(src);
        return pm.matches();
    }

    /**
     * �ж�������ַ����Ƿ��ǺϷ�mobile��ʽ������δ���ơ�
     *
     * @param   mobile
     * @return  True    �ǺϷ���mobile
     *          False   ���ǺϷ���mobile
     */
    public static boolean isValidMobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return false;
        }
        mobile = StringUtils.trim(mobile);
        Matcher pm = REGEX_CHINA_MOBILE_PATTERN.matcher(mobile);
        if (!pm.matches()) {
            pm = REGEX_INTERNATIONAL_MOBILE_PATTERN.matcher(mobile);
        }
        return pm.matches();
    }
}
