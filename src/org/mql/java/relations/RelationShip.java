package org.mql.java.relations;

public class RelationShip {
    private Class<?> source;
    private Class<?> target; 
    private RelationShipType type; 

    public RelationShip(Class<?> source, Class<?> target, RelationShipType type) {
        this.source = source;
        this.target = target;
        this.type = type;
    }

    public Class<?> getSource() {
        return source;
    }

    public Class<?> getTarget() {
        return target;
    }

    public RelationShipType getType() {
        return type;
    }
}
