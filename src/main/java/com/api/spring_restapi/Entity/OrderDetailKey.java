package com.api.spring_restapi.Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
@Getter
@Setter
public class OrderDetailKey implements Serializable {

    @Column(name ="order_id",nullable = false)
    private Integer orderID;

    @Column(name = "product_id",nullable = false)
    private Integer productID;
}
