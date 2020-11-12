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

	public void save() {
		try {			
			createConfig();			
			DataRepositoryJson stor = (DataRepositoryJson)this;
			ArrayList <MyFile> files = (ArrayList<MyFile>) stor.getFiles();
			ArrayList <Entity> pom = new ArrayList<Entity>();
			ArrayList <Entity> pomList = new ArrayList<Entity>();
			for (int i=0; i < files.size(); i++) {
				MyFile f = files.get(i);
				pom.addAll(f.getEntityList());
			}
			int j = 0;
			int m = getMaxEntities();
			File u = new File(adress + "\\" + j + ".json");
			for (int i=0; i < pom.size(); i++) {
				pomList.add(pom.get(i));
				if ( i % m == m - 1) {
					j++;
					objectMapper.writerWithDefaultPrettyPrinter().writeValue(u , pomList);
					pomList.clear();
					u = new File(adress + "\\" + j + ".json");
				}
				objectMapper.writerWithDefaultPrettyPrinter().writeValue(u , pomList);
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
				this.addFile(new MyFile("f",enList));
			
			}		
			this.loadEntities();			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Problem with loading document");
		}
	}
}
