package com.api.spring_restapi.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@SQLDelete(sql = "UPDATE orders SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")

public class Order extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //1 order have many order detail
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Set<OrderDetail> orderDetails = new HashSet<>();

    //Create relationship with account
    @ManyToOne
    @JoinColumn(name = "account_id",insertable = false,updatable = false)
    @JsonIgnore
    private Account account;

    @NotNull(message = "Customer ID is required")
    @Column(name = "account_id")
    private Integer account_id;

    @Column(name="check_out")
    private boolean checkOut = Boolean.FALSE;

    @Column(name="total_price")
    private int totalPrice;

    @Column(name="ship_status")
    private int shipStatus;

    private boolean deleted = Boolean.FALSE;

    //Date time
//    @Column(name="created_at")
//    private LocalDate createAt;
//
//    @Column(name = "updated_at")
//    private LocalDate updateAt;
//
//    @Column(name = "deleted_at")
//    private LocalDate deleteAt;
}
