package kz.bit.kormefinall.controllers;

import kz.bit.kormefinall.models.Category;
import kz.bit.kormefinall.models.Product;

import kz.bit.kormefinall.services.CategoryService;
import kz.bit.kormefinall.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor

public class HomeController {



    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/")
    public String index(Model model) {
        List<Product> products = productService.allProducts();
        model.addAttribute("products", products);
        return "posts/index";
    }


    @GetMapping("/detail/{productId}")
    public String productDetails(@PathVariable Long productId, Model model){
        List<Category> categories = categoryService.allCategories();
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "posts/post";
    }

}
