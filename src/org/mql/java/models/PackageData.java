package org.mql.java.models;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.mql.java.ExplorerProjet;

public class PackageData {
	 private String name;
	    private List<Classe> classes;
	    private List<InterfaceData> interfaces;
	    private List<EnumerationData> enums;
	    private List<AnnotationData> annotations;

	    public PackageData(String name) {
	        this.name = name;
	        this.classes = new ArrayList<>();
	        this.interfaces = new ArrayList<>();
	        this.enums = new ArrayList<>();
	        this.annotations = new ArrayList<>();
	    }

	    public String getName() {
	        return name;
	    }

	    public void addClass(Classe classInfo) {
	        classes.add(classInfo);
	    }

	    public void addInterface(InterfaceData interfaceInfo) {
	        interfaces.add(interfaceInfo);
	    }

	    public void addEnum(EnumerationData enumInfo) {
	        enums.add(enumInfo);
	    }

	    public void addAnnotation(AnnotationData annotationInfo) {
	        annotations.add(annotationInfo);
	    }

		public List<Classe> getClasses() {
			return classes;
		}

		public List<InterfaceData> getInterfaces() {
			return interfaces;
		}

		public List<EnumerationData> getEnums() {
			return enums;
		}

		public List<AnnotationData> getAnnotations() {
			return annotations;
		}
	    

}
