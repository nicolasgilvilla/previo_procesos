package com.previo.procesos.repository;

import com.previo.procesos.models.ArticleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<ArticleModel, String> {

    Optional<ArticleModel> findAllByCode(String code);

}