package com.komponente;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Entity, has name, id, map for simple properties and map for entity properties.
 * @author ivan
 *
 */
public class Entity {
	private String name;
	private String ID; 
	private Map <String, String> mapa;
	private Map <String, Entity> enMapa;
	
	
	/**
	 * Class constructor.
	 */
	public Entity() {
		super();
	}

	/**
	 * Class constructor specifying entity name and entity id.
	 * @param name Entity name.
	 * @param ID Entity id.
	 */
	public Entity(String name, String ID) {
		super();
		this.name = name;
		this.ID = ID;
		mapa = new HashMap<>();
		enMapa = new HashMap<>();
	}

	/**
	 * Class constructor specifying entity name, entity id and key value map of simple properties.
	 * @param name Entity name.
	 * @param iD Entity id.
	 * @param mapa Simple property map.
	 */
	public Entity(String name, String iD, Map<String, String> mapa) {
		super();
		this.name = name;
		this.ID = iD;
		this.mapa = mapa;
		enMapa = new HashMap<>();
	}
	
	/**
	 * Class constructor specifying entity name, entity id, simple property map and entity property map.
	 * @param name Entity name.
	 * @param iD Entity id.
	 * @param mapa Simple property map.
	 * @param enMapa Entity property map.
	 */
	public Entity(String name, String iD, Map<String, String> mapa, Map<String, Entity> enMapa) {
		super();
		this.name = name;
		ID = iD;
		this.mapa = mapa;
		this.enMapa = enMapa;
	}

	/**
	 * Adds string to simple property map.
	 * @param key Key.
	 * @param value Value.
	 */
	public void addStringToMap (String key, String value) {
		mapa.put(key,value);
	}
	
	
	/**
	 * Adds entity to simple property map.
	 * @param key Key.
	 * @param en Entity to add.
	 */
	public void addEntityToMap (String key, Entity en) {
		enMapa.put(key, en);
	}
	
	/**
	 * Gets entity name.
	 * @return Entity name to get.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets entity name.
	 * @param name Entity name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets entity id.
	 * @return Entity id to get.
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Sets entity id.
	 * @param iD Entity id to set.
	 */
	public void setID(String iD) {
		ID = iD;
	}

	/**
	 * Gets simple property map.
	 * @return Simple property map to get.
	 */
	public Map<String, String> getMapa() {
		return mapa;
	}

	/**
	 * Sets simple property map.
	 * @param mapa Simple property map to set.
	 */
	public void setMapa(Map<String, String> mapa) {
		this.mapa = mapa;
	}
	
	
	/**
	 * Gets entity property map.
	 * @return Entity map to get.
	 */
	public Map<String, Entity> getEnMapa() {
		return enMapa;
	}

	
	/** Sets entity property map.
	 * @param Entity property map to set.
	 */
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