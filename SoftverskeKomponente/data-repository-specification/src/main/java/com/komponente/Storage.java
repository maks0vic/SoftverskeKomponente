package com.komponente;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public abstract class Storage {
	protected String adress; 
	private String storageType;
	private int maxEntities;
	private List<MyFile> files;
	private List<Entity> workingList;


	
	public Storage(String adress) {
		super();
		this.adress = adress;
	}

	public Storage(String adress, List<MyFile> files, String storageType, int maxEntities) {
		super();
		this.adress = adress;
		this.files = files;
		this.storageType = storageType;
		this.maxEntities = maxEntities;
		//loadEntities();
	}
	
	public void readConfig (String adress) {
		ArrayList <String> config = new ArrayList<String>(); 	
		try (BufferedReader br = new BufferedReader(new FileReader(adress+"config.txt"))) {			
			String st; 			
			while ((st = br.readLine()) != null) {
			  config.add(st);
			}
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		
		this.storageType = config.get(0);
		this.maxEntities = Integer.parseInt(config.get(1));		
	}
	
	public void createConfig() {
		try {
			File cnfg = new File("config.txt");
			cnfg.createNewFile();
			
		      FileWriter myWriter = new FileWriter("config.txt");
		      myWriter.write(storageType + "\n");
		      myWriter.write("" + maxEntities);
		      myWriter.close();
		}
		catch (Exception e ) {
			e.printStackTrace();
		}
		
	}
	
	public void loadEntities ( ) {
		for (int i=0; i < files.size(); i++) {
			MyFile f = files.get(i);
			for (Entity e : f.getEntityList()) 
				workingList.add(e);
		}
	}

	public abstract void save(String collection, Object object);
	public abstract void load();
	
	public void addFile(MyFile mf) {
		files.add(mf);
	}
	
	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public List<MyFile> getFiles() {
		return files;
	}

	public void setFiles(List<MyFile> files) {
		this.files = files;
	}

	public String getStorageType() {
		return storageType;
	}

	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}

	public int getMaxEntities() {
		return maxEntities;
	}

	public void setMaxEntities(int maxEntities) {
		this.maxEntities = maxEntities;
	}

	public List<Entity> getWorkingList() {
		return workingList;
	}

	public void setWorkingList(List<Entity> workingList) {
		this.workingList = workingList;
	} 
	
	
	
	
	
}
