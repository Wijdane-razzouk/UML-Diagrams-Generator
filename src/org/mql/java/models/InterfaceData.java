package org.mql.java.models;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class InterfaceData {
    private Class<?> clazz;
    private String name;
    private List<MethodInfo> methods;

    public InterfaceData(Class<?> clazz) {
        if (!clazz.isInterface()) {
            throw new IllegalArgumentException("The provided class is not an interface.");
        }
        this.clazz = clazz;
        this.name = clazz.getSimpleName();
        this.methods = new ArrayList<>();
        extractInterfaceDetails();
    }

    
    private void extractInterfaceDetails() {
        for (Method method : clazz.getDeclaredMethods()) {
            methods.add(new MethodInfo(
                method.getName(),
                method.getReturnType(),
                Modifier.toString(method.getModifiers()),
                method.getGenericParameterTypes()
            ));
        }
    }
    public Class<?> getJavaClass() {
        return clazz;
    }

    public String getName() {
        return clazz.getName();
    }

    public List<MethodInfo> getMethods() {
        return methods;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Interface: ").append(name).append("\n");

        sb.append("Methods:\n");
        for (MethodInfo method : methods) {
            sb.append("  - ").append(method).append("\n");
        }

        return sb.toString();
    }
}
