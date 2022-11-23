package com.previo.procesos.controllers;


import com.previo.procesos.models.ArticleModel;
import com.previo.procesos.services.ArticleService;
import com.previo.procesos.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping(value = "/article/{code}")
    public ResponseEntity getByCode(@PathVariable String code, @RequestHeader(value = "Authorization") String token) {
        if (jwtUtil.getKey(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalid");
        }
        return articleService.getByCode(code);
    }

    @PostMapping(value = "/article")
    public ResponseEntity createArticle(@RequestBody ArticleModel articleModel, @RequestHeader(value = "Authorization") String token) {
        if (jwtUtil.getKey(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalid");
        }
        return articleService.createArticle(articleModel);
    }


    @GetMapping(value = "/articles")
    public ResponseEntity listArticle(@RequestHeader(value = "Authorization") String token) {
        if (jwtUtil.getKey(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalid");
        }
        return articleService.listArticle();
    }


    @PutMapping(value = "/article/{code}")
    public ResponseEntity updateArticle(@PathVariable String code, @RequestBody ArticleModel articleModel, @RequestHeader(value = "Authorization") String token) {
        if (jwtUtil.getKey(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalid");
        }
        return articleService.updateArticle(code, articleModel);
    }

    @DeleteMapping(value = "/article/{code}")
    public ResponseEntity deleteArticle(@PathVariable String code, @RequestHeader(value = "Authorization") String token) {
        if (jwtUtil.getKey(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalid");
        }
        return articleService.deleteArticle(code);
    }


}