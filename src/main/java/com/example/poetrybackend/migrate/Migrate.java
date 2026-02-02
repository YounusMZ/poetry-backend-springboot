package com.example.poetrybackend.migrate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.poetrybackend.migrate.migratetools.ReadFromSource;
import com.example.poetrybackend.repository.PoemRepository;

@Component
public class Migrate implements CommandLineRunner {
    private PoemRepository poemRepository;

    public Migrate(PoemRepository poemRepository){
        this.poemRepository = poemRepository;
    }

    @Override
    public void run(String... args){
            System.out.println("Starting...");
            ReadFromSource readFromSource = new ReadFromSource();
            readFromSource.seedDBFromCSV(poemRepository);
            System.out.println(poemRepository.count() + " entries found. Running...");
    }
}
