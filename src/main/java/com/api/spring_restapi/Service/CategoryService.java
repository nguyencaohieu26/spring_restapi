package com.api.spring_restapi.Service;

import com.api.spring_restapi.DTO.CategoryDTO;
import com.api.spring_restapi.Entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Page<?> findAll(Object filter);
    List<CategoryDTO> findAllCategory();
    CategoryDTO findById(Integer id);
    Category save (Category category);
    Category edit(Category newCategoryInfo);
    Optional<?> delete(Integer id);
}
