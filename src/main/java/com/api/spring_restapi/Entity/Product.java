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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@SQLDelete(sql = "UPDATE products SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")

public class Product extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Product name is required")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Price is required")
    private Integer price;

    @NotEmpty(message = "Description is required")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotEmpty(message = "Detail is required")
    @Column(columnDefinition = "TEXT")
    private String detail;

    @NotEmpty(message = "Thumbnail is required")
    @Column(columnDefinition = "TEXT")
    private String thumbnail;

    private boolean deleted = Boolean.FALSE;

    //Relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id",insertable = false,updatable = false)
    @JsonIgnore
    private Category category;

    //Foreign key of category id in product table
    @NotNull(message = "Category ID is required")
    @Column(name = "category_id")
    private Integer category_id;

    //1 product have in many order detail
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Set<OrderDetail> orderDetails = new HashSet<>();

    //Update method for product
    public void updateProduct(Product product){
        this.name =product.getName();
        this.price=product.getPrice();
        this.description = product.getThumbnail();
        this.thumbnail = product.getThumbnail();
        this.detail = product.getDetail();
        this.category_id = product.getCategory_id();
    }

    //private Integer status;

//    @Column(name="created_at")
//    private LocalDate createAt;
//
//    @Column(name="updated_at")
//    private LocalDate updateAt;
//
//    @Column(name="delete_at")
//    private LocalDate deleteAt;

}
