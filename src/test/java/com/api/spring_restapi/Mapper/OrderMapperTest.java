package com.api.spring_restapi.Mapper;

import com.api.spring_restapi.DTO.OrderDTO;
import com.api.spring_restapi.Entity.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class OrderMapperTest {

    @Test
    @DisplayName("Is OrderMapper Working ?")
    void isOrderMapperWork(){
        //given
        Order order = new Order(1,null,null,1,false,100,1,false);
        //when
        OrderDTO orderDTO = OrderMapper.INSTANCE.ordertodto(order);
        //then
        assertThat(orderDTO).isNotNull();
        assertThat(orderDTO.getId()).isEqualTo(order.getId());
        assertThat(orderDTO.getTotalPrice()).isEqualTo(order.getTotalPrice());
    }
}