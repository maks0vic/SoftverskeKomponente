package com.komponente;

import java.util.List;

public abstract class Storage {
	private String adress; 
	private String storageType;
	private List<File> files;
	

	
	public Storage(String adress, List<File> files, String storageType) {
		super();
		this.adress = adress;
		this.files = files;
		this.storageType = storageType;
	}


	public abstract void save(String collection, Object object);
	
	
	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public String getStorageType() {
		return storageType;
	}

	public void setStorageType(String storageType) {
		this.storageType = storageType;
	} 
	
	
	
	
	
}
