package kz.bit.kormefinall.controllers;


import kz.bit.kormefinall.models.Category;
import kz.bit.kormefinall.models.Product;
import kz.bit.kormefinall.models.User;
import kz.bit.kormefinall.services.CategoryService;
import kz.bit.kormefinall.services.ProductService;
import kz.bit.kormefinall.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@AllArgsConstructor

public class HomeController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String index(Model model){
        List<Product> products = productService.allProducts();
        model.addAttribute("products", products);
        return "index";
    }
    @GetMapping("/add-products")
    public String addRequest(Model model){
        List<Category> categories = categoryService.allCategories();
        model.addAttribute("categories", categories);
        return "add-products";
    }

    @PostMapping("/add-products")
    public String addRequest(Product product){
        productService.save(product);
        return "redirect:/";
    }

    @GetMapping("/detail/{productId}")
    public String productDetails(@PathVariable Long productId, Model model){
        List<Category> categories = categoryService.allCategories();
        Product products = productService.getProductById(productId);
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "post";
    }

//    @GetMapping("/product/{id}")
//    public String productInfo(@PathVariable Long id, Model model) {
//        List<Product> products = productService.allRequests();
//        model.addAttribute("product", products);
//        model.addAttribute("images", products.getImages());
//        return "product-info";
//    }

    @GetMapping(value = "/sign-in-page")
    public String signinPage() {
        return "signin";
    }

    @GetMapping(value = "/sign-up-page")
    public String signupPage() {
        return "signup";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/profile")
    public String profilePage() {
        return "profile";
    }

    @GetMapping(value = "/403-page")
    public String accessDenied() {
        return "403";
    }

    @GetMapping(value = "/update-password-page")
    public String updaetPasswordPage() {
        return "update-password";
    }

    @PostMapping(value = "/to-sign-up")
    public String toSignUp(@RequestParam(name = "user_email") String email,
                           @RequestParam(name = "user_password") String password,
                           @RequestParam(name = "user_repeat_password") String repeatPassword,
                           @RequestParam(name = "user_full_name") String fullName) {
        if (password.equals(repeatPassword)) {
            User user = new User();
            user.setEmail(email);
            user.setFullName(fullName);
            user.setPassword(password);
            User newUser = userService.addUser(user);
            if (newUser != null) {
                return "redirect:/sign-up-page?success";
            } else {
                return "redirect:/sign-up-page?emailerror";
            }
        } else {
            return "redirect:/sign-up-page?passworderror";
        }
    }

    @PostMapping(value = "/to-update-password")
    public String toUpdatePassword(
            @RequestParam(name = "user_old_password") String oldPassword,
            @RequestParam(name = "user_new_password") String newPassword,
            @RequestParam(name = "user_repeat_new_password") String repeatNewPassword) {

        if (newPassword.equals(repeatNewPassword)) {


            User user = userService.updatePassword(newPassword, oldPassword);
            if (user != null) {
                return "redirect:/update-password-page?success";
            } else {
                return "redirect:/update-password-page?oldpassworderror";
            }

        } else {
            return "redirect:/update-password-page?passwordmismatch";
        }
    }
}
