package com.previo.procesos.services;

import com.previo.procesos.data.FactoryTestData;
import com.previo.procesos.models.ArticleModel;
import com.previo.procesos.models.CategoryModel;
import com.previo.procesos.repository.ArticleRepository;
import com.previo.procesos.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ArticleServiceImplTest {

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void whenGetArticleByCodeReturnSuccess() {
        ArticleModel mockArticleModel = FactoryTestData.mockArticleModel();

        when(articleRepository.findAllByCode(anyString())).thenReturn(Optional.of(mockArticleModel));
        ResponseEntity mockArticleService = articleService.getByCode(mockArticleModel.getCode());

        Assertions.assertNotNull(mockArticleService);
    }

    @Test
    void whenGetArticleByCodeReturnNoFound() {
        ArticleModel mockArticleModel = FactoryTestData.mockArticleModel();

        when(articleRepository.findAllByCode(anyString())).thenReturn(Optional.empty());
        ResponseEntity mockArticleService = articleService.getByCode(mockArticleModel.getCode());

        Assertions.assertNotNull(mockArticleService);
        Assertions.assertEquals( 404, mockArticleService.getStatusCodeValue());
    }

    @Test
    void whenCreateArticleReturnSuccess() {
        ArticleModel mockArticleModel = FactoryTestData.mockArticleModel();
        CategoryModel mockCategoryModel = FactoryTestData.mockCategoryModel();

        when(categoryRepository.findById(mockCategoryModel.getId())).thenReturn(Optional.of(mockCategoryModel));
        when(articleRepository.save(any(ArticleModel.class))).thenReturn(mockArticleModel);
        ResponseEntity mockArticleService = articleService.createArticle(FactoryTestData.mockArticleModel());

        Assertions.assertNotNull(mockArticleService);
        verify(articleRepository).save(any(ArticleModel.class));
    }

    @Test
    void whenCreateArticleReturnBadRequest() {
        ArticleModel mockArticleModel = FactoryTestData.mockArticleModelBadRequest();
        CategoryModel mockCategoryModel = FactoryTestData.mockCategoryModel();

        when(categoryRepository.findById(mockCategoryModel.getId())).thenReturn(Optional.empty());
        when(articleRepository.save(any(ArticleModel.class))).thenReturn(null);
        ResponseEntity mockArticleService = articleService.createArticle(mockArticleModel);


        Assertions.assertNotNull(mockArticleService);
        Assertions.assertEquals( 400, mockArticleService.getStatusCodeValue());
    }

    @Test
    void whenCreateArticleReturnBadRequestThrows() {
        ArticleModel mockArticleModel = null;
        CategoryModel mockCategoryModel = FactoryTestData.mockCategoryModel();

        when(categoryRepository.findById(mockCategoryModel.getId())).thenReturn(Optional.empty());
        when(articleRepository.save(any(ArticleModel.class))).thenThrow(new NullPointerException());

        Assertions.assertThrows(NullPointerException.class, () ->{
            articleService.createArticle(mockArticleModel);
        });
    }

    @Test
    void whenListArticleReturnSuccess() {
        List<ArticleModel> mockArticleListModel = FactoryTestData.mockListArticleModel();
        when(articleRepository.findAll()).thenReturn(mockArticleListModel);
        ResponseEntity mockArticleService = articleService.listArticle();
        Assertions.assertNotNull(mockArticleService);
    }

    @Test
    void whenListArticleReturnNoFound() {
        when(articleRepository.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity mockArticleService = articleService.listArticle();

        Assertions.assertNotNull(mockArticleService);
        Assertions.assertEquals( 404, mockArticleService.getStatusCodeValue());
    }


    @Test
    void whenUpdateArticleReturnSuccess() {
        ArticleModel mockArticleModel = FactoryTestData.mockArticleModel();
        CategoryModel mockCategoryModel = FactoryTestData.mockCategoryModel();
        ArticleModel mockArticleModelUpdate = FactoryTestData.mockArticleUpdateModel();

        when(categoryRepository.findById(mockCategoryModel.getId())).thenReturn(Optional.of(mockCategoryModel));
        when(articleRepository.findById(mockArticleModel.getCode())).thenReturn(Optional.of(mockArticleModel));
        when(articleRepository.save(mockArticleModelUpdate)).thenReturn(mockArticleModelUpdate);

        ResponseEntity mockArticleService = articleService.updateArticle(mockArticleModel.getCode(),
                mockArticleModelUpdate);

        Assertions.assertNotNull(mockArticleService);
    }


}