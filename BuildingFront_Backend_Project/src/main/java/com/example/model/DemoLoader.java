package com.example.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


@Component
public class DemoLoader implements CommandLineRunner{

	
	private final Repo repository; 
	
	@Autowired
	public DemoLoader(Repo repository) {
		this.repository = repository;
	}
	
	
	@Override
	public void run(String... strings) throws Exception {
		 this.repository.save(new Contact("Ray", "Culp", "culpr@icloud.com")) ; 
		 
	}
	
	
}
