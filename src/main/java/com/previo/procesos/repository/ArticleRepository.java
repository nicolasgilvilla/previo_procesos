package com.previo.procesos.repository;

import com.previo.procesos.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, String> {

    Optional<Article> findAllByCode(String code);

}