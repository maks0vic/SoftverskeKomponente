package projekat;

import java.io.File;
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
		enMapa1.put("limun", "zuta");
		
		Map <String, String> enMapa2 = new HashMap<>();
		enMapa2.put("jabuka", "crvena");
		
		Map <String, String> enMapa3 = new HashMap<>();
		enMapa3.put("ananas", "narandzasta");
		
		Map <String, String> enMapa4 = new HashMap<>();
		enMapa4.put("kupus", "zelena");
		
		Map <String, String> enMapa5 = new HashMap<>();
		enMapa5.put("banana", "zuta");
		
		Map <String, String> enMapa6 = new HashMap<>();
		enMapa6.put("jagoda", "crvena");
		
		Map <String, String> enMapa7 = new HashMap<>();
		enMapa7.put("pomorandza", "narandzasta");
		
		Map <String, String> enMapa8 = new HashMap<>();
		enMapa8.put("mango", "zelena");
			
		Entity en1 = new Entity("Stefan", "001", enMapa1);
		Entity en2 = new Entity("Ivan", "002", enMapa2);
		Entity en3 = new Entity("Sanja", "003", enMapa3);
		Entity en4 = new Entity("Lazar", "004", enMapa4);
		Entity en5 = new Entity("Matija", "005", enMapa5);
		Entity en6 = new Entity("Stole", "006", enMapa6);
		Entity en7 = new Entity("Fica", "007", enMapa7);
		Entity en8 = new Entity("Majkic", "008", enMapa8);
		
		//**//
		
		/*Map <String, Entity> enMapa3 = new HashMap<>();
		enMapa3.put("en3", en3);
		enMapa3.put("en4", en4);*/
		

		
		MyFile myFile1 = new MyFile("file1");
		myFile1.addEntity(en1);
		myFile1.addEntity(en2);
		myFile1.addEntity(en3);
		myFile1.addEntity(en4);
		myFile1.addEntity(en5);
		myFile1.addEntity(en6);
		myFile1.addEntity(en7);
		myFile1.addEntity(en8);
		
		List<MyFile> myFileList = new ArrayList<>();
		myFileList.add(myFile1);
		//myFileList.add(myFile2);
		
	//	File pom = new File("");
	//	String saveAddress = pom.getAbsolutePath();
	//	String loadAddress = "C:\\Users\\StefanMaksovic\\git\\repository\\data-repository-specification\\SoftverskeKomponente\\test-projekat\\";
		
	//	DataRepositoryJson myStorage = new DataRepositoryJson(saveAddress, myFileList, "JSON", 3);		
	//	myStorage.save(myStorage);
		
		/*DataRepositoryJson newStorage = new DataRepositoryJson(loadAddress);
		newStorage.readConfig(newStorage.getAdress());
		newStorage.load();*/

		
		MainFrame.getInstance();
	}

}
