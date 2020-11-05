package projekat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.komponente.*;

public class Main {

	public static void main(String[] args) {
		
		
		Map <String, String> enMapa1 = new HashMap<>();
		enMapa1.put("en1key1", "en1value1");
		enMapa1.put("en1key2", "en1value2");
		
		Map <String, String> enMapa2 = new HashMap<>();
		enMapa2.put("en2key1", "en2value1");
		enMapa2.put("en2key2", "en2value2");
		
		
		Entity en1 = new Entity("Entitet1", "001", enMapa1);
		Entity en2 = new Entity("Entitet2", "002", enMapa2);
		
		File myFile = new File("file1");
		myFile.addEntity(en1);
		myFile.addEntity(en2);
		
		List<File> myFileList = new ArrayList<>();
		myFileList.add(myFile);
		DataRepositoryJson myStorage = new DataRepositoryJson("???", myFileList, "JSON");
		
		myStorage.save(null, myStorage);
		
		DataRepositoryJson jsonStorage = null ;
		jsonStorage = (DataRepositoryJson) myStorage.load();
		System.out.println(jsonStorage.getStorageType());
		
		
		
		
	}

}
