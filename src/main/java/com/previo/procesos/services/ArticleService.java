package com.previo.procesos.services;

import com.previo.procesos.models.ArticleModel;
import org.springframework.http.ResponseEntity;

public interface ArticleService {

    ResponseEntity getByCode(String code);

    ResponseEntity createArticle(ArticleModel articleModel);

    ResponseEntity listArticle();


    ResponseEntity updateArticle(String code, ArticleModel articleModel);

    ResponseEntity deleteArticle(String code);
}
