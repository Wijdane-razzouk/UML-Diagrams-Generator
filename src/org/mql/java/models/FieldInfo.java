package org.mql.java.models;

public class FieldInfo {
	private String modifier;
	private String type;
	private String name;
	public FieldInfo(String modifier, String type, String name) {
		super();
		this.modifier = modifier;
		this.type = type;
		this.name = name;
	}
	public String getModifier() {
		return modifier;
	}
	public String getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
	    return modifier + " " + type + " " + name;
	}

	

}
