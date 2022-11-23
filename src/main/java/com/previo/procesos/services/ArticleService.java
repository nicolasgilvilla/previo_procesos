package com.previo.procesos.services;

import com.previo.procesos.models.Article;
import org.springframework.http.ResponseEntity;

public interface ArticleService {

    ResponseEntity getByCode(String code);

    ResponseEntity createArticle(Article article);

    ResponseEntity listArticle();


    ResponseEntity updateArticle(String code, Article article);

    ResponseEntity deleteArticle(String code);
}
