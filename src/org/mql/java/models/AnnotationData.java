package org.mql.java.models;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class AnnotationData {
    private Class<?> clazz;

    public AnnotationData(Class<?> clazz) {
        this.clazz = clazz;
    }
    public Class<?> getJavaClass() {
        return clazz;
    }

    public String getName() {
		return "Annotation: " + (Modifier.isPublic(clazz.getModifiers()) ? " public" : "") + " " + clazz.getSimpleName();
    }
    	public void printInfo() {

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            Object defaultValue = getDefaultValue(method);
            String defaultValueString = defaultValue != null ? " [Default: " + defaultValue + "]" : "";
            System.out.println("      - " + methodName + "() " + method.getReturnType().getSimpleName() + defaultValueString);
        }
    }

    private Object getDefaultValue(Method method) {
        try {
            if (method.isAnnotationPresent(java.lang.annotation.Retention.class)) {
                return method.getDefaultValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
