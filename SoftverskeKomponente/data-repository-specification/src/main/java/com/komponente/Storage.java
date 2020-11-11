package com.komponente;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.componente.comparators.IDComparator;
import com.componente.comparators.NameComparator;
import com.componente.comparators.Order;
import com.componente.comparators.SortBy;
import com.komponente.search.SearchEnum;

public abstract class Storage {
	protected String adress; 
	private String storageType;
	private int maxEntities;
	private List<MyFile> files;
	private List<Entity> workingList;

	public Storage() {
		super();
	}

	public Storage(String adress) {
		super();
		this.adress = adress;
		files = new ArrayList<MyFile>();
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
		System.out.println(adress);
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

	public abstract void save(Object object);
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

	public void sortWorkingList(SortBy sort, Order order) {
		if(sort.equals(SortBy.NAME)) {
			Collections.sort(workingList, new NameComparator());
			if(order.equals(Order.DESC))Collections.reverse(workingList);
		}
		if(sort.equals(SortBy.ID)) {
			Collections.sort(workingList, new IDComparator());
			if(order.equals(Order.DESC))Collections.reverse(workingList);
		}
	}
	
	public List<Entity> searchByName(SearchEnum searchEnum, String valueForSearch){
		List<Entity> toReturn = new ArrayList<>();
		if(workingList.isEmpty())return null;
		
		if(searchEnum.equals(SearchEnum.AT_THE_BEGINING)) {
			for(Entity e: workingList) {
				if(e.getName().startsWith(valueForSearch)) {
					if(!toReturn.contains(e))toReturn.add(e);
				}
			}
		}else if(searchEnum.equals(SearchEnum.AT_THE_END)) {
			for(Entity e: workingList) {
				if(e.getName().endsWith(valueForSearch)) {
					if(!toReturn.contains(e))toReturn.add(e);
				}
			}
		}else if(searchEnum.equals(SearchEnum.SUBSTRING)) {
			for(Entity e: workingList) {
				if(e.getName().contains(valueForSearch)) {
					if(!toReturn.contains(e))toReturn.add(e);
				}
			}
		}else if(searchEnum.equals(SearchEnum.EQUAL_AS)) {
			for(Entity e: workingList) {
				if(e.getName().equals(valueForSearch)) {
					if(!toReturn.contains(e))toReturn.add(e);
				}
			}
		}
		
		return toReturn;
	}
	
	public List<Entity> searchByID(SearchEnum searchEnum, String valueForSearch){
		List<Entity> toReturn = new ArrayList<>();
		if(workingList.isEmpty())return null;
		
		if(searchEnum.equals(SearchEnum.AT_THE_BEGINING)) {
			for(Entity e: workingList) {
				if(e.getID().startsWith(valueForSearch)) {
					if(!toReturn.contains(e))toReturn.add(e);
				}
			}
		}else if(searchEnum.equals(SearchEnum.AT_THE_END)) {
			for(Entity e: workingList) {
				if(e.getID().endsWith(valueForSearch)) {
					if(!toReturn.contains(e))toReturn.add(e);
				}
			}
		}else if(searchEnum.equals(SearchEnum.SUBSTRING)) {
			for(Entity e: workingList) {
				if(e.getID().contains(valueForSearch)) {
					if(!toReturn.contains(e))toReturn.add(e);
				}
			}
		}else if(searchEnum.equals(SearchEnum.EQUAL_AS)) {
			for(Entity e: workingList) {
				if(e.getID().equals(valueForSearch)) {
					if(!toReturn.contains(e))toReturn.add(e);
				}
			}
		}
		
		return toReturn;
	}
	
	public List<Entity> searchByValueInMap(String key, SearchEnum searchEnum, String valueForSearch){
		List<Entity> toReturn = new ArrayList<>();
		if(workingList.isEmpty())return null;
		
		if(searchEnum.equals(SearchEnum.AT_THE_BEGINING)) {
			for(Entity e: workingList) {
				if(e.getID().startsWith(valueForSearch)) {
					if(!toReturn.contains(e))toReturn.add(e);
				}
			}
		}else if(searchEnum.equals(SearchEnum.AT_THE_END)) {
			for(Entity e: workingList) {
				if(e.getID().endsWith(valueForSearch)) {
					if(!toReturn.contains(e))toReturn.add(e);
				}
			}
		}else if(searchEnum.equals(SearchEnum.SUBSTRING)) {
			for(Entity e: workingList) {
				if(e.getID().contains(valueForSearch)) {
					if(!toReturn.contains(e))toReturn.add(e);
				}
			}
		}else if(searchEnum.equals(SearchEnum.EQUAL_AS)) {
			for(Entity e: workingList) {
				if(e.getID().equals(valueForSearch)) {
					if(!toReturn.contains(e))toReturn.add(e);
				}
			}
		}
		
		
		return toReturn;
	}
	
	
}
