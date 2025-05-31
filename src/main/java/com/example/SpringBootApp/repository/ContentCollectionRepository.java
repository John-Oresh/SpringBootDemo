package com.example.SpringBootApp.repository;

import com.example.SpringBootApp.model.Content;
import com.example.SpringBootApp.model.Status;
import com.example.SpringBootApp.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContentCollectionRepository {
    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository() {}

    @PostConstruct
    private void init() {
        Content c = new Content(1, "My First Blog Post", "First Ever Blog Post", Status.IDEA, Type.DOCUMENT, LocalDateTime.now(), null, "");

        contentList.add(c);
    }

    public boolean existsById(Integer id) {
//        return contentList.stream().anyMatch(c -> c.id().equals(id));
        return contentList.stream().filter(c -> c.id().equals(id)).count() == 1;
    }

    public void save(Content c) {
        contentList.removeIf(content -> content.id().equals(c.id()));
        contentList.add(c);
    }

    public List<Content> findAll() {
        return contentList;
    }
}
