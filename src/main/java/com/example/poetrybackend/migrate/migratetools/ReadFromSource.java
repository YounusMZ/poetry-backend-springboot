package com.example.poetrybackend.migrate.migratetools;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import com.example.poetrybackend.model.Poem;
import com.example.poetrybackend.repository.PoemRepository;

public class ReadFromSource {
    private final String filePath = "./PoetryData.csv";

    public void seedDBFromCSV(PoemRepository poemRepository) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> records = reader.readAll();
            String title = "";
            String poem = "";
            String poet = "";
            String tags = "";

            if(records.size() > poemRepository.count()){
                System.out.println("Seeding db from PoertryData.csv...");
                for (String[] record : records) {
                    for (int index = 0; index < record.length; index++) {
                        switch (index) {
                            case 1:
                                title = record[index];
                                break;
                            case 2:
                                poem = record[index];
                                break;
                            case 3:
                                poet = record[index];
                                break;
                            case 4:
                                tags = record[index];
                                break;
                            default:
                                break;
                        }
                    }
                    if (!title.isEmpty()){
                        poemRepository.save(new Poem(title, poem, poet, tags));
                        System.out.println(poet + " : " + title);
                    }
                }
                long noOfPoems = poemRepository.count();
                System.out.println(noOfPoems + " entries moved");
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}