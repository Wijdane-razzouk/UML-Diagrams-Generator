package org.mql.java;

import java.util.ArrayList;
import java.util.List;

public class InterfaceData {
	  private String name;
	  private List<MethodInfo> methods;
	public InterfaceData(String name) {
		super();
		this.name = name;
		this.methods = new ArrayList<>();
	}
	
	public void addMethod(MethodInfo method) {
        methods.add(method);
    }

	public String getName() {
		return name;
	}

	public List<MethodInfo> getMethods() {
		return methods;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMethods(List<MethodInfo> methods) {
		this.methods = methods;
	}
	  
	  
}
