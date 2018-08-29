package org.smart4j.chapter3.helper;

import org.smart4j.chapter3.util.ReflectionUtil;

import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Set;

public final class BeanHelper {

    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();

    static {
        Set<Class<?>> set = ClassHelper.getBeanClassSet();
        for (Class<?> clazz : set) {
            Object obj = ReflectionUtil.getInstance(clazz);
            BEAN_MAP.put(clazz, obj);
        }
    }

    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    public static <T> T getBean(Class<T> clazz) {
        if (!BEAN_MAP.containsKey(clazz))
            throw new NoSuchElementException("not found this class:" + clazz);
        return (T) BEAN_MAP.get(clazz);
    }

    /**
     * 设置 Bean 实例
     */
    public static void setBean(Class<?> cls, Object obj) {
        BEAN_MAP.put(cls, obj);
    }
}
