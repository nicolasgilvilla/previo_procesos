package com.previo.procesos.data;

import com.previo.procesos.models.ArticleModel;
import com.previo.procesos.models.CategoryModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FactoryTestData {

    public static ArticleModel mockArticleModel() {
        ArticleModel articleModel = new ArticleModel();
        articleModel.setId(1L);
        articleModel.setCode("123");
        articleModel.setName("Article Test");
        articleModel.setStock(2);
        articleModel.setCategoryModel(mockCategoryModel());
        articleModel.setDescription("This article is for test");
        articleModel.setSalePrice(2000F);
        articleModel.setPurchasePrice(2000F);
        articleModel.setDateOfRegister(new Date(10, 10, 20));
        return articleModel;
    }

    public static ArticleModel mockArticleUpdateModel() {
        ArticleModel articleModel = new ArticleModel();
        articleModel.setId(1L);
        articleModel.setCode("123443");
        articleModel.setName("Article Test Update");
        articleModel.setStock(2);
        articleModel.setCategoryModel(mockCategoryModel());
        articleModel.setDescription("This article is for test");
        articleModel.setSalePrice(2000F);
        articleModel.setPurchasePrice(2000F);
        articleModel.setDateOfRegister(new Date(10, 10, 20));
        return articleModel;
    }

    public static ArticleModel mockArticleModelBadRequest() {
        ArticleModel articleModel = new ArticleModel();
        articleModel.setCategoryModel(mockCategoryModel());
        return articleModel;
    }

    public static List<ArticleModel> mockListArticleModel() {
        List<ArticleModel> articleModelList = new ArrayList<>();
        String code = "1";
        for (int i = 0; i < 10; i++) {
            ArticleModel articleModel = new ArticleModel();
            articleModel.setId(1L);
            articleModel.setCode(code);
            articleModel.setName("Article Test");
            articleModel.setStock(2);
            articleModel.setCategoryModel(mockCategoryModel());
            articleModel.setDescription("This article is for test");
            articleModel.setSalePrice(2000F);
            articleModel.setPurchasePrice(2000F);
            articleModel.setDateOfRegister(new Date(10, 10, 20));

            code = code + "1";
            articleModelList.add(articleModel);
        }
        return articleModelList;
    }

    public static CategoryModel mockCategoryModel() {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(1L);
        categoryModel.setName("Category test");
        categoryModel.setDescription("This category is for test");
        return categoryModel;
    }

    public static CategoryModel mockCategoryModelUpdate() {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(1L);
        categoryModel.setName("Category update test");
        categoryModel.setDescription("This category is for test update");
        return categoryModel;
    }
}
