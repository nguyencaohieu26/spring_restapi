package com.api.spring_restapi.Service;

import com.api.spring_restapi.Entity.Order;
import com.api.spring_restapi.Specification.FilterField;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface OrderService {
    Page<?> findAll(FilterField filter);
    Order findById(Integer id);
    Order save(Order order);
    Order updateStatus(Integer id,int status);
    Optional<?> delete(Integer id);
}
