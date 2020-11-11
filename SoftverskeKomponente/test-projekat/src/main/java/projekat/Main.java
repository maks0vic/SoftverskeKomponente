package projekat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.komponente.*;

import view.MainFrame;

import javax.swing.JFileChooser;
import java.io.File;   

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
		Entity en3 = new Entity("Entitet3", "003", enMapa2);
		Entity en4 = new Entity("Entitet4", "004", enMapa2);
		
		/*Map <String, Entity> enMapa3 = new HashMap<>();
		enMapa3.put("en3", en3);
		enMapa3.put("en4", en4);*/
		
		en1.addEntityToMap("zekaPeka", en3);
		en1.addEntityToMap("pekaZeka", en4);
		

		
		MyFile myFile1 = new MyFile("file1");
		myFile1.addEntity(en1);
		myFile1.addEntity(en2);
		
		/*MyFile myFile2 = new MyFile("file2");
		myFile2.addEntity(en2);
		myFile2.addEntity(en1);	*/	
		
		List<MyFile> myFileList = new ArrayList<>();
		myFileList.add(myFile1);
		//myFileList.add(myFile2);
		
		File pom = new File("");
		String saveAddress = pom.getAbsolutePath();
		String loadAddress = "C:\\Users\\StefanMaksovic\\git\\repository\\data-repository-specification\\SoftverskeKomponente\\test-projekat\\";
		
		DataRepositoryJson myStorage = new DataRepositoryJson(saveAddress, myFileList, "JSON", 5);		
		myStorage.save(myStorage);
		
		DataRepositoryJson newStorage = new DataRepositoryJson(loadAddress);
		//newStorage.readConfig(adresa);
		newStorage.load();

		
		System.out.println(newStorage.getWorkingList());
		
		MainFrame.getInstance();
	}

}
