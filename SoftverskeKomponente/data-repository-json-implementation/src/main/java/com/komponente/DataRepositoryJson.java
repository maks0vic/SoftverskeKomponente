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
			this.loadEntities();			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Problem with loading document");
		}
	}
}
