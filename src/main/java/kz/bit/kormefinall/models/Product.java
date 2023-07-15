package kz.bit.kormefinall.models;

import jakarta.persistence.*;
        import lombok.Getter;
        import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String Name;

    @Column(name = "author")
    private String Author;

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private double price;

    @ManyToOne
    private Category category;


    public String loadImage(){
        if (image == null || image.isEmpty()) {
            return "postsim/products/default.png";
        }
        return image;
    }

}

