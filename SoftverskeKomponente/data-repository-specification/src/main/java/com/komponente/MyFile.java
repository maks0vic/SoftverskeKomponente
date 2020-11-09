package com.komponente;

import java.util.ArrayList;
import java.util.List;

public class MyFile {
	private String fileName;
	private List<Entity> entityList;
	
	public MyFile(String fileName) {
		super();
		this.fileName = fileName;
		entityList = new ArrayList<Entity>();
	}

	public void addEntity (Entity en) {
		entityList.add(en);
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
