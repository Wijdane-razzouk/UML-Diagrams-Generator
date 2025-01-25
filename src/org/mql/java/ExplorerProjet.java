package org.mql.java;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.mql.java.models.AnnotationData;
import org.mql.java.models.Classe;
import org.mql.java.models.EnumerationData;
import org.mql.java.models.InterfaceData;
import org.mql.java.models.PackageData;
import org.mql.java.models.Project;

public class ExplorerProjet {
	
	public static List<Class<?>> allclasses = new ArrayList<>();

	public static List<Class<?>> allinterfaces = new ArrayList<>();
	public static List<Class<?>> allenumerations = new ArrayList<>();
	public static List<Class<?>> allannotations = new ArrayList<>();

	public static List<String> allpackages = new ArrayList<>();

	
	public static String extractProjectNameUsingSplit(String path) {
        String[] segments = path.split("\\\\");
        
        if (segments.length >= 2) {
            return segments[segments.length - 2];
        } else {
            throw new IllegalArgumentException("Le chemin ne contient pas suffisamment de segments.");
        }
    }
	
	public static List<File> extractFoldersfromproject(String path) {
		File directory = new File(path);
		File[] files = directory.listFiles();
		List<File> allfolders = new Vector<>();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					allfolders.add(file);
				}
			}
		}
		return allfolders;
	}

	public static List<String> extractPackages(String path, String packagePath) {
		Project project=new Project(extractProjectNameUsingSplit(path), packagePath);
		File dir = new File(packagePath);
		File[] content = dir.listFiles();
		if (content != null) {
			for (File file : content) {
				if (file.isDirectory()) {
					if (containsClassFiles(file)) {
						String packageName = file.getAbsolutePath().replace(path, "").replace(File.separator, ".")
								.substring(1);
						PackageData pk = new PackageData(packageName);
						project.addPackage(pk);
						allpackages.add(packageName);
					}
					ExplorerProjet.extractPackages(path, file.getPath());
				}
			}
		}

		return allpackages;
	}

	private static boolean containsClassFiles(File directory) {
		File[] files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isFile() && file.getName().endsWith(".class")) {
					return true;
				}
			}
		}
		return false;
	}

	public static List<Class<?>> getClassesFromPath(String projectPath, String packageName)
			throws IOException, ClassNotFoundException {
		File directory = new File(projectPath + "/" + packageName.replace('.', '/'));
		if (!directory.exists()) {
			throw new IllegalArgumentException("Package path does not exist: " + directory.getAbsolutePath());
		}
		List<Class<?>> classes = new ArrayList<>();
		URL[] urls = { new File(projectPath).toURI().toURL() };
		try (URLClassLoader classLoader = new URLClassLoader(urls)) {
			for (File file : directory.listFiles()) {
				if (file.getName().endsWith(".class") && !file.getName().contains("$")) {
					String className = packageName + "." + file.getName().replace(".class", "");
					classes.add(Class.forName(className, true, classLoader));
				}
			}
		}
		return classes;
	}

	public static void analyseClasses(Class<?> classe, PackageData pk) {
        if (classe.isAnnotation()) {
            AnnotationData annotationData = new AnnotationData(classe);
            pk.addAnnotation(annotationData);

        } else if (classe.isEnum()) {
            EnumerationData enumData = new EnumerationData(classe);
            pk.addEnum(enumData);

        } else if (classe.isInterface()) {
            InterfaceData interfaceData = new InterfaceData(classe);
            pk.addInterface(interfaceData);

        } else {
            Classe classData = new Classe(classe);
            pk.addClass(classData);

        }
    }

}
