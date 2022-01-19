package com.api.spring_restapi.Service;

import com.api.spring_restapi.DTO.CategoryDTO;
import com.api.spring_restapi.Entity.Category;
import com.api.spring_restapi.Exceptions.NotFoundException;
import com.api.spring_restapi.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository1){
        this.categoryRepository = categoryRepository1;
    }

//    @PersistenceContext
//    private EntityManager entityManager;

    @Override
    public Page<?> findAll(Object filter) {
        return null;
    }

    @Override
    public List<CategoryDTO> findAllCategory() {
        List<Category> list = categoryRepository.findAll();
        return list.stream().map(CategoryDTO::categoryDTO).collect(Collectors.toList());
    }

    @Override
        public CategoryDTO findById(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(!category.isPresent()){
            throw new NotFoundException("Category Is Not Found!");
        }
        return CategoryDTO.categoryDTO(category.get());
    }


    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category edit(Category newCategoryInfo) {
        Category categoryExist = categoryRepository.getById(newCategoryInfo.getId());
        categoryExist.updateCategory(newCategoryInfo);
        return categoryRepository.save(categoryExist);
    }

    //This is soft delete
    @Override
    public Optional<?> delete(Integer id) {
        Optional<?> category = categoryRepository.findById(id);
        if(category.isPresent()){
            categoryRepository.deleteById(id);
            return category;
        }else{
            throw new NotFoundException("Category Is Not Found!");
        }
    }
}
