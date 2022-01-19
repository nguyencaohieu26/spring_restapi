package com.api.spring_restapi.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name ="order_details")
public class OrderDetail {

    @EmbeddedId
    private OrderDetailKey orderDetailKey;

    //Create relationship between Order Detail & Product
    @ManyToOne
    @MapsId("productID")
    @JoinColumn(name = "product_id")
    private Product product;

    //Create relationship between Order Detail & Order
    @ManyToOne
    @MapsId("orderID")
    @JsonIgnore
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "quanity")
    private int quanity;

    @Column(name = "unit_price")
    private int unitPrice;
}
