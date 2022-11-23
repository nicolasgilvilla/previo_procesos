package com.previo.procesos.controllers;


import com.previo.procesos.models.ArticleModel;
import com.previo.procesos.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @GetMapping(value = "/article/{code}")
    public ResponseEntity getByCode(@PathVariable String code) {
        return articleService.getByCode(code);
    }

    @PostMapping(value = "/article")
    public ResponseEntity createArticle(@RequestBody ArticleModel articleModel) {
        return articleService.createArticle(articleModel);
    }


    @GetMapping(value = "/articles")
    public ResponseEntity listArticle() {
        return articleService.listArticle();
    }


    @PutMapping(value = "/article/{code}")
    public ResponseEntity updateArticle(@PathVariable String code, @RequestBody ArticleModel articleModel) {
        return articleService.updateArticle(code, articleModel);
    }

    @DeleteMapping(value = "/article/{code}")
    public ResponseEntity deleteArticle(@PathVariable String code) {
        return articleService.deleteArticle(code);
    }


}