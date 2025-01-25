package org.mql.java.models;

import java.util.List;
import java.util.Vector;

//import org.mql.java.ExplorerProjet;

public class Project {
	private String name;
	private String path;
	private List<PackageData> packages;

	
	public Project(String name,String path) {
		super();
		this.name = name;
		this.path = path;
		this.packages = new Vector<>();
	}


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


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
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
		
