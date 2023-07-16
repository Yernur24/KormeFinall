package kz.bit.kormefinall.controllers;

import kz.bit.kormefinall.dto.ProductDTO;
import kz.bit.kormefinall.models.Category;
import kz.bit.kormefinall.models.Product;

import kz.bit.kormefinall.services.CategoryService;
import kz.bit.kormefinall.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor

public class HomeController {



    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/")
    public String index(Model model) {
        List<Product> products = productService.allProducts();
        List<Category> categories = categoryService.allCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "posts/index";
    }
    @GetMapping("/news")
    public String news() {
        return "news";
    }


    @GetMapping("/post/{productId}")
    public String productDetails(@PathVariable Long productId, Model model){
        List<Category> categories = categoryService.allCategories();
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "posts/post";
    }

    @GetMapping("/category/{categoryId}")
    public String getPostsByCategory(@PathVariable Long categoryId , Model model) {
        Category categories = categoryService.getCategoryById(categoryId);
        List<Product> products = productService.getProductsByCategory(categories);
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "posts/sort";
    }
//    @GetMapping("/search")
//    public String searchPostsByName(@RequestParam("name") String name, Model model) {
//        List<ProductDTO> searchResults = productService.searchPostsByName(name);
//        model.addAttribute("searchResults", searchResults);
//        return "posts/index";
//    }

}
