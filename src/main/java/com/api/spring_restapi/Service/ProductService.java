package com.api.spring_restapi.Service;

import com.api.spring_restapi.DTO.ProductDTO;
import com.api.spring_restapi.Entity.Product;
import com.api.spring_restapi.Specification.FilterField;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Page<Product> findAll(FilterField filter);
//    List<ProductDTO> getAllProduct();
    ProductDTO findById(Integer id);
    ProductDTO save(Product product);
    Product edit(Product product);
    Optional<?> delete(Integer id);
}
