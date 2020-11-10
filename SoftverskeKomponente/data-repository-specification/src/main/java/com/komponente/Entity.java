package com.komponente;

import java.util.HashMap;
import java.util.Map;

public class Entity {
	private String name;
	private String ID; 
	private Map <String, String> mapa;

	
	
	public Entity() {
		super();
	}


	public Entity(String name) {
		super();
		this.name = name;
		this.ID = getGeneratedID();
	}


	public Entity(String name, String ID) {
		super();
		this.name = name;
		this.ID = ID;
		mapa = new HashMap<>();
	}

	public Entity(String name, String ID, Map<String, String> mapa) {
		super();
		this.name = name;
		this.ID = ID;
		this.mapa = mapa;
	}
	
	public void addToMap (String key, String value) {
		mapa.put(key,value);
	}
	
	public String getName() {
		return name;
	}
	

	public void setName(String name) {
		this.name = name;
	}


	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}


	public Map<String, String> getMapa() {
		return mapa;
	}


	public void setMapa(Map<String, String> mapa) {
		this.mapa = mapa;
	}


	public boolean checkIsIdUnique () {
		return true;		
	}
	
	private String getGeneratedID () {
		return "default ID";
	}
	
	public void addEntity (String key, Entity en) {// ??????????????????????
		mapa.put(key, en.toString());
	}
	
}

/*
Map<String, Integer> hm 
= new HashMap<String, Integer>(); 

hm.put("a", new Integer(100)); 
hm.put("b", new Integer(200)); 
hm.put("c", new Integer(300)); 
hm.put("d", new Integer(400)); 

// Traversing through the map 
for (Map.Entry<String, Integer> me : hm.entrySet()) { 
System.out.print(me.getKey() + ":"); 
System.out.println(me.getValue()); 
} 
*/