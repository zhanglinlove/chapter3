package org.smart4j.chapter3.helper;

import org.smart4j.chapter3.annotation.Inject;
import org.smart4j.chapter3.util.CollectionUtil;
import org.smart4j.chapter3.util.ReflectionUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

public final class IocHelper {

    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
                Class<?> clazz = entry.getKey();
                Object instance = entry.getValue();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(Inject.class)) {
                        Class<?> beanFieldClass = field.getType();
                        Object fieldInstance = beanMap.get(beanFieldClass);
                        if (fieldInstance != null)
                            ReflectionUtil.setFild(instance, field, fieldInstance);
                    }
                }
            }
        }
    }
}
