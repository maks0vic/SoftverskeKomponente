package com.komponente;

import java.util.List;

public interface  DataRepository  {
	/**
	 * Save object in the specified collection of the storage.
	 * 
	 * @param collection name of the collection
	 * @param object data
	 */
	public void save(String collection, Object object);

	/**
	 * Get the object with the specified id.
	 * 
	 * @param collection name of the collection
	 * @param id id of the object we want to get
	 * @param type type of the object
	 * @return object with specified id
	 */
	public <T> T findById(String collection, String id, Class<T> type);

	/**
	 * Get all objects from specified collection
	 * 
	 * @param collection name of the collection
	 * @param type of objects
	 * @return list off objects from specified collection
	 */
	public <T> List<T> findAll(String collection, Class<T> type);
}
