package org.mql.java.models;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class EnumerationData {
    private Class<?> clazz;
    private String name;
    private List<String> constantes;
    private List<MethodInfo> methods;
    private List<ConstructorInfo> constructors;

    public EnumerationData(Class<?> clazz) {
        this.clazz = clazz;
        this.name = clazz.getSimpleName();
        this.constantes = new ArrayList<>();
        this.methods = new ArrayList<>();
        this.constructors = new ArrayList<>();
        extractEnumerationDetails();
    }
    public Class<?> getJavaClass() {
        return clazz;
    }

 
    private void extractEnumerationDetails() {
        extractConstants();
        extractMethods();
        extractConstructors();
    }

   
    private void extractConstants() {
        Object[] enumConstants = clazz.getEnumConstants();
        if (enumConstants != null) {
            for (Object constant : enumConstants) {
                constantes.add(constant.toString());
            }
        }
    }

   
    private void extractMethods() {
        for (Method method : clazz.getDeclaredMethods()) {
            methods.add(new MethodInfo(
                method.getName(),
                method.getReturnType(),
                Modifier.toString(method.getModifiers()),
                method.getGenericParameterTypes()
            ));
        }
    }

  
    private void extractConstructors() {
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            constructors.add(new ConstructorInfo(constructor));
        }
    }

    public String getName() {
        return name;
    }

    public List<String> getConstantes() {
        return constantes;
    }

    public List<MethodInfo> getMethods() {
        return methods;
    }

    public List<ConstructorInfo> getConstructors() {
        return constructors;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Enumeration: ").append(name).append("\n");

        sb.append("Constants:\n");
        for (String constant : constantes) {
            sb.append("  - ").append(constant).append("\n");
        }

        sb.append("Methods:\n");
        for (MethodInfo method : methods) {
            sb.append("  - ").append(method).append("\n");
        }

        sb.append("Constructors:\n");
        for (ConstructorInfo constructor : constructors) {
            sb.append("  - ").append(constructor).append("\n");
        }

        return sb.toString();
    }
}
