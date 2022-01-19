package com.api.spring_restapi.Service;

import com.api.spring_restapi.DTO.ProductDTO;
import com.api.spring_restapi.Entity.Category;
import com.api.spring_restapi.Entity.Product;
import com.api.spring_restapi.Entity.QProduct;
import com.api.spring_restapi.Enum.SearchOperation;
import com.api.spring_restapi.Exceptions.NotFoundException;
import com.api.spring_restapi.Exceptions.SystemException;
import com.api.spring_restapi.Repository.CategoryRepository;
import com.api.spring_restapi.Repository.ProductRepository;
import com.api.spring_restapi.Specification.FilterField;
import com.api.spring_restapi.Specification.ProductSpecification;
import com.api.spring_restapi.Specification.SearchCriteria;
import com.querydsl.core.QueryFactory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Page<Product> findAll(FilterField filter) {
        Specification<Product> spe = Specification.where(null);
        //
        PageRequest paging = PageRequest.of(filter.getPage() - 1, filter.getPageSize());
        //filter by product name
        if(filter.getName() != null && filter.getName().length() > 0){
            spe = spe.and(new ProductSpecification(new SearchCriteria(FilterField.NAME, SearchOperation.EQUALITY,filter.getName())));
        }
        //filter by category id
        if(filter.getCategoryId() > 0){
            spe = spe.and(new ProductSpecification(new SearchCriteria(FilterField.CATEGORY_ID,SearchOperation.EQUALITY,filter.getCategoryId())));
        }
        //filter by max price
        if(filter.getMaxPrice() > 0){
            spe = spe.and(new ProductSpecification(new SearchCriteria(FilterField.PRICE,SearchOperation.GREATER_THAN,filter.getMaxPrice())));
        }
        //filter by min price
        if(filter.getMinPrice() > 0){
            spe = spe.and(new ProductSpecification(new SearchCriteria(FilterField.PRICE,SearchOperation.LESS_THAN,filter.getMinPrice())));
        }
        //filter by id
        if(filter.getId() > 0){
            spe = spe.and(new ProductSpecification(new SearchCriteria(FilterField.PRICE,SearchOperation.EQUALITY,filter.getId())));
        }
        return productRepository.findAll(spe,paging);
    }

    @Override
    public ProductDTO findById(Integer id) {
        Optional<?> product = productRepository.findById(id);
        if(!product.isPresent()){
            throw new NotFoundException("Product Is Not Found!");
        }else{
            Product productExist = productRepository.findById(id).get();
            ProductDTO newProductDTO = new ProductDTO();
            newProductDTO.setCategory_name(categoryRepository.findById(productExist.getCategory_id()).get().getName());
            BeanUtils.copyProperties(productExist,newProductDTO);
            return newProductDTO;
        }
    }

    @Override
    public ProductDTO save(Product product) {
        //Check category code exist
        Optional<Category> categoryExist = categoryRepository.findById(product.getCategory_id());
        if(!categoryExist.isPresent()){
            throw new NotFoundException("Category Is Not Found!");
        }
        product.setStatus(1);
        productRepository.save(product);
        ProductDTO newProductDTO = new ProductDTO();
        BeanUtils.copyProperties(product,newProductDTO);
        return newProductDTO;
    }

    @Override
    public Product edit(Product product) {
        try{
            Product productExist = productRepository.getById(product.getId());
            productExist.updateProduct(product);
            return productRepository.save(productExist);
        }catch (Exception e){
            throw new SystemException("System Fail, Please try again!");
        }
    }

    @Override
    public Optional<?> delete(Integer id) {
        Optional<?> product = productRepository.findById(id);
        if(product.isPresent()) {
            productRepository.deleteById(id);
            return product;
        }else{
            throw new NotFoundException("Product Is Not Found!");
        }
    }
}
