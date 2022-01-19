package com.api.spring_restapi.DTO;

import com.api.spring_restapi.Entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private int id;
    private String name;
    private String description;
    private String thumbnail;

    public static CategoryDTO categoryDTO(Category category){
        CategoryDTO categorydto = new CategoryDTO();
        categorydto.setId(category.getId());
        categorydto.setDescription(category.getDescription());
        categorydto.setName(category.getName());
        categorydto.setThumbnail(category.getThumbnail());
        return  categorydto;
    }
}
