package org.mql.java;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.List;

import org.mql.java.models.PackageData;
import org.mql.java.models.Project;
import org.mql.java.relations.RelationClasse;
import org.mql.java.relations.RelationShipType;
import org.mql.java.models.AnnotationData;
import org.mql.java.models.Classe;
import org.mql.java.models.ConstructorInfo;
import org.mql.java.models.EnumerationData;
import org.mql.java.models.MethodInfo;
import org.mql.java.models.FieldInfo;
import org.mql.java.models.InterfaceData;


public class firstTest {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        String path = "C:\\Users\\hp\\MQL\\eclipse-workspace\\p03_reflection_and_annotations\\bin";
        String projectName = ExplorerProjet.extractProjectNameUsingSplit(path);
        
        System.out.println("Nom du projet : " + projectName);
        List<File> folders = ExplorerProjet.extractFoldersfromproject(path);

        RelationClasse relationManager = new RelationClasse();
        
        for (File folder : folders) {
            System.out.println("Folder: " + folder.getName());
            Project project=new Project(projectName,path);
            List<String> packages = ExplorerProjet.extractPackages(path, folder.getAbsolutePath());

            for (String packageName : packages) {
                System.out.println("\nPackage: " + packageName);
                PackageData pk = new PackageData(packageName);

                List<Class<?>> classes = ExplorerProjet.getClassesFromPath(path, packageName);
                for (Class<?> clazz : classes) {
                    ExplorerProjet.analyseClasses(clazz, pk);
                    manageRelationsForClass(clazz, relationManager);
                }

               for (Classe classData : pk.getClasses()) {
                displayClassDetails(classData, relationManager);
            }

            // Affichage des interfaces
            for (InterfaceData interfaceData : pk.getInterfaces()) {
                displayInterfaceDetails(interfaceData, relationManager);
            }

            // Affichage des annotations
            for (AnnotationData annotationData : pk.getAnnotations()) {
                displayAnnotationDetails(annotationData);
            }

            // Affichage des énumérations
            for (EnumerationData enumData : pk.getEnums()) {
                displayEnumerationDetails(enumData);
            }

            }
        }
    }

    private static void displayClassDetails(Classe classData, RelationClasse relationManager) {
        System.out.println("=========================================================");
        Class<?> clazz = classData.getJavaClass();
        System.out.print("Class: " + clazz.getName()+" ");

        // Afficher la classe étendue
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null) {
            System.out.print("extends: " + superclass.getName()+" ");
        } 
          else {
            System.out.print("extends: java.lang.Object ");
        }
        
        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces.length > 0) {
            System.out.print("  implements: ");
            for (int i = 0; i < interfaces.length; i++) {
                System.out.print(interfaces[i].getName());
                if (i < interfaces.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        } else {
            System.out.println("");
        }

        // Afficher les agrégations
        System.out.println("Aggregates: ");
        for (FieldInfo field : classData.getFields()) {
            System.out.println("  - " + field.getType());
        }

        // Afficher les utilisations
        System.out.println("Uses: ");
        for (MethodInfo method : classData.getMethods()) {
            for (Type param : method.getParameterTypes()) {
                System.out.println("  - " + param.getTypeName());
            }
        }

        // Afficher le contenu

        System.out.println("  Annotations:");
        for (AnnotationData annotation : classData.getAnnotations()) {
        	displayAnnotationDetails(annotation);
		}
        
        System.out.println("  Fields:");
        for (FieldInfo field : classData.getFields()) {
            System.out.println("    - " + field);
        }

        System.out.println("  Methods:");
        for (MethodInfo method : classData.getMethods()) {
            System.out.println("    - " + method);
        }

        System.out.println("  Constructors:");
        for (ConstructorInfo constructor : classData.getConstructors()) {
            System.out.println("    - " + constructor);
        }
    }

    private static void displayInterfaceDetails(InterfaceData interfaceData, RelationClasse relationManager) {
        System.out.println("=========================================================");
        Class<?> interfaceClass = interfaceData.getJavaClass();
        System.out.println("Interface: " + interfaceData.getName());

        // Afficher les méthodes de l'interface
        System.out.println("Methods:");
        for (MethodInfo method : interfaceData.getMethods()) {
            System.out.println("  - " + method);
        }

        Class<?>[] superInterfaces = interfaceClass.getInterfaces();
        if (superInterfaces.length > 0) {
            System.out.print("Extends: ");
            for (int i = 0; i < superInterfaces.length; i++) {
                System.out.print(superInterfaces[i].getName());
                if (i < superInterfaces.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        } else {
            System.out.println("Extends: None");
        }
    }

    private static void displayAnnotationDetails(AnnotationData annotationData) {
    	System.out.println("=========================================================");
        System.out.println("Annotation: " + annotationData.getName());
        System.out.println("Modifiers: " + annotationData.getModifiers());
        System.out.println("Meta-Annotations: " + annotationData.getMetaAnnotations());
        System.out.println("Elements:");
        for (MethodInfo element : annotationData.getElements()) {
            System.out.println("  - " + element);
        }
     
        
    }

    private static void displayEnumerationDetails(EnumerationData enumData) {
        System.out.println("=========================================================");
        Class<?> enumClass = enumData.getJavaClass();
        System.out.println("Enumeration: " + enumClass.getName());

        // Afficher les constantes de l'énumération
        for (Object constant : enumClass.getEnumConstants()) {
            System.out.println("  - " + constant);
        }
        for (MethodInfo method : enumData.getMethods()) {
            System.out.println("        - Method: " + method);
        }
    }


    private static void manageRelationsForClass(Class<?> clazz, RelationClasse relationManager) {
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null) {
            relationManager.addRelation(clazz, superclass, RelationShipType.EXTENSION);
        }

        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> i : interfaces) {
            relationManager.addRelation(clazz, i, RelationShipType.IMPLEMENTATION);
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            relationManager.addRelation(clazz, field.getType(), RelationShipType.AGGREGATION);
        }

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            for (Class<?> param : method.getParameterTypes()) {
                relationManager.addRelation(clazz, param, RelationShipType.USAGE);
            }
        }
    }
}
