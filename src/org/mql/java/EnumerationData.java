package org.mql.java;

import java.util.ArrayList;
import java.util.List;

public class EnumerationData {
	private String name;
	private List<String> constantes;
	private List<MethodInfo> methods;
	private List<ConstructorInfo> contructors;

	public EnumerationData(String name) {
		super();
		this.name = name;
		this.constantes = new ArrayList<>();
		this.methods = new ArrayList<>();
		this.contructors = new ArrayList<>();
	}
	
	 public void addMethod(MethodInfo method) {
	        methods.add(method);
	    }
	 
	 public void addConstructorInfo(ConstructorInfo declaredConstructors) {
		 contructors.add(declaredConstructors);
			
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

	public List<ConstructorInfo> getContructor() {
		return contructors;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setConstantes(List<String> constantes) {
		this.constantes = constantes;
	}

	public void setMethods(List<MethodInfo> methods) {
		this.methods = methods;
	}

	public void setContructor(List<ConstructorInfo> contructor) {
		this.contructors = contructor;
	}

	
	 
	    
}
