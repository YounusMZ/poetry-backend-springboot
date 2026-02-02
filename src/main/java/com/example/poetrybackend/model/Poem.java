package com.example.poetrybackend.model;

import jakarta.persistence.Column;
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
    private Long id;
    private String title;
    private String poem;
    private String poet;
    private String tags;
    @Column(columnDefinition = "INTEGER default 0")
    private int isBookmarked;
    @Column(columnDefinition = "INTEGER default 0")
    private long totalCount;

    protected Poem(){};

    public Poem(String title, String poem, String poet, String tags){
        this.title = title;
        this.poem = poem;
        this.poet = poet;
        this.tags = tags;
    }

    public Long getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getPoem(){
        return this.poem;
    }

    public String getPoet(){
        return this.poet;
    }

    public String getTags(){
        return this.tags;
    }

    public int getIsBookmarked(){
        return this.isBookmarked;
    }

    public Long getTotalCount(){
        return this.totalCount;
    }

    public void setTotalCount(long totalCount){
        this.totalCount = totalCount;
    }

    public void setIsBookmarked(int isBookmarked){
        this.isBookmarked = isBookmarked;
    }
}
