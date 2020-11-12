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
	public void save() {
		try {			
			createConfig();			
			DataRepositoryYaml stor = (DataRepositoryYaml) this;
			ArrayList <MyFile> files = (ArrayList<MyFile>) stor.getFiles();
			ArrayList <Entity> pom = new ArrayList<Entity>();
			ArrayList <Entity> pomList = new ArrayList<Entity>();
			for (int i=0; i < files.size(); i++) {
				MyFile f = files.get(i);
				pom.addAll(f.getEntityList());
			}
			int j = 0;
			int m = getMaxEntities();
			File u = new File(adress + "\\" + j + ".yaml");
			for (int i=0; i < pom.size(); i++) {
				pomList.add(pom.get(i));
				if ( i % m == m - 1) {
					j++;
					objectMapper.writerWithDefaultPrettyPrinter().writeValue(u , pomList);
					pomList.clear();
					u = new File(adress + "\\" + j + ".yaml");
				}
				objectMapper.writerWithDefaultPrettyPrinter().writeValue(u , pomList);
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
