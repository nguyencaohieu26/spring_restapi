package com.api.spring_restapi.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
@SQLDelete(sql = "UPDATE  categories SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Category extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Category Name is required")
    private String name;

    @NotEmpty(message = "Description is required")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotEmpty(message = "Thumbnail is required")
    @Column(columnDefinition = "TEXT")
    private String thumbnail;

    //Create soft delete field
    private boolean deleted = Boolean.FALSE;

    //Relationship
    /* 1 category have multiple product
    * */
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Product> products = new HashSet<>();

    //Update category method
    public void updateCategory(Category category){
        this.name = category.getName();
        this.description = category.getDescription();
        this.thumbnail = category.getThumbnail();
    }


//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "created_at")
//    private Date createAt;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "updated_at")
//    private Date updateAt;

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name="deleted_at")
//    private Date deleteAt;

}
