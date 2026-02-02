package com.example.poetrybackend.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.poetrybackend.repository.PoemRepository;
import com.example.poetrybackend.model.Poem;

@Service
public class PoemService {
    PoemRepository poemRepository;

    public PoemService(PoemRepository poemRepository) {
        this.poemRepository = poemRepository;
    }

    public long getNoofPoems() {
        return poemRepository.count();
    }

    public boolean isEmpty() {
        return poemRepository.count() == 0;
    }

    public void addPoem(Poem poem) {
        poemRepository.save(poem);
    }

    public Poem getPoem(Long id) {
        System.out.println(id.toString());
        return poemRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public List<Poem> searchForPoems(String searchString, int page) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        Page<Poem> searchResults = poemRepository.findByTitleContainingIgnoreCaseOrPoetContainingIgnoreCase(searchString, searchString, pageable);
        long totalNoOfElements = searchResults.getTotalElements();
        List<Poem> searchResultContent = searchResults.getContent();
        if(searchResultContent.size() >= 1){
            searchResultContent.get(0).setTotalCount(totalNoOfElements);
            return searchResultContent;
        } else {
            return new ArrayList<Poem>();
        }
    }

    public List<Poem> getPoems(List<Long> id) {
        return poemRepository.findAllById(id);
    }

    public List<Poem> getRandomPoems(){
        Random random = new Random();
        Long noOfPoems = getNoofPoems();
        List<Long> randomNumbers = new ArrayList<Long>(Collections.nCopies(10, null));
        IntStream.range(0, 10).forEachOrdered(it -> {
            randomNumbers.set(it, random.nextLong(noOfPoems));
        });
        return getPoems(randomNumbers);
    }

    public List<Poem> getFavouritePoems(int page) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        Page<Poem> searchResults = poemRepository.findAllByIsBookmarked(1, pageable);
        long totalNoOfElements = searchResults.getTotalElements();
        List<Poem> searchResultContent = searchResults.getContent();

        if(searchResultContent.size() >= 1){
            searchResultContent.get(0).setTotalCount(totalNoOfElements);
            return searchResultContent;
        } else {
            return new ArrayList<Poem>();
        }
    }

    @Modifying
    @Transactional
    public Poem updateIsBookmarked(Long id, int isBookmarked){
        Poem updatedPoem = poemRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        updatedPoem.setIsBookmarked(isBookmarked);
        return poemRepository.save(updatedPoem);
    }

}
