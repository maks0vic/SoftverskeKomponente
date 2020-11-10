package projekat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.komponente.*;

import view.MainFrame;

public class Main {

	public static void main(String[] args){
		
	
		Map <String, String> enMapa1 = new HashMap<>();
		enMapa1.put("en1key1", "en1value1");
		enMapa1.put("en1key2", "en1value2");
		
		Map <String, String> enMapa2 = new HashMap<>();
		enMapa2.put("en2key1", "en2value1");
		enMapa2.put("en2key2", "en2value2");
		
		
		Entity en1 = new Entity("Entitet1", "001", enMapa1);
		Entity en2 = new Entity("Entitet2", "002", enMapa2);
		
		MyFile myFile1 = new MyFile("file1");
		myFile1.addEntity(en1);
		myFile1.addEntity(en2);
		
		MyFile myFile2 = new MyFile("file2");
		myFile2.addEntity(en1);
		myFile2.addEntity(en2);
		
		List<MyFile> myFileList = new ArrayList<>();
		myFileList.add(myFile1);
		myFileList.add(myFile2);
		DataRepositoryJson myStorage = new DataRepositoryJson("C:\\Users\\StefanMaksovic\\git\\repository\\SoftverskeKomponente\\test-projekat\\", myFileList, "JSON", 5);		
		myStorage.save(myStorage);

		
		String adresa = "C:\\Users\\StefanMaksovic\\git\\repository\\SoftverskeKomponente\\test-projekat\\";
		Storage storage = new DataRepositoryJson(adresa);
		storage.readConfig(adresa);
		storage.load();
		

		MainFrame.getInstance();
		
	}

}
