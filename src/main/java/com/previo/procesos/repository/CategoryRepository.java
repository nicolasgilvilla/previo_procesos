package com.previo.procesos.repository;

import com.previo.procesos.models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {


}
