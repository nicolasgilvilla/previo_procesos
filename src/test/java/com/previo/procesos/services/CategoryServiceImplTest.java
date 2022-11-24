package com.previo.procesos.services;

import com.previo.procesos.data.FactoryTestData;
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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void whenCreateCategoryReturnSuccess() {
        CategoryModel mockCategoryModel = FactoryTestData.mockCategoryModel();

        when(categoryRepository.save(any(CategoryModel.class))).thenReturn(mockCategoryModel);
        ResponseEntity mockCategoryService = categoryService.createCategory(FactoryTestData.mockCategoryModel());

        Assertions.assertNotNull(mockCategoryService);
        verify(categoryRepository).save(any(CategoryModel.class));
    }

    @Test
    void whenCreateCategoryReturnNoFound() {
        CategoryModel mockCategoryModel = null;

        when(categoryRepository.save(mockCategoryModel)).thenReturn(any());
        ResponseEntity mockCategoryService = categoryService.createCategory(any());


        Assertions.assertNotNull(mockCategoryService);
        Assertions.assertEquals(404, mockCategoryService.getStatusCodeValue());
    }

    @Test
    void whenUpdateCategoryReturnBadRequest() {
        CategoryModel mockCategoryModel = FactoryTestData.mockCategoryModel();

        when(categoryRepository.save(any(CategoryModel.class))).thenReturn(mockCategoryModel);
        ResponseEntity mockCategoryService = categoryService.updateCategory(mockCategoryModel.getId(), FactoryTestData.mockCategoryModel());

        Assertions.assertNotNull(mockCategoryService);
        Assertions.assertEquals(400, mockCategoryService.getStatusCodeValue());
    }

    @Test
    void whenUpdateCategoryReturnSuccess() {
        CategoryModel mockCategoryModel = FactoryTestData.mockCategoryModel();
        CategoryModel mockCategoryModelUpdate = FactoryTestData.mockCategoryModelUpdate();
        given(categoryRepository.findById(mockCategoryModel.getId())).willReturn(Optional.of(mockCategoryModel));
        given(categoryRepository.save(mockCategoryModelUpdate)).willReturn(mockCategoryModelUpdate);


        ResponseEntity articleResponse = categoryService.updateCategory(mockCategoryModel.getId(), mockCategoryModelUpdate);

        Assertions.assertNotNull(articleResponse);
    }

}