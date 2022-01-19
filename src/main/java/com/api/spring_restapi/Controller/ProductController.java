package com.api.spring_restapi.Controller;

import com.api.spring_restapi.DTO.ProductDTO;
import com.api.spring_restapi.Entity.Product;
import com.api.spring_restapi.Repository.CategoryRepository;
import com.api.spring_restapi.Response.RestPagination;
import com.api.spring_restapi.Response.RestResponse;
import com.api.spring_restapi.Service.ProductService;
import com.api.spring_restapi.Specification.FilterField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/products")
public class ProductController {

    //Constructor injection
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @Autowired
    CategoryRepository categoryRepository;

    //get all productDTO
    @RequestMapping("/list")
    public ResponseEntity<?> getAllProduct(
            @RequestParam(name = "page",defaultValue = "1") int page,
            @RequestParam(name = "pageSize",defaultValue = "5") int pageSize,
            @RequestParam(name = "minPrice",defaultValue ="-1") int minPrice,
            @RequestParam(name = "maxPrice",defaultValue = "-1") int maxPrice,
            @RequestParam(name = "name",required = false) String name,
            @RequestParam(name = "categoryID",defaultValue = "-1") int categoryID,
            @RequestParam(name = "id",defaultValue = "-1") int id
    ){
        //Convert to FilterField
        FilterField productFilter = FilterField.FilterFieldBuilder.aFilterField()
                .withId(id)
                .withCategoryId(categoryID)
                .withName(name)
                .withPage(page)
                .withPageSize(pageSize)
                .withMinPrice(minPrice)
                .withMaxPrice(maxPrice)
                .build();
        Page<Product> productPage = productService.findAll(productFilter);
        if(productPage.getContent().size() == 0){
            return new ResponseEntity<>(
                    new RestResponse.Success()
                            .setMessage("List is empty")
                            .build()
                    ,HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new RestResponse.Success()
                        //convert product -> productDTO
                        .addDatas(productPage.getContent().stream().map(this::convertProductDTO).collect(Collectors.toList()))
                        //format pagination response
                        .setPagination(new RestPagination(productPage.getNumber() + 1, productPage.getSize(), productPage.getTotalElements()))
                        .setStatus(HttpStatus.OK.value())
                        .build()
                ,HttpStatus.OK);
    }

    //get product by id
    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public ResponseEntity<?> getProduct(@PathVariable(name="id") Integer id){
        return new ResponseEntity<>(
                new RestResponse.Success()
                        .addData(productService.findById(id)).build(),
                HttpStatus.OK);
    }
    //create product
    @RequestMapping(method = RequestMethod.POST,value = "/add")
    public ResponseEntity<?> createProduct(@RequestBody @Valid Product product){
        return new ResponseEntity<>(new RestResponse.Success()
                .setMessage("Create Successfully")
                .addData(productService.save(product)).build(),
                HttpStatus.OK);
    }
    //update product
    @RequestMapping(method = RequestMethod.PUT,value = "/edit")
    public ResponseEntity<?> updateProduct(@RequestBody @Valid Product product){
        return new ResponseEntity<>(
                new RestResponse.Success()
                        .setMessage("Update Successfully")
                        .addData(productService.edit(product)).build()
                ,HttpStatus.OK);
    }
    //delete product
    @RequestMapping(method = RequestMethod.DELETE,value = "delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(
                new RestResponse.Success()
                        .addData(productService.delete(id))
                        .build()
                ,HttpStatus.OK);
    }
    //Method convert product to product dto
        private ProductDTO convertProductDTO(Product product){
        ProductDTO newProductDTO = new ProductDTO();
        newProductDTO.setId(product.getId());
        newProductDTO.setName(product.getName());
        newProductDTO.setDetail(product.getDetail());
        newProductDTO.setDescription(product.getDescription());
        newProductDTO.setPrice(product.getPrice());
        newProductDTO.setThumbnail(product.getThumbnail());
        //get category name
        String categoryName = categoryRepository.findById(product.getCategory_id()).get().getName();
        newProductDTO.setCategory_name(categoryName);
        return newProductDTO;
    }
}

