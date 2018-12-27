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
 * 日志工具类
 *
 * @author liqingyu
 * @since 2018/05/25
 */
public class LogUtil {
    /** 日志打印 */
    private final static Logger              LOGGER    = LoggerFactory.getLogger(LogUtil.class);

    /** 调用栈下标 */
    private static final int                 INDEX     = 2;

    /** 缓存map */
    private static final Map<String, Logger> loggerMap = Maps.newHashMap();

    /**
     * 输出info level的log信息.
     *
     * @param logger
     * @param message log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params  log格式化参数,数组length与message参数化个数相同, 如:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void info(Logger logger, String message, Object... params) {
        if (logger.isInfoEnabled()) {
            logger.info(format(message, params));
        }
    }

    /**
     * 输出warn level的log信息.
     *
     * @param message log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params log格式化参数,数组length与message参数化个数相同, 如:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
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
     * 输出warn level的log信息.
     *
     * @param throwable 异常对象
     * @param message log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params log格式化参数,数组length与message参数化个数相同, 如:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void warn(Logger logger, Throwable throwable, String message, Object... params) {
        logger.warn(format(message, params), throwable);
    }

    /**
     * 输出debug level的log信息.
     *
     * @param message log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params log格式化参数,数组length与message参数化个数相同, 如:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void debug(Logger logger, String message, Object... params) {
        if (logger.isDebugEnabled()) {
            logger.debug(format(message, params));
        }
    }

    /**
     * 输出error level的log信息.
     *
     * @param message log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params log格式化参数,数组length与message参数化个数相同, 如:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
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
     * 输出error level的log信息.
     *
     * @param throwable 异常对象
     * @param message log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params log格式化参数,数组length与message参数化个数相同, 如:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
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
     * 日志信息参数化格式化
     *
     * @param message log信息,如:<code>xxx{0},xxx{1}...</code>或者<code>xxx%s,xxx%s...</code>
     * @param params log格式化参数,数组length与message参数化个数相同, 如:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
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
     * 打印调用日志
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
     * 打印调用日志
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
     * 反射获取当前LOGGER
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
