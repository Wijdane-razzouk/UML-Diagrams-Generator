package org.mql.java;

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
	
}
