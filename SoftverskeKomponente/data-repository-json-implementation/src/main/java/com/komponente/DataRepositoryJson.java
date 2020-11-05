package com.komponente;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;


public class DataRepositoryJson extends Storage{
	
	
	public DataRepositoryJson(String adress, List<com.komponente.File> files, String storageType) {
		super(adress, files, storageType);
	}

	ObjectMapper objectMapper = new ObjectMapper();

	public void save(String collection, Object object) {
		try {
			objectMapper.writeValue( new File("file.json"), object);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Problem with saving document");
		}
		
	//public void save(String collection, Object object) {
		/*try {
			List<Object> objects = objectMapper.readValue(new File(collection), new TypeReference<List<Object>>() {
			});
			objects.add(object);
			objectMapper.writeValue(new File(collection), objects);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Fail to save object to the storage.");
		}*/
	}
	
	public Storage load() {
		try {
			return (objectMapper.readValue("file.json", DataRepositoryJson.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Problem with loading document");
		}
		
	//public void save(String collection, Object object) {
		/*try {
			List<Object> objects = objectMapper.readValue(new File(collection), new TypeReference<List<Object>>() {
			});
			objects.add(object);
			objectMapper.writeValue(new File(collection), objects);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Fail to save object to the storage.");
		}*/
	}

	/*@Override
	public <T> T findById(String collection, String id, Class<T> type) {
		return null; //brisi
		/*try {
			File file = new File(collection);
			JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, type);
			List<T> objects = objectMapper.readValue(file, javaType);
			return objects.stream().filter(object -> {
				JsonNode josnNode = objectMapper.valueToTree(object);
				return josnNode.get("id").asText().equals(id);
			}).findFirst().orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Storage error.");
		}
	}

	@Override
	public <T> List<T> findAll(String collection,Class<T> type) {
		return null;//brisi
		try {
			File file = new File(collection);
			JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, type);
			return objectMapper.readValue(file, javaType);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Storage error.");
		}
	}*/
}
