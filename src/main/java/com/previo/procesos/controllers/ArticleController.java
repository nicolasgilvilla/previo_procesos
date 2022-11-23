package com.previo.procesos.controllers;


import com.previo.procesos.models.Article;
import com.previo.procesos.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @GetMapping(value = "/article/{code}")
    public ResponseEntity getByCode(@PathVariable String code) {
        return articleService.getByCode(code);
    }

    @PostMapping(value = "/article")
    public ResponseEntity createArticle(@RequestBody Article article) {
        return articleService.createArticle(article);
    }


    @GetMapping(value = "/articles")
    public ResponseEntity listArticle() {
        return articleService.listArticle();
    }


    @PutMapping(value = "/article/{code}")
    public ResponseEntity updateArticle(@PathVariable String code, @RequestBody Article article) {
        return articleService.updateArticle(code, article);
    }

    @DeleteMapping(value = "/article/{code}")
    public ResponseEntity deleteArticle(@PathVariable String code) {
        return articleService.deleteArticle(code);
    }


}