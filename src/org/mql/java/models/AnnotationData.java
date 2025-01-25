package org.mql.java.models;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class AnnotationData {
    private Class<?> clazz;
    private String name;
    private String modifiers;
    private List<MethodInfo> elements;
    private List<String> metaAnnotations;

    public AnnotationData(Class<?> clazz) {
    	 this.clazz = clazz;
        this.metaAnnotations = new ArrayList<>();
        this.elements = new ArrayList<>();
        extractData();
    }

    public void extractData() {
        for (Method method : clazz.getDeclaredMethods()) {
            this.elements.add(new MethodInfo(
                method.getName(),
                method.getReturnType(),
                Modifier.toString(method.getModifiers()),
                method.getParameterTypes()
            ));
        }

        for (Annotation annotation : clazz.getDeclaredAnnotations()) {
            this.metaAnnotations.add(annotation.annotationType().getSimpleName());
        }
    }
    public Class<?> getJavaClass() {
        return clazz;
    }
   
    public String getName() {
        return clazz.getSimpleName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModifiers() {
        return  Modifier.toString(clazz.getModifiers());
    }

    public void setModifiers(String modifiers) {
        this.modifiers = modifiers;
    }

    public List<MethodInfo> getElements() {
        return elements;
    }

    public void setElements(List<MethodInfo> elements) {
        this.elements = elements;
    }

    public List<String> getMetaAnnotations() {
        return metaAnnotations;
    }

    public void setMetaAnnotations(List<String> metaAnnotations) {
        this.metaAnnotations = metaAnnotations;
    }

 

//    @Override
//    public String toString() {
//        return "AnnotationData [name=" + name + ", modifiers=" + modifiers + ", elements=" + elements
//                + ", metaAnnotations=" + metaAnnotations + "]";
//    }
}