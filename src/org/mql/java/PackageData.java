package org.mql.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PackageData {
	private String name;
	private List<String> packages = ExplorerProjet.allpackages;
	private List<Class<?>> enumerations = ExplorerProjet.allenumerations;
	private List<Class<?>> interfaces = ExplorerProjet.allinterfaces;
	private List<Class<?>> classes = ExplorerProjet.allclasses;
	private List<Class<?>> annotations = ExplorerProjet.allannotations;

	public PackageData(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addEnumerationData() {
		for (Class<?> data : enumerations) {
			EnumerationData enume = new EnumerationData(data.getName());
			for (Object obj : data.getEnumConstants()) {
				enume.getConstantes().add(obj.toString());
			}

			Method[] methods = data.getDeclaredMethods();
			for (Method method : methods) {
				MethodInfo methodInfo = new MethodInfo(method.getName(), method.getReturnType(),
						Modifier.toString(method.getModifiers()), method.getGenericParameterTypes());
				enume.addMethod(methodInfo);
			}
			Constructor<?>[] constructors = data.getDeclaredConstructors();
			for (Constructor<?> constructor : constructors) {
				ConstructorInfo constr = new ConstructorInfo(constructor);
				enume.addConstructorInfo(constr);
			}
		}
	}

	public void addClasseData() {
		for (Class<?> data : classes) {
			Classe pk= new Classe(data.getName());
			Field[] fields= data.getDeclaredFields();
			for (Field field : fields) {
				FieldInfo fieldInfo = new FieldInfo(field.getName(),field.getType().getTypeName(),Modifier.toString(field.getModifiers()));
	            pk.addField(fieldInfo);
			}
			Method[] methods=data.getDeclaredMethods();
			for (Method method : methods) {
				 MethodInfo methodInfo = new MethodInfo(method.getName(),method.getReturnType(),Modifier.toString(method.getModifiers()),method.getGenericParameterTypes());
				 pk.addMethod(methodInfo);
			}
			Constructor<?>[] constructors=data.getDeclaredConstructors();
			for(Constructor<?> constructor :constructors) {
				ConstructorInfo constr= new ConstructorInfo(constructor);
				pk.addConstructorInfo(constr);
				}

			pk.addInterface(data.getInterfaces());
			pk.setSuperclass(data.getSuperclass());
			
		}
	}
	public void addInterfaceData() {
		for (Class<?> data : interfaces) {
			InterfaceData inter= new InterfaceData(data.getName());
			Method[] methods = data.getDeclaredMethods();
			for (Method method : methods) {
				MethodInfo methodInfo = new MethodInfo(method.getName(), method.getReturnType(),
						Modifier.toString(method.getModifiers()), method.getGenericParameterTypes());
				inter.addMethod(methodInfo);
			}

		}
	}

}
