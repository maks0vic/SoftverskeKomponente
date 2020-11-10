package com.komponente;


import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataRepositoryJson extends Storage{
	ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());	
	
	public DataRepositoryJson(String adress) {
		super(adress);
	}


	public DataRepositoryJson(String adress, List<MyFile> files, String storageType, int maxEntities) {
		super(adress, files, storageType, maxEntities);
	}

	
	public void save(Object object) {
		try {			
			createConfig();			
			DataRepositoryJson stor = (DataRepositoryJson) object;
			ArrayList <MyFile> files = (ArrayList<MyFile>) stor.getFiles();
			for (int i=0; i < files.size(); i++) {
				MyFile f = files.get(i);
				File u = new File(adress + f.getFileName() + ".json");
				objectMapper.writeValue(u ,  f.getEntityList());												
			}			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Problem with saving document");
		}
	}
	
	
	public void load() {
		try {						
			File dir = new File(adress);
			File [] files = dir.listFiles(new FilenameFilter() {
			    @Override
			    public boolean accept(File dir, String name) {
			        return name.endsWith(".json");
			    }
			});
			for (File jsonfile : files) {
				List<Entity> enList = objectMapper.readValue(jsonfile, new TypeReference<List<Entity>>() {});
				this.addFile(new MyFile(enList));
			    }			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Problem with loading document");
		}
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
