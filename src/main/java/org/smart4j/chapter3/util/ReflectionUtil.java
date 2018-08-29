package org.smart4j.chapter3.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class ReflectionUtil {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    public static Object getInstance(Class<?> clazz) {
        Object obj = null;

        try {
            obj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("instance fail.");
        }
        return obj;
    }

    public static Object invokeMethod(Object obj, Method method, Object ...args) {
        Object result = null;
        try {
            method.setAccessible(true);
            result = method.invoke(obj, args);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("method invoke fail.");
        }
        return result;
    }

    public static void setFild(Object obj, Field field, Object value) {
        field.setAccessible(true);
        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            logger.error("set field value fail.");
        }
    }
}
