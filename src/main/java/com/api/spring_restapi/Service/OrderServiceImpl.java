package com.api.spring_restapi.Service;

import com.api.spring_restapi.Entity.Order;
import com.api.spring_restapi.Enum.SearchOperation;
import com.api.spring_restapi.Exceptions.NotFoundException;
import com.api.spring_restapi.Repository.OrderRepository;
import com.api.spring_restapi.Specification.FilterField;
import com.api.spring_restapi.Specification.OrderSpecification;
import com.api.spring_restapi.Specification.SearchCriteria;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Page<?> findAll(FilterField filter) {
        Specification<Order> spe = Specification.where(null);
        PageRequest paging = PageRequest.of(filter.getPage() - 1,filter.getPageSize());
        if(filter.getId() > 0){
            spe = spe.and(new OrderSpecification(new SearchCriteria(FilterField.ID,SearchOperation.EQUALITY,filter.getId())));
        }
        if(filter.getAccountId() > 0){
            spe = spe.and(new OrderSpecification(new SearchCriteria(FilterField.ACCOUNT_ID,SearchOperation.EQUALITY,filter.getAccountId())));
        }
        if(filter.getMinPrice() > 0){
            spe = spe.and(new OrderSpecification(new SearchCriteria(FilterField.ORDER_PRICE,SearchOperation.LESS_THAN,filter.getMinPrice())));
        }
        if(filter.getMaxPrice() > 0){
            spe = spe.and(new OrderSpecification(new SearchCriteria(FilterField.ORDER_PRICE,SearchOperation.GREATER_THAN,filter.getMaxPrice())));
        }
        if(filter.getShipStatus() > 0){
            spe = spe.and(new OrderSpecification(new SearchCriteria(FilterField.SHIPSTATUS,SearchOperation.EQUALITY,filter.getShipStatus())));
        }
        //check date
        if(filter.getDateEnd() != null && filter.getDateEnd().length() > 0){

        }
        if(filter.getDateStart() != null && filter.getDateStart().length() > 0){

        }
        return orderRepository.findAll(spe,paging);
    }

    @Override
    public Order findById(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            return order.get();
        }else{
            throw new NotFoundException("Order Is Not Found!");
        }
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateStatus(Integer id, int status) {
        Optional<Order> order = orderRepository.findById(id);
        if(!order.isPresent()){
            throw new NotFoundException("Order Is Not Found!");
        }
        Order orderExist = order.get();
        orderExist.setShipStatus(status);
        return orderExist;
    }

    @Override
    public Optional<?> delete(Integer id) {
        Optional<?> order = orderRepository.findById(id);
        if(order.isPresent()){
            orderRepository.deleteById(id);
            return order;
        }else{
            throw new NotFoundException("Order Is Not Found!");
        }
    }
}
