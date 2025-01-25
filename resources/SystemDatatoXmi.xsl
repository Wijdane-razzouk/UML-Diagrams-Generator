<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:uml="http://www.omg.org/spec/UML/20090901">
    
    <xsl:output method="xml" indent="yes"/>

    <xsl:template match="/">
        <XMI xmi.version="2.1" xmlns:xmi="http://www.omg.org/XMI" xmlns:uml="http://www.omg.org/spec/UML/20090901">
            <uml:Model name="{root/@name}" xmi:type="uml:Model">
                <xsl:apply-templates select="root/package"/>
            </uml:Model>
        </XMI>
    </xsl:template>
    
    <xsl:template match="package">
        <ownedElement xmi:type="uml:Package" name="{@name}">
            <xsl:apply-templates select="class | interface | enumeration"/>
        </ownedElement>
    </xsl:template>
    
    <xsl:template match="class">
        <ownedElement xmi:type="uml:Class" name="{@name}" visibility="public">
            <xsl:if test="@extends">
                <generalization general="{@extends}"/>
            </xsl:if>
            <xsl:apply-templates select="method | field"/>
        </ownedElement>
    </xsl:template>

    <xsl:template match="interface">
        <ownedElement xmi:type="uml:Interface" name="{@name}">
            <xsl:apply-templates select="method"/>
        </ownedElement>
    </xsl:template>
    
    <xsl:template match="enumeration">
        <ownedElement xmi:type="uml:Enumeration" name="{@name}" visibility="public">
            <xsl:apply-templates select="literal | method"/>
        </ownedElement>
    </xsl:template>
    
    <xsl:template match="literal">
        <ownedLiteral name="{@name}"/>
    </xsl:template>

    <xsl:template match="method">
        <ownedOperation name="{@name}" visibility="public" returnType="{@returnType}">
            <xsl:apply-templates select="parameter | exception"/>
        </ownedOperation>
    </xsl:template>

    <xsl:template match="parameter">
        <ownedParameter name="{@name}" type="{@type}"/>
    </xsl:template>

    <xsl:template match="exception">
        <ownedException type="{@type}"/>
    </xsl:template>

    <xsl:template match="field">
        <ownedField name="{@name}" type="{@type}" visibility="public" isAbstract="{@isAbstract}"/>
    </xsl:template>
</xsl:stylesheet>
