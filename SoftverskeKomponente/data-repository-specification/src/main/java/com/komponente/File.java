package com.komponente;

import java.util.ArrayList;
import java.util.List;

public class File {
	private int maxEntities;
	private String fileName;
	private List<Entity> entityList;
	
	public File(String fileName) {
		super();
		this.fileName = fileName;
		entityList = new ArrayList<Entity>();
	}

	public void addEntity (Entity en) {
		entityList.add(en);
	}

	public int getMaxEntities() {
		return maxEntities;
	}

	public void setMaxEntities(int maxEntities) {
		this.maxEntities = maxEntities;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<Entity> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<Entity> entityList) {
		this.entityList = entityList;
	}
		
	
	
}
