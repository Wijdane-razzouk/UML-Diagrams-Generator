package org.mql.java.models;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.mql.java.relations.RelationShip;

public class Classe {
    private Class<?> clazz;
    private List<FieldInfo> fields;
    private List<MethodInfo> methods;
    private Class<?> superclass;
    private List<Class<?>> interfaces;
    private List<ConstructorInfo> constructors;
    private List<AnnotationData> annotations;
    private List<RelationShip> relationships;

    public Classe(Class<?> clazz) {
        this.clazz = clazz;
        this.fields = new ArrayList<>();
        this.methods = new ArrayList<>();
        this.interfaces = new ArrayList<>();
        this.constructors = new ArrayList<>();
        this.annotations= new ArrayList<>();
        this.relationships = new ArrayList<>();
        extractClassDetails();
    }

    private void extractClassDetails() {
        extractFields();
        extractMethods();
        extractConstructors();
        extractSuperclass();
        extractInterfaces();
        extractAnnotations();
    }

    private void extractFields() {
        for (Field field : clazz.getDeclaredFields()) {
            fields.add(new FieldInfo(
                Modifier.toString(field.getModifiers()),
                field.getType().getSimpleName(),
                field.getName()
            ));
        }
    }

    private void extractMethods() {
        for (Method method : clazz.getDeclaredMethods()) {
            methods.add(new MethodInfo(
                method.getName(),
                method.getReturnType(),
                Modifier.toString(method.getModifiers()),
                method.getParameterTypes()
            ));
        }
    }

    private void extractConstructors() {
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            constructors.add(new ConstructorInfo(constructor));
        }
    }

    private void extractSuperclass() {
        this.superclass = clazz.getSuperclass();
    }

    private void extractInterfaces() {
        for (Class<?> iface : clazz.getInterfaces()) {
            interfaces.add(iface);
        }
    }
    private void extractAnnotations() {
        System.out.println("Extracting annotations from class: " + clazz.getName());
        for (Annotation annotation : clazz.getDeclaredAnnotations()) {
            System.out.println("Found annotation: " + annotation.annotationType().getSimpleName());
            annotations.add(new AnnotationData(annotation.annotationType()));
        }
    }
    public Class<?> getJavaClass() {
        return clazz;
    }

    public String getName() {
        return clazz.getName();
    }

    public String getModifiers() {
		return Modifier.toString(clazz.getModifiers());
	}


	public List<FieldInfo> getFields() {
        return fields;
    }

    public List<MethodInfo> getMethods() {
        return methods;
    }

    public Class<?> getSuperclass() {
        return superclass;
    }

    public List<Class<?>> getInterfaces() {
        return interfaces;
    }

    public List<ConstructorInfo> getConstructors() {
        return constructors;
    }
    public List<AnnotationData> getAnnotations() {
        return annotations;
    }
    public List<RelationShip> getRelationships() {
        return relationships;
    }

    public void addRelationship(RelationShip relationship) {
        this.relationships.add(relationship);
    }
}
