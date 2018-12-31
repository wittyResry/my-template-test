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

import java.text.MessageFormat;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.mytest.common.constants.SymbolConstants;

/**
 * ��־������
 *
 * @author liqingyu
 * @since 2018/05/25
 */
public class LogUtil {
    /** ��־��ӡ */
    private final static Logger              LOGGER    = LoggerFactory.getLogger(LogUtil.class);

    /** ����ջ�±� */
    private static final int                 INDEX     = 2;

    /** ����map */
    private static final Map<String, Logger> loggerMap = Maps.newHashMap();

    /**
     * ���info level��log��Ϣ.
     *
     * @param logger
     * @param message log��Ϣ,��:<code>xxx{0},xxx{1}...</code>
     * @param params  log��ʽ������,����length��message������������ͬ, ��:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void info(Logger logger, String message, Object... params) {
        if (logger.isInfoEnabled()) {
            logger.info(format(message, params));
        }
    }

    /**
     * ���warn level��log��Ϣ.
     *
     * @param message log��Ϣ,��:<code>xxx{0},xxx{1}...</code>
     * @param params log��ʽ������,����length��message������������ͬ, ��:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void warn(Logger logger, String message, Object... params) {
        Throwable t = getThrowable(params);
        if (null == t) {
            logger.warn(format(message, params));
        } else {
            logger.warn(format(message, params), t);
        }
    }

    /**
     * ���warn level��log��Ϣ.
     *
     * @param throwable �쳣����
     * @param message log��Ϣ,��:<code>xxx{0},xxx{1}...</code>
     * @param params log��ʽ������,����length��message������������ͬ, ��:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void warn(Logger logger, Throwable throwable, String message, Object... params) {
        logger.warn(format(message, params), throwable);
    }

    /**
     * ���debug level��log��Ϣ.
     *
     * @param message log��Ϣ,��:<code>xxx{0},xxx{1}...</code>
     * @param params log��ʽ������,����length��message������������ͬ, ��:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void debug(Logger logger, String message, Object... params) {
        if (logger.isDebugEnabled()) {
            logger.debug(format(message, params));
        }
    }

    /**
     * ���error level��log��Ϣ.
     *
     * @param message log��Ϣ,��:<code>xxx{0},xxx{1}...</code>
     * @param params log��ʽ������,����length��message������������ͬ, ��:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void error(Logger logger, String message, Object... params) {
        Throwable t = getThrowable(params);
        if (null == t) {
            logger.error(format(message, params));
        } else {
            logger.error(format(message, params), t);
        }
    }

    /**
     * ���error level��log��Ϣ.
     *
     * @param throwable �쳣����
     * @param message log��Ϣ,��:<code>xxx{0},xxx{1}...</code>
     * @param params log��ʽ������,����length��message������������ͬ, ��:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void error(Logger logger, Throwable throwable, String message, Object... params) {
        logger.error(format(message, params), throwable);
    }

    /**
     * search throwable object from params
     *
     * @param params
     * @return
     */
    private static Throwable getThrowable(Object... params) {
        for (Object obj : params) {
            if (obj instanceof Throwable) {
                return (Throwable) obj;
            }
        }
        return null;
    }

    /**
     * ��־��Ϣ��������ʽ��
     *
     * @param message log��Ϣ,��:<code>xxx{0},xxx{1}...</code>����<code>xxx%s,xxx%s...</code>
     * @param params log��ʽ������,����length��message������������ͬ, ��:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    private static String format(String message, Object... params) {
        if (params != null && params.length != 0) {
            if (StringUtils.contains(message, "{0}")) {
                return MessageFormat.format(message, params);
            } else {
                String.format(message, params);
            }
        }
        return message;
    }

    public static void log(Logger logger, Throwable throwable, String message, Object... params) {
        if (throwable != null) {
            logger.error(format(message, params), throwable);
        } else {
            logger.info(format(message, params), throwable);
        }
    }

    /**
     * ��ӡ������־
     */
    public static void digestLog() {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        if (ste.length < 3) {
            LogUtil.error(LOGGER, "skip digest method name");
            return;
        }
        LogUtil.info(LOGGER,
            String.format("%s.%s", ste[INDEX].getClassName(), ste[INDEX].getMethodName()));
    }

    /**
     * ��ӡ������־
     */
    public static void digestLog(Logger logger, String msg, Object... params) {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        if (ste.length < 3 || logger == null) {
            LogUtil.error(LOGGER, "skip digest method name");
            return;
        }
        try {
            LogUtil.info(logger, String.format("msg=%s[%s.%s]", String.format(msg, params),
                ste[INDEX].getClassName(), ste[INDEX].getMethodName()));
        } catch (Throwable e) {
            LogUtil.error(LOGGER, e, "digestLog ERROR");
        }
    }

    /**
     * �����ȡ��ǰLOGGER
     * @return
     */
    public static Logger getCurrentLog() {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        if (ste.length < 3) {
            LogUtil.error(LOGGER, "skip digest method name");
            return LOGGER;
        }
        try {
            String className = ste[INDEX].getClassName();
            if (loggerMap.containsKey(className)) {
                return loggerMap.get(className);
            }
            Class clazz = Class.forName(ste[INDEX].getClassName());
            Logger logger = LoggerFactory.getLogger(clazz);
            loggerMap.put(className, logger);
            return logger;
        } catch (ClassNotFoundException e) {
            LogUtil.error(LOGGER, "class forName ERROR");
            return LOGGER;
        }
    }

    public static String formatScene(String scene, String msg) {
        StringBuilder sb = new StringBuilder();
        sb.append(SymbolConstants.LEFT_C_SQUARE_BRACKET).append(scene)
            .append(SymbolConstants.RIGHT_C_SQUARE_BRACKET).append(msg);
        return sb.toString();
    }
}
