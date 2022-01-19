package com.api.spring_restapi.Repository;

import com.api.spring_restapi.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> , JpaSpecificationExecutor<Order> {
}
