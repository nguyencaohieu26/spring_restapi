package com.api.spring_restapi.Controller;

import com.api.spring_restapi.Entity.Category;
import com.api.spring_restapi.Response.RestResponse;
import com.api.spring_restapi.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/categories")
public class CategoryController {

    //Create constructor injection
    private  final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService1){
        this.categoryService = categoryService1;
    }

    //get all category
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getCategories(){
        return new ResponseEntity<>(
                new RestResponse.Success()
                        .addDatas(categoryService.findAllCategory())
                        .build()
                ,HttpStatus.OK);
    }

    //find category by id
    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public ResponseEntity<?> getCategory(@PathVariable(name="id") Integer id){
        return new ResponseEntity<>(
                new RestResponse.Success()
                        .addData(categoryService.findById(id))
                        .build()
                , HttpStatus.OK);
    }
    //create category
    @RequestMapping(method = RequestMethod.POST,value = "/add")
    public ResponseEntity<?> createCategory(@RequestBody @Valid Category category){
        return new ResponseEntity<>(
                new RestResponse.Success()
                        .addData(categoryService.save(category))
                        .build()
                ,HttpStatus.OK);
    }
    //update category
    @RequestMapping(method = RequestMethod.PUT,value = "/edit")
    public ResponseEntity<?> updateCategory(@RequestBody @Valid Category category){
        return new ResponseEntity<>(
                new RestResponse.Success()
                        .addData(categoryService.edit(category))
                        .build(),
                HttpStatus.OK
        );
    }
    //delete category
    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(name="id") Integer id){
        return new ResponseEntity<>("Delete Successfully"
                ,HttpStatus.OK);
    }
}
