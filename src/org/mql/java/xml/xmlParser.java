package org.mql.java.xml;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;

public class xmlParser {

    public static void main(String[] args) throws Exception {
        File xmlFile = new File("resources/SystemData.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        document.getDocumentElement().normalize();

        parseProject(document);
    }

    private static void parseProject(Document document) {
        NodeList packageList = document.getElementsByTagName("package");
        
        for (int i = 0; i < packageList.getLength(); i++) {
            Node packageNode = packageList.item(i);
            if (packageNode.getNodeType() == Node.ELEMENT_NODE) {
                Element packageElement = (Element) packageNode;
                String packageName = packageElement.getAttribute("name");
                System.out.println("Package: " + packageName);
                parseClasses(packageElement);
            }
        }
    }

    private static void parseClasses(Element packageElement) {
        NodeList classList = packageElement.getElementsByTagName("class");

        for (int i = 0; i < classList.getLength(); i++) {
            Node classNode = classList.item(i);
            if (classNode.getNodeType() == Node.ELEMENT_NODE) {
                Element classElement = (Element) classNode;
                String className = classElement.getAttribute("name");
                String classModifiers = classElement.getAttribute("modifiers");
                String classExtends = classElement.getAttribute("extends");

                System.out.println("  Class: " + className);
                System.out.println("    Modifiers: " + classModifiers);
                System.out.println("    Extends: " + classExtends);

                parseMethods(classElement);
                parseConstructor(classElement);
                parseRelationships(classElement);
            }
        }
    }

    private static void parseMethods(Element classElement) {
        NodeList methodList = classElement.getElementsByTagName("method");

        for (int i = 0; i < methodList.getLength(); i++) {
            Node methodNode = methodList.item(i);
            if (methodNode.getNodeType() == Node.ELEMENT_NODE) {
                Element methodElement = (Element) methodNode;
                String methodName = methodElement.getAttribute("name");
                String methodModifiers = methodElement.getAttribute("modifiers");
                String methodReturnType = methodElement.getAttribute("returnType");

                System.out.println("    Method: " + methodName);
                System.out.println("      Modifiers: " + methodModifiers);
                System.out.println("      Return Type: " + methodReturnType);

                parseParameters(methodElement);
            }
        }
    }

    private static void parseParameters(Element methodElement) {
        NodeList parameterList = methodElement.getElementsByTagName("parameter");

        for (int i = 0; i < parameterList.getLength(); i++) {
            Node parameterNode = parameterList.item(i);
            if (parameterNode.getNodeType() == Node.ELEMENT_NODE) {
                Element parameterElement = (Element) parameterNode;
                String paramName = parameterElement.getAttribute("name");
                String paramType = parameterElement.getAttribute("type");

                System.out.println("      Parameter: " + paramName);
                System.out.println("        Type: " + paramType);
            }
        }
    }

    private static void parseConstructor(Element classElement) {
        NodeList constructorList = classElement.getElementsByTagName("constructor");

        for (int i = 0; i < constructorList.getLength(); i++) {
            Node constructorNode = constructorList.item(i);
            if (constructorNode.getNodeType() == Node.ELEMENT_NODE) {
                Element constructorElement = (Element) constructorNode;
                String constructorName = constructorElement.getAttribute("name");
                String constructorModifiers = constructorElement.getAttribute("modifiers");

                System.out.println("    Constructor: " + constructorName);
                System.out.println("      Modifiers: " + constructorModifiers);
            }
        }
    }

    private static void parseRelationships(Element classElement) {
        NodeList relationshipsList = classElement.getElementsByTagName("relationship");

        for (int i = 0; i < relationshipsList.getLength(); i++) {
            Node relationshipNode = relationshipsList.item(i);
            if (relationshipNode.getNodeType() == Node.ELEMENT_NODE) {
                Element relationshipElement = (Element) relationshipNode;
                String source = relationshipElement.getAttribute("source");
                String target = relationshipElement.getAttribute("target");
                String type = relationshipElement.getAttribute("type");

                System.out.println("    Relationship: " + source + " -> " + target);
                System.out.println("      Type: " + type);
            }
        }
    }

    private static void parseFields(Element classElement) {
        NodeList fieldList = classElement.getElementsByTagName("field");

        for (int i = 0; i < fieldList.getLength(); i++) {
            Node fieldNode = fieldList.item(i);
            if (fieldNode.getNodeType() == Node.ELEMENT_NODE) {
                Element fieldElement = (Element) fieldNode;
                String fieldName = fieldElement.getAttribute("name");
                String fieldModifiers = fieldElement.getAttribute("modifiers");
                String fieldType = fieldElement.getAttribute("type");

                System.out.println("    Field: " + fieldName);
                System.out.println("      Modifiers: " + fieldModifiers);
                System.out.println("      Type: " + fieldType);
            }
        }
    }
}
