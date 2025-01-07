package org.mql.java;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;



public class ExplorerProjet {


	static List<Class<?>> allclasses = new ArrayList<>();

    static List<Class<?>> allinterfaces = new ArrayList<>();
    static List<Class<?>> allenumerations= new ArrayList<>();
    static List<Class<?>> allannotations= new ArrayList<>();
    
	static List <String> allpackages = new ArrayList<>();
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

    public static List<String> extractPackages(String path,String packagePath) {
  
        File dir = new File(packagePath);
        File[] content = dir.listFiles();
        if (content != null) {
            for (File file : content) {
                if (file.isDirectory()) {
                    if (containsClassFiles(file)) {
                        String packageName = file.getAbsolutePath()
                            .replace(path, "")       
                            .replace(File.separator, ".")
                            .substring(1);  
                       
                        allpackages.add(packageName); 
                    }
                    ExplorerProjet.extractPackages(path,file.getPath());
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

    public static List<Class<?>> getClassesFromPath(String projectPath, String packageName) throws IOException, ClassNotFoundException {
        File directory = new File(projectPath + "/" + packageName.replace('.', '/'));
        if (!directory.exists()) {
            throw new IllegalArgumentException("Package path does not exist: " + directory.getAbsolutePath());
        }
         List<Class<?>> classes = new ArrayList<>();
               URL[] urls = {new File(projectPath).toURI().toURL()};
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

    public static void analyseClasses(Class<?> classe) {
        System.out.println("Class: " + classe.getName());
    	if (classe.isEnum()) {
    		allenumerations.add(classe);
    	}
    	else if (classe.isInterface()) {
    		allinterfaces.add(classe);
		} 
    	if (classe.isAnnotation()) {
    		allannotations.add(classe);
    	}
    	else {
    		allclasses.add(classe);
    	}
    }
    


    
}
        
    
