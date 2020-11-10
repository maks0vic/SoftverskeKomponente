package com.komponente;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


public class DataRepositoryYaml extends Storage{

	private ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

	public DataRepositoryYaml(String adress) {
		super(adress);
		
	}

	@Override
	public void save(Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

}
