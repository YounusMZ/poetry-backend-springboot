package com.example.poetrybackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.poetrybackend.repository.PoemRepository;
import com.example.poetrybackend.migrate.migratetools.ReadFromSource;
import com.example.poetrybackend.model.Poem;;

@SpringBootApplication
public class PoetryBackend {
	public static final Logger logger = LoggerFactory.getLogger(PoetryBackend.class);
	public static void main(String[] args) {
		SpringApplication.run(PoetryBackend.class, args);
	}

	@Bean
	public CommandLineRunner test() {
		return (args) -> {
			//poemRepository.save(new Poem("title", "gdsgdsgds", "Me", "gdfg", 0));
			//System.out.print(poemRepository.findAll());
			//System.out.println(poemRepository.count());
			//logger.info(poemRepository.findById(0).toString());
			//new ReadFromSource().read(poemRepository);
		};
	}
}
