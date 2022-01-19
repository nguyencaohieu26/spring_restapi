package com.api.spring_restapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class OrderDTO {
    private int id;
    private int totalPrice;
    private boolean checkOut;
    private String account_name;
}
