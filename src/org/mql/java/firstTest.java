package org.mql.java;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class firstTest {
	public static void main(String[] args) throws ClassNotFoundException, IOException {
        String path="C:\\Users\\hp\\MQL\\eclipse-workspace\\p02_generics\\bin";
        
        // Extraction des dossiers et des packages
        List<File> folders = ExplorerProjet.extractFoldersfromproject(path);

        for (File folder : folders) {
            System.out.println("Folder: " + folder.getName());
            
          
            List<String> packages =ExplorerProjet.extractPackages(path,folder.getAbsolutePath());
            
            for (String packageName : packages) {
                System.out.println("Package: " + packageName);
                
                // Récupération des classes dans chaque package
                List<Class<?>> classes = ExplorerProjet.getClassesFromPath(path, packageName);
                for (Class<?> clazz : classes) {
                	System.out.println(" -"+clazz.getName());

                }

            }
        }
    }
}
