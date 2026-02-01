package com.example.poetrybackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "poems")
public class Poem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String poem;
    private String poet;
    private String tags;
    private int isBookmarked;

    protected Poem(){};

    public Poem(String title, String poem, String poet, String tags, int isBookmarked){
        this.title = title;
        this.poem = poem;
        this.poet = poet;
        this.tags = tags;
        this.isBookmarked = isBookmarked;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPoem() {
        return poem;
    }

    public String getPoet() {
        return poet;
    }

    public int getIsBookmarked() {
        return isBookmarked;
    }
}
