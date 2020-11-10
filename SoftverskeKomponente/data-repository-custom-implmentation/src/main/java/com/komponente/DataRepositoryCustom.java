package com.komponente;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class DataRepositoryCustom extends Storage{
	
	public DataRepositoryCustom () {
		super();
	}
	
	public DataRepositoryCustom(String adress, List<MyFile> files, String storageType, int maxEntities) {
		super(adress, files, storageType, maxEntities);
	}

	public String prepare(List<Entity> el) {
		String str = "";
		
		for (Entity e : el) {
			str += e.toString() + "\n";
		}		
		return str;
	}
	
	
	@Override
	public void save(Object object) {
		try {
			  DataRepositoryCustom drc = (DataRepositoryCustom) object;
		      List<MyFile> filovi = new ArrayList<MyFile>();
		      filovi = drc.getFiles();
		      for (MyFile f : filovi) {
				  File fil = new File(f.getFileName() + ".txt");
			      FileWriter myWriter = new FileWriter(fil);				  
		    	  myWriter.write(prepare(f.getEntityList()));
		    	  myWriter.close();
		      }		      
		}
		catch (Exception e ) {
			e.printStackTrace();
		}
	}

	@Override
	public void load() {
		
	}
	
	

}
