package kz.bit.kormefinall.api;

import kz.bit.kormefinall.dto.ProductDTO;
import kz.bit.kormefinall.models.Product;
import kz.bit.kormefinall.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/posts")
public class PostController {
    private final ProductService productService;
    @GetMapping(value = "/get-course-list")
    public List<ProductDTO> getPosts(){
        return productService.getPosts();
    }

}