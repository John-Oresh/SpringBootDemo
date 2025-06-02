package com.example.SpringBootApp.controller;

import com.example.SpringBootApp.model.Content;
import com.example.SpringBootApp.repository.ContentCollectionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {
    private final ContentCollectionRepository contentCollectionRepository;

    public ContentController(ContentCollectionRepository contentCollectionRepository) {
        this.contentCollectionRepository = contentCollectionRepository;
    }

    @GetMapping("/getContent")
    public List<Content> getAllContent() {
        return contentCollectionRepository.findAll();
    }

    @PostMapping("/create")
    public void create(@RequestBody Content content) {
        contentCollectionRepository.save(content);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody Content content) {
        if(!contentCollectionRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
        contentCollectionRepository.save(content);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
//        contentCollectionRepository.findAll().removeIf(content -> content.id().equals(id));
        contentCollectionRepository.delete(id);
    }
}
