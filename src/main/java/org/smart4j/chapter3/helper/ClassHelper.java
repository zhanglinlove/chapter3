package org.smart4j.chapter3.helper;

import org.smart4j.chapter3.Constant.ConfigConstant;
import org.smart4j.chapter3.annotation.Controller;
import org.smart4j.chapter3.annotation.Service;
import org.smart4j.chapter3.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

public final class ClassHelper {

    private static final Set<Class<?>> CLASS_SET;

    static {
        String name = ConfigHelper.getAppBasePackage();
        System.out.println("name=" + name);
        CLASS_SET = ClassUtil.getClassSet(name);
    }

    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> serviceSet = new HashSet<>();
        for (Class<?> clazz : CLASS_SET) {
            if (clazz.isAnnotationPresent(Service.class)) {
                serviceSet.add(clazz);
            }
        }
        return serviceSet;
    }

    public static Set<Class<?>> getControllerSet() {
        Set<Class<?>> controllerSet = new HashSet<>();
        for (Class<?> clazz : CLASS_SET) {
            if (clazz.isAnnotationPresent(Controller.class)) {
                controllerSet.add(clazz);
            }
        }
        return controllerSet;
    }

    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> beanSet = new HashSet<>();
        beanSet.addAll(getControllerSet());
        beanSet.addAll(getServiceClassSet());
        return beanSet;
    }
}
