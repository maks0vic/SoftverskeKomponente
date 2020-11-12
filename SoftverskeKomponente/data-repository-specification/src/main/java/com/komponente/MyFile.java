package com.komponente;

import java.util.ArrayList;
import java.util.List;

/**
 * File that holds entityList.
 * @author ivan
 *
 */
public class MyFile {
	private String fileName;
	private List<Entity> entityList;

	
	/**
	 * Class constructor.
	 */
	public MyFile() {
		super();
	}

	/**
	 * Class constructor specifying file name.
	 * @param fileName File name.
	 */
	public MyFile(String fileName) {
		super();
		this.fileName = fileName;
		entityList = new ArrayList<Entity>();
	}

	/**
	 * Class constructor specifying file name and entity list.
	 * @param fileName File name.
	 * @param entityList Entity list.
	 */
	public MyFile(String fileName, List<Entity> entityList) {
		super();
		this.fileName = fileName;
		this.entityList = entityList;
	}

	/**
	 * Adds entity to entity list.
	 * @param en Entity to add to list.
	 */
	public void addEntity (Entity en) {
		entityList.add(en);
	}

	/**
	 * Gets file name.
	 * @return File name to get.
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets file name.
	 * @param fileName File name to set.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets entity list.
	 * @return Entity list to get.
	 */
	public List<Entity> getEntityList() {
		return entityList;
	}

	/**
	 * Sets entity list.
	 * @param entityList Entity list to set.
	 */
	public void setEntityList(List<Entity> entityList) {
		this.entityList = entityList;
	}
		
	
	
}
