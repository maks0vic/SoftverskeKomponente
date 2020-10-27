package projekat;

import com.komponente.DataRepository;
import com.komponente.DataRepositoryJson;

public class Main {

	public static void main(String[] args) {
		DataRepository repo = new DataRepositoryJson();
		repo.save(null, new TestModel("1", "1"));
	}

}
