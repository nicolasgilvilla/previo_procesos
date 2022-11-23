package com.previo.procesos.services;

import com.previo.procesos.models.Article;
import com.previo.procesos.models.Category;
import com.previo.procesos.repository.ArticleRepository;
import com.previo.procesos.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity getByCode(@PathVariable String code) {
        Optional<Article> listArticleForCode = articleRepository.findAllByCode(code);
        if (listArticleForCode.isPresent()) {
            return new ResponseEntity<>(listArticleForCode, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity createArticle(@RequestBody Article article) {
        Long idCategory = article.getCategory().getId();
        Optional<Category> articleFind = categoryRepository.findById(idCategory);
        if (articleFind.isPresent()) {
            try {
                articleRepository.save(article);
                return new ResponseEntity(article, HttpStatus.CREATED);
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @Override
    public ResponseEntity listArticle() {
        List<Article> listArticle = articleRepository.findAll();
        if (!listArticle.isEmpty()) {
            return new ResponseEntity<>(listArticle, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }


    @Override
    public ResponseEntity updateArticle(@PathVariable String code, @RequestBody Article article) {
        Optional<Article> articleFind = articleRepository.findAllByCode(code);
        try {
            if (articleFind.isPresent()) {
                Article articlePresent = articleFind.get();
                articlePresent.setCode(article.getCode());
                articlePresent.setName(article.getName());
                articlePresent.setDescription(article.getDescription());
                articlePresent.setDateOfRegister(article.getDateOfRegister());
                articlePresent.setCategory(article.getCategory());
                articlePresent.setStock(article.getStock());
                articlePresent.setPurchasePrice(article.getPurchasePrice());
                articlePresent.setSalePrice(article.getSalePrice());
                articleRepository.save(articlePresent);
                return new ResponseEntity(articlePresent, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity deleteArticle(@PathVariable String code) {
        Optional<Article> article = articleRepository.findAllByCode(code);
        if (article.isPresent()) {
            articleRepository.delete(article.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
