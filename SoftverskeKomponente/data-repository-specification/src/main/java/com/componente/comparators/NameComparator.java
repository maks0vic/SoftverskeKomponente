package com.componente.comparators;

import java.util.Comparator;

import com.komponente.Entity;

public class NameComparator implements Comparator<Entity>{

	@Override
	public int compare(Entity arg0, Entity arg1) {
		if(arg0.getName().compareTo(arg0.getName()) < 0)return -1;
		if(arg0.getName().compareTo(arg0.getName()) > 0)return 1;
		return 0;
	}

}
