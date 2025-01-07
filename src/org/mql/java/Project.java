package org.mql.java;

import java.util.List;

public class Project {
	private String name;
	private List<String> packages= ExplorerProjet.allpackages;

	
	public void addPackage() {
		for (String pack : packages) {
			PackageData pk= new PackageData(pack);
		}
	}
	
}
		
