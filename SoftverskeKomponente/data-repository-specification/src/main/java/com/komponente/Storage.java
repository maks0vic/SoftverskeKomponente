package com.komponente;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.componente.comparators.IDComparator;
import com.componente.comparators.NameComparator;
import com.componente.comparators.Order;
import com.componente.comparators.SortBy;
import com.komponente.search.NestedSearchEnum;
import com.komponente.search.SearchEnum;


/**Repository that holds all files, entities, adress to file system, and max entities in file.
 * @author ivan
 *
 */
public abstract class Storage {
	protected String adress; 
	private String storageType;
	private int maxEntities;
	private List<MyFile> files;
	private List<Entity> workingList;
	
	
	
	/**
	 * Class constructor
	 */
	public Storage() {
		super();
		files = new ArrayList<MyFile>();
		workingList = new ArrayList<Entity>();
	}
	
	/**
	 * Class constructor specifying address to file system.
	 * @param adress An absolute address giving the location to repository in file system.
	 */
	public Storage(String adress) {
		super();
		this.adress = adress;
		files = new ArrayList<MyFile>();
		workingList = new ArrayList<Entity>();
	}
	
	/**
	 * Class constructor specifying address to file system and storage type.
	 * @param adress Absolute address giving the location to repository in file system.
	 * @param storageType Type of storage.
	 */
	public Storage(String adress, String storageType) {
		super();
		this.adress = adress;
		this.storageType = storageType;
		files = new ArrayList<MyFile>();
		workingList = new ArrayList<Entity>();
	}

	
	/**
	 * Class constructor specifying address to file system, storage type, max entities in file and list of storage files.
	 * @param adress	Absolute address giving the location to repository in file system.	
	 * @param files		List of files in storage
	 * @param storageType	Storage type
	 * @param maxEntities	Max entities in a single file
	 */
	public Storage(String adress, List<MyFile> files, String storageType, int maxEntities) {
		super();
		this.adress = adress;
		this.files = files;
		this.storageType = storageType;
		this.maxEntities = maxEntities;
		workingList = new ArrayList<Entity>();
		//loadEntities();
	}
	
	/**
	 * Gets entity by id.
	 * @param id Id of entity to get.
	 * @return Entity with equal id
	 */
	public Entity getEntityByID(String id) {
		for(Entity e: workingList) {
			if(e.getID().equals(id))return e;
		}
		return null;
		
	}
	
	/**
	 * Creates and adds entity to working list.
	 * @param name Name of entity
	 * @param id Id of entity
	 * @param map Simple key value map of entity
	 * @param enMap Nested entities key value map
	 */
	public void addEntity(String name, String id, Map<String, String> map, Map<String, Entity> enMap) {
		if(id.equals("") && !this.checkID(id))id = this.getGeneratedID();
		if(map == null)map = new HashMap<String, String>();
		if(enMap == null)enMap = new HashMap<String, Entity>();
		Entity e = new Entity(name, id, map, enMap);
		this.workingList.add(e);
	}
	
	/**
	 * Creates and adds entity to working list.
	 * @param name Name of entity
	 * @param id Id of entity
	 */
	public void addEntity(String name, String id) {
		if(id.equals("") && !this.checkID(id))id = this.getGeneratedID();
		Entity e = new Entity(name, id);
		this.workingList.add(e);
	}
	
	/**
	 * Creates and adds entity on working list.
	 * @param name Name of entity
	 * @param id Id of entity
	 * @param map Simple key value map of entity
	 */
	public void addEntity(String name, String id, Map<String, String> map) {
		if(id.equals("") && !this.checkID(id))id = this.getGeneratedID();
		if(map == null)map = new HashMap<String, String>();
		Entity e = new Entity(name, id, map);
		this.workingList.add(e);
	}
	
	/**
	 * Saves all entities from working list to files in repository.
	 */
	public abstract void save();
	/**
	 * Loads all entities from repository in file system to working list.
	 */
	public abstract void load();
	/**
	 * Checks if storage type is compatible with the repository type.
	 * @param s String that holds storage type.
	 * @return True if storage type is compatible and false if it is not.
	 */
	public abstract boolean checkType(String s);

	/**
	 * Reads configuration file of repository. Configuration file holds number of max entities in file and storage type.
	 * @param adress Absolute path to configuration file.
	 * @return True if storage type is compatible with repository and False if it is not.
	 */
	public boolean readConfig (String adress) {
		ArrayList <String> config = new ArrayList<String>(); 	
		try (BufferedReader br = new BufferedReader(new FileReader(adress+"\\config.txt"))) {			
			String st; 			
			while ((st = br.readLine()) != null) {
			  config.add(st);
			}
		}	
		catch (Exception e) {
			e.printStackTrace();
		}		
		if ( checkType(config.get(0)) ) {
			this.storageType = config.get(0);
			this.maxEntities = Integer.parseInt(config.get(1));
			return true;
		}
		else {
			System.out.println("Type ne odgovara");
			return false;
		}
	}
	
	/**
	 * Creates configuration file. Configuration file holds number of max entities in file and storage type.
	 */
	public void createConfig() {
		try {
			File cnfg = new File(this.adress + "\\config.txt");
			cnfg.createNewFile();
			
		      FileWriter myWriter = new FileWriter(this.adress + "\\config.txt");
		      myWriter.write(storageType + "\n");
		      myWriter.write("" + maxEntities);
		      myWriter.close();
		}
		catch (Exception e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads entities from files to working list.
	 */
	public void loadEntities ( ) {
		for (int i=0; i < files.size(); i++) {
			MyFile f = files.get(i);
			System.out.println("file size u loadEn : " + f.getEntityList().size());
			for (Entity e : f.getEntityList()) 
				workingList.add(e);
		}
	}
	
	/**
	 * Checks if id is unique in storage.
	 * @param id String that holds value of id.
	 * @return True if id is not unique. False if id is unique.
	 */
	public boolean checkID (String id) {
		for (int i=0; i<workingList.size(); i++) {
			if (id.equals(workingList.get(i).getID())) 
				return true;
		}
		return false;
	}
	
	/**
	 * Gets auto-generated unique id.
	 * @return Returns auto-generated unique id.
	 */
	private String getGeneratedID () {
		Random rand = new Random();
	    int upperbound = 100000;
	    int int_random = rand.nextInt(upperbound); 
	    if (!checkID("" + int_random))
	    	return "" + int_random;
	    else return getGeneratedID();
	}
	
	
	/**
	 * Delete entities from working list.
	 * @param en List of entities to delete.
	 */
	public void deleteEntityList(List<Entity> en) {

		workingList.removeAll(en);
		
	}
	
	/**
	 * Deletes entity from working list.
	 * @param en Entity to delete.
	 */
	public void deleteEntity(Entity en) {
		if(workingList.contains(en))workingList.remove(en);
		
	}


	/**
	 * Adds file to storage files.
	 * @param mf A file to add to storage files.
	 */
	public void addFile(MyFile mf) {
		files.add(mf);
	}
	
	/**
	 * Gets the absolute address of repository in file system.
	 * @return The absolute address of repository in file system.
	 */
	public String getAdress() {
		return adress;
	}
 
	/**
	 * Sets the absolute address of repository in file system.
	 * @param adress The absolute address of repository in file system.
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}

	
	/**
	 * Gets files from storage.
	 * @return Files from storage.
	 */
	public List<MyFile> getFiles() {
		return files;
	}

	/**
	 * Sets file to storage.
	 * @param files Files to set to storage.
	 */
	public void setFiles(List<MyFile> files) {
		this.files = files;
	}

	
	/**
	 * Gets the storage type.
	 * @return Storage type.
	 */
	public String getStorageType() {
		return storageType;
	}

	/**
	 * Sets storage type.
	 * @param storageType Storage type to set.
	 */
	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}

	/**
	 * Gets number of max entities in a single file.
	 * @return Number of max entities in a single file.
	 */
	public int getMaxEntities() {
		return maxEntities;
	}

	/**
	 * Sets number of max entities in a single file.
	 * @param maxEntities Number of max entities in a single file to set.
	 */
	public void setMaxEntities(int maxEntities) {
		this.maxEntities = maxEntities;
	}

	/**
	 * Gets working list. Working list is a list of entities in storage.
	 * @return Working list.
	 */
	public List<Entity> getWorkingList() {
		return workingList;
	}

	/**
	 * Sets working list. Working list is a list of entities in storage.
	 * @param workingList Working List.
	 */
	public void setWorkingList(List<Entity> workingList) {
		this.workingList = workingList;
	} 

	
	/**
	 * Sort Working list by name or by id, ascending or descending.
	 * @param sort SortBy enumeration, specifies if the sort is by id or by name.
	 * @param order Order enumeration, specifies if the sort is ascending or descending.
	 */
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
	
	/**
	 * Search entities in working list by name. 
	 * @param searchEnum SearchEnum enumeration, specifies if the search value is to be found at the beginning of name,<br>
	 * at the end of name, to be a substring of name or to be equal to name.
	 * @param valueForSearch Value for search to.
	 * @return List of entities that match the condition.
	 */
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
	
	/**
	 * Search entities in working list by id.
	 * @param searchEnum SearchEnum enumeration, specifies if the search value is to be found at the beginning of id,<br>
	 * at the end of id, to be a substring of id or to be equal to id.
	 * @param valueForSearch Value for search to.
	 * @return List of entities that match the condition.
	 */
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
	
	
	/**
	 * Search entities in working list by simple map.
	 * @param key Key in map to search.
	 * @param searchEnum SearchEnum enumeration, specifies if the search value is to be found at the beginning of value in map,<br>
	 * at the end of value in map, to be a substring of value in map or to be equal to value in map.
	 * @param valueForSearch Value for search to.
	 * @return List of entities that match the condition.
	 */
	public List<Entity> searchByValueInSimplePropertyMap(String key, SearchEnum searchEnum, String valueForSearch){
		List<Entity> toReturn = new ArrayList<>();
		if(workingList.isEmpty())return null;
		
		for(Entity e: workingList) {
			if(e.getMapa().isEmpty())continue;
			if(!e.getMapa().containsKey(key))continue;
				if(searchEnum.equals(SearchEnum.AT_THE_BEGINING)) {
					
					if(e.getMapa().get(key).startsWith(valueForSearch))
						if(!toReturn.contains(e))toReturn.add(e);
					
				}else if(searchEnum.equals(SearchEnum.AT_THE_END)) {
					
					if(e.getMapa().get(key).endsWith(valueForSearch))
						if(!toReturn.contains(e))toReturn.add(e);
					
				}else if(searchEnum.equals(SearchEnum.SUBSTRING)) {
					
					if(e.getMapa().get(key).contains(valueForSearch))
						if(!toReturn.contains(e))toReturn.add(e);
					
				}else if(searchEnum.equals(SearchEnum.EQUAL_AS)) {
					
					if(e.getMapa().get(key).equals(valueForSearch))
						if(!toReturn.contains(e))toReturn.add(e);
				}
			}
		
		return toReturn;
		
	}
	
	
	
	/**
	 * 
	 * @param key Key in entity map to search to.
	 * @param nEnum NestedSearcEnum enumeration, specifies if we want to search name,<br> id or simple map of entity in an entiy map.
	 * @param searchEnum SearchEnum enumeration, specifies if the search value is to be found at the beginning of value,<br>
	 * at the end of value, to be a substring of value or to be equal to value.
	 * @param valueForSearch
	 * @param key2 Key in simple map of entity in an entity map to search.
	 * @return List of entities that match the condition.
	 */
	public List<Entity> searchInEntityMap(String key, NestedSearchEnum nEnum, SearchEnum searchEnum, String valueForSearch, String key2){
		List<Entity> toReturn = new ArrayList<>();
		if(workingList.isEmpty())return null;
		for(Entity e: workingList) {
			if(e.getEnMapa().isEmpty())continue;
			if(!e.getEnMapa().containsKey(key))continue;
			if(nEnum.equals(NestedSearchEnum.ID)) {
				
				if(searchEnum.equals(SearchEnum.AT_THE_BEGINING)) {
				
					if(e.getEnMapa().get(key).getID().startsWith(valueForSearch)) {
						if(!toReturn.contains(e))toReturn.add(e);
					}
				
				}else if(searchEnum.equals(SearchEnum.AT_THE_END)) {
					
					if(e.getEnMapa().get(key).getID().endsWith(valueForSearch)) {
						if(!toReturn.contains(e))toReturn.add(e);
					}
					
				}else if(searchEnum.equals(SearchEnum.SUBSTRING)) {
					
					if(e.getEnMapa().get(key).getID().contains(valueForSearch)) {
						if(!toReturn.contains(e))toReturn.add(e);
					}
					
				}else if(searchEnum.equals(SearchEnum.EQUAL_AS)) {
					
					if(e.getEnMapa().get(key).getID().equals(valueForSearch)) {
						if(!toReturn.contains(e))toReturn.add(e);
					}
					
				}
				
			}else if(nEnum.equals(NestedSearchEnum.NAME)) {
				
				if(searchEnum.equals(SearchEnum.AT_THE_BEGINING)) {
					
					if(e.getEnMapa().get(key).getName().startsWith(valueForSearch)) {
						if(!toReturn.contains(e))toReturn.add(e);
					}
				
				}else if(searchEnum.equals(SearchEnum.AT_THE_END)) {
					
					if(e.getEnMapa().get(key).getName().endsWith(valueForSearch)) {
						if(!toReturn.contains(e))toReturn.add(e);
					}
					
				}else if(searchEnum.equals(SearchEnum.SUBSTRING)) {
					
					if(e.getEnMapa().get(key).getName().contains(valueForSearch)) {
						if(!toReturn.contains(e))toReturn.add(e);
					}
					
				}else if(searchEnum.equals(SearchEnum.EQUAL_AS)) {
					
					if(e.getEnMapa().get(key).getName().equals(valueForSearch)) {
						if(!toReturn.contains(e))toReturn.add(e);
					}
					
				}
				
			}else if(nEnum.equals(NestedSearchEnum.MAP)) {
				if(key2 == null)continue;
				if(e.getEnMapa().get(key).getMapa().isEmpty())continue;
				if(!e.getEnMapa().get(key).getMapa().containsKey(key2))continue;
				
				if(searchEnum.equals(SearchEnum.AT_THE_BEGINING)) {
					
					if(e.getEnMapa().get(key).getMapa().get(key2).startsWith(valueForSearch)) {
						if(!toReturn.contains(e))toReturn.add(e);
					}
				
				}else if(searchEnum.equals(SearchEnum.AT_THE_END)) {
					
					if(e.getEnMapa().get(key).getMapa().get(key2).endsWith(valueForSearch)) {
						if(!toReturn.contains(e))toReturn.add(e);
					}
					
				}else if(searchEnum.equals(SearchEnum.SUBSTRING)) {
					
					if(e.getEnMapa().get(key).getMapa().get(key2).contains(valueForSearch)) {
						if(!toReturn.contains(e))toReturn.add(e);
					}
					
				}else if(searchEnum.equals(SearchEnum.EQUAL_AS)) {
					
					if(e.getEnMapa().get(key).getMapa().get(key2).equals(valueForSearch)) {
						if(!toReturn.contains(e))toReturn.add(e);
					}
				}
			}
		}
		return toReturn;
	}
	
	
}
