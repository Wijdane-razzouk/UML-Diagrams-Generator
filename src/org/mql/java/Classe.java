package org.mql.java;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class Classe {
    private List<FieldInfo> fields;
    private List<MethodInfo> methods;
    private Class<?> superclass;
    private List<Class<?>[]> interfaces;
    private List<ConstructorInfo> constructors;
    
    public Classe(String nom) {
        this.fields = new ArrayList<>();
        this.methods = new ArrayList<>();
        this.interfaces = new ArrayList<>();
        this.constructors=new ArrayList<>();
    }
    
    public void addField(FieldInfo field) {
        fields.add(field);
    }
    
    public void addMethod(MethodInfo method) {
        methods.add(method);
    }
    
    public void setSuperclass(Class<?> superclass) {
        this.superclass = superclass;
    }
    
    public void addInterface(Class<?>[] classes) {
        interfaces.add(classes);
    }
    public void addConstructor(Class<?>[] constructors) {
        interfaces.add(constructors);
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
    
    public List<Class<?>[]> getInterfaces() {
        return interfaces;
    }

	public void addConstructorInfo(ConstructorInfo declaredConstructors) {
		constructors.add(declaredConstructors);
		
	}
}