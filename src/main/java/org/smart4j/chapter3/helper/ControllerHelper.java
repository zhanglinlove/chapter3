package org.smart4j.chapter3.helper;

import org.smart4j.chapter3.annotation.Action;
import org.smart4j.chapter3.annotation.Inject;
import org.smart4j.chapter3.bean.Handler;
import org.smart4j.chapter3.bean.Request;
import org.smart4j.chapter3.util.CollectionUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class ControllerHelper {

    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static {
        Set<Class<?>> controllerSet = ClassHelper.getControllerSet();
        if (CollectionUtil.isNotEmpty(controllerSet)) {
            for (Class<?> clazz : controllerSet) {
                Method[] methods = clazz.getDeclaredMethods();
                if (methods != null) {
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(Action.class)) {
                            Annotation annotation = method.getAnnotation(Action.class);
                            String mapping = ((Action) annotation).value();
                            if (mapping.matches("\\w+:/\\w*")) {
                                String[] array = mapping.split(":");
                                if (array != null && array.length == 2) {
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(clazz, method);
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}
