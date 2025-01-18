package org.mql.java.relations;

import java.util.ArrayList;
import java.util.List;

public class RelationClasse {
	    private List<RelationShip> allRelations;

	    public RelationClasse() {
	        allRelations = new ArrayList<>();
	    }

	    public void addRelation(Class<?> from, Class<?> to, RelationShipType type) {
	    	RelationShip relation = new RelationShip(from, to, type);
	        allRelations.add(relation);
	    }

	    public List<RelationShip> getRelations(Class<?> clazz) {
	        List<RelationShip> result = new ArrayList<>();
	        for (RelationShip relation : allRelations) {
	            if (relation.getSource() == clazz) {
	                result.add(relation);
	            }
	        }
	        return result;
	    }

	    public void displayAllRelations() {
	        for (RelationShip relation : allRelations) {
	            Class<?> from = relation.getSource();
	            Class<?> to = relation.getTarget();
	            RelationShipType type = relation.getType();

	            switch (type) {
	                case EXTENSION:
	                    System.out.println(from.getSimpleName() + " extends " + to.getSimpleName());
	                    break;

	                case IMPLEMENTATION:
	                    System.out.println(from.getSimpleName() + " implements " + to.getSimpleName());
	                    break;

	                case AGGREGATION:
	                    System.out.println(from.getSimpleName() + " aggregates " + to.getSimpleName());
	                    break;

	                case USAGE:
	                    System.out.println(from.getSimpleName() + " uses " + to.getSimpleName());
	                    break;

	                default:
	                    System.out.println("Unknown relationship: " + type);
	            }
	        }
	    }
	    public void displayRelationsForClass(Class<?> clazz) {

	        for (RelationShip relation : allRelations) {
	            if (relation.getSource().equals(clazz)) {
	                System.out.println("    - " + relation.getType() + " -> " + relation.getTarget().getName());
	            }
	        }
	    }

}
