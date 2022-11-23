package com.previo.procesos.services;

import com.previo.procesos.models.ArticleModel;
import com.previo.procesos.models.CategoryModel;
import com.previo.procesos.repository.ArticleRepository;
import com.previo.procesos.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
        Optional<ArticleModel> listArticleForCode = articleRepository.findAllByCode(code);
        if (listArticleForCode.isPresent()) {
            return new ResponseEntity<>(listArticleForCode, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity createArticle(@RequestBody ArticleModel articleModel) {
        Long idCategory = articleModel.getCategoryModel().getId();
        Optional<CategoryModel> articleFind = categoryRepository.findById(idCategory);
        if (articleFind.isPresent()) {
            try {
                articleRepository.save(articleModel);
                return new ResponseEntity(articleModel, HttpStatus.CREATED);
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @Override
    public ResponseEntity listArticle() {
        List<ArticleModel> listArticleModel = articleRepository.findAll();
        if (!listArticleModel.isEmpty()) {
            return new ResponseEntity<>(listArticleModel, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }


    @Override
    public ResponseEntity updateArticle(@PathVariable String code, @RequestBody ArticleModel articleModel) {
        Optional<ArticleModel> articleFind = articleRepository.findAllByCode(code);
        try {
            if (articleFind.isPresent()) {
                ArticleModel articleModelPresent = articleFind.get();
                articleModelPresent.setCode(articleModel.getCode());
                articleModelPresent.setName(articleModel.getName());
                articleModelPresent.setDescription(articleModel.getDescription());
                articleModelPresent.setDateOfRegister(articleModel.getDateOfRegister());
                articleModelPresent.setCategoryModel(articleModel.getCategoryModel());
                articleModelPresent.setStock(articleModel.getStock());
                articleModelPresent.setPurchasePrice(articleModel.getPurchasePrice());
                articleModelPresent.setSalePrice(articleModel.getSalePrice());
                articleRepository.save(articleModelPresent);
                return new ResponseEntity(articleModelPresent, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity deleteArticle(@PathVariable String code) {
        Optional<ArticleModel> article = articleRepository.findAllByCode(code);
        if (article.isPresent()) {
            articleRepository.delete(article.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
