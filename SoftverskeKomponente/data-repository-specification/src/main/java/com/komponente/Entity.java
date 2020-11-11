package com.komponente;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Entity {
	private String name;
	private String ID; 
	private Map <String, String> mapa;
	private Map <String, Entity> enMapa;
	
	public Entity() {
		super();
	}

	public Entity(String name) {
		super();
		this.name = name;
	}

	public Entity(String name, String ID) {
		super();
		this.name = name;
		this.ID = ID;
		mapa = new HashMap<>();
		enMapa = new HashMap<>();
	}

	public Entity(String name, String iD, Map<String, String> mapa) {
		super();
		this.name = name;
		this.ID = iD;
		this.mapa = mapa;
		enMapa = new HashMap<>();
	}
	
	public Entity(String name, String iD, Map<String, String> mapa, Map<String, Entity> enMapa) {
		super();
		this.name = name;
		ID = iD;
		this.mapa = mapa;
		this.enMapa = enMapa;
	}

	public void addStringToMap (String key, String value) {
		mapa.put(key,value);
	}
	
	public void addEntityToMap (String key, Entity en) {
		enMapa.put(key, en);
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
	
	public Map<String, Entity> getEnMapa() {
		return enMapa;
	}

	public void setEnMapa(Map<String, Entity> enMapa) {
		this.enMapa = enMapa;
	}

	@Override
	public String toString() {
		String str = "";
		str += "name:" + name + ",";
		str += "id:" + ID + ",";
		str += "mapa:{" + this.mapa.toString() + "}";
		str += "enMapa:{" + this.enMapa.toString() + "}";
		return str;		
	}	
}