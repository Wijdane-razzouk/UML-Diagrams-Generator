package org.mql.java.relations;

import org.mql.java.models.PackageData;

import java.lang.reflect.Type;

import org.mql.java.models.Classe;
import org.mql.java.models.FieldInfo;
import org.mql.java.models.MethodInfo;


public class RelationAnalyse {
    private RelationClasse relationClasse;

    public RelationAnalyse() {
        this.relationClasse = new RelationClasse();
    }

    public void analyzeRelations(PackageData packageData) {
        for (Classe clazz : packageData.getClasses()) {
            Class<?> javaClass = clazz.getJavaClass();
            Class<?> superclass = clazz.getSuperclass();
            if (superclass != null) {
                relationClasse.addRelation(javaClass, superclass, RelationShipType.EXTENSION);
            }
            for (Class<?> iface : clazz.getInterfaces()) {
                relationClasse.addRelation(javaClass, iface, RelationShipType.IMPLEMENTATION);
            }
            analyzeFieldsAndMethods(clazz);
        }
    }

    private void analyzeFieldsAndMethods(Classe clazz) {
        for (FieldInfo field : clazz.getFields()) {
            Class<?> fieldType;
            try {
                fieldType = Class.forName(field.getType()); 
                relationClasse.addRelation(clazz.getJavaClass(), fieldType, RelationShipType.AGGREGATION);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    
        for (MethodInfo method : clazz.getMethods()) {
            for (Type paramType : method.getParameterTypes()) {
                Class<?> paramClass = (Class<?>) paramType; 
                relationClasse.addRelation(clazz.getJavaClass(), paramClass, RelationShipType.USAGE);
            }
        }
    }

    public void displayRelations() {
        relationClasse.displayAllRelations();
    }
}
