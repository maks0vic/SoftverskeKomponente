package com.komponente;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class DataRepositoryCustom extends Storage{
	
	public DataRepositoryCustom (String address) {
		super();
		this.adress = address;
	}
	
	public DataRepositoryCustom(String adress, List<MyFile> files, String storageType, int maxEntities) {
		super(adress, files, storageType, maxEntities);
	}
	
	
	@Override
	public void save() {
		try {        
			createConfig();	
			ArrayList <MyFile> files = (ArrayList<MyFile>) this.getFiles();
			System.out.println("kolko fajlova " + getFiles().size());
			ArrayList <Entity> pom = new ArrayList<Entity>();
			ArrayList <Entity> pomList = new ArrayList<Entity>();
			pom.addAll(getWorkingList());
			for (int i=0; i < files.size(); i++) {
				MyFile f = files.get(i);
				pom.addAll(f.getEntityList());
			}		
				int j = 0;
				int m = getMaxEntities();
				File u = new File(adress + "\\" + j + ".custom");
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(u.getAbsolutePath()));
				for (int i=0; i < pom.size(); i++) {
					pomList.add(pom.get(i));
					if ( i % m == m - 1) {
						j++;
						objectOutputStream.writeObject(pomList);
						System.out.println("pom lista size: " + pomList.size());
						objectOutputStream.close();
						pomList.clear();
						u = new File(adress + "\\" + j + ".custom");
						objectOutputStream = new ObjectOutputStream(new FileOutputStream(u.getAbsolutePath()));						
					}
				}
				objectOutputStream.writeObject(pomList);
				objectOutputStream.close();
				System.out.println("zavrsio save");
				System.out.println("kolko fajlova " + getFiles().size());
      
		}
		catch (Exception e ) {
			e.printStackTrace();
		}
	}

	@Override
	public void load() {
		try {
			File dir = new File(adress);
			File [] files = dir.listFiles(new FilenameFilter() {
			    @Override
			    public boolean accept(File dir, String name) {
			        return name.endsWith(".custom");
			    }
			});
			for (File jsonfile : files) {
				ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(jsonfile.getAbsolutePath()));
				List<Entity> enList = ((List<Entity>) objectInputStream.readObject());
				objectInputStream.close();
				System.out.println("enList size: " + enList.size());
				this.addFile(new MyFile("f",enList));			
			}		
			System.out.println("kolko fajlova " + getFiles().size());
			this.loadEntities();	
			System.out.println("zavrsio load");
			System.out.println(getWorkingList());
			System.out.println(getWorkingList().size());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Problem with loading document");
		}
	}

	@Override
	public boolean checkType(String s) {
		if (s.equals("CUSTOM")) return true;
		return false;
	}
	
	

}
