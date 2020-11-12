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

public abstract class Storage {
	protected String adress; 
	private String storageType;
	private int maxEntities;
	private List<MyFile> files;
	private List<Entity> workingList;

	public Storage() {
		super();
		files = new ArrayList<MyFile>();
		workingList = new ArrayList<Entity>();
	}

	public Storage(String adress) {
		super();
		this.adress = adress;
		files = new ArrayList<MyFile>();
		workingList = new ArrayList<Entity>();
	}
	

	public Storage(String adress, String storageType) {
		super();
		this.adress = adress;
		this.storageType = storageType;
		files = new ArrayList<MyFile>();
		workingList = new ArrayList<Entity>();
	}

	public Storage(String adress, List<MyFile> files, String storageType, int maxEntities) {
		super();
		this.adress = adress;
		this.files = files;
		this.storageType = storageType;
		this.maxEntities = maxEntities;
		workingList = new ArrayList<Entity>();
		//loadEntities();
	}
	
	public Entity getEntityByID(String id) {
		for(Entity e: workingList) {
			if(e.getID().equals(id))return e;
		}
		return null;
		
	}
	
	public void addEntity(String name, String id, Map<String, String> map, Map<String, Entity> enMap) {
		if(id.equals("") && !this.checkID(id))id = this.getGeneratedID();
		if(map == null)map = new HashMap<String, String>();
		if(enMap == null)enMap = new HashMap<String, Entity>();
		Entity e = new Entity(name, id, map, enMap);
		this.workingList.add(e);
	}
	
	public void addEntity(String name, String id) {
		if(id.equals("") && !this.checkID(id))id = this.getGeneratedID();
		Entity e = new Entity(name, id);
		this.workingList.add(e);
	}
	
	public void addEntity(String name, String id, Map<String, String> map) {
		if(id.equals("") && !this.checkID(id))id = this.getGeneratedID();
		if(map == null)map = new HashMap<String, String>();
		Entity e = new Entity(name, id, map);
		this.workingList.add(e);
	}

	
	public void readConfig (String adress) {
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
		this.storageType = config.get(0);
		this.maxEntities = Integer.parseInt(config.get(1));		
	}
	
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
	
	public void loadEntities ( ) {
		for (int i=0; i < files.size(); i++) {
			MyFile f = files.get(i);
			for (Entity e : f.getEntityList()) 
				workingList.add(e);
		}
	}
	
	public boolean checkID (String id) {
		for (int i=0; i<workingList.size(); i++) {
			if (id.equals(workingList.get(i).getID())) 
				return true;
		}
		return false;
	}
	
	private String getGeneratedID () {
		Random rand = new Random();
	    int upperbound = 100000;
	    int int_random = rand.nextInt(upperbound); 
	    if (!checkID("" + int_random))
	    	return "" + int_random;
	    else return getGeneratedID();
	}
	
	public void deleteEntityList(List<Entity> en) {
		workingList.removeAll(en);
		System.out.println("lista: " + en);
		/*for (int i=0; i<en.size(); i++) {
			Iterator itr = workingList.iterator(); 
	        while (itr.hasNext()) 
	        { 
	        	if (en.get(i).equals((Entity) itr.next()))
	        		System.out.println("en: " + en.get(i));
	        		System.out.println("it: " + (Entity) itr.next());
	        		itr.remove();  
	        } 
		}*/
	}
	
	public void deleteEntity(Entity en) {
		Iterator itr = workingList.iterator(); 
        while (itr.hasNext()) 
        { 
        	if (en.equals((Entity) itr.next()))
        		itr.remove();  
        } 
		
	}

	public abstract void save();
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
