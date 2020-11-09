package com.komponente;


import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;


public class DataRepositoryJson extends Storage{
	ObjectMapper objectMapper = new ObjectMapper();	
	
	public DataRepositoryJson(String adress) {
		super(adress);
	}


	public DataRepositoryJson(String adress, List<MyFile> files, String storageType, int maxEntities) {
		super(adress, files, storageType, maxEntities);
	}

	
	public void save(String collection, Object object) {
		try {			
			createConfig();			
			DataRepositoryJson stor = (DataRepositoryJson) object;
			ArrayList <MyFile> files = (ArrayList<MyFile>) stor.getFiles();
			for (int i=0; i < files.size(); i++) {
				MyFile f = files.get(i);
				objectMapper.writeValue( new File(adress + f.getFileName() + ".json"),  f);				
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
				MyFile mf = objectMapper.readValue(jsonfile, MyFile.class);
				this.addFile(mf);
			    }			
			System.out.println("procitao sve lepo" + this.getFiles().size());
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
