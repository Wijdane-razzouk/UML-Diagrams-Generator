package org.mql.java.models;

import java.lang.reflect.Constructor;
import java.util.List;

public class ConstructorInfo {
	private Constructor<?> constructor;

	
	public ConstructorInfo(Constructor<?> constructor) {
		super();
		this.constructor = constructor;
	}

	public Constructor<?> getName() {
		return constructor;
	}

	public void setName(Constructor<?> constructor) {
		this.constructor = constructor;
	}

	@Override
	public String toString() {
		return " "+ constructor;
	}
	
	
}
