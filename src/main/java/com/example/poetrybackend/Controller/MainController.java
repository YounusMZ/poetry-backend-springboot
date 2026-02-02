package com.example.poetrybackend.Controller;

import java.util.List;
import java.util.Arrays;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

import com.example.poetrybackend.repository.PoemRepository;

import tools.jackson.databind.ObjectMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.example.poetrybackend.PoetryBackend;
import com.example.poetrybackend.model.BookmarkStatus;
import com.example.poetrybackend.model.Poem;
import com.example.poetrybackend.Service.PoemService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class MainController {

    PoemService poemService;

    public MainController(PoemService poemService, PoemRepository poemRepository){
        this.poemService = poemService;
    }

    @GetMapping("/poem/{id}")
    public Poem getPoem(@PathVariable String id) {
        return poemService.getPoem(Long.valueOf(id));
    }

    @GetMapping("/poems")
    public List<Poem> getPoems(@RequestParam(value = "id") String id) {
        String[] idStringSplit = id.split("[+]");
        List<Long> poemIds = Arrays.stream(idStringSplit).map((it) -> Long.valueOf(it)).toList();
        return poemService.getPoems(poemIds);
    }

    @GetMapping("/search")
    public List<Poem> getSearchResults(@RequestParam(value = "poem") String searchKeys, @PageableDefault(page = 1) Pageable pageable) {
        String searchKeysPlusRemoved = String.join(" ", searchKeys.split("[+]"));
        return poemService.searchForPoems(searchKeysPlusRemoved, pageable.getPageNumber());
    }

    @GetMapping("/favourites")
    public List<Poem> getFavourites(@PageableDefault(page = 1) Pageable pageable) {
        return poemService.getFavouritePoems(pageable.getPageNumber());
    }
    
    @GetMapping("/random")
    public List<Poem> getRandomPoems() {
        return poemService.getRandomPoems();
    }

    @PutMapping("bookmark/{id}")
    public ResponseEntity<String> putMethodName(@PathVariable String id, @RequestBody String isBookmarked) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            BookmarkStatus bookmarkStatus = objectMapper.readValue(isBookmarked, BookmarkStatus.class);
            Long poemId = Long.valueOf(id);
            int isBookmarkedInteger = Integer.valueOf(bookmarkStatus.getIsBookmarked());
            poemService.updateIsBookmarked(poemId, isBookmarkedInteger);
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("invalid input");
        }
        return ResponseEntity.ok("Updated Successfully");
    }
}