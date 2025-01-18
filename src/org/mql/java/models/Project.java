package org.mql.java.models;

import java.util.List;
import java.util.Vector;

//import org.mql.java.ExplorerProjet;

public class Project {
	private String name;
	private List<PackageData> packages;

	
	public Project(String name) {
		super();
		this.name = name;
//		this.packages = ExplorerProjet.allpackages;
		this.packages = new Vector<>();
	}

//
//	public void addPackage() {
//		for (String pack : packages) {
//			PackageData pk= new PackageData(pack);
//		}
//	}
	public void addPackage(PackageData packageinfo) {
		packages.add(packageinfo);
	}

	public String getName() {
		return name;
	}


	public List<PackageData> getPackages() {
		return packages;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setPackages(List<PackageData> packages) {
		this.packages = packages;
	}
	  public PackageData getPackage(String packageName) {
	        for (PackageData pkg : packages) {
	            if (pkg.getName().equals(packageName)) {
	                return pkg;
	            }
	        }
	        return null;
	    }


	 public void printStructure() {
        System.out.println("Project: " + name);
        for (PackageData pkg: packages) {
            pkg.getName();
        }
    }

	
}
		
