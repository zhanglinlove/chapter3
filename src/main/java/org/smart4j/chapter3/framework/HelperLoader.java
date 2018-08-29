package org.smart4j.chapter3.framework;

import org.smart4j.chapter3.helper.*;
import org.smart4j.chapter3.util.ClassUtil;

public final class HelperLoader {

    public static void init() {
        Class<?>[] classList = {BeanHelper.class, ClassHelper.class, ConfigHelper.class, ControllerHelper.class, IocHelper.class};
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName(), true);
        }
    }
}
