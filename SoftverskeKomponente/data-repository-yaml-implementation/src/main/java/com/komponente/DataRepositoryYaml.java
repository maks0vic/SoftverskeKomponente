package com.komponente;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


public class DataRepositoryYaml extends Storage{

	private ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

	public DataRepositoryYaml(String adress) {
		super(adress);
	}
	
	public DataRepositoryYaml(String adress, List<MyFile> files, String storageType, int maxEntities) {
		super(adress, files, storageType, maxEntities);
	}

	@Override
	public void save(Object object) {
		
		try {			
			createConfig();			
			DataRepositoryYaml stor = (DataRepositoryYaml) object;
			ArrayList <MyFile> files = (ArrayList<MyFile>) stor.getFiles();
			for (int i=0; i < files.size(); i++) {
				MyFile f = files.get(i);
				File u = new File(adress + f.getFileName() + ".yaml");
				objectMapper.writeValue(u ,  f.getEntityList());												
			}			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Problem with saving document");
		}
		
	}

	@Override
	public void load() {
		try {						
			File dir = new File(adress);
			File [] files = dir.listFiles(new FilenameFilter() {
			    @Override
			    public boolean accept(File dir, String name) {
			        return name.endsWith(".yaml");
			    }
			});
			for (File yamlfile : files) {
				List<Entity> enList = objectMapper.readValue(yamlfile, new TypeReference<List<Entity>>() {});
				this.addFile(new MyFile("f", enList));
			    }			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Problem with loading document");
		}
		
	}

}
