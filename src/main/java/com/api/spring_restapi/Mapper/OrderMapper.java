package com.api.spring_restapi.Mapper;

import com.api.spring_restapi.DTO.OrderDTO;
import com.api.spring_restapi.Entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper( OrderMapper.class );
    OrderDTO ordertodto(Order order);
}
