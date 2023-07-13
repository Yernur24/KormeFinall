package kz.bit.kormefinall.mapper;


import kz.bit.kormefinall.dto.ProductDTO;
import kz.bit.kormefinall.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

//    @Mapping(source = "name", target = "Name")
    ProductDTO toDto(Product product);

//    @Mapping(source = "Name", target = "name")
    Product toModel(ProductDTO productDTO);

    List<ProductDTO> toDtoList(List<Product> productList);
    List<Product> toModelList(List<ProductDTO> productList);

}
