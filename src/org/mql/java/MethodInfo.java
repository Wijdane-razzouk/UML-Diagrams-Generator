package org.mql.java;

import java.util.List;
import java.lang.reflect.Type;
public class MethodInfo {
	private String name;
	 Class<?> Type;
	 private String modifier;
	 Type[] parameterTypes;
	 
	public MethodInfo(String name, Class<?> type, String modifier, Type[] types) {
		super();
		this.name = name;
		Type = type;
		this.modifier = modifier;
		this.parameterTypes = types;
	}
	 
	
	 
	 
}
