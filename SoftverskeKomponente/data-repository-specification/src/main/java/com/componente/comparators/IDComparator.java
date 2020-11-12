package com.componente.comparators;

import java.util.Comparator;

import com.komponente.Entity;


/**
 * Comparator that compares two entities by id.
 * @author ivan
 *
 */
public class IDComparator implements Comparator<Entity>{

	@Override
	public int compare(Entity arg0, Entity arg1) {
		if(arg0.getID().compareTo(arg1.getID()) < 0)return -1;
		if(arg0.getID().compareTo(arg1.getID()) > 0)return 1;
		return 0;
	}


}
