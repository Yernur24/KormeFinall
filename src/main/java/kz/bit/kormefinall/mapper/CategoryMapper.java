package kz.bit.kormefinall.mapper;

import kz.bit.kormefinall.dto.CategoryDTO;
import kz.bit.kormefinall.models.Category;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CategoryMapper {
    //    @Mapping(source = "name", target = "Name")
    CategoryDTO toDto(Category category);

    //    @Mapping(source = "Name", target = "name")
    Category toModel(CategoryDTO categoryDTO);

    List<CategoryDTO> toDtoList(List<Category> categoryList);
    List<Category> toModelList(List<CategoryDTO> categoryList);

}
