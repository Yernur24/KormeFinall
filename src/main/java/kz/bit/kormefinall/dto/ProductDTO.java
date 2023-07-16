package kz.bit.kormefinall.dto;
import kz.bit.kormefinall.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String title;
    private String author;
    private String content;
    private String image;
    private double price;
    private Category category;

}