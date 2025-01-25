package org.mql.java.models;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;

public class MethodInfo {
	private String name;
	Class<?> Type;
	private String modifier;
	Type[] parameterTypes;

	public MethodInfo(String name, Class<?> type, String string, Type[] types) {
		super();
		this.name = name;
		Type = type;
		this.modifier = string;
		this.parameterTypes = types;
	}

	

	public String getName() {
		return name;
	}

	public Class<?> getType() {
		return Type;
	}

	public String getModifier() {
		return modifier;
	}

	public Type[] getParameterTypes() {
		return parameterTypes;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(Class<?> type) {
		Type = type;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public void setParameterTypes(Type[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	@Override
	public String toString() {
	    StringBuilder build = new StringBuilder();
	    build.append(modifier).append(" ").append(Type).append(" ").append(name).append("(");
	    for (int i = 0; i < parameterTypes.length; i++) {
	        build.append(parameterTypes[i].getTypeName());
	        if (i < parameterTypes.length - 1) {
	            build.append(", ");  
	        }
	    }
	    build.append(")");
	    return build.toString();
	}

}
